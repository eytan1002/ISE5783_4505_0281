package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * Camera class represents a camera in 3D Cartesian coordinate system
 * with location and direction vectors
 * and constructs rays through pixels on view plane
 *
 * @author: Eytan Kaantman & Ori Perlmutter
 */
public class Camera {
    /**
     * _P0 - the camera location
     */
    private Point p0;

    /**
     * vTo - X vector
     */
    private Vector vTo;

    /**
     * _Vup - Y vector
     */
    private Vector vUp;


    /**
     * vRight - Z vector
     */
    private Vector vRight;

    /**
     * object's actual distance from the camera center
     */
    private int cDistance;

    /**
     * object's actual width
     */
    private double cWidth;

    /**
     * object's actual height
     */
    private double cHeight;

    private ImageWriter imageWriter;

    private RayTracerBase tracer;

    /**
     * @param p0  - camera location
     * @param vTo - X vector
     * @param vUp - Y vector
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp))) { // if vto and vup are not orthogonal
            throw new IllegalArgumentException("vto  and vup are not orthogonal");
        }
        //else
        this.p0 = p0;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        // vRight is orthogonal to vTo and vUp.
        // and points to the right side of the camera
        this.vRight = this.vTo.crossProduct(this.vUp);
    }


    /**
     * @param distance - distance from VP
     * @return the camera object after setting its distance from VP
     */
    public Camera setVPDistance(int distance) {
        this.cDistance = distance;
        return this;

    }

    public Camera setRayTracer(RayTracerBase tracer) {
        this.tracer = tracer;
        return this;
    }


    /**
     * setter for imageWriter
     *
     * @param imageWriter
     * @return the image writer for the camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * @param width, height: size of VP
     * @return the camera object after setting VP's size
     */
    public Camera setVPSize(double width, double height) {
        this.cWidth = width;
        this.cHeight = height;
        return this;
    }

    /**
     * @param nX amount of pixels in the horizontal
     * @param nY amount of lines - column's height
     * @param j  - pixel's line
     * @param i  - pixel's column
     * @return Ray constructed from camera to middle of pixel on VP
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // Calculate the center point of the view plane
        Point viewPlaneCenter = p0.add(vTo.scale(cDistance));

// Calculate the pixel ratios
        double pixelRatioX = cWidth / nX;
        double pixelRatioY = cHeight / nY;

// Calculate the coordinates of point (i,j) on the view plane
        Point Pij = viewPlaneCenter;

// Calculate the offsets for moving on the view plane
        double offsetY = -(i - (nY - 1) / 2.0) * pixelRatioY;
        double offsetX = (j - (nX - 1) / 2.0) * pixelRatioX;
// Apply the offsets to the view plane coordinates to get the final point (i,j)
        if (!isZero(offsetX)) {
            Pij = Pij.add(vRight.scale(offsetX));
        }
        if (!isZero(offsetY)) {
            Pij = Pij.add(vUp.scale(offsetY));
        }
// Calculate the vector from the camera's eye in the direction of point (i,j) on the view plane
        Vector Vij = Pij.subtract(p0);
        return new Ray(p0, Vij);
    }

    /**
     * checks if imageWriter and tracer are initialized
     */
    public Camera renderImage() {
        //check all the parameters are initialized
        if (imageWriter == null) {
            throw new MissingResourceException("Missing resource", ImageWriter.class.getName(), "");
        }
        if (tracer == null) {
            throw new MissingResourceException("Missing resource", RayTracerBase.class.getName(), "");
        }
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        // iterate over all the pixels in the view plane
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                // construct a ray from the camera to the middle of the pixel
                Color pixelColor = castRay(nX, nY, i, j);
                // color the pixel with the color of the closest intersection point
                imageWriter.writePixel(i, j, pixelColor);
            }
        }
        return this;
    }

    /**
     * Cast ray from camera in order to color a pixel
     *
     * @param nX   - resolution on X axis (number of pixels in row)
     * @param nY   - resolution on Y axis (number of pixels in column)
     * @param icol - pixel's column number (pixel index in row)
     * @param jrow - pixel's row number (pixel index in column)
     */
    private Color castRay(int nX, int nY, int icol, int jrow) {
        Ray ray = constructRay(nX, nY, icol, jrow);
        return tracer.traceRay(ray);
    }

    /**
     * the function prints a grid on the image
     *
     * @param interval - the interval between the lines
     * @param color    - the color of the lines
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null) {
            throw new MissingResourceException("Missing resource", ImageWriter.class.getName(), "");
        }
        // color the grid lines
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    /**
     * writes the image to the file using the image writer class
     */
    public void writeToImage() {
        if (imageWriter == null) {
            throw new MissingResourceException("Missing resource", ImageWriter.class.getName(), "");
        }
        imageWriter.writeToImage();//delegate to image writer
    }

    public int getcDistance() {
        return cDistance;
    }

    public double getcWidth() {
        return cWidth;
    }

    public double getcHeight() {
        return cHeight;
    }
}
