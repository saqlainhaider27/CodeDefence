package Core.Scene.Entity;

import Core.ModelLoader;
import Core.Scene.Entity.Component.BaseComponent;
import org.joml.*;

import java.util.HashMap;
import java.util.Map;

public class GameObject {
    private Map<Class<? extends BaseComponent>, BaseComponent> components = new HashMap<>();

    private Matrix4f modelMatrix;
    public Transform transform;
    private Model model;

    public GameObject(Model model){
        this.model = model;
        this.transform = new Transform();
        modelMatrix = new Matrix4f();
    }
    public GameObject(Model model, Transform transform){
        this.model = model;
        this.transform = transform;
        modelMatrix = new Matrix4f();
    }

    public Matrix4f getModelMatrix() {
        return modelMatrix;
    }
    public void updateModelMatrix() {
        modelMatrix.translationRotateScale(transform.position, transform.rotation, transform.scale);
    }
    public void update(){
        for (BaseComponent c : components.values()){
            c.update();
        }

    }

    public <T extends BaseComponent> T addComponent(Class<T> componentClass){
        if (hasComponent(componentClass)){
            System.out.println("Cannot add component component is already attached");
            return getComponent(componentClass);
        }
        try{
            T component = componentClass.getDeclaredConstructor().newInstance();
            components.put(componentClass, component);
            component.start();
            component.gameObject = this;
            return component;
        } catch (Exception e) {
            throw new RuntimeException(" Failed to add component: " + e);
        }
    }
    public <T extends BaseComponent> T getComponent(Class<T> componentClass){
        return componentClass.cast(components.get(componentClass));
    }
    public <T extends BaseComponent> boolean hasComponent(Class<T> componentClass){
        return components.containsKey(componentClass);
    }
    public <T extends BaseComponent> T removeComponent(Class<T> componentClass){
        if (hasComponent(componentClass)){
            T component = getComponent(componentClass);
            components.remove(componentClass);
            return component;
        }else {
            throw new RuntimeException("Cannot remove component: Component not attached");
        }
    }

    public Model getModel() {
        return model;
    }
}