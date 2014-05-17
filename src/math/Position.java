/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package math;

/**
 *
 * @author blue
 */
public class Position {

    private double x;
    private double y;

    public Position() {
               x = 0.0;
               y = 0.0;
            }

    public Position(double xVal, double yVal) {
               x = xVal;
               y = yVal;
            }

          // instance variable referencers
         // - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

            public double x()
            {
               return x;
            }

            public double y()
            {
               return y;
            }


         // instance methods
         // - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

            public double distance(Position p)
            {
               double deltax = x - p.x();
               double deltay = y - p.y();
               double deltaXsquared = deltax * deltax;
               double deltaYsquared = deltay * deltay;
               double sum = deltaXsquared + deltaYsquared;
               return (double)Math.sqrt(sum);
            }

            public boolean equals(Position p)
            {
               boolean result = ( this.x() == p.x() ) && ( this.y() == p.y() );
               return result;
            }

            public void print()
            {
               System.out.print("(");
               System.out.print(x);
               System.out.print(",");
               System.out.print(y);
               System.out.print(")");
            }

            public void println()
            {
               print();
               System.out.println();
            }

            public void set(double newx, double newy)
            {
               x = newx;
               y = newy;
            }

}
