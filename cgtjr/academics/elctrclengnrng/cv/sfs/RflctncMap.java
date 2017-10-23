package cgtjr.academics.elctrclengnrng.cv.sfs;


public class RflctncMap
{
   private float rMapIlluminant[];
   private int imageData[];
   private int zMapData[];
   private int rMapTmp1[];
   private int rMapTmp2[];
   private double rMapData[];
   private float dzdx1[];
   private float dzdy1[];
   private float dzdx2[];
   private float dzdy2[];

   private int dzdxAvg[];
   private int dzdyAvg[];
   private double drdp1;
   private double drdp2;
   private double drdp3; 
   private double drdp[];
   private double drdq1;
   private double drdq2;
   private double drdq3; 
   private double drdq[];
   private int imageHeight;
   private int imageWidth;
   private double imgRMapDiff;
   private double rMap[];
   private float lambda;
   private int nmbrFxBndrs;
   private boolean fixedBndrs;
   private int itrtnNmbr;

   public RflctncMap(float myRMapIlluminant[])
   {
      rMapIlluminant = myRMapIlluminant;
   }
   public RflctncMap(float myRMapIlluminant[],int myImageData[],int myImageWidth,int myImageHeight)
   {
      rMapIlluminant = myRMapIlluminant;
      imageData = myImageData;
      imageWidth = myImageWidth;
      imageHeight = myImageHeight;
      dzdx1 = new float[myImageData.length];
      dzdy1 = new float[myImageData.length];
      dzdx2 = new float[myImageData.length];
      dzdy2 = new float[myImageData.length];

      //System.out.println("RflctncMap height = "+imageHeight+", widht = "+imageWidth+", length = "+myImageData.length);
   }
   public RflctncMap(float myRMapIlluminant[],int myrMapTmp1[],int myImageData[],int myImageWidth,int myImageHeight)
   {
      rMapIlluminant = myRMapIlluminant;
      imageData = myImageData;
      rMapTmp1 = myrMapTmp1;
      imageWidth = myImageWidth;
      imageHeight = myImageHeight;
      dzdx1 = new float[myImageData.length];
      dzdy1 = new float[myImageData.length];
      dzdx2 = new float[myImageData.length];
      dzdy2 = new float[myImageData.length];
      //System.out.println("RflctncMap height = "+imageHeight+", widht = "+imageWidth+", length = "+myImageData.length);
   }
   public RflctncMap(int myImageData[],int myImageWidth,int myImageHeight)
   {
      imageData = myImageData;
      imageWidth = myImageWidth;
      imageHeight = myImageHeight;
      dzdx1 = new float[myImageData.length];
      dzdy1 = new float[myImageData.length];
      dzdx2 = new float[myImageData.length];
      dzdy2 = new float[myImageData.length];
      zMapData = new int[myImageData.length];
      rMapData = crtDfltRMap(myImageData);
      imageData = myImageData;
      //System.out.println("RflctncMap height = "+imageHeight+", widht = "+imageWidth+", length = "+myImageData.length);
   }
   public double[] crtDfltRMap(int myImageData[])
   {
      int aLength = myImageData.length;
      double aRMapData[] = new double[aLength];
      //System.out.println("RflctnceMap: aLength = "+aLength);
      for(int i=0;i<aLength;i++)
      {
         aRMapData[i]=myImageData[i];
      }
      return aRMapData;
   }
   public void compute()
   {
      for(int i=0;i<imageHeight;i++)
      {
         for(int j=0;j<imageWidth;j++)
         {
            //System.out.println("RflctncMap : imageData.length = "+imageData.length+", E("+i*imageWidth+","+j+") = "+imageData[i*imageWidth+j]);
            if(imageData[i*imageWidth+j] != 0)
            {
               computeZxZy(dzdx1,dzdy1,imageData,rMapIlluminant,i,j,imageWidth);
            }else{
            //System.out.println("RflctncMap : E("+i*imageWidth+","+j+") = "+imageData[i*imageWidth+j]);
            }
         }
      }
   }
   public void computeZxZy(float myDzdx[],float myDzdy[],int myImageData[],float myRMapIlluminant[],int i,int j,int w)
   {
      computeZx(myDzdx,myDzdy,myImageData,myRMapIlluminant,i,j,w);
      computeZy(myDzdx,myDzdy,myImageData,myRMapIlluminant,i,j,w);
   }
   public float computeZx(float myDzdx[],float myDzdy[],int myImageData[],float myRMapIlluminant[],int i,int j,int w)
   {
      float dzdxAvg = (float)computeAvgZx(myDzdx,i,j,w);
      float aRMapValue = (float)cmptRMap(myDzdx,myDzdy,myRMapIlluminant,i,j,w);
      float imgRMapDiff = cmptBrghtMapDiff(myImageData,aRMapValue,i,j,w);
      float aDrdp = cmptAnltclDRDP(myDzdx,myDzdy,myRMapIlluminant,i,j,w);
      float aZx = dzdxAvg+imgRMapDiff*aDrdp;
      dzdx2[i*w+j] = aZx;
      return aZx;
   }
   public float computeZy(float myDzdx[],float myDzdy[],int myImageData[],float myRMapIlluminant[],int i,int j,int w)
   {
      float dzdyAvg = (float)computeAvgZy(myDzdy,i,j,w);
      float aRMapValue = (float)cmptRMap(myDzdx,myDzdy,myRMapIlluminant,i,j,w);
      float imgRMapDiff = cmptBrghtMapDiff(myImageData,aRMapValue,i,j,w);
      float aDrdq = cmptAnltclDRDQ(myDzdx,myDzdy,myRMapIlluminant,i,j,w);
      float aZy = dzdyAvg+imgRMapDiff*aDrdq;
      dzdy2[i*w+j] = aZy;
      return aZy;     
   }
   public float cmptAnltclDRDP(float myDzdx[],float myDzdy[],float myRMapIlluminant[],int i,int j,int w)
   {
      float drdp = 0;                
      double drdp1 = myRMapIlluminant[0]*(-1*Math.pow(1+myDzdx[i*w+j]*myDzdx[i*w+j]+myDzdy[i*w+j]*myDzdy[i*w+j],-1/2)+
                myDzdx[i*w+j]*myDzdx[i*w+j]*Math.pow(1+myDzdx[i*w+j]*myDzdx[i*w+j]+myDzdy[i*w+j]*myDzdy[i*w+j],-3/2));
      double drdp2 = myRMapIlluminant[1]*myDzdy[i*w+j]*myDzdx[i*w+j]*Math.pow(1+myDzdx[i*w+j]*myDzdx[i*w+j]+myDzdy[i*w+j]*myDzdy[i*w+j],-3/2);
      double drdp3 = myRMapIlluminant[2]*(-1)*myDzdx[i*w+j]*Math.pow(1+myDzdx[i*w+j]*myDzdx[i*w+j]+myDzdy[i*w+j]*myDzdy[i*w+j],-3/2);
      drdp = (float)(drdp1+drdp2+drdp3);
      return drdp;
   }
   public double cmptNmrclDRDP(int myX,int myY)
   {
      if(isInBounds1x3(myX,myY) == false)
      {
         return 0;
      }
      int anIndex = getIndex(myX,myY);
      int index1 = getIndex(myX-1,myY);
      int index2 = getIndex(myX+1,myY);

      double aValue = (rMapData[index2] - rMapData[index1])/2;
      //System.out.println("RflctncMap: rMapData["+index2+"] = "+rMapData[index2]+", rMapData["+index1+"] = "+rMapData[index1]+", value = "+aValue);
      return aValue;
   }
   public double cmptNmrclDRDQ(int myX,int myY)
   {
      if(isInBounds3x1(myX,myY) == false)
      {
         return 0;
      }
      int anIndex = getIndex(myX,myY);
      int index1 = getIndex(myX,myY-1);
      int index2 = getIndex(myX,myY+1);

      double aValue = (rMapData[index2] - rMapData[index1])/2;
      //System.out.println("RflctncMap: rMapData["+index2+"] = "+rMapData[index2]+", rMapData["+index1+"] = "+rMapData[index1]+", value = "+aValue);
      return aValue;
   }
   public int cmptNmrclDZDX(int myX,int myY)
   {
      if(isInBounds3x1(myX,myY) == false || isInBounds1x3(myX,myY) == false)
      {
         return 0;
      }
      int anIndex = getIndex(myX,myY);
      int index1 = getIndex(myX-1,myY);
      int index2 = getIndex(myX+1,myY);

      int aValue = (zMapData[index2] - zMapData[index1])/2;
      return aValue;
   }
   public void updtZMapData(int myValue,int myIndex)
   {
      zMapData[myIndex] = myValue;
   }
   public int getZMapData(int myX,int myY)
   {
      int anIndex = getIndex(myX,myY);
      return getZMapData(anIndex);
   }
   public int getZMapData(int myIndex)
   {
      return zMapData[myIndex];
   }
   public void setZMapData(int myValue,int myX,int myY)
   {
      int anIndex = getIndex(myX,myY);
      setZMapData(myValue,anIndex);
   }
   public void setZMapData(int myValue,int myIndex)
   {
      zMapData[myIndex] = myValue;    
   }
   public int[] getZMapData()
   {
      return zMapData;
   }
   public int cmptNmrclDZDY(int myX,int myY)
   {
      if(isInBounds3x1(myX,myY) == false || isInBounds1x3(myX,myY) == false)
      {
         return 0;
      }
      int anIndex = getIndex(myX,myY);
      int index1 = getIndex(myX,myY-1);
      int index2 = getIndex(myX,myY+1);

      int aValue = (zMapData[index2] - zMapData[index1])/2;
      return aValue;
   }
   public double getRMapData(int myX,int myY)
   {

      int anIndex = getIndex(myX,myY);
      return getRMapData(anIndex);
   }
   public double getRMapData(int myIndex)
   {
      return rMapData[myIndex];
   }
   public void updateRMap(double myValue,int myX,int myY)
   {
      int anIndex = getIndex(myX,myY);
      if(isInBounds1x3(myX,myY)==false)
      {
         return;
      }
      updateRMap(myValue,anIndex);
   }
   /*
   public void updateRMap(int myIndex,int myValue)
   {
      if(itrtnNmbr % 2 == 0)
      {
         rMapTmp1[myIndex] = myValue;
      }else{
         rMapTmp2[myIndex] = myValue;
      }
   }*/
   public void updateRMap(double myValue,int myIndex)
   {
      if(myValue < 0)
      {
         myValue = 0;
      }
      rMapData[myIndex] = myValue;
   }
   public float cmptAnltclDRDQ(float myDzdx[],float myDzdy[],float myRMapIlluminant[],int i,int j,int w)
   {
      float drdq = 0;
      double drdq1 = myRMapIlluminant[1]*myDzdy[i*w+j]*myDzdx[i*w+j]*Math.pow(1+myDzdx[i*w+j]*myDzdx[i*w+j]+myDzdy[i*w+j]*myDzdy[i*w+j],-3/2);
      double drdq2 = myRMapIlluminant[0]*(-1*Math.pow(1+myDzdx[i*w+j]*myDzdx[i*w+j]+myDzdy[i*w+j]*myDzdy[i*w+j],-1/2)+
                myDzdy[i*w+j]*myDzdy[i*w+j]*Math.pow(1+myDzdx[i*w+j]*myDzdx[i*w+j]+myDzdy[i*w+j]*myDzdy[i*w+j],-3/2));
      double drdq3 = myRMapIlluminant[2]*(-1)*myDzdy[i*w+j]*Math.pow(1+myDzdx[i*w+j]*myDzdx[i*w+j]+myDzdy[i*w+j]*myDzdy[i*w+j],-3/2);
      drdq = (float)(drdq1+drdq2+drdq3);
      return drdq;
   }
   public float computeAvgZx(float myDzdx[],int i,int j,int w)
   {        
      //System.out.println("RflctncMap : i+1 = "+(i+1)+", i-1 = "+(i-1)+"i+w = "+(i+w)+", i-w = "+(i-w));
      float hrzntlData = 0;
      if(i-1 >= 0 && i+1 < myDzdx.length && i-w >=0 && i+w < myDzdx.length)
      {      
         hrzntlData = (myDzdx[i+1]+myDzdx[i-1]+myDzdx[i-w]+myDzdx[i+w])/4;
      }
      return hrzntlData;
   }
   public float computeAvgZy(float myDzdy[],int i,int j,int w)
   {  
      float vrtclData = 0;
      if(i-1 >= 0 && i+1 < myDzdy.length && i-w >=0 && i+w < myDzdy.length)
      {
         vrtclData = (myDzdy[i+1]+myDzdy[i-1]+myDzdy[i-w]+myDzdy[i+w])/4;
      }
      return vrtclData;
   }
   public float cmptBrghtMapDiff(int myBrghtData[],float myRMapValue,int myIIndex,int myJIndex,int myWidth)
   {
      float anAnswer = (float)((myBrghtData[myIIndex*myWidth+myJIndex]-myRMapValue)/(4*lambda));
      return anAnswer;
   }
   public double cmptRMap(float myDzdx[],float myDzdy[],float myRMapIlluminant[],int i,int j,int w)
   {
      double rMapValue = (-myRMapIlluminant[0]*myDzdx[i*w+j]-myRMapIlluminant[1]*myDzdy[i*w+j]+myRMapIlluminant[2]) /
             Math.sqrt(1+myDzdx[i*w+j]*myDzdx[i*w+j]+myDzdy[i*w+j]*myDzdy[i*w+j]);
      if (rMapValue < 0)
      {
         rMapValue = 0;
      }
      rMapData[i*w+j] = rMapValue;
      return rMapValue;
   }
   public double cmptRMap(float myDzdx,float myDzdy,float myRMapIlluminant[],int i,int j,int w)
   {
      double rMapValue = (-myRMapIlluminant[0]*myDzdx-myRMapIlluminant[1]*myDzdy+myRMapIlluminant[2]) /
             Math.sqrt(1+myDzdx*myDzdx+myDzdy*myDzdy);
      if (rMapValue < 0)
      {
         rMapValue = 0;
      }
      rMapData[i*w+j] = rMapValue;
      return rMapValue;
   }
   public int getXPstn(int myIndex)
   {
      int x = myIndex % getImageWidth();
      return x;
   }
   public int getYPstn(int myIndex)
   {
      int y = myIndex / getImageWidth();
      return y;
   }
   public int getIndex(int myX,int myY)
   {
      int aWidth = getImageWidth();
      int anIndex = myY*aWidth+myX;
      return anIndex;
   }
   public boolean isInBounds1x3(int aX,int aY)
   {
      boolean inBounds = false;
      if( aY >= 0 && aY < getImageHeight()
      &&  aX >= 0 && aX < getImageWidth()
      && (aX-1 >= 0 && aX-1 < getImageWidth())
      && (aX+1 >= 0 && aX+1 < getImageWidth() ))
      {
         inBounds = true;
      }
      return inBounds;
   }
   public boolean isInBounds3x1(int aX,int aY)
   {
      boolean inBounds = false;
      if( aX >= 0 && aX < getImageWidth() 
      &&  aY >= 0 && aY < getImageHeight()
      && (aY-1 >= 0 && aY-1 < getImageHeight())
      && (aY+1 >= 0 && aY+1 < getImageHeight() ))
      {
         inBounds = true;
      }
      return inBounds;
   }
   public int getImageWidth()
   {
      return imageWidth;
   }
   public int getImageHeight()
   {
      return imageHeight;
   }
}