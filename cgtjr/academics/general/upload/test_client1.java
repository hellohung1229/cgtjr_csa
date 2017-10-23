/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general.upload;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;

public class test_client1 extends Applet {
    
    
    public void init() {
        
	Button b = new Button("submit6");
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
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi,"JPEG",baos);
		byte[] buf = baos.toByteArray();
                URL url = new URL("http://10.70.70.1:8080/servlet/send_testclient");
                HttpURLConnection con =(HttpURLConnection)url.openConnection();
            
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/octet-stream");   
		OutputStream os = con.getOutputStream();
		os.write(buf);
		os.flush();
		os.close();
            InputStream is = con.getInputStream();
		int c;
		while ((c = is.read()) != -1)
		  System.out.write(c);
		System.out.println();
		is.close();
		//os.close();
		con.disconnect();
	    } catch (Exception e) {
		 showStatus(e.toString());
            // System.out.println(e);   
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
