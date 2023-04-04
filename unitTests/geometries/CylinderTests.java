package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import org.junit.jupiter.api.Test;
import static primitives.Util.isZero;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Cylinder Class
 * @author Ori Perlmutter, Eitan Kaantman
 */
class CylinderTests {
    @Test
    void TestGetNormal() {

        Cylinder c1 = new Cylinder(1, new Ray(new Vector(1, 0, 0),new Point(0, 0, 0)), 5);
        Cylinder c2 = new Cylinder(4, new Ray(new Vector(1, 0, 0),new Point(1, 1, 1)), 5);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the getNormal function works correctly
        assertTrue(c1.getNormal(new Point(1, 1, 0)).equals(new Vector(0, 1, 0)));
        assertTrue(c1.getNormal(new Point(2, 0,1 )).equals(new Vector(0, 0, 1)));
        assertTrue(c1.getNormal(new Point(2, 1, 0)).equals(new Vector(0, 1, 0)));
        assertTrue(c1.getNormal(new Point(-2, 1, 0)).equals(new Vector(0, 1, 0)));
        assertTrue(c2.getNormal(new Point(2, 5, 1)).equals(new Vector(0, 1, 0)));
        assertTrue(c2.getNormal(new Point(3, 1, 5)).equals(new Vector(0, 0, 1)));
        assertTrue(c2.getNormal(new Point(3, 5, 1)).equals(new Vector(0, 1, 0)));

            // 2 TC’s on the bases (1 TC for each base)


        // =============== Boundary Values Tests ==================

        // Test normal to the base edge (force decision which normal to use)

     //   assertTrue(c1.getNormal(new Point(1, 0, 0)).equals(new Vector(0, -1, 0)));
      //  assertTrue(c2.getNormal(new Point(5, 1, 1)).equals(new Vector(0, 0, 1)));

        assertTrue(c1.getNormal(new Point(0, 1, 0)).equals(new Vector(0, 1, 0)));
        assertTrue(c1.getNormal(new Point(0,0,1)).equals(new Vector(0, 0, 1)));
        // Test normal to the center of the top base
        assertTrue(c1.getNormal(new Point(0, 0, 0)).equals(new Vector(1, 0, 0)));
        assertTrue(c2.getNormal(new Point(1, 5, 1)).equals(new Vector(1, 0, 0)));

        // Test normal to the center of the bottom base
        assertTrue(c1.getNormal(new Point(0, 0, 5)).equals(new Vector(1, 0, 0)));
        assertTrue(c2.getNormal(new Point(6, 1, 1)).equals(new Vector(1, 0, 0)));
        
    }
    }
