/*
 * Painter class.
 */

package painter;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import math.Position;
import math.Predicates;
import shapes.SCircle;
import shapes.SPolygon;
import shapes.SRectangle;
import shapes.SSquare;

/**
 * Painter class designed to work in conjunction with the simple Shapes
 * class.  There are two ways to obtain a painter.  If you wish to simply
 * establish a stand alone frame into which the painter will work, the
 * following sort of construction will serve to create the frame with the
 * given title and a content pane of the size specified by width and height.
 * <p>
 * <pre>
 *     Painter p = new Painter("Doodle World",500,500);
 * </pre>
 * <p>
 * If you want to paint another sort of component, you will have to
 * establish the component and send it along to the other constructor.
 * Something like this.
 * <p>
 * <pre>
 *     private static Painter painter() {
 *        JFrame jf = new JFrame("Doodle World");
 *        jf.setSize(500,522); // the content pain is 22 less in height
 *        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 *        jf.setVisible(true);
 *        Painter painter = new Painter(jf.getContentPane());
 *        return painter;
 *     }
 *
 *     Painter p = new Painter();
 * </pre>
 * <p>
 * Once the painter has been established, the rest is straightforward.
 *
 * @author blue
 */
public class Painter extends JPanel implements PainterADT {

    Point2D.Double center;
    public Point2D.Double location = new Point2D.Double(0,0);
    String direction = "north";
    public Color color = Color.black;
    public int brushWidth = 4;
    BufferedImage image = null;
    Graphics2D gir = null;
    public int painterHeight;
    public int painterWidth;
    Stack<State> state = new Stack<State>();
    Container container;
    public JFrame theFrame;

    /**
     * Create a painter whose "canvas" is a JPanel housed in the given
     * container.
     * @param c is the container which houses the painters "canvas"
     */
    public Painter(Container c) {
//        if ( c == null ) {
//            System.out.println("null .........\n") ;
//        } else {
//            System.out.println("not null ........\n");
//        }
        container = c;
        painterHeight = c.getHeight();
        painterWidth = c.getWidth();
        //System.out.println("painterHeight = " + painterHeight);
        //System.out.println("painterWidth = " + painterWidth);
        setSize(c.getWidth(),c.getHeight());
//        c.setLayout(new BorderLayout());
//        c.add(this,BorderLayout.CENTER);
        c.add(this);
        initializeImage();

    }

    public void redo(Color cc, int h, int w) {
        setBackground(Color.red);
        container.setBounds(500,500,500,500);
        setSize(500,500);
        theFrame.setSize(500,500);
        painterWidth = 500;
        painterHeight=500;
        initializeImage();
    }

    public BufferedImage image() {
        return image;
    }

    /**
     * Create a Painter whose canvas is a JPanel placed in the content pane
     * of a newly created JFrame.
     * @param t is the title of the JFrame to be created
     * @param h is the height of the content pane of the JFrame
     * @param w is the width of the content pane of the JFrame
     */
    public Painter(String t,int h, int w) {
        final JFrame jf = new JFrame(t);
        theFrame = jf;
        // jf.setSize(h+22,w);
        jf.setSize(h,w+22);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = jf.getContentPane();
        container = c;
        painterHeight = c.getHeight();
        painterWidth = c.getWidth();
        setSize(c.getWidth(),c.getHeight());
        c.add(this);
        initializeImage();
    }

    public Painter(String t,int h, int w, int x, int y) {
        final JFrame jf = new JFrame(t);
        theFrame = jf;
        jf.setSize(h+22,w);
        jf.setLocation(x,y);
        jf.setVisible(true);
        Container c = jf.getContentPane();
        container = c;
        painterHeight = c.getHeight();
        painterWidth = c.getWidth();
        setSize(c.getWidth(),c.getHeight());
        c.add(this);
        initializeImage();
    }


