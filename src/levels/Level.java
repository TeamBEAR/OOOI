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
