/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.transengnrng;

import cgtjr.academics.elctrclengnrng.cv.clustering.ClusterActn;
import cgtjr.academics.elctrclengnrng.imgprcssng.annotate.FrameAnnotator;
import cgtjr.academics.general.colorspace.ColorUpdater;
import cgtjr.academics.elctrclengnrng.imgprcssng.color.ColorDiffBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryPntArrayList;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.DigitPxls;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.elmnt.QuadPnts;
import java.awt.Point;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */
public class TrffcSensor {
    
    ColorDiffBndry nodeColorDiffBndry;
    ColorUpdater nodeColorUpdater;
    ClusterActn nodeCluster;
    private boolean nodeState;
    private int count;
    private int sampleFrame;
    private int data[][];
    private int groupNumber;
    private int colorValues[] = {0x00ff2121, 0x0000ffff, 0x000000ff, 0x00ff00ff, 0x00ffff00};
    private RectBndryPntArrayList aRectBndryPntArrayList = null;
    private RectBndryPntArrayList tmpRectBndryPntArrayList = null;
    private TreeMap aTreeMap;
    private int width;
    private int height;
    private int imagePixels[];
    final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");    
    private FrameAnnotator theFrameAnnotator;
    
    
    public TrffcSensor() {
        DmnsnVar aDmnsnVar = new DmnsnVar(4, 4, 0, 0, 10, 0, 320, 270, 0, 5, 5, 1, 160, 140, 0);
        nodeColorDiffBndry = new ColorDiffBndry(aDmnsnVar);
        nodeColorDiffBndry.setClrDistance(15);
        nodeColorUpdater = new ColorUpdater(0, 20);
        nodeCluster = new ClusterActn();
        data = new int[15][50];
        aTreeMap = new TreeMap();
        theFrameAnnotator = new FrameAnnotator();
        //nodeCluster.setTreeMap(aTreeMap);
        //dateTime = new Date();
    }
    public void setImagePixels(int myImagePixels[]){
        imagePixels = myImagePixels;
    }
    public void updateSensorNodes(ShapePnt myShapePnt) {
        //System.out.println("TrffcSensor = test 100");
        
        Vector vectorNodes = myShapePnt.getNodes();
        int aSize = vectorNodes.size();
        Pnt aPnt = null;

        //System.out.println("TrffcSensor: node count = " + aSize + ", sample frame = " + sampleFrame);
        sampleFrame++;
        for (int i = 0; i < aSize; i++) {
            aPnt = (Pnt) vectorNodes.elementAt(i);
            updateCount(aPnt);
        }
        //TODO: This should be in a seperate function
        /*
         if (sampleFrame == 245) {
         System.out.print("0");
         for (int i = 1; i
         < data[0].length; i++) {
         System.out.print("," + i);
         }
         System.out.print("\n");
         for (int i = 0; i < data.length; i++) {
         System.out.print("" + (i));
         for (int j = 0; j < data[0].length; j++) {
         System.out.print("," + data[i][j]);
         }
         System.out.print("\n");
         }
         }*/
        
    }
    
    public void updateClusterNodes(ShapePnt myShapePnt) {
        
        
        Vector aQuadVector = myShapePnt.getQuadElmnts();
        Pnt aPnt1 = null;
        Pnt aPnt2 = null;
        Pnt aPnt3 = null;
        Pnt aPnt4 = null;
        
        QuadPnts smQuadPnts;
        
        int size = aQuadVector.size();
        for (int i = 0; i < size; i++) {
            smQuadPnts = (QuadPnts) aQuadVector.elementAt(i);
            aPnt1 = smQuadPnts.getPoint0();
            aPnt2 = smQuadPnts.getPoint1();
            aPnt3 = smQuadPnts.getPoint2();
            aPnt4 = smQuadPnts.getPoint3();
            insertClusterData(aPnt1);
            insertClusterData(aPnt2);
            insertClusterData(aPnt3);
            insertClusterData(aPnt4);
        }

        //TODO: This should be in a seperate function
        /*
         if (sampleFrame == 245) {
         System.out.print("0");
         for (int i = 1; i
         < data[0].length; i++) {
         System.out.print("," + i);
         }
         System.out.print("\n");
         for (int i = 0; i < data.length; i++) {
         System.out.print("" + (i));
         for (int j = 0; j < data[0].length; j++) {
         System.out.print("," + data[i][j]);
         }
         System.out.print("\n");
         }
         }*/
        
    }
    
