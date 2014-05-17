/*
 * Simple Rectangle class.
 */

package shapes;

/**
 *
 * @author blue
 */

public class SRectangle extends SShape implements SRectangleADT {

    private double height;
    private double width;

    /**
     * Creates a new SRectangle instance.
     * @param h the height of the rectangle
     * @param w the width of the rectangle
     */
    public SRectangle(double h, double w) {
        this.height = h;
        this.width = w;
    }

    /**
     * Computes the height of the rectangle.
     * @return the height of the rectangle
     */
    public double height() {
        return height;
    }

    /**
     * Computes the width of the rectangle.
     * @return the width of the rectangle
     */
    public double width() {
        return width;
    }

    /**
     * Computes the area of the rectangle.
     * @return the area of the rectangle
     */
    public double area() {
        return height * width;
    }

    /**
     * Computes the perimeter of the rectangle.
     * @return the perimeter of the rectangle
     */
    public double perimeter() {
        return 2 * ( height + width) ;
    }

    /**
     * Computes the diagonal of the rectangle.
     * @return the diagonal of the rectangle
     */
    public double diagonal() {
        return Math.sqrt(Math.pow(height,2)+Math.pow(width,2));
    }

    /**
     * Expands the rectangle by increasing its height and its width.
     * @param h is the amount added to the height of the rectangle
     * @param w is the amount added to the width of the rectangle
     */
    public void expand(double h, double w) {
        height = height + h;
        width = width + w;
    }

    /**
     * Shrinks the rectangle by decreasing its height and its width.
     * @param h is the amount subtracted from the height of the rectangle
     * @param w is the amount subtracted from the width of the rectangle
     */
    public void shrink(double h, double w) {
        height = height - h;
        width = width - w;
    }

    /**
     * Set the height of the rectangle
     * @param h the value to which the height will be bound
     */
    public void resetHeight(int h) {
        height = h;
    }

    /**
     * Set the height of the rectangle
     * @param w the value to which the width will be bound
     */
    public void resetWidth(int w) {
        width = w;
    }



    /**
     * Computes a textual representation of the rectangle.
     * @return a textual representation of the rectangle
     */
    @Override
    public String toString() {
        return "<Rectangle: height=" + height + " width=" + width + ">";
    }

}
