package com.graphics.tools;

import com.graphics.utils.Matrix;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Mesh implements Paint {
    private ArrayList<Triangle> triangles;
    private Matrix transformationLeft = Matrix.transformation();
    private Matrix transformationRight = Matrix.transformation();

    public Mesh(ArrayList<Triangle> triangles) {
        this.triangles = new ArrayList<>(triangles);
        this.transformationRight.setRight(true);
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

    public void clearTransformation(){
        this.transformationRight = Matrix.transformation();
        this.transformationLeft = Matrix.transformation();
    }
    public void addTransformation(Matrix matrix) {
        if (matrix.fromRight()) {
            this.transformationRight = this.transformationRight.multiply(matrix);
        } else {
            this.transformationLeft = this.transformationLeft.multiply(matrix);
        }
    }

    @Override
    public void draw(Graphics2D g, int screenWidth, int screenHeight) {
        for (Triangle triangle : triangles) {
            Triangle transformed = triangle.transform(this.transformationRight);
            transformed = transformed.transform(transformationLeft);
            transformed.draw(g, screenWidth, screenHeight);
        }
    }
}
