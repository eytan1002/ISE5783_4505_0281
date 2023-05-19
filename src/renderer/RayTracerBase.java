package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBase is an abstract class for all ray tracers
 * It has a scene field
 *
 * @ author - Eytan Kantman and Ori Perlmuter
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * @param scene - the scene to render
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * @param ray - the ray to trace
     * @return color
     */
    public abstract Color
    traceRay(Ray ray);
}
