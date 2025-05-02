package Core.Graphics;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class TestMesh {
    private Vertex[] vertices;
    private int[] indices;

    private int vao;
    private int vbo;
    private int ibo;

    public TestMesh(Vertex[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
    }
    public void create() {

        // Vertices Data
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        FloatBuffer verticesBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] verticesArray = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            verticesArray[i * 3] = vertices[i].getPosition().x;
            verticesArray[i * 3 + 1] = vertices[i].getPosition().y;
            verticesArray[i * 3 + 2] = vertices[i].getPosition().z;
        }
        verticesBuffer.put(verticesArray).flip();
        vbo = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL30.GL_ARRAY_BUFFER, verticesBuffer, GL30.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0, 3, GL15.GL_FLOAT, false, 0, 0);

        // Indices Data
        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();

        ibo = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL15.glBufferData(GL30.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL30.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,0);



    }
}
