package cgtjr.academics.math.geometry.crdntepnts;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.general.ColorSpectra;
import cgtjr.academics.general.MtrlVar;
import cgtjr.academics.math.geometry.elmnt.HxhdrlPnts;
import cgtjr.academics.math.geometry.elmnt.LinePnts;
import cgtjr.academics.math.geometry.elmnt.QuadPnts;
import cgtjr.academics.math.graph.Vertex;
import cgtjr.academics.math.lnralgbra.Trnsfrm;
import java.util.Enumeration;
import java.util.Vector;

/**
 * The shape point class provides 3D, 2D, and 1D tessellation of arbitrary 
 * geometrical domains.  Volume elements are generated in the form of hexahedrals.
 * Two dimensional surfaces are formed from quadrilaterals.  Triangular elements
 * are created from dividing quadrilaterals.  Line elements represent 1D boundaries.
 * @author clayton g thomas jr
 */
public class ShapePnt extends Pnt {

    private Pnt origin;
    private Trnsfrm transform;
    private Vector quadElmnts;
    private Vector trnglElmnts;
    private Vector hxhdrlElmnts;
    private int updtCnt;
    private int crtCnt;
    private int ttlCnt;
    private MtrlVar mtrlVar;
    private ShapePntFnshLstnr shpPntFnshLstnr;
    /**
     * The constructor initializes a new shape point (ShapePnt) object.
     */    
    public ShapePnt() {
        quadElmnts = new Vector();
        trnglElmnts = new Vector();
        hxhdrlElmnts = new Vector();
        glblMsh = new Vector();
    }
    /**
     * The constructor instantiates a shape point (ShapePnt) object with an initial
     * orthogonal position.
     * @param x The parameter represents the 1st orthogonal coordinate.
     * @param y The parameter represents the 2nd orthogonal coordinate.
     * @param z The parameter represents the 3rd orthogonal coordinate.
     */
    public ShapePnt(float x, float y, float z) {
        super(x, y, z);
        quadElmnts = new Vector();
        hxhdrlElmnts = new Vector();
        glblMsh = new Vector();    
    }

