package renderer;

import geometries.Geometry;
import geometries.Intersectable.*;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import java.util.List;

/**
 * RayTracerBasic is a basic ray tracer, it extends RayTracerBase and implements the traceRay function
 *
 * @ author - Eytan Kantman and Ori Perlmuter
 */

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    /**
     * @param ray - the ray to trace
     * @return the color of the closest point to the ray's starting point
     */
    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.getGeometries().findGeoIntersections(ray);
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return closestPoint == null ? scene.getBackground() : calcColor(closestPoint, ray);
    }

    /**
     * @param intersection - the intersection point
     * @return - the background color for now
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
        return scene.getAmbientLight().getIntensity().add(calcLocalEffects(intersection, ray)); //for now return the ambient light intensity
    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }

    private Double3 calcDiffusive(Material material, double nl) {
        return material.getKd().scale(nl < 0 ? -nl : nl);
    }

    /**
     * Calculates the specular reflection component of a material at a given intersection point.
     *
     * @param material The material of the intersected geometry
     * @param n        The surface normal
     * @param l        The light direction
     * @param nl       The dot product between the surface normal and the light direction
     * @param v        The view direction
     * @return The color of the specular reflection
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double max = -r.dotProduct(v);
        return alignZero(max) > 0 ? material.getKs().scale(Math.pow(max, material.getShininess())) : Double3.ZERO;
    }
}
