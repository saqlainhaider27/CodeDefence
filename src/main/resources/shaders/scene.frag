#version 330 core

in vec2 passTexCoords;      // Texture coordinates from vertex shader

uniform sampler2D txtSampler; // Texture sampler

out vec4 fragColor; // Final color output

void main() {
    fragColor = texture(txtSampler, passTexCoords);
}