
package geometries;

import primitives.Point;

import org.junit.jupiter.api.Test;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static primitives.Util.isZero;

/**
 * Testing Plane
 *
 * @author Eitan Kaantman, Ori Perlmutter
 */
class PlaneTests {
    /**
     * Test method for {@link geometries.Plane#getNormal()}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 2, 1);
        Point p3 = new Point(2, 0, 1);
        Plane plane = new Plane(p1, p2, p3);
        Vector result1 = plane.getNormal();
        // ensure |result| = 1
        assertEquals(1, result1.length(), 0.00000001, "Plane's normal is not a unit vector");
        // ensure the result is orthogonal to the all the vectors of the plane
        Vector v1 = p1.subtract(p2);
        Vector v2 = p1.subtract(p3);
        Vector v3 = p2.subtract(p3);
        assertTrue(isZero(result1.dotProduct(v1)) && isZero(result1.dotProduct(v2)) && isZero(result1.dotProduct(v3)),
                "Plane's normal is not orthogonal to one of the vectors of the plane");
    }

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     */
    @Test
    public void testConstructor() {
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 2, 1);
        Point p3 = new Point(2, 0, 1);
        Point p4 = new Point(1, 0, 0);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Correct plane
        assertDoesNotThrow(() -> new Plane(p1, p2, p3), "Plane's constructor throws an exception when the plane is correct");
        // =============== Boundary Values Tests ==================
        // first and second points are the same, throws exception
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(p1, p4, p3), "Plane's constructor does not throw an exception when the first and second points are the same");
        // all points on the same Vector, throws exception
        Point p5 = new Point(4, 0, 0);
        Point p6 = new Point(3, 0, 0);
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(p1, p5, p6), "Plane's constructor does not throw an exception when all points are on the same Vector");
    }

    /**
     * Test method for {@Link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormalWithPoint() {
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 2, 1);
        Point p3 = new Point(2, 0, 1);
        Plane plane = new Plane(p1, p2, p3);
        //TC01: Point is on the plane
        assertDoesNotThrow(() -> plane.getNormal(p1), "Plane's getNormal throws an exception when the point is on the plane");
        // Point not on the plane
        Point notOnPlane = new Point(1, 2, 1);
        assertThrows(IllegalArgumentException.class,
                () -> plane.getNormal(notOnPlane), "Plane's getNormal does not throw an exception when the point is not on the plane");
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(Ray ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(new Point(1, 1, 1), new Vector(1, 2, 8));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the plane (1 point)
        Point p1 = new Point(0.23076923076923073, 0.46153846153846145, 1.2307692307692308); //around
        Ray ray1 = new Ray(new Point(0, 0, 1), new Vector(1, 2, 1));
        assertEquals(List.of(p1), plane.findIntersections(ray1), "Ray crosses plane at one place");
        // TC02: Ray does not intersect the plane (0 points)
        Ray ray2 = new Ray(new Point(-3, 5, -3), new Vector(-1, -2, -1));
        assertNull(plane.findIntersections(ray2), "Wrong number of points");
        // =============== Boundary Values Tests ==================
        // TC03: Ray is parallel to the plane, and on the plane (0 points)
        Ray ray3 = new Ray(new Point(1, 1, 1), new Vector(2, -1, 0));
        assertNull(plane.findIntersections(ray3), "Ray's line has no intersection with the plane");
        // TC04: Ray is parallel to the plane, and not on the plane (0 points)
        Ray ray4 = new Ray(new Point(0, 0, 1), new Vector(2, -1, 0));
        assertNull(plane.findIntersections(ray4), "Ray's line has no intersection with the plane");
        // TC05: Ray is orthogonal to the plane, and on the plane (0 points)
        Ray ray5 = new Ray(new Point(1, 1, 1), new Vector(1, 2, 8));
        assertNull(plane.findIntersections(ray5), "Ray's line has no intersection with the plane");
        // TC06: Ray is orthogonal to the plane, and before the plane (1 point)
        Point p2 = new Point(0.043478260869565216, 0.08695652173913043, 1.3478260869565217);
        Ray ray6 = new Ray(new Point(0, 0, 1), new Vector(1, 2, 8));
        assertEquals(List.of(p2), plane.findIntersections(ray6), "Ray crosses plane at one place");
        // TC07: Ray is orthogonal to the plane, and after the plane (0 points)
        Ray ray7 = new Ray(new Point(2, 2, 2), new Vector(1, 2, 8));
        assertNull(plane.findIntersections(ray7), "Ray's line has no intersection with the plane");
        // TC08: Ray is neither orthogonal nor parallel to and begins at the plane (0 points)
        Ray ray8 = new Ray(new Point(1, 1, 1), new Vector(2, 3, 2));
        assertNull(plane.findIntersections(ray8), "Ray's line has no intersection with the plane");
        // TC09: Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (0 points)
        Ray ray9 = new Ray(new Point(1, 1, 1), new Vector(2, 4, 2));
        assertNull(plane.findIntersections(ray9), "Ray's line has no intersection with the plane");
    }
}