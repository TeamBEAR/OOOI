package levels;

import core.Agent;
import processing.core.PApplet;

public class EndLevel extends Level {

	public EndLevel(PApplet parent, Agent agent) {
		super(parent, agent);
	}

	public void draw(){
		parent.background(0);
	    parent.textSize(32);
	    parent.text("to do...",parent.width/2,parent.height/2);
	}
	@Override
	public ILevel getNextLevel() {
		// TODO Auto-generated method stub
		return null;
	}

}
