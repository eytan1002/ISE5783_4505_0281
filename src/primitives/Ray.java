package primitives;

public class Ray {
 final Point p0;
 final Vector dir;

    public Ray(Vector v, Point p) {
        dir = v.normalize();
        p0 = p;
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Ray other)
            return this.p0.xyz.equals(other.p0)&&this.dir.equals(other.dir);
        return false;
    }
    @Override
    public String toString() {
        return p0.toString() + dir.toString();
    }

}
