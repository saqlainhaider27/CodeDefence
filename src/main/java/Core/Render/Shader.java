package Core.Render;

import Utils.Generics.HashMap;
import org.joml.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class Shader {
    private final int programId;
    private final HashMap<String, Integer> uniforms;

    public Shader(String vertexPath, String fragmentPath) throws IOException {
        uniforms = new HashMap<>();

        String vertexSource = new String(Files.readAllBytes(Paths.get(vertexPath)));
        String fragmentSource = new String(Files.readAllBytes(Paths.get(fragmentPath)));

        int vertexShaderId = compileShader(vertexSource, GL_VERTEX_SHADER);
        int fragmentShaderId = compileShader(fragmentSource, GL_FRAGMENT_SHADER);

        programId = glCreateProgram();
        glAttachShader(programId, vertexShaderId);
        glAttachShader(programId, fragmentShaderId);

        glLinkProgram(programId);

        if (glGetProgrami(programId, GL_LINK_STATUS) == GL_FALSE) {
            throw new RuntimeException("Shader program linking failed: " + glGetProgramInfoLog(programId));
        }
        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == GL_FALSE) {
            throw new RuntimeException("Shader program validation failed: " + glGetProgramInfoLog(programId));
        }

        glDetachShader(programId, vertexShaderId);
        glDetachShader(programId, fragmentShaderId);
        glDeleteShader(vertexShaderId);
        glDeleteShader(fragmentShaderId);
    }

    private int compileShader(String source, int type) {
        int shaderId = glCreateShader(type);
        glShaderSource(shaderId, source);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new RuntimeException("Failed to compile shader: " + glGetShaderInfoLog(shaderId));
        }

        return shaderId;
    }
    public void createUniform(String uniformName) {
        int uniformLocation = glGetUniformLocation(programId, uniformName);
        if (uniformLocation < 0) {
            throw new RuntimeException("Could not find uniform: " + uniformName);
        }
        uniforms.put(uniformName, uniformLocation);
    }
    public void setUniform(String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, float value) {
        glUniform1f(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, float x, float y) {
        glUniform2f(uniforms.get(uniformName), x, y);
    }
    public void setUniform(String uniformName, Vector2f vector) {
        glUniform2f(uniforms.get(uniformName), vector.x, vector.y);
    }
    public void setUniform(String uniformName, float x, float y, float z) {
        glUniform3f(uniforms.get(uniformName), x, y, z);
    }
    public void setUniform(String uniformName, Vector3f vector) {
        glUniform3f(uniforms.get(uniformName), vector.x, vector.y, vector.z);
    }
    public void setUniform(String uniformName, float x, float y, float z, float w) {
        glUniform4f(uniforms.get(uniformName), x, y, z, w);
    }
    public void setUniform(String uniformName, Vector4f vector) {
        glUniform4f(uniforms.get(uniformName), vector.x, vector.y, vector.z, vector.w);
    }
    public void setUniform(String uniformName, boolean value) {
        glUniform1i(uniforms.get(uniformName), value ? 1 : 0);
    }

    public void setUniformMatrix(String uniformName, Matrix4f matrix) {
        java.nio.FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
        matrix.get(matrixBuffer);
        glUniformMatrix4fv(uniforms.get(uniformName), false, matrixBuffer);
    }
    public int getUniform(String uniformName) {
        return uniforms.getOrDefault(uniformName, -1);
    }

    public void bind() {
        glUseProgram(programId);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void cleanup() {
        glDeleteProgram(programId);
    }
}