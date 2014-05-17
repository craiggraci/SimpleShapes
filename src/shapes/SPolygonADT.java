/*
 * Abstract Data Type for a simple Polygon class.
 */

package shapes;

/**
 *
 * @author blue
 */
public interface SPolygonADT {
    public int degree();
    public double side();
    public void inc();
    public void dec();
    public void incSide();
    public void decSide();
    public void resize(int s);
    public double area();
    public double perimeter();
    public SCircle circumscribingCircle();
    public SCircle inscribingCircle();
    @Override
    public String toString();
}
