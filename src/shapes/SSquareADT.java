/*
 * Abstract Data Type for a simple Square Class
 */

package shapes;

/**
 *
 * @author blue
 */
public interface SSquareADT {
    public SCircle circumscribingCircle();
    public SCircle inscribingCircle();
    public double side();
    public double area();
    public double perimeter();
    public double diagonal();
    public void resetSide(int s);
    public void expand(double amount);
    public void shrink(double amount);
    @Override
    public String toString();
}
