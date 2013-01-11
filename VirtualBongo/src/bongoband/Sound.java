package bongoband;

import processing.core.PApplet;
import ddf.minim.*;

public class Sound {
	
	AudioSample bongo;
	Minim minim;
	
	public Sound (PApplet applet) {
	minim = new Minim(applet);
	bongo = minim.loadSample("/VirtualBongo/SoundFiles/BO Bongo 006 HC.wav");
	boolean hit = false;
	}
	
	public void play () {
		bongo.trigger();
	}

	public void stop(PApplet applet)
	{
	  // always close Minim audio classes when you are done with them
	  bongo.close();
	  minim.stop();
	  applet.stop();
	}

}
