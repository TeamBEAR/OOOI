package parser;

import core.Agent;

public class Parser {
	private String request;
	private boolean deleteTouch;
	private boolean enterTouch;
	private boolean levelFinished;
	
	public Parser(){
		deleteTouch=false;
		request=new String();
		enterTouch=false;
		levelFinished=false;
	}
	
	public void clear(){
		request="";
	}
	public void handleInput(int pressed_key) {
		if(enterTouch){
			request="";
		}
		if (pressed_key==127 || pressed_key==8) {
			//delete letter
			try {
				request=request.substring(0, request.length()-1);
			} catch (Exception e) {
				request="";
			}
			deleteTouch=true;
		} else if (pressed_key==10) {
			//validate request
			enterTouch=true;
		} else  if (pressed_key >= 32) {
			//add letter
			request += (char) pressed_key ;
			deleteTouch=false;
			enterTouch=false;
		}		
	}
	
	public String getRequest() {
		return request;
	}
	public boolean isDeleteTouch() {
		return deleteTouch;
	}
	public boolean isEnterTouch() {
		return enterTouch;
	}
	
	public boolean executeInput(Agent agent) {
        /*Here we must use a true parser*/
	    if(request.equals("virer.droite")){
	        agent.turnRight();
	        return true;
	    }else if(request.equals("virer.gauche")){
	        agent.turnLeft();
	        return true;
	    }else if(request.equals("arreter")){
	        agent.stop();
	        return true;
	    }else if(request.equals("accelerer")){
	        agent.speed_up();
	        return true;
	    }else if(request.equals("allumer.radar")){
	        agent.setRadarActive(true);
	        return true;
	    }else if(request.equals("eteindre.radar")){
	        agent.setRadarActive(false);
	        return true;
	    }else if(request.equals("allumer.radar et accelerer")){
	        agent.speed_up();
	        agent.setLooping(true);
	        agent.setRadarActive(true);
	        return true;
	    }else if(request.equals("continuer")){
	        levelFinished=true;
	        return true;
	    }
	    return false;
	}

	public void resetLevelFinished() {
	    this.levelFinished = false;
	}

	public boolean isLevelFinished() {
	    return levelFinished;
	}
}
