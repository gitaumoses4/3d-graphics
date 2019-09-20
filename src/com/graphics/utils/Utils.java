package com.graphics.utils;

import com.graphics.maths.Vector2f;
import com.graphics.maths.Vector3f;
import org.lwjgl.BufferUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Utils {
    public static final String RESOURCES_DIRECTORY = "../../../resources";
    public static final String SHADERS_DIRECTORY = RESOURCES_DIRECTORY + "/shaders";
    public static final String TEXTURES_DIRECTORY = RESOURCES_DIRECTORY + "/textures";
    public static final String OBJECTS_DIRECTORY = RESOURCES_DIRECTORY + "/objects";


    public static String createFilePath(String directory, String fileName) {
        fileName = fileName.startsWith(File.separator) ? fileName : (File.separator + fileName);
        return directory + fileName;
    }

    public static File createResourceFile(String path) throws ClassNotFoundException {
        try {
            return new File(Class.forName(Utils.class.getName()).getResource(path).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream createInputStream(String path) throws ClassNotFoundException {
        return Class.forName(Utils.class.getName()).getResourceAsStream(path);
    }

    public static String loadShader(String fileName) throws Exception {
        // add the path separator to the file name
        String result;
        try (InputStream in = createInputStream(createFilePath(SHADERS_DIRECTORY, fileName));
             Scanner scanner = new Scanner(in, StandardCharsets.UTF_8)) {
            result = scanner.useDelimiter("\\A").next();
        }
        return result;
    }

    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf + 1);
    }

    public static InputStream loadTexture(String fileName) throws ClassNotFoundException {
        return createInputStream(createFilePath(TEXTURES_DIRECTORY, fileName));
    }

    public static File loadObject(String fileName) throws ClassNotFoundException {
        return createResourceFile(createFilePath(OBJECTS_DIRECTORY, fileName));
    }

    public static FloatBuffer toBuffer(float[] data) {
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(data.length);
        floatBuffer.put(data);
        floatBuffer.flip();

        return floatBuffer;
    }

    public static IntBuffer toBuffer(int[] data) {
        IntBuffer intBuffer = BufferUtils.createIntBuffer(data.length);
        intBuffer.put(data);
        intBuffer.flip();
        return intBuffer;
    }

    public static void addToArray(Vector3f vector, float[] array, int index) {
        array[index] = vector.x;
        array[index + 1] = vector.y;
        array[index + 2] = vector.z;
    }

    public static void addToArray(Vector2f vector, float[] array, int index) {
        array[index] = vector.x;
        array[index + 1] = vector.y;
    }
}
