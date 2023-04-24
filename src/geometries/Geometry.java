
package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The class uses as an interface, we chose to make it abstract class, because polygon extends it, and it cant extend an interface
 * @author Ori Perlmutter, Eitan Kaantman
 */
public interface Geometry extends Intersectable {
    /**
     * Abstract method for getting the normal vector to a geometry at a given point
     * @param point The point on the geometry for which to get the normal vector
     * @return The normal vector to the geometry at the given point
     */
    public abstract Vector getNormal(Point point);
}
