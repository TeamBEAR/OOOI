package parser;

import java.util.ArrayList;

/*

<S> ::= <STATEMENT> ;
<STATEMENT> ::= <ASSIGNMENT> | <CALL> | <RESERVED>

<ASSIGNMENT> ::= <A_ID> |
                 <NEW> <TYPE>: <A_ID>

<PARAM_LIST> = <A_ID> | 
               <A_ID>, <PARAM_LIST>

<FUNCTION> ::= <F_ID> |
               <F_ID> <PARAM_LIST>
                                    
<CALL> ::= <A_ID>, <FUNCTION> |
           <A_ID>, <FUNCTION_LIST> |
           <FUNCTION_LIST>

<FUNCTION_LIST> ::= <FUNCTION> |
                    <FUNCTION> <CONJUCTION> <FUNCTION_LIST>

<RESERVED> ::= continuer | fermer | continue | exit

<TYPE> ::= Agent
<NEW> ::= new | nouveau
<CONJUNCTION> ::= and | et

*/
import core.Context;
import core.types.Agent;
import processing.core.PConstants;

public class Interpreter {
	
	private String request;
	private boolean deleteTouch;
	private boolean enterTouch;
	private boolean levelFinished;
	private BearParser parser;
	private ArrayList<String> cl_buffer;
	private int cl_counter;
	
	public Interpreter(Context context){
		deleteTouch=false;
		request=new String();
		enterTouch=false;
		levelFinished=false;
		parser = new BearParser(context);
		cl_buffer = new ArrayList<String>();
		cl_counter = 0;
	}
	
	public void clear(){
		request="";
		enterTouch=false;
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
				cl_counter=-1;
			}
			deleteTouch=true;
		} else if (pressed_key==10) {
			//validate request
		    if(!request.equals("")){
			    cl_buffer.add(request);
			    cl_counter=-1;
			    }
	         enterTouch=true;
		} else  if (pressed_key >= 32 && pressed_key < 168) {
			//add letter
			request += (char) pressed_key ;
			deleteTouch=false;
			enterTouch=false;
		}
	}
	
	public void handleCodedInput(int pressed_key){
	    if(cl_buffer.size()!=0){
    	    if(pressed_key == PConstants.DOWN){
    	        if(cl_counter > 0 && cl_counter < cl_buffer.size()){
                    cl_counter++;
    	            request = cl_buffer.get(cl_counter-1);
    	        }
    	    }else if (pressed_key == PConstants.UP) {
    	        if(cl_counter < 0)
    	            cl_counter=cl_buffer.size();
    	        else if(cl_counter-1 > 0)
    	            cl_counter--;
    	        request = cl_buffer.get(cl_counter-1);
            }
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
	
	public void parseInput(){
	       try{
	            // Artificially add SEMICOLON token
	            parser.parse(new BearScanner(new java.io.StringReader(request+";")));
	        }catch(Exception e){
	            
	        }
	       parser.execution.run();
	       clear();
	}
	
	public boolean executeInput(Agent agent) {
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
