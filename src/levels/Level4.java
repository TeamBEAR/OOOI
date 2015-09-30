package levels;

import core.Context;
import core.Main;
import core.types.Agent;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Level4 extends Level{

	int state;String[] directions;
	int[] command_color;
	int current_direction;
	PVector bg_color, color;

	public Level4(Main parent){
		super(parent);
		this.request = "";
		this.current_direction = 0;
		

		bg_color = new PVector(0, 0, 0);
		color = new PVector(0, 255, 0);

		this.command_color = new int[3];

		this.directions = new String[4];
		this.directions[0] = "virer.droite";
		this.directions[1] = "virer.gauche";
		this.directions[2] = "arreter";
		this.directions[3] = "continuer";
		
		if(!parent.getAgent().isMoving()){
		    parent.getAgent().speed_up();
		}

		interpreter.resetLevelFinished();

		parent.getAgent().setPos(10,(float)(parent.height*0.5));
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

		float bb_x0=parent.getAgent().get_x()-(parent.getAgent().get_width()/2);
		float bb_x1=parent.getAgent().get_x()+(parent.getAgent().get_width()/2);
		float bb_y0=parent.getAgent().get_y()-(parent.getAgent().get_height()/2);
		float bb_y1=parent.getAgent().get_y()+(parent.getAgent().get_height()/2);

		for(int i=0; i < obstacles.getChildCount(); i++){
			obstacleParameters = obstacles.getChild(i).getParams();
			//left
			obstacle_limits[0] = obstacleParameters[0];
			//right
			obstacle_limits[1] = obstacleParameters[0] + obstacleParameters[2];
			//upper
			obstacle_limits[2] = obstacleParameters[1];
			//lower
			obstacle_limits[3] = obstacleParameters[1] + obstacleParameters[3];

			float xmin=Math.min(obstacle_limits[0],obstacle_limits[1]);
			float xmax=Math.max(obstacle_limits[0],obstacle_limits[1]);


			float ymin=Math.min(obstacle_limits[2],obstacle_limits[3]);
			float ymax=Math.max(obstacle_limits[2],obstacle_limits[3]);

			if(bb_x1>xmin && bb_x1<xmax){
				if(bb_y1>ymin && bb_y1<ymax){
				    parent.getAgent().inverse_x_speed();
				}
			}else if(bb_x0>xmin && bb_x0<xmax){
				if(bb_y0>ymin && bb_y0<ymax){
				    parent.getAgent().inverse_x_speed();
				}

			}

			if(bb_y1>ymin && bb_y1<ymax){
				if(bb_x1>xmin && bb_x1<xmax){
				    parent.getAgent().inverse_y_speed();
				}	
			}else if(bb_y0>ymin && bb_y0<ymax){
				if(bb_x0>xmin && bb_x0<xmax){
				    parent.getAgent().inverse_y_speed();
				}					
			}


		}
	}


	@Override
	public void draw(){
		

		if(parent.getAgent().radarReadsRectangle(obstacles))
			bg_color=new PVector(255, 0, 0);

		parent.background(
				bg_color.x,
				bg_color.y,
				bg_color.z);

		if (bg_color.mag()>0)
			bg_color.sub(17, 0, 0);
		else
			bg_color.set(0, 0, 0);
		
		if(interpreter.isEnterTouch()){
		    interpreter.executeInput(parent.getAgent());
		    interpreter.clear();
		}

		if(state==0){
			if(parent.getAgent().get_x()>100){
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
		parent.getAgent().draw();
		collision();
		if(state>0){
			if(parent.getAgent().isOutOfBounds()){

				this.setFinished(true);
			}
		}
	}



	@Override
	public ILevel getNextLevel() {
		// TODO Auto-generated method stub
		return new EndLevel(this.parent);
	}

}
