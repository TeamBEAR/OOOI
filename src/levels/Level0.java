package levels;

import core.Agent;
import processing.core.PApplet;

/**
 * Level where user give a name to its character 
 * @author berengere
 * 
 *
 */
public class Level0 extends Level {

	public Level0(PApplet parent, Agent agent) {
		super(parent, agent);
		askName();
		printName(parser.getRequest());
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
		if(parser.isEnterTouch()){
			agent.setName(parser.getRequest());
			finished=true;
		}else {			
			askName();
			printName(parser.getRequest());			
		}		
	}

	@Override
	public ILevel getNextLevel() {
		// TODO Auto-generated method stub
		return new Level1(this.parent,this.agent);
	}
}
