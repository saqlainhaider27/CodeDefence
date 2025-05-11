package Core;

import Core.Scene.Entity.Material;
import Core.Scene.Entity.Mesh;
import Core.Scene.Entity.Model;
import Utils.Generics.List;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.*;

import java.nio.IntBuffer;

public class ModelLoader {

    public static Model loadModel(String path) {
        return loadModel(path, Material.DEFAULT_TEXTURE);
    }

    public static Model loadModel(String modelPath, String texturePath) {
        AIScene aiScene = Assimp.aiImportFile(modelPath, Assimp.aiProcess_Triangulate | Assimp.aiProcess_GenSmoothNormals);
        if (aiScene == null) {
            throw new RuntimeException("Could not load model: " + modelPath);
        }

        int numMeshes = aiScene.mNumMeshes();
        PointerBuffer buffer = aiScene.mMeshes();

        List<Float> combinedVertices = new List<>();
        List<Float> combinedTexCoords = new List<>();
        List<Integer> combinedIndices = new List<>();

        int vertexOffset = 0;

        for (int z = 0; z < numMeshes; z++) {
            AIMesh aiMesh = AIMesh.create(buffer.get(z));

            List<Float> vertices = new List<>();
            List<Float> textCoords = new List<>();
            List<Integer> indices = new List<>();

            processMesh(aiMesh, vertices, textCoords, indices);

            combinedVertices.add(vertices);
            combinedTexCoords.add(textCoords);

            for (int index : indices) {
                combinedIndices.add(index + vertexOffset);
            }
            vertexOffset += vertices.size() / 3;
        }

        Mesh combinedMesh = new Mesh(combinedVertices.toArray(new float[0]), combinedVertices.toArray(new float[0]), combinedIndices.toArray(new int[0]));
        return new Model(combinedMesh, texturePath);
    }

    private static void processMesh(AIMesh aiMesh, List<Float> vertices, List<Float> textCoords, List<Integer> indices) {
        vertices.add(processVertices(aiMesh));
        textCoords.add(processTextCoords(aiMesh));
        indices.add(processIndices(aiMesh));
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