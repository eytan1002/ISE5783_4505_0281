package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Testing Geometries, the class that holds a list of geometries.
 *
 * @author Eitan Kaantman, Ori Perlmutter
 */
class GeometriesTests {

    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
    @Test
    void findIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Some of the geometries intersect the ray, and some don't. it intersects 2 geometries, plane and sphere. returns 3 points
        Geometries geometries = new Geometries(
                new Sphere(1, new Point(1, 0, 0)),
                new Plane(new Point(0, 0, 1), new Vector(0, 0, 1)),
                new Triangle(new Point(1, 1, 1), new Point(1, 2, 2), new Point(2, 3, 2)));

        List<Point> result = geometries.findIntersections(new Ray(new Vector(0, 0, 1), new Point(0.5, 0.5, -3.5)));
        assertEquals(3, result.size(), "Wrong number of points");
        // ================== Boundary Values Tests ==================
        // TC11: All the geometries intersect the ray. it intersects 3 geometries, plane, sphere and triangle. returns 4 points
        Geometries geometries2 = new Geometries(
                new Sphere(1, new Point(1, 0, 0)),
                new Plane(new Point(0, 0, 1), new Vector(0, 0, 1)),
                new Triangle(new Point(1, 1, 1), new Point(1, 2, 2), new Point(2, 3, 2)));
        List<Point> result2 = geometries2.findIntersections(new Ray(new Vector(0, 0, 1), new Point(0.5, 0.5, -4)));
        assertEquals(4, result2.size(), "Wrong number of points");

        // TC12: None of the geometries intersect the ray
        List<Point> result_1 = geometries2.findIntersections(new Ray(new Vector(0, 0, 1), new Point(8.5, 7.5, 4)));
        assertEquals(0, result_1.size(), "Wrong number of points");

        // TC13: empty list of geometries
        Geometries emptyGeometries = new Geometries();
        assertNull(emptyGeometries.findIntersections(new Ray(new Vector(3, 1, 0), new Point(-1, 0, 0))), "Wrong number of points");

        //TC14:  only one geometry in the list, with a couple of geometries intersects the ray. The ray intersects the sphere, at 1 point
        List<Point> result3 = geometries.findIntersections(new Ray(new Vector(0, 0, 1), new Point(0.5, 0.5, -0.5)));
        assertEquals(1, result3.size(), "Wrong number of points");

    }
}