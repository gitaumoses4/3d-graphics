package com.graphics.tools;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Mesh implements Paint {
    private ArrayList<Triangle> triangles;

    public Mesh(ArrayList<Triangle> triangles) {
        this.triangles = new ArrayList<>(triangles);
    }

    public Mesh(Triangle... triangles) {
        this.triangles = new ArrayList<>(Arrays.asList(triangles));
    }

    public ArrayList<Triangle> getTriangles() {
        return triangles;
    }

    public void setTriangles(ArrayList<Triangle> triangles) {
        this.triangles = new ArrayList<>(triangles);
    }

    public void addTriangle(Triangle triangle) {
        this.triangles.add(triangle);
    }

    public void removeTriangle(Triangle triangle) {
        this.triangles.remove(triangle);
    }

    public void removeTriangle(int index) {
        this.triangles.remove(index);
    }

    @Override
    public void draw(Graphics2D g, int screenWidth, int screenHeight) {
        for (Triangle triangle : triangles) {
            triangle.draw(g, screenWidth, screenHeight);
        }
    }
}
