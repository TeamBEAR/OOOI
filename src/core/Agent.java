package core;

import processing.core.*;
import processing.core.PVector;

public class Agent{
  PApplet parent;
  PVector pos;//cordinate of agent's center of mass
  PVector speed;//agent's speed
  //default constructor
  
  public Agent(PApplet parent, float x,float y){
	  this.parent=parent;
	  pos=new PVector(x,y);//initialize position
	  speed=new PVector(0,0);//no speed
  }
  
  //speed up
  public void speed_up(){
    speed.set(speed.x+10,speed.y);
   
  }
  //update agent position
  public void update(){
    pos.add(speed);
    if(pos.x>parent.width){
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
    update();
    parent.fill(255);
    parent.noStroke();
    parent.ellipse(pos.x, pos.y, 50, 50);
  }
}
