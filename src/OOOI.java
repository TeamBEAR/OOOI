import processing.core.*;

import core.Agent;
import levels.*;


public class OOOI extends PApplet{

	ILevel[] levels;
	Agent agent;
	int currentLevel;

	public void settings(){
		size(displayWidth, displayHeight);
	}
	
	//processing initialization function
	public void setup() {
		background(0);
        agent = new Agent(this, width/2, height/2);
        currentLevel = 0;
        
        //  TODO: Dynamic assignment of levels, maybe with a factory?
        levels = new ILevel[2];
        levels[0] = new Level1(this, agent);
        levels[1] = new Level2(this, agent);
	}

	public void draw() {
		levels[currentLevel].draw();
	}

	//read what user type and update string containing previous text
	//this function has some bugs
	public void keyPressed() {
		levels[currentLevel].handleInput(key);
	}
	  
	public static void main(String[] args) {
		PApplet.main(new String[] { "--present", "OOOI" });
	}
}