    public boolean isInBndry(Pnt myPnt) {
        double x = myPnt.getX1();
        double y = myPnt.getY1();
        double z = myPnt.getZ1();
        boolean isInBndry = nodeColorDiffBndry.isInBndry(x, y, z);
        //if(isInBndry == true)

        return isInBndry;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public void setNodeState(boolean myNodeState) {
        nodeState = myNodeState;
    }
    
    public void updateTimeStamp(Pnt myPnt) {
        long aDateTime = System.currentTimeMillis();
        myPnt.setDateTime(aDateTime);
    }
    
    public void updateCount(Pnt myPnt) {
        //myPnt.setColor(0x00ffffff);
        boolean isInBndry = isInBndry(myPnt);
        
        boolean nodeState = myPnt.getActiveState();
        
        int count = (int) myPnt.getValue();

        //System.out.println("TrffcSensor : x = " + myPnt.getX1() + ",y=" + myPnt.getY1() + ", state = " + nodeState + ", inbounds = " + isInBndry);

        if (nodeState == false && isInBndry == true) {
            
            myPnt.setValue(count + 1);
            //nodeColorUpdater.update(myPnt, myPnt.getValue());

            myPnt.setActiveState(true);
            //System.out.println("TrffcSensor: "+myPnt.getX1()+", "+myPnt.getY1()+", "+myPnt.getDateTime());
            updateTimeStamp(myPnt);
            updateDrctnStates(myPnt);
            if (myPnt.getX1() == 168 && myPnt.getY1() == 172) {
                System.out.println("TrffcSensor, " + new Date(myPnt.getDateTime()) + ", " + myPnt.getX1() + ", " + myPnt.getY1() + ", " + myPnt.getValue());
            }
            //nodeCluster.insertGroupData(myPnt);
            insertClusterData(myPnt);
        } else if (nodeState == true && isInBndry == false) {
            if (hasAdjacentState(myPnt) == false) {
                myPnt.setActiveState(false);
                myPnt.setXVlcty(0);
                myPnt.setYVlcty(0);
                
                myPnt.setName(null);
                myPnt.resetColor();
            } else {
                //insertClusterData(myPnt);    
            }            
        } else if (nodeState == true && isInBndry == true) {
            //nodeCluster.insertGroupData(myPnt);
            //insertClusterData(myPnt);
        }

        /*
         if (sampleFrame == 245) {
         +int xIndex = (int) ((myPnt.getX1() - 4) / 4);
         int yIndex = (int) ((myPnt.getY1() - 160) / 4);
         data[yIndex][xIndex] = (int) myPnt.getValue();
         System.out.println("" + myPnt.getX1() + "," + myPnt.getY1() + "," + (myPnt.getValue()));
         }*/
    }
    
    public void updateDrctnStates(Pnt myPnt) {
        Pnt posXPnt = myPnt.getPosXDrctn();
        Pnt negXPnt = myPnt.getNegXDrctn();
        Pnt posYPnt = myPnt.getPosYDrctn();
        Pnt negYPnt = myPnt.getNegYDrctn();
        
        float xVel = 0;
        float yVel = 0;
        float xPosVel = 0;
        float xNegVel = 0;
        float yPosVel = 0;
        float yNegVel = 0;
        float xPosDstnce = 0;
        float xNegDstnce = 0;
        float yPosDstnce = 0;
        float yNegDstnce = 0;
        long xTime = 0;
        long yTime = 0;
        long time1 = 0;
        long time2 = 0;
        
        if (posXPnt != null && posXPnt.getActiveState() == true) {
            xPosDstnce = myPnt.getX1() - posXPnt.getX1();
            time1 = posXPnt.getDateTime();
            time2 = myPnt.getDateTime();
            xTime = time2 - time1;
            //System.out.println("TrffcSensor: time1 = " + time1 + ", time2 = " + time2 + ", xTime = " + xTime);
            if (xTime != 0) {
                xPosVel = xPosDstnce / xTime;
            }
        }
        if (negXPnt != null && negXPnt.getActiveState() == true) {
            xNegDstnce = myPnt.getX1() - negXPnt.getX1();
            time2 = myPnt.getDateTime();
            time1 = negXPnt.getDateTime();
            xTime = time2 - time1;
            //System.out.println("TrffcSensor: time1 = " + time1 + ", time2 = " + time2 + ", xTime = " + xTime);
            if (xTime != 0) {
                xNegVel = xNegDstnce / xTime;
            }
        }
        if (posYPnt != null && posYPnt.getActiveState() == true) {
            yPosDstnce = myPnt.getY1() - posYPnt.getY1();
            time2 = myPnt.getDateTime();
            time1 = posYPnt.getDateTime();
            yTime = time2 - time1;
            //System.out.println("TrffcSensor: time1 = " + time1 + ", time2 = " + time2 + ", yTime = " + yTime);
            if (yTime != 0) {
                yPosVel = yPosDstnce / yTime;
            }
        }
        if (negYPnt != null && negYPnt.getActiveState() == true) {
            yNegDstnce = myPnt.getY1() - negYPnt.getY1();
            time2 = myPnt.getDateTime();
            time1 = negYPnt.getDateTime();
            yTime = time2 - time1;
            //System.out.println("TrffcSensor: time1 = " + time1 + ", time2 = " + time2 + ", yTime = " + yTime);
            if (yTime != 0) {
                yNegVel = yNegDstnce / yTime;
            }
        }
        
        yVel = yPosVel + yNegVel;
        xVel = xPosVel + xNegVel;
        myPnt.setXVlcty(xVel);
        myPnt.setYVlcty(yVel);
        //System.out.println("TrffcSensor: xTime = " + xTime + ", yTime = " + yTime + ", x = " + myPnt.getX1() + ", y = " + myPnt.getY1() + ", xVel = " + xVel + ", yVel = " + yVel);
    }
    
    public boolean hasAdjacentState(Pnt myPnt) {
        Pnt posXPnt = myPnt.getPosXDrctn();
        Pnt negXPnt = myPnt.getNegXDrctn();
        Pnt posYPnt = myPnt.getPosYDrctn();
        Pnt negYPnt = myPnt.getNegYDrctn();
        boolean adjacentstate = false;
        
        
        if (negXPnt != null && negXPnt.getActiveState() == true) {
            adjacentstate = true;
        } else {
            adjacentstate = false;
        }
        if (posYPnt != null && posYPnt.getActiveState() == true) {
            adjacentstate = adjacentstate & true;
        } else {
            adjacentstate = adjacentstate & false;
        }
        if (negYPnt != null && negYPnt.getActiveState() == true) {
            adjacentstate = adjacentstate & true;
        } else {
            adjacentstate = adjacentstate & false;
        }
        return adjacentstate;
    }
    /*
     * public void updateCount(Pnt myPnt) { boolean isInBndry =
     * isInBndry(myPnt);
     *
     * boolean nodeState = myPnt.getStateStts();
     *
     * int count = (int) myPnt.getValue();
     *
     * //System.out.println("TrffcSensor : x =
     * "+myPnt.getX1()+",y="+myPnt.getY1()+", state = "+nodeState+", inbounds =
     * "+isInBndry);
     *
     * if (nodeState == false && isInBndry == false) { myPnt.setValue(count +
     * 1); nodeColorUpdater.update(myPnt, myPnt.getValue());
     *
     * myPnt.setStateStts(true); } else if (nodeState == true && isInBndry ==
     * true) { myPnt.setStateStts(false); } if (sampleFrame == 245) { int xIndex
     * = (int) ((myPnt.getX1() - 4)/4); int yIndex = (int) ((myPnt.getY1() -
     * 160)/4); data[yIndex][xIndex] = (int)myPnt.getValue();
     * System.out.println("" + myPnt.getX1() + "," + myPnt.getY1() + "," +
     * (myPnt.getValue())); } }
     */
    
    public void setImgPxlData(int[] myImgPixels, int myImgWidth, int myImgHeight) {
        imagePixels = myImgPixels;
        width = myImgWidth;
        height = myImgHeight;
        nodeColorDiffBndry.setImgPxlData(myImgPixels, myImgWidth, myImgHeight);
    }
    
    public void insertClusterData(Pnt myPnt1) {
        
        if (myPnt1 == null) {
            return;
        }
        boolean isInBndry = isInBndry(myPnt1);
        /*
         if (isInBndry == false) {
         return;
         }*/

        //Set aSet = aTreeMap.keySet();

        boolean isActiveState = myPnt1.getActiveState();
        
        String aGroupName = myPnt1.getName();
        //System.out.println("TrffcSensor : aGroupName = " + aGroupName);
        Pnt posXPnt = myPnt1.getPosXDrctn();
        Pnt negXPnt = myPnt1.getNegXDrctn();
        Pnt posYPnt = myPnt1.getPosYDrctn();
        Pnt negYPnt = myPnt1.getNegYDrctn();

        //TODO: update code with state group variables
        //if (isActiveState == true && aGroupName != null && posXPnt != null && posXPnt.getActiveState() == true) {
        if (aGroupName == null && posXPnt != null && posXPnt.getName() != null) {
            //System.out.println("TrffcSensor: test 3 posXPnt.getName()=" + posXPnt.getName());
            aGroupName = posXPnt.getName();
            myPnt1.setName(aGroupName);
            myPnt1.setColor(posXPnt.getColor());
            aRectBndryPntArrayList = (RectBndryPntArrayList) aTreeMap.get(aGroupName);
            aRectBndryPntArrayList.add(myPnt1);
            aRectBndryPntArrayList.setBoxID(Integer.parseInt(aGroupName));            
            //System.out.println("TrffcSensor: aGroupName = " + aGroupName);
            //posXPnt.setColor(0x0000ffff);            
        } else if (aGroupName == null && negXPnt != null && negXPnt.getName() != null) {
            //System.out.println("TrffcSensor: test 4a negXPnt.getName() = "+negXPnt.getName());
            aGroupName = negXPnt.getName();
            myPnt1.setName(aGroupName);
            myPnt1.setColor(negXPnt.getColor());
            aRectBndryPntArrayList = (RectBndryPntArrayList) aTreeMap.get(aGroupName);
            aRectBndryPntArrayList.add(myPnt1);
            aRectBndryPntArrayList.setBoxID(Integer.parseInt(aGroupName));            
            //System.out.println("TrffcSensor: test 4b aGroupName = " + aGroupName);
            //posXPnt.setColor(0x0000ffff);            
        } else if (aGroupName == null && posYPnt != null && posYPnt.getName() != null) {
            //System.out.println("TrffcSensor: test 5a posYPnt.getName()" + posYPnt.getName());
            aGroupName = posYPnt.getName();
            myPnt1.setName(aGroupName);
            myPnt1.setColor(posYPnt.getColor());
            aRectBndryPntArrayList = (RectBndryPntArrayList) aTreeMap.get(aGroupName);
            aRectBndryPntArrayList.add(myPnt1);
            aRectBndryPntArrayList.setBoxID(Integer.parseInt(aGroupName));            
            //System.out.println("TrffcSensor: test 5b aGroupName = " + aGroupName);
            //posXPnt.setColor(0x0000ffff);
        } else if (aGroupName == null && negYPnt != null && negYPnt.getName() != null) {
            //System.out.println("TrffcSensor: test 6a negYPnt.getName() = " + negYPnt.getName());
            aGroupName = negYPnt.getName();
            myPnt1.setName(aGroupName);
            myPnt1.setColor(negYPnt.getColor());
            aRectBndryPntArrayList = (RectBndryPntArrayList) aTreeMap.get(aGroupName);
            aRectBndryPntArrayList.add(myPnt1);
            aRectBndryPntArrayList.setBoxID(Integer.parseInt(aGroupName));            
            //System.out.println("TrffcSensor: test 6b aGroupName = " + aGroupName);

        } else if (aGroupName == null) {
            //posXPnt.setColor(0x00ff0000);
            //System.out.println("TrffcSensor: test 7");
            int colorIndex = groupNumber % colorValues.length;
            aGroupName = "" + groupNumber;
            myPnt1.setName(aGroupName);
            myPnt1.setColor(colorValues[colorIndex]);
            //myPnt1.setColor(0x00ff0000);  

            aRectBndryPntArrayList = new RectBndryPntArrayList(myPnt1);
            //Integer anIteger = new Integer(groupNumber);
            //aRectBndryPntArrayList.setBoxID(groupNumber);
            //System.out.println("TrffcSensor : groupname = " + aGroupName);
            aTreeMap.put(aGroupName, aRectBndryPntArrayList);
            //System.out.println("TrffcSensor: test 7b");
            groupNumber++;
        } else if (aGroupName != null) {
            //aRectBndryPntArrayList = (RectBndryPntArrayList) aTreeMap.get(aGroupName);
            //aRectBndryPntArrayList.add(myPnt1);            
        }
    }
    public void printData() {
        int anImageWidth = width;
        int anImageHeight = height;        
        
        Point aPoint = null;
        RectBndryPntArrayList aRectBndryPntArrayList = null;
        Set aSet = aTreeMap.keySet();
        Iterator anIterator1 = aSet.iterator();
        //Iterator anIterator2 = null;
        String aGroupKey = null;
        //int pixelData[] = getImageDrawData().getImagePixels();
        //int myPixelData[] = imagePixelData.getImagePixels();
        int myPixelData[] = imagePixels;
        
        while (anIterator1.hasNext()) {
            aGroupKey = (String) anIterator1.next();
            //System.out.println("----------GROUP : " + aGroupKey.intValue() + "------------");
            aRectBndryPntArrayList = (RectBndryPntArrayList) aTreeMap.get(aGroupKey);
            //aRectBndryPntArrayList.drawSquare(myPixelData, width, height, 0x0000ff00);
            //anIterator2 = aRectBndryPntArrayList.iterator();
            int x = aRectBndryPntArrayList.getXAvg();
            int y = aRectBndryPntArrayList.getYAvg();
            //int anImageIndex = ImageTool.rtrvIndex(x, y, anImageWidth);
          
            int nmbrIndex = 40;
            Calendar now = Calendar.getInstance();
            String aDateTime = dateFormat.format(now.getTime());
            //int pxlDataX1[] = DigitPntPixels.rtrvNmbr(aGroupKey.intValue());            
            //DigitPxls.draw9x9(myPixelData,pxlDataX1,anImageWidth,anImageHeight,anImageIndex,nmbrIndex);
          
            //DigitPxls.drawDigit(myPixelData,anImageWidth,anImageHeight,x,y,aRectBndryPntArrayList.getBoxID());
            //theFrameAnnotator.process(aDateTime, 0, width,height, aRectBndryPntArrayList.getBoxID(),aRectBndryPntArrayList.getXMin(),aRectBndryPntArrayList.getYMin(),aRectBndryPntArrayList.getXMax(),aRectBndryPntArrayList.getYMax(), "traffic analysis");
            //aTreeMap.remove(aGroupKey);
        }
        
    }    
}