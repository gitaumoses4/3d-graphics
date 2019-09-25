package com.graphics.engine;

import com.graphics.engine.events.MouseListener;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWVidMode;

import java.awt.*;
import java.nio.DoubleBuffer;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private final String title;
    private int width;
    private int height;
    private long windowHandle;
    private boolean resized;
    private boolean vSync;
    private final ArrayList<MouseListener> mouseListeners = new ArrayList<>();
    private float prevX = -1, prevY = -1;

    public Window(String title, int width, int height, boolean vSync) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vSync;
        this.resized = false;
    }

    public void init() {
        // set up an error callback. The default implementation will
        // print the error message in System.err
        GLFWErrorCallback.createPrint(System.err);

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwWindowHint(GLFW_VISIBLE, GL_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
//        glfwWindowHint(GLFW_MAXIMIZED, GL_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);
        if (windowHandle == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwSetFramebufferSizeCallback(windowHandle, (window, width, height) -> {
            this.width = width;
            this.height = height;
            this.setResized(true);
        });

        glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
        });

        glfwSetScrollCallback(windowHandle, ((window, xoffset, yoffset) -> {
            mouseListeners.forEach(mouseListener -> {
                mouseListener.onMouseScroll((float) xoffset, (float) yoffset);
            });
        }));

        glfwSetCursorPosCallback(windowHandle, ((window, xpos, ypos) -> {
            mouseListeners.forEach(mouseListener -> {
                if (prevX != -1 && prevY != -1) {
                    mouseListener.onMouseMoved((float) xpos, (float) ypos, (float) (xpos - prevX), (float) (ypos - prevY));
                    if (isMousePressed(GLFW_MOUSE_BUTTON_1)) {
                        mouseListener.onMouseDragged((float) xpos, (float) ypos, (float) (xpos - prevX), (float) (ypos - prevY));
                    }
                }
                prevX = (float) xpos;
                prevY = (float) ypos;
            });
        }));

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // center the window
        glfwSetWindowPos(windowHandle, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);

        // make the OpenGL context current
        glfwMakeContextCurrent(windowHandle);

        if (isvSync()) {
            glfwSwapInterval(1);
        }

        // make the window visible

        glfwShowWindow(windowHandle);

        createCapabilities();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }


    public void addMouseListener(MouseListener mouseListener) {
        this.mouseListeners.add(mouseListener);
    }

    public void removeMouseListener(MouseListener mouseListener) {
        this.mouseListeners.remove(mouseListener);
    }

    public void setClearColor(Color color) {
        glClearColor(
                color.getRed() / 255f,
                color.getGreen() / 255f,
                color.getBlue() / 255f,
                color.getAlpha() / 255f
        );
    }

    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(windowHandle, keyCode) == GLFW_PRESS;
    }

    public boolean isMousePressed(int key) {
        return glfwGetMouseButton(windowHandle, key) == GLFW_PRESS;
    }

    public boolean windowShouldClose() {
        return glfwWindowShouldClose(windowHandle);
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isResized() {
        return resized;
    }

    public void setResized(boolean resized) {
        this.resized = resized;
    }

    public boolean isvSync() {
        return vSync;
    }

    public void setvSync(boolean vSync) {
        this.vSync = vSync;
    }

    public void update() {
        glfwSwapBuffers(windowHandle);
        glfwPollEvents();
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
