package com.graphics.engine;

import com.graphics.engine.models.Model;
import com.graphics.engine.models.RawModel;
import com.graphics.maths.Vector2f;
import com.graphics.maths.Vector3f;
import com.graphics.utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OBJLoader {
    private static List<Vector3f> vertices, normals;
    private static List<Vector2f> textures;
    private static List<Integer> indices;
    private static BufferedReader reader;
    private static String line;

    private static Vector3f readVector3f(String line[]) {
        return new Vector3f(Float.parseFloat(line[1]), Float.parseFloat(line[2]), Float.parseFloat(line[3]));
    }

    private static Vector2f readVector2f(String line[]) {
        return new Vector2f(Float.parseFloat(line[1]), Float.parseFloat(line[2]));
    }

    public static RawModel loadObjModel(String fileName, Loader loader) {
        try {
            vertices = new ArrayList<>();
            indices = new ArrayList<>();
            textures = new ArrayList<>();
            normals = new ArrayList<>();

            FileReader fileReader = new FileReader(Utils.loadObject(fileName));
            reader = new BufferedReader(fileReader);
            loadVertexData();
            float[] verticesArray = new float[vertices.size() * 3];
            float[] texturesArray = new float[vertices.size() * 2];
            float[] normalsArray = new float[vertices.size() * 3];
            while (line != null) {
                String[] currentLine = line.split(" ");
                if (currentLine[0].equals("f")) {
                    for (int i = 1; i < currentLine.length; i++) {
                        readVertex(currentLine[i], verticesArray, texturesArray, normalsArray);
                    }
                }
                line = reader.readLine();
            }
            int[] indicesArray = indices.stream().mapToInt(i -> i).toArray();
            return loader.loadToVAO(verticesArray, texturesArray, normalsArray, indicesArray);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void readVertex(String vertexData, float[] verticesArray, float[] texturesArray, float[] normalsArray) {
        String[] tokens = vertexData.split("/");
        int index = Integer.parseInt(tokens[0]) - 1;
        int textureIndex = tokens[1].isEmpty() ? 0 : Integer.parseInt(tokens[1]) - 1;
        int normalIndex = Integer.parseInt(tokens[2]) - 1;
        indices.add(index);

        Vector2f texture = textures.get(textureIndex);
        texturesArray[index * 2] = texture.x;
        texturesArray[index * 2 + 1] = 1 - texture.y;

        Vector3f normal = normals.get(normalIndex);
        Utils.addToArray(normal, normalsArray, index * 3);

        Vector3f vertex = vertices.get(index);
        Utils.addToArray(vertex, verticesArray, index * 3);
    }


    private static void loadVertexData() throws IOException {
        line = null;
        boolean reading = true;
        while (reading) {
            line = reader.readLine();
            String[] currentLine = line.split(" ");
            switch (currentLine[0]) {
                case "v":
                    vertices.add(readVector3f(currentLine));
                    break;
                case "vt":
                    textures.add(readVector2f(currentLine));
                    break;
                case "vn":
                    normals.add(readVector3f(currentLine));
                    break;
                case "f":
                    reading = false;
                    break;
            }
        }
    }
}
