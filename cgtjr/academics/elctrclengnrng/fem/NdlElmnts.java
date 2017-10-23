package cgtjr.academics.elctrclengnrng.fem;

/**
 * @author clayton g thomas jr
 */
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.math.geometry.elmnt.LinePnts;
import cgtjr.academics.math.geometry.elmnt.Nodes;
import java.util.Vector;

/**
 * The nodal element class (NdlElmnts) is responsible for managing and manipulating 
 * the nodes and elements collected from the WTS algorithm.  The class is capable of 
 * organizing 1D, 2D, and 3D elements associated with the mesh structure.  The elements and nodes
 * are process for solving the FEM equation.
 * procedural call.
 */
public class NdlElmnts {

    private Vector prscrbBndrs;
    private int prscrbNdCnt;
    private int prscrbSrcCnt;
    private int prscrbNdIndx[];
    private int prscrbSrcIndx[];
    private double prscrbNdVls[];
    private double prscrbSrcVls[];
    private int ndLstIndx[];
    private double ndValue[];
    private double elmntCffcnt[][];
    private double glblCffcnt[][];
    private int elmntLclNds[][];
    private int frNdIndx[];
    private double xGlbl[];
    private double yGlbl[];
    private double zGlbl[];
    private int frNdCnt;
    private int nodeCnt;
    private int glblSize;
    private int lclSize;
    private int bndrElmnts[][];
    private int elmntCnt;

    /**
     * Constructs a nodal element object.  The object represents a mesh structure
     * which encapsulates elements and their associated node. 1D, 2D, or 3D mesh structures
     * are generated dependent of the local size of the elements.
     * @param myShape represents the shape point object containing the Nodes and elements.
     * @param myShpBndry represents a shape boundary object.
     */
    public NdlElmnts(ShapePnt myShape, ShpBndry myShpBndry) {
        int aGlblSize = getGlblSize(myShape);
        int aLclSize = getLclSize(myShape);
        prscrbBndrs = myShpBndry.getBoundaryShape();
        contructor(aGlblSize, aLclSize);
        insrtElmntLclNds(myShape);
        updtPrscrbNd(myShape, myShpBndry);
    }
    /**
     * Constructs a nodal element object.  The object represents a mesh structure
     * which encapsulates elements and their associated node. 1D, 2D, or 3D mesh structures
     * are generated dependent of the local size of the elements.
     * @param myGlblSize represents maximum number of nodes within a mesh structure.
     * @param myLclSize represents the number of nodes within an element.
     */
    public NdlElmnts(int myGlblSize, int myLclSize) {
        contructor(myGlblSize, myLclSize);
    }

    private void contructor(int myGlblSize, int myLclSize) {
        glblSize = myGlblSize;
        if (myLclSize == 3) {
            glblSize = 2 * myGlblSize;
        }
        lclSize = myLclSize;
        elmntCffcnt = new double[lclSize][lclSize];
        //glblCffcnt = new double[glblSize][glblSize];
        elmntLclNds = new int[glblSize][lclSize];
        prscrbNdIndx = new int[glblSize];
        prscrbNdVls = new double[glblSize];
        prscrbSrcIndx = new int[glblSize];
        prscrbSrcVls = new double[glblSize];
        ndLstIndx = new int[glblSize];
        frNdIndx = new int[glblSize];
        xGlbl = new double[glblSize];
        yGlbl = new double[glblSize];
        zGlbl = new double[glblSize];
        ndValue = new double[glblSize];
        ////bndrElmnts = new int[glblSize][2];
        //prscrbBndrs = new Vector();
    }

    /**
     * The insrtPrscrbNdIndx() method is responsible for inserting prescribe
     * values.
     * @param myShape represents a shape point object.
     * @return integer representation of the element local number size.
     */
    public static int getLclSize(ShapePnt myShape) {
        int lclSize = 1;
        double aWidth = myShape.getXMax() - myShape.getXMin();
        double aHeight = myShape.getYMax() - myShape.getYMin();
        double aLength = myShape.getZMax() - myShape.getZMin();
        if (aWidth != 0) {
            lclSize *= 2;
        }
        if (aHeight != 0) {
            lclSize *= 2;
        }
        if (aLength != 0) {
            lclSize *= 2;
        }
        return lclSize;
    }

    /**
     * Set the prescribe boundaries of the mesh structure.
     * @param myPrscrbBndrs represents a Vector containing prescribe Nodes.
     */
    public void setPrscrbBndrs(Vector myPrscrbBndrs) {
        prscrbBndrs = myPrscrbBndrs;
    }

