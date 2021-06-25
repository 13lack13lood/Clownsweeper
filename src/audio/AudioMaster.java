package audio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.system.MemoryUtil;

public class AudioMaster {
	private static long device;
	private static long context;
	private static List<Integer> buffers = new ArrayList<Integer>();
	
	public static void init() {
		
		device = ALC10.alcOpenDevice((ByteBuffer) null);
		
		if (device == MemoryUtil.NULL) {
            throw new IllegalStateException("Failed to open the default OpenAL device.");
        }
        
        ALCCapabilities deviceCaps = ALC.createCapabilities(device);
        
        context = ALC10.alcCreateContext(device, (IntBuffer) null);
        
        if (context == MemoryUtil.NULL) {
            throw new IllegalStateException("Failed to create OpenAL context.");
        }
        
        ALC10.alcMakeContextCurrent(context);
        AL.createCapabilities(deviceCaps);
	}
	
	public static void setListenerData() {
		AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}
	
	public static int loadSound(String file){
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);
		WaveData waveFile = WaveData.create(file);
		AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();
		
		return buffer;
	}
	
	public static void cleanUp() {
		for(Integer buffer : buffers) {
			AL10.alDeleteBuffers(buffer);
		}
		
		if(context != 0) {
			ALC10.alcDestroyContext(context);
		}
		
		if(device != 0) {
			ALC10.alcCloseDevice(device);
		}
	}
}
