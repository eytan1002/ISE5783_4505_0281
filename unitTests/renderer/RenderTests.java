package renderer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import renderer.*;
import static java.awt.Color.YELLOW;
import static renderer.Json.parseSceneFromJson;

import com.google.gson.*;
import geometries.*;
import org.junit.jupiter.api.Test;

import lighting.AmbientLight;
import primitives.*;
import scene.Scene;

import java.io.FileNotFoundException;
import java.io.FileReader;

/** Test rendering a basic image
 * @author Dan */
public class RenderTests {

   /** Produce a scene with basic 3D model and render it into a png image with a
    * grid */
   @Test
   public void basicRenderTwoColorTest() {
      Scene scene = new Scene("Test scene")//
         .setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
                                           new Double3(1, 1, 1))) //
         .setBackground(new Color(75, 127, 90));
      scene.geometries.add(new Sphere(50d, new Point(0, 0, -100)),
                           new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
                           // left
                           new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100),
                                        new Point(-100, -100, -100)), // down
                           // left
                           new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
      // right
      Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPDistance(100) //
         .setVPSize(500, 500) //
         .setImageWriter(new ImageWriter("base render test", 1000, 1000))
         .setRayTracer(new RayTracerBasic(scene));

      camera.renderImage();
      camera.printGrid(100, new Color(YELLOW));
      camera.writeToImage();
   }

   // For stage 6 - please disregard in stage 5
   /** Produce a scene with basic 3D model - including individual lights of the
    * bodies and render it into a png image with a grid */
   // @Test
   // public void basicRenderMultiColorTest() {
   // Scene scene = new Scene("Test scene")//
   // .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2))); //
   //
   // scene.geometries.add( //
   // new Sphere(new Point(0, 0, -100), 50),
   // // up left
   // new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new
   // Point(-100, 100, -100))
   // .setEmission(new Color(GREEN)),
   // // down left
   // new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new
   // Point(-100, -100, -100))
   // .setEmission(new Color(RED)),
   // // down right
   // new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new
   // Point(100, -100, -100))
   // .setEmission(new Color(BLUE)));
   //
   // Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1,
   // 0)) //
   // .setVPDistance(100) //
   // .setVPSize(500, 500) //
   // .setImageWriter(new ImageWriter("color render test", 1000, 1000))
   // .setRayTracer(new RayTracerBasic(scene));
   //
   // camera.renderImage();
   // camera.printGrid(100, new Color(WHITE));
   // camera.writeToImage();
   // }



   /**
    * Test for JSON based scene, for bonus
    */
   @Test
   public void basicRenderJson() throws Exception {
       FileReader reader = new FileReader("jsonFileNameWrite");
       JsonElement json = JsonParser.parseReader(reader);
       JsonObject jsonObject = json.getAsJsonObject();
       GsonBuilder gsonBuilder = new GsonBuilder();
       Gson gson = gsonBuilder.create();
       //getting the scene's name, background and ambient light
       Scene scene = new Scene(jsonObject.get("name").getAsString())
               .setBackground(gson.fromJson(jsonObject.get("background"), Color.class))
               .setAmbientLight(gson.fromJson(jsonObject.get("ambientLight"), AmbientLight.class));

       //getting the geometries, each geometry is a json object.
       JsonArray jsonGeometries = jsonObject.get("geometries").getAsJsonArray();
       for (JsonElement jsonElement : jsonGeometries) {
           JsonObject jsonGeometry = jsonElement.getAsJsonObject();
           String type = jsonGeometry.get("type").getAsString();
           switch (type) {
               case "sphere":
                   JsonObject sphereObject = jsonGeometry.getAsJsonObject("sphere");
                   scene.geometries.add(gson.fromJson(sphereObject, Sphere.class));
                   break;
               case "triangle":
                   JsonObject triangleObject = jsonGeometry.getAsJsonObject("triangle");
                   scene.geometries.add(gson.fromJson(triangleObject, Triangle.class));
                   break;
               case "plane":
                   JsonObject planeObject = jsonGeometry.getAsJsonObject("plane");
                   scene.geometries.add(gson.fromJson(planeObject, Plane.class));
                   break;
               case "tube":
                   JsonObject tubeObject = jsonGeometry.getAsJsonObject("tube");
                   scene.geometries.add(gson.fromJson(tubeObject, Tube.class));
                   break;
               case "cylinder":
                   JsonObject cylinderObject = jsonGeometry.getAsJsonObject("cylinder");
                   scene.geometries.add(gson.fromJson(cylinderObject, Cylinder.class));
                   break;
               case "polygon":
                   JsonObject polygonObject = jsonGeometry.getAsJsonObject("polygon");
                   scene.geometries.add(gson.fromJson(polygonObject, Polygon.class));
                   break;
               default:
                   throw new Exception("Invalid type: " + type);
           }
       }

      // Continue with the rest of the code
      Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))
              .setVPDistance(100)
              .setVPSize(500, 500)
              .setImageWriter(new ImageWriter("Gson render test", 1000, 1000))
              .setRayTracer(new RayTracerBasic(scene));
      camera.renderImage();
      camera.printGrid(100, new Color(YELLOW));
      camera.writeToImage();
   }

}