    /**
     * Retrieves the vector containing Nodes representing the prescribe boundaries.
     * @return vector containing prescribe Nodes
     */
    public Vector getPrscrbBndrs() {
        return prscrbBndrs;
    }

    /**
     * gets the number of global indexes associated with the mesh structure.
     * @param myShape representation of shape point object.
     * @return integer representation of global number count.
     */
    public int getGlblSize(ShapePnt myShape) {
        Vector aVector = (Vector) myShape.getNodes();
        int aGlblSize = getGlblSize(aVector);
        return aGlblSize;
    }

    /**
     * gets the number of global indexes associated with the mesh structure.
     * @param myVector represents the vector of objects (type Nodes).
     * @return integer representation of global number count.
     */
    public int getGlblSize(Vector myVector) {
        int aGlblSize = myVector.size();
        return aGlblSize;
    }

    /**
     * Insert the prescribe node values
     * @param myVector represents a vector of prescribe nodes global indexes.
     */
    public void insrtPrscrbNdIndx(Vector myVector) {
        int aSize = myVector.size();
        double aValue = 0;
        Pnt aPoint = null;
        int anIndex = 0;

        for (int i = 0; i < aSize; i++) {
            aPoint = (Pnt) myVector.elementAt(i);
            aValue = aPoint.getValue();
            anIndex = aPoint.getCounter();
            insrtPrscrbNdIndx(anIndex, aValue);
        }
    }

    /**
     * The insrtPrscrbNdIndx() method is responsible for inserting prescribe
     * node values.
     *
     * @param myIndex 
     * @param myValue
     */
    public void insrtPrscrbNdIndx(int myIndex, double myValue) {
        //System.out.println("NdElmnts: myIndex = " + myIndex + ",prscrbNdCnt = " + prscrbNdCnt + ",nodeCount=" + nodeCnt);
        prscrbNdIndx[prscrbNdCnt] = myIndex;
        prscrbNdVls[prscrbNdCnt] = myValue;
        prscrbNdCnt = prscrbNdCnt + 1;
        ndLstIndx[nodeCnt] = myIndex;
        nodeCnt = nodeCnt + 1;
    }

    /**
     * The insrtPrscrbSrcIndx method is utilized to insert the index associated
     * with the prescribe excitation source values.
     *
     * @param myVector
     */
    public void insrtPrscrbSrcIndx(Vector myVector) {
        int aSize = myVector.size();
        double aValue = 0;
        Pnt aPoint = null;
        int anIndex = 0;

        for (int i = 0; i < aSize; i++) {
            aPoint = (Pnt) myVector.elementAt(i);
            aValue = aPoint.getValue();
            anIndex = aPoint.getCounter();
            insrtPrscrbSrcIndx(anIndex, aValue);
        }
    }

    /**
     * The insrtPrscrbSrcIndx method is utilized to insert the index associated
     * with the prescribe excitation source values.
     *
     * @param myIndex
     * @param myValue
     */
    public void insrtPrscrbSrcIndx(int myIndex, double myValue) {
        prscrbSrcIndx[prscrbSrcCnt] = myIndex;
        prscrbSrcVls[prscrbSrcCnt] = myValue;
        prscrbSrcCnt = prscrbSrcCnt + 1;
    }

    /**
     * The insrtPrscrbNdIndx method is utilized to insert the index associated
     * with the prescribe node values.
     *
     * @param myIndex
     */
    public void insrtPrscrbNdIndx(int myIndex) {
        prscrbNdIndx[prscrbNdCnt] = myIndex;
        prscrbNdCnt = prscrbNdCnt + 1;
        ndLstIndx[nodeCnt] = myIndex;
        nodeCnt = nodeCnt + 1;
    }

    /**
     * The "insrtXYZByIndex" method is responsible for inserting the global
     * coordinates corresponding to the coordinates system (cartesian,
     * cylindrical, spherical, etc.).
     * @param myVector
     */
    public void insrtXYZByIndex(Vector myVector) {
        Pnt aPoint = null;
        int aLength = myVector.size();
        float aX = 0;
        float aY = 0;
        float aZ = 0;

        for (int i = 0; i < aLength; i++) {
            aPoint = (Pnt) myVector.elementAt(i);
            aX = aPoint.getX1();
            aY = aPoint.getY1();
            aZ = aPoint.getZ1();
            insrtXGlbl(aX, i);
            insrtYGlbl(aY, i);
            insrtZGlbl(aZ, i);
        }
    }

