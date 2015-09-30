package levels;

import core.Context;
import core.Main;
import core.types.Agent;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Level2 extends Level{
	
	PShape obstacles;
	PVector bg_color, color;
	int state;

	public Level2(Main parent){
		super(parent);
		obstacles = parent.createShape(parent.GROUP);
		bg_color = new PVector(0, 0, 0);
		color = new PVector(0, 255, 0);
		state = 0;
		
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
	
	public void drawScenery(){
		parent.noStroke();
		parent.shape(obstacles);
	}
	
	public PShape getObstacles() {
		return obstacles;
	}
	
	@Override
	public void draw() {

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
	            if(interpreter.executeInput(parent.getAgent())){
	                if(parent.getAgent().isRadarActive() && state == 1)
	                    state++;
	                else if(!parent.getAgent().isRadarActive() && state == 2){
	                    color.set(255, 0, 0);
	                    state++;
	                }
	                else if(!parent.getAgent().isMoving() && state == 3){
	                       color.set(0, 255, 0);
	                    state++;
	                }
	                else if(parent.getAgent().isRadarActive() && parent.getAgent().isMoving() && state == 4){
	                    color.set(0, 125, 255);
	                    state++;
	                    parent.getAgent().setLooping(false);
	                }
	            }
	            interpreter.clear();
	        }
		
		switch(state){
		case 0:
			/*agent comes from the left*/
		    parent.getAgent().setPos(-50, parent.getAgent().getPos().y);
		    parent.getAgent().setLooping(true);
			state++;
		case 1:
			print_hint(color, "allumer.radar");
			break;
		case 2:
			print_hint(color, "eteindre.radar");
			break;
		case 3:
			print_hint(color, "arreter");
			break;
		case 4:
			print_hint(color, "allumer.radar et accelerer");
			break;
		case 5:
			// You can type "continuer" or wait
			if(interpreter.isLevelFinished() || parent.getAgent().isOutOfBounds()){
			    interpreter.resetLevelFinished();
				this.setFinished(true);
			}
			break;
		}
		//parent.textSize(32);
		//parent.text("to do...",parent.width/2,parent.height/2);
		print_request();
		parent.getAgent().draw();
		drawScenery();
	}

	@Override
	public ILevel getNextLevel() {
		// TODO Auto-generated method stub
		return new Level3(this.parent);
	}
}