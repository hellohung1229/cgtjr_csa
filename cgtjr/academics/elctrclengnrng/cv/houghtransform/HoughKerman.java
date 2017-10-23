package cgtjr.academics.elctrclengnrng.cv.houghtransform;

import java.util.*;
import java.awt.*;
import java.awt.image.*;

public class HoughKerman extends ImageFilter
{
   private int theAccumulator[][];
   private Vector theAccumulatorVector[][];
   private int rhoParameterSpace[];
   private int thetaParameterSpace[];

   private int theYMinValue[][];
   private int theXMinValue[][];
   private int theYMaxValue[][];
   private int theXMaxValue[][];

   private Vector theXVector;
   private Vector theYVector;

   private Vector theRhoThetaVector;

   private String theRhoName = "rho";
   private String theThetaName = "theta";
   private String theXCoordName = "xcoord";
   private String theYCoordName = "ycoord";
   private String theSquareName = "square";

   private int theRhoDelta = 1;

   private Vector theThetaVector;
   private Vector theRhoVector;

   private Vector theThetaVector2;
   private Vector theRhoVector2;
   

   private Hashtable theRhoThetaHashtable;

   private Vector thePerpThetaVector;
   private Vector thePerpRhoVector;
   private Hashtable theRhoThetaPerpHashtable;

   private Vector thePeraThetaVector;
   private Vector thePeraRhoVector;
   private Hashtable theRhoThetaParaHashtable;
   
   private Hashtable theSquareHashtable;
   private Hashtable theSquareXYHashtable;
   private Hashtable theRectangleHashtable;


   private Vector theRectXYVector;
   private Vector theRectangleVector;
   private Vector theRectCoordVector;

   private int theThreshHold = 10;

   public static final String type = "HoughKerman";

   /*Note: 10/15/2013 ... change code
   private UgotImage theUgotImage;
   */ 

   private String threeDData = "";
   
 
   public HoughKerman()
   {
      //setName(type);
      theThetaVector = new Vector();
      theRhoVector = new Vector();
      theRhoThetaHashtable = new Hashtable();
      theRhoThetaPerpHashtable = new Hashtable();
      theRhoThetaParaHashtable = new Hashtable();
      theSquareHashtable = new Hashtable();
      theRectXYVector = new Vector();
      theRectangleVector = new Vector();
      theRectangleHashtable = new Hashtable();
      theRhoThetaVector = new Vector();
      theRectCoordVector = new Vector();
   }
   public void setImage(BufferedImage a){}
   public void setImage(Image a){}
   public HoughKerman(Image myImage)
   {
      //setName(type);
      /*Note 10/15/2013 ... change code
      setInputPixels(myImage);
      */ 
      theThetaVector = new Vector();
      theRhoVector = new Vector();
      theRhoThetaHashtable = new Hashtable();
      theRhoThetaPerpHashtable = new Hashtable();
      theRhoThetaParaHashtable = new Hashtable();
      theSquareHashtable = new Hashtable();
      theRectXYVector = new Vector();
      theRectangleVector = new Vector();
      theRectangleHashtable = new Hashtable();
      theRhoThetaVector = new Vector();
      theRectCoordVector = new Vector();
   }
   public HoughKerman(int inputPixels[], int myWidth,int myHeight)
   {
      //setName(type);
      theThetaVector = new Vector();
      theRhoVector = new Vector();
      theRhoThetaHashtable = new Hashtable();
      theRhoThetaPerpHashtable = new Hashtable();
      theRhoThetaParaHashtable = new Hashtable();
      theSquareHashtable = new Hashtable();
      theRectXYVector = new Vector();
      theRectangleVector = new Vector();
      theRectangleHashtable = new Hashtable();
      theRhoThetaVector = new Vector();
      theRectCoordVector = new Vector();
   }

