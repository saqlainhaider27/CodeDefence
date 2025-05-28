package Game;

import Utils.Generics.LinkedList;
import org.joml.Vector3f;

public class Path {
    public static class Waypoint{
        public Vector3f position ;
        public Waypoint(Vector3f position) {
            this.position = position;
        }
    }
    private LinkedList<Waypoint> list;
    private int current;
    public Path() {
        list = new LinkedList<>();
        list.add(new Waypoint(new Vector3f(0,0,0)));
        list.add(new Waypoint(new Vector3f(0,10,0)));
        list.add(new Waypoint(new Vector3f(0,0,0)));
        list.add(new Waypoint(new Vector3f(-10,0,0)));
        list.add(new Waypoint(new Vector3f(0,0,0)));
        list.add(new Waypoint(new Vector3f(0,0,10)));
        current = 0;

    }
    public Vector3f nextPointPosition(){
        if (current >= list.size()){
            return null;
        }
        return list.get(current++).position;
    }
}
