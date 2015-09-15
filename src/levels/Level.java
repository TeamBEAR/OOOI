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
	
	
	
}
