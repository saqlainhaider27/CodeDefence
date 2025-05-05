package Core.Render;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;

public class Shader {
    private final int programId;

    public Shader(String vertexPath, String fragmentPath) throws IOException {
        // Read shader source code
        String vertexSource = new String(Files.readAllBytes(Paths.get(vertexPath)));
        String fragmentSource = new String(Files.readAllBytes(Paths.get(fragmentPath)));\

        // Compile shaders
        int vertexShaderId = compileShader(vertexSource, GL_VERTEX_SHADER);
        int fragmentShaderId = compileShader(fragmentSource, GL_FRAGMENT_SHADER);

        // Link program
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
        // Cleanup shaders (they are already linked)
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

