package primitivs;

public class Vector extends Point {

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector cannot be Vector(0,0,0)");
    }


    Vector(Double3 double3){
        super(double3);
        if (double3.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector cannot be Vector(0,0,0)");
    }



    public double length(){
        return Math.sqrt(lenghSquared());
    }

    private double lenghSquared(){
        double x = xyz.d1;
        double y = xyz.d2;
        double z = xyz.d3;

        return x*x + y*y + z*z;
    }

    public Vector normalize(){
        double len = length();
        return new Vector(xyz.d1/len,xyz.d2/len,xyz.d3/len);
    }

    public Vector add(Vector vector){
        if(this.xyz.d1==- vector.xyz.d1&&this.xyz.d2==- vector.xyz.d2&&this.xyz.d3==- vector.xyz.d3)
            throw new IllegalArgumentException("Vector cannot be Vector(0,0,0)");
        return new Vector(xyz.add(vector.xyz));
    }

    public Vector scale(double d){
        if (d == 0)
            throw new IllegalArgumentException("Vector cannot be Vector(0,0,0)");
        return new Vector(xyz.scale(d));
    }

    public double dotProduct(Vector vector){
        return this.xyz.d1 * vector.xyz.d1 + this.xyz.d2 * vector.xyz.d2 + this.xyz.d3 * vector.xyz.d3;
    }
    public Vector crossProduct(Vector vector) {
        double x = this.xyz.d2 * vector.xyz.d3 - this.xyz.d3 * vector.xyz.d2;
        double y = this.xyz.d3 * vector.xyz.d1 - this.xyz.d1 * vector.xyz.d3;
        double z = this.xyz.d1 * vector.xyz.d2 - this.xyz.d2 * vector.xyz.d1;
        if (x == 0 && y == 0 && z == 0)
            throw new IllegalArgumentException("Vector cannot be Vector(0,0,0)");
        return new Vector(x,y,z);
    }
    @Override
    public String toString() { return "(" + xyz.d1 + "," + xyz.d2 + "," + xyz.d3 + ")"; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector vector)) return false;
        return xyz.equals(vector.xyz);    }
}
