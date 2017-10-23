package cgtjr.academics.elctrclengnrng.cv.houghtransform;


import java.util.*;
//import java.awt.*;
import java.awt.image.*;

/*
public class HoughTransform extends ImageFilter
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

   public static final String type = "HoughTransform";

   private UgotImage theUgotImage;

   private String x3dString = "";
   
 
   public HoughTransform()
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
   public HoughTransform(Image myImage)
   {
      //setName(type);
      setInputPixels(myImage);
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
   public HoughTransform(int inputPixels[], int myWidth,int myHeight)
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
   public void detectRectangles()
   {
      int inputPixels[] = getInputPixels();
      int aWidth = getWidth();
      int aHeight = getHeight();
      rectangleDetection(inputPixels,aWidth,aHeight);
   }
   public void rectangleDetection(int aTwoDImage[],int aWidth,int aHeight)
   {
      //System.out.println("HoughTransform.rectangleDetection() : width="+aWidth+", height="+aHeight);
      int anAccummulator[][] = processHoughTransform(aTwoDImage,aWidth,aHeight);
      Hashtable aRhoThetaHashtable = findLines(anAccummulator,theThreshHold);
      //Hashtable aRhoThetaPerpHashtable = findPerpendicularLine(aRhoThetaHashtable);
      //Hashtable aRhoThetaParaHashtable = findParallelLine(aRhoThetaHashtable);
      //Vector myRectangleVector = buildRectangles(theRhoThetaHashtable,theRhoThetaPerpHashtable,aRhoThetaParaHashtable);
      //Vector myRectangleVector = buildRectangles(theRhoThetaPerpHashtable,aRhoThetaParaHashtable);
      //Vector myRectCoordVector = buildRectCoord(myRectangleVector);
      //generateSVGBox(myRectCoordVector);
      //generateSVGBox(myRectangleVector);
      //Vector myXYCoordRectVector = buildRectXYCoord(theRhoThetaHashtable,theRhoThetaPerpHashtable,aRhoThetaParaHashtable);
      //Vector anXYCoordVector = buildRectangleXYCoord(aRectangleHashtable);
      //generateSVGBox(anXYCoordVector);
   }      
   public int[][] processHoughTransform(int aTwoDImage[],int aWidth,int aHeight)
   {
      Vector myVector = new Vector();
      Hashtable myHashtable = null;
      TwoDConvertFilter aTwoDConvertFilter = new TwoDConvertFilter();
      int myTwoDImage[][] = aTwoDConvertFilter.getTwoDImage(aTwoDImage,aWidth,aHeight);

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

      calculateRhoTheta(myTwoDImage,aWidth,aHeight);    
      //System.out.println("HoughTransform : finish calculateRhoTheta");
      return theAccumulator;
   }

   public Hashtable generateVRMLLine(int anAccumulator[][],int threshHold)
   { 
      double yMinValue = 0;
      double xMinValue = 0;
      double yMaxValue = 0;
      double xMaxValue = 0;
      double xMidPoint = 0;
      double yMidPoint = 0;
      double anAngleDeg = 0;
      double anAngleRad = 0;
      double aLength = 0;

      Point aPoint = new Point(); 
      int aWidth = anAccumulator.length;
      int aHeight = anAccumulator[0].length;
      int localMaximum[][] = new int[aWidth][aHeight];
      int perpendicularThetaIndex= 0;
      int rhoValue = 0;
      int thetaValue = 0;
      String aVRMLString = "";
    
      //System.out.println("HoughTransform.generateVRMLLine():anAccumulator.width="+aWidth+", anAccumulator.height="+aHeight); 
      for(int kIndex=0;kIndex<aWidth;kIndex++)
      {
         for(int hIndex=0;hIndex<aHeight;hIndex++)
         {
            //System.out.println("HoughTransform.generateVRMLLine(): rho("+kIndex+")="+rhoParameterSpace[kIndex]+","+"theta("+hIndex+")="+thetaParameterSpace[hIndex]+","+"accumulator="+theAccumulator[kIndex][hIndex]+", threshold="+threshHold);
           
            if(anAccumulator[kIndex][hIndex] >= threshHold)
            {
               rhoValue = rhoParameterSpace[kIndex];
               thetaValue = thetaParameterSpace[hIndex];

               yMinValue = theYMinValue[kIndex][hIndex];
               xMinValue = theXMinValue[kIndex][hIndex];
               yMaxValue = theYMaxValue[kIndex][hIndex];
               xMaxValue = theXMaxValue[kIndex][hIndex];
               xMidPoint = ((double)(xMaxValue - xMinValue)) / 2;
               yMidPoint = ((double)(yMaxValue - yMinValue)) / 2;
               anAngleDeg = -1*(90-rhoValue);
               anAngleRad = Math.toRadians(anAngleDeg);
               aLength = aPoint.getDistance(xMinValue,yMinValue,0,xMaxValue,yMaxValue,0);

               //generateVRMLLine(aLength,.1,xMidPoint,yMidPoint,0,anAngleRad);
            }
         }
      }
      return theRhoThetaHashtable;
   }
   public String generateVRMLLine(int myLength,double myWidth,double myXTrans,double myYTrans,double myZTrans,double myAngle)
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
	"  	 Transform {\n"+
	"		//translation "+xMidPoint+" "+yMidPoint+" 0\n"+
	"		rotation 0 0 1 1.5708\n"+
	"		scale 1 1 1\n"+
	"		children [\n"+
	"			DEF aCylinderLineAB Shape {\n"+
	"				appearance Appearance {\n"+
	"					material Material {\n"+
	"						specularColor 1 1 1\n"+
	"						diffuseColor 1 0 0\n"+
	"						ambientIntensity 0\n"+
	"						transparency 0\n"+
	"						shininess 0.2\n"+
	"					}\n"+
	"				}\n"+
	"				geometry Cylinder { radius 0.3 height 12 }\n"+
	"			}\n"+
	"		]\n"+
	"	  }\n"+
      "      ]\n"+
      "    },\n"+
      "  ]\n"+
      "}\n";
      System.out.println(vrmlString);
      return vrmlString;
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
            //System.out.println("HoughTransform.calculateRhoTheta(): rgbValues = "+rgbValues);
            if(rgbValues >= 1)
            {  
               for(int hIndex = 0;hIndex <  thetaParameterSpace.length;hIndex++)
               {
                  aRhoParameter = (int) Math.abs(( iIndex*Math.cos(Math.toRadians(thetaParameterSpace[hIndex])) + 
                  jIndex*Math.sin(Math.toRadians(thetaParameterSpace[hIndex]))));
                  kIndex = getIndex(rhoParameterSpace,aRhoParameter);     
                  theAccumulator[kIndex][hIndex]++;
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
      //System.out.println("HoughTransform : findLocalMaximum");
      int aWidth = anAccumulator.length;
      int aHeight = anAccumulator[0].length;
      int localMaximum[][] = new int[aWidth][aHeight];
      int perpendicularThetaIndex= 0;
      int rhoValue = 0;
      int thetaValue = 0;
      //System.out.println("HoughTransform.findLines():anAccumulator.width="+aWidth+", anAccumulator.height="+aHeight); 
      for(int kIndex=0;kIndex<aWidth;kIndex++)
      {
         for(int hIndex=0;hIndex<aHeight;hIndex++)
         {
            //System.out.println("rho("+kIndex+")="+rhoParameterSpace[kIndex]+","+"theta("+hIndex+")="+thetaParameterSpace[hIndex]+","+"accumulator="+theAccumulator[kIndex][hIndex]+", threshold="+threshHold);
           
            if(anAccumulator[kIndex][hIndex] >= threshHold)
            {
               rhoValue = rhoParameterSpace[kIndex];
               thetaValue = thetaParameterSpace[hIndex];

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

         //System.out.println("HoughTransform - sortIntoVector : rhoValue = "
         //                         +rhoValue+"thetaValue = "+thetaValue);
         return;
      }            
      for(int anIndex = 0;anIndex < aVectorLength;anIndex++)
      {
            Integer myRhoValue = (Integer)theRhoVector.elementAt(anIndex);      
            if(rhoValue < myRhoValue.intValue())//double check this statement !!!
            {
               theRhoVector.insertElementAt(new Integer(rhoValue),anIndex);
               theThetaVector.insertElementAt(new Integer(thetaValue),anIndex);
               theRhoThetaHashtable.put(theRhoName,theRhoVector);
               theRhoThetaHashtable.put(theThetaName,theThetaVector);
               //System.out.println("HoughTransform - sortIntoVector : rhoValue = "
               //                   +rhoValue+"thetaValue = "+thetaValue);
               return;
            }
      }         
      theRhoVector.addElement(new Integer(rhoValue));
      theThetaVector.addElement(new Integer(thetaValue));
      theRhoThetaHashtable.put(theRhoName,theRhoVector);
      theRhoThetaHashtable.put(theThetaName,theThetaVector);               
      //System.out.println("HoughTransform - sortIntoVector : rhoValue = "
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
   public void generateSVGBox(Vector aVector)
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
      
         
         //generateSVGBox(myXInteger0.intValue(),myYInteger0.intValue(),
         //               myXInteger1.intValue(),myYInteger1.intValue(),
         //               myXInteger2.intValue(),myYInteger2.intValue(),
         //               myXInteger3.intValue(),myYInteger3.intValue());
         

      }
      //writeDataToFile("data/vrml/square.wrl",vrmlData);

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
   public String generateVRMLBox(int aLength,int aWidth)
   {
      //System.out.println("myCoordinates = "+myCoordinates);
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
"           translation 1 .5 .5\n"+
"        children\n"+
        
"        Shape {\n"+
"          appearance Appearance {\n"+
"            material Material {\n"+
"              diffuseColor 1.0 1.0 0.0\n"+
"            }\n"+
"          }\n"+
"          geometry Box {\n"+
"            size ."+aLength+" "+ " ."+aWidth+" ."+aWidth+"\n"+
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
   
}*/