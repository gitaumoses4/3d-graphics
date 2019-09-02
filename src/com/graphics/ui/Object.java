package com.graphics.ui;

import com.graphics.events.MouseInfoListener;
import com.graphics.tools.Camera;
import com.graphics.tools.Mesh;
import com.graphics.tools.Paint;

import java.awt.*;
import java.util.ArrayList;

public abstract class Object implements Paint, MouseInfoListener {
    private ArrayList<Mesh> meshes = new ArrayList<>();

    public Object() {
        this.initialize();
    }

    public abstract void initialize();

    public Object(ArrayList<Mesh> meshes) {
        this.meshes = new ArrayList<>(meshes);
    }

    public void addMesh(Mesh mesh) {
        this.meshes.add(mesh);
    }

    public void removeMesh(Mesh mesh) {
        this.meshes.remove(mesh);
    }

    public ArrayList<Mesh> getMeshes() {
        return meshes;
    }

    @Override
    public void draw(DrawingParams drawingParams) {
        for (Mesh m : meshes) {
            m.draw(drawingParams);
        }
    }
}
