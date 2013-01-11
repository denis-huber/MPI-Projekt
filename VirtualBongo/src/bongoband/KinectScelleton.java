package bongoband;

import SimpleOpenNI.SimpleOpenNI;
import SimpleOpenNI.XnVFlowRouter;
import SimpleOpenNI.XnVSessionManager;
import SimpleOpenNI.XnVSteadyDetector;
import SimpleOpenNI.XnVSwipeDetector;
import processing.core.*;


public class KinectScelleton {

	SimpleOpenNI soni;
	PApplet p;

	public KinectScelleton(PApplet p)
	{
		soni = new SimpleOpenNI(p);
		// enable depthMap generation 
		if(soni.enableDepth() == false)
		{
			PApplet.println("Can't open the depthMap, maybe the camera is not connected!"); 
			p.exit();
			return;
		}
		// enable User
		soni.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL); 

		// Disable mirroring
		soni.setMirror(true);

		// enable the hands + gesture
		soni.enableGesture();
		soni.enableHands();
	}

	public void drawSkeleton(int userId)
	{
		soni.drawLimb(userId, SimpleOpenNI.SKEL_HEAD, SimpleOpenNI.SKEL_NECK);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_NECK, SimpleOpenNI.SKEL_LEFT_SHOULDER);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER, SimpleOpenNI.SKEL_LEFT_ELBOW);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_ELBOW, SimpleOpenNI.SKEL_LEFT_HAND);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_NECK, SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER, SimpleOpenNI.SKEL_RIGHT_ELBOW);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_ELBOW, SimpleOpenNI.SKEL_RIGHT_HAND);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER, SimpleOpenNI.SKEL_TORSO);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER, SimpleOpenNI.SKEL_TORSO);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_TORSO, SimpleOpenNI.SKEL_LEFT_HIP);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_HIP, SimpleOpenNI.SKEL_LEFT_KNEE);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_KNEE, SimpleOpenNI.SKEL_LEFT_FOOT);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_TORSO, SimpleOpenNI.SKEL_RIGHT_HIP);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_HIP, SimpleOpenNI.SKEL_RIGHT_KNEE);
		soni.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_KNEE, SimpleOpenNI.SKEL_RIGHT_FOOT);  

	}
	
	public void update()
	{
		soni.update();
	}


	public PVector getVectorForLeftHand(int userId)
	{
		PVector jointPos = new PVector();
		PVector jointPos_Proj = new PVector();

		soni.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_HAND,jointPos);
		soni.convertRealWorldToProjective(jointPos, jointPos_Proj);

		return jointPos_Proj;
	}

	public PVector getVectorForRightHand(int userId)
	{
		PVector jointPos = new PVector();
		PVector jointPosProj = new PVector();

		soni.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_HAND,jointPos);
		soni.convertRealWorldToProjective(jointPos, jointPosProj);
		return jointPosProj;
	}	
}