    /**
     * The method is responsible for setting the transform for this shape.  
     * Full implementation is not currently supported.
     * @param myTrnsfrm The parameter is of type Trnsfrm (Transform).
     */
    public void setTransform(Trnsfrm myTrnsfrm) {
        transform = myTrnsfrm;
    }
   public void setMtrlVar(MtrlVar myMtrlVar)
   {
      if(myMtrlVar ==  null) return;
      mtrlVar = myMtrlVar;
      float r = mtrlVar.getDffs1Val();
      float g = mtrlVar.getDffs2Val();
      float b = mtrlVar.getDffs3Val();
      System.out.println("ShapePnt: r = "+r+", g = "+g+", b = "+b);
      int aColor = ColorSpectra.cnvrtRGBFLTToRGBINT(r,g,b);      
      setColor(aColor);
   }
   public MtrlVar getMtrlVar()
   {
      return mtrlVar;
   }      
    /**
     * The method is responsible for getting the transform associated with this
     * shape object (ShapePnt).
     * @return The method returns type Trnsfrm (Transform).
     */
    public Trnsfrm getTransform() {
        return transform;
    }
    private void insrtVrtx(Pnt myPoint) {
        super.addVertex(myPoint);
    }
    private Pnt crtHozVerOrDiagPnt(Pnt aPoint1, Pnt aPoint2) {
        return crtHozVerOrDiagPnt(this, aPoint1, aPoint2);
    }
    private Pnt crtHozVerOrDiagPnt(Pnt originPoint, Pnt aPoint1, Pnt aPoint2) {
        Pnt myPoint = null;
        if (aPoint2 == null) {
            myPoint = crtHorOrVerPnt(originPoint, aPoint1);
        } else {
            myPoint = crtDiagonalPnt(originPoint, aPoint1, aPoint2);
        }
        return myPoint;
    }
    private Pnt findCrdntCmplt6(boolean aCoordinated) {
        return findCrdntCmplt6(this, aCoordinated);
    }
    private Pnt findCrdntCmplt6(Vertex aPoint1, boolean aCoordinated) {
        return findCrdntCmplt6(aPoint1, aCoordinated, 0);
    }
    private Pnt findCrdntCmplt6(Vertex aPoint1, boolean aCoordinated, int myCount) {
        Pnt aPoint = null;
        Vector aVector = aPoint1.getAdjcntVertices();
        int aSize = aVector.size();
        int i = 0;
        if (i < aSize) {
            Pnt myPoint = (Pnt) aVector.elementAt(i);
            if (myPoint.getCrdntCmpltn() == aCoordinated) {
                aPoint = myPoint;
                if (i >= myCount) {
                    return aPoint;
                }
            }
            ++i;
        }
        if (i < aSize) {
            Pnt myPoint = (Pnt) aVector.elementAt(i);
            if (myPoint.getCrdntCmpltn() == aCoordinated) {
                aPoint = myPoint;
                if (i >= myCount) {
                    return aPoint;
                }
            }
            ++i;
        }
        if (i < aSize) {
            Pnt myPoint = (Pnt) aVector.elementAt(i);
            if (myPoint.getCrdntCmpltn() == aCoordinated) {
                aPoint = myPoint;
                if (i >= myCount) {
                    return aPoint;
                }
            }
            ++i;
        }
        if (i < aSize) {
            Pnt myPoint = (Pnt) aVector.elementAt(i);
            if (myPoint.getCrdntCmpltn() == aCoordinated) {
                aPoint = myPoint;
                if (i >= myCount) {
                    return aPoint;
                }
            }
            ++i;
        }
        if (i < aSize) {
            Pnt myPoint = (Pnt) aVector.elementAt(i);
            if (myPoint.getCrdntCmpltn() == aCoordinated) {
                aPoint = myPoint;
                if (i >= myCount) {
                    return aPoint;
                }
            }
            ++i;
        }
        if (i < aSize) {
            Pnt myPoint = (Pnt) aVector.elementAt(i);
            if (myPoint.getCrdntCmpltn() == aCoordinated) {
                aPoint = myPoint;
                if (i >= myCount) {
                    return aPoint;
                }
            }
            ++i;
        }
        if (i < aSize) {
            Pnt myPoint = (Pnt) aVector.elementAt(i);
            if (myPoint.getCrdntCmpltn() == aCoordinated) {
                aPoint = myPoint;
                if (i >= myCount) {
                    return aPoint;
                }
            }
            ++i;
        }
        return aPoint;
    }
    private Pnt findCrdntCmpltAll(boolean aCoordinated) {
        return findCrdntdCmpltAll(this, aCoordinated);
    }
    private Pnt findCrdntdCmpltAll(Vertex aPoint1, boolean aCoordinated) {
        boolean found = false;

        Enumeration myEnumeration = aPoint1.getAdjcntVertices().elements();
        while (myEnumeration.hasMoreElements()) {
            Pnt myPoint = (Pnt) myEnumeration.nextElement();
    
            if (myPoint.getCoordinated() == aCoordinated) {
                myPoint.setCoordinated(true);
                found = true;
                return myPoint;
            }
        }
        return null;
    }
    private Pnt crtDiagonalPnt(Pnt aPoint1, Pnt aPoint2) {
        return crtDiagonalPnt(this, aPoint1, aPoint2);
    }
    private Pnt crtDiagonalPnt(Pnt originPoint, Pnt aPoint1, Pnt aPoint2) {
        Pnt myPoint1 = createDataPoint();
        float x1 = ((Pnt) (aPoint1.getDataObject())).getX1() - ((Pnt) (originPoint.getDataObject())).getX1();
        float y1 = ((Pnt) (aPoint1.getDataObject())).getY1() - ((Pnt) (originPoint.getDataObject())).getY1();
        float z1 = ((Pnt) (aPoint1.getDataObject())).getZ1() - ((Pnt) (originPoint.getDataObject())).getZ1();
        float x2 = ((Pnt) aPoint1.getDataObject()).getX1() - ((Pnt) (aPoint2.getDataObject())).getX1();
        float y2 = ((Pnt) aPoint1.getDataObject()).getY1() - ((Pnt) (aPoint2.getDataObject())).getY1();
        float z2 = ((Pnt) aPoint1.getDataObject()).getZ1() - ((Pnt) (aPoint2.getDataObject())).getZ1();

        int x3 = ((Pnt) (aPoint1.getDataObject())).getIndex1() - ((Pnt) (originPoint.getDataObject())).getIndex1();
        int y3 = ((Pnt) (aPoint1.getDataObject())).getIndex2() - ((Pnt) (originPoint.getDataObject())).getIndex2();
        int z3 = ((Pnt) (aPoint1.getDataObject())).getIndex3() - ((Pnt) (originPoint.getDataObject())).getIndex3();
        int x4 = ((Pnt) aPoint1.getDataObject()).getIndex1() - ((Pnt) (aPoint2.getDataObject())).getIndex1();
        int y4 = ((Pnt) aPoint1.getDataObject()).getIndex2() - ((Pnt) (aPoint2.getDataObject())).getIndex2();
        int z4 = ((Pnt) aPoint1.getDataObject()).getIndex3() - ((Pnt) (aPoint2.getDataObject())).getIndex3();

        myPoint1.setX1(((Pnt) aPoint1.getDataObject()).getX1() - (x1 + x2));
        myPoint1.setY1(((Pnt) aPoint1.getDataObject()).getY1() - (y1 + y2));
        myPoint1.setZ1(((Pnt) aPoint1.getDataObject()).getZ1() - (z1 + z2));

        myPoint1.setIndex1(((Pnt) aPoint1.getDataObject()).getIndex1() - (x3 + x4));
        myPoint1.setIndex2(((Pnt) aPoint1.getDataObject()).getIndex2() - (y3 + y4));
        myPoint1.setIndex3(((Pnt) aPoint1.getDataObject()).getIndex3() - (z3 + z4));

        myPoint1.setEnvrnmntVar(((Pnt) originPoint.getDataObject()).getEnvrnmntX(),
                ((Pnt) originPoint.getDataObject()).getEnvrnmntY(),
                ((Pnt) originPoint.getDataObject()).getEnvrnmntZ());
        //System.out.println("Point : coordinated Point - originPoint : "+aPoint1+"-"+originPoint);
        //System.out.println("Point : coordinated Point - selected point : "+aPoint1+"-"+aPoint2);
        //System.out.println("Point : coordinated Point - originPoint + selected point :"+aPoint1+"-("+aPoint2+"+"+aPoint2+")");
        //System.out.println("Point : answer = "+myPoint1);   
        return myPoint1;
    }
    private Pnt crtHorOrVerPnt(Pnt aPoint1) {
        return crtHorOrVerPnt(this, aPoint1);
    }
    private Pnt crtHorOrVerPnt(Pnt originPoint, Pnt aPoint1) {
        Pnt myPoint1 = createDataPoint();
        float x1 = ((Pnt) (originPoint.getDataObject())).getX1() - ((Pnt) (aPoint1.getDataObject())).getX1();
        float y1 = ((Pnt) (originPoint.getDataObject())).getY1() - ((Pnt) (aPoint1.getDataObject())).getY1();
        float z1 = ((Pnt) (originPoint.getDataObject())).getZ1() - ((Pnt) (aPoint1.getDataObject())).getZ1();

        float x2 = ((Pnt) (originPoint.getDataObject())).getX1() + x1;
        float y2 = ((Pnt) (originPoint.getDataObject())).getY1() + y1;
        float z2 = ((Pnt) (originPoint.getDataObject())).getZ1() + z1;

        myPoint1.setX1(x2);
        myPoint1.setY1(y2);
        myPoint1.setZ1(z2);

        int x3 = ((Pnt) (originPoint.getDataObject())).getIndex1() - ((Pnt) (aPoint1.getDataObject())).getIndex1();
        int y3 = ((Pnt) (originPoint.getDataObject())).getIndex2() - ((Pnt) (aPoint1.getDataObject())).getIndex2();
        int z3 = ((Pnt) (originPoint.getDataObject())).getIndex3() - ((Pnt) (aPoint1.getDataObject())).getIndex3();

        int x4 = ((Pnt) (originPoint.getDataObject())).getIndex1() + x3;
        int y4 = ((Pnt) (originPoint.getDataObject())).getIndex2() + y3;
        int z4 = ((Pnt) (originPoint.getDataObject())).getIndex3() + z3;

        myPoint1.setIndex1(x4);
        myPoint1.setIndex2(y4);
        myPoint1.setIndex3(z4);

        myPoint1.setEnvrnmntVar(((Pnt) originPoint.getDataObject()).getEnvrnmntX(),
                ((Pnt) originPoint.getDataObject()).getEnvrnmntY(),
                ((Pnt) originPoint.getDataObject()).getEnvrnmntZ());

        //System.out.println("Point : creating horizontal or vertical point, name = "+myPoint1.getName()+", "+myPoint1);
        return myPoint1;
    }
    /**
     * The method provides the mesh generation functionality.  The node insertion
     * process is enumerated on the boundary.  The boundary expands until the constraints 
     * are satisfied.
     * @param myNodeActions The parameter is of type PntInsrtActn (point insert action),
     * and is called when nodes are inserted into the mesh structure.
     * 
     */
    public void crtMeshByBndry(PntInsrtActn myNodeActions) {
        crtMeshByBndry(myNodeActions, 0);
        //createCoordinateMesh4a(myNodeActions,1);
        //createCoordinateMesh4a(myNodeActions,2);
        //createCoordinateMesh4a(myNodeActions,3);
    }
    private Pnt crtMeshByBndry(PntInsrtActn myNodeActions, int myCrdntCmpltNmbr) {
        Vector tmpMsh = new Vector();
        int aTotalNumber = 0;
        int myTotalNumber = 0;
        int myDepth = 0;
        int myDepthCounter = 0;
        int nodeCounter = 0;
        Pnt aPoint = null;
        int meshSize = 0;
        do {
            tmpMsh.clear();
            tmpMsh.addAll(glblMsh);
            int aSize = tmpMsh.size();
            glblMsh.clear();
            //meshSize = glblMsh.size();
            for (int i = 0; i < aSize; i++) {
                aPoint = (Pnt) tmpMsh.elementAt(i);
                nodeCounter = crtCmpltCrdnt(aPoint, myDepthCounter, myDepth, myTotalNumber, myNodeActions, 0);
                nodeCounter = crtCmpltCrdnt(aPoint, myDepthCounter, myDepth, myTotalNumber, myNodeActions, 1);
                //nodeCounter = crtCmpltCrdnt(aPoint,myDepthCounter,myDepth,myTotalNumber,myNodeActions,2);
                //nodeCounter = crtCmpltCrdnt(aPoint,myDepthCounter,myDepth,myTotalNumber,myNodeActions,3);
                //nodeCounter = createCoordinateComplete(aPoint,myDepthCounter,myDepth,myTotalNumber,myNodeActions,0);
             /*
                 * nodeCounter =
                 * createCoordinateComplete(aPoint,myDepthCounter,myDepth,myTotalNumber,myNodeActions,1);
                 * nodeCounter =
                 * createCoordinateComplete(aPoint,myDepthCounter,myDepth,myTotalNumber,myNodeActions,0);
                 * nodeCounter =
                 * createCoordinateComplete(aPoint,myDepthCounter,myDepth,myTotalNumber,myNodeActions,3);
                 * nodeCounter =
                 * createCoordinateComplete(aPoint,myDepthCounter,myDepth,myTotalNumber,myNodeActions,2);
                 */
            }
        } while (glblMsh.size() > meshSize);
        shpPntFnshActn(this);
        return aPoint;
    }
    public void setShpPntFnshLstnr(ShapePntFnshLstnr myShpPntFnshLstnr)
    {
       shpPntFnshLstnr = myShpPntFnshLstnr;
    }
    private void shpPntFnshActn(ShapePnt myShapePnt)
    {
        if(shpPntFnshLstnr != null)
        {
           shpPntFnshLstnr.shapePntFinished(myShapePnt);
        }
    }
    /**
     * The crtMeshByOrigin (create mesh by origin) function generates a mesh for arbitrary 
     * multidimensional domains.  Each enumeration process starts from the origin.  
     * @param myNodeActions The parameter is of type PntInsrtActn (point insert action),
     * and is called when nodes are inserted into the mesh structure.
     * @return The function returns type Pnt (point object).
     */
    public Pnt crtMeshByOrigin(PntInsrtActn myNodeActions) {
        int nodeCount = getAdjcntVertices().size() + 1;
        int totalNodeCount = nodeCount;
        int treeDepth = 1;
        crtMeshByOrigin(this, nodeCount, treeDepth, totalNodeCount, myNodeActions);
        shpPntFnshActn(this);
        return this;
    }
    private Pnt crtMeshByOrigin(int myNodeCounter, int myTreeDepth, int myPrevTotalNumber, PntInsrtActn myNodeActions) {
        crtMeshByOrigin(this, myNodeCounter, myTreeDepth, myPrevTotalNumber, myNodeActions);
        return this;
    }
    private int crtMeshByOrigin(Pnt myPoint, int myNodeCounter, int myTreeDepth, int myPrevTotalNumber, PntInsrtActn myNodeActions) {
        int nodesCreated7 = crtCrdntNodes(myPoint, myNodeCounter, myTreeDepth, myPrevTotalNumber, myNodeActions, 0, 0);
        //int nodesCreated7 = createCoordinateMesh(myPoint,myNodeCounter,myTreeDepth,myPrevTotalNumber,myNodeActions,1,4);
        //int nodesCreated6 = createCoordinateMesh(myPoint,myNodeCounter,myTreeDepth,myPrevTotalNumber,myNodeActions,3,22);
        //int nodesCreated4 = createCoordinateMesh(myPoint,myNodeCounter,myTreeDepth,myPrevTotalNumber,myNodeActions,4,22);

        int nodesCreated5 = crtCrdntNodes(myPoint, myNodeCounter, myTreeDepth, myPrevTotalNumber, myNodeActions, 2, 4);
        //System.out.println("Point: nodesCreated5 = "+nodesCreated5 );
        int nodesCreated3 = crtCrdntNodes(myPoint, myNodeCounter, myTreeDepth, myPrevTotalNumber, myNodeActions, 5, 4);
        //System.out.println("Point: nodesCreated3 = "+nodesCreated3);
        int nodesCreated0 = crtCrdntNodes(myPoint, myNodeCounter, myTreeDepth, myPrevTotalNumber, myNodeActions, 0, 4);
        //System.out.println("Point: nodesCreated0 = "+nodesCreated0);

        if (nodesCreated5 != 0 || nodesCreated3 != 0 || nodesCreated0 != 0) {
            crtMeshByOrigin(myPoint, myNodeCounter, myTreeDepth, myPrevTotalNumber, myNodeActions);
        }
        return 1;
    }
    private int crtCrdntNodes(Pnt myPoint, int myNodeCounter, int myTreeDepth, int myPrevTotalNumber, PntInsrtActn myNodeActions, int myCrdntCmpltNmbr, int myMaxTreeDepth) {
        int maxTreeDepth = 50;
        int nmbrOfItrtns = 1;
        int levelCounter = 0;
        int aTotalNumber = 0;
        myPoint.setVisited(true);
        aTotalNumber = crtCoordinates(myPoint, levelCounter, myTreeDepth, myPrevTotalNumber, myNodeActions, myCrdntCmpltNmbr);
        if (aTotalNumber - myPrevTotalNumber > 0 || myTreeDepth < myMaxTreeDepth) {
            myTreeDepth++;
            nmbrOfItrtns += crtCrdntNodes(myPoint, myNodeCounter, myTreeDepth, aTotalNumber, myNodeActions, myCrdntCmpltNmbr, myMaxTreeDepth);
        }
        //System.out.println("Point: Number of iterations = "+nmbrOfItrtns);
        return aTotalNumber;
    }
    private int rtrveCrdntsByPnt(Pnt myPoint, int myNodeCounter, int myTreeDepth, int myPrevTotalNumber, PntInsrtActn myNodeActions) {
        int nodesCreated = rtrveCrdntNodes(myPoint, myNodeCounter, myTreeDepth, myPrevTotalNumber, myNodeActions, 2, 4);
        //System.out.println("Point: nodesCreated5 = "+nodesCreated5 );
        if (nodesCreated != 0) {
            rtrveCrdntsByPnt(myPoint, myNodeCounter, myTreeDepth, myPrevTotalNumber, myNodeActions);
        }
        return 1;
    }    
    private int rtrveCrdntNodes(Pnt myPoint, int myNodeCounter, int myTreeDepth, int myPrevTotalNumber, PntInsrtActn myNodeActions, int myCrdntCmpltNmbr, int myMaxTreeDepth) {
        int maxTreeDepth = 50;
        int nmbrOfItrtns = 1;
        int levelCounter = 0;
        int aTotalNumber = 0;
        myPoint.setVisited(true);
        aTotalNumber = rtrveCoordinates(myPoint, levelCounter, myTreeDepth, myPrevTotalNumber, myNodeActions, myCrdntCmpltNmbr);
        if (aTotalNumber - myPrevTotalNumber > 0 || myTreeDepth < myMaxTreeDepth) {
            myTreeDepth++;
            nmbrOfItrtns += rtrveCrdntNodes(myPoint, myNodeCounter, myTreeDepth, aTotalNumber, myNodeActions, myCrdntCmpltNmbr, myMaxTreeDepth);
        }
        //System.out.println("Point: Number of iterations = "+nmbrOfItrtns);
        return aTotalNumber;
    }
    private int crtCoordinates(Pnt myPoint, int myDepthCounter, int myDepth, int myTotalNumber, PntInsrtActn myNodeActions, int myCrdntCmpltNmbr) {
        //Note : coordinate complete and boundary conditions can be used to stop this function
        int nodeCounter = 0;
        int totalCount = 0;
        Enumeration anEnumeration = null;
        Pnt aPoint6 = null;
        myPoint.setVisited(true);
        //System.out.println("Point: init pt = "+myPoint+" size = "+myPoint.numberOfVertices()+", depth count = "+myDepthCounter+", tree depth = "+myDepth);

        //myNodeActions.nodeCreateAction(myPoint,myPoint,myPoint,myPoint,0,0,0);
        if (myDepthCounter == myDepth) {
            nodeCounter = crtCmpltCrdnt(myPoint, myDepthCounter, myDepth, myTotalNumber, myNodeActions, myCrdntCmpltNmbr);
            myTotalNumber += nodeCounter;
        } else if (myDepthCounter < myDepth) {
            anEnumeration = myPoint.rtrvEnumeration();
            myDepthCounter++;
            while (anEnumeration.hasMoreElements()) {
                aPoint6 = (Pnt) anEnumeration.nextElement();
                //System.out.println("Point :  "+aPoint6+" size = "+aPoint6.numberOfVertices()+", visited state = "+aPoint6.getIsVisited()+", depth count = "+myDepthCounter+", tree depth = "+myDepth);
                if (aPoint6.getVisited() == false && aPoint6.getDepth() >= myPoint.getDepth() && aPoint6.getItrtnNmbr() <= myPoint.getItrtnNmbr()) //if(aPoint6.getIsVisited() == false)
                {
                    totalCount = crtCoordinates(aPoint6, myDepthCounter, myDepth, myTotalNumber, myNodeActions, myCrdntCmpltNmbr);
                    myTotalNumber = totalCount;
                    //System.out.println("Point.createCoordinates() : nodeCounter = "+nodeCounter+" totalCounter = "+myTotalNumber);
                }
            }
        }
        myPoint.setItrtnNmbr(myPoint.getItrtnNmbr() + 1);
        //System.out.println("Point : point = "+myPoint+", iteration number = "+myPoint.getItrtnNmbr());
        myPoint.setVisited(false);
        return myTotalNumber;
    }
    //TODO: complete retrieving Pnt via coordinate
    private int rtrveCoordinates(Pnt myPoint, int myDepthCounter, int myDepth, int myTotalNumber, PntInsrtActn myNodeActions, int myCrdntCmpltNmbr) {
        //Note : coordinate complete and boundary conditions can be used to stop this function
        int nodeCounter = 0;
        int totalCount = 0;
        Enumeration anEnumeration = null;
        Pnt aPoint6 = null;
        myPoint.setVisited(true);
        //System.out.println("Point: init pt = "+myPoint+" size = "+myPoint.numberOfVertices()+", depth count = "+myDepthCounter+", tree depth = "+myDepth);

        //myNodeActions.nodeCreateAction(myPoint,myPoint,myPoint,myPoint,0,0,0);
        if (myDepthCounter == myDepth) {
            nodeCounter = crtCmpltCrdnt(myPoint, myDepthCounter, myDepth, myTotalNumber, myNodeActions, myCrdntCmpltNmbr);
            myTotalNumber += nodeCounter;
        } else if (myDepthCounter < myDepth) {
            anEnumeration = myPoint.rtrvEnumeration();
            myDepthCounter++;
            while (anEnumeration.hasMoreElements()) {
                aPoint6 = (Pnt) anEnumeration.nextElement();
                //System.out.println("Point :  "+aPoint6+" size = "+aPoint6.numberOfVertices()+", visited state = "+aPoint6.getIsVisited()+", depth count = "+myDepthCounter+", tree depth = "+myDepth);
                if (aPoint6.getVisited() == false && aPoint6.getDepth() >= myPoint.getDepth() && aPoint6.getItrtnNmbr() <= myPoint.getItrtnNmbr()) //if(aPoint6.getIsVisited() == false)
                {
                    totalCount = crtCoordinates(aPoint6, myDepthCounter, myDepth, myTotalNumber, myNodeActions, myCrdntCmpltNmbr);
                    myTotalNumber = totalCount;
                    //System.out.println("Point.createCoordinates() : nodeCounter = "+nodeCounter+" totalCounter = "+myTotalNumber);
                }
            }
        }
        myPoint.setItrtnNmbr(myPoint.getItrtnNmbr() + 1);
        //System.out.println("Point : point = "+myPoint+", iteration number = "+myPoint.getItrtnNmbr());
        myPoint.setVisited(false);
        return myTotalNumber;
    }
    private int crtCmpltCrdnt(Pnt myIntlPoint, int myDepthCounter, int aDepth, int myTotalNumber, PntInsrtActn myNodeActions, int myCrdntCmpltNmbr) {
        int nodeCounter = 0;
        boolean bndryChk = false;
        Pnt anElementPoint = null;
        Pnt aCrtdPoint = null;
        Pnt aSlctdPoint = null;
        Pnt myPoint7 = null;
        Pnt myPoint1_7 = null;
        Pnt myPoint6_7 = null;

        Pnt myCrdntCmpltPnt = (Pnt) findCrdntCmplt6(myIntlPoint, true, myCrdntCmpltNmbr);

        Enumeration anEnumeration = myCrdntCmpltPnt.rtrvEnumeration();

        while (anEnumeration.hasMoreElements()) {
            aSlctdPoint = (Pnt) anEnumeration.nextElement();

            myPoint7 = (Pnt) crtHozVerOrDiagPnt(myIntlPoint, myCrdntCmpltPnt, aSlctdPoint);

            bndryChk = checkBoundary(myPoint7);

            if ((bndryChk == true) && ((myPoint6_7 = (Pnt) aSlctdPoint.getVertexByIndices(myPoint7)) == null) && ((myPoint1_7 = (Pnt) myIntlPoint.getVertexByIndices(myPoint7)) == null)) {
                //aCrtdPoint = new Point();
                aCrtdPoint = createPoint();
                //System.out.println("Point : creating new coordinate = "+myPoint7);
                aCrtdPoint.setDataObject(myPoint7);
                aCrtdPoint.setPoint(myPoint7.getX1(), myPoint7.getY1(), myPoint7.getZ1());
                aCrtdPoint.setIndex1(myPoint7.getIndex1());
                aCrtdPoint.setIndex2(myPoint7.getIndex2());
                aCrtdPoint.setIndex3(myPoint7.getIndex3());
                aCrtdPoint.setDepth(myIntlPoint.getDepth() + 1);
                aCrtdPoint.setEnvrnmntVar(myPoint7.getEnvrnmntX(), myPoint7.getEnvrnmntY(), myPoint7.getEnvrnmntZ());
                //myIntlPoint.connectVertices2(aCrtdPoint);
                myIntlPoint.connectVertices2(aCrtdPoint);
                aSlctdPoint.connectVertices2(aCrtdPoint);

                Pnt dataPoint1 = (Pnt) myIntlPoint.getDataObject();
                Pnt dataPoint2 = (Pnt) aSlctdPoint.getDataObject();

                dataPoint1.connectVertices2(myPoint7);
                dataPoint2.connectVertices2(myPoint7);

                nodeCounter++;

                aCrtdPoint.setCounter(++glblNmbr);
                myCrdntCmpltPnt.setNumber(0);
                myIntlPoint.setNumber(1);
                aCrtdPoint.setNumber(2);
                aSlctdPoint.setNumber(3);
                insrtDrctn(myIntlPoint);
                insrtDrctn(aCrtdPoint);
                insrtDrctn(aSlctdPoint);
                nodeCrtActn(myNodeActions, myIntlPoint, myCrdntCmpltPnt, aSlctdPoint, aCrtdPoint, myDepthCounter, aDepth, myTotalNumber + nodeCounter);

            } else if ((bndryChk == true) && (((myPoint6_7 = (Pnt) aSlctdPoint.getVertexByIndices(myPoint7)) != null)) && ((myPoint1_7 = (Pnt) myIntlPoint.getVertexByIndices(myPoint7)) == null)) {

                aCrtdPoint = (Pnt) myPoint6_7;//Doube check this statement with if conditions!!!!!

                myIntlPoint.connectVertices2(aCrtdPoint);

                Pnt aDataPoint1 = (Pnt) myIntlPoint.getDataObject();
                Pnt aDataPoint2 = (Pnt) aCrtdPoint.getDataObject();
                aDataPoint1.connectVertices2(aDataPoint2);
                insrtDrctn(myIntlPoint);
                insrtDrctn(aCrtdPoint);
                insrtDrctn(aSlctdPoint);
                nodeUpdtActn1(myNodeActions, myIntlPoint, myCrdntCmpltPnt, aSlctdPoint, aCrtdPoint, myDepthCounter, aDepth, myTotalNumber + nodeCounter);

            } else if ((bndryChk == true) && (((myPoint6_7 = (Pnt) aSlctdPoint.getVertexByIndices(myPoint7)) == null)) && ((myPoint1_7 = (Pnt) myIntlPoint.getVertexByIndices(myPoint7)) != null)) {
                aCrtdPoint = (Pnt) myPoint1_7;//Doube check this statement with if conditions!!!!!

                aSlctdPoint.connectVertices2(aCrtdPoint);

                Pnt aDataPoint1 = (Pnt) aSlctdPoint.getDataObject();
                Pnt aDataPoint2 = (Pnt) aCrtdPoint.getDataObject();
                aDataPoint1.connectVertices2(aDataPoint2);
                insrtDrctn(myIntlPoint);
                insrtDrctn(aCrtdPoint);
                insrtDrctn(aSlctdPoint);

                nodeUpdtActn2(myNodeActions, myIntlPoint, myCrdntCmpltPnt, aSlctdPoint, aCrtdPoint, myDepthCounter, aDepth, myTotalNumber + nodeCounter);
                //nodeUpdateAction(aPoint,myCrdntCmpltPnt,myPoint6,aCrtdPoint,myDepthCounter,aDepth,myTotalNumber+nodeCounter);
            }
        }
        //myNodeActions.nodeCmpltAction(aPoint,myDepthCounter,aDepth,myTotalNumber+nodeCounter);
        myIntlPoint.setCrdntCompltn(true);
        insrtDrctn(myIntlPoint);
        nodeCmpltActn(myNodeActions, myIntlPoint, myDepthCounter, aDepth, myTotalNumber + nodeCounter);
        return nodeCounter;
    }

