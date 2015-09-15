package core;

import processing.core.*;
import processing.core.PVector;

public class Agent{
  PApplet parent;
  PVector pos;//cordinate of agent's center of mass
  PVector speed;//agent's speed
  String name;//agent's name
  boolean looping;
  
  //default constructor  
  public Agent(PApplet parent, float x,float y){
	  this.parent = parent;
	  this.name = "";
	  this.looping = false;
	  pos=new PVector(x,y);//initialize position
	  speed=new PVector(0,0);//no speed
  }
  
  public boolean isLooping() {
	return looping;
  }
  
  public void setLooping(boolean looping) {
	this.looping = looping;
  }
    
  //speed up
  public void speed_up(){
	  if(speed.mag()==0){
          speed.set(speed.x+10,speed.y);
	      return;
	  }
	  if(speed.x>.9)
	      speed.set(speed.x+10, speed.y);
	  else if(speed.x<-.9)
		  speed.set(speed.x-10, speed.y);
	  if(speed.y>.9)
		  speed.set(speed.x, speed.y+10);
	  else if(speed.y<-.9)
		  speed.set(speed.x, speed.y-10);
  }
  
  public void setName(String name) {
	this.name = name;
  }
  
  public String getName() {
	return name;
  }
  
  public void stop(){
	    speed.set(0.0f, 0.0f);
  }
  
  public void turnRight(){
	  if(speed.mag() > 0){
		speed.rotate((float)(.5f * Math.PI));
	  }
	  System.out.println(speed);
  }
  
  public void turnLeft(){
      if(speed.mag() > 0){
    	  speed.rotate((float)(-.5f * Math.PI));  
	  }
  }
  
  public void setPos(float x, float y){
	  pos.set(x, y);
  }
  
  public PVector getPos() {
	return pos;
  }
  
  //update agent position
  public void update(){
    pos.add(speed);
    if(looping && speed.mag()>0){
    	if(isOutOfBounds()){
    		if(speed.x > 0 && pos.x > parent.width){
    			setPos(-50, pos.y);	
    		}else if(speed.x < 0 && pos.x < 0){
    			setPos(parent.width-50, pos.y);
    		}
    		if (speed.y > 0 && pos.y > parent.height) {
    			setPos(pos.x, -50);
			}else if(speed.y < 0 && pos.y < 0 ){
				setPos(pos.x, parent.height-50);
			}
    	}
    }
  }
  
  public boolean isOutOfBounds(){
	  if(pos.x > parent.width || pos.x < 0 || pos.y > parent.height || pos.y < 0)
		  return true;
	  else
		  return false;
  }
  
  //draw agent
  //because I have no gift for drawing
  //agent is only a white disk
  public void draw(){
	  //Erase previous position
	parent.fill(0);
	parent.noStroke();
	parent.ellipse(pos.x, pos.y, 51, 51);
    update();
    parent.fill(255);
    parent.noStroke();
    parent.ellipse(pos.x, pos.y, 50, 50);
  }
}
