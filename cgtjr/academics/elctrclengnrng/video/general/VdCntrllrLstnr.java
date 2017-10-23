/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.video.general;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.FrameParser;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;
import javax.media.CachingControl;
import javax.media.CachingControlEvent;
import javax.media.ConfigureCompleteEvent;
import javax.media.Controller;
import javax.media.ControllerClosedEvent;
import javax.media.ControllerErrorEvent;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Processor;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;

/**
 *
 * @author clayton g thomas jr
 */
public class VdCntrllrLstnr implements ControllerListener{

   private Processor player = null;
   private Component visualComponent = null;
   private Component controlComponent = null;
   private Component progressBar = null;
   private Panel panel = null;
   private int controlPanelHeight = 0;
   private int videoWidth = 0;
   private int videoHeight = 0;
   private String mediaFile = null;
   private boolean realized = false;
   private boolean configured = false;
   private VideoRndrr videoRender;
   private FrameParser videoParser;

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
		panel.remove(progressBar);
		progressBar = null;
	   }

	   int width = 320;
	   int height = 0;
	   if (controlComponent == null)
	      if (( controlComponent =
		      player.getControlPanelComponent()) != null) {

		    controlPanelHeight = controlComponent.getPreferredSize().height;
		    panel.add(controlComponent);
		    height += controlPanelHeight;
	      }
	   if (visualComponent == null)
	      if (( visualComponent =
		      player.getVisualComponent())!= null) {
		    panel.add(visualComponent);
		    Dimension videoSize = visualComponent.getPreferredSize();
		    videoWidth = videoSize.width;
		    videoHeight = videoSize.height;
		    width = videoWidth;
		    height += videoHeight;
		    visualComponent.setBounds(0, 0, videoWidth, videoHeight);
         }

	   panel.setBounds(0, 0, width, height);
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
		    panel.add(progressBar);
		    panel.setSize(progressBar.getPreferredSize());
		    //validate();
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
	    panel.removeAll();
      }
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
