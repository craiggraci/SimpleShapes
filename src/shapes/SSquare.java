/*
 * Simple Square Class
 */

package shapes;

/**
 *
 * @author blue
 */

public class SSquare extends SShape implements SSquareADT {

    private double side;

    /**
     * Creates a new SSquare instance.
     * @param s the side length of the square
     */
    public SSquare(double s) {
        this.side = s;
    }

    /**
     * Computes the side length of the square.
     * @return the side length of the square
     */
    public double side() {
        return side;
    }

    /**
     * Computes the area of the square.
     * @return the area of the square
     */
    public double area() {
        return Math.pow(side,2);
    }

    /**
     * Computes the perimeter of the square.
     * @return the perimenter of the square
     */
    public double perimeter() {
        return side * 4;
    }

    /**
     * Set the side of the square
     * @param s the value to which the side will be bound
     */
    public void resetSide(int s) {
        side = s;
    }



    /**
     * Computes the diagonal of the square.
     * @return the diagonal of the square
     */
    public double diagonal() {
        return Math.sqrt(Math.pow(side,2)+Math.pow(side,2));
    }

    /**
     * Expand the square by adding the given amount to the side.
     * @param a is the amount by which the side is increased
     */
    public void expand(double a) {
        side = side + a;
    }

    /**
     * Shrink the square by adding the given amount to the side.
     * @param a is the amount by which the side is decreased
     */
    public void shrink(double a) {
        side = side - a;
    }

    /**
     * Return the largest circle which is bounded by this square.
    */

    public SCircle inscribingCircle() {
        double radius = (double)( side / 2.0 );
        return new SCircle(radius);
    }

    /**
     * Return the smallest circle which bounds this square.
    */

    public SCircle circumscribingCircle() {
        double radius = (double)( diagonal() / 2.0 );
        return new SCircle(radius);
    }

    /**
     * Computes a textual representation of the square.
     * @return a textual representation of the square
     */
    @Override
    public String toString() {
        return "<Square: side=" + side + ">";
    }

}

