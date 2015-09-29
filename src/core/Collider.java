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
	
	public boolean rectAndRectGroup(PShape rect, PShape obstacles){
		PVector pos, size;
		float[] obstacleParameters;
		
		float[] rectParameters;
		rectParameters = rect.getParams();
		pos = new PVector(rectParameters[0], rectParameters[1]);
		size = new PVector(rectParameters[2], rectParameters[3]);
		
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
			if(pos.x + size.x/.5f >= obstacle_limits[0] && pos.x + size.x/.5f <= obstacle_limits[1])
				if(pos.y + size.y/.5f>= obstacle_limits[2] && pos.y + size.y/.5f<= obstacle_limits[3])
					return true;


//			// The center is not colliding, test corners
//			for(int j=0; j<2; j++){
//				x = obstacle_limits[j];
//				for(int k=2; k<4; k++){
//					y = obstacle_limits[k];
//					dist= (float)Math.sqrt(
//							Math.pow((x-pos.x), 2) + 
//							Math.pow((y-pos.y), 2)
//							);
//					if(dist <= size.x/2.0f)
//						return true;
//				}
//
//			}

			// The corners are not colliding, test bounding box
			//left of rect
			farthest_lines[0] = pos.x;
			//right of rect
			farthest_lines[1] = pos.x + size.x;
			//up of rect
			farthest_lines[2] = pos.y;
			//down of rect
			farthest_lines[3] = pos.y + size.y;
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
