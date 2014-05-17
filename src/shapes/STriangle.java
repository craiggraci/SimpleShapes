/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shapes;

/**
 *
 * @author blue
 */

/**
 * An STriangle is modeled in terms of its three sides -- sideA,
 * sideB, and sideC.
 */

public class STriangle extends SShape implements STriangleADT {

    private double sideA;
    private double sideB;
    private double sideC;

    /**
     * Create an equilateral triangle.
     * @param d the length of each side.
     */
    public STriangle (double d) {
        sideA = d;
        sideB = d;
        sideC = d;
    }

    /**
     * Create an isosceles triangle.
     * @param same the length of two of the sides.
     * @param diff the length of the "odd" side.
     */
     public STriangle (double same, double diff) {
         sideA = same;
         sideB = diff;
         sideC = same;
     }

     /**
      * Create an irregular triangle.
      * @param a the length of sideA.
      * @param b the length of sideB.
      * @param c the length of sideC.
      */
      public STriangle (double a, double b, double c) {
          sideA = a;
          sideB = b;
          sideC = c;
      }

    public double sideA() {
        return sideA;
    }

    public double sideB() {
        return sideB;
    }

    public double sideC() {
        return sideC;
    }

    public double area() {
        double a = sideA; double b = sideB; double c = sideC;
        double s = ( ( a + b + c ) / 2 );
        double t = ( s * ( s - a ) * ( s - b ) * ( s - c ) );
	return (double)Math.sqrt(t);
    }

    public double perimeter() {
        return ( sideA + sideB + sideC );
    }

    @Override
    public String toString() {
        return "< Triangle with SIDEA=" + sideA +
                " SIDEB= " + sideB +
                " SIDEC= " + sideC + ">";
    }

}
