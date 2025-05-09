package Core.Scene.Entity;

import org.joml.Vector4f;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.system.MemoryStack.stackPush;

public class Material {

    private int textureID;
    public static String DEFAULT_TEXTURE = "src/main/resources/models/default/default_texture.png";

    public static final Vector4f DEFAULT_COLOR = new Vector4f(1.0f, 0.0f, 0.0f, 1.0f);

    private float width, height;

    public Material(String path) {
        loadTexture(path);
    }

    public Material() {
        loadTexture(DEFAULT_TEXTURE);
    }

    private void loadTexture(String path) {
        try (var stack = stackPush()) {
            IntBuffer widthBuffer = stack.mallocInt(1);
            IntBuffer heightBuffer = stack.mallocInt(1);
            IntBuffer channelsBuffer = stack.mallocInt(1);

            ByteBuffer imageData = STBImage.stbi_load(path, widthBuffer, heightBuffer, channelsBuffer, 4);
            if (imageData == null) {
                System.err.println("Failed to load texture: " + path);
                return;
            }

            width = widthBuffer.get(0);
            height = heightBuffer.get(0);

            // Generate texture
            textureID = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureID);

            // Upload image data to OpenGL
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, (int) width, (int) height, 0, GL_RGBA, GL_UNSIGNED_BYTE, imageData);

            // Generate mipmaps
            glGenerateMipmap(GL_TEXTURE_2D);

            STBImage.stbi_image_free(imageData);
        }
    }

    public void cleanup() {
        glDeleteTextures(textureID);
    }

    public int getTextureID() {
        return textureID;
    }
}