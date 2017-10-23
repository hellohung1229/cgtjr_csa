package cgtjr.academics.math.geometry.crdntepnts;

import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.math.geometry.elmnt.LinePnts;
import cgtjr.academics.math.graph.Vertex;
import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */
public class Pnt extends ShpBndry
{
    protected Vector glblMsh;
    private float x1, y1, z1;
    private float x0, y0, z0;
    private Vector nodes;
    private Vector edges;
    private float radius;
 
    public int glblNmbr;
    
    private boolean crdntCmpltn;
    private boolean coordinated;
    private Pnt posXDrctn;
    private Pnt negXDrctn;
    private Pnt posYDrctn;
    private Pnt negYDrctn;
    private Pnt posZDrctn;
    private Pnt negZDrctn;
    private float normal[];

    private float mass;
    private float xVlcty;
    private float yVlcty;
    private float zVlcty;    
    
    /**
     * Constructor to initialize a single point (Pnt) object.
     */
    public Pnt() {
        nodes = new Vector();
        edges = new Vector();
       
    }

    /**
     * Creates a point (Pnt) object with initial x and y coordinates.  These 
     * coordinate are allowed to be cartesian, Spherical, cylindrical, & etc..
     * @param x1Coordinate represents floating point value.
     * @param yCoordinate represents floating point value.
     */
    public Pnt(float x1Coordinate, float yCoordinate) {
        setX1(x1Coordinate);
        setY1(yCoordinate);
        nodes = new Vector();
        edges = new Vector();
    }

    /**
     * Creates a point (Pnt) object with initial x and y coordinates.  These 
     * coordinate are allowed to be cartesian, Spherical, cylindrical, & etc..
     * @param x1Coordinate represents floating point value.
     * @param yCoordinate represents floating point value.
     * @param zCoordinate represents floating point value.
     */
    public Pnt(float x1Coordinate, float yCoordinate, float zCoordinate) {
        setPoint(x1Coordinate, yCoordinate, zCoordinate);
        nodes = new Vector();
        edges = new Vector();
    }

    /**
     * get method for retrieving nodes.
     * @return vector containing list of nodes (type Vector<Nodes>).
     */
    public Vector getNodes() {
        return nodes;
    }

    /**
     * set method for assigning edges (LinePnts).
     * @param myLineVctr represents vector containing list of line points (LinePnts).
     */
    public void setEdges(Vector myLineVctr) {
        edges = myLineVctr;
    }
    /**
     *
     * @return
     */
    public Vector getEdges() {
        return edges;
    }
    protected void crtNodePnts(Pnt myPoint1) {
        if (myPoint1 == null) {
            return;
        }
        nodes.add(myPoint1);
    }
    protected void crtLinePnts(Pnt myPoint1, Pnt myPoint3) {
        if (myPoint1 == null || myPoint3 == null || myPoint1 == myPoint3) {
            return;
        }
        LinePnts aLinePnts = new LinePnts(myPoint1, myPoint3);
        getEdges().add(aLinePnts);
    }
    /**
     *
     * @param myBoundaryAction
     * @param myThis
     * @param myNmbr1
     * @param myNmbr2
     * @param myNmbr3
     * @param boundaryCheck
     */
    protected void nodeOrgnActn(PntInsrtActn myBoundaryAction, Pnt myThis, int myNmbr1, int myNmbr2, int myNmbr3, boolean boundaryCheck) {
        if (boundaryCheck == true && myBoundaryAction != null) {
            myBoundaryAction.nodeOriginAction(myThis, myNmbr1, myNmbr2, myNmbr3);
        }
        crtNodePnts(myThis);
    }

    /**
     *
     * @param myBoundaryAction
     * @param myThis
     * @param myPoint1
     * @param myIndex0
     * @param myNmbr
     * @param myIndex1
     * @param boundaryCheck
     */
    protected void nodeInitActn(PntInsrtActn myBoundaryAction, Pnt myThis, Pnt myPoint1, int myIndex0, int myNmbr, int myIndex1, boolean boundaryCheck) {
        if (boundaryCheck == true && myBoundaryAction != null) {
            myBoundaryAction.nodeInitAction(myThis, myPoint1, myIndex0, myNmbr, myIndex1);
        }
        crtNodePnts(myPoint1);
        crtLinePnts(myThis, myPoint1);
        glblMsh.add(myPoint1);
    }

