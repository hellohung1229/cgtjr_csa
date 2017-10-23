/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.complabel;

/**
 *
 * @author clayton g thomas jr
 */
/*
 * This applet will display a gif on screen for now the gif file must be in the
 * same directory as this applet
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;

public class RcrsvCnnctCmpnnt extends JApplet {

    Image my_gif;
    URL base;
    MediaTracker mt;
    BufferedImage bimage;
    int sizeh;
    int sizew;

    public void init() {

        try{
          bimage = ImageIO.read(new File("./cnnctpttrn1.gif"));
        }catch(Exception e){
            
        }
        sizeh = bimage.getHeight();
        sizew = bimage.getWidth();
        beginLabel();
        repaint();
    }

    public void paint(Graphics g) {
        g.drawImage(bimage, 20, 20, this);
    }

    void compLabel(int i, int j, int m) {
        int pixelValue = getPixel(i, j);

        if (pixelValue == 0xff000000) {
            setPixel(i, j, m);
            getPixel(i, j);
            compLabel(i - 1, j - 1, m);
            compLabel(i - 1, j, m);
            compLabel(i - 1, j + 1, m);
            compLabel(i, j - 1, m);
            compLabel(i, j + 1, m);
            compLabel(i + 1, j - 1, m);
            compLabel(i + 1, j, m);
            compLabel(i + 1, j + 1, m);
        }
    }

    void beginLabel() {
        int m = 1;
        for (int y = 0; y < sizeh; y++) {
            for (int x = 0; x < sizew; x++) {
                int pixelValue = getPixel(x, y);
                if (pixelValue == 0xff000000) {
                    compLabel(x, y, ++m);
                }
            }
        }
    }

    private int getPixel(int i, int j) {
        if (i >= 0 && j >= 0 && i < sizew && j < sizeh) {
            int color = bimage.getRGB(i, j);
            return color;
        } else {
            return 9;
        }
    }

    private void setPixel(int i, int j, int m) {
        if (i >= 0 && j >= 0 && i < sizew && j < sizeh) {
            bimage.setRGB(i, j, m );
            bimage.flush();
        }
    }

    public static void main(String args[]) {
        JFrame aJFrame = new JFrame();
        aJFrame.setSize(500,500);
        RcrsvCnnctCmpnnt aCnnctCmpnnt = new RcrsvCnnctCmpnnt();
        aJFrame.add(aCnnctCmpnnt);
        aCnnctCmpnnt.init();
        aJFrame.setVisible(true);
    }
}
