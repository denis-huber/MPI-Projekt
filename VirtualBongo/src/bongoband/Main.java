package bongoband;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import SimpleOpenNI.*;

public class Main extends PApplet{
	
	private ArrayList<Integer> userList; //  Stores the IDs of the users
	KinectScelleton scel;
	int sizeX, sizeY; // Dimensions of the game field
	int usersInGame = 0;
	Sound sound;
	

public void setup() {
	background(0);
	stroke(0);
	scel = new KinectScelleton(this); 
	sizeX = scel.soni.depthWidth();
	sizeY = scel.soni.depthHeight();
	size(sizeX, sizeY);
	frameRate(50);
	smooth();
	userList = new ArrayList<Integer>();
	sound = new Sound(this);
}	


public void draw() {
	
			background(255);
			scel.update();
			image(scel.soni.depthImage(),0,0);
			//sound.play();
			

			switch (usersInGame) {

			case 1:
				scel.drawSkeleton(userList.get(0));
				//				rightPad = scel.getVectorForRightHand(userList.get(0));
				//				leftPad = scel.getVectorForLeftHand(userList.get(0));
				break;
			case 2:
				scel.drawSkeleton(userList.get(0));
				scel.drawSkeleton(userList.get(1));

//				rightPad = scel.getVectorForRightHand(userList.get(0));
//				leftPad = scel.getVectorForLeftHand(userList.get(1));

				break;

			default:
				break;
			}
			//pointDrawer.draw();

		}

		public void onNewUser(int userId){
			if (userList.size() < 2) {
				println("Welcome New Player ");
				scel.soni.requestCalibrationSkeleton(userId, true);
			}

			//		else if(userList.size() == 1) {
			//			//userList.add(userId);
			//			println("Welcome New Player 2");
			//			//usersInGame = 2;
			//			scel.soni.requestCalibrationSkeleton(userId, true);
			//		}

			println("New User Detected - userId: " + userId);
		}

		public void onLostUser(int userId)
		{
			// not implemented
		}
		public void onStartCalibration(int userId){
			println("Beginning Calibration - userId: " + userId);
		}
		public void onEndCalibration(int userId, boolean successfull){
			println("Calibration of userId: " + userId + ", successfull: " + successfull);
			if (successfull) 
			{ 
				userList.add(userId);
				if(usersInGame == 0 && userList.size() == 1) {
					usersInGame = 1;
				}
				else if (usersInGame == 2 && userList.size() == 2) {
					//start_flag = true;
				}
				println("  User calibrated !!!");
				scel.soni.startTrackingSkeleton(userId); 
			} 
			else 
			{ 
				PApplet.println("  Failed to calibrate user !!!");
				scel.soni.requestCalibrationSkeleton(userId, true);
			}
			sound.stop(this);
		}

}
	

