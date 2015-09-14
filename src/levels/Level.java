package levels;

import core.Agent;
import processing.core.PApplet;

public class Level implements ILevel{
	PApplet parent;
	Agent agent;
	String request;
	boolean finished;
	int state;//state of the level
	
	public Level(PApplet parent, Agent agent){
		this.finished = false;
		this.parent = parent;
		this.agent = agent;
		this.state = 0; // Level always starts in state 0
		this.request = "";
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	@Override
	public void handleInput(int pressed_key) {
		if (pressed_key==127 || pressed_key==8) {
			//delete letter
			try {
				request=request.substring(0, request.length()-1);
			} catch (Exception e) {
				request="";
			}
			parent.background(0);
		} else if (pressed_key==10) {
			//validate request
			state = validateInput(request);
		} else  if (pressed_key >= 32) {
			//add letter
			request += (char) pressed_key ;
		}		
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
