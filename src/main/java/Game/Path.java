package Game;

import Utils.Generics.LinkedList;
import org.joml.Vector3f;

public class Path {
    public static class Waypoint {
        public Vector3f position;
        public Waypoint(Vector3f position) {
            this.position = position;
        }
    }

    private final LinkedList<Waypoint> list;
    private int current;

    public Path() {
        list = new LinkedList<>();

        int segments = 10;
        float startX = -10;
        float endX = 7.5f;
        float centerZ = -10;
        float curveHeight = 10;

        for (int i = 0; i <= segments; i++) {
            float t = i / (float) segments;
            float x = lerp(startX, endX, t);
            float z = (float) (centerZ + curveHeight * Math.sin(t * Math.PI));
            list.add(new Waypoint(new Vector3f(x, 0.5f, z)));
        }

        current = 0;
    }

    public Vector3f nextPointPosition() {
        if (current >= list.size()) {
            return null;
        }
        return list.get(current++).position;
    }

    private float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }
}
