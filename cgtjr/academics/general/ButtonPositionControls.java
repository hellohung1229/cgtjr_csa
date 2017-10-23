package cgtjr.academics.general;

import java.awt.*;
import java.awt.event.*;
import javax.vecmath.*;
import javax.media.j3d.*;

public class ButtonPositionControls extends Panel implements PositionControls, MouseListener {
    private final static int STILL=0;
    private final static int MOVING_UP=1;
    private final static int MOVING_DOWN=2;
    private final static int MOVING_LEFT=3;
    private final static int MOVING_RIGHT=4;
    private final static int MOVING_FORWARD=5;
    private final static int MOVING_BACK=6;
    private final static int CLEARSCREEN = 7;
    private boolean allClear;

    // initial mode
    private int mode = STILL;

    Vector3f position = new Vector3f();
    Vector3f orig_position = new Vector3f();

    private Button leftB = new Button("Left");
    private Button rightB = new Button("Right");
    private Button upB = new Button("Up");
    private Button downB = new Button("Down");

    private Button forwardB = new Button("Forward");
    private Button backwardB = new Button("Back");

    private Button reset = new Button("Reset");
    private Button clear = new Button("Clear");
    
    
    private InputDevice device;

    private float step_rate = 0.0023f;   // movement rate per millisecond
    private long time_last_state_change = System.currentTimeMillis();

    // the constructor arguments are the intitial X, Y, and Z positions
    public ButtonPositionControls( float x, float y, float z ) {

        // up, down, right, and left movement buttons
        Panel panPanel = new Panel();
        /*
        panPanel.setLayout( new BorderLayout() );
        panPanel.add("North", upB);
        panPanel.add("East", rightB);
        panPanel.add("South", downB);
        panPanel.add("West", leftB);
        */
        setLayout( new GridLayout(1,0) );
        add( leftB);
        add(rightB);

        //Panel panPanel2 = new Panel();
        //.setLayout( new BorderLayout() );
        add(upB);
        add(downB);


        // forward, backward, and reset buttons 
        //Panel p = new Panel();
        //p.setLayout( new GridLayout(0,1,0,0) );
        //p.setLayout( new BorderLayout() );
        add(forwardB);
        add(backwardB);
        add(reset);
        add(clear);        

        // set the initial position
        position.x = x;
        position.y = y;
        position.z = z;
        orig_position.set(position);

        // add a mouse listener to each button
        upB.addMouseListener(this);
        downB.addMouseListener(this);
        leftB.addMouseListener(this);
        rightB.addMouseListener(this);
        forwardB.addMouseListener(this);
        backwardB.addMouseListener(this);
        reset.addMouseListener(this);
        clear.addMouseListener(this);

	//this.setLayout( new BorderLayout() );
        //add("West", p );
	//add("Center", panPanel );
	//add("East", panPanel2 );
    }

    public void setDevice ( InputDevice device) {
        this.device = device;
    }

    public void getPosition(Vector3f pos ) {
	calculateMotion();
	pos.set(position);
    }

    public void setPosition(Vector3f pos ) {
	position.set(pos);
    }

    public void setStepRate( float stepRate ) {
	step_rate = stepRate;
    }

    private void calculateMotion() {

        long current_time = System.currentTimeMillis();
        long elapsed_time = current_time - time_last_state_change;

        switch(mode) {
            case STILL:
                break;
            case MOVING_LEFT:
                position.x = orig_position.x - step_rate*elapsed_time;
                break;
            case MOVING_RIGHT:
                position.x = orig_position.x + step_rate*elapsed_time;
                break;
            case MOVING_UP:
                position.y = orig_position.y + step_rate*elapsed_time;
                break;
            case MOVING_DOWN:
                position.y = orig_position.y - step_rate*elapsed_time;
                break;
            case MOVING_FORWARD:
                position.z = orig_position.z + step_rate*elapsed_time;
                break;
            case MOVING_BACK:
                position.z = orig_position.z - step_rate*elapsed_time;
                break;
            case CLEARSCREEN:
                allClear = true;
                break;                
            default:
                throw( new RuntimeException("Unknown motion"));
        }
    }

    public void mouseClicked( MouseEvent e ) {
    }
 
    public void mouseEntered( MouseEvent e ) {
    }

    public void mouseExited( MouseEvent e ) {
    }

    public void mousePressed( MouseEvent e ) {
        if (e.getSource()==leftB && mode != MOVING_LEFT) {
              time_last_state_change =  System.currentTimeMillis();
              mode = MOVING_LEFT;
              orig_position.set(position);
        } else if (e.getSource()==rightB && mode != MOVING_RIGHT) {
              time_last_state_change =  System.currentTimeMillis();
              mode = MOVING_RIGHT;
              orig_position.set(position);
        } else if (e.getSource()==upB && mode != MOVING_UP) {
              time_last_state_change =  System.currentTimeMillis();
              mode = MOVING_UP;
              orig_position.set(position);
        } else if (e.getSource()==downB && mode != MOVING_DOWN) {
              time_last_state_change =  System.currentTimeMillis();
              mode = MOVING_DOWN;
              orig_position.set(position);
        } else if (e.getSource()==forwardB && mode != MOVING_FORWARD) {
              time_last_state_change =  System.currentTimeMillis();
              mode = MOVING_FORWARD;
              orig_position.set(position);
        } else if (e.getSource()==backwardB && mode != MOVING_BACK) {
              time_last_state_change =  System.currentTimeMillis();
              mode = MOVING_BACK;
              orig_position.set(position);
        } else if (e.getSource()==reset) {
              device.setNominalPositionAndOrientation();
        }else if (e.getSource()==clear) {
            ((VirtualInputDevice)device).setAllClear(true);
            
        }
    }

    public void mouseReleased( MouseEvent e ) {
        mode = STILL;
        //allClear = false;
    }
    /*
    public void setAllClear(boolean myAllClear){
        allClear = myAllClear;
    }*/
}
