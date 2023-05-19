package renderer;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * RayTracerBasic is a basic ray tracer, it extends RayTracerBase and implements the traceRay function
 * @ author - Eytan Kantman and Ori Perlmuter
 */

public class RayTracerBasic extends RayTracerBase{

    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    /**
     *
     * @param ray - the ray to trace
     * @return the color of the closest point to the ray's starting point
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = scene.geometries.findIntersections(ray);
        Point closestPoint = ray.findClosestPoint(intersections);
        return closestPoint == null ? scene.background : calcColor(closestPoint);
    }

    /**
     *
     * @param point - the point to calculate the color for
     * @return - the background color for now
     */
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity(); //for now return the ambient light intensity
    }

}