    /**
     * This method is called when a node is connected by all potentially adjacent 
     * nodes.  The PntInsrtActn (point insert action) provides an interface for 
     * when the node is complete.
     * @param myNodeActions The interface myNodeActions is of type PntInsrtActn.
     * @param myPoint The myPoint parameter is of type Pnt; and represents the completed node.
     * @param myDepthCounter The parameter represents the iteration count of the tree 
     * representation.
     * structure.
     * @param myDepth The parameter represents the depth of the traversed tree.
     * @param myTotal The parameter corresponds to the total number of nodes represented 
     * in the mesh structure.
     */
    protected void nodeCmpltActn(PntInsrtActn myNodeActions, Pnt myPoint, int myDepthCounter, int myDepth, int myTotal) {
        if (myNodeActions != null) {
            myNodeActions.nodeCmpltAction(myPoint, myDepthCounter, myDepth, myTotal);
        }
    }
    /**
     * The nodeUpdtActn1 occurs when an attempt is made to insert a node where a 
     * node currently exist.
     * @param myNodeActions The parameter is an interface of type PntInsrtActn.
     * @param aPoint The parameter represents the initial node point.
     * @param myPoint4 The parameter represents the coordinate completed node.
     * @param myPoint6 The parameter represents the selected node.
     * @param myPoint5 The parameter represents the the newly created node.
     * @param myDepthCounter The parameter represents the tree iteration count.
     * @param aDepth The parameter represents the depth of the tree.
     * @param myTotal The parameter represents the index of the newly inserted node.
     */
    protected void nodeUpdtActn1(PntInsrtActn myNodeActions, Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint5, int myDepthCounter, int aDepth, int myTotal) {
        if (myNodeActions != null) {
            myNodeActions.nodeUpdateAction(aPoint, myPoint4, myPoint6, myPoint5, myDepthCounter, aDepth, myTotal);
        }
        //TODO: This method should be in QuadPnts
        crtQuadPnts(myNodeActions, aPoint, myPoint4, myPoint6, myPoint5, myDepthCounter, aDepth, myTotal);
        crtHxhdrlPnts(myNodeActions, aPoint, myPoint4, myPoint6, myPoint5, myDepthCounter, aDepth, myTotal);
        //TODO ... this method should be located in linepnts
        crtLinePnts(aPoint, myPoint5);
        updtCnt++;
    }

