package levels;

import core.Agent;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Level4 extends Level{

	int state;
	public Level4(PApplet parent, Agent agent){
		super(parent, agent);
		parser.resetLevelFinished();

		agent.setPos(-50,(float)(parent.height*0.5));
		agent.setRadarActive(false);
		agent.set_speed(10,0);
		createObstacles();
		state=0;
	}

	private void createObstableRect(float x0,float y0,float width,float height){
		PShape obst = parent.createShape(parent.RECT,
				x0,
				y0, 
				width, 
				height
				);
		obst.setFill(255);
		obst.setStroke(255);
		obstacles.addChild(obst);

	}
	private void createObstacles(){

		createObstableRect((float)0,(float)(0),(float)parent.width,(float)50);
		createObstableRect((float)0,(float)(parent.height*0.5+parent.height*0.25),(float)parent.width,(float)50);
		createObstableRect((float)(parent.width-50),(float)150,(float)50,(float)(parent.height*0.75-150));
		createObstableRect((float)(parent.width*0.6),(float)150,(float)50,(float)(parent.height*0.75-100));
		createObstableRect((float)(parent.width*0.75),(float)0,(float)50,(float)(parent.height*0.75-100));	
	}

	private void collision(){

		float[] obstacleParameters;
		PVector[] corners = new PVector[4];
		for (int i=0;i<4;i++)
			corners[i] = new PVector(0, 0);

		float[] obstacle_limits = new float[4];
		float[] farthest_lines = new float[4];

		float x, y;
		float dist;

		float bb_x0=agent.get_x()-(agent.get_width()/2);
		float bb_y0=agent.get_y()-(agent.get_height()/2);
		float bb_x1=agent.get_x()+(agent.get_width()/2);
		float bb_y1=agent.get_y()+(agent.get_height()/2);

		parent.stroke(255,0,9);
		parent.noFill();

		parent.rect(bb_x0,bb_y0,agent.get_width(),agent.get_height());
		parent.stroke(255);
		parent.fill(255);
		parent.println("nb obstacles : %d\n",obstacles.getChildCount());

		for(int i=0; i < obstacles.getChildCount(); i++){
			obstacleParameters = obstacles.getChild(i).getParams();
			//left
			obstacle_limits[0] = obstacleParameters[0] - .5f*obstacleParameters[2];
			//right
			obstacle_limits[1] = obstacleParameters[0] + .5f*obstacleParameters[2];
			//upper
			obstacle_limits[2] = obstacleParameters[1] - .5f*obstacleParameters[3];
			//lower
			obstacle_limits[3] = obstacleParameters[1] + .5f*obstacleParameters[3];
			
			float xmin=Math.min(obstacle_limits[0],obstacle_limits[1]);
			float xmax=Math.max(obstacle_limits[0],obstacle_limits[1]);

			if(bb_x1>xmin && bb_x1<xmax){
				agent.inverse_x_speed();
			}else if(bb_x0>xmin && bb_x0<xmax){
				agent.inverse_x_speed();
				
			}

			float ymin=Math.min(obstacle_limits[2],obstacle_limits[3]);
			float ymax=Math.max(obstacle_limits[2],obstacle_limits[3]);
			
			if(bb_y1>ymin && bb_y1<ymax){
				agent.inverse_y_speed();
			}else if(bb_y0>ymin && bb_y0<ymax){
				agent.inverse_y_speed();				
			}

		}
	}


	@Override
	public void draw(){
		if(state==0){
			if(agent.get_x()>50){
				createObstableRect((float)0,(float)0,(float)50,(float)(parent.height*0.75));
				state++;
			}
		}
		//second game state : the first level 
		//agent must speed up to quit the left area of the game scenery 
		//and pass the door
		parent.background(0); 
		drawScenery();
		print_request();
		if(parser.isEnterTouch()){

		}
		agent.draw();
		collision();
		if(agent.isOutOfBounds()){
			//this.setFinished(true);
		}
	}



	@Override
	public ILevel getNextLevel() {
		// TODO Auto-generated method stub
		return new EndLevel(this.parent,this.agent);
	}

}
