package levels;

import core.Agent;
import processing.core.PApplet;

public class Level{
	PApplet parent;
	Agent agent;
	int state;//state of the level
	
	public Level(PApplet parent, Agent agent){
		this.parent = parent;
		this.agent=agent;
		this.state=0; // Level always starts in state 0
	}
}
