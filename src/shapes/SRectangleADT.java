/*
 * Abstract Data Type for a simple Rectangle class.
 */

package shapes;

/**
 *
 * @author blue
 */
public interface SRectangleADT {
    public double height();
    public double width();
    public double area();
    public double perimeter();
    public double diagonal();
    public void expand(double h, double w);
    public void shrink(double h, double w);
    @Override
    public String toString();
}
