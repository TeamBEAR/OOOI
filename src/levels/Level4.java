package levels;

import core.Agent;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Level4 extends Level implements ILevel{
	
	public Level4(PApplet parent, Agent agent){
		super(parent, agent);
	}
	
	@Override
	public void draw() {
		parent.background(0);
		parent.textSize(32);
		parent.text("to do...",parent.width/2,parent.height/2);
	}

	@Override
	public void handleInput(int pressed_key) {
		// TODO Auto-generated method stub
		
	}
}