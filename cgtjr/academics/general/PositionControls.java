package cgtjr.academics.general;

import java.awt.Component;
import javax.vecmath.Vector3f;

// Classes that implement this interface must be a 
// subclass of java.awt.Component
public interface PositionControls {

    /**
      * Get the position
      */
    public void getPosition( Vector3f pos);

    /**
      * Set the position
      */
    public void setPosition( Vector3f pos);

    /**
      * Increment added to position each time mouse is pressed
      * or if the mouse is held down each time the Sensor is
      * read
      */
    public void setStepRate( float stepRate );
}
