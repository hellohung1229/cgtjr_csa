/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.clustering;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.elctrclengnrng.imgprcssng.annotate.FrameAnnotator;
import cgtjr.academics.elctrclengnrng.imgprcssng.color.ColorDiffBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryPntArrayList;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryPntArrayList;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.DigitPxls;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ArrowGridDrawActn;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.elmnt.QuadPnts;
import java.awt.Point;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

public class ClusterActn extends ArrowGridDrawActn {

    private DmnsnVar aDmnsnVar;
    private ColorDiffBndry nodeColorDiffBndry;
    private double maxDistance;
    private Vector groupVector;
    private HashMap aHashMap;
    private String groupName;
    private int groupNumber;
    private int colorValues[] = {0x00ff0000, 0x0000ffff,0x000000ff,0x00ff00ff,0x00ffff00};   
    private TreeMap aTreeMap;
    private RectBndryPntArrayList aRectBndryPntArrayList = null;
    private RectBndryPntArrayList tmpRectBndryPntArrayList = null;    
    final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");    
    private FrameAnnotator theFrameAnnotator;

    public ClusterActn() {

        groupVector = new Vector();
        aHashMap = new HashMap();
        aDmnsnVar = new DmnsnVar(4, 4, 0, 0, 10, 0, 320, 270, 0, 5, 5, 1, 160, 140, 0);
        nodeColorDiffBndry = new ColorDiffBndry(aDmnsnVar);
    }

    @Override
    public void setImgPxlData(int[] myImgPixels, int myImgWidth, int myImgHeight) {
        super.setImgPxlData(myImgPixels, myImgWidth, myImgHeight);
        nodeColorDiffBndry.setImgPxlData(myImgPixels, myImgWidth, myImgHeight);
    }

    @Override
    public void nodeCmpltAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        super.nodeCmpltAction(aPoint, myDepthCounter, myDepth, myTotalNumber);
        //insertGroupData(aPoint);
    }

    @Override
    public void nodeCreateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        super.nodeCreateAction(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
    }

    @Override
    public void nodeUpdateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        super.nodeUpdateAction(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
    }

    @Override
    public void nodeInitAction(Pnt aPoint, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        super.nodeInitAction(aPoint, myPoint7, myDepthCounter, myDepth, myTotalNumber);
    }

    @Override
    public void nodeOriginAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        super.nodeOriginAction(aPoint, myDepthCounter, myDepth, myTotalNumber);
    }

    //Insert clustering method
    public boolean isInBndry(Pnt myPnt) {
        boolean isIn = nodeColorDiffBndry.isInBndry(maxDistance, maxDistance, maxDistance);
        return isIn;
    }
    public void updateMesh(ShapePnt myShapePnt) {
        Vector quadVector = myShapePnt.getQuadElmnts();
        //drawArrows(quadVector);
        //System.out.println("ClusterActn : updateMesh");
        //aTreeMap = new TreeMap();
        //groupNumber = 0;
        aRectBndryPntArrayList = null;
        tmpRectBndryPntArrayList = null;
        
        drawArrowGrid(quadVector);
        //drawMesh(quadVector);
        //printData();
    } 
    public void drawArrowGrid(Vector myQuadVector)
    {
        QuadPnts smQuadPnts;

        int size = myQuadVector.size();
        for (int i = 0; i < size; i++) {
            smQuadPnts = (QuadPnts) myQuadVector.elementAt(i);
            drawMesh(smQuadPnts);
            drawArrows(smQuadPnts);            
        }        
    }    
    
    
    /*
    public void drawMesh(QuadPnts smQuadPnts) {
        
        Pnt aPnt1 = null;
        Pnt aPnt2 = null;
        Pnt aPnt3 = null;
        Pnt aPnt4 = null;
        
        aPnt1 = smQuadPnts.getPoint0();
        aPnt2 = smQuadPnts.getPoint1();
        aPnt3 = smQuadPnts.getPoint2();
        aPnt4 = smQuadPnts.getPoint3();
        
        insertGroupData(aPnt1);
        insertGroupData(aPnt2);
        insertGroupData(aPnt3);
        insertGroupData(aPnt4);        
    }*/

