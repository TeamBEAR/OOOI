package levels;

import core.Context;
import core.Main;
import core.types.Agent;
import processing.core.PApplet;

public class EndLevel extends Level {

	public EndLevel(Main parent) {
		super(parent);
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
