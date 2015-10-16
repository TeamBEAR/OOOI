package levels;
import parser.Interpreter;
import core.Context;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;
import core.Main;

public abstract class Level implements ILevel{
	Main parent;
	String request;
	boolean finished;
	Interpreter interpreter;
	PShape obstacles;

	PVector bg_color, color;

	public Level(Main parent){

		bg_color = new PVector(0, 0, 0);
		color = new PVector(0, 255, 0);

		this.finished = false;
		this.parent = parent;
		if(parent.getContext().hasVar("Interpreter"))
			this.interpreter = (Interpreter) parent.getContext().getVar("Interpreter");
		else{
			this.interpreter = new Interpreter(parent.getContext());
			parent.getContext().registerVar("Interpreter", this.interpreter);
		}
		this.request = "";

		obstacles = parent.createShape(parent.GROUP);
	}

	/**
	 * Draw radar effects if radar is activ
	 */
	protected void draw_background(){
		if(parent.getAgent().isRadarActive()){
			if(parent.getAgent().radarReadsRectangle(obstacles)){
				this.bg_color=new PVector(255, 0, 0);
			}
		}


		parent.background(
				bg_color.x,
				bg_color.y,
				bg_color.z);

		if (bg_color.mag()>0)
			bg_color.sub(17, 0, 0);
		else
			bg_color.set(0, 0, 0);


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
		interpreter.handleInput(pressed_key);
	}

	public void handleCodedInput(int pressed_key){
		interpreter.handleCodedInput(pressed_key);
	}

	//print what user tip
	public void print_request(){
		parent.textSize(32);
		parent.fill(125);  
		parent.text("> " + interpreter.getRequest(), 10, (float) (parent.height*0.85));
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
		String hint = parent.getAgent().getName() + " tu peux ";
		parent.fill(255);
		parent.text(hint, 10, (float) (parent.height*0.75));
		parent.fill(color[0], color[1], color[2]);
		parent.text(direction, 10+parent.textWidth(hint), (float) (parent.height*0.75));
	}
}
