package com.graphics.engine;

import com.graphics.events.MouseData;
import com.graphics.events.MouseInfoListener;
import com.graphics.tools.*;
import com.graphics.tools.Paint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Object extends Mesh implements Paint, MouseInfoListener {

    private String name;

    public Object(String fileName) {
        ArrayList<Triangle> result = this.initialize();

        if (result != null) {
            this.addTriangles(result);
        }
        if (fileName != null && !fileName.isEmpty()) {
            this.load(fileName);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Object() {
        this(null);
    }

    public ArrayList<Triangle> initialize() {
        return null;
    }

    public void load(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            ArrayList<Vector3D> vertices = new ArrayList<>();
            reader.lines().forEach((line) -> {
                if (line != null && !line.isEmpty()) {
                    String[] tokens = line.split(" ");
                    switch (tokens[0]) {
                        case "o":
                            this.setName(line.substring(1).trim());
                            break;
                        case "v":
                            vertices.add(new Vector3D(Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3])));
                            break;
                        case "f":

                            addTriangle(new Triangle(
                                    vertices.get(Integer.parseInt(tokens[1]) - 1),
                                    vertices.get(Integer.parseInt(tokens[2]) - 1),
                                    vertices.get(Integer.parseInt(tokens[3]) - 1)
                            ));
                            break;
                    }
                }
            });
        } catch (IOException ex) {
            System.err.println("Error reading object file \"" + fileName + "\"");
        }
    }

    @Override
    public void onMouseEvent(MouseData data) {

    }
}