    /**
     * Insert the global coordinates by global numbers.
     * @param myVector 
     */
    public void insrtXYZByGlblNmbr(Vector myVector) {
        Pnt aPoint = null;
        int aLength = myVector.size();
        float aX = 0;
        float aY = 0;
        float aZ = 0;
        int aCounter = 0;
        for (int i = 0; i < aLength; i++) {
            aPoint = (Pnt) myVector.elementAt(i);
            aX = aPoint.getX1();
            aY = aPoint.getY1();
            aZ = aPoint.getZ1();
            aCounter = aPoint.getCounter();

            insrtXGlbl(aX, aCounter);
            insrtYGlbl(aY, aCounter);
            insrtZGlbl(aZ, aCounter);
        }
    }

    /**
     * The insrtElmntLclNds function is used to insert the element local nodes
     * in a counter clockwise fashion.
     * @param myShape represents a shape point object.
     */
    private void insrtElmntLclNds(ShapePnt myShape) {
        Vector aVector = null;
        if (lclSize == 2) {
            aVector = myShape.getEdges();
            System.out.println("NdlElements : line elements");
        } else if (lclSize == 3) {
            aVector = myShape.rtrvElmnts();
        } else if (lclSize == 4) {
            aVector = myShape.getQuadElmnts();
        } else if (lclSize == 8) {
            aVector = myShape.getHxhdrlElmnts();
        }
        insrtElmntLclNds(aVector);
    }

    /**
     * The method is responsible for inserting local nodes into elements.
     * @param myVector represents a vector of objects (type Nodes).
     */
    public void insrtElmntLclNds(Vector myVector) {
        Nodes smNodes = null;
        Pnt points[] = null;

        int aLength = myVector.size();
        int glblNmbr = 0;
        System.out.println("NdlElmnts : size = " + aLength);
        elmntCnt = aLength;
        for (int i = 0; i < aLength; i++) {
            smNodes = (Nodes) myVector.elementAt(i);
            points = smNodes.getPoints();

            for (int j = 0; j < lclSize; j++) {
                glblNmbr = points[j].getCounter();
                insrtElmntsNds(glblNmbr, i, j);
            }
        }
    }

    /**
     * The insrtElmntLclNds function is used to insert the element local nodes
     * in a counter clockwise fashion.
     * @param myGlblNmbr represents an integer value corresponding to the global number.
     * @param myElmntNmbr represents the element number.
     * @param myLclIndx represents the local index number.
     */
    public void insrtElmntsNds(int myGlblNmbr, int myElmntNmbr, int myLclIndx) {
        elmntLclNds[myElmntNmbr][myLclIndx] = myGlblNmbr;

    }

    /**
     * The insrtBndrElmnts method is responsible for inserting the global number
     * associated with the element number and local number.
     * @param myVector represents a vector of boundary edge (line elements).
     */
    public void insrtBndrElmnts(Vector myVector) {
        LinePnts aLinePnts = null;
        int aLength = myVector.size();
        int glblNmbr0 = 0;
        int glblNmbr1 = 0;

        for (int i = 0; i < aLength; i++) {
            aLinePnts = (LinePnts) myVector.elementAt(i);
            glblNmbr0 = aLinePnts.getPoint0().getCounter();
            glblNmbr1 = aLinePnts.getPoint1().getCounter();
            insrtBndrElmnts(glblNmbr0, i, 0);
            insrtBndrElmnts(glblNmbr1, i, 1);
        }
    }

    /**
     * The insrtFrNdIndx is utilized to insert the free nodes associated with
     * the mesh structure.
     * @param myIndex
     */
    public void insrtFrNdIndx(int myIndex) {
        frNdIndx[frNdCnt] = myIndex;
        frNdCnt = frNdCnt + 1;
        ndLstIndx[nodeCnt] = myIndex;
        nodeCnt = nodeCnt + 1;
    }

    /**
     * The insrtFrNdIndx is utalized to insert the free nodes associated with
     * the mesh structure.
     *
     * @param myIndex
     * @param myValue
     */
    public void insrtFrNdIndx(int myIndex, double myValue) {
        ndValue[myIndex] = myValue;
        frNdIndx[frNdCnt] = myIndex;
        frNdCnt = frNdCnt + 1;
        ndLstIndx[nodeCnt] = myIndex;
        nodeCnt = nodeCnt + 1;
    }