    /**
     * Update the component
     * @param g is the graphics area
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    /**
     * Paint the component
     * @param g is the graphics area
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        if ( image == null ) {
            initializeImage();
        }
        g2.drawImage(image,0,0,this);
    }

    public void initializeImage() {
        establishCenterPoint();
        establishImage();
        wash();
    }

    public void initializeImage2() {
        establishCenterPoint();
        establishImage();
        // wash();
    }

    /**
     * Compute a simple rectangle corresponding to the bounding rectangle
     * of the canvas.
     * @return a simple rectangle corresponding to the bounding recgangle
     * of the canvas
     */
    public Rectangle2D.Double getBoundingRectangle() {
        return new Rectangle2D.Double(0,0,painterWidth,painterHeight);
    }

    /**
     * Compute a simple rectangle corresponding to the bounding rectangle
     * of the canvas.
     * @return a simple rectangle corresponding to the bounding recgangle
     * of the canvas
     */
    public Rectangle2D.Double getBoundingSuperRectangle() {
        return new Rectangle2D.Double(-500,-500,painterWidth+500,painterHeight+500);
    }

    void establishCenterPoint() {
        int w = this.getWidth();
        int h = this.getHeight();
        int xCenter = (int) (w / 2.0);
        int yCenter = (int) (h / 2.0);
        center = new Point2D.Double(xCenter,yCenter);
        location = new Point2D.Double(xCenter,yCenter);
    }

    void establishImage() {
        int w = painterWidth;
        int h = painterHeight;
        image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        gir = image.createGraphics();
    }

    /**
     * Paint the entire "canvas" with the current color.
     */
    public void wash() {
        Point p = new Point(0,0);
        Dimension d = new Dimension(this.getWidth(),this.getHeight());
        Rectangle r = new Rectangle(p,d);
        gir.setPaint(Color.white);
        gir.fill(r);
        Graphics2D pg = (Graphics2D)getGraphics();
        update(pg);
    }

    /**
     * Set the brush of the painter to the given color
     * @param c is the color to which the painter's brush is set
     */
    public void setColor(Color c) {
        color = c;
    }

    private Color savedColor;

    /**
     * Set the brush of the painter to the given color
     */
    public void saveColor() {
        savedColor = color;
    }

    public void restoreColor() {
        color = savedColor;
    }

    /**
     * Paint the given SCircle at the current location of the painter in
     * the current color.
     * @param c is the given SCircle to be painted
     */
    public void paint(SCircle c) {
        System.out.println("HEYHEYHEY");
        double x = location.getX() - c.radius();
        double y = location.getY() - c.radius();
        double w = c.diameter();
        double h = c.diameter();
        gir.setPaint(color);
        gir.fillOval((int)x,(int)y,(int)w,(int)h);
        Graphics pg = getGraphics();
        update(pg);
    }

    public void paintImage(BufferedImage image) {
        gir.drawImage( image, 0, 0, null);
        Graphics pg = getGraphics();
        update(pg);
    }

    /**
     * Paint the given SSquare at the current location of the painter in
     * the current color.
     * @param s is the SSquare to be painted
     */
    public void paint(SSquare s) {
        java.awt.Polygon poly = new java.awt.Polygon();
        Point2D.Double p = null;
        mlt(s.side()/2); mbk(s.side()/2);
        mfd(s.side());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mfd(s.side());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mfd(s.side());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mfd(s.side());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mrt(s.side()/2); mfd(s.side()/2);
        gir.setPaint(color);
        //gir.setPaint(Color.CYAN);
        gir.fillPolygon(poly);
        Graphics pg = getGraphics();
        update(pg);
//        double x = location.getX() - s.side()/2.0;
//        double y = location.getY() - s.side()/2.0;
//        double w = s.side()+1;
//        double h = s.side()+1;
//        gir.setPaint(color);
//        gir.fillRect((int)x,(int)y,(int)w,(int)h);
//        Graphics pg = getGraphics();
//        update(pg);
    }

