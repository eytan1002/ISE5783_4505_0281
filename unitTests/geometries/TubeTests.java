package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class TubeTests {

    /**
     * Test method for {@link geometries.Tube#getNormal(Point point)}.
     */

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Tube t1 = new Tube(1, new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)));
        Tube t2 = new Tube(4, new Ray(new Point(1, 1, 1), new Vector(1, 0, 0)));
        assertTrue(t1.getNormal(new Point(1, 1, 0)).equals(new Vector(0, 1, 0)));
        assertTrue(t1.getNormal(new Point(2, 0, 1)).equals(new Vector(0, 0, 1)));
        assertTrue(t1.getNormal(new Point(2, 1, 0)).equals(new Vector(0, 1, 0)));
        assertTrue(t1.getNormal(new Point(-2, 1, 0)).equals(new Vector(0, 1, 0)));
        assertTrue(t2.getNormal(new Point(2, 5, 1)).equals(new Vector(0, 1, 0)));
        assertTrue(t2.getNormal(new Point(3, 1, 5)).equals(new Vector(0, 0, 1)));
        assertTrue(t2.getNormal(new Point(3, 5, 1)).equals(new Vector(0, 1, 0)));

        // ============ Boundry value Tests ==============
        // vec P-P0 orthogonal to V: expect get the same vector
        assertTrue(t1.getNormal(new Point(0, 1, 0)).equals(new Vector(0, 1, 0)));
        assertTrue(t1.getNormal(new Point(0, 0, 1)).equals(new Vector(0, 0, 1)));

    }


}