    /**
     *
     * @param myNodes
     */
    public void setNodes(Vector myNodes) {
        nodes = myNodes;
    }

    /**
     * Set normals associated with the point, which represents x,y,z vector direction.
     * @param myNormal floating point array 
     */
    public void setNormal(float myNormal[]) {
        normal = myNormal;
    }

    /**
     * Get surface normals associated with the point.
     * @return float[] representation of surface normal.
     */
    public float[] getNormal() {
        return normal;
    }

    /**
     * The function is responsible for setting the point connected in the positive
     * x direction.
     * @param myPoint represents a point (Pnt) object.
     */
    public void setPosXDrctn(Pnt myPoint) {
        posXDrctn = myPoint;
    }
    /**
     * The method is responsible for setting a point (Pnt) connected in the negative
     * X direction.
     * @param myPoint 
     */
    public void setNegXDrctn(Pnt myPoint) {
        negXDrctn = myPoint;
    }
    /**
     * The method is responsible for setting the point which is connected in 
     * the Y direction.
     * @param myPoint represents point (Pnt) object.
     */
    public void setPosYDrctn(Pnt myPoint) {
        posYDrctn = myPoint;
    }

    /**
     * The function sets the point associated in the negative Y direction.
     * @param myPoint represents a point object of type Pnt.
     */
    public void setNegYDrctn(Pnt myPoint) {
        negYDrctn = myPoint;
    }

    /**
     * The method is responsible for setting the point associated with
     * the positive Z direction.
     * @param myPoint represents a point object (Pnt).
     */
    public void setPosZDrctn(Pnt myPoint) {
        posZDrctn = myPoint;
    }

    /**
     *
     * @param myPoint
     */
    public void setNegZDrctn(Pnt myPoint) {
        negZDrctn = myPoint;
    }

    /**
     * The method gets the point object (Pnt) associated with the positive X 
     * direction.
     * @return point object (Pnt)
     */
    public Pnt getPosXDrctn() {
        return posXDrctn;
    }

    /**
     * The method gets the point object associated with the negative X direction.
     * @return point object (Pnt)
     */
    public Pnt getNegXDrctn() {
        return negXDrctn;
    }

    /**
     * The method gets the point object (Pnt) associated with the Y direction.
     * @return The method returns a point object (Pnt).
     */
    public Pnt getPosYDrctn() {
        return posYDrctn;
    }

    /**
     * The function is responsible for getting the point object (Pnt) associated
     * with the negative Y direction.
     * @return The return value is of type Pnt (point object).
     */
    public Pnt getNegYDrctn() {
        return negYDrctn;
    }

    /**
     * This method gets the point object (Pnt) associated with the positive Z
     * direction.
     * @return The point object (Pnt) is returned.
     */
    public Pnt getPosZDrctn() {
        return posZDrctn;
    }

    /**
     * The function gets the point object associated with the negative Z
     * direction.
     * @return The return object is of type Pnt.
     */
    public Pnt getNegZDrctn() {
        return negZDrctn;
    }

    /**
     * The get coordinate completion method returns true if all possible 
     * connections exist; and false if remaining connections are possible.
     * @return
     */
    public boolean getCrdntCmpltn() {
        return crdntCmpltn;
    }

    /** 
     * Setting the coordinate completion method to true indicates that all 
     * surrounding nodes are connected; false indicates that remaining connections
     * exist.
     * @param aCrdntCmpltn The parameter is of type boolean.
     */
    public void setCrdntCompltn(boolean aCrdntCmpltn) {
        crdntCmpltn = aCrdntCmpltn;
    }

    /**
     * The method is true when a coordinate has been assigned; and false otherwise.
     * @param aCoordinated This parameter is type boolean.
     */
    public void setCoordinated(boolean aCoordinated) {
        coordinated = aCoordinated;
    }
    /**
     * The method gets the coordinated flag.  The value indicates if the point 
     * object (Pnt) has an associated coordinate.
     * @return The method returns type boolean.
     */
    public boolean getCoordinated() {
        return coordinated;
    }

    /**
     * This set method assigns the coordinate values to this object (Pnt).
     * @param x1Coordinate This parameter represents the x (r, ..., etc.) coordinate.
     * @param yCoordinate This parameter represents the y (phi, ..., etc.) coordinate.
     * @param zCoordinate  This parameter represents the z (theta, ..., etc.) coordinate.
     */
    public void setPoint(float x1Coordinate, float yCoordinate, float zCoordinate) {
        x1 = x1Coordinate;
        y1 = yCoordinate;
        z1 = zCoordinate;
    }
    /**
     * The method gets the radius associated with the point object (Pnt).
     * @return The return value is a floating point number.
     */
    public float getRadius() {
        return radius;
    }

