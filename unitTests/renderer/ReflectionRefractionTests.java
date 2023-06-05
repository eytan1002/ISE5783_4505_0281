/**
 *
 */
package renderer;

import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

/**
 * Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Scene scene = new Scene.SceneBuilder("Test scene").build();
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.getGeometries().add( //
                new Sphere(50d, new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(25d, new Point(0, 0, -50)).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.getLights().add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));

        camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)); //
        camera.setRayTracer(new RayTracerBasic(scene)); //
        camera.renderImage(); //
        camera.writeToImage();
    }

    /**
     * produce a picture with 10 objects and spot light
     */
    @Test
    public void fourObjects() {
        Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        //Camera camera2 = new Camera(new Point(20, 25, 26), new Vector(-2, 0, 0), new Vector(0, 0, 2)) //
        //        .setVPSize(200, 200).setVPDistance(70);
        Scene scene = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(WHITE), 0.15)).build();
        scene.getGeometries().add( //
                new Plane(new Point(58, 40, 1), new Point(1, 13, 1), new Point(-18, 20, 1)) //
                        .setEmission(new Color(darkGray)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKr(1)),
                new Triangle(new Point(-50, -50, 10), new Point(50, -50, 10),
                        new Point(25, 25, 14)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKt(0.45).setKs(0.5).setShininess(60)),
                new Sphere(5d, new Point(-50, -50, 10)).setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKr(1)),
                new Sphere(5d, new Point(50, -50, 10)).setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKr(0.8)),
                new Sphere(5d, new Point(25, 25, 14)).setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKr(0.8)),
                new Triangle(new Point(-50, -50, 10), new Point(50, -50, 10),
                        new Point(25, 0, 20)).setEmission(new Color(BLACK)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKr(0.8)),
                new Triangle(new Point(-50, -50, 10), new Point(25, 25, 14),
                        new Point(25, 0, 20)).setEmission(new Color(BLACK)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(1)),
                new Sphere(24d, new Point(-50, 25, 0)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKr(0.6)),
                new Triangle(new Point(-80, 70, 0), new Point(-80, -70, 0),
                        new Point(-90, 50, 20)).setEmission(new Color(BLACK)).
                        setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10).setKr(0.2)), //
                new Triangle(new Point(-80, 70, 0), new Point(60, 70, 0),
                        new Point(-90, 50, 20)).setEmission(new Color(BLACK)).
                        setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10).setKr(0.2)), //
                new Sphere(5d, new Point(-50, 25, 0)).setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKr(1)),
                new Sphere(5d, new Point(-50, 25, 4)).setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKr(1)));


        scene.getLights().add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 50), new Vector(-0.6, -0.5, -0.5)) //
                .setKl(0.00001).setKq(0.000005));

        ImageWriter imageWriter = new ImageWriter("refraction4Objects_firstAngle", 600, 600);
        camera1.setImageWriter(imageWriter); //
        camera1.setRayTracer(new RayTracerBasic(scene)); //
        camera1.renderImage(); //
        camera1.writeToImage();
/*
       ImageWriter imageWriter2 = new ImageWriter("refraction4Objects_secondAngle", 600, 600);
       camera2.setImageWriter(imageWriter2); //
       camera2.setRayTracer(new RayTracerBasic(scene)); //
       camera2.renderImage(); //
       camera2.writeToImage();

*/
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //
        Scene scene = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1)).build();

        scene.getGeometries().add( //
                new Sphere(400d, new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                .setKt(new Double3(0.5, 0, 0))),
                new Sphere(200d, new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(670, 670, 3000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))));

        scene.getLights().add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setKl(0.00001).setKq(0.000005));

        ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
        camera.setImageWriter(imageWriter); //
        camera.setRayTracer(new RayTracerBasic(scene)); //
        camera.renderImage(); //
        camera.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a
     * partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);
        Scene scene = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(WHITE), 0.15)).build();
        scene.getGeometries().add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Sphere(30d, new Point(60, 50, -50)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

        scene.getLights().add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
        camera.setImageWriter(imageWriter); //
        camera.setRayTracer(new RayTracerBasic(scene)); //
        camera.renderImage(); //
        camera.writeToImage();
    }
}
