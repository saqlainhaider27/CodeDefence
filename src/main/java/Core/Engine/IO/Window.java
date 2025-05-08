package Core.Engine.IO;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL11C.*;

public class Window {
    private int width,height;
    private String title;
    private long windowHandle;
    private Input input;
    public Window(String title, int width, int height){
        this.title = title;
        this.height = height;
        this.width = width;
    }
    public void init(){
        try{
            create(width, height);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void create(int width, int height) throws Exception {
        if (!glfwInit()) {
            throw new Exception("Unable to initialize GLFW");
        }

        input = new Input();
        glfwDefaultWindowHints();

        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);

        if (width > 0 && height > 0) {
            this.width = width;
            this.height = height;
        } else {
            glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            width = vidMode.width();
            height = vidMode.height();
        }

        windowHandle = glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
        if (windowHandle == MemoryUtil.NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Resize event
        glfwSetFramebufferSizeCallback(windowHandle, (window, w, h) -> resized(w, h));

        // Input CallBack
        glfwSetKeyCallback(windowHandle,input.getKeyCallback());
        glfwSetCursorPosCallback(windowHandle,input.getCursorPosCallback());
        glfwSetMouseButtonCallback(windowHandle,input.getMouseButtonCallback());

        glfwMakeContextCurrent(windowHandle);
        glfwShowWindow(windowHandle);
        glfwSwapInterval(1); // This is used to set turn on vSync
        createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Set the colour to black on init

        // Generic settings
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_STENCIL_TEST);
        //glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        

    }

    private void resized(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // Display the next scene in queue
    public void update(){
        glfwSwapBuffers(windowHandle);
        glfwPollEvents();
    }

    public void cleanup() {
        glfwFreeCallbacks(windowHandle);
        glfwDestroyWindow(windowHandle);
        glfwTerminate();
        GLFWErrorCallback callback = glfwSetErrorCallback(null);
        if (callback != null) {
            callback.free();
        }
    }
    public boolean shouldClose(){
        return glfwWindowShouldClose(windowHandle);
    }
    public void setBackgroundColor(float r, float g, float b) {
        GL11.glClearColor(r, g, b, 1.0f);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
