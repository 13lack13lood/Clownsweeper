package audio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoundEffects {

	private static List<Source> sources = new ArrayList<Source>();
	private static List<Source> remove = new ArrayList<Source>();
	
	public static void init() {
		AudioMaster.init();
		AudioMaster.setListenerData();
	}
	
	public static void flag() {
		int buffer = AudioMaster.loadSound("flag.wav");
		Source source = new Source();
		
		source.play(buffer);
		sources.add(source);
	}
	
	public static void unflag() {
		int buffer = AudioMaster.loadSound("unflag.wav");
		Source source = new Source();
		
		source.play(buffer);
		sources.add(source);
	}
	
	public static void mine() {
		Random random = new Random();
		int r = random.nextInt(3) + 1;
		
		int buffer = AudioMaster.loadSound("mine" + r + ".wav");
		Source source = new Source();
		
		source.play(buffer);
		sources.add(source);
	}
	
	public static void win() {
		int buffer = AudioMaster.loadSound("win.wav");
		Source source = new Source();
		
		source.play(buffer);
		sources.add(source);
	}
	
	public static void lose() {
		int buffer = AudioMaster.loadSound("lose.wav");
		Source source = new Source();
		
		source.play(buffer);
		sources.add(source);
	}
	
	public static void update() {		
		for(Source s : remove) {
			sources.remove(s);
		}
		
		remove = new ArrayList<Source>();
		
		for(Source s : sources) {
			if(!s.isPlaying()) {
				s.delete();
				remove.add(s);
			}		
		}
		
	}
	
	public static void cleanUp() {
		AudioMaster.cleanUp();
	}

}
