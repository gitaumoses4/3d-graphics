package com.graphics.tools;

import java.awt.*;

public class Triangle implements Paint {
    private final Vector3D vertices[] = new Vector3D[3];

    public Triangle(Vector3D a, Vector3D b, Vector3D c) {
        this.vertices[0] = a;
        this.vertices[1] = b;
        this.vertices[2] = c;
    }

    public Triangle(float... points) {
        if (points.length != 9) {
            throw new IllegalArgumentException("The number of points should be equal to 9, 3 for each vector");
        }
        for (int i = 0; i < points.length; i++) {
            vertices[i / 3] = new Vector3D(points[i % 3], points[i % 3], points[i % 3]);
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

    public Triangle scale(float value) {
        return new Triangle(
                vertices[0].scale(value),
                vertices[1].scale(value),
                vertices[2].scale(value)
        );
    }

    public Triangle getProjected(int screenWidth, int screenHeight) {
        return new Triangle(
                vertices[0].getProjected(screenWidth, screenHeight),
                vertices[1].getProjected(screenWidth, screenHeight),
                vertices[2].getProjected(screenWidth, screenHeight)
        );
    }


    @Override
    public void draw(Graphics2D g, int screenWidth, int screenHeight) {
        Triangle projected = getProjected(screenWidth, screenHeight);
        projected = projected.scale(50);
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        Vector3D vertices[] = projected.getVertices();
        for (int i = 0; i < 3; i++) {
            xPoints[i] = (int) vertices[i].getX();
            yPoints[i] = (int) vertices[i].getY();
        }
        g.setColor(Color.WHITE);
        g.drawPolygon(new int[]{10, 20, 30}, new int[]{10, 20, 30}, 3);
    }
}
