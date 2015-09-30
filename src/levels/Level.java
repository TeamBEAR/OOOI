package levels;
import parser.Interpreter;
import core.Agent;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public abstract class Level implements ILevel{
	PApplet parent;
	Agent agent;
	String request;
	boolean finished;
	Interpreter parser;
	PShape obstacles;
	
	public Level(PApplet parent, Agent agent){
		this.finished = false;
		this.parent = parent;
		this.agent = agent;
		this.parser = new Interpreter();
		this.request = "";

		obstacles = parent.createShape(parent.GROUP);
	}
	
	protected void drawScenery(){
		parent.noStroke();
		parent.shape(obstacles);
	}

	protected PShape getObstacles() {
		return obstacles;
	}
	@Override
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public void handleInput(int pressed_key) {
		parser.handleInput(pressed_key);
	}

	//print what user tip
	public void print_request(){
		parent.textSize(32);
		parent.fill(125);  
		parent.text("> " + parser.getRequest(), 10, (float) (parent.height*0.85));
	}
	
	public void print_arbitrary_message(String msg){
		parent.textSize(32);
		parent.fill(255);
		parent.text(msg, 10, (float) (parent.height*0.75));
	}
	
	public void print_hint(PVector color, String direction){
	    int[] int_color = {(int) color.x, (int) color.y, (int) color.z};
	    print_hint(int_color, direction);
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
