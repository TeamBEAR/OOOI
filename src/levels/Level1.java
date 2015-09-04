package levels;

import processing.core.PApplet;
import processing.data.StringList;
import core.Agent;

public class Level1 extends Level implements ILevel{
	
	String request;//to store action tipped by user
	StringList actions;//action enabeled
	String name;//user name
	
	public Level1(PApplet parent, Agent agent){
		super(parent, agent);
		this.request="";
		this.actions = new StringList();
	}

	//print what user tip
	public void print_request(){
		parent.textSize(32);      
		parent.fill(125);  
		parent.text("> "+request, 10, (float) (parent.height*0.85));
	}

	//draw game scenery
	public void draw_scenery(){
		parent.fill(255);
		parent.noStroke();
		parent.rect( (float) (parent.width*0.75), (float) 0, (float) 50, (float) (parent.height*0.5-50));
		parent.rect( (float) (parent.width*0.75), (float) (parent.height*0.5+50), (float) 50, (float) (parent.height*0.5));
	}

	//ask the user a name for the agent
	public void get_user_name(){  
		parent.textSize(32);      
		parent.fill(255, 255, 255, 125);
		parent.text("Quel est ton nom, petit d'homme ?", 10, (float) (parent.height*0.75));
		print_request();
	}
	
	@Override
	public void draw(){
		switch(state) {
		case 0:
			//first state of the game : just ask the user a name for the agent
			//to engage dialog
			get_user_name();
			break;
		case 1:
			//save user name and go to the next game state
			parent.background(0);
			name=request;
			request="";
			actions.append("accelerer");
			state=2;
			break;
		case 2:     
			//second game state : the first level 
			//agent must speed up to quit the left area of the game scenery 
			//and pass the door
			parent.background(0); 
			parent.textSize(32);      
			parent.fill(255, 255, 255);
			String tmp=name+" tu peux ";
			parent.text(tmp, 10, (float) (parent.height*0.75));      
			parent.fill(255, 0, 0);
			String actionsToDisplay="";
			for(int i=0;i<actions.size();++i){
				actionsToDisplay+=actions.get(i);
				actionsToDisplay+= " ";

			}
			parent.text(actionsToDisplay, 10+parent.textWidth(tmp), (float) (parent.height*0.75));
			print_request();
			draw_scenery();
			agent.draw();
			if(agent.isOutOfBounds()){
			    state=4;
			}
			break;
		case 3:
			if (request.equals(actions.get(0))){
				agent.speed_up();
			}
			parent.background(0);
			request="";
			state=2;
			break;
		case 4:
			
		}
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
			//valid request
			state++;
		} else  if (pressed_key >= 32) {
			//add letter
			request += (char) pressed_key ;
		}
	}
}
