package geometries;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import geometries.Polygon;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Testing Polygons
 *
 * @author Ori Perlmutter, Eitan Kaantman
 */
public class PolygonTests {

    /**
     * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertix on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertice on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point[] pts =
                {new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1)};
        Polygon pol = new Polygon(pts);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        Vector result = pol.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Polygon's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1]))),
                    "Polygon's normal is not orthogonal to one of the edges");
    }

    /**
     * Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}.
     */
    // decided to use the same test as in the triangle, since the polygon could be a triangle
    @Test
    public void FindIntersections() {
        // assuming the ray intersects the plane of the polygon
        Polygon polygon = new Polygon(new Point(0, 1, -1), new Point(0, 3, -3), new Point(1, 2, -2));
        // ============ Equivalence Partitions Tests ==============
        //TC01: Ray intersects the triangle's plane (1 point)
        Point p1 = new Point(0.7, 2, -2);
        Ray ray1 = new Ray(new Point(-3.3,-2,-6),new Vector(1,1,1) );
        assertEquals(List.of(p1), polygon.findIntersections(ray1), "Ray crosses triangle at one place");
        //TC02: Ray does not intersect the Polygon's plane. against edge (0 points)
        Point p2 = new Point(4, 5, 3);
        Ray ray2 = new Ray(p2,new Vector(1, 1, 1));
        assertNull(polygon.findIntersections(ray2), "Ray  uncrosses triangle");
        //TC03: Ray does not intersect the triangle's plane. against vertex (0 points)
        Point p3 = new Point(2, 3, 1);
        Ray ray3 = new Ray(p3,new Vector(1, 1, 1));
        assertNull(polygon.findIntersections(ray3), "Ray uncrosses triangle");
        // ================== Boundary Values Tests ==================
        //TC04: Ray starts before and crosses the triangle in vertex (0 points)
        Point p4 = new Point(0, 1, -3);
        Ray ray4 = new Ray(p4,new Vector(1, 1, 1));
        assertNull(polygon.findIntersections(ray4), "Ray uncrosses triangle");
        //TC05: Ray starts before and crosses the triangle in edge (0 points)
        Point p5 = new Point(-1, 1, -3);
        Ray ray5 = new Ray(p5,new Vector(1, 1, 1));
        assertNull(polygon.findIntersections(ray5), "Ray uncrosses triangle");
        //TC06: Ray starts before and crosses the triangle in edge's continuation (0 points)
        Point p6 = new Point(-1, 5, -5);
        Ray ray6 = new Ray(p6,new Vector(1, 1, 1));
        assertNull(polygon.findIntersections(ray6), "Ray uncrosses triangle");

    }


}