    /**
     * The method sets the radius of this object (Pnt).
     * @param aRadius This parameter is defined as a floating point type.
     */
    public void setRadius(float aRadius) {
        radius = aRadius;
    }
    /**
     * This method returns the x value associated with this point.
     * @return The return type is float.
     */
    public float getX1() {
        return x1;
    }

    /**
     * The function returns the y value associated with the Pnt (point object).
     * @return The return values is defined as type float.
     */
    public float getY1() {
        return y1;
    }

    /**
     * This get method returns the z value associated with the Pnt (point object).
     * @return The return value is of type float.
     */
    public float getZ1() {
        return z1;
    }
    /**
     * The method set the x value associated the Pnt (point object).
     * @param aX1 The parameter is defined as type float and represents 
     * the x value.
     */
    public void setX1(float aX1) {
        x1 = aX1;
    }

    /**
     * The method set the y value associated the Pnt (point object).
     * @param aY1 The parameter is defined as type float and represents 
     * the y value.
     */
    public void setY1(float aY1) {
        y1 = aY1;
    }

    /**
     * The method set the z value associated the Pnt (point object).
     * @param aZ1 The parameter is defined as type float and represents 
     * the z value.
     */
    public void setZ1(float aZ1) {
        z1 = aZ1;
    }
    public String toString() {
        return "[" + x1 + ", " + y1 + "," + z1 + "]";
    }
    /**
     * 
     * @return
     */
    protected Pnt createDataPoint() {
        Pnt aPoint = new Pnt();
        //aPoint.setColor(getColor());
        //aPoint.setColor(0xffbdbdbd);        
        aPoint.setColor(0x0000ff00);                
        return aPoint; 
    }
    /**
     *
     * @return
     */
    protected Pnt createPoint() {
        Pnt aPoint = new Pnt();
        //aPoint.setColor(getColor());
        //aPoint.setColor(0xffbdbdbd);                
        aPoint.setColor(0x0000ff00);                        
        return aPoint;
    }
    /**
     * This method is responsible for increasing the x value of the 
     * associated Pnt (point object).  Each time the method is called
     * the PntInsrtActn (point insert action) is also executed.
     * @param myBoundaryAction The parameter is defined as type PntInsrtActn 
     * (point insert action).  
     */
    public void increaseX(PntInsrtActn myBoundaryAction) {
        int anIndex = 0;
        Pnt myPoint1 = null;
        Pnt myPoint2 = (Pnt) getDataObject();
        Pnt myPoint3 = createDataPoint();

        if (myPoint3 == null || myPoint2 == null) {
            return;
        }

        myPoint3.setX1(myPoint2.getX1() + getDeltaX());
        myPoint3.setY1(myPoint2.getY1());
        myPoint3.setZ1(myPoint2.getZ1());
        myPoint3.setColor(myPoint2.getColor());

        boolean boundaryCheck = checkBoundary(myPoint3);
        if (boundaryCheck == true) {
            myPoint3.setIndex1(getIndex1() + 1);
            myPoint3.setIndex2(getIndex2());
            myPoint3.setIndex3(getIndex3());
            myPoint1 = createPoint();

            myPoint1.setDataObject(myPoint3);
            myPoint1.setPoint(myPoint3.getX1(), myPoint3.getY1(), myPoint3.getZ1());
            myPoint1.setIndex1(myPoint3.getIndex1());
            myPoint1.setIndex2(myPoint3.getIndex2());
            myPoint1.setIndex3(myPoint3.getIndex3());
            myPoint1.setColor(myPoint3.getColor());
            myPoint1.setCoordinated(true);
            anIndex = getAdjcntVertices().size();
            myPoint1.setTreeIndex(anIndex);
            myPoint1.setEnvrnmntVar(myPoint2.getEnvrnmntX(), myPoint2.getEnvrnmntY(), myPoint2.getEnvrnmntZ());
            myPoint3.setEnvrnmntVar(myPoint2.getEnvrnmntX(), myPoint2.getEnvrnmntY(), myPoint2.getEnvrnmntZ());
            connectVertices2(myPoint1);
            myPoint2.connectVertices2(myPoint3);
            glblNmbr = glblNmbr + 1;
            myPoint3.setCounter(glblNmbr);
            myPoint1.setCounter(glblNmbr);
            myPoint2.setCounter(glblNmbr);
            nodeInitActn(myBoundaryAction, this, myPoint1, anIndex, 0, anIndex + 1, boundaryCheck);
        }
    }

