package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        this.geometries = new ArrayList<Intersectable>(Arrays.asList(geometries));
    }

    /**
     * Adds a geometry to the geometries list.
     *
     * @param geometry The geometry to add to the geometries list.
     */
    public void add(Intersectable geometry) {
        geometries.add(geometry);
    }

    /**
     * @param ray The ray that we want to find the intersections with the geometry shape
     * @return list of points where the ray intersect the geometry shape
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        //if there are no intersections return null
        boolean isIntersect = false;
        for (Intersectable geometry : geometries) {
            if (geometry.findIntersections(ray) != null) {
                isIntersect = true;
                break;
            }
        }
        if (!isIntersect)
            return null;
        //if there are intersections, return a list of all the intersections
        List<Point> intersections = new LinkedList<Point>();
        for (Intersectable geometry : geometries) {
            if (geometry.findIntersections(ray) != null)
                intersections.addAll(geometry.findIntersections(ray));
        }
        return intersections;
    }


}
