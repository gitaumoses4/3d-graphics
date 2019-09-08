package com.graphics.engine;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import com.graphics.events.CustomMouseListener;
import com.graphics.events.MouseData;
import com.graphics.events.MouseInfoListener;
import com.graphics.tools.Paint;
import com.graphics.utils.Utils;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.opengl.GL40.*;


public class DummyGame implements MouseInfoListener, GameLogic {

    private final ArrayList<Object> objects;
    private final CustomMouseListener customMouseListener;
    private final Paint.DrawingParams drawingParams;
    private float zoomLevel;

    private float[] vertices = new float[]{
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    };
    private int programId;

    private int vboId, vaoId;

    private int fragmentShaderId, vertexShaderId;

    public DummyGame(Paint.DrawingParams drawingParams) {
        objects = new ArrayList<>();

        customMouseListener = new CustomMouseListener();
        customMouseListener.addMouseInfoListener(this);

        this.drawingParams = drawingParams;
    }

    public void addObject(Object object) {
        this.objects.add(object);
        this.addMouseListener(object);
    }

    public void addMouseListener(MouseInfoListener mouseInfoListener) {
        this.customMouseListener.addMouseInfoListener(mouseInfoListener);
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    @Override
    public void onMouseEvent(MouseData data) {
        if (data.type == MouseData.Type.ZOOM_IN || data.type == MouseData.Type.ZOOM_OUT) {
            float value = 0.1f;
            this.zoomLevel += data.type == MouseData.Type.ZOOM_IN ? value : -value;
        } else if (data.type == MouseData.Type.DRAG) {
        }
    }

    private int createShader(String shaderCode, int shaderType) {
        int shaderId = glCreateShader(shaderType);
        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        glAttachShader(programId, shaderId);
        return shaderId;
    }

    @Override
    public void init() throws Exception {
        programId = glCreateProgram();

        vertexShaderId = createShader(Utils.loadResource("vertex.glsl"), GL_VERTEX_SHADER);
        fragmentShaderId = createShader(Utils.loadResource("fragment.glsl"), GL_FRAGMENT_SHADER);

        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking shader code: " + glGetProgramInfoLog(programId, 1024));
        }

        if (vertexShaderId != 0) {
            glDetachShader(programId, vertexShaderId);
        }

        if (fragmentShaderId != 0) {
            glDetachShader(programId, fragmentShaderId);
        }

        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(programId, 1024));
        }

        FloatBuffer verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
        verticesBuffer.put(vertices).flip();

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    @Override
    public void input(Window window) {

    }

    @Override
    public void update(float interval) {

    }

    @Override
    public void render(Window window) {
        drawingParams.zoomLevel = zoomLevel;

        glUseProgram(programId);

        glBindVertexArray(vaoId);
        glEnableVertexAttribArray(0);

        glDrawArrays(GL_TRIANGLES, 0, 3);

        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }
}
