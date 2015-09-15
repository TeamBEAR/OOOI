
import core.Agent;
import levels.ILevel;
import levels.Level;
import levels.Level0;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import parser.Parser;

import processing.core.*;

public class Controler extends PApplet{
	ILevel level;
	Agent agent;
	Parser parser;

	public void settings(){
		size(displayWidth, displayHeight);
	}
	
	//processing initialization function
	public void setup() {
		background(0);
        agent = new Agent(this, width/2, height/2);
        
        //  TODO: Dynamic assignment of levels, maybe with a factory?
        level =  new Level0(this, agent);
        
	}
	


	public void draw() {
		if(level.isFinished()){
			background(255);			
		}else{
			level.draw();
		}
	}

	//read what user type and update string containing previous text
	//this function has some bugs
	public void keyPressed() {
		level.handleInput(key);
	}
	
	
	  
	public static void main(String[] args) {
		PApplet.main(new String[] { "--present", "Controler" });
	}

}