    /**
     * This method is responsible for decreasing the x value of the 
     * associated Pnt (point object).  Each time the method is called
     * the PntInsrtActn (point insert action) is also executed.
     * @param myBoundaryAction The parameter is defined as type PntInsrtActn 
     * (point insert action).  
     */
    public void decreaseX(PntInsrtActn myBoundaryAction) {
        int anIndex = 0;
        Pnt myPoint1 = null;
        Pnt myPoint2 = (Pnt) getDataObject();
        Pnt myPoint3 = createDataPoint();

        if (myPoint3 == null || myPoint2 == null) {
            return;
        }

        myPoint3.setX1(myPoint2.getX1() - getDeltaX());
        myPoint3.setY1(myPoint2.getY1());
        myPoint3.setZ1(myPoint2.getZ1());
        myPoint3.setColor(myPoint2.getColor());

        boolean boundaryCheck = checkBoundary(myPoint3);
        if (boundaryCheck == true) {
            myPoint3.setIndex1(getIndex1() - 1);
            myPoint3.setIndex2(getIndex2());
            myPoint3.setIndex3(getIndex3());
            myPoint1 = createPoint();
            myPoint1.setDataObject(myPoint3);
            myPoint1.setPoint(myPoint3.getX1(), myPoint3.getY1(), myPoint3.getZ1());
            myPoint1.setIndex1(myPoint3.getIndex1());
            myPoint1.setIndex2(myPoint3.getIndex2());
            myPoint1.setIndex3(myPoint3.getIndex3());
            myPoint1.setColor(myPoint3.getColor());
            myPoint1.setCoordinated(true);
            connectVertices2(myPoint1);
            anIndex = getAdjcntVertices().size();
            myPoint1.setTreeIndex(anIndex);
            myPoint1.setEnvrnmntVar(myPoint2.getEnvrnmntX(), myPoint2.getEnvrnmntY(), myPoint2.getEnvrnmntZ());
            myPoint3.setEnvrnmntVar(myPoint2.getEnvrnmntX(), myPoint2.getEnvrnmntY(), myPoint2.getEnvrnmntZ());
            myPoint2.connectVertices2(myPoint3);
            glblNmbr = glblNmbr + 1;
            myPoint3.setCounter(glblNmbr);
            myPoint1.setCounter(glblNmbr);
            myPoint2.setCounter(glblNmbr);
            nodeInitActn(myBoundaryAction, this, myPoint1, anIndex, 0, anIndex + 1, boundaryCheck);
        }
    }

    /**
     * This method is responsible for decreasing the y value of the 
     * associated Pnt (point object).  Each time the method is called
     * the PntInsrtActn (point insert action) is also executed.
     * @param myBoundaryAction The parameter is defined as type PntInsrtActn 
     * (point insert action).  
     */
    public void decreaseY(PntInsrtActn myBoundaryAction) {
        int anIndex = 0;
        Pnt myPoint1 = null;
        Pnt myPoint2 = (Pnt) getDataObject();
        Pnt myPoint3 = createDataPoint();
        if (myPoint3 == null || myPoint2 == null) {
            return;
        }
        myPoint3.setX1(myPoint2.getX1());
        myPoint3.setY1(myPoint2.getY1() - getDeltaY());
        myPoint3.setZ1(myPoint2.getZ1());
        myPoint3.setColor(myPoint2.getColor());

        boolean boundaryCheck = checkBoundary(myPoint3);
        if (boundaryCheck == true) {
            myPoint3.setIndex1(getIndex1());
            myPoint3.setIndex2(getIndex2() - 1);
            myPoint3.setIndex3(getIndex3());
            myPoint1 = createPoint();
            //myPoint1 = new Point();
            myPoint1.setDataObject(myPoint3);
            myPoint1.setPoint(myPoint3.getX1(), myPoint3.getY1(), myPoint3.getZ1());
            myPoint1.setIndex1(myPoint3.getIndex1());
            myPoint1.setIndex2(myPoint3.getIndex2());
            myPoint1.setIndex3(myPoint3.getIndex3());
            myPoint1.setColor(myPoint3.getColor());
            myPoint1.setCoordinated(true);
            connectVertices2(myPoint1);
            anIndex = getAdjcntVertices().size();
            myPoint1.setTreeIndex(anIndex);
            myPoint1.setEnvrnmntVar(myPoint2.getEnvrnmntX(), myPoint2.getEnvrnmntY(), myPoint2.getEnvrnmntZ());
            myPoint3.setEnvrnmntVar(myPoint2.getEnvrnmntX(), myPoint2.getEnvrnmntY(), myPoint2.getEnvrnmntZ());
            myPoint2.connectVertices2(myPoint3);
            glblNmbr = glblNmbr + 1;
            myPoint3.setCounter(glblNmbr);
            myPoint1.setCounter(glblNmbr);
            myPoint2.setCounter(glblNmbr);
            nodeInitActn(myBoundaryAction, this, myPoint1, anIndex, 0, anIndex + 1, boundaryCheck);
            //System.out.println(myPoint3.getCounter()+","+myPoint3.getX1()+","+myPoint3.getY1()+","+myPoint3.getZ1());
        }
    }

