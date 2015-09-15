package levels;
import parser.Parser;
import core.Agent;
import processing.core.PApplet;

public abstract class Level implements ILevel{
	PApplet parent;
	Agent agent;
	String request;
	boolean finished;
	Parser parser;
	
	public Level(PApplet parent, Agent agent){
		this.finished = false;
		this.parent = parent;
		this.agent = agent;
		this.parser = new Parser();
		this.request = "";
	}
	
	@Override
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	@Override
	public void handleInput(int pressed_key) {
		parser.handleInput(pressed_key);
	}

	//print what user tip
	public void print_request(){
		parent.textSize(32);
		parent.fill(125);  
		parent.text("> " + request, 10, (float) (parent.height*0.85));
	}
	
	public void print_arbitrary_message(String msg){
		parent.textSize(32);
		parent.fill(255);
		parent.text(msg, 10, (float) (parent.height*0.75));
	}
	public void print_hint(int[] color, String direction){
		parent.textSize(32);
		String hint = agent.getName() + " tu peux ";
		parent.fill(255);
		parent.text(hint, 10, (float) (parent.height*0.75));
		parent.fill(color[0], color[1], color[2]);
		parent.text(direction, 10+parent.textWidth(hint), (float) (parent.height*0.75));
	}
}
