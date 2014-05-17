/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shapes;

import math.Conversions;

/**
 *
 * @author blue
 */

/**
 *  A 'regular polygon' is modeled in terms of its degree (number of
 *  sides) and its side length.
 */
public class SPolygon extends SShape implements SPolygonADT {

    private int degree;
    private double side;

    /**
     *  Create a polygon.
     *  @param d the degree of the polygon.
     *  @param s side length of the polygon.
     */
    public SPolygon (int d, double s) {
        degree = d;
        side = s;
    }

    /**
     * @return the degree of the polygon
     */
    public int degree() {
        return degree;
    }

    /**
     * @return the side of the polygon
     */
    public double side() {
        return side;
    }

    /**
     * Transform this polygon to a polygon of one additional degree.
     * "Morph it up!"
     */
    public void inc() {
        degree = degree + 1;
    }

    /**
     * Transform this polygon to a polygon of one less degree.
     * "Morph it down!"
     */
    public void dec() {
        degree = degree - 1;
    }

    /**
     * Increase the length of each side by 1 unit.
     */
    public void incSide() {
        side = side + 1;
    }

    /**
     * Decrease the length of each side by 1 unit.
     */
    public void decSide() {
        side = side - 1;
    }

    /**
     * Change the length of each side to the specified length
     * @param s is the length of each side.
     */
    public void resize(int s) {
        side = s;
    }

    /**
     * @return the area of the polygon.
     */
    public double area() {
         SCircle cc = circumscribingCircle();
         STriangle t = new STriangle(side,cc.radius(),cc.radius());
         return ( t.area() * degree );

    }

    /**
     * @return the perimeter of the polygon.
     */
    public double perimeter() {
        return ( degree * side );
    }

    /**
     * Write a textual description of the polygon to the standard output file.
     */


    @Override
    public String toString() {
         return "<Polygon with DEGREE=" + degree + " SIDE= " + side + ">";
    }

    /**
     * @return the circumscribing circle of this polygon.
     */
    public SCircle circumscribingCircle() {
         double hs = (double)( side / 2.0 );
         double angleD = (double)( ( 360.0 / degree ) / 2.0 );
         double angleR = (double)Conversions.cvtDegToRad(angleD);
         double r = (double)( hs / Math.sin(angleR) );
         return new SCircle(r);
    }

    /**
     * @return the inscribing circle of this polygon.
     */
    public SCircle inscribingCircle() {
        double hs = (double)( side / 2.0 );
        double angleD = (double)( ( 360.0 / degree ) / 2.0 );
        double angleR = (double)Conversions.cvtDegToRad(angleD);
        double r = (double)( hs / Math.tan(angleR) );
        return new SCircle(r);
    }

}
