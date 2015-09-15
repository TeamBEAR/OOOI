package levels;

import core.Agent;
import processing.core.PApplet;

public class Level3 extends Level{
	
	String[] directions;
	int[] command_color;
	int current_direction;
	int state;
	
	public Level3(PApplet parent, Agent agent){
		super(parent, agent);
		this.request = "";
		this.current_direction = 0;
		this.state = 0;
		
		this.command_color = new int[3];
		setCommand_color(0, 255, 0);
		
		this.directions = new String[4];
		this.directions[0] = "virer.droite";
		this.directions[1] = "virer.gauche";
		this.directions[2] = "arreter";
		this.directions[3] = "continuer";
	}
	
	@Override
	public void draw() {
	    
	    if(parser.isEnterTouch()){
	        parser.executeInput(agent);
	        if(parser.isLevelFinished()){
	            parser.resetLevelFinished();
	            this.setFinished(true);
	        }
	        
	        // Change message
	        if(parser.getRequest().equals(directions[current_direction]))
	            current_direction++;
	        
	        parser.clear();
	    }
	    
		switch(state){
		case 0: // Preparation state
			agent.setLooping(true);
			state = 1;
		case 1:
			if(current_direction == directions.length-2){
				setCommand_color(255, 0, 0);
			}else if(current_direction == directions.length-1){
				setCommand_color(0, 125, 255);
			}else if(current_direction == directions.length){
				current_direction--;
			}
			parent.background(0);
			print_hint(command_color, directions[current_direction]);
			print_request();
			agent.draw();
			break;
		}
	}
	
	public int checkColor(int color){
		if(color>255) color = 255;
		else if(color<0) color = 0;
		return color;
	}
	
	public void setCommand_color(int r, int g, int b) {
		r = checkColor(r);
		g = checkColor(g);
		b = checkColor(b);
		
		this.command_color[0] = r;
		this.command_color[1] = g;
		this.command_color[2] = b;
	}

	@Override
	public ILevel getNextLevel() {
		// TODO Auto-generated method stub
		return new EndLevel(this.parent,this.agent);
	}
}