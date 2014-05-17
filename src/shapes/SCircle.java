/*
 * Simple Circle class.
 */

package shapes;

import math.Conversions;

/**
 *
 * @author blue
 */

public class SCircle extends SShape implements SCircleADT {

    // Declare the instance variable
    private double radius;

    /**
     * Creates a new SCircle instance.
     * @param r the radius of the circle
     */
    public SCircle(double r) {
        this.radius = r;
    }

    /**
     * Computes the radius of the circle.
     * @return the radius of the circle
     */
    public double radius() {
        return radius;
    }

    /**
     * Set the radius of the circle
     * @param r the value to which the radius will be bound
     */
    public void resetRadius(int r) {
        radius = r;
    }

    /**
     * Computes the area of the circle.
     * @return the area of the circle
     */
    public double area() {
        return Math.PI * Math.pow(radius,2);
    }

    /**
     * Computes the perimeter of the circle.
     * @return the perimeter of the circle
     */
    public double perimeter() {
        return Math.PI * diameter();
    }

    /**
     * Computes the diameter of the circle.
     * @return the diameter of the circle
     */
    public double diameter() {
        return radius*2;
    }

    /**
     * Expand the circle by a given amount.
     * @param a is the amount by which the radius is increased
     */
    public void expand(double a) {
        //System.out.println("in expand a =" + a);
        radius = radius + a;
    }

    /**
     * Shrink the circle by a given amount.
     * @param a is the amount by which the radius is decreased
     */
    public void shrink(double a) {
        //System.out.println("in shrink a =" + a);
        radius = radius - a;
    }

    /**
     * Return the square which has the property that the
     * midpoints of its sides each touch this circle.
    */

    public SSquare circumscribingSquare() {
        double side = diameter();
        return new SSquare(side);
    }

    /**
     * Return the square which has the property that
     * its corners touch the circle.
    */

    public SSquare inscribingSquare() {
        double keySide = (double)( diameter() / 2.0 );
        SSquare key = new SSquare(keySide);
        double side = key.diagonal();
        return new SSquare(side);
    }

/**
 * Return the regular polygon of degree equal to the
 * given <tt>int</tt> which has the property that the
 * midpoints of its sides each touch this circle.
 * @param d number of sides of the new polygon
 */

            public SPolygon circumscribingPolygon(int d)
            {
               double r = radius();
               double angleD = (double)( ( 360.0 / d ) / 2.0 );
               double angleR = (double)Conversions.cvtDegToRad(angleD);
               double s = (double)( ( Math.tan(angleR) * r ) * 2.0 );
               return new SPolygon(d,s);
            }

/**
 * Return the regular polygon of degree equal to the
 * given <tt>int</tt> which has the property that its
 * corners touch this circle.
 * @param d number of sides of the new polygon
 */

            public SPolygon inscribingPolygon(int d)
            {
               double r = radius();
               double angleD = (double)( ( 360.0 / d ) / 2.0 );
               double angleR = (double)Conversions.cvtDegToRad(angleD);
               double s = (double)( ( Math.sin(angleR) * r ) * 2.0 );
               return new SPolygon(d,s);
            }

            private double square( double f )
            {
               return ( f * f );
            }


    /**
     * Computes a textual representation of the circle.
     * @return a textual representation of the circle
     */
    @Override
    public String toString() {
        return "<Circle: radius=" + radius + ">";
    }

    /**
     * Set the radius of the circle to the value provided.
     * @param r the new radius of the circle
     */
    public void setRadius(int r) {
        radius = r;
    }

}