    /**
     * This method is responsible for increasing the y value of the 
     * associated Pnt (point object).  Each time the method is called
     * the PntInsrtActn (point insert action) is also executed.
     * @param myBoundaryAction The parameter is defined as type PntInsrtActn 
     * (point insert action).  
     */
    public void increaseY(PntInsrtActn myBoundaryAction) {
        //Optimize by removing new object prior to boundary check
        int anIndex = 0;
        Pnt myPoint1 = null;
        Pnt myPoint2 = (Pnt) getDataObject();
        Pnt myPoint3 = createDataPoint();

        if (myPoint3 == null || myPoint2 == null) {
            return;
        }

        myPoint3.setX1(myPoint2.getX1());
        myPoint3.setY1(myPoint2.getY1() + getDeltaY());
        myPoint3.setZ1(myPoint2.getZ1());
        myPoint3.setColor(myPoint2.getColor());

        boolean boundaryCheck = checkBoundary(myPoint3);
        if (boundaryCheck == true) {
            myPoint3.setIndex1(getIndex1());
            myPoint3.setIndex2(getIndex2() + 1);
            myPoint3.setIndex3(getIndex3());
            myPoint1 = createPoint();
            myPoint1.setDataObject(myPoint3);
            myPoint1.setPoint(myPoint3.getX1(), myPoint3.getY1(), myPoint3.getZ1());
            myPoint1.setIndex1(myPoint3.getIndex1());
            myPoint1.setIndex2(myPoint3.getIndex2());
            myPoint1.setIndex3(myPoint3.getIndex3());
            myPoint1.setColor(myPoint3.getColor());
            myPoint1.setCoordinated(true);
            connectVertices2(myPoint1);
            anIndex = getAdjcntVertices().size();
            myPoint1.setTreeIndex(anIndex);
            myPoint1.setEnvrnmntVar(myPoint2.getEnvrnmntX(), myPoint2.getEnvrnmntY(), myPoint2.getEnvrnmntZ());
            myPoint3.setEnvrnmntVar(myPoint2.getEnvrnmntX(), myPoint2.getEnvrnmntY(), myPoint2.getEnvrnmntZ());
            myPoint2.connectVertices2(myPoint3);

            glblNmbr = glblNmbr + 1;
            myPoint3.setCounter(glblNmbr);
            myPoint1.setCounter(glblNmbr);
            myPoint2.setCounter(glblNmbr);


            nodeInitActn(myBoundaryAction, this, myPoint1, anIndex, 0, anIndex + 1, boundaryCheck);
            //System.out.println(myPoint3.getCounter() + "," + myPoint3.getX1() + "," + myPoint3.getY1() + "," + myPoint3.getZ1());
        }
    }

