package com.graphics.tools;

import com.graphics.utils.Matrix;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Mesh implements Paint {
    private ArrayList<Triangle> triangles;
    private Matrix transformation = Matrix.transformation();
    private float translateX, translateY, translateZ;

    public Mesh(ArrayList<Triangle> triangles) {
        this.triangles = new ArrayList<>(triangles);
    }

    public Mesh(Triangle... triangles) {
        this.triangles = new ArrayList<>(Arrays.asList(triangles));
    }

    public ArrayList<Triangle> getTriangles() {
        return triangles;
    }

    public void addTriangles(ArrayList<Triangle> triangles) {
        this.triangles.addAll(triangles);
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

    public void clearTransformation() {
        this.transformation = Matrix.transformation();
    }

    public void addTransformation(Matrix matrix) {
        this.transformation = this.transformation.multiply(matrix);
    }

    public void setTranslateX(float translateX) {
        this.translateX = translateX;
    }

    public void setTranslateY(float translateY) {
        this.translateY = translateY;
    }

    public void setTranslateZ(float translateZ) {
        this.translateZ = translateZ;
    }

    @Override
    public void draw(DrawingParams drawingParams) {
        for (Triangle triangle : triangles) {
            Triangle transformed = triangle.transform(this.transformation);
            Triangle translated = transformed.translateTo(translateX, translateY, translateZ);
            translated.draw(drawingParams);
        }
    }
}
