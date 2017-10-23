package cgtjr.academics.general;

import java.awt.Component;

// Classes that implement this interface must be a subclass
// of java.awt.Component

public interface RotationControls {

    /**
      * Get the angle of Rotation around the X Axis
      */
    public abstract float getXAngle();

    /**
      * Get the angle or Rotation around the Y Axis
      */
    public abstract float getYAngle();

    /**
      * Get the angle or Rotation around the Z Axis
      */
    public abstract float getZAngle();

    /**
      *  Reset angles to original angle.
      */
    public abstract void reset();
}
