package geometries;

import primitives.Point;

import org.junit.jupiter.api.Test;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
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
     *
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
        Point notOnPlane = new Point (1, 2, 1);
        assertThrows(IllegalArgumentException.class,
                () -> plane.getNormal(notOnPlane), "Plane's getNormal does not throw an exception when the point is not on the plane");
    }
}



