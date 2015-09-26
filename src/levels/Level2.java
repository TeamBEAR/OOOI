package levels;

import core.Agent;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Level2 extends Level{

	PVector bg_color, color;
	int state;

	public Level2(PApplet parent, Agent agent){
		super(parent, agent);
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


	@Override
	public void draw() {

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
				if(agent.isRadarActive() && state == 1)
					state++;
				else if(!agent.isRadarActive() && state == 2){
					color.set(255, 0, 0);
					state++;
				}
				else if(!agent.isMoving() && state == 3){
					color.set(0, 255, 0);
					state++;
				}
				else if(agent.isRadarActive() && agent.isMoving() && state == 4){
					color.set(0, 125, 255);
					state++;
					agent.setLooping(false);
				}
			}
			parser.clear();
		}

		switch(state){
		case 0:
			/*agent comes from the left*/
			agent.setPos(-50, agent.getPos().y);
			agent.setLooping(true);
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
			if(parser.isLevelFinished() || agent.isOutOfBounds()){
				parser.resetLevelFinished();
				this.setFinished(true);
			}
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
		return new Level3(this.parent,this.agent);
	}
}