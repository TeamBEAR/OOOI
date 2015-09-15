package levels;

import core.Agent;
import processing.core.PApplet;

public class EndLevel extends Level {

	public EndLevel(PApplet parent, Agent agent) {
		super(parent, agent);
	}

	public void draw(){
		parent.background(255);
	}
	@Override
	public ILevel getNextLevel() {
		// TODO Auto-generated method stub
		return null;
	}

}
