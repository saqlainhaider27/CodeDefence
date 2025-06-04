package Core.Audio;

import org.lwjgl.stb.STBVorbisInfo;
import org.lwjgl.system.*;
import java.nio.*;
import java.nio.file.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class SoundLoader {
    public static SoundData loadSound(String path){
        try{
            return loadOgg(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static SoundData loadOgg(String path) throws Exception {
        byte[] data = Files.readAllBytes(Paths.get(path));
        ByteBuffer vorbisData = MemoryUtil.memAlloc(data.length);
        vorbisData.put(data).flip();

        try (MemoryStack stack = stackPush()) {
            IntBuffer error = stack.mallocInt(1);
            long decoder = stb_vorbis_open_memory(vorbisData, error, null);
            if (decoder == 0L) {
                MemoryUtil.memFree(vorbisData);
                throw new RuntimeException("Failed to open OGG file: " + error.get(0));
            }

            STBVorbisInfo info = STBVorbisInfo.malloc(stack);
            stb_vorbis_get_info(decoder, info);

            int channels = info.channels();
            int sampleRate = info.sample_rate();
            int samples = stb_vorbis_stream_length_in_samples(decoder) * channels;

            ShortBuffer pcm = MemoryUtil.memAllocShort(samples);
            stb_vorbis_get_samples_short_interleaved(decoder, channels, pcm);

            stb_vorbis_close(decoder);
            MemoryUtil.memFree(vorbisData);

            return new SoundData(pcm, channels, sampleRate);
        }
    }


    public static class SoundData {
        public final ShortBuffer pcm;
        public final int channels;
        public final int sampleRate;

        public SoundData(ShortBuffer pcm, int channels, int sampleRate) {
            this.pcm = pcm;
            this.channels = channels;
            this.sampleRate = sampleRate;
        }
    }
}