package Core.Audio;

import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.openal.AL10.*;

public class SoundPlayer {
    private int bufferId;
    private int sourceId;

    public void load(SoundLoader.SoundData data) {
        bufferId = alGenBuffers();

        int format = data.channels == 1 ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16;
        alBufferData(bufferId, format, data.pcm, data.sampleRate);
        MemoryUtil.memFree(data.pcm);

        sourceId = alGenSources();
        alSourcei(sourceId, AL_BUFFER, bufferId);
        alSourcef(sourceId, AL_GAIN, 1.0f);
        alSourcef(sourceId, AL_PITCH, 1.0f);
        alSource3f(sourceId, AL_POSITION, 0f, 0f, 0f);
    }

    public void play() {
        alSourcePlay(sourceId);
    }

    public void cleanup() {
        alDeleteSources(sourceId);
        alDeleteBuffers(bufferId);
    }
}