    /**
     * The insrtNdLstIndx implementation is used to insert all node indexes
     * associated with the generated mesh.
     *
     * @param myIndex
     */
    public void insrtNdLstIndx(int myIndex) {
        ndLstIndx[nodeCnt] = myIndex;
        nodeCnt = nodeCnt + 1;
    }

    /**
     * The insrtNdValue function is used to insert all node indexes and values.
     *
     * @param myIndex
     * @param myValue
     */
    public void insrtNdValue(int myIndex, int myValue) {
        ndValue[myIndex] = myValue;
    }

    /**
     * The insrtBndrElmnts method is responsible for inserting the global number
     * associated with the element number and local number.
     *
     * @param myGlblNmbr
     * @param myElmntNmbr
     * @param myLclIndx
     */
    public void insrtBndrElmnts(int myGlblNmbr, int myElmntNmbr, int myLclIndx) {
        bndrElmnts[myElmntNmbr][myLclIndx] = myGlblNmbr;
    }

    /**
     * The insrtXGlbl function inserts the global x-coordinate and the
     * corresponding index.
     *
     * @param myValue
     * @param myIndex
     */
    public void insrtXGlbl(double myValue, int myIndex) {
        xGlbl[myIndex] = myValue;
    }

    /**
     * The insrtYGlbl function inserts the global y-coordinate and the
     * corresponding index.
     *
     * @param myValue
     * @param myIndex
     */
    public void insrtYGlbl(double myValue, int myIndex) {
        yGlbl[myIndex] = myValue;
    }

    /**
     * The insrtZGlbl function inserts the global z-coordinate and the
     * corresponding index.
     *
     * @param myValue
     * @param myIndex
     */
    public void insrtZGlbl(double myValue, int myIndex) {
        zGlbl[myIndex] = myValue;
    }

    /**
     * The getBndrElmnts method retrieves an array of boundary elements.
     *
     * @return
     */
    public int[][] getBndrElmnts() {
        return bndrElmnts;
    }

    /**
     * The getElmntLclNds method retrieves the array of local nodes associated
     * with the elements.
     *
     * @return
     */
    public int[][] getElmntLclNds() {
        return elmntLclNds;
    }

    /**
     * The getXGlbl retrieves the array of global x-coordiante values
     *
     * @return
     */
    public double[] getXGlbl() {
        return xGlbl;
    }

    /**
     * The getYGlbl retrieves the array of global y-coordiante values
     *
     * @return
     */
    public double[] getYGlbl() {
        return yGlbl;
    }

    /**
     * The getZGlbl function retrieves the array of global z-coordiante values.
     *
     * @return
     */
    public double[] getZGlbl() {
        return zGlbl;
    }

    /**
     * The getFrNdIndx method is responsible of retrieving the array of free
     * nodes.
     *
     * @return
     */
    public int[] getFrNdIndx() {
        return frNdIndx;
    }

    /**
     * The getNdLstIndx retrieves the array of nodes indexes associated with the
     * geometrical structure.
     *
     * @return
     */
    public int[] getNdLstIndx() {
        return ndLstIndx;
    }

    /**
     * The getFrNdCnt method is responsible for obtaining the free node count.
     *
     * @return
     */
    public int getFrNdCnt() {
        return frNdCnt;
    }

    /**
     * The getPrscrbNdCnt function is used for obtaining the prescribe node
     * count.
     *
     * @return
     */
    public int getPrscrbNdCnt() {
        return prscrbNdCnt;
    }

    /**
     * The getNodeCnt function is responsible for acquiring the node count.
     *
     * @return
     */
    public int getNodeCnt() {
        return nodeCnt;
    }

    /**
     * The getElmntCffcnt method is used to acquire the element coefficients
     * matrix.
     *
     * @return
     */
    public double[][] getElmntCffcnt() {
        return elmntCffcnt;
    }

    /**
     * The getGlblCffcnt function is implemented to retrieve the global
     * coefficient matrix.
     *
     * @return
     */
    public double[][] getGlblCffcnt() {
        return glblCffcnt;
    }

    /**
     * The getElmntCnt method is called to acquire the total number of elements.
     *
     * @return
     */
    public int getElmntCnt() {
        return elmntCnt;
    }

    /**
     * The getGlblSize function is called to acquire the global size.
     *
     * @return
     */
    public int getGlblSize() {
        return glblSize;
    }

    /**
     * The getLclSize method is implemented to acquire hte local size of the
     * elements.
     *
     * @return
     */
    public int getLclSize() {
        return lclSize;
    }

