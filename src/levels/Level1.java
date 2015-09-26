package levels;

import processing.core.PApplet;
import processing.core.PShape;
import core.Agent;

public class Level1 extends Level{

	String action;//action enabeled
	int[] green;

	public Level1(PApplet parent, Agent agent){
		super(parent, agent);
		this.action = new String("accelerer");
		green = new int[3];
		green[0]=0;
		green[1]=255;
		green[2]=0;
		
		createObstacles();
	}
	
	private void createObstacles(){
		PShape upper_rect = parent.createShape(parent.RECT,
				(float) (parent.width*0.75),
				(float) 0, 
				(float) 50, 
				(float) (parent.height*0.5-50)
				);
		upper_rect.setFill(255);


		PShape lower_rect = parent.createShape(parent.RECT,
				(float) (parent.width*0.75), 
				(float) (parent.height*0.5+50), 
				(float) 50, 
				(float) (parent.height*0.5)
				);
		lower_rect.setFill(255);


		obstacles.addChild(upper_rect);
		obstacles.addChild(lower_rect);

		
	}


	@Override
	public void draw(){
		//second game state : the first level 
		//agent must speed up to quit the left area of the game scenery 
		//and pass the door
	    parent.background(0); 
	    drawScenery();
		print_hint(green, action);
		print_request();
		if(parser.isEnterTouch()){
			if(parser.getRequest().equals(action)){
				parser.clear();
				agent.speed_up();
			}
		}
		agent.draw();
		if(agent.isOutOfBounds()){
			this.setFinished(true);
		}
	}
	


	@Override
	public ILevel getNextLevel() {
		// TODO Auto-generated method stub
		return new Level4(this.parent,this.agent);
	}


}
