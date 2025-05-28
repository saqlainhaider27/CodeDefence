package Core;

import Core.Scene.Entity.Texture;
import Core.Scene.Entity.Mesh;
import Core.Scene.Entity.Model;
import Utils.Generics.List;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.*;

import java.nio.IntBuffer;

public class ModelLoader {

    public static Model loadModel(String path) {
        return loadModel(path, Texture.DEFAULT_TEXTURE);
    }

    public static Model loadModel(String modelPath, String texturePath) {
        AIScene aiScene = Assimp.aiImportFile(modelPath, Assimp.aiProcess_Triangulate | Assimp.aiProcess_GenSmoothNormals);
        if (aiScene == null) {
            throw new RuntimeException("Could not load model: " + modelPath);
        }

        int numMeshes = aiScene.mNumMeshes();
        PointerBuffer buffer = aiScene.mMeshes();

        List<Mesh> meshes = new List<>();

        for (int z = 0; z < numMeshes; z++) {
            AIMesh aiMesh = AIMesh.create(buffer.get(z));

            List<Float> vertices = processVertices(aiMesh);
            List<Float> texCoords = processTextCoords(aiMesh);
            List<Integer> indices = processIndices(aiMesh);
            List<Float> colors = processColors(aiMesh);

            Mesh mesh = new Mesh(vertices.toArray(new float[0]), texCoords.toArray(new float[0]), indices.toArray(new int[0]),colors.toArray(new float[0]));
            meshes.add(mesh);
        }
        return new Model(texturePath, meshes);
    }

    private static List<Float> processColors(AIMesh aiMesh) {
        List<Float> data = new List<>();
        AIColor4D.Buffer colorBuffer = aiMesh.mColors(0);
        if (colorBuffer != null) {
            while (colorBuffer.remaining() > 0) {
                AIColor4D color = colorBuffer.get();
                data.add(color.r());
                data.add(color.g());
                data.add(color.b());
                data.add(color.a());
            }
        }
        return data;
    }

    private static List<Float> processVertices(AIMesh aiMesh) {
        List<Float> data = new List<>();
        AIVector3D.Buffer buffer = aiMesh.mVertices();
        while (buffer.remaining() > 0) {
            AIVector3D vertex = buffer.get();
            data.add(vertex.x());
            data.add(vertex.y());
            data.add(vertex.z());
        }
        return data;
    }

    private static List<Float> processTextCoords(AIMesh aiMesh) {
        List<Float> data = new List<>();
        AIVector3D.Buffer buffer = aiMesh.mTextureCoords(0);
        if (buffer != null) {
            while (buffer.remaining() > 0) {
                AIVector3D texCoord = buffer.get();
                data.add(texCoord.x());
                data.add(1 - texCoord.y());
            }
        }
        return data;
    }
    private static List<Integer> processIndices(AIMesh aiMesh) {
        List<Integer> indices = new List<>();
        int numFaces = aiMesh.mNumFaces();
        AIFace.Buffer aiFaces = aiMesh.mFaces();
        for (int i = 0; i < numFaces; i++) {
            AIFace aiFace = aiFaces.get(i);
            IntBuffer buffer = aiFace.mIndices();
            while (buffer.remaining() > 0) {
                indices.add(buffer.get());
            }
        }
        return indices;
    }
}
