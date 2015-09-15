package parser;

public class Parser {
	private String request;
	private boolean deleteTouch;
	private boolean enterTouch;
	
	public Parser(){
		deleteTouch=false;
		request=new String();
		enterTouch=false;
	}
	public void handleInput(int pressed_key) {
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
	
	
	
	

}
