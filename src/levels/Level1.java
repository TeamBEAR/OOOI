package levels;

import processing.core.PApplet;
import processing.data.StringList;
import core.Agent;

public class Level1 extends Level{

	String action;//action enabeled
	int state;
	int[] green;

	public Level1(PApplet parent, Agent agent){
		super(parent, agent);
		this.action = new String("accelerer");
		green = new int[3];
		green[0]=0;
		green[1]=255;
		green[2]=0;
		state=0;
	}

	//draw game scenery
	public void draw_scenery(){
		parent.fill(255);
		parent.noStroke();
		parent.rect( (float) (parent.width*0.75), (float) 0, (float) 50, (float) (parent.height*0.5-50));
		parent.rect( (float) (parent.width*0.75), (float) (parent.height*0.5+50), (float) 50, (float) (parent.height*0.5));
	}



	@Override
	public void draw(){
		//second game state : the first level 
		//agent must speed up to quit the left area of the game scenery 
		//and pass the door
	    parent.background(0); 
	    draw_scenery();
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
		return new Level2(this.parent,this.agent);
	}


}