    /**
     * The nodeUpdtActn1 occurs when an attempt is made to insert a node where a 
     * node currently exist.
     * @param myNodeActions The parameter is an interface of type PntInsrtActn.
     * @param aPoint The parameter represents the initial node point.
     * @param myPoint4 The parameter represents the coordinate completed node.
     * @param myPoint6 The parameter represents the selected node.
     * @param myPoint5 The parameter represents the the newly created node.
     * @param myDepthCounter The parameter represents the tree iteration count.
     * @param aDepth The parameter represents the depth of the tree.
     * @param myTotal The parameter represents the index of the newly inserted node.
     */
    protected void nodeUpdtActn2(PntInsrtActn myNodeActions, Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint5, int myDepthCounter, int aDepth, int myTotal) {
        if (myNodeActions != null) {
            myNodeActions.nodeUpdateAction(aPoint, myPoint4, myPoint6, myPoint5, myDepthCounter, aDepth, myTotal);
        }
        crtQuadPnts(myNodeActions, aPoint, myPoint4, myPoint6, myPoint5, myDepthCounter, aDepth, myTotal);
        crtHxhdrlPnts(myNodeActions, aPoint, myPoint4, myPoint6, myPoint5, myDepthCounter, aDepth, myTotal);
        crtLinePnts(myPoint6, myPoint5);
        updtCnt++;
    }
    private void insrtDrctn(Pnt myPoint0) {

        Pnt aPoint1 = (Pnt) myPoint0.getVertexByIndex(0);
        Pnt aPoint2 = (Pnt) myPoint0.getVertexByIndex(1);
        Pnt aPoint3 = (Pnt) myPoint0.getVertexByIndex(2);
        Pnt aPoint4 = (Pnt) myPoint0.getVertexByIndex(3);
        Pnt aPoint5 = (Pnt) myPoint0.getVertexByIndex(4);
        Pnt aPoint6 = (Pnt) myPoint0.getVertexByIndex(5);
        insrtDrctn(myPoint0, aPoint1);
        insrtDrctn(myPoint0, aPoint2);
        insrtDrctn(myPoint0, aPoint3);
        insrtDrctn(myPoint0, aPoint4);
        insrtDrctn(myPoint0, aPoint5);
        insrtDrctn(myPoint0, aPoint6);
    }
    private void insrtDrctn(Pnt myPoint0, Pnt myPoint1) {
        //System.out.println("Point: "+myPoint0+","+myPoint1);
        //System.out.println("Point: "+myPoint0.numberOfVertices());
        if (myPoint1 == null) {
            return;
        }
        if (myPoint0.getIndex1() < myPoint1.getIndex1()) {
            myPoint0.setPosXDrctn(myPoint1);
        } else if (myPoint0.getIndex1() > myPoint1.getIndex1()) {
            myPoint0.setNegXDrctn(myPoint1);
        } else if (myPoint0.getIndex2() < myPoint1.getIndex2()) {
            myPoint0.setPosYDrctn(myPoint1);
        } else if (myPoint0.getIndex2() > myPoint1.getIndex2()) {
            myPoint0.setNegYDrctn(myPoint1);
        } else if (myPoint0.getIndex3() < myPoint1.getIndex3()) {
            myPoint0.setPosZDrctn(myPoint1);
        } else if (myPoint0.getIndex3() > myPoint1.getIndex3()) {
            myPoint0.setNegZDrctn(myPoint1);
        }
    }
    /**
     * The crtInitCoordinates (create initial coordinate) method is responsible 
     * for generating the first set of coordinate points.  This is the starting 
     * of the mesh generation algorithm.
     * @param myPoint The parameter represents the starting point of the mesh
     * generation algorithm.
     * @return The method returns the initialized point.
     */
    public Pnt crtInitCoordinates(Pnt myPoint) {
        //TODO: this point should become the origin
        float x = myPoint.getX1();
        float y = myPoint.getY1();
        float z = myPoint.getZ1();
        return crtInitCoordinates(x, y, z);
    }
    /**
     * The crtInitCoordinates (create initial coordinate) method is responsible 
     * for generating the first set of coordinate points.  This is the starting 
     * of the mesh generation algorithm.
     * @return The method returns a Pnt with a default location of (0,0,0).
     */
    public Pnt crtInitCoordinates() {
        return crtInitCoordinates(0, 0, 0);
    }

