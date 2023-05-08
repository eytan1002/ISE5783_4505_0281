package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Random;

import static primitives.Util.isZero;

/**

 The Camera class represents a viewpoint of the geometric world through a view plane, which acts as a picture plane.
 The camera captures the geometric world and produces graphic views of objects using the view plane and ray-object intersections.
 The rays converge based on the location of the pixel centers on the view plane.
 The camera has directions to the right, up, and front (relative to the original x,y,z axis), all of which are orthogonal to each other.
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
        this.vRight = this.vTo.crossProduct(this.vUp);// vRight is orthogonal to vTo and vUp. and points to the right side of the camera
    }

    /**
     * @param distance - distance from VP
     * @return the camera object after setting its distance from VP
     */
    public Camera setVPDistance(int distance) {
        this.cDistance = distance;
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
     * @param nX amount of columns - line's width
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
