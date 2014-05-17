/*
 * Painter ADT.
 */

package painter;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import shapes.SCircle;
import shapes.SPolygon;
import shapes.SRectangle;
import shapes.SSquare;

/**
 * Painter ADT.
 * @author blue
 */
public interface PainterADT {
    public void setColor(Color c);
    public void wash();
    public void paint(SCircle c);
    public void paint(SSquare s);
    public void paint(SRectangle r);
    public void paint(SPolygon p);
    public void draw(SCircle c);
    public void draw(SSquare s);
    public void draw(SRectangle r);
    public void draw(SPolygon p);
    public void move();
    public void moveTo(Point2D.Double p);
    public void moveToCenter();
    public void drawLineTo(Point2D.Double p);
    public void drawLineToI(Point2D.Double p);
    public void mfd(double d);
    public void mbk(double d);
    public void dfd(double d);
    public void dbk(double d);
    public void tr();
    public void tl();
    public void ta();
    public void tr(double d);
    public void tl(double d);
    public Point2D.Double random();
    public Point2D.Double position();
    public Point2D.Double center();
    public double heading();
    public void setBrushWidth(int w);
    public SRectangle frame();
    public void paintFrame(Color c, int w);
    public Rectangle2D.Double getBoundingRectangle();
}
