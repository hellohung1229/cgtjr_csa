package cgtjr.academics.general;

import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import javax.swing.*;

public class VirtualInputDevice implements InputDevice {

    private Vector3f position = new Vector3f();
    private Transform3D newTransform = new Transform3D();
    Sensor sensors[] = new Sensor[1];
    // The wheel controls control the view platform orientation
    private RotationControls rotControls;
    // The button position controls control the view platform position
    private PositionControls positionControls;
    private Transform3D rotTransX = new Transform3D();
    private Transform3D rotTransY = new Transform3D();
    private Transform3D rotTransZ = new Transform3D();
    private Vector3f initPos = new Vector3f();
    private int processingMode;
    private SensorRead sensorRead = new SensorRead();
    // These are the settable parameters.
    private boolean printvalues;
    private int xscreeninitloc;
    private int yscreeninitloc;
    private int xscreensize;
    private int yscreensize;
    private float xobjinitloc;
    private float yobjinitloc;
    private float zobjinitloc;
    private float xaxisrotinit;
    private float yaxisrotinit;
    private float zaxisrotinit;
    private JPanel devicePanel;
    private BranchGroup shpeBranchGroup;
    private boolean allClear;

    /* 
     * Create a device, and use the string arguments in args to construct
     * the device with user preferences.
     */
    public VirtualInputDevice() {
        String[] args = new String[10];
        args[0] = "printvalues";
        args[1] = "true";
        args[2] = "yscreeninitloc";
        args[3] = "50";
        args[4] = null;
        process(args);

    }

    public void setShpeBranchGroup(BranchGroup myShpeBranchGroup) {
        shpeBranchGroup = myShpeBranchGroup;
    }

    public VirtualInputDevice(String[] args) {

        process(args);
    }

    public void process(String[] args) {
        // default user-definable values
        printvalues = false;
        xscreeninitloc = 0;
        yscreeninitloc = 0;
        xscreensize = 400;
        yscreensize = 200;
        xobjinitloc = 0.0f;
        yobjinitloc = 0.0f;
        zobjinitloc = 0.0f;
        xaxisrotinit = 0.0f;
        yaxisrotinit = 0.0f;
        zaxisrotinit = 0.0f;


        for (int i = 0; i < args.length; i += 2) {
            if (args[i] == null) {
                break;
            } else if (args[i] == "printvalues") {
                printvalues = (Boolean.valueOf(args[i + 1])).booleanValue();
            } else if (args[i] == "xscreeninitloc") {
                xscreeninitloc = (Integer.valueOf(args[i + 1])).intValue();
            } else if (args[i] == "yscreeninitloc") {
                yscreeninitloc = 0;// (Integer.valueOf(args[i+1])).intValue();
            } else if (args[i] == "xscreensize") {
                xscreensize = (Integer.valueOf(args[i + 1])).intValue();
            } else if (args[i] == "yscreensize") {
                yscreensize = (Integer.valueOf(args[i + 1])).intValue();
            } else if (args[i] == "xobjinitloc") {
                xobjinitloc = (Float.valueOf(args[i + 1])).floatValue();
            } else if (args[i] == "yobjinitloc") {
                yobjinitloc = (Float.valueOf(args[i + 1])).floatValue();
            } else if (args[i] == "zobjinitloc") {
                zobjinitloc = (Integer.valueOf(args[i + 1])).floatValue();
            }
        }

        if (printvalues == true) {
            System.out.println("Initial values for VirtualInputDevice:");
            System.out.println("xscreeninitloc = " + xscreeninitloc);
            System.out.println("yscreeninitloc = " + yscreeninitloc);
            System.out.println("xscreeninitsize = " + xscreensize);
            System.out.println("yscreeninitsize = " + yscreensize);
            System.out.println("xobjinitloc = " + xobjinitloc);
            System.out.println("yobjinitloc = " + yobjinitloc);
            System.out.println("zobjinitloc = " + zobjinitloc);
            System.out.println("xaxisrotinit = " + xaxisrotinit);
            System.out.println("yaxisrotinit = " + yaxisrotinit);
            System.out.println("zaxisrotinit = " + zaxisrotinit);
        }


        // initialize the InputDevice GUI
        //Frame deviceFrame = new Frame();
        //Panel devicePanel = new Panel();

        //deviceFrame.setSize(xscreensize,yscreensize);
        //deviceFrame.setLocation(xscreeninitloc, yscreeninitloc);
        //deviceFrame.setTitle("Virtual Input Device");
        ButtonPositionControls positionControls;
        // initialize position with initial x, y, and z position
        positionControls = new ButtonPositionControls(xobjinitloc,
                yobjinitloc, zobjinitloc);
        WheelControls rotControls;
        // initialize rotations with initial angles in radians)
        rotControls = new WheelControls(xaxisrotinit, yaxisrotinit,
                zaxisrotinit);
        positionControls.setDevice(this);
        devicePanel = new JPanel();
        //devicePanel.setLayout( new BorderLayout() );
        devicePanel.setLayout(new BorderLayout());
        //devicePanel.add("East", positionControls );
        //devicePanel.add("West", rotControls );
        devicePanel.add("West", rotControls);
        devicePanel.add("Center", positionControls);


        //deviceFrame.add( devicePanel );
        //deviceFrame.pack();
        //deviceFrame.setVisible(true);

        initPos.set(xobjinitloc, yobjinitloc, zobjinitloc);

        this.positionControls = positionControls;
        this.rotControls = rotControls;

        // default processing mode
        processingMode = InputDevice.DEMAND_DRIVEN;

        sensors[0] = new Sensor(this);
    }

