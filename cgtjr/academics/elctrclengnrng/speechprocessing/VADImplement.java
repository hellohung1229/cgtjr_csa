package cgtjr.academics.elctrclengnrng.speechprocessing;


import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.util.Vector;

import javax.media.*;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.control.*;

public class VADImplement implements ControllerListener {
    private boolean realized = false;
    private boolean configured = false;
    public static double theEnergyLevel;
    public static double theStandardDeviation;

    //public static double theEnergyLevel;
    //public static double theStandardDeviation;

     public static JTextArea theJTextArea;
     private static JScrollPane theJScrollPane;
     

    public static void main(String [] args) {
    
      theEnergyLevel = new Double(args[1]).doubleValue();
      theStandardDeviation = new Double(args[2]).doubleValue();
      String myFile = args[0];

     theJTextArea = new JTextArea();
     theJTextArea.setColumns(7);

      new VADImplement(myFile);

     theJScrollPane = new JScrollPane(theJTextArea);
     //theJScrollPane.add(theJScrollPane );
     JFrame theJFrame = new JFrame();
     theJFrame.add(theJScrollPane);
     theJFrame.setSize(700,400);
     theJFrame.setVisible(true);
          
    }

    //public VADImplement() {
      public VADImplement(String aFile1)
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
          VADEffect anotherVADEffect = new VADEffect();

          Effect anotherEffect[] = new Effect[1];

          anotherEffect[0] = anotherVADEffect;
          for(int i = 0; i<theTrackControl.length;i++)
          {
            theTrackControl[i].setCodecChain(anotherEffect);
          }

	    ContentDescriptor[] descriptors = p.getSupportedContentDescriptors();
	    for (int n = 0; n < descriptors.length; n++) {
		System.out.println("Desc: " + descriptors[n].toString());
	    }
	    //p.setContentDescriptor(new ContentDescriptor(FileTypeDescriptor.MPEG_AUDIO));
	    p.setContentDescriptor(new ContentDescriptor(FileTypeDescriptor.WAVE));


	    p.realize();
	    while (! realized) {
		try {
		    Thread.currentThread().sleep(100L);;
		} catch (InterruptedException e) {
		    // ignore
		}
	    }

	    DataSource output = p.getDataOutput();

	    System.out.println("DataSource type: ");
	    Class cls = output.getClass();
	    while (cls != null) {
		System.out.println(cls.toString());
		cls = cls.getSuperclass();
	    }

	    sink = Manager.createDataSink(output, dest);
	    sink.open();
	    sink.start();
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
	} else {
	    // System.out.println(evt.toString());
	}
    }
}