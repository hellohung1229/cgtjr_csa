package cgtjr.academics.elctrclengnrng.videotmp;

import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.util.Vector;

import javax.media.*;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.control.*;
import java.awt.*;

public class VideoListener implements ControllerListener {
    private boolean realized = false;
    private boolean configured = false;
    public static double theEnergyLevel;
    public static double theStandardDeviation;

    //public static double theEnergyLevel;
    //public static double theStandardDeviation;

     public static JTextArea theJTextArea;
     private static JScrollPane theJScrollPane;
     private static Panel videoCanvas;
     private static VideoCompRenderer anotherVideoCompRenderer ;

    public static void main(String [] args) {
    
      //theEnergyLevel = new Double(args[1]).doubleValue();
      //theStandardDeviation = new Double(args[2]).doubleValue();
      String myFile = args[0];
     videoCanvas = new Panel();
     //theJTextArea = new JTextArea();
     //theJTextArea.setColumns(7);

      new VideoListener(myFile);

     //theJScrollPane = new JScrollPane(videoCanvas);
     //theJScrollPane.add(theJScrollPane );
     JFrame theJFrame = new JFrame();
     theJFrame.getContentPane().add(videoCanvas);
     theJFrame.setSize(700,400);
     theJFrame.setVisible(true);
          
    }

    //public VideoListener() {
      public VideoListener(String aFile1)
      {
	Processor p;

	DataSink sink;
	MediaLocator src = new MediaLocator("file:" + aFile1);
	MediaLocator dest = new MediaLocator("file:" + "tmp.wav");

	try {
	    p = Manager.createProcessor(src);

	    p.addControllerListener(this);
       
	    p.configure();
	    while (! configured) {
		try {
		    Thread.currentThread().sleep(100L);;
		} catch (InterruptedException e) {
		    // ignore
		}
	    }
          TrackControl theTrackControl[] = p.getTrackControls();

          System.out.println("Number of track controls = "+theTrackControl.length);
          anotherVideoCompRenderer = new VideoCompRenderer();
          anotherVideoCompRenderer.setComponent(videoCanvas); 
          //Effect anotherEffect[] = new Effect[1];
          //anotherEffect[0] = anotherVideoCompRenderer;
          for(int i = 0; i<theTrackControl.length;i++)
          {
            theTrackControl[i].setRenderer(anotherVideoCompRenderer);
          }

	    ContentDescriptor[] descriptors = p.getSupportedContentDescriptors();
	    for (int n = 0; n < descriptors.length; n++) {
		System.out.println("Desc: " + descriptors[n].toString());
	    }
	    //p.setContentDescriptor(new ContentDescriptor(FileTypeDescriptor.MPEG_AUDIO));
	    p.setContentDescriptor(new ContentDescriptor(FileTypeDescriptor.MSVIDEO));


	    p.realize();
	    while (! realized) {
		try {
		    Thread.currentThread().sleep(100L);;
		} catch (InterruptedException e) {
		    // ignore
		}
	    }

	    //DataSource output = p.getDataOutput();

	    //System.out.println("DataSource type: ");
	    //Class cls = output.getClass();
	    //while (cls != null) {
		//System.out.println(cls.toString());
		//cls = cls.getSuperclass();
	    //}

	    //sink = Manager.createDataSink(output, dest);
	    //sink.open();
	    //sink.start();
       System.out.println("test 0");
	    p.start();

	} catch(Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}

    }

    public synchronized void controllerUpdate(ControllerEvent evt) {
	if (evt instanceof RealizeCompleteEvent) {
	    realized = true;
	} else 	if (evt instanceof ConfigureCompleteEvent) {
	    configured = true;
	} else if (evt instanceof EndOfMediaEvent) {
	    //stem.exit(0);
	} else if (evt instanceof StartEvent) {
       System.out.println("test ... startevent");
       //anotherVideoCompRenderer.start();
	    //stem.exit(0);
	}  else {
	    System.out.println(evt.toString());
	}
    }
} 
