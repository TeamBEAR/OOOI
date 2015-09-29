package core;

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


	private boolean touch_obstacles(PShape obstacles){
		float[] obstacleParameters;
		PVector[] corners = new PVector[4];
		for (int i=0;i<4;i++)
			corners[i] = new PVector(0, 0);

		float[] obstacle_limits = new float[4];
		float[] farthest_lines = new float[4];

		float x, y;
		float dist;

		for(int i=0; i < obstacles.getChildCount(); i++){
			obstacleParameters = obstacles.getChild(i).getParams();
			//left
			obstacle_limits[0] = obstacleParameters[0] - .5f*obstacleParameters[2];
			//right
			obstacle_limits[1] = obstacleParameters[0] + .5f*obstacleParameters[2];
			//upper
			obstacle_limits[2] = obstacleParameters[1] - .5f*obstacleParameters[3];
			//lower
			obstacle_limits[3] = obstacleParameters[1] + .5f*obstacleParameters[3];

			// Test Center
			if(pos.x >= obstacle_limits[0] && pos.x <= obstacle_limits[1])
				if(pos.y >= obstacle_limits[2] && pos.y <= obstacle_limits[3])
					return true;


			// The center is not colliding, test corners
			for(int j=0; j<2; j++){
				x = obstacle_limits[j];
				for(int k=2; k<4; k++){
					y = obstacle_limits[k];
					dist= (float)Math.sqrt(
							Math.pow((x-pos.x), 2) + 
							Math.pow((y-pos.y), 2)
							);
					if(dist <= radarState.x/2.0f)
						return true;
				}

			}

			// The corners are not colliding, test limits
			//left of Agent
			farthest_lines[0] = pos.x - .5f*radarState.x;
			//right of Agent
			farthest_lines[1] = pos.x + .5f*radarState.x;
			//up of Agent
			farthest_lines[2] = pos.y - .5f*radarState.y;
			//down of Agent
			farthest_lines[3] = pos.y + .5f*radarState.y;
			for(int j =0; j<4; j++)
			{
				if (farthest_lines[j] >= obstacle_limits[0] && farthest_lines[j] <= obstacle_limits[1])
					if(farthest_lines[j] >= obstacle_limits[2] && farthest_lines[j] <= obstacle_limits[3])
						return true;
			}
		}

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