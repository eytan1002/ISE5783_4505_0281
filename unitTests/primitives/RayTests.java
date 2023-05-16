package primitives;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import primitives.Ray;

import java.util.List;


import org.junit.jupiter.api.Test;

/**
 * Testing Ray Class
 *
 * @ author Eitan Kantman & Ori Perlmutter
 */
class RayTests {
    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List<Point>)}.
     */
    @Test
    void testFindClosestPoint() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: The closest point is in the middle of the list
        Ray ray1 = new Ray(new Point(0, 0, 1.75), new Vector(0, 0, 1));
        List<Point> points = List.of(new Point(0, 0, 1), new Point(0, 0, 2), new Point(0, 0, 3));
        assertEquals(new Point(0, 0, 2), ray1.findClosestPoint(points), "TC01: The closest point is in the middle of the list");

        // =============== Boundary Values Tests ==================
        // TC02: The closest point is the first point in the list
        Ray ray2 = new Ray(new Point(0, 0, 1.2), new Vector(0, 0, 1));
        assertEquals(new Point(0, 0, 1), ray2.findClosestPoint(points), "TC02: The closest point is the first point in the list");

        // TC03: The closest point is the last point in the list
        Ray ray3 = new Ray(new Point(0, 0, 2.2), new Vector(0, 0, 1));
        assertEquals(new Point(0, 0, 2), ray3.findClosestPoint(points), "TC03: The closest point is the last point in the list");

        // TC04: The list is empty
        points = List.of();
        assertNull(ray3.findClosestPoint(points), "TC04: The list is empty");
    }
}