    /**
     * The crtInitCoordinates (create initial coordinate) method is responsible 
     * for generating the first set of coordinate points.  This is the starting 
     * of the mesh generation process.
     * @param x1 The parameter represents the x (1st orthogonal) coordinate.
     * @param y1 The parameter represents the y (2nd orthogonal) coordinate.
     * @param z1 The parameter represents the z (3rd orthogonal) coordinate.
     * @return The method returns a Pnt with a location of (x1,y1,z1).
     */
    public Pnt crtInitCoordinates(float x1, float y1, float z1) {
        return crtInitCoordinates(x1, y1, z1, null);
    }
    /**
     * The crtInitCoordinates (create initial coordinate) method is responsible 
     * for generating the first set of coordinate points.  This is the starting 
     * of the mesh generation process.
     * @param myBndryNodeAction The parameter is an interface of type PntInsrtActn.
     * @return The method returns a Pnt with a location of (0,0,0).
     */
    public Pnt crtInitCoordinates(PntInsrtActn myBndryNodeAction) {
        return crtInitCoordinates(0, 0, 0, myBndryNodeAction);
    }
    /**
     * The crtInitCoordinates (create initial coordinate) method is responsible 
     * for generating the first set of coordinate points.  This is the starting 
     * of the mesh generation process.
     * @param x1 The parameter represents the x (1st orthogonal) coordinate.
     * @param y1 The parameter represents the y (2nd orthogonal) coordinate.
     * @param z1 The parameter represents the z (3rd orthogonal) coordinate.
     * @param myBndryNodeAction The parameter is an interface of type PntInsrtActn.
     * @return The method returns a Pnt with a location of (x1,y1,z1).
     */
    public Pnt crtInitCoordinates(float x1, float y1, float z1, PntInsrtActn myBndryNodeAction) {
        setOrigin(x1, y1, z1, myBndryNodeAction);
        increaseX(myBndryNodeAction);
        decreaseX(myBndryNodeAction);
        increaseY(myBndryNodeAction);
        decreaseY(myBndryNodeAction);
        increaseZ(myBndryNodeAction);
        decreaseZ(myBndryNodeAction);
        setCrdntCompltn(true);
        if (myBndryNodeAction != null) {
            myBndryNodeAction.nodeCmpltAction(this, 0, 0, 1);
        }
        setVisited(false);
        //TODO: combine insrtDrctn with increase and decrease functions 
        insrtDrctn(this);
        PrimaryRoot aPrimaryOrigin = PrimaryRoot.rtrvePrimaryRoot();
        aPrimaryOrigin.addVertex(this);
        return this;
    }
    /**
     * The method gets the quadrilateral elements associated with the mesh structure.
     * @return The method returns type Vector containing quadrilateral elements.
     */
    public Vector getQuadElmnts() {
        return quadElmnts;
    }

