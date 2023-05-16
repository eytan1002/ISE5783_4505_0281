package renderer;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration tests of Camera Ray construction with Ray-Geometry intersections
 *
 * @author Ori Perlmutter, Eitan Kaantman
 */

class CameraIntegrationTest {
    static final Point ZERO_POINT = new Point(0, 0, 0);

    /**
     * Test helper function for intersections count
     *
     * @param cam camera for the test
     * @param geo 3D body to test the integration of the camera with
     */
    private int countIntersections(Camera cam, Intersectable geo) {
        int counter = 0;
        List<Point> allpoints = null;
        cam.setVPSize(3, 3);
        cam.setVPDistance(1);
        int nX = 3;
        int nY = 3;
        //view plane 3X3 (WxH 3X3 & nx,ny =3 => Rx,Ry =1)
        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                List<Point> intersections = geo.findIntersections(cam.constructRay(nX, nY, j, i));
                //adding the amount of intersections
                if (intersections != null) {
                    if (allpoints == null) {
                        allpoints = new LinkedList<>();
                    }
                    allpoints.addAll(intersections);
                }
                if (intersections != null)
                    counter += intersections.size();
            }
        }
        return counter;
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Sphere intersections
     */

    @Test
    public void cameraRaySphereIntegration() {
        Camera cam1 = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Small Sphere 2 points
        assertEquals(2, countIntersections(cam1, new Sphere(1, new Point(0, 0, -3))));

        // TC02: Big Sphere 18 points
        assertEquals(18, countIntersections(cam2, new Sphere(2.5, new Point(0, 0, -2.5))));

        // TC03: Medium Sphere 10 points
        assertEquals(10, countIntersections(cam2, new Sphere(2, new Point(0, 0, -2))));

        // TC04: Inside Sphere 9 points
        assertEquals(9, countIntersections(cam2, new Sphere(4, new Point(0, 0, -1))));

        // TC05: Beyond Sphere 0 points
        assertEquals(0, countIntersections(cam1, new Sphere(0.5, new Point(0, 0, 1))));
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void cameraRayPlaneIntegration() {
        Camera cam = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Plane against camera 9 points
        assertEquals(9, countIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 0, 1))));

        // TC02: Plane with small angle 9 points
        assertEquals(9, countIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 2))));

        // TC03: Plane parallel to lower rays 6 points
        assertEquals(6, countIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1))));

        // TC04: Beyond Plane 0 points
        assertEquals(0, countIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, -1, 0))));
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Triangle intersections
     */
    @Test
    public void cameraRayTriangleIntegration() {
        Camera cam = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Small triangle 1 point
        assertEquals(1, countIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -1, -2))));

        // TC02: Medium triangle 2 points
        assertEquals(2, countIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2))));
    }

    @Test
    public void cameraRayGeometriesIntegration() {
        Camera cam = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0));
        Geometries geometries = new Geometries(
                new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2)),  // 2
                new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), // 6
                new Sphere(0.5, new Point(0, 0, 1)), // 0
                new Sphere(1, new Point(0, 0, -3)) // 2
        );
        assertEquals(10, countIntersections(cam, geometries));
    }
}
