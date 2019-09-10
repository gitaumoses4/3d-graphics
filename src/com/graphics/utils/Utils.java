package com.graphics.utils;

import org.lwjgl.BufferUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Utils {
    public static final String RESOURCES_DIRECTORY = "../../../resources";

    public static String loadResource(String fileName) throws Exception {
        // add the path separator to the file name
        fileName = fileName.startsWith(File.separator) ? fileName : (File.separator + fileName);
        String result;
        try (InputStream in = Class.forName(Utils.class.getName()).getResourceAsStream(RESOURCES_DIRECTORY + fileName);
             Scanner scanner = new Scanner(in, StandardCharsets.UTF_8)) {
            result = scanner.useDelimiter("\\A").next();
        }
        return result;
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