    /**
     * The method sets the quadrilateral elements associated with the mesh structure.
     * @param myQuadVctr The parameter is of type Vector; which contains a list of 
     * quadrilateral elements.
     */
    public void setQuadElmnts(Vector myQuadVctr) {
        quadElmnts = myQuadVctr;
    }
    /**
     * The method gets the triangular elements associated with the mesh structure.
     * @return The method returns type Vector; which contains triangular elements.
     */
    public Vector getTrnglElmnts() {
        return trnglElmnts;
    }
    /**
     * The method gets the hexahedral elements associated with the mesh structure.
     * @return The method returns type Vector containing hexahedral elements.
     */
    public Vector getHxhdrlElmnts() {
        return hxhdrlElmnts;
    }
    /**
     * The method gets the surface nodes associated with the mesh structure.
     * @return The method returns type Vector containing surface nodes.
     */
    public Vector getSrfcNodes() {
        Vector aVector = new Vector();
        Pnt aPoint = null;
        int ndSize = getNodes().size();
        for (int i = 0; i < ndSize; i++) {
            aPoint = (Pnt) getNodes().elementAt(i);
            if (aPoint.nmbrOfVertices() < 6) {
                aVector.add(aPoint);
            }
        }
        return aVector;
    }

    /**
     * The method gets the boundary lines associated with the 2D structure.
     * @return The method returns type Vector containing boundary lines.
     */
    public Vector getLineBndry2D() {
        Vector aVector = new Vector();
        LinePnts aLinePnts = null;
        Pnt aPoint0 = null;
        Pnt aPoint1 = null;
        if (getEdges() == null) {
            System.err.println("Shape : getLineElmnts() are null");
        }
        int ndSize = getEdges().size();
        for (int i = 0; i < ndSize; i++) {
            aLinePnts = (LinePnts) getEdges().elementAt(i);
            aPoint0 = aLinePnts.getPoint0();
            aPoint1 = aLinePnts.getPoint1();
            if (aPoint0.nmbrOfVertices() <= 3 && aPoint1.nmbrOfVertices() <= 3 && aPoint0.getX1() >= getXMax() && aPoint1.getX1() >= getXMax()) {
                aVector.add(aLinePnts);
            }
        }
        return aVector;
    }
    //TODO: put in seperate class
    /**
     * The method gets the boundary lines associated with the 2D structure.
     * @return The method returns type Vector containing boundary lines.
     */
    public Vector getNodeBndry2D() {
        Vector aVector = new Vector();
        Pnt aPoint = null;
        int ndSize = getNodes().size();
        for (int i = 0; i < ndSize; i++) {
            aPoint = (Pnt) getNodes().elementAt(i);
            if (aPoint.nmbrOfVertices() <= 3 && aPoint.getX1() >= getXMax()) {
                aVector.add(aPoint);
            }
        }
        return aVector;
    }

