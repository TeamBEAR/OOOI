package levels;

import core.Context;
import core.Main;
import core.types.Agent;
import processing.core.PApplet;

/**
 * Level where user give a name to its character 
 * @author berengere
 * 
 *
 */
public class Level0 extends Level {

	public Level0(Main parent) {
		super(parent);
		askName();
		printName(interpreter.getRequest());
	}
	
	/**
	 * print text asking a name for the agent
	 */
	private void askName(){
		parent.background(0);
		parent.textSize(32);      
		parent.fill(255, 255, 255, 125);
		parent.text("Quel est ton nom, petit d'homme ?", 10, (float) (parent.height*0.75));
	}
	
	/**
	 * print agent name
	 * @param name current agent name
	 */
	private void printName(String name){
		parent.textSize(32);      
		parent.fill(125);  
		parent.text("> "+name, 10, (float) (parent.height*0.85));
	}
	
	public void draw(){
		if(interpreter.isEnterTouch()){
			//agent.setName(interpreter.getRequest());
		    interpreter.parseInput();
			if(parent.getAgent() != null)
			    finished=true;
		}else {			
			askName();
			printName(interpreter.getRequest());			
		}		
	}

	@Override
	public ILevel getNextLevel() {
		// TODO Auto-generated method stub
		return new Level1(this.parent);
	}
}