    /**
     * This method is responsible for increasing the z value of the 
     * associated Pnt (point object).  Each time the method is called
     * the PntInsrtActn (point insert action) is also executed.
     * @param myBoundaryAction The parameter is defined as type PntInsrtActn 
     * (point insert action).  
     */
    public void increaseZ(PntInsrtActn myBoundaryAction) {
        //Optimize by removing new object prior to boundary check
        int anIndex = 0;
        Pnt myPoint1 = null;
        Pnt myPoint2 = (Pnt) getDataObject();
        Pnt myPoint3 = createDataPoint();

        if (myPoint3 == null || myPoint2 == null) {
            return;
        }

        myPoint3.setX1(myPoint2.getX1());
        myPoint3.setY1(myPoint2.getY1());
        myPoint3.setZ1(myPoint2.getZ1() + getDeltaZ());
        myPoint3.setColor(myPoint2.getColor());

        boolean boundaryCheck = checkBoundary(myPoint3);
        if (boundaryCheck == true) {
            myPoint3.setIndex1(getIndex1());
            myPoint3.setIndex2(getIndex2());
            myPoint3.setIndex3(getIndex3() + 1);
            myPoint1 = createPoint();
            //myPoint1 = new Point();
            myPoint1.setDataObject(myPoint3);
            myPoint1.setPoint(myPoint3.getX1(), myPoint3.getY1(), myPoint3.getZ1());
            myPoint1.setIndex1(myPoint3.getIndex1());
            myPoint1.setIndex2(myPoint3.getIndex2());
            myPoint1.setIndex3(myPoint3.getIndex3());
            myPoint1.setColor(myPoint3.getColor());
            myPoint1.setCoordinated(true);
            connectVertices2(myPoint1);
            anIndex = getAdjcntVertices().size();
            myPoint1.setTreeIndex(anIndex);
            myPoint1.setEnvrnmntVar(myPoint2.getEnvrnmntX(), myPoint2.getEnvrnmntY(), myPoint2.getEnvrnmntZ());
            myPoint3.setEnvrnmntVar(myPoint2.getEnvrnmntX(), myPoint2.getEnvrnmntY(), myPoint2.getEnvrnmntZ());
            myPoint2.connectVertices2(myPoint3);

            glblNmbr = glblNmbr + 1;
            myPoint3.setCounter(glblNmbr);
            myPoint1.setCounter(glblNmbr);
            myPoint2.setCounter(glblNmbr);
            nodeInitActn(myBoundaryAction, this, myPoint1, anIndex, 0, anIndex + 1, boundaryCheck);

            System.out.println(myPoint3.getCounter() + "," + myPoint3.getX1() + "," + myPoint3.getY1() + "," + myPoint3.getZ1());
        }
    }

    /**
     * This method is responsible for decreasing the z value of the 
     * associated Pnt (point object).  Each time the method is called
     * the PntInsrtActn (point insert action) is also executed.
     * @param myBoundaryAction The parameter is defined as type PntInsrtActn 
     * (point insert action).  
     */
    public void decreaseZ(PntInsrtActn myBoundaryAction) {
        //Optimize by removing new object prior to boundary check
        int anIndex = 0;
        Pnt myPoint1 = null;
        Pnt myPoint2 = (Pnt) getDataObject();
        Pnt myPoint3 = createDataPoint();

        if (myPoint3 == null || myPoint2 == null) {
            return;
        }

        myPoint3.setX1(myPoint2.getX1());
        myPoint3.setY1(myPoint2.getY1());
        myPoint3.setZ1(myPoint2.getZ1() - getDeltaZ());
        myPoint3.setColor(myPoint2.getColor());

        boolean boundaryCheck = checkBoundary(myPoint3);
        if (boundaryCheck == true) {
            myPoint3.setIndex1(getIndex1());
            myPoint3.setIndex2(getIndex2());
            myPoint3.setIndex3(getIndex3() - 1);
            myPoint1 = createPoint();
            //myPoint1 = new Point();
            myPoint1.setDataObject(myPoint3);
            myPoint1.setPoint(myPoint3.getX1(), myPoint3.getY1(), myPoint3.getZ1());
            myPoint1.setIndex1(myPoint3.getIndex1());
            myPoint1.setIndex2(myPoint3.getIndex2());
            myPoint1.setIndex3(myPoint3.getIndex3());
            myPoint1.setColor(myPoint3.getColor());
            myPoint1.setCoordinated(true);
            connectVertices2(myPoint1);
            anIndex = getAdjcntVertices().size();
            myPoint1.setTreeIndex(anIndex);
            myPoint1.setEnvrnmntVar(myPoint2.getEnvrnmntX(), myPoint2.getEnvrnmntY(), myPoint2.getEnvrnmntZ());
            myPoint3.setEnvrnmntVar(myPoint2.getEnvrnmntX(), myPoint2.getEnvrnmntY(), myPoint2.getEnvrnmntZ());
            myPoint2.connectVertices2(myPoint3);

            glblNmbr = glblNmbr + 1;
            myPoint3.setCounter(glblNmbr);
            myPoint1.setCounter(glblNmbr);
            myPoint2.setCounter(glblNmbr);
            nodeInitActn(myBoundaryAction, this, myPoint1, anIndex, 0, anIndex + 1, boundaryCheck);
            //System.out.println(myPoint3.getCounter()+","+myPoint3.getX1()+","+myPoint3.getY1()+","+myPoint3.getZ1());
        }
    }
    //TODO change connect to addvertex & addvertex to insertVertex