    /**
     * The method gets the interior nodes associated with the 2D structure.
     * @return The method returns type Vector containing interior nodes.
     */
   
    public Vector getIntrrNd2D() {
        Vector aVector = new Vector();
        Pnt aPoint = null;
        int ndSize = getNodes().size();
        for (int i = 0; i < ndSize; i++) {
            aPoint = (Pnt) getNodes().elementAt(i);
            if (aPoint.nmbrOfVertices() == 4) {
                aVector.add(aPoint);
            }
        }
        return aVector;
    }

    /**
     * The method retrieves vertexes by the connections.  Each connected vertex 
     * is retrieved via its associated index number.  In general this number is 
     * between 0 to 5.
     * @param myNumbr The paramter represents the integer (int) index number.
     * @return The return type is Vertex; which contains the retrieved Vertex.
     */
    public Vertex rtrvVrtxByCnnctn(int myNumbr) {
        Vertex aVertex = new Vertex();
        Pnt aPoint = null;
        int ndSize = getNodes().size();
        for (int i = 0; i < ndSize; i++) {
            aPoint = (Pnt) getNodes().elementAt(i);
            if (aPoint.nmbrOfVertices() == myNumbr) {
                aVertex.addVertex(aPoint);
            }
        }
        return aVertex;
    }
    private void crtQuadPnts(PntInsrtActn myNodeActions, Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint5, int myDepthCounter, int aDepth, int myTotal) {
        if (PntTool.equalDstnce(aPoint, myPoint4) || PntTool.equalDstnce(myPoint4, myPoint6) || PntTool.equalDstnce(aPoint, myPoint6)) {
            return;
        }
        ttlCnt++;
        QuadPnts aRctnglPnts = new QuadPnts();
        aRctnglPnts.addPntsCClck(aPoint, myPoint4, myPoint6, myPoint5);
        quadElmnts.add(aRctnglPnts);
    }

