package levels;
import parser.Parser;
import core.Agent;
import processing.core.PApplet;

public class Level implements ILevel{
	PApplet parent;
	Agent agent;
	String request;
	boolean finished;
	int state;//state of the level
	Parser parser;
	
	public Level(PApplet parent, Agent agent){
		this.finished = false;
		this.parent = parent;
		this.agent = agent;
		this.parser = new Parser();
		this.state = 0; // Level always starts in state 0
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

	@Override
	public int validateInput(String input){
		return state;
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}	
}
