package core;

import processing.core.PShape;
import processing.core.PVector;

public class Collider {
    
	public Collider(){
		
	}
	
	public boolean circleAndRect(PShape circle, PShape rectangles){
		
		PVector center;
		PVector radius;
		
		float x, y;
		float dist;
		
		float[] circleParameters;
		float[] obstacleParameters;
		
		float[] obstacle_limits = new float[4];
		float[] farthest_lines = new float[4];
		
		circleParameters = circle.getParams();
		center = new PVector(circleParameters[0], circleParameters[1]);
		radius = new PVector(circleParameters[2], circleParameters[3]);

		for(int i=0; i < rectangles.getChildCount(); i++){
			   obstacleParameters = rectangles.getChild(i).getParams();
			   //left
			   obstacle_limits[0] = obstacleParameters[0] - .5f*obstacleParameters[2];
			   //right
			   obstacle_limits[1] = obstacleParameters[0] + .5f*obstacleParameters[2];
			   //upper
			   obstacle_limits[2] = obstacleParameters[1] - .5f*obstacleParameters[3];
			   //lower
			   obstacle_limits[3] = obstacleParameters[1] + .5f*obstacleParameters[3];
			   
			   // Test Center
			   if(center.x >= obstacle_limits[0] && center.x <= obstacle_limits[1])
				   if(center.y >= obstacle_limits[2] && center.y <= obstacle_limits[3])
					   return true;

			   
			   // The center is not colliding, test corners
			   for(int j=0; j<2; j++){
				   x = obstacle_limits[j];
				   for(int k=2; k<4; k++){
				       y = obstacle_limits[k];
				       dist= (float)Math.sqrt(
				    		   Math.pow((x-center.x), 2) + 
				    		   Math.pow((y-center.y), 2)
				    		   );
				       if(dist <= radius.x)
				    	   return true;
				   }
				   
			   }

			   // The corners are not colliding, test limits
			   //left of Agent
			   farthest_lines[0] = center.x - radius.x;
			   //right of Agent
			   farthest_lines[1] = center.x + radius.x;
			   //up of Agent
			   farthest_lines[2] = center.y - radius.y;
			   //down of Agent
			   farthest_lines[3] = center.y + radius.y;
			   
			   for(int j =0; j<4; j++)
			   {
				   if (farthest_lines[j] >= obstacle_limits[0] && farthest_lines[j] <= obstacle_limits[1])
					   if(farthest_lines[j] >= obstacle_limits[2] && farthest_lines[j] <= obstacle_limits[3])
						   return true;
			   }
		  }
		return false;
	}

}