    /**
     * This is an overriding method which also provides a call to update
     * boundary (updBndry).  This is a bi-direction doubly connected link.  
     * The in-degree and out-degree of both objects increase.
     * @param myVertex The parameter is of type Vertex.
     */
    public void connectVertices2(Vertex myVertex) {
        super.connectVertices2(myVertex);
        updtBndry((Pnt) myVertex);
    }

    /**
     * This is an overriding method which also provides a call to update
     * boundary (updBndry).  A single uni-direction connection is created
     * and the out-degree of this object increase by 1.
     * @param myVertex The parameter is of type Vertex; and the in-degree of
     * this object increases by 1.
     */
    public void connectVertices(Vertex myVertex) {
        super.connectVertices(myVertex);
        updtBndry((Pnt) myVertex);
    }

    /**
     * The method sets the origin of the Pnt (point object).
     * @param x1 The x coordinate represents the first orthogonal coordinate.
     * @param y1 The y coordinate represents the second orthogonal coordinate.
     * @param z1 The x coordinate represents the third orthogonal coordinate.
     * @param myBoundaryAction The parameter is type PntInsrtActn; and is call
     * each time setOrigin is called.
     */
    public void setOrigin(float x1, float y1, float z1, PntInsrtActn myBoundaryAction) {
        //TODO update to allow shape to point to all points
        Pnt myPoint3 = createDataPoint();
        myPoint3.setX1(x1);
        myPoint3.setY1(y1);
        myPoint3.setZ1(z1);

        boolean boundaryCheck = checkBoundary(myPoint3);
        if (boundaryCheck == true) {
            myPoint3.setEnvrnmntVar(getEnvrnmntX(), getEnvrnmntY(), getEnvrnmntZ());
            setDataObject(myPoint3);
            setX1(x1);
            setY1(y1);
            setZ1(z1);
            setIndex1(0);
            setIndex2(0);
            setIndex3(0);
            setCoordinated(true);
            setVisited(true);
            setTreeIndex(0);
            setCounter(glblNmbr);
            myPoint3.setCounter(glblNmbr);
        }
        nodeOrgnActn(myBoundaryAction, this, 0, 0, 1, boundaryCheck);
        updtBndry(this);
    }
    public float getMass() {
        return mass;
    }

    public float getxVlcty() {
        return xVlcty;
    }

    public float getyVlcty() {
        return yVlcty;
    }

    public float getzVlcty() {
        return zVlcty;
    }
    public float getNormXVlcty()
    {
        float xVel = (float)(xVlcty/Math.sqrt(xVlcty*xVlcty+yVlcty*yVlcty+zVlcty*zVlcty*zVlcty));
        //System.out.println("Pnt: xVel = "+xVel);
        return xVel;
    }
    public float getNormYVlcty()
    {
        float yVel = (float)(yVlcty/Math.sqrt(xVlcty*xVlcty+yVlcty*yVlcty+zVlcty*zVlcty*zVlcty));
        //System.out.println("Pnt: yVel = "+yVel);
        return yVel;
    }    
    public float getNormZVlcty()
    {
        float zVel = (float)(zVlcty/Math.sqrt(xVlcty*xVlcty+yVlcty*yVlcty+zVlcty*zVlcty*zVlcty));
        return zVel;
    }    
    public void setMass(float mass) {
        this.mass = mass;
    }

    public void setXVlcty(float xVlcty) {
        this.xVlcty = xVlcty;
    }

    public void setYVlcty(float yVlcty) {
        this.yVlcty = yVlcty;
    }

    public void setzVlcty(float zVlcty) {
        this.zVlcty = zVlcty;
    }        
}