package levels;

import core.Agent;
import processing.core.PApplet;

public class Level{
	PApplet parent;
	Agent agent;
	boolean finished;
	int state;//state of the level
	
	public Level(PApplet parent, Agent agent){
		this.finished = false;
		this.parent = parent;
		this.agent = agent;
		this.state = 0; // Level always starts in state 0
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
