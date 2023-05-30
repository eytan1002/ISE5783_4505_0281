package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Testing Polygons
 *
 * @author Ori perlmutter, Eitan Kantman.
 */
class TriangleTests {
    /**
     * Test method for {@link geometries.Triangle#getNormal(Point)}.
     */
    @Test
    public void testGetNormal() {
        //TC01: Simple normal test
        Triangle triangle = new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        Vector normal = triangle.getNormal(new Point(0, 0, 0));
        assertEquals(new Vector(0, 0, 1), normal);
        //making sure the normal size is 1
        assertEquals(1, normal.length(), 0.00000001, "Polygon's normal is not a unit vector");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(Ray)}.
     */
    @Test
    public void FindIntersections() {
        // assuming the ray intersects the plane of the triangle
        // ================== Equivalence Partitions Tests ==================
        Triangle triangle = new Triangle(new Point(0, 1, -1), new Point(0, 3, -3), new Point(1, 2, -2));
        //TC01: Ray intersects the triangle's plane (1 point)
        Point p1 = new Point(0.7, 2, -2);
        Ray ray1 = new Ray(new Point(-3.3, -2, -6), new Vector(1, 1, 1));
        assertEquals(List.of(p1), triangle.findIntersections(ray1), "Ray crosses triangle at one place");
        //TC02: Ray does not intersect the triangle's plane. against edge (0 points)
        Point p2 = new Point(4, 5, 3);
        Ray ray2 = new Ray(p2, new Vector(1, 1, 1));
        assertNull(triangle.findIntersections(ray2), "Ray  uncrosses triangle");
        //TC03: Ray does not intersect the triangle's plane. against vertex (0 points)
        Point p3 = new Point(2, 3, 1);
        Ray ray3 = new Ray(p3, new Vector(1, 1, 1));
        assertNull(triangle.findIntersections(ray3), "Ray uncrosses triangle");
        // ================== Boundary Values Tests ==================
        //TC04: Ray starts before and crosses the triangle in vertex (0 points)
        Point p4 = new Point(-1, 0, -2);
        Ray ray4 = new Ray(p4, new Vector(1, 1, 1));
        assertNull(triangle.findIntersections(ray4), "Ray uncrosses triangle");
        //TC05: Ray starts before and crosses the triangle in edge (0 points)
        Point p5 = new Point(-1, 1, -3);
        Ray ray5 = new Ray(p5, new Vector(1, 1, 1));
        assertNull(triangle.findIntersections(ray5), "Ray uncrosses triangle");
        //TC06: Ray starts before and crosses the triangle in edge's continuation (0 points)
        Point p6 = new Point(-1, 5, -5);
        Ray ray6 = new Ray(p6, new Vector(1, 1, 1));
        assertNull(triangle.findIntersections(ray6), "Ray uncrosses triangle");


        //yair's attempt to failure tests. lol
        Triangle t1 = new Triangle(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0));
        Ray rr = new Ray(new Point(0, 0, 0), new Vector(1, 1, 1));
        assertEquals(List.of(new Point(1d / 3, 1d / 3, 1d / 3)), t1.findIntersections(rr), "Ray crosses triangle at one place");
    }
}