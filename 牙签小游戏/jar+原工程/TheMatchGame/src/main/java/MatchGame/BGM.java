package main.java.MatchGame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class BGM {
	File f = new File("src/images/MyBGM.wav");
	URI uri;
	URL url;
	public BGM() {
		try {
			uri = f.toURI();
			url = uri.toURL(); //Ω‚Œˆµÿ÷∑
			AudioClip aau;
			aau = Applet.newAudioClip(url);
			aau.loop();
			System.out.println(aau.hashCode());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
