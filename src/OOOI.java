import processing.core.*;
import processing.data.StringList;

public class OOOI extends PApplet{

	String request;//to store action tipped by user
	String name;//user name
	int  state;//state of the game
	StringList actions;//action enabeled
	Agent agent;//gamer's character

	public void settings(){
		size(displayWidth, displayHeight);
	}
	//processing initialization function
	public void setup() {
		background(0);
		request="";
		actions=new StringList();
		agent=new Agent(this, width/2, height/2);
		state=0;
	}

	//print what user tip
	public void print_request(){
		textSize(32);      
		fill(125);  
		text("> "+request, 10, (float) (height*0.85));
	}

	//draw game scenery
	public void draw_scenery(){
		fill(255);
		noStroke();
		rect( (float) (width*0.75), (float) 0, (float) 50, (float) (height*0.5-50));
		rect( (float) (width*0.75), (float) (height*0.5+50), (float) 50, (float) (height*0.5));
	}
	//ask the user a name for the agent
	public void get_user_name(){  
		textSize(32);      
		fill(255, 255, 255, 125);
		text("Quel est ton nom, petit d'homme ?", 10, (float) (height*0.75));
		print_request();
	}

	public void draw() {
		switch(state) {
		case 0:
			//first state of the game : just ask the user a name for the agent
			//to engage dialog
			get_user_name();
			break;
		case 1:
			//save user name and go to the next game state
			background(0);
			name=request;
			request="";
			actions.append("accelerer");
			state=2;
			break;
		case 2:     
			//second game state : the first level 
			//agent must speed up to quit the left area of the game scenery 
			//and pass the door
			background(0); 
			textSize(32);      
			fill(255, 255, 255);
			String tmp=name+" tu peux ";
			text(tmp, 10, (float) (height*0.75));      
			fill(255, 0, 0);
			String actionsToDisplay="";
			for(int i=0;i<actions.size();++i){
				actionsToDisplay+=actions.get(i);
				actionsToDisplay+= " ";

			}
			text(actionsToDisplay, 10+textWidth(tmp), (float) (height*0.75));
			print_request();
			draw_scenery();
			agent.draw();
			break;
		case 3:
			if (request.equals(actions.get(0))){
				agent.speed_up();
			}
			background(0);
			request="";
			state=2;
			break;
		case 4:
			background(0);
			textSize(32);
			text("to do...",width/2,height/2);
		}
	}

	//read what user type and update string containing previous text
	//this function has some bugs
	public void keyPressed() {
		int keyIndex = -1;
		if (key==127 || key==8) {
			//delete letter
			try {
				request=request.substring(0, request.length()-1);
			} catch (Exception e) {
				request="";
			}
			background(0);
		} else if (key==10) {
			//valid request
			state++;
		} else  if (key >= 32) {
			//add letter
			request += key ;
		}
	}
	  
	public static void main(String[] args) {
		PApplet.main(new String[] { "--present", "OOOI" });
	}
}
