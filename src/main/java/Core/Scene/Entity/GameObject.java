package Core.Scene.Entity;

import Core.Scene.Entity.Component.BaseComponent;
import Core.Scene.Scene;
import Main.Launcher;
import Utils.Generics.HashMap;
import org.joml.*;


public abstract class GameObject implements IBehaviour{
    private HashMap<Class<? extends BaseComponent>, BaseComponent> components = new HashMap<>();

    private Matrix4f modelMatrix;
    public Transform transform;
    private Model model;
    public Scene scene;

    public GameObject(Model model){
        this.model = model;
        this.transform = new Transform();
        modelMatrix = new Matrix4f();
        scene = Launcher.getGame().getScene();
        start();
    }
    public GameObject(Model model, Transform transform){
        this.model = model;
        this.transform = transform;
        modelMatrix = new Matrix4f();
        scene = Launcher.getGame().getScene();
        start();
    }

    public Matrix4f getModelMatrix() {
        return modelMatrix;
    }
    public void updateModelMatrix() {
        modelMatrix.translationRotateScale(transform.position, transform.rotation, transform.scale);
    }

    public Model getModel() {
        return model;
    }
    public void loop(){
        for (BaseComponent c : components.values()){
            c.update();
        }
        update();
    }
    public abstract void start();
    public abstract void update();

    public <T extends BaseComponent> T addComponent(Class<T> componentClass){
        if (hasComponent(componentClass)){
            System.out.println("Cannot add component component is already attached");
            return getComponent(componentClass);
        }
        try{
            T component = componentClass.getDeclaredConstructor().newInstance();
            components.put(componentClass, component);
            component.gameObject = this;
            component.start();
            return component;
        } catch (Exception e) {
            throw new RuntimeException(" Failed to add component: " + e);
        }
    }
    public <T extends BaseComponent> T getComponent(Class<T> componentClass){
        return componentClass.cast(components.get(componentClass));
    }
    public <T extends BaseComponent> T addComponent(Class<T> componentClass, Object... args) {
        if (hasComponent(componentClass)){
            System.out.println("Cannot add component. Component is already attached");
            return getComponent(componentClass);
        }
        try {
            Class<?>[] argTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                argTypes[i] = args[i].getClass();
            }

            T component = componentClass.getDeclaredConstructor(argTypes).newInstance(args);

            components.put(componentClass, component);
            component.gameObject = this;
            component.start();
            return component;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("No matching constructor found for " + componentClass.getName(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add component: " + e);
        }
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
    public void cleanup(){
        model.cleanup();
    }


}