package levels;

import core.Agent;
import processing.core.PApplet;
import processing.core.PShape;

public class Level4 extends Level{

	public Level4(PApplet parent, Agent agent){
		super(parent, agent);
		
		createObstacles();
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
		
		createObstableRect((float)0,(float)0.,(float)50.,(float)(parent.height*0.5-50.));
		createObstableRect((float)0,(float)(parent.height*0.5+50),(float)50.,(float)(parent.height*0.25));
		createObstableRect((float)0,(float)(0),(float)parent.width,(float)50);
		createObstableRect((float)0,(float)(parent.height*0.5+parent.height*0.25),(float)parent.width,(float)50);
		createObstableRect((float)(parent.width-50),(float)150,(float)50,(float)(parent.height*0.75-150));
		createObstableRect((float)(parent.width*0.6),(float)150,(float)50,(float)(parent.height*0.75-100));
		createObstableRect((float)(parent.width*0.75),(float)0,(float)50,(float)(parent.height*0.75-100));	
	}
	
	private void collision(){
		
	}


	@Override
	public void draw(){
		//second game state : the first level 
		//agent must speed up to quit the left area of the game scenery 
		//and pass the door
	    parent.background(0); 
	    drawScenery();
		print_request();
		if(parser.isEnterTouch()){

		}
		agent.draw();
		if(agent.isOutOfBounds()){
			this.setFinished(true);
		}
	}
	


	@Override
	public ILevel getNextLevel() {
		// TODO Auto-generated method stub
		return new EndLevel(this.parent,this.agent);
	}

}