    private void crtHxhdrlPnts(PntInsrtActn myNodeActions, Pnt myPoint1, Pnt myPoint4, Pnt myPoint6, Pnt myPoint5, int myDepthCounter, int aDepth, int myTotal) {

        if (PntTool.equalDstnce(myPoint1, myPoint5) || PntTool.equalDstnce(myPoint4, myPoint6) || PntTool.equalDstnce(myPoint1, myPoint6)) {
            return;
        }
        if (myPoint5.getIndex1() == 0 || myPoint5.getIndex2() == 0 || myPoint5.getIndex3() == 0) {
            return;
        }
        HxhdrlPnts aHxhdrlPnts = new HxhdrlPnts();
        aHxhdrlPnts.crtHxhdrlPnts(myPoint1, myPoint4, myPoint6, myPoint5);
        if (aHxhdrlPnts.getHasNulls() == false) {
            hxhdrlElmnts.add(aHxhdrlPnts);
        }
        ttlCnt++;
    }
    /**
     * 
     * @param myNodeActions
     * @param aPoint
     * @param myPoint4
     * @param myPoint6
     * @param myPoint5
     * @param myDepthCounter
     * @param aDepth
     * @param myTotal
     */
    protected void nodeCrtActn(PntInsrtActn myNodeActions, Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint5, int myDepthCounter, int aDepth, int myTotal) {
        updtBndry(myPoint5);
        if (myNodeActions != null) {
            myNodeActions.nodeCreateAction(aPoint, myPoint4, myPoint6, myPoint5, myDepthCounter, aDepth, myTotal);
        }
        crtNodePnts(myPoint5);
        crtLinePnts(aPoint, myPoint5);
        if (aPoint != myPoint6)//Must check if four nodes rectangle
        {
            crtLinePnts(myPoint6, myPoint5);
        }
        crtQuadPnts(myNodeActions, aPoint, myPoint4, myPoint6, myPoint5, myDepthCounter, aDepth, myTotal);
        //crtTrnglPnts(myNodeActions,aPoint,myPoint4,myPoint6,myPoint5,myDepthCounter,aDepth,myTotal);
        crtHxhdrlPnts(myNodeActions, aPoint, myPoint4, myPoint6, myPoint5, myDepthCounter, aDepth, myTotal);
        crtCnt++;
        glblMsh.addElement(myPoint5);
    }
    /**
     * The function dynamically retrieves the elements of the mesh structure.  
     * The type of elements are determined by the local coordinate size of the 
     * mesh structure. 
     * @return The return type is a Vector; the Vector contains the associated 
     * elements.
     */
    public Vector rtrvElmnts() {
        Vector aVector = null;
        if (getLclSize() == 4) {
            aVector = getQuadElmnts();
        } else if (getLclSize() == 8) {
            aVector = getHxhdrlElmnts();
        } else {
            System.err.println("Shape: no elements returned!");
        }
        return aVector;
    }
    
    /*     
    public Pnt rtrvePntByIndex(int myXIndex,int myYIndex,int myZIndex){
        
    }*/

    public Pnt getOrigin() {
        return origin;
    }

    public void setOrigin(Pnt myOrigin) {
        this.origin = myOrigin;
    }
}