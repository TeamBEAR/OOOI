package core.types;

import core.Collider;
import processing.core.*;
import processing.core.PVector;


public class Agent{
  PApplet parent;
  PVector pos;//cordinate of agent's center of mass
  PVector speed;//agent's speed
  String name;//agent's name
  boolean looping;
  
  PShape radar;
  boolean radarActive;
  
  Collider collider;
  float width;
  float height;
  
  // simple constructor
  public Agent(PApplet parent){
      this(parent, parent.width/2, parent.height/2);
  }
  
  //default constructor  
  public Agent(PApplet parent, float x,float y){
	  this.parent = parent;
	  this.name = "";
	  this.looping = false;
	  this.radarActive = false;
	  
	  parent.ellipseMode(parent.RADIUS);
	  this.radar = parent.createShape(parent.ELLIPSE, x, y, 25, 25);
	  
	  pos=new PVector(x,y);//initialize position
	  speed=new PVector(0,0);//no speed
	  
	  width=50;
	  height=50;
	  
	  collider = new Collider();
  }
  
  public boolean isLooping() {
	return looping;
  }
  
  public boolean isMoving(){
      return speed.mag()>=1;
  }
  
  public void setLooping(boolean looping) {
	this.looping = looping;
  }
  
  
  public void setRadarActive(boolean radarActive){
	  this.radarActive = radarActive;
  }
  
  public boolean isRadarActive() {
	return radarActive;
  }
  
  public boolean radarReadsRectangle(PShape obstacles){
	  
	  if(radarActive){
		return collider.circleAndRect(this.radar, obstacles);
	  }
	  return false;
  }

  private boolean touch_obstacles(PShape obstacles){
		return collider.rectAndRectGroup(
				parent.createShape(
						parent.RECT, pos.x-25, pos.y-25, 50, 50), 
				obstacles);
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
  
  public float get_x(){
	  return pos.x;
  }
  
  public float get_y(){
	  return pos.y;
  }
  
  public float get_width() {
	return width;
  }
  
  public float get_height() {
	return height;
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
    if (radarActive){
    	float[] radar_data = radar.getParams();
    	radar_data[0] = pos.x;
    	radar_data[1] = pos.y;
    	radar_data[2] += 2;
    	radar_data[3] += 2;
    	
    	if(radar_data[2] > 87.5  || radar_data[3] > 87.5){
    	    radar_data[2] = 25;
    	    radar_data[3] = 25;
    	}
    	parent.ellipseMode(parent.RADIUS);
    	radar = parent.createShape(parent.ELLIPSE, radar_data);
    }else{
    	parent.ellipseMode(parent.RADIUS);
    	radar = parent.createShape(parent.ELLIPSE, pos.x, pos.y, 25, 25);
    }
  }
  
  public boolean isOutOfBounds(){
	  if(pos.x > parent.width || pos.x < 0 || pos.y > parent.height || pos.y < 0)
		  return true;
	  else
		  return false;
  }


	public void inverse_x_speed(){
		speed.set(-1*speed.x,speed.y);
	}
	
	public void inverse_y_speed(){
		speed.set(speed.x,-1*speed.y);
	}

  //draw agent
  //because I have no gift for drawing
  //agent is only a white disk
  public void draw(){
	////Erase previous position
	//parent.fill(0);
	//parent.noStroke();
	//parent.ellipse(pos.x, pos.y, width+1, height+1);
	//update();
	//parent.fill(255);
	//parent.noStroke();
	//parent.ellipse(pos.x, pos.y,width,height);

    //Erase previous position
	parent.fill(0);
	parent.noStroke();
	parent.ellipseMode(parent.RADIUS);
	if(radarActive){
		parent.noFill();
		parent.stroke(255);
		parent.shape(radar);
	}else{
    	parent.stroke(0);
		parent.ellipse(pos.x, pos.y, 25, 25);
	}
    update();
    parent.fill(255);
    parent.noStroke();
    parent.ellipseMode(parent.RADIUS);
    parent.ellipse(pos.x, pos.y, 25, 25);
    
    if(radarActive){
    	parent.noFill();
    	parent.stroke(255);
    	parent.shape(radar);
    }

  }
}