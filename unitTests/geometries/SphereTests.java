package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class SphereTests {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point point)}.
     */
    @Test
    public void getNormalTest() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere s1 = new Sphere(4, new Point(0, 0, 0));
        Sphere s2 = new Sphere(1, new Point(1, 1, 1));

        assertTrue(s1.getNormal(new Point(0, 0, 4)).equals(new Vector(0, 0, 1)));
        assertTrue(s1.getNormal(new Point(0, 0, -4)).equals(new Vector(0, 0, -1)));
        assertTrue(s1.getNormal(new Point(0, 4, 0)).equals(new Vector(0, 1, 0)));
        assertTrue(s1.getNormal(new Point(0, -4, 0)).equals(new Vector(0, -1, 0)));
        assertTrue(s1.getNormal(new Point(4, 0, 0)).equals(new Vector(1, 0, 0)));
        assertTrue(s1.getNormal(new Point(-4, 0, 0)).equals(new Vector(-1, 0, 0)));
        assertTrue(s2.getNormal(new Point(1, 1, 0)).equals(new Vector(0, 0, -1)));
        assertTrue(s2.getNormal(new Point(0, 1, 1)).equals(new Vector(-1, 0, 0)));
        assertTrue(s2.getNormal(new Point(1, 0, 1)).equals(new Vector(0, -1, 0)));
    }
}