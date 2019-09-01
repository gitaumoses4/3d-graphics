package com.graphics.tools;

import com.graphics.utils.Axis;
import com.graphics.utils.Matrix;
import com.graphics.utils.TransformationMatrices;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Triangle implements Paint {
    private final Vector3D vertices[] = new Vector3D[3];

    public Triangle(Vector3D a, Vector3D b, Vector3D c) {
        this.vertices[0] = a;
        this.vertices[1] = b;
        this.vertices[2] = c;
    }

    public Triangle(Vector3D... vertices) {
        System.arraycopy(vertices, 0, this.vertices, 0, 3);
    }

    public Triangle(List<Vector3D> vertices) {
        for (int i = 0; i < 3; i++) {
            this.vertices[i] = vertices.get(i);
        }
    }

    public Triangle(float... points) {
        if (points.length != 9) {
            throw new IllegalArgumentException("The number of points should be equal to 9, 3 for each vector");
        }
        for (int i = 0; i < 3; i++) {
            vertices[i] = new Vector3D(points[i * 3], points[i * 3 + 1], points[i * 3 + 2]);
        }
    }

    public Vector3D[] getVertices() {
        return vertices;
    }

    public void setVertex(int index, Vector3D vector) {
        this.vertices[index] = vector;
    }

    public Vector3D getVertex(int index) {
        return vertices[index];
    }

    public Triangle transform(Matrix matrix) {
        List<Vector3D> vertices = Stream.of(this.vertices)
                .map(vector3D -> {
                    return vector3D.transform(matrix);
                }).collect(Collectors.toList());
        return new Triangle(vertices);
    }

    @Override
    public void draw(Graphics2D g, int screenWidth, int screenHeight) {
        Vector3D[] vertices = this.getVertices();
        Polygon p = new Polygon();
        for (int i = 0; i < 3; i++) {
            Vector3D vertex = TransformationMatrices.applyTranslation(2, Axis.Z, vertices[i]);
            vertex = TransformationMatrices.applyProjection(screenWidth, screenHeight, vertex);
            p.addPoint((int) vertex.getX(), (int) vertex.getY());
        }
        g.setColor(Color.WHITE);
        g.drawPolygon(p);
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %s]", vertices[0], vertices[1], vertices[2]);
    }
}
