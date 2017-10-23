package cgtjr.academics.general;

import java.awt.*;
import java.awt.event.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.*;

public class AWTInteractionBehavior extends Behavior
implements ActionListener {

    private TransformGroup transformGroup;
    private Transform3D trans = new Transform3D();
    private WakeupCriterion criterion;
    private float angle = 0.0f;

    // create a new AWTInteractionBehavior
    public AWTInteractionBehavior(TransformGroup tg) {
	transformGroup = tg;
    }

    // initialize the behavior to wakeup on a behavior post with the id
    // MouseEvent.MOUSE_CLICKED
    public void initialize() {
	criterion = new WakeupOnBehaviorPost(this,
 					     MouseEvent.MOUSE_CLICKED);
	wakeupOn(criterion);
    }

    // processStimulus to rotate the cube
    public void processStimulus(Enumeration criteria) {
	angle += Math.toRadians(10.0);
	trans.rotY(angle);
	transformGroup.setTransform(trans);
	wakeupOn(criterion);
    }

    // when the mouse is clicked, postId for the behavior
    public void actionPerformed(ActionEvent e) {
	postId(MouseEvent.MOUSE_CLICKED);
    }
}