    /**
     * Paint the given SRectangle at the current location of the painter in
     * the current color.
     * @param r is the SRectangle to be painted.
     */
    public void paint(SRectangle r) {
        java.awt.Polygon poly = new java.awt.Polygon();
        Point2D.Double p = null;
        mlt(r.width()/2); mbk(r.height()/2);
        mfd(r.height());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mfd(r.width());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mfd(r.height());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mfd(r.width());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mrt(r.width()/2); mfd(r.height()/2);
        gir.setPaint(color);
        //gir.setPaint(Color.CYAN);
        gir.fillPolygon(poly);
        Graphics pg = getGraphics();
        update(pg);
//        double x = location.getX() - r.width()/2;
//        double y = location.getY() - r.height()/2;
//        double w = r.width();
//        double h = r.height();
//        gir.setPaint(color);
//        gir.fillRect((int)x,(int)y,(int)w,(int)h);
//        Graphics pg = getGraphics();
//        update(pg);
    }

    /**
     * Draw the given SCircle at the current location of the painter in
     * the current color.
     * @param c is the given SCircle to be painted
     */
    public void draw(SCircle c) {
        double x = location.getX() - c.radius();
        double y = location.getY() - c.radius();
        double w = c.diameter();
        double h = c.diameter();
        gir.setPaint(color);
        gir.drawOval((int)x,(int)y,(int)w,(int)h);
        Graphics pg = getGraphics();
        update(pg);
    }

    /**
     * Draw the given SCircle at the current location of the painter in
     * the current color.
     * @param s is the given SSquare to be painted
     */
//    public void draw(SSquare s) {
//        double x = location.getX() - s.side()/2;
//        double y = location.getY() - s.side()/2;
//        double w = s.side();
//        double h = s.side();
//        gir.setPaint(color);
//        gir.drawRect((int)x,(int)y,(int)w,(int)h);
//        Graphics pg = getGraphics();
//        update(pg);
//    }

    public void draw(SSquare s) {
        java.awt.Polygon poly = new java.awt.Polygon();
        Point2D.Double p = null;
        mlt(s.side()/2); mbk(s.side()/2);
        mfd(s.side());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mfd(s.side());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mfd(s.side());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mfd(s.side());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mrt(s.side()/2); mfd(s.side()/2);
        gir.setPaint(color);
        //gir.setPaint(Color.CYAN);
        gir.drawPolygon(poly);
        Graphics pg = getGraphics();
        update(pg);

    }





    /**
     * Draw the given SPolygon at the current location of the painter in
     * the current color.
     * @param s is the given SPolygon to be painted
     */
    public void draw(SPolygon s) {
               if ( Predicates.congruent(1,4,s.degree()) )
               {
                  tl(360.0/s.degree()/4.0);
               }
               else if ( Predicates.congruent(3,4,s.degree()) )
               {
                  tl(360.0/s.degree()/4.0);
                  tl(180);
               }
               else if ( Predicates.congruent(2,4,s.degree()) )
               {
                  tl(360.0/s.degree()/2.0);
               }
               double side = s.side();
               SCircle cc = s.inscribingCircle();
               System.out.println("cc=" + cc.toString());
               // paint(cc);
               double radius = cc.radius();
               mlt(radius); mbk(side/2.0);
               java.awt.Polygon poly = new java.awt.Polygon();
               Point2D.Double p = null;
               for ( int i = 1; i <= s.degree(); i++ )
               {
                  // p = mapPainterToAWT(location);
                  p = location;
                  poly.addPoint((int)p.x,(int)p.y);
                  mfd(side);
                  tr((360.0/s.degree()));
               }
               // p = mapPainterToAWT(location);
               p = location;
               poly.addPoint((int)p.x,(int)p.y);
               mfd(side/2.0);
               mrt(radius);
               if ( Predicates.congruent(1,4,s.degree()) )
               {
                  tr(360.0/s.degree()/4.0);
               }
               else if ( Predicates.congruent(3,4,s.degree()) )
               {
                  tr(360.0/s.degree()/4.0);
                  tr(180);
               }
               else if ( Predicates.congruent(2,4,s.degree()) )
               {
                  tr(360.0/s.degree()/2.0);
               }
               gir.setPaint(color);
               //gir.setPaint(Color.CYAN);
               gir.drawPolygon(poly);
               Graphics pg = getGraphics();
               update(pg);
            }

//            public Position mapPainterToAWT(Position p)
//            {
//               double x = center.x + p.x();
//               double y = center.y - p.y();
//               return new Position(x,y);
//            }
//

