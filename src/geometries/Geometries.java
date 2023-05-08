package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.*;

/**
 * The class represents a collection of geometry shapes, and it implements the Intersectable interface.
 */
public class Geometries implements Intersectable {
    List<Intersectable> geometries;

    /**
     * Default constructor that creates an empty list of geometries.
     */
    public Geometries() {
        geometries = new LinkedList<Intersectable>();
    }

    /**
     * Constructor that gets a list of geometries and adds them to the geometries list.
     *
     * @param geometries The geometries to add to the geometries list.
     */
    public Geometries(Intersectable... geometries) {
        this();
        add(geometries);
    }

    /**
     * Adds a geometry to the geometries list.
     *
     * @param geometries The geometric objects to add to the geometries list.
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }

    /**
     * @param ray The ray that we want to find the intersections with the geometry shape
     * @return list of points where the ray intersect the geometry shape
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        //if there are no intersections return null
        List<Point> result = new LinkedList<>();
        for (Intersectable geo : geometries) {
            List<Point> temp = geo.findIntersections(ray);
            if (temp != null) {
                result.addAll(temp);
            }
        }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }


}
