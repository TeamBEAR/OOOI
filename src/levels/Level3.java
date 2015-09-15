package levels;

import core.Agent;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Level3 extends Level{
	
	PShape obstacles;
	PVector bg_color;
	int state;

	public Level3(PApplet parent, Agent agent){
		super(parent, agent);
		obstacles = parent.createShape(parent.GROUP);
		bg_color = new PVector(0, 0, 0);
		state = 0;
		
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
	
	//print what user tip
	public void print_request(){
		parent.textSize(32);
		parent.fill(125);  
		parent.text("> " + request, 10, (float) (parent.height*0.85));
	}
	
	public void drawScenery(){
		parent.noStroke();
		parent.shape(obstacles);
	}
	
	public PShape getObstacles() {
		return obstacles;
	}
	
	@Override
	public void draw() {
		int[] color = {0, 195, 195};
		
		if(agent.radarReadsRectangle(obstacles))
			bg_color=new PVector(255, 0, 0);
		
		parent.background(
				bg_color.x,
				bg_color.y,
				bg_color.z);
		
		if (bg_color.mag()>0)
			bg_color.sub(17, 0, 0);
		else
			bg_color.set(0, 0, 0);

		drawScenery();
		
	      if(parser.isEnterTouch()){
	            if(parser.executeInput(agent)){
	                if(agent.isRadarActive() && state == 0)
	                    state=1;
	                else if(!agent.isRadarActive() && state == 1)
	                    state=2;
	                else if(agent.isRadarActive() && agent.isMoving())
	                    state=3;
	            }
	            if(parser.isLevelFinished()){
	                parser.resetLevelFinished();
	                this.setFinished(true);
	            }
	            parser.clear();
	        }
		
		switch(state){
		case 0:
			print_hint(color, "allumer.radar");
			break;
		case 1:
			print_hint(color, "eteindre.radar");
			break;
		case 2:
			print_hint(color, "allumer.radar et accelerer");
			break;
		case 3:
			color[1] = 0;
			color[2] = 255;
			state=4;
			break;
		case 4:
			print_hint(color, "continuer");
			break;
		}
		//parent.textSize(32);
		//parent.text("to do...",parent.width/2,parent.height/2);
		print_request();
		agent.draw();
	}
	
	@Override
	public ILevel getNextLevel() {
		// TODO Auto-generated method stub
		return new EndLevel(this.parent,this.agent);
	}
}