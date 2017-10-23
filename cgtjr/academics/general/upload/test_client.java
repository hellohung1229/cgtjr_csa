/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general.upload;


import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;
import javax.imageio.ImageIO;

public class test_client extends Applet {
       public void init() {
	Button b = new Button("submit");
	b.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent ae) {
	     showStatus("button pressed");
             try {
		BufferedImage bi =new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();
                Graphics g = getGraphics();
		g.setColor(Color.BLUE);
		g.drawLine(100,100,200,200);
                g2=(Graphics2D)g;   
		URL url = new URL("http://10.70.70.1:8080/servlet/send_client");
                HttpURLConnection con =(HttpURLConnection)url.openConnection();
            	con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "image/jpeg");   
		ImageIO.write(bi,"JPEG",con.getOutputStream());
		con.disconnect();
	    } catch (Exception e) {
		 showStatus(e.toString());
           }
	 }
      });
      add(b);
   }
}
/**
 *
 * @author finitesystem
 */
