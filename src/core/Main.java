package core;
import core.Context;
import core.types.Agent;
import levels.ILevel;
import levels.Level0;

import processing.core.*;

public class Main extends PApplet{
	ILevel level;
	Agent agent;
	Context context;

	public void settings(){
		size(displayWidth, displayHeight);
	}
	
	public void setAgent(Agent agent) {
        this.agent = agent;
    }
	
	public Agent getAgent() {
        return agent;
    }
	
	//processing initialization function
	public void setup() {
		background(0);
		context = new Context();
		context.registerVar("Main", "Main", this);
		agent = null;
        
        //  TODO: Dynamic assignment of levels, maybe with a factory?
        level =  new Level0(this);
        
        context.registerVar("Level", "current", (Object) level);
        
	}
	


	public void draw() {
		if(level.isFinished()){
			level=level.getNextLevel();
		}else{
			level.draw();
		}
	}

	//read what user type and update string containing previous text
	//this function has some bugs
	public void keyPressed() {
		level.handleInput(key);
	}
	
	public Context getContext() {
        return context;
    }

	public static void main(String[] args) {
		PApplet.main(new String[] { "--present", "core.Main" });
	}

}
