#version 330 core

uniform vec4 baseColor;
uniform sampler2D txtSampler;
uniform bool useTexture;

in vec2 fragTexCoord;
out vec4 fragColor;

void main() {
    if (useTexture) {
        fragColor = texture(txtSampler, fragTexCoord);
    } else {
        fragColor = baseColor;
    }
}
