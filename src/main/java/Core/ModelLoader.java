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

        // Retrieve the number of meshes in the model
        int numMeshes = aiScene.mNumMeshes();
        PointerBuffer buffer = aiScene.mMeshes();

        // Lists to store combined data for the single Mesh
        List<Float> combinedVertices = new List<>();
        List<Float> combinedTexCoords = new List<>();
        List<Integer> combinedIndices = new List<>();

        int vertexOffset = 0;

        for (int z = 0; z < numMeshes; z++) {
            // Extract mesh and process it
            AIMesh aiMesh = AIMesh.create(buffer.get(z));

            List<Float> vertices = new List<>();
            List<Float> textCoords = new List<>();
            List<Integer> indices = new List<>();

            processMesh(aiMesh, vertices, textCoords, indices);

            // Add vertices and texture coordinates to combined lists
            combinedVertices.add(vertices);
            combinedTexCoords.add(textCoords);

            // Adjust indices to account for the current vertex offset and add to combined list
            for (int index : indices) {
                combinedIndices.add(index + vertexOffset);
            }

            // Update vertex offset
            vertexOffset += vertices.size() / 3; // Each vertex has 3 components (x, y, z)
        }

        // Convert combined data to arrays
        float[] finalVertices = new float[combinedVertices.size()];
        float[] finalTexCoords = new float[combinedTexCoords.size()];
        int[] finalIndices = new int[combinedIndices.size()];

        for (int i = 0; i < combinedVertices.size(); i++) {
            finalVertices[i] = combinedVertices.get(i);
        }
        for (int i = 0; i < combinedTexCoords.size(); i++) {
            finalTexCoords[i] = combinedTexCoords.get(i);
        }
        for (int i = 0; i < combinedIndices.size(); i++) {
            finalIndices[i] = combinedIndices.get(i);
        }
        // Create a single Mesh with combined data and pass it to the Model
        Mesh combinedMesh = new Mesh(finalVertices, finalTexCoords, finalIndices);
        return new Model(combinedMesh, texturePath);
    }

    private static void processMesh(AIMesh aiMesh, List<Float> vertices, List<Float> textCoords, List<Integer> indices) {
        vertices.add(processVertices(aiMesh));
        textCoords.add(processTextCoords(aiMesh));
        indices.add(processIndices(aiMesh));

        // Handle missing texture coordinates
        if (textCoords.isEmpty()) {
            int numElements = (vertices.size() / 3) * 2; // Assuming 2 texture coords per vertex
            for (int i = 0; i < numElements; i++) {
                textCoords.add(0f); // Add placeholder data
            }
        }
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
                data.add(1 - texCoord.y()); // Flip Y coordinate
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