   public void initialize()
   {        
   }   
   public void setThreeDData(String myData)
   {
      threeDData = myData;
   }
   public String getThreeDData()
   {
      return threeDData;
   }
   public void detectRectangles()
   {
      /*Note 10/15/2013: ... this code needs modification 
      int inputPixels[] = getInputPixels();
      int aWidth = getWidth();
      int aHeight = getHeight();
      rectangleDetection(inputPixels,aWidth,aHeight);
      */ 
   }
   public void rectangleDetection(int aTwoDImage[],int aWidth,int aHeight)
   {
      //System.out.println("HoughKerman.rectangleDetection() : width="+aWidth+", height="+aHeight);
      int anAccummulator[][] = processHoughTransform(aTwoDImage,aWidth,aHeight);
System.out.println("test 1");
      Hashtable aRhoThetaHashtable = findLines(anAccummulator,theThreshHold);
System.out.println("test 2");
      Hashtable aRhoThetaPerpHashtable = findPerpendicularLine(aRhoThetaHashtable);
System.out.println("test 3");
      Hashtable aRhoThetaParaHashtable = findParallelLine(aRhoThetaHashtable);
System.out.println("test 4");
      //Vector myRectangleVector = buildRectangles(theRhoThetaHashtable,theRhoThetaPerpHashtable,aRhoThetaParaHashtable);
      Vector myRectangleVector = buildRectangles(theRhoThetaPerpHashtable,aRhoThetaParaHashtable);
System.out.println("test 5");
      Vector myRectCoordVector = buildRectCoord(myRectangleVector);
System.out.println("test 6");
      generateVRMLBox(myRectCoordVector);
System.out.println("test 7");
      //generateSVGBox(myRectangleVector);
      //Vector myXYCoordRectVector = buildRectXYCoord(theRhoThetaHashtable,theRhoThetaPerpHashtable,aRhoThetaParaHashtable);
      //Vector anXYCoordVector = buildRectangleXYCoord(aRectangleHashtable);
      //generateSVGBox(anXYCoordVector);
   }      
   public int[][] processHoughTransform(int aTwoDImage[],int aWidth,int aHeight)
   {
      Vector myVector = new Vector();
      Hashtable myHashtable = null;
      //TwoDConvertFilter aTwoDConvertFilter = new TwoDConvertFilter();
      /*Note: 10/15/2015 ... change code
      int myTwoDImage[][] = ArrayDimensionConvert.getTwoDImage(aTwoDImage,aWidth,aHeight);
      */
      int thetaDelta = 10;
      
      int myStartNumber = 0;
      int myEndNumber = 179;

      thetaParameterSpace  = descretize(myStartNumber,myEndNumber,thetaDelta); 
      int maxLength = (int)Math.sqrt(aWidth*aWidth + aHeight*aHeight);
      rhoParameterSpace = descretize(0,maxLength,theRhoDelta);

      theAccumulator = new int[rhoParameterSpace.length][thetaParameterSpace.length];
      theXMinValue  = new int[rhoParameterSpace.length][thetaParameterSpace.length];
      theYMinValue  = new int[rhoParameterSpace.length][thetaParameterSpace.length];
      theXMaxValue  = new int[rhoParameterSpace.length][thetaParameterSpace.length];
      theYMaxValue  = new int[rhoParameterSpace.length][thetaParameterSpace.length];

      /*Note: 10/15/2013 ... change code
      calculateRhoTheta(myTwoDImage,aWidth,aHeight);
      */ 
      //System.out.println("HoughKerman : finish calculateRhoTheta");
      return theAccumulator;
   }
   public void printParams(Hashtable aHashtable)
   {
      Vector myVector1 = (Vector) aHashtable.get(theRhoName);
      Vector myVector2 = (Vector) aHashtable.get(theThetaName);
      printParams(myVector1,myVector2);
   }
   public void printParams(Vector aVector1,Vector aVector2)
   {
      int myLength = aVector1.size();
      for(int i=0;i<myLength;i++)
      {
         String aString1 = (String) aVector1.elementAt(i);
         String aString2 = (String) aVector2.elementAt(i);
         //System.out.println(aString1+","+aString2);
      }
   }   
   public int[][] calculateRhoTheta(int myHorizontalCoord,int myVerticalCoord,int aWidth,int aHeight)
   {
      int kIndex = 0;
      int aRhoParameter = 0;
      theRhoDelta = 4;
      
      for(int hIndex = 0;hIndex <  thetaParameterSpace.length;hIndex++)
      {
         aRhoParameter = (int) Math.abs(( myHorizontalCoord*Math.cos(Math.toRadians(thetaParameterSpace[hIndex])) + 
         myVerticalCoord*Math.sin(Math.toRadians(thetaParameterSpace[hIndex]))));
 
         kIndex = getIndex(rhoParameterSpace,aRhoParameter);     
         theAccumulator[kIndex][hIndex]++;

         setYXBoundValues(kIndex,hIndex,myHorizontalCoord,myVerticalCoord);
      }
      return theAccumulator;
   }
   public int[][] calculateRhoTheta(int aTwoDImage[][],int aWidth,int aHeight)
   {
      int kIndex = 0;
      int aRhoParameter = 0;
      theRhoDelta = 4;
      int rgbValues = 0;
      for(int iIndex = 0;iIndex < aWidth;iIndex++)
      {
         for(int jIndex = 0;jIndex < aHeight;jIndex++)
         {
            rgbValues = aTwoDImage[iIndex][jIndex] & 0x00ffffff;
            //System.out.println("HoughKerman.calculateRhoTheta(): rgbValues = "+rgbValues);
            if(rgbValues >= 0x00eeeeee)
            {  
               for(int hIndex = 0;hIndex <  thetaParameterSpace.length;hIndex++)
               {
                  aRhoParameter = (int) Math.abs(( iIndex*Math.cos(Math.toRadians(thetaParameterSpace[hIndex])) + 
                  jIndex*Math.sin(Math.toRadians(thetaParameterSpace[hIndex]))));
                  kIndex = getIndex(rhoParameterSpace,aRhoParameter);     
                  theAccumulator[kIndex][hIndex]++;
                  System.out.println("HoughKerman : theAccumulator["+kIndex+"]["+hIndex+"] = "+theAccumulator[kIndex][hIndex]);
                  setYXBoundValues(kIndex,hIndex,iIndex,jIndex);
               }
            }
         }
      } 
      return theAccumulator;
   }
   public boolean isInAccumulator(int rho,int theta,int anAccumulator[][])
   { 
      if(anAccumulator[rho][theta] >= 1)
      {
         return true;
      }else{
         return false;
      }
   }
   public void setYXBoundValues(int kIndex,int hIndex,int x,int y)
   {
      if(theAccumulator[kIndex][hIndex] == 1)
      {
         theYMinValue[kIndex][hIndex] = y; 
         theXMinValue[kIndex][hIndex] = x;
         theYMaxValue[kIndex][hIndex] = y;
         theXMaxValue[kIndex][hIndex] = x;
      } 
      if(theYMinValue[kIndex][hIndex] > y)
      {
         theYMinValue[kIndex][hIndex] = y; 
      }
      if(theXMinValue[kIndex][hIndex] > x)
      {
         theXMinValue[kIndex][hIndex] = x;
      }
      if(theYMaxValue[kIndex][hIndex] < y)
      {
         theYMaxValue[kIndex][hIndex] = y;
      }
      if(theXMaxValue[kIndex][hIndex] < x)
      {
         theXMaxValue[kIndex][hIndex] = x;
      }
   }
   public int[][] findLocalMaximum(int anAccumulator[][],int threshHold)
   {
      int aWidth = anAccumulator.length;
      int aHeight = anAccumulator[0].length;
      int localMaximum[][] = new int[aWidth][aHeight];

      for(int kIndex=0;kIndex<aWidth;kIndex++)
      {
         for(int hIndex=0;hIndex<aHeight;hIndex++)
         {
            if(anAccumulator[kIndex][hIndex] >= threshHold)
            {
               localMaximum[kIndex][hIndex] = anAccumulator[kIndex][hIndex];    
               //System.out.println("findLocalMaximum : "+anAccumulator[kIndex][hIndex]);
               
               //generateSVGBox(anAccumulator[kIndex][hIndex],anAccumulator[kIndex][hIndex]);
               //System.out.println("findLocalMaximum : rho("+kIndex+")="+rhoParameterSpace[kIndex]+","+"theta("+hIndex+")="+thetaParameterSpace[hIndex]+","+"counter="+theAccumulator[kIndex][hIndex]);
            }     
         }
      }   
      return localMaximum;
   }
   public Hashtable findLines(int anAccumulator[][],int threshHold)
   {
      //System.out.println("HoughKerman : findLocalMaximum");
      int aWidth = anAccumulator.length;
      int aHeight = anAccumulator[0].length;
      int localMaximum[][] = new int[aWidth][aHeight];
      int perpendicularThetaIndex= 0;
      int rhoValue = 0;
      int thetaValue = 0;
      //System.out.println("HoughKerman.findLines():anAccumulator.width="+aWidth+", anAccumulator.height="+aHeight); 
      for(int kIndex=0;kIndex<aWidth;kIndex++)
      {
         for(int hIndex=0;hIndex<aHeight;hIndex++)
         {
            //System.out.println("rho("+kIndex+")="+rhoParameterSpace[kIndex]+","+"theta("+hIndex+")="+thetaParameterSpace[hIndex]+","+"accumulator="+theAccumulator[kIndex][hIndex]+", threshold="+threshHold);
           
            if(anAccumulator[kIndex][hIndex] >= threshHold)
            {
               rhoValue = rhoParameterSpace[kIndex];
               thetaValue = thetaParameterSpace[hIndex];

               //localMaximum[kIndex][hIndex] = anAccumulator[kIndex][hIndex];    
               //System.out.println("HoughKerman.findLines(): LocalMaximum : "+anAccumulator[kIndex][hIndex]);               
               //generateSVGBox(anAccumulator[kIndex][hIndex],anAccumulator[kIndex][hIndex]);
               System.out.println("HoughKerman.findLines(): Found Line! ... rho("+kIndex+")="+rhoParameterSpace[kIndex]+","+"theta("+hIndex+")="+thetaParameterSpace[hIndex]+","+"counter="+theAccumulator[kIndex][hIndex]);
               //System.out.println("HoughKerman.findLines(): rho("+kIndex+")="+rhoParameterSpace[kIndex]+","+"theta("+hIndex+")="+thetaParameterSpace[hIndex]+","+"counter="+theAccumulator[kIndex][hIndex]);
               sortIntoVector(rhoValue,thetaValue);
            }     
         }
      }
      return theRhoThetaHashtable;
   }
   private void sortIntoVector(int rhoValue,int thetaValue)
   { 
      int aVectorLength = theRhoVector.size();
      
      if(aVectorLength == 0)
      {
         //System.out.println("size = 0");
         theRhoVector.addElement(new Integer(rhoValue));
         theThetaVector.addElement(new Integer(thetaValue));
         theRhoThetaHashtable.put(theRhoName,theRhoVector);
         theRhoThetaHashtable.put(theThetaName,theThetaVector);

         //System.out.println("HoughKerman - sortIntoVector : rhoValue = "
         //                         +rhoValue+"thetaValue = "+thetaValue);
         return;
      }            
      for(int anIndex = 0;anIndex < aVectorLength;anIndex++)
      {
            Integer myRhoValue = (Integer)theRhoVector.elementAt(anIndex);      
            if(rhoValue < myRhoValue.intValue())
            {
               theRhoVector.insertElementAt(new Integer(rhoValue),anIndex);
               theThetaVector.insertElementAt(new Integer(thetaValue),anIndex);
               theRhoThetaHashtable.put(theRhoName,theRhoVector);
               theRhoThetaHashtable.put(theThetaName,theThetaVector);
               //System.out.println("HoughKerman - sortIntoVector : rhoValue = "
               //                   +rhoValue+"thetaValue = "+thetaValue);
               return;
            }
      }         
      theRhoVector.addElement(new Integer(rhoValue));
      theThetaVector.addElement(new Integer(thetaValue));
      theRhoThetaHashtable.put(theRhoName,theRhoVector);
      theRhoThetaHashtable.put(theThetaName,theThetaVector);               
      //System.out.println("HoughKerman - sortIntoVector : rhoValue = "
      //                      +rhoValue+"thetaValue = "+thetaValue);
      return;           
   }
   public int getDistance(int aXCoord1,int aYCoord1,int aXCoord2,int aYCoord2)
   {
      //System.out.println("aXCoord1 = "+aXCoord1+" aXCoord2="+aXCoord2);
      //System.out.println("aYCoord1 = "+aYCoord1+" aYCoord2="+aYCoord2);

      int myDistance = 0;//(x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) + (z2-z1)*(z2-z1);
      try{
         myDistance = (int)Math.sqrt((aXCoord2-aXCoord1)*(aXCoord2-aXCoord1) + 
                          (aYCoord2-aYCoord1)*(aYCoord2-aYCoord1));
         return myDistance;
      }catch(ArithmeticException ae){
         return Integer.MIN_VALUE; 
      }
   }
   public void generateVRMLBox(Vector aVector)
   {
      Hashtable myXYHashtable = new Hashtable();
      Vector myXVector = null;
      Vector myYVector = null;

      int aLength1 = aVector.size();
      int aLength2 = 0;
      Integer myXInteger0 = null;
      Integer myYInteger0 = null;
      Integer myXInteger1 = null;
      Integer myYInteger1 = null;
      Integer myXInteger2 = null;
      Integer myYInteger2 = null;
      Integer myXInteger3 = null;
      Integer myYInteger3 = null;
      String vrmlData = null;
      int aWidth = 0;
      int aHeight = 0;

      for(int anIndex1=0;anIndex1 < aLength1;anIndex1++)
      {

         myXYHashtable = (Hashtable)aVector.elementAt(anIndex1);
         myXVector = (Vector)myXYHashtable.get(theXCoordName);
         myYVector = (Vector)myXYHashtable.get(theYCoordName);
         
         myXInteger0 = (Integer)myXVector.elementAt(0);
         myYInteger0 = (Integer)myYVector.elementAt(0);
         myXInteger1 = (Integer)myXVector.elementAt(1);
         myYInteger1 = (Integer)myYVector.elementAt(1);
         myXInteger2 = (Integer)myXVector.elementAt(2);
         myYInteger2 = (Integer)myYVector.elementAt(2);
         myXInteger3 = (Integer)myXVector.elementAt(3);
         myYInteger3 = (Integer)myYVector.elementAt(3);

         //aWidth = (int)Math.abs(myXInteger0.intValue() - myXInteger2.intValue());
         //aHeight = (int)Math.abs(myYInteger0.intValue() - myYInteger3.intValue());
         aWidth = getDistance(myXInteger0.intValue(),myYInteger0.intValue(),myXInteger3.intValue(),myYInteger3.intValue());
         aHeight = getDistance(myXInteger0.intValue(),myYInteger0.intValue(),myXInteger1.intValue(),myYInteger1.intValue());
         //aHeight = (int)Math.abs(myYInteger0.intValue() - myYInteger3.intValue());

         vrmlData = generateVRMLBox(aHeight,aWidth);
      
         /*
         generateSVGBox(myXInteger0.intValue(),myYInteger0.intValue(),
                        myXInteger1.intValue(),myYInteger1.intValue(),
                        myXInteger2.intValue(),myYInteger2.intValue(),
                        myXInteger3.intValue(),myYInteger3.intValue());
         */
         /*Note : 10/15/2013 ... change code
         writeDataToFile("data/vrml/hkthreedimage.wrl",vrmlData);
         */ 
      }
   }
   public String generateX3DBox(int aLength,int aWidth)
   {
      //System.out.println("myCoordinates = "+myCoordinates);
      String x3dString = 
         "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"+
         "<X3D profile=\"Interchange\" version=\"3.1\">\n"+
         "<Scene>\n"+
         "<Background skycolor=\"1 1 1\" />\n"+
         "<Viewpoint description=\"view\"/>\n"+ 
         "<Transform translation=\"0 0 0\">\n"+
         "<Shape>\n"+ 
         "<Box size=\"."+aLength+" ."+aWidth+" ."+aWidth+"\" />\n"+
         "<Appearance>\n"+
         "<Material diffuseColor=\"0 .5 1\" />\n"+
         "</Appearance>\n"+ 
         "</Shape>\n"+ 
         "</Transform>\n"+
         "</Scene>\n"+
         "</X3D>\n";
      System.out.println(x3dString);
      return x3dString;
   }
   public String generateVRMLBox(int myLength,int myWidth)
   {
      //System.out.println("myCoordinates = "+myCoordinates);
      double aLength =((double)myLength)/100;
      double aWidth = ((double)myWidth)/100;
      String vrmlString = 
      "#VRML V2.0 utf8\n"+
      "Group {\n"+
      "  children [\n"+
      "    NavigationInfo {\n"+
      "      type      \"EXAMINE\"\n"+
      "      headlight TRUE\n"+
      "    },\n"+
      "    DEF GreenBox Transform {\n"+
      "      children [\n"+
      "        Transform{\n"+
      "           translation 0 0 0\n"+
      "              children\n"+
        
      "        Shape {\n"+
      "          appearance Appearance {\n"+
      "            material Material {\n"+
      "              diffuseColor 0.0 .8 0.0\n"+
      "            }\n"+
      "          }\n"+
      "          geometry Box {\n"+
      "            size "+aWidth+" "+ " "+aLength+" "+aWidth+"\n"+
      "          }\n"+
      "        }}\n"+
      "      ]\n"+
      "    },\n"+
      "  ]\n"+
      "}\n";
      System.out.println(vrmlString);
      return vrmlString;
   }
   public String generateSVGBox(int xCoord1,int yCoord1,int xCoord2,int yCoord2,
                                int xCoord3,int yCoord3,int xCoord4,int yCoord4)
   {
      String myCoordinates = ""+xCoord1+","+yCoord1+" "+xCoord3+","+yCoord3+" "+xCoord4+","+yCoord4+" "+
                                xCoord2+","+yCoord2;
      //System.out.println("myCoordinates = "+myCoordinates);
      String mySVGString = 
         "<svg width='100%' height='100%' version='1.1' xmlns='http://www.w3.org/2000/svg'>\n"+
         "<polygon points='"+myCoordinates+"' style='fill:#cccccc; stroke:#000000;stroke-width:1'/>\n"+
         "</svg>\n";
      System.out.println(mySVGString);
      return mySVGString;
   }
   public Vector buildRectCoord(Vector aVector)
   {
      Hashtable myXYHashtable = new Hashtable();
      Hashtable myXYCoordHashtable = new Hashtable();
      Vector myRhoVector = null;
      Vector myThetaVector = null;
      Vector myYCoordVector = new Vector();
      Vector myXCoordVector = new Vector();

      int myRho0Value = 0;
      int myRho1Value = 0;
      int myRho2Value = 0;
      int myRho3Value = 0;
      int myTheta0Value = 0;
      int myTheta1Value = 0;
      int myTheta2Value = 0;
      int myTheta3Value = 0;

      int myXIntersection0 = 0;
      int myYIntersection0 = 0;
      int myXIntersection1 = 0;
      int myYIntersection1 = 0;
      int myXIntersection2 = 0;
      int myYIntersection2 = 0;
      int myXIntersection3 = 0;
      int myYIntersection3 = 0;

      int aLength2 = 0;
      Integer myRho0 = null;
      Integer myTheta0 = null;
      Integer myRho1 = null;
      Integer myTheta1 = null;
      Integer myRho2 = null;
      Integer myTheta2 = null;
      Integer myRho3 = null;
      Integer myTheta3 = null;

      boolean checkPoint1 = false;
      boolean checkPoint2 = false;
      boolean checkPoint3 = false;
      boolean checkPoint4 = false;
      boolean checkPoint5 = false;
      boolean checkPoint6 = false;
      boolean checkPoint7 = false;
      boolean checkPoint8 = false;
      
      int aWidth = 0;
      int aHeight = 0;

      int aLength1 = aVector.size();
      //System.out.println("Generate real rectangle from "+aLength1+" rectangles");
      for(int anIndex1=0;anIndex1 < aLength1;anIndex1++)
      {
         myXYHashtable = (Hashtable)aVector.elementAt(anIndex1);
         myRhoVector = (Vector)myXYHashtable.get(theRhoName);
         myThetaVector = (Vector)myXYHashtable.get(theThetaName);
         
         myRho0 = (Integer)myRhoVector.elementAt(0);
         myTheta0 = (Integer)myThetaVector.elementAt(0);
         myRho1 = (Integer)myRhoVector.elementAt(1);
         myTheta1 = (Integer)myThetaVector.elementAt(1);
         myRho2 = (Integer)myRhoVector.elementAt(2);
         myTheta2 = (Integer)myThetaVector.elementAt(2);
         myRho3 = (Integer)myRhoVector.elementAt(3);
         myTheta3 = (Integer)myThetaVector.elementAt(3);

         myRho0Value = myRho0.intValue();
         myRho1Value = myRho1.intValue();
         myRho2Value = myRho2.intValue();
         myRho3Value = myRho3.intValue();
         myTheta0Value = myTheta0.intValue();
         myTheta1Value = myTheta1.intValue();
         myTheta2Value = myTheta2.intValue();
         myTheta3Value = myTheta3.intValue();

         myXIntersection0 = getXIntersectionLine(myRho0,myTheta0,myRho2,myTheta2);
         myYIntersection0 = getYIntersectionLine(myRho0,myTheta0,myRho2,myTheta2);
         myXIntersection1 = getXIntersectionLine(myRho0,myTheta0,myRho3,myTheta3);
         myYIntersection1 = getYIntersectionLine(myRho0,myTheta0,myRho3,myTheta3);
         myXIntersection2 = getXIntersectionLine(myRho1,myTheta1,myRho2,myTheta2);
         myYIntersection2 = getYIntersectionLine(myRho1,myTheta1,myRho2,myTheta2);
         myXIntersection3 = getXIntersectionLine(myRho1,myTheta1,myRho3,myTheta3);
         myYIntersection3 = getYIntersectionLine(myRho1,myTheta1,myRho3,myTheta3);
         
         checkPoint1 = checkBoundary(myXIntersection0,myYIntersection0,myRho0Value,myTheta0Value);
         checkPoint2 = checkBoundary(myXIntersection0,myYIntersection0,myRho2Value,myTheta2Value);
         checkPoint3 = checkBoundary(myXIntersection1,myYIntersection1,myRho0Value,myTheta0Value);
         checkPoint4 = checkBoundary(myXIntersection1,myYIntersection1,myRho3Value,myTheta3Value);
         checkPoint5 = checkBoundary(myXIntersection2,myYIntersection2,myRho1Value,myTheta1Value);
         checkPoint6 = checkBoundary(myXIntersection2,myYIntersection2,myRho2Value,myTheta2Value);
         checkPoint7 = checkBoundary(myXIntersection3,myYIntersection3,myRho1Value,myTheta1Value);
         checkPoint8 = checkBoundary(myXIntersection3,myYIntersection3,myRho3Value,myTheta3Value);
         /*
            System.out.println("myXIntersection0="+myXIntersection0+",myYIntersection0="+myYIntersection0+";"+
                               "myXIntersection1="+myXIntersection1+",myYIntersection1="+myYIntersection1+";"+
                               "myXIntersection2="+myXIntersection2+",myYIntersection2="+myYIntersection2+";"+
                               "myXIntersection3="+myXIntersection3+",myYIntersection3="+myYIntersection3+";");               
         */
         if(checkPoint1 && checkPoint2 && checkPoint3 && checkPoint4 && 
            checkPoint5 && checkPoint6 && checkPoint7 && checkPoint8  )
         {
               if(!doesRectangleExist(myXIntersection0,myYIntersection0,myXIntersection1,myYIntersection1,myXIntersection2,myYIntersection2,myXIntersection3,myYIntersection3))
               {
                  
                  myXCoordVector.addElement(new Integer(myXIntersection0));
                  myXCoordVector.addElement(new Integer(myXIntersection1));
                  myXCoordVector.addElement(new Integer(myXIntersection2));
                  myXCoordVector.addElement(new Integer(myXIntersection3));
                  myYCoordVector.addElement(new Integer(myYIntersection0));
                  myYCoordVector.addElement(new Integer(myYIntersection1));
                  myYCoordVector.addElement(new Integer(myYIntersection2));
                  myYCoordVector.addElement(new Integer(myYIntersection3));
                  myXYCoordHashtable.put(theXCoordName,myXCoordVector);
                  myXYCoordHashtable.put(theYCoordName,myYCoordVector);  
                  theRectCoordVector.addElement(myXYCoordHashtable);
                  /*System.out.println(""+myXIntersection0+","+myYIntersection0+";"+
                                  ""+myXIntersection1+","+myYIntersection1+";"+
                                  ""+myXIntersection2+","+myYIntersection2+";"+
                                  ""+myXIntersection3+","+myYIntersection3+";");   
                  */
               }               

            //myXCoordVector.addElement(new Integer(myXIntersection0));
            //myYCoordVector.addElement(new Integer(myYIntersection0));
            //myXCoordVector.addElement(new Integer(myXIntersection1));
            //myYCoordVector.addElement(new Integer(myYIntersection1));
            //myXCoordVector.addElement(new Integer(myXIntersection2));
            //myYCoordVector.addElement(new Integer(myYIntersection2));
            //myXCoordVector.addElement(new Integer(myXIntersection3));
            //myYCoordVector.addElement(new Integer(myYIntersection3));
            //myXYCoordHashtable.put(theXCoordName,myXCoordVector);
            //myXYCoordHashtable.put(theYCoordName,myYCoordVector);
            //theRectCoordVector.addElement(myXYCoordHashtable);
         }
      }
      return theRectCoordVector;
   }
   public boolean checkBoundary(int aXCoord,int aYCoord,int aRho,int aTheta)
   {
     int aRhoIndex = getIndex(rhoParameterSpace,aRho);
     int aThetaIndex = getIndex(thetaParameterSpace,aTheta);
     boolean boundaryCheck = false;
     if(
        (aXCoord >= theXMinValue[aRhoIndex][aThetaIndex] && aXCoord <= theXMaxValue[aRhoIndex][aThetaIndex] ) &&
        (aYCoord >= theYMinValue[aRhoIndex][aThetaIndex] && aYCoord <= theYMaxValue[aRhoIndex][aThetaIndex] )
       )
     {
        boundaryCheck = true;
     }
     return boundaryCheck;
   }
   public String generateSVGBox(int aLength,int aWidth)
   {
      String aVRMLString=
         "Shape {\n"+
            "appearance Appearance {\n"+
               "material Material {diffuseColor 1 0 0}\n"+
            "}\n"+
            "geometry Box {size "+aLength+" "+aWidth+" "+"1.0}\n"+
         "}\n";
      System.out.println(aVRMLString);
      return aVRMLString;
   }
   private Hashtable findPerpendicularLine(Hashtable myRhoThetaHashtable)
   {
      Vector myRhoVector = (Vector) myRhoThetaHashtable.get(theRhoName);
      Vector myThetaVector = (Vector) myRhoThetaHashtable.get(theThetaName);
      Integer myRhoInteger = null;
      Integer myThetaInteger = null;
      int myRhoValue = 0;
      int myThetaValue = 0;

      int myVectorLength = myRhoVector.size();
      //System.out.println("HoughKerman.findPerpendicularLine() - testing ..." );

      for(int myIndex = 0;myIndex < myVectorLength;myIndex++)
      {          
         myRhoInteger = (Integer) myRhoVector.elementAt(myIndex);
         myThetaInteger = (Integer) myThetaVector.elementAt(myIndex);
         myRhoValue = myRhoInteger.intValue();
         myThetaValue = myThetaInteger.intValue();
         //System.out.println("myRhoValue = "+myRhoValue+" myThetaValue = "+myThetaValue);

         findPerpendicularLine(myRhoValue,myThetaValue,myRhoThetaHashtable);
      }
      return theRhoThetaPerpHashtable;
   }
   public Hashtable findPerpendicularLine(int rhoValue,int thetaValue,Hashtable aHashtable)
   {      
      Vector aRhoVector = (Vector) aHashtable.get(theRhoName);
      Vector aThetaVector = (Vector) aHashtable.get(theThetaName);
      Vector myRhoVector = new Vector();
      Vector myThetaVector = new Vector();
      Hashtable myRhoThetaHashtable = new Hashtable();

      int myThetaNinetyDegree = thetaValue+90;
      if( myThetaNinetyDegree > 179)
      {
         myThetaNinetyDegree = myThetaNinetyDegree - 180;
      }
      int myThetaNinetyIndex1 = getIndex(thetaParameterSpace,myThetaNinetyDegree);
      int myThetaNinetyIndex2 = 0;
      int myThetaValue = 0;
      
      Integer myRhoInteger = null;
      Integer myThetaInteger = null;

      String aRhoThetaString = ""+rhoValue+""+thetaValue;
   
      int aVectorLength = aRhoVector.size();

      for(int anIndex = 0;anIndex < aVectorLength;anIndex++)
      {
         myThetaInteger = (Integer) aThetaVector.elementAt(anIndex);
         myThetaValue = myThetaInteger.intValue();
         myThetaNinetyIndex2 = getIndex(thetaParameterSpace,myThetaValue);
         //System.out.println("Perpendicular ThetaValue = "+myThetaValue);         
         if(myThetaNinetyIndex1 == myThetaNinetyIndex2)
         {
            myRhoInteger = (Integer) aRhoVector.elementAt(anIndex);
            int myRhoValue = myRhoInteger.intValue();
            //System.out.println(""HoughKerman.findPerpendicularLine(): "+rhoValue="+rhoValue+" myRhoValue="+myRhoValue+" thetaValue="+thetaValue+" myThetaValue="+myThetaValue );   

               myRhoVector.addElement(new Integer(myRhoValue));
               myThetaVector.addElement(myThetaInteger);  
               //System.out.println("HoughKerman.findPerpendicularLine(): Perpendicular RhoValue = "+myRhoValue+" ThetaValue = "+myThetaValue);
         }
      }
      myRhoThetaHashtable.put(theRhoName,myRhoVector);
      myRhoThetaHashtable.put(theThetaName,myThetaVector);
      theRhoThetaPerpHashtable.put(aRhoThetaString,myRhoThetaHashtable);
      return theRhoThetaPerpHashtable;
   }
   private Hashtable findParallelLine(Hashtable myRhoThetaHashtable)
   {
      Vector myRhoVector = (Vector) myRhoThetaHashtable.get(theRhoName);
      Vector myThetaVector = (Vector) myRhoThetaHashtable.get(theThetaName);
      Integer myRhoInteger = null;
      Integer myThetaInteger = null;
      int myRhoValue = 0;
      int myThetaValue = 0;

      int myVectorLength = myRhoVector.size();

      for(int myIndex = 0;myIndex < myVectorLength;myIndex++)
      {          
         myRhoInteger = (Integer) myRhoVector.elementAt(myIndex);
         myThetaInteger = (Integer) myThetaVector.elementAt(myIndex);
         myRhoValue = myRhoInteger.intValue();
         myThetaValue = myThetaInteger.intValue();
         //System.out.println("myRhoValue = "+myRhoValue+" myThetaValue = "+myThetaValue);
         findParallelLine(myRhoValue,myThetaValue,myRhoThetaHashtable);
      }
      return theRhoThetaParaHashtable;
   }
   public Hashtable findParallelLine(int rhoValue,int thetaValue,Hashtable aHashtable)
   {      
      Vector aRhoVector = (Vector) aHashtable.get(theRhoName);
      Vector aThetaVector = (Vector) aHashtable.get(theThetaName);
      Vector myRhoVector = new Vector();
      Vector myThetaVector = new Vector();
      Hashtable myRhoThetaHashtable = new Hashtable();


      int myThetaSimilar = thetaValue;
      int myThetaSimilarIndex1 = getIndex(thetaParameterSpace,myThetaSimilar);
      int myThetaSimilarIndex2 = 0;
      int myThetaValue = 0;
      

      Integer myRhoInteger = null;
      Integer myThetaInteger = null;

      String aRhoThetaString = ""+rhoValue+""+thetaValue;
   
      int aVectorLength = aRhoVector.size();

      for(int anIndex = 0;anIndex < aVectorLength;anIndex++)
      {
         //myRhoInteger = (Integer) aRhoVector.elementAt(anIndex);
         //myRhoValue = myRhoInteger.intValue();
         //myRhoSimilarIndex2 = getIndex(rhoParameterSpace,myRhoValue);
         //System.out.println("anIndex = "+anIndex);
         myThetaInteger = (Integer) aThetaVector.elementAt(anIndex);
         myThetaValue = myThetaInteger.intValue();
         myThetaSimilarIndex2 = getIndex(thetaParameterSpace,myThetaValue);
         
         if(myThetaSimilarIndex1 == myThetaSimilarIndex2)
         {
            myRhoInteger = (Integer) aRhoVector.elementAt(anIndex);
            int myRhoValue = myRhoInteger.intValue();
            //if(rhoValue != myRhoValue)
            //{
               myRhoVector.addElement(new Integer(myRhoValue));
               myThetaVector.addElement(myThetaInteger);  
               //System.out.println("HoughKerman.findParallelLine() : Parallel RhoValue = "+myRhoValue+" ThetaValue = "+myThetaValue);
            //}
         }
      }
      myRhoThetaHashtable.put(theRhoName,myRhoVector);
      myRhoThetaHashtable.put(theThetaName,myThetaVector);
      theRhoThetaParaHashtable.put(aRhoThetaString,myRhoThetaHashtable);
      return theRhoThetaParaHashtable;
   }
   public Vector buildRectangles(Hashtable aPerpHashtable,Hashtable aParaHashtable)
   {    
      //Vector myRhoVector1 = (Vector) aRhoThetaHashtable.get(theRhoName);
      //Vector myThetaVector1 = (Vector) aRhoThetaHashtable.get(theThetaName);

      Vector myRhoVector1 = new Vector();
      Vector myThetaVector1 = new Vector();
      Vector myRhoVector2 = new Vector();
      Vector myThetaVector2 = new Vector();
      Vector myRhoVector3 = new Vector();
      Vector myThetaVector3 = new Vector();
      
      Vector myRhoRectVector = new Vector();
      Vector myThetaRectVector = new Vector();
      Hashtable myRhoThetaRectHashtable = null;

      Hashtable myRhoThetaHashtable1 = new Hashtable();
      Hashtable myRhoThetaHashtable2 = new Hashtable();
      Hashtable myRhoThetaHashtable3 = new Hashtable();
      Hashtable myRhoThetaHashtable4 = new Hashtable();
      Hashtable myRectangleHashtable = null;

      Integer myRhoInteger1 = null;
      Integer myThetaInteger1 = null;
      Integer myRhoInteger2 = null;
      Integer myThetaInteger2 = null;
      Integer myRhoInteger3 = null;
      Integer myThetaInteger3 = null;
      Integer myRhoInteger4 = null;
      Integer myThetaInteger4 = null;

      int myLength1 = 0;
      int myLength2 = 0;
      int myLength3 = 0;

      //int myVectorLength = myRhoVector1.size();
      String myRhoThetaKey = null;

      Enumeration aParaEnumeration = aParaHashtable.keys();
 
      while(aParaEnumeration.hasMoreElements())
      {
         myRhoThetaKey = (String)aParaEnumeration.nextElement();     
         myRhoThetaHashtable1 = (Hashtable)aParaHashtable.get(myRhoThetaKey);
         myRhoVector1 = (Vector)myRhoThetaHashtable1.get(theRhoName);
         myThetaVector1 = (Vector)myRhoThetaHashtable1.get(theThetaName);       
         //   myLength2 = myRhoVector2.size();
         
         myRhoThetaHashtable3 = (Hashtable)aPerpHashtable.get(myRhoThetaKey);
         myRhoVector3 = (Vector)myRhoThetaHashtable3.get(theRhoName);
         myThetaVector3 = (Vector)myRhoThetaHashtable3.get(theThetaName);         
         myLength3 = myRhoVector3.size();

         myLength1 = myRhoVector1.size();

         for(int anotherIndex=1;anotherIndex<myLength1;anotherIndex++)
         {
            myRhoInteger1 = (Integer) myRhoVector1.elementAt(anotherIndex-1);
            myThetaInteger1 = (Integer) myThetaVector1.elementAt(anotherIndex-1);

            myRhoInteger2 = (Integer) myRhoVector1.elementAt(anotherIndex);
            myThetaInteger2 = (Integer) myThetaVector1.elementAt(anotherIndex);

            for(int oneMoreIndex=1;oneMoreIndex<myLength3;oneMoreIndex++)
            {
               
               
System.out.println("test a ...");
               myRhoInteger3 = (Integer) myRhoVector3.elementAt(oneMoreIndex);
               myThetaInteger3 = (Integer) myThetaVector3.elementAt(oneMoreIndex);
               myRhoInteger4 = (Integer) myRhoVector3.elementAt(oneMoreIndex-1);
               myThetaInteger4 = (Integer) myThetaVector3.elementAt(oneMoreIndex-1);

               myRhoRectVector.add(myRhoInteger1);
               myThetaRectVector.add(myThetaInteger1);
               myRhoRectVector.add(myRhoInteger2);
               myThetaRectVector.add(myThetaInteger2);
               myRhoRectVector.add(myRhoInteger3);
               myThetaRectVector.add(myThetaInteger3);
               myRhoRectVector.add(myRhoInteger4);
               myThetaRectVector.add(myThetaInteger4);

               myRectangleHashtable = new Hashtable();
               myRectangleHashtable.put(theRhoName,myRhoRectVector);
               myRectangleHashtable.put(theThetaName,myThetaRectVector);

               System.out.println(myRhoInteger1.toString()+","+myThetaInteger1.toString()+";"+
                                  myRhoInteger2.toString()+","+myThetaInteger2.toString()+";"+
                                  myRhoInteger3.toString()+","+myThetaInteger3.toString()+";"+
                                  myRhoInteger4.toString()+","+myThetaInteger4.toString());

               theRectangleVector.add(myRectangleHashtable);
            }
         }
      }
      return theRectangleVector;
   }   
   public Vector buildRectXYCoord(Hashtable aRhoThetaHashtable,Hashtable aPerpHashtable,Hashtable aParaHashtable)
   {    
      Vector myRhoVector1 = (Vector) aRhoThetaHashtable.get(theRhoName);
      Vector myThetaVector1 = (Vector) aRhoThetaHashtable.get(theThetaName);
      Vector myRhoVector2 = new Vector();
      Vector myThetaVector2 = new Vector();
      Vector myRhoVector3 = new Vector();
      Vector myThetaVector3 = new Vector();      
      Vector myRhoRectVector = new Vector();
      Vector myThetaRectVector = new Vector();
      Vector myXCoordVector = new Vector();
      Vector myYCoordVector = new Vector();
      Hashtable myRhoThetaRectHashtable = new Hashtable();
      Hashtable myRhoThetaHashtable2 = new Hashtable();
      Hashtable myRhoThetaHashtable3 = new Hashtable();
      Hashtable myRhoThetaHashtable4 = new Hashtable();
      Hashtable myRectangleHashtable = null;
      Hashtable myXYCoordHashtable = new Hashtable();
      Integer myRhoInteger1 = null;
      Integer myThetaInteger1 = null;
      Integer myRhoInteger2 = null;
      Integer myThetaInteger2 = null;
      Integer myRhoInteger3 = null;
      Integer myThetaInteger3 = null;
      Integer myRhoInteger4 = null;
      Integer myThetaInteger4 = null;
      int myLength1 = 0;
      int myLength2 = 0;
      int myLength3 = 0;
      Integer myInteger1 = null;
      Integer myInteger2 = null;
      Integer myInteger3 = null;
      Integer myInteger4 = null;

      Integer myXInteger1 = null;
      Integer myYInteger1 = null;
      Integer myXInteger2 = null;
      Integer myYInteger2 = null;
      Integer myXInteger3 = null;
      Integer myYInteger3 = null;
      Integer myXInteger4 = null;
      Integer myYInteger4 = null;

      int myVectorLength = myRhoVector1.size();
      for(int myIndex = 0;myIndex < myVectorLength;myIndex++)
      {
         myRhoInteger1 = (Integer) myRhoVector1.elementAt(myIndex);
         myThetaInteger1 = (Integer) myThetaVector1.elementAt(myIndex);
         myRhoThetaHashtable2 = (Hashtable)aParaHashtable.get(myRhoInteger1.toString()+myThetaInteger1.toString());
         myRhoVector2 = (Vector)myRhoThetaHashtable2.get(theRhoName);
         myThetaVector2 = (Vector)myRhoThetaHashtable2.get(theThetaName);         
         myLength2 = myRhoVector2.size();         
         myRhoThetaHashtable3 = (Hashtable)aPerpHashtable.get(myRhoInteger1.toString()+myThetaInteger1.toString());
         myRhoVector3 = (Vector)myRhoThetaHashtable3.get(theRhoName);
         myThetaVector3 = (Vector)myRhoThetaHashtable3.get(theThetaName);         
         myLength3 = myRhoVector3.size();
         for(int anotherIndex=0;anotherIndex<myLength2;anotherIndex++)
         {
            myRhoInteger2 = (Integer) myRhoVector2.elementAt(anotherIndex);
            myThetaInteger2 = (Integer) myThetaVector2.elementAt(anotherIndex);
            for(int oneMoreIndex=1;oneMoreIndex<myLength3;oneMoreIndex++)
            {
               myRhoInteger3 = (Integer) myRhoVector3.elementAt(oneMoreIndex);
               myThetaInteger3 = (Integer) myThetaVector3.elementAt(oneMoreIndex);
               myRhoInteger4 = (Integer) myRhoVector3.elementAt(oneMoreIndex-1);
               myThetaInteger4 = (Integer) myThetaVector3.elementAt(oneMoreIndex-1);
               int xCoord1 = getXIntersectionLine(myRhoInteger1,myThetaInteger1,myRhoInteger3,myThetaInteger3);
               int xCoord2 = getXIntersectionLine(myRhoInteger3,myThetaInteger3,myRhoInteger2,myThetaInteger2);
               int xCoord3 = getXIntersectionLine(myRhoInteger2,myThetaInteger2,myRhoInteger4,myThetaInteger4);
               int xCoord4 = getXIntersectionLine(myRhoInteger4,myThetaInteger4,myRhoInteger1,myThetaInteger1);
               int yCoord1 = getYIntersectionLine(myRhoInteger1,myThetaInteger1,myRhoInteger3,myThetaInteger3);
               int yCoord2 = getYIntersectionLine(myRhoInteger3,myThetaInteger3,myRhoInteger2,myThetaInteger2);
               int yCoord3 = getYIntersectionLine(myRhoInteger2,myThetaInteger2,myRhoInteger4,myThetaInteger4);
               int yCoord4 = getYIntersectionLine(myRhoInteger4,myThetaInteger4,myRhoInteger1,myThetaInteger1);

               if(!doesRectangleExist(xCoord1,yCoord1,xCoord2,yCoord2,xCoord3,yCoord3,xCoord4,yCoord4))
               {
                  myXInteger1 = new Integer(xCoord1);
                  myYInteger1 = new Integer(yCoord1);
                  myXInteger2 = new Integer(xCoord2);
                  myYInteger2 = new Integer(yCoord2);
                  myXInteger3 = new Integer(xCoord3);
                  myYInteger3 = new Integer(yCoord3);
                  myXInteger4 = new Integer(xCoord4);
                  myYInteger4 = new Integer(yCoord4);

                  myXCoordVector.addElement(myXInteger1);
                  myXCoordVector.addElement(myXInteger2);
                  myXCoordVector.addElement(myXInteger3);
                  myXCoordVector.addElement(myXInteger4);
                  myYCoordVector.addElement(myYInteger1);
                  myYCoordVector.addElement(myYInteger2);
                  myYCoordVector.addElement(myYInteger3);
                  myYCoordVector.addElement(myYInteger4);

                  myXYCoordHashtable.put(theXCoordName,myXCoordVector);
                  myXYCoordHashtable.put(theYCoordName,myYCoordVector);  

                  theRectCoordVector.addElement(myXYCoordHashtable);
                  /*
                  System.out.println(""+xCoord1+","+yCoord1+";"+
                                  ""+xCoord2+","+yCoord2+";"+
                                  ""+xCoord3+","+yCoord3+";"+
                                  ""+xCoord4+","+yCoord4+";");
                  */   
               }               
            }               
         }
      }
      return theRectXYVector;
   }
   public boolean doesRectangleExist(int aXCoord1,int aYCoord1,int aXCoord2,int aYCoord2,
                                    int aXCoord3,int aYCoord3,int aXCoord4,int aYCoord4)
   {
      return doesRectangleExist(new Integer(aXCoord1),new Integer(aYCoord1),new Integer(aXCoord2),new Integer(aYCoord2),
                                    new Integer(aXCoord3),new Integer(aYCoord3),new Integer(aXCoord4),new Integer(aYCoord4));
   }
   public boolean doesRectangleExist(Integer aXCoord1,Integer aYCoord1,Integer aXCoord2,Integer aYCoord2,
                                    Integer aXCoord3,Integer aYCoord3,Integer aXCoord4,Integer aYCoord4)
   {
      int myVectorSize1 = theRectCoordVector.size();
      Hashtable myHashtable = null; 
      int myVectorSize2 = 0;
      Vector myXVector = null;
      Vector myYVector = null;

      //System.out.println("Does rectangle exist, vector size = "+myVectorSize1);
      
      for(int anIndex = 0;anIndex<myVectorSize1;anIndex++)
      {
         myHashtable = (Hashtable) theRectCoordVector.elementAt(anIndex);
         myXVector = (Vector)myHashtable.get(theXCoordName);
         myYVector = (Vector)myHashtable.get(theYCoordName);

         int x = ((Integer)myXVector.elementAt(0)).intValue();
         int y = ((Integer)myYVector.elementAt(0)).intValue();
         //System.out.println("x="+x+",y="+y);
         x = ((Integer)myXVector.elementAt(1)).intValue();
         y = ((Integer)myYVector.elementAt(1)).intValue();
         //System.out.println("x="+x+",y="+y);
         x = ((Integer)myXVector.elementAt(2)).intValue();
         y = ((Integer)myYVector.elementAt(2)).intValue();
         //System.out.println("x="+x+",y="+y);
         x = ((Integer)myXVector.elementAt(3)).intValue();
         y = ((Integer)myYVector.elementAt(3)).intValue();
         //System.out.println("x="+x+",y="+y);
         /*
         System.out.println("myXVector.contains(aXCoord1)="+myXVector.contains(aXCoord1));
         System.out.println("myXVector.contains(aXCoord2)="+myXVector.contains(aXCoord2));
         System.out.println("myXVector.contains(aXCoord3)="+myXVector.contains(aXCoord3));
         System.out.println("myXVector.contains(aXCoord4)="+myXVector.contains(aXCoord4));
         System.out.println("myYVector.contains(aYCoord1)="+myYVector.contains(aYCoord1));
         System.out.println("myYVector.contains(aYCoord2)="+myYVector.contains(aYCoord2));
         System.out.println("myYVector.contains(aYCoord3)="+myYVector.contains(aYCoord3));
         System.out.println("myYVector.contains(aYCoord4)="+myYVector.contains(aYCoord4));
         */
         if(myXVector.contains(aXCoord1) && myXVector.contains(aXCoord2) && 
            myXVector.contains(aXCoord3) && myXVector.contains(aXCoord4) &&
            myYVector.contains(aYCoord1) && myYVector.contains(aYCoord2) &&
            myYVector.contains(aYCoord3) && myYVector.contains(aYCoord4) )
         {
            //System.out.println("doesRectrangeExist = true");
            return true;
         }
      }
      return false;
   }
   public Hashtable getAssociateLine(int rhoValue,int thetaValue,Hashtable aHashtable,int aCount)
   {
      Hashtable myHashtable = (Hashtable) aHashtable.get(""+rhoValue+""+thetaValue);
      Hashtable myRhoThetaHashtable = new Hashtable();
      Integer myRhoInteger = null;
      Integer myThetaInteger = null;
      Vector myRhoVector = new Vector();
      Vector myThetaVector = new Vector();
      Vector aRhoVector = (Vector)myHashtable.get(theRhoName);
      Vector aThetaVector = (Vector)myHashtable.get(theThetaName);

      int aVectorLength = aRhoVector.size(); 
      int numberFound = 1;
   
      for(int anIndex = 0;anIndex < aVectorLength;anIndex++)
      {
         myThetaInteger = (Integer) aThetaVector.elementAt(anIndex);
         myRhoInteger = (Integer) aRhoVector.elementAt(anIndex);
         if( numberFound == aCount)
         {
            myRhoVector.addElement(myRhoInteger);
            myThetaVector.addElement(myThetaInteger);
            myRhoThetaHashtable.put(theRhoName,myRhoVector);
            myRhoThetaHashtable.put(theThetaName,myThetaVector);
            //System.out.println("Associated Line RhoValue = "+myRhoInteger.intValue()+" ThetaValue = "+myThetaInteger.intValue());
            return myRhoThetaHashtable;
         }
         numberFound++;
      }      
      return null;      
   }
   public int getYIntersectionLine(Integer aRhoInteger1,Integer aThetaInteger1,Integer aRhoInteger2,Integer aThetaInteger2)
   {
      return getYIntersectionLine(aRhoInteger1.intValue(),aThetaInteger1.intValue(),aRhoInteger2.intValue(),aThetaInteger2.intValue());
   }
   public int getXIntersectionLine(Integer aRhoInteger1,Integer aThetaInteger1,Integer aRhoInteger2,Integer aThetaInteger2)
   {
      return getXIntersectionLine(aRhoInteger1.intValue(),aThetaInteger1.intValue(),aRhoInteger2.intValue(),aThetaInteger2.intValue());
   }
   public int getYIntersectionLine(int aRhoValue1,int aThetaValue1,int aRhoValue2,int aThetaValue2)
   {
      double thetaRadian1 = Math.toRadians(aThetaValue1);
      double thetaRadian2 = Math.toRadians(aThetaValue2);

      double yCoord =   (aRhoValue1*Math.cos(thetaRadian2)- aRhoValue2*Math.cos(thetaRadian1)) / 
                        Math.sin(thetaRadian1-thetaRadian2);
      int yCoordinate = (int)Math.floor(yCoord + .5);
      return yCoordinate;
   }
   public int getXIntersectionLine(int aRhoValue1,int aThetaValue1,int aRhoValue2,int aThetaValue2)
   {
      double thetaRadian1 = Math.toRadians(aThetaValue1);
      double thetaRadian2 = Math.toRadians(aThetaValue2);

      double xCoord =      (aRhoValue1*Math.sin(thetaRadian2)- aRhoValue2*Math.sin(thetaRadian1)) / 
                        Math.sin(thetaRadian2-thetaRadian1);
      int xCoordinate = (int) Math.floor(xCoord + .5);
      return xCoordinate;
   }
   public int getIndex(int myArray[],int aValue)
   {
      int aLength = myArray.length;

      for(int i=0;i<aLength;i++)
      {
         if(myArray[i] >= aValue)
         {
            return i;
         }         
      }
      return -1;
   }
   public int[] descretize(int startNumber,int finishNumber,int aDelta)
   {

      int aNumber = finishNumber - startNumber;
      int numberOfElements = (aNumber / aDelta) + 1;
      int aParameterSpace[] = new int[numberOfElements];

      //int aLength = aNumber.length;
      int aValue = 0;

      for(int anIndex = 0;anIndex < numberOfElements;anIndex++)
      {
         aValue = anIndex * aDelta;
         aParameterSpace[anIndex] = aValue; 
         //System.out.println("aValue = "+aValue);
      }
      return aParameterSpace;
   }   
   public Image performFiltering(){return null;}

}