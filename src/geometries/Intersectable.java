package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The class uses as an interface, we chose to make it abstract class, and it cant extend an interface
 * @author Ori Perlmutter, Eitan Kaantman
 */
public interface Intersectable {
    /**
     * @param ray, the ray that we want to find the intersections with the geometry shape
     * @return list of points where the ray intersect the geometry shape
     */
    List<Point> findIntersections(Ray ray);
}
