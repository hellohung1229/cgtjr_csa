/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.video.shapepnt;

import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;

/**
 *
 * @author finitesystem
 */
public class VideoEdgeShpFrame extends JFrame {


    

    public static void main(String[] args) {
        VideoEdgeShpApplt myApplet = new VideoEdgeShpApplt();

    JFrame myFrame = new JFrame("Applet Holder"); // create frame with title
    // Call applet's init method (since Java App does not
    // call it as a browser automatically does)
    myApplet.init();	
    myApplet.start();
    // add applet to the frame
    myFrame.add(myApplet, BorderLayout.CENTER);
    myFrame.pack(); // set window to appropriate size (for its elements)
    myFrame.setVisible(true); // usual step to make frame visible
    }

}
