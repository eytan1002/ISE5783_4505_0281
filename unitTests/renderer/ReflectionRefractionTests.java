/**
 *
 */
package renderer;

import geometries.*;
import lighting.AmbientLight;
import lighting.LightSource;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

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
        Camera camera2 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);        // Rotate the camera by an angle

        camera2.cameraPosition(new Point(900, 100, 600), new Point(0, 0, 0), 180);


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
                new Sphere(19d, new Point(-50, 25, 30)).setEmission(new Color(BLUE)) //
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

//change in the radius - smaller creates a better picture.
        //list of lights:


        scene.getLights().add(new SpotLight(new Color(WHITE), new Point(60, 50, 50), new Vector(-0.6, -0.5, -0.5)) //
                .setKl(0.00001).setKq(0.000005).setRadius(5d));
        scene.getLights().add(new PointLight(new Color(WHITE), new Point(0, 0, 20)).setKl(0.00001).setKq(0.000005).setRadius(5d));
        scene.getLights().add(new PointLight(new Color(WHITE), new Point(-50, -20, 20)).setKl(0.00001).setKq(0.000005).setRadius(5d));


        //scene.getLights().add(new SpotLight(new Color(WHITE),new Point(50,-30,200),new Vector(-1,0,-1)).setKl(0.0004).setKq(0.0000006));
        //scene.getLights().add(new SpotLight(new Color(WHITE),new Point(-75,30,200),new Vector(1,0,-0.55)).setKl(0.0004).setKq(0.0000006));


        ImageWriter imageWriter = new ImageWriter("refraction4Objects_firstAngle", 600, 600);
        camera1.setImageWriter(imageWriter); //
        camera1.setRayTracer(new RayTracerBasic(scene)); //
        camera1.renderImage(); //
        camera1.writeToImage();

        ImageWriter imageWriter2 = new ImageWriter("refraction4Objects_secondAngle", 600, 600);
        ImageWriter imageWriter3 = new ImageWriter("refractionObjects_Rotated", 600, 600);
        ImageWriter imageWriter4 = new ImageWriter("refractionObjects_Soft_Shadowed_Rad5", 600, 600);

        //  camera2.moveCamera(0,0,25);
        //  camera2.rotateCamera(10);

        camera2.setImageWriter(imageWriter2); //
        camera2.setRayTracer(new RayTracerBasic(scene)); //
        camera2.renderImage(); //
        camera2.writeToImage();

        camera1.setImageWriter(imageWriter4); //
        camera1.setRayTracer(new RayTracerBasic(scene).setSoftShadow(true)); //
        camera1.renderImage(); //
        camera1.writeToImage();

        camera1.rotateCamera(20);
        camera1.setImageWriter(imageWriter3); //
        camera1.setRayTracer(new RayTracerBasic(scene)); //
        camera1.renderImage(); //
        camera1.writeToImage();


    }
    //test 2 - guy's tets

    /**
     * TODO: take inspiration from it and delete it
     * <p>
     * Produce a picture of two spheres and a cylinder - includes all effects
     * transparency and reflectiveness
     */
    @Test
    public void reflectionIntegrationTest() {
        List<LightSource> lights = new LinkedList<>();
        lights.add(new SpotLight(new Color(WHITE), new Point(50, -30, 200), new Vector(-1, 0, -1)).setKl(0.0004).setKq(0.0000006));
        lights.add(new SpotLight(new Color(WHITE), new Point(-75, 30, 200), new Vector(1, 0, -0.55)).setKl(0.0004).setKq(0.0000006));
        Scene scene = new Scene.SceneBuilder("Test Scene")
                .setAmbientLight(new AmbientLight(new Color(229, 204, 255), new Double3(.15)))
                .setGeometries(new Geometries(
                        new Sphere(25d, new Point(-15, -10, -10)).setEmission(new Color(200, 50, 0))
                                .setMaterial(new Material().setKs(0.25).setKd(0.25).setShininess(80).setKt(0.5)),
                        new Polygon(new Point(-250, -90, -70), new Point(0, 50, -60), new Point(200, -90, -70))
                                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                        new Polygon(new Point(-150, 90, -65), new Point(-150, 90, 50), new Point(150, 160, 50), new Point(150, 160, -65))
                                .setEmission(new Color(20, 20, 20)) //
                                .setMaterial(new Material().setKr(0.45)),
                        new Sphere(7, new Point(-6, 2, -2)).setMaterial(new Material().setKs(0.25).setKd(0.25)).setEmission(new Color(0, 255, 0)),


                        new Cylinder(10d, new Ray(new Point(75, -8, -88), new Vector(-0.2, -0.3, 1)), 130d)
                                .setEmission(new Color(102, 0, 204))
                                .setMaterial(new Material().setKs(0.35).setKd(0.25).setKt(0.2).setKr(0).setShininess(10))))

                .setLights(lights)
                .setBackground(new Color(0, 102d, 102d))
                .build();

        ImageWriter imageWriter = new ImageWriter("aaaaabbbbrefractionIntegrationnn4", 600, 600);
        Camera camera = new Camera(new Point(0, -1200, 100), new Vector(0, 1, -0.1), new Vector(0, 0.1, 1)); //
        camera.setVPSize(200, 200);
        camera.setVPDistance(1000);
        camera.setImageWriter(imageWriter); //
        camera.setRayTracer(new RayTracerBasic(scene));

        camera.renderImage(); //
        camera.writeToImage();
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