    public JPanel retrieveVirtualPanel() {
        return devicePanel;
    }

    public void close() {
    }

    public int getProcessingMode() {
        return processingMode;
    }

    public int getSensorCount() {
        return sensors.length;
    }

    public Sensor getSensor(int sensorIndex) {
        return sensors[sensorIndex];
    }

    public boolean initialize() {
        return true;
    }

    public void pollAndProcessInput() {

        sensorRead.setTime(System.currentTimeMillis());

        rotTransX.rotX(-rotControls.getXAngle());
        rotTransY.rotY(-rotControls.getYAngle());
        rotTransZ.rotZ(-rotControls.getZAngle());

        positionControls.getPosition(position);
        newTransform.set(position);
        newTransform.mul(rotTransX);

        newTransform.mul(rotTransY);
        newTransform.mul(rotTransZ);

        sensorRead.set(newTransform);
        sensors[0].setNextSensorRead(sensorRead);
        
        clearScreen();

    }

    public void clearScreen() {
        if (allClear == true) {
            shpeBranchGroup.removeAllChildren();
            allClear = false;
        }
    }
    public void setAllClear(boolean myAllClear){
        allClear = myAllClear;
    }
    public void processStreamInput() {
    }

    public void setNominalPositionAndOrientation() {

        sensorRead.setTime(System.currentTimeMillis());

        rotTransX.rotX(xaxisrotinit);
        rotTransY.rotY(yaxisrotinit);
        rotTransZ.rotZ(zaxisrotinit);

        position.set(initPos);

        newTransform.set(position);

        newTransform.mul(rotTransX);
        newTransform.mul(rotTransY);
        newTransform.mul(rotTransZ);

        sensorRead.set(newTransform);
        sensors[0].setNextSensorRead(sensorRead);
        rotControls.reset();
        positionControls.setPosition(initPos);
    }

    public void setProcessingMode(int mode) {

        // A typical driver miht implement only one of these modes, and
        // throw an exception when there is an attempt to switch modes.
        // However, this example allows one to use any processing mode.

        switch (mode) {
            case InputDevice.DEMAND_DRIVEN:
            case InputDevice.NON_BLOCKING:
            case InputDevice.BLOCKING:
                processingMode = mode;
                break;
            default:
                throw new IllegalArgumentException("Processing mode must "
                        + "be one of DEMAND_DRIVEN, NON_BLOCKING, or BLOCKING");
        }
    }
}
