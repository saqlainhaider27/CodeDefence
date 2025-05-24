package Core.Scene.Entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {
    private int vao, pbo, ibo, tbo, cbo;

    private float[] vertices;
    private int[] indices;
    private float[] textureCoords;
    private float[] colors;

    public Mesh(float[] positions, float[] textureCoords, int[] indices, float[] colors) {
        this.vertices = positions;
        this.indices = indices;
        this.textureCoords = textureCoords;
        this.colors = colors;
    }

    public Mesh(float[] positions, float[] textureCoords, int[] indices) {
        this(positions, textureCoords, indices, null);
    }

    public void create() {
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length);
        positionBuffer.put(vertices).flip();
        pbo = storeData(positionBuffer, 0, 3);

        FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(textureCoords.length);
        textureBuffer.put(textureCoords).flip();
        tbo = storeData(textureBuffer, 1, 2);

        if (colors != null) {
            FloatBuffer colorBuffer = MemoryUtil.memAllocFloat(colors.length);
            colorBuffer.put(colors).flip();
            cbo = storeData(colorBuffer, 2, 3);
        }

        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();

        ibo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    private int storeData(FloatBuffer buffer, int index, int size) {
        int bufferID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        return bufferID;
    }

    public void cleanup() {
        GL15.glDeleteBuffers(pbo);
        GL15.glDeleteBuffers(ibo);
        GL15.glDeleteBuffers(tbo);
        if (colors != null) {
            GL15.glDeleteBuffers(cbo);
        }
        GL30.glDeleteVertexArrays(vao);
    }

    public int[] getIndices() {
        return indices;
    }

    public int getVAO() {
        return vao;
    }

    public int getIBO() {
        return ibo;
    }
}