            public Point2D.Double mapPainterToAWT(Point2D.Double location) {
                double x = center.x + location.x;
                double y = center.y = location.y;
                return new Point2D.Double(x,y);
            }

            public void paint(SPolygon s) {
               Point2D.Double locsav = new Point2D.Double(location.x,location.y);
                SCircle mark = new SCircle(8);
                setColor(Color.black);
               paint(mark);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Painter.class.getName()).log(Level.SEVERE, null, ex);
        }
               setColor(Color.YELLOW);
               System.out.println("location(=" + location.toString());
               if ( Predicates.congruent(1,4,s.degree()) ) {
                  tl(360.0/s.degree()/4.0);
               } else if ( Predicates.congruent(3,4,s.degree()) ) {
                  tl(360.0/s.degree()/4.0);
                  tl(180);
               } else if ( Predicates.congruent(2,4,s.degree()) ) {
                  tl(360.0/s.degree()/2.0);
               }
               double side = s.side();
               SCircle cc = s.inscribingCircle();
               double radius = cc.radius();
               mlt(radius); mbk(side/2.0);
               java.awt.Polygon poly = new java.awt.Polygon();
               Point2D.Double p = null;
               for ( int i = 1; i <= s.degree(); i++ )
               {
                  // p = mapPainterToAWT(location);
                  p = location;
                  poly.addPoint((int)p.x,(int)p.y);
                  paint(mark);
                  mfd(side);
                  tr((360.0/s.degree()));
               }
               // p = mapPainterToAWT(location);
               p = location;
               poly.addPoint((int)p.x,(int)p.y);
               paint(mark);
               mfd(side/2.0);
               // mfd(side/2.0);
               mrt(radius);
               if ( Predicates.congruent(1,4,s.degree()) ) {
                  tr(360.0/s.degree()/4.0);
               } else if ( Predicates.congruent(3,4,s.degree()) ) {
                  tr(360.0/s.degree()/4.0);
                  tr(180);
               } else if ( Predicates.congruent(2,4,s.degree()) ) {
                  tr(360.0/s.degree()/2.0);
               }
               gir.setPaint(color);
               gir.fillPolygon(poly);
               System.out.println("location)=" + location.toString());
               Graphics pg = getGraphics();
               update(pg);
               System.out.println("location()=" + location.toString());
               setColor(Color.black);
               paint(mark);
        try {
            Thread.sleep(1000);
            location = new Point2D.Double(locsav.x,locsav.y);
        } catch (InterruptedException ex) {
            Logger.getLogger(Painter.class.getName()).log(Level.SEVERE, null, ex);
        }
            }

    /**
     * Draw the given SRectangle at the current location of the painter in
     * the current color.
     * @param r is the SRectangle to be painted.
     */
    public void draw(SRectangle r) {
        java.awt.Polygon poly = new java.awt.Polygon();
        Point2D.Double p = null;
        mlt(r.width()/2); mbk(r.height()/2);
        mfd(r.height());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mfd(r.width());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mfd(r.height());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mfd(r.width());
        p = location;
        poly.addPoint((int)p.x,(int)p.y);
        tr();
        mrt(r.width()/2); mfd(r.height()/2);
        gir.setPaint(color);
        //gir.setPaint(Color.CYAN);
        gir.drawPolygon(poly);
        Graphics pg = getGraphics();
        update(pg);
//        double x = location.getX() - r.width()/2;
//        double y = location.getY() - r.height()/2;
//        double w = r.width();
//        double h = r.height();
//        gir.setPaint(color);
//        gir.drawRect((int)x,(int)y,(int)w,(int)h);
//        Graphics pg = getGraphics();
//        update(pg);
    }

    /**
     * Move the painter to a random location.
     */
    public void move() {
        // System.out.println("move");
        int w = this.getWidth();
        int h = this.getHeight();
        int x = (int) (Math.random()*w);
        int y = (int) (Math.random()*h);
        location.setLocation(x,y);
        // System.out.println(location.toString());
    }

    /**
     * Move the painter to a random location within "the neighborhood".
     */
    public void moveWithinNeighborhood(int d) {
        int currentX = (int)location.x;
        int currentY = (int)location.y;
        int xAdjustment = (int) (Math.random()*d);
        int yAdjustment = (int) (Math.random()*d);
        if ( Math.random() < 0.5 ) { xAdjustment = -1 * xAdjustment; }
        if ( Math.random() < 0.5 ) { yAdjustment = -1 * yAdjustment; }
        int newX = currentX + xAdjustment;
        int newY = currentY + yAdjustment;
        //System.out.println("currentX="+currentX+" currentY="+currentY);
        //System.out.println("newX="+newX+" newY="+newY);
        location.setLocation(newX,newY);
    }



    /**
     * Move the painter to the given point.
     * @param p is the destination of the painter.
     */
    public void moveTo(Point2D.Double p) {
        location.setLocation(p.getX(),p.getY());
    }

    /**
     * Move the painter to the center of the canvas.
     */
    public void moveToCenter() {
        location.setLocation(center.getX(),center.getY());
    }

    /**
     * Move the painter to the center of the canvas.
     */
    public void faceNorth() {
        heading = 0;
    }

    /**
     * Draw a line from the current location of the painter to point the
     * given point, leaving the painter at the point.
     */
    public void drawLineTo(Point2D.Double p) {
        Line2D.Double line = new Line2D.Double(location,p);
        gir.setPaint(color);
        gir.draw(line);
        Graphics pg = getGraphics();
        update(pg);
        location = p;
    }

    /**
     * Draw a line from the current location of the painter to point the
     * given point, without moving the painter.
     */
    public void drawLineToI(Point2D.Double p) {
        Line2D.Double line = new Line2D.Double(location,p);
        gir.setPaint(color);
        gir.draw(line);
        Graphics pg = getGraphics();
        update(pg);
    }

    Point2D pointSave;

    /**
     * Move the painter a specified number of units forward.  No line
     * is drawn.
     * @param d the number of units that the painter moves
     */
    public void mfd(double d) {
        Point2D.Double newp = (Point2D.Double) newPosition(location,d);
        moveTo(newp);
    }

    /**
     * Move the painter a specified number of units backward.  No line
     * is drawn.
     * @param d the number of units that the painter moves
     */
    public void mbk(double d) {
        ta();
        mfd(d);
        ta();
    }

    public void mlt(double d) {
        tl();
        mfd(d);
        tr();
    }

    public void mrt(double d) {
        tr();
        mfd(d);
        tl();
    }

    /**
     * The painter draws a line as it moves a specified number of
     * units forward.
     * @param d the length of the line drawn
     */
    public void dfd(double d) {
        Point2D.Double newp = (Point2D.Double) newPosition(location,d);
        drawLineTo(newp);
    }

    /**
     * The painter draws a line as it moves a specified number of
     * units backward.
     * @param d the length of the line drawn
     */
    public void dbk(double d) {
        ta();
        dfd(d);
        ta();
    }

    public double heading = 0.0;

    /**
     * Turn the painter 90 degrees to its right.
     */
    public void tr() {
        heading = heading + 90;
        if ( heading >= 360 ) {
            heading = heading - 360;
        }
    }

    /**
     * Turn the painter 90 degrees to its left.
     */
    public void tl() {
        heading = heading - 90;
        if ( heading < 0 ) {
            heading = heading + 360;
        }
    }

    /**
     * Turn the painter around.
     */
    public void ta() {
        tr(); tr();
    }

    /**
     * Turn the painter the specified number of degrees to its right.
     * @param d is the number of degrees that the painter turns right.
     */
    public void tr(double d) {
        heading = heading + d;
        if ( heading > 360 ) {
            heading = heading - 360;
        }
    }

    /**
     * Turn the painter the specified number of degrees to its left.
     * @param d is the number of degrees that the painter turns left.
     */
    public void tl(double d) {
        heading = heading - d;
        if ( heading < 0 ) {
            heading = heading + 360;
        }
    }

    /**
     * Compute a random point within the painter's canvas.
     * @return the random point within the painter's canvas.
     */
    public Point2D.Double random() {
        int w = this.getWidth();
        int h = this.getHeight();
        double x = (Math.random()*w);
        double y = (Math.random()*h);
        return new Point2D.Double(x,y);
    }

    /**
     * Compute the center point of the painter's canvas.
     * @return the center point of the painter's canvas.
     */
    public Point2D.Double center() {
        return center;
    }

    /**
     * Compute the current location of the painter.
     * @return the current location of the painter.
     */
    public Point2D.Double position() {
        return location;
    }

    /**
     * Change the "brush width" of the painter's paint brush.
     * @param w the new paint brush width
     */
    public void setBrushWidth(int w) {
        // System.out.println("w="+w);
        gir.setStroke(new BasicStroke(w));
    }

    /**
     * Paint a frame around the painter's canvas.
     * @param c is the color to be used
     * @param w is the width of the frame
     */
    public void paintFrame(Color c, int w) {
        SRectangle frame = frame();
        push();
        moveToCenter();
        setColor(c);
        setBrushWidth(w*2);
        draw(frame);
        pop();
    }

    void push() {
        state.push(new State(location,heading,color,brushWidth));
    }

    void pop() {
        State s = state.pop();
        location = s.location;
        heading = s.heading;
        color = s.color;
        brushWidth = s.brushWidth;
    }

    /**
     * Set the heading ot the painter to a specific value.
     * @param d is the value to which the painter's heading is set
     */
    public void setHeading(double d) {
        heading = d;
    }

    /**
     * Create a bordering frame for the painter's canvas.
     * @return the bordering frame for the painte's canvas.
     */
    public SRectangle frame() {
        Rectangle2D.Double r = getBoundingRectangle();
        return new SRectangle(r.getHeight(),r.getWidth());
    }

    /**
     * Compute the heading of the painter.
     * @return the heading of the painter
     */
    public double heading() {
        return heading;
    }

    static double cvtDegToRad(double d)
    {
       return ( d * ( ( 2 * Math.PI ) / 360 ) );
    }

    Point2D newPosition(Point2D position, double distance) {

       double oldx = position.getX();
       double oldy = position.getY();
       double newx = oldx;
       double newy = oldy;
       double deltax = 0.0;
       double deltay = 0.0;

       if ( heading == 0.0 ) {
          newx = oldx;
          newy = oldy - distance;
       }
       if ( ( 0.0 < heading ) && ( heading < 90.0 ) ) {
          double alpha = cvtDegToRad(heading);
          deltax = (double)( distance * Math.sin(alpha) );
          deltay = (double)( distance * Math.cos(alpha) );
          newx = oldx + deltax;
          newy = oldy - deltay;
       }
       if ( heading == 90.0 ) {
          newx = oldx + distance;
          newy = oldy;
       }
       if ( ( 90.0 < heading ) && ( heading < 180.0 ) ) {
          double alpha = cvtDegToRad(180.0 - heading);
          deltax = (double)( distance * Math.sin(alpha) );
          deltay = (double)( distance * Math.cos(alpha) );
          newx = oldx + deltax;
          newy = oldy + deltay;
       }
       if ( heading == 180.0 ) {
          newx = oldx;
          newy = oldy + distance;
       }
       if ( ( 180.0 < heading ) && ( heading < 270.0 ) ) {
          double alpha = cvtDegToRad(heading - 180.0);
          deltax = (double)( distance * Math.sin(alpha) );
          deltay = (double)( distance * Math.cos(alpha) );
          newx = oldx - deltax;
          newy = oldy + deltay;
       }
       if ( heading == 270.0 ) {
          newx = oldx - distance;
          newy = oldy;
       }
       if ( ( 270.0 < heading ) && ( heading < 360.0 ) ) {
          double alpha = cvtDegToRad(360.0 - heading);
          deltax = (double)( distance * Math.sin(alpha) );
          deltay = (double)( distance * Math.cos(alpha) );
          newx = oldx - deltax;
          newy = oldy - deltay;
       }
       return new Point2D.Double(newx,newy);

    }

    public Color paintBrushColor() {
        return color;
    }

    class State {

        Point2D.Double location;
        Color color;
        double heading;
        int brushWidth;

        public State(Point2D.Double l, double h, Color c, int bw) {
            location = l;
            color = c;
            heading = h;
            brushWidth = bw;
        }

    }


}
