package com.graphics.utils;

import org.lwjgl.BufferUtils;

import java.io.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Utils {
    public static final String RESOURCES_DIRECTORY = "../../../resources";
    public static final String SHADERS_DIRECTORY = RESOURCES_DIRECTORY + "/shaders";
    public static final String TEXTURES_DIRECTORY = RESOURCES_DIRECTORY + "/textures";


    public static String createFilePath(String directory, String fileName) {
        fileName = fileName.startsWith(File.separator) ? fileName : (File.separator + fileName);
        return directory + fileName;
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
}
