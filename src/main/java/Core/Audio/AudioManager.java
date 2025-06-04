package Core.Audio;

import org.lwjgl.openal.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.system.MemoryUtil.*;

public class AudioManager {
    private long device;
    private long context;

    public void init(){
        try {
            this.device = alcOpenDevice((ByteBuffer) null);
            if (device == NULL) {
                throw new IllegalStateException("Failed to open the default OpenAL device.");
            }
            ALCCapabilities deviceCaps = ALC.createCapabilities(device);
            this.context = alcCreateContext(device, (IntBuffer) null);
            if (context == NULL) {
                throw new IllegalStateException("Failed to create OpenAL context.");
            }
            alcMakeContextCurrent(context);
            AL.createCapabilities(deviceCaps);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanup() {
        alcDestroyContext(context);
        alcCloseDevice(device);
    }

}
