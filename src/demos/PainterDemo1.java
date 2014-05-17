/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demos;

import java.awt.Color;
import painter.Painter;
import shapes.SCircle;

/**
 *
 * @author blue
 */
public class PainterDemo1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Painter picasso = new Painter("Demo 1",500,700);
        picasso.setColor(Color.BLUE);
        SCircle circle = new SCircle(100);
        picasso.paint(circle);
    }
}
