package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Polygons
 * @author Ori perlmutter, Eitan Kantman.
 * */
class TriangleTests {
    /**
     * Test method for {@link geometries.Triangle#getNormal(Point)}.
     */
    @Test
    public void testGetNormal() {
        //TC01: Simple normal test
        Triangle triangle = new Triangle(new Point(0,0,0), new Point(1,0,0), new Point(0,1,0));
        Vector normal = triangle.getNormal(new Point(0,0,0));
        assertEquals(new Vector(0,0,1), normal);
        //making sure the normal size is 1
        assertEquals(1, normal.length(), 0.00000001, "Polygon's normal is not a unit vector");
    }
}