package Core;

import Core.Scene.Entity.Material;
import Core.Scene.Entity.Mesh;
import Core.Scene.Entity.Model;
import Utils.Generics.List;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.*;

import java.nio.IntBuffer;

public class ModelLoader {

    public static Model loadModel(String path){
        return loadModel(path, Material.DEFAULT_TEXTURE);
    }
    public static Model loadModel(String modelPath, String texturePath) {
        AIScene aiScene = Assimp.aiImportFile(modelPath, Assimp.aiProcess_Triangulate | Assimp.aiProcess_GenSmoothNormals);
        if (aiScene == null) {
            throw new RuntimeException("Could not load model: " + modelPath);
        }
        PointerBuffer buffer = aiScene.mMeshes();
        for (int i = 0; i < buffer.limit(); i++) {
            AIMesh aiMesh = AIMesh.create(buffer.get(i));
            processMesh(aiMesh);
        }
        float[] v = new float[vertices.size()];
        float[] t = new float[textCoords.size()];
        int[] i = new int[indices.size()];
        for (int j = 0; j < vertices.size(); j++) {
            v[j] = vertices.get(j);
        }
        for (int j = 0; j < textCoords.size(); j++) {
            t[j] = textCoords.get(j);
        }
        for (int j = 0; j < indices.size(); j++) {
            i[j] = indices.get(j);
        }
        Mesh mesh = new Mesh(v, t, i);
        return new Model(mesh, texturePath);
    }
    private static List<Float> vertices = new List<>();
    private static List<Float> textCoords = new List<>();
    private static List<Integer> indices = new List<>();
    private static void processMesh(AIMesh aiMesh) {
        vertices.add(processVertices(aiMesh));
        textCoords.add(processTextCoords(aiMesh));
        indices.add(processIndices(aiMesh));

        // Texture coordinates may not have been populated. We need at least the empty slots
//        if (textCoords.length == 0) {
//            int numElements = (vertices.length / 3) * 2;
//            textCoords = new float[numElements];
//        }
    }

    private static Float[] processVertices(AIMesh aiMesh) {
        AIVector3D.Buffer buffer = aiMesh.mVertices();
        Float[] data = new Float[buffer.remaining() * 3];
        int pos = 0;
        while (buffer.remaining() > 0) {
            AIVector3D textCoord = buffer.get();
            data[pos++] = textCoord.x();
            data[pos++] = textCoord.y();
            data[pos++] = textCoord.z();
        }
        return data;
    }

    private static Float[] processTextCoords(AIMesh aiMesh) {
        AIVector3D.Buffer buffer = aiMesh.mTextureCoords(0);
        if (buffer == null) {
            return new Float[]{};
        }
        Float[] data = new Float[buffer.remaining() * 2];
        int pos = 0;
        while (buffer.remaining() > 0) {
            AIVector3D textCoord = buffer.get();
            data[pos++] = textCoord.x();
            data[pos++] = 1 - textCoord.y();
        }
        return data;
    }
    private static Integer[] processIndices(AIMesh aiMesh) {
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
        Integer[] ind = new Integer[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            ind[i] = indices.get(i);
        }
        return ind;
        //return indices.stream().mapToInt(Integer::intValue).toArray();
    }


}