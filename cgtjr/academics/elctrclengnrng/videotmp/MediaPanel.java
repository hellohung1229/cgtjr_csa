/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.videotmp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import javax.swing.JPanel;
import javax.media.*;
import javax.media.control.*;
/**
 *
 * @author Nit
 */
public class MediaPanel extends JPanel  implements ControllerListener 
{
   private Processor player = null;
	MediaLocator mrl = null;
   private boolean configured = false;
   private boolean realized = false;
   private Component progressBar = null;
   private Component controlComponent = null;
   private Component visualComponent = null;
   private int videoWidth = 0;
   private int videoHeight = 0;   
   private int controlPanelHeight = 0;   
   // controls gain, position, start, stop

   private Panel panel = null;   
   
   
   public MediaPanel( URL mediaURL ) {

    //initComponents();

    setLayout( null ); // use a BorderLayout
      try {
	   // Create a media locator from the file name
	   if ( (mrl = new MediaLocator(mediaURL)) == null)
         {
	      Fatal("Can't build URL for " + mediaURL);
         }
	   // Create an instance of a player for this media
	   try {
            if(mrl != null)
            {
	         player = Manager.createProcessor(mrl);
	         //player.addControllerListener(this);
            
            }
	   } catch (NoPlayerException e) {
            System.out.println(e);
	   }
    player.configure();
      while (! configured) {
		try {
		    Thread.currentThread().sleep(100L);;
		} catch (InterruptedException e) {
		    // ignore
		}
      }
      player.setContentDescriptor(null);
      TrackControl theTrackControl[] = player.getTrackControls();
      
      //System.out.println("VideoAnalyzer:"+theTrackControl[1].getFormat());

      VideoCompRenderer anotherVideoCompRenderer = new VideoCompRenderer();
       
      setRenderer(theTrackControl,anotherVideoCompRenderer);
      
	} catch (MalformedURLException e) {
	    Fatal("Invalid media file URL!");
	} catch (IOException e) {
	    Fatal("IO exception creating player for " + mrl);
	}    
      start();
        Component video = player.getVisualComponent();     
        Component controls = player.getControlPanelComponent();
        
            //add( video, BorderLayout.CENTER ); // add video component    
            //add( controls, BorderLayout.SOUTH );            
      //return panel;      
      /*
    try {


           
        // Create a JMF player to play the media specified in the URL:
        Player mediaPlayer = Manager.createRealizedPlayer( new MediaLocator(mediaURL) );

        // Get the components for the video and the playback controls:
        Component video = mediaPlayer.getVisualComponent();
        Component controls = mediaPlayer.getControlPanelComponent();
       
        VideoCompRenderer anotherVideoCompRenderer = new VideoCompRenderer();
       

      
       if ( video != null )
            add( video, BorderLayout.CENTER ); // add video component

        if ( controls != null )
            add( controls, BorderLayout.SOUTH ); // add controls

        // Start the JMF player:
        mediaPlayer.start(); // start playing the media clip

    } // end try

    catch ( NoPlayerException noPlayerException ) {
        System.err.println( "No media player found" );
    } // end catch

    catch (CannotRealizeException ex) {
            ex.printStackTrace();
    } // end catch

    catch ( IOException iOException ) {
        System.err.println( "Error reading from the source" );
    } // end catch*/

} // end MediaPanel constructor
  public void setRenderer(TrackControl[] myTrackControl,
                           VideoCompRenderer myVideoCompRenderer)
   {
      int aSize = myTrackControl.length;
      try{
         for(int i=0;i<aSize;i++)
         {
            Format aFormat = myTrackControl[i].getFormat();
            System.out.println("VideoAnalyzer:"+myTrackControl[i].getFormat());
            if(aFormat.toString().startsWith("H263"))
            {
               myTrackControl[i].setRenderer(myVideoCompRenderer);
            }else if(aFormat.toString().startsWith("RGB"))
            {
               myTrackControl[i].setRenderer(myVideoCompRenderer);
            }else if(aFormat.toString().startsWith("CVID"))
            {
               myTrackControl[i].setRenderer(myVideoCompRenderer);
            }else if(aFormat.toString().startsWith("MPEG"))
            {
               myTrackControl[i].setRenderer(myVideoCompRenderer);
            }else if(aFormat.toString().startsWith("MP4V"))
            {
               myTrackControl[i].setRenderer(myVideoCompRenderer);
            }else if(aFormat.toString().startsWith("mpeglayer3"))
            {
               myTrackControl[i].setRenderer(myVideoCompRenderer);
            }else if(aFormat.toString().startsWith("XVID"))
            {
               myTrackControl[i].setRenderer(myVideoCompRenderer);
            }
         }
      }catch(javax.media.UnsupportedPlugInException upe){
         System.err.println("VideoAnalyzer:"+upe.getMessage());
      }
   }
  
   public synchronized void controllerUpdate(ControllerEvent event) {
	// If we're getting messages from a dead player, 
	// just leave
      if (player == null)
	    return;
		if (event instanceof RealizeCompleteEvent) {
	    realized = true;
	   }else 	if (event instanceof ConfigureCompleteEvent) {
	    configured = true;
	   }
	// When the player is Realized, get the visual 
	// and control components and add them to the Applet
      if (event instanceof RealizeCompleteEvent) {
	   if (progressBar != null) {
		remove(progressBar);
		progressBar = null;
	   }

	   int width = 320;
	   int height = 0;
	   if (controlComponent == null)
	      if (( controlComponent = 
		      player.getControlPanelComponent()) != null) {
		    
		    controlPanelHeight = controlComponent.getPreferredSize().height;
		    add(controlComponent);
		    height += controlPanelHeight;
	      }
	   if (visualComponent == null)
	      if (( visualComponent =
		      player.getVisualComponent())!= null) {
		    add(visualComponent);
		    Dimension videoSize = visualComponent.getPreferredSize();
		    videoWidth = videoSize.width;
		    videoHeight = videoSize.height;
		    width = videoWidth;
		    height += videoHeight;
		    visualComponent.setBounds(0, 0, videoWidth, videoHeight);
         }

	   setBounds(0, 0, width, height);
         if (controlComponent != null) {
            controlComponent.setBounds(0, videoHeight,
					   width, controlPanelHeight);
		controlComponent.invalidate();
	   } 
 	} else if (event instanceof CachingControlEvent) {
         if (player.getState() > Controller.Realizing)
            return;
	   // Put a progress bar up when downloading starts, 
	   // take it down when downloading ends.
	   CachingControlEvent e = (CachingControlEvent) event;
	   CachingControl cc = e.getCachingControl();

	   // Add the bar if not already there ...
	   if (progressBar == null) {
	      if ((progressBar = cc.getControlComponent()) != null) {
		    add(progressBar);
		    setSize(progressBar.getPreferredSize());
		    validate();
		   }
	   }
	} else if (event instanceof EndOfMediaEvent) {
	    // We've reached the end of the media; rewind and
	    // start over
	    player.setMediaTime(new Time(0));
	    player.start();
	} else if (event instanceof ControllerErrorEvent) {
	    // Tell TypicalPlayerApplet.start() to call it a day
	    player = null;
	    Fatal(((ControllerErrorEvent)event).getMessage());
      } else if (event instanceof ControllerClosedEvent) {
	    removeAll();
      }
   }
   public void start() {
      if (player != null)
	    player.start();
   }   
    void Fatal (String s) {
	// Applications will make various choices about what
	// to do here. We print a message
      System.err.println("FATAL ERROR: " + s);
	   throw new Error(s); // Invoke the uncaught exception
			    // handler System.exit() is another
			    // choice.	
   }   
}
