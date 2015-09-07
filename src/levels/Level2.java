package levels;

import core.Agent;
import processing.core.PApplet;

public class Level2 extends Level{
	
	String[] directions;
	int[] command_color;
	int current_direction;
	
	public Level2(PApplet parent, Agent agent){
		super(parent, agent);
		this.request = "";
		this.current_direction = 0;
		
		this.command_color = new int[3];
		setCommand_color(0, 255, 0);
		
		this.directions = new String[4];
		this.directions[0] = "virer.droite";
		this.directions[1] = "virer.gauche";
		this.directions[2] = "arreter";
		this.directions[3] = "continuer";
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
	public void draw() {
		switch(state){
		case 0: // Preparation state
			/*agent comes from the left*/
			agent.setPos(-50, agent.getPos().y);
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
	public int validateInput(String input) {
		if(request.equals(directions[current_direction]))
			current_direction++;
			
		if(request.equals("virer.droite")){
			request = "";
			agent.turnRight();
		}else if(request.equals("virer.gauche")){
			request = "";
			agent.turnLeft();
		}else if(request.equals("arreter")){
			request = "";
			agent.stop();
		}else if(request.equals("accelerer")){
			request = "";
			agent.speed_up();
		}else if(request.equals("continuer")){
			request = "";
			this.setFinished(true);
		}
		return super.validateInput(input);
	}

}