    /**
     * The getPrscrbSrcCnt is utalized to obtain the count for the prescribe
     * source nodes.
     *
     * @return
     */
    public int getPrscrbSrcCnt() {
        return prscrbSrcCnt;
    }

    /**
     * The getPrscrbSrcVls method is responsible for retrieving the array of
     * presribe source values.
     *
     * @return
     */
    public double[] getPrscrbSrcVls() {
        return prscrbSrcVls;
    }

    /**
     * The getPrscrbSrcIndx function is utalized for acquiring the array of
     * source index values.
     *
     * @return
     */
    public int[] getPrscrbSrcIndx() {
        return prscrbSrcIndx;
    }

    /**
     * The getPrscrbNdVls function is implemented to retrieve the arra of
     * prescribe node values.
     *
     * @return
     */
    public double[] getPrscrbNdVls() {
        return prscrbNdVls;
    }

    /**
     * The getPrscrbNdIndx method is called to retrieve the array of prescribe
     * node indexes.
     *
     * @return
     */
    public int[] getPrscrbNdIndx() {
        return prscrbNdIndx;
    }

    /**
     * The rtrvNdValue method is utalized to retrieve the node value
     * corresponding to a specified index.
     *
     * @param myIndex
     * @return
     */
    public double rtrvNdValue(int myIndex) {
        return ndValue[myIndex];
    }

    /**
     * The rtrvNdLstIndx function is called to retrieve the list of nodes in
     * their order of node generation.
     *
     * @param myIndex
     * @return
     */
    public int rtrvNdLstIndx(int myIndex) {
        return ndLstIndx[myIndex];
    }

    /**
     * The setElmntCffcntVl method is implemented to set the element coefficient
     * matrix values.
     *
     * @param myValue
     * @param myIndex1
     * @param myIndex2
     */
    public void setElmntCffcntVl(double myValue, int myIndex1, int myIndex2) {
        elmntCffcnt[myIndex1][myIndex2] = myValue;
    }

    /**
     * The setLclSize function is called to set the local element node size
     *
     * @param myLclSize
     */
    public void setLclSize(int myLclSize) {
        lclSize = myLclSize;
    }

    /**
     * The setGlblSize metod is implemented to set the maximum global node
     * count.
     *
     * @param myGlblSize
     */
    public void setGlblSize(int myGlblSize) {


        glblSize = myGlblSize;
    }

    /**
     * The update prescribe node function provides the ability to associate a
     * new prescribe boundary with a shape point object.
     * @param myShape represents a shape point object
     * @param myShpBndry represents a shape boundary object.
     */
    public final void updtPrscrbNd(ShapePnt myShape, ShpBndry myShpBndry) {
        prscrbBndrs = myShpBndry.getBoundaryShape();
        updtPrscrbNd(myShape, prscrbBndrs);
    }

    /**
     * Overloads the update prescribe node metho with the same functionality.
     * @param myShape represents a shape point object
     * @param myVector represents a vector of boundary nodes.
     */
    public void updtPrscrbNd(ShapePnt myShape, Vector myVector) {
        Vector aVector = myShape.getNodes();
        Pnt aPoint = null;
        int aSize = aVector.size();
        for (int i = 0; i < aSize; i++) {
            aPoint = (Pnt) aVector.elementAt(i);
            updtPrscrbNd(aPoint, myVector);
        }
    }

    /**
     * The update prescribe node function provides the ability to associate a
     * new prescribe boundary with a shape point object.  Also see overload methods.
     * @param myPoint represents point object.
     * @param myVector represents vector object.
     */
    public void updtPrscrbNd(Pnt myPoint, Vector myVector) {
        prscrbBndrs = myVector;
        float c1 = myPoint.getX1();
        float c2 = myPoint.getY1();
        float c3 = myPoint.getZ1();

        int anIndex = myPoint.getCounter();

        insrtXGlbl(c1, anIndex);
        insrtYGlbl(c2, anIndex);
        insrtZGlbl(c3, anIndex);

        int aSize = myVector.size();
        for (int i = 0; i < aSize; i++) {
            ShpBndry aShpBndry = (ShpBndry) myVector.elementAt(i);
            if (aShpBndry.isInBndry(c1, c2, c3) == true) {
                double aValue = aShpBndry.getValue();
                myPoint.setValue(aValue);
                insrtPrscrbNdIndx(anIndex, aValue);

                return;
            }
        }
        insrtFrNdIndx(anIndex);
    }
}