/*
    public void insertGroupData(Pnt myPnt1) {
        boolean isInBndry = isInBndry(myPnt1);
  
        //Set aSet = aTreeMap.keySet();

        boolean isInDstnce = false;
        
        String aGroupName = myPnt1.getName();
        System.out.println("ClusterActn : aGroupName = "+aGroupName);
        Pnt posXPnt = myPnt1.getPosXDrctn();
        Pnt negXPnt = myPnt1.getNegXDrctn();
        Pnt posYPnt = myPnt1.getPosYDrctn();
        Pnt negYPnt = myPnt1.getNegYDrctn();

        //TODO: update code with state group variables
        if (myPnt1.getName() == null && posXPnt != null && posXPnt.getName() != null) {
            aGroupName = posXPnt.getName();
            myPnt1.setName(aGroupName);
            myPnt1.setColor(posXPnt.getColor());
            //posXPnt.setColor(0x0000ffff);            
        } else if (myPnt1.getName() == null && negXPnt != null && negXPnt.getName() != null) {
            aGroupName = negXPnt.getName();
            myPnt1.setName(aGroupName);
            myPnt1.setColor(negXPnt.getColor());            
            //posXPnt.setColor(0x0000ffff);            
        } else if (myPnt1.getName() == null && posYPnt != null && posYPnt.getName() != null) {
            aGroupName = posYPnt.getName();
            myPnt1.setName(aGroupName);
            myPnt1.setColor(posYPnt.getColor());            
            //posXPnt.setColor(0x0000ffff);
        } else if (myPnt1.getName() == null && negYPnt != null && negYPnt.getName() != null) {
            aGroupName = negYPnt.getName();
            myPnt1.setName(aGroupName);                     
            //posXPnt.setColor(0x0000ffff);
            myPnt1.setColor(negYPnt.getColor());            
        } else if (aGroupName == null) {
            //posXPnt.setColor(0x00ff0000);
            int colorIndex = groupNumber%colorValues.length;
            aGroupName = "" + groupNumber;
            myPnt1.setName(aGroupName);   
            myPnt1.setColor(colorValues[colorIndex]);       
            //myPnt1.setColor(0x00ff0000);  
            
            aRectBndryPntArrayList = new RectBndryPntArrayList(myPnt1);
            //Integer anIteger = new Integer(groupNumber);
            //aRectBndryPntArrayList.setBoxID(groupNumber);

            //aTreeMap.put(aGroupName, aRectBndryPntArrayList);
            
            groupNumber++;            
        }else if(aGroupName != null){
            //aRectBndryPntArrayList = (RectBndryPntArrayList) aTreeMap.get(aGroupName);
            //aRectBndryPntArrayList.add(myPnt1);            
        }
    }
*/
    public TreeMap getTreeMap() {
        return aTreeMap;
    }

    public void setTreeMap(TreeMap myTreeMap) {
        aTreeMap = myTreeMap;
    }
    public void printData() {
        int anImageWidth = getImgDtWdth();
        int anImageHeight = getImgDtHght();        
        
        Point aPoint = null;
        RectBndryPntArrayList aRectBndryPntArrayList = null;
        Set aSet = aTreeMap.keySet();
        Iterator anIterator1 = aSet.iterator();
        Iterator anIterator2 = null;
        Integer aGroupKey = null;
        //int pixelData[] = getImageDrawData().getImagePixels();
        //int myPixelData[] = imagePixelData.getImagePixels();
        int myPixelData[] = getImgPixels();
        while (anIterator1.hasNext()) {
            aGroupKey = (Integer) anIterator1.next();
            //System.out.println("----------GROUP : " + aGroupKey.intValue() + "------------");
            aRectBndryPntArrayList = (RectBndryPntArrayList) aTreeMap.get(aGroupKey);
            //aRectBndryPntArrayList.drawSquare(myPixelData, getImgDtWdth(), getImgDtHght(), 0x0000ff00);
            //anIterator2 = aRectBndryPntArrayList.iterator();
            int x = aRectBndryPntArrayList.getXAvg();
            int y = aRectBndryPntArrayList.getYAvg();
            int anImageIndex = ImageTool.rtrvIndex(x, y, anImageWidth);
            /*
             while (anIterator2.hasNext()) {
             aPoint = (Point) anIterator2.next();
             System.out.println("feature location : " + aPoint.getX() + "," + aPoint.getY());
             }*/
            int nmbrIndex = 40;
            Calendar now = Calendar.getInstance();
            String aDateTime = dateFormat.format(now.getTime());
            //int pxlDataX1[] = DigitPntPixels.rtrvNmbr(aGroupKey.intValue());            
            //DigitPxls.draw9x9(myPixelData,pxlDataX1,anImageWidth,anImageHeight,anImageIndex,nmbrIndex);
            DigitPxls.drawDigit(myPixelData,anImageWidth,anImageHeight,x,y,aRectBndryPntArrayList.getBoxID());
            theFrameAnnotator.process(aDateTime, 0, getImgDtWdth(), getImgDtHght(), aRectBndryPntArrayList.getBoxID(),aRectBndryPntArrayList.getXMin(),aRectBndryPntArrayList.getYMin(),aRectBndryPntArrayList.getXMax(),aRectBndryPntArrayList.getYMax(), "traffic analysis");
        }
    }
    public void resetClusterName(Pnt myPnt)
    {
        myPnt.setName(null);        
    }
}