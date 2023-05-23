
package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The Triangle class represents a two-dimensional polygon with three vertices in a three-dimensional Cartesian coordinate system.
 * This class extends the Polygon class and is defined by three Point objects that represent the three vertices of the triangle.
 *
 * @autor Ori perlmutter, Eytan Kantman
 */
public class Triangle extends Polygon {
    /**
     * Constructs a new Triangle object with the given three vertices, using polygon c'tor.
     *
     * @param p1 the first vertex of the triangle
     * @param p2 the second vertex of the triangle
     * @param p3 the third vertex of the triangle
     */

    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }// using Sphere c'tor

    // using Polygon findGeoIntersectionsHelper
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return super.findGeoIntersectionsHelper(ray);
    }


}
