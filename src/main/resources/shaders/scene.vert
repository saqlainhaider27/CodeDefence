#version 330 core

layout(location = 0) in vec3 position;
layout(location = 1) in vec2 texCoords;

uniform mat4 projectionMatrix; // Projection matrix
uniform mat4 modelMatrix;      // Model matrix

out vec2 passTexCoords; // Pass texture coordinates to fragment shader

void main() {
    gl_Position = projectionMatrix * modelMatrix * vec4(position, 1.0);
    passTexCoords = texCoords;
}