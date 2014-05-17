/*
 * Abstract Data Type for a simple Circle class.
 */

package shapes;

/**
 *
 * @author blue
 */
public interface SCircleADT {
    public double radius();
    public void setRadius(int r);
    public double diameter();
    public double area();
    public double perimeter();
    public void expand(double amount);
    public void shrink(double amount);
    public SSquare inscribingSquare();
    public SSquare circumscribingSquare();
    public SPolygon inscribingPolygon(int d);
    public SPolygon circumscribingPolygon(int d);
    @Override
    public String toString();
}
