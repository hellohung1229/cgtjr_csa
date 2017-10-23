/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.math.trgnmtry.TrigFnctn;

/**
 *
 * @author clayton g thomas jr
 */
public class CrdntType
{
   private static float x1,y1,z1;
   private final static String crtsnCrdntTp = "cartessian";
   private final static String clndrclCrdntTp = "cylindrical";
   private final static String oblateSphrdlCrdntTp = "oblatesphrdl";   
   private final static String knotCrdntTp = "knot";   
   private final static String sphrclCrdntTp = "spherical";
   private final static String trdlCrdntTp = "toroidal";
   private final static String hlxCrdntTp = "helix";
   private final static String confocalEllpsdlCrdntTp = "confocalellpsdl";   
   private final static String conicalCrdntTp = "conical";
   private final static String ellptcClndrclCrdntTp = "ellptcclndrcl";   
   private final static String parabolicCrdntTp = "parabolic";   
   private final static String biSphericalCrdntTp = "bispherical";   
   
   private static float pitch = 5.4f;
   private static float spiralXOffSet = 2;
   private static float spiralYOffSet = 2;   
   private static float A = 8.0f;
   private static float B = 4.0f;
   private static float C = 2.0f;
   private static float scale = 1.0f;
   
   public static void setPitch(float myPitch)
   {
      pitch = myPitch;
   }
   public static float getPitch()
   {
      return pitch;
   }
   public static final String getCrtsnCrdntTp()
   {
      return crtsnCrdntTp;
   }
   public static final String getKnotCrdntTp()
   {
      return knotCrdntTp;
   }
   public static final String getClndrclCrdntTp()
   {
      return clndrclCrdntTp;
   }
   public static final String getOblateSphrdlCrdntTp()
   {
      return oblateSphrdlCrdntTp;
   }   
   public static final String getSphrclCrdntTp()
   {
      return sphrclCrdntTp;
   }
   public static final String getTrdlCrdntTp()
   {
      return trdlCrdntTp;
   }
   public static final String getHlxCrdntTp()
   {
      return hlxCrdntTp;
   }
   public static final String getConfocalEllpsdlCrdntTp()
   {
      return confocalEllpsdlCrdntTp;
   }   
   public static final String getConicalCrdntTp()
   {
      return conicalCrdntTp;
   }      
   public static final String getEllptcClndrclCrdntTp()
   {
      return ellptcClndrclCrdntTp;
   }         
   public static final String getParabolicCrdntTp()
   {
      return parabolicCrdntTp;
   }         

   public static String getBiSphericalCrdntTp() {
       return biSphericalCrdntTp;
   }

   public static float sphericalC1(float myX,float myY,float myZ,float myX0,float myXE)
   {
      float ex = myXE;
      float aRadius = myX;
      float aTheta = myY;
      float aPhi = myZ;
      float aX0 = myX0;
      float x = aRadius*(float)(Math.sin(aTheta)*Math.cos(aPhi));
      return x + aX0 + ex;
   }
   public static float sphericalC2(float myX,float myY,float myZ,float myY0,float myYE)
   {
      float ey = myYE;
      float aRadius = myX;
      float aTheta = myY;
      float aPhi = myZ;
      float aY0 = myY0;
      float y = aRadius*(float)(Math.sin(aTheta)*Math.sin(aPhi));
      return y + aY0 + ey;
   }
   public static float sphericalC3(float myX,float myY,float myZ,float myZ0,float myZE)
   {
      float ez = myZE;
      float aRadius = myX;
      float aTheta = myY;
      float aPhi = myZ;
      float aZ0 = myZ0;
      float z = aRadius*(float)Math.cos(aTheta);
      return z + aZ0 + ez;
   }
   public static float parabolicC1(float myX,float myY,float myZ,float myX0,float myXE)
   {
      float ex = myXE;
      float aRadius = myX;
      float aTheta = myY;
      float aPhi = myZ;
      float aX0 = myX0;
      float x = (float)(myX*myY*Math.cos(aPhi));
      return x + aX0 + ex;
   }
   public static float parabolicC2(float myX,float myY,float myZ,float myY0,float myYE)
   {
      float ey = myYE;
      float aRadius = myX;
      float aTheta = myY;
      float aPhi = myZ;
      float aY0 = myY0;
      float y = (float)(myX*myY*Math.sin(aPhi));
      return y + aY0 + ey;
   }
   public static float parabolicC3(float myX,float myY,float myZ,float myZ0,float myZE)
   {
      float ez = myZE;
      float aRadius = myX;
      float aTheta = myY;
      float aPhi = myZ;
      float aZ0 = myZ0;
      float z = (float)(.5*(myX*myX-myY*myY));
      return z + aZ0 + ez;
   }
   public static float cylindricalC1(float myX,float myY,float myZ,float myX0,float myXE)
   {
      float ex = myXE;
      float aRadius = myX;
      float aPhi = myY;
      float aX0 = myX0;
      float x = aRadius*((float)Math.cos(aPhi));
      System.out.println("CrdntType.cylindricalC1: ex="+ex+",aRadius="+aRadius+",aPhi="+aPhi+",aX0="+aX0+",x="+x);

      return x + aX0 + ex;
   }
   public static float cylindricalC2(float myX,float myY,float myZ,float myY0,float myYE)
   {
      float ey = myYE;
      float aRadius = myX;
      float aY0 = myY0;
      float aPhi = myY;
      float y = aRadius*(float)(Math.sin(aPhi));
      System.out.println("CrdntType.cylindricalC1: ey="+ey+",aRadius="+aRadius+",aPhi="+aPhi+",aY0="+aY0+",y="+y);
      return y + aY0 + ey;
   }
   public static float cylindricalC3(float myX,float myY,float myZ,float myZ0,float myZE)
   {
      float ez = myZE;
      float aZ0 = myZ0;
      float z = myZ;
      System.out.println("CrdntType.cylindricalC1: ez="+ez+",aZ0="+aZ0+",z="+z);
      return z + aZ0 + ez;
   }
   public static float ellptcClndrclC1(float myX,float myY,float myZ,float myX0,float myXE)
   {
      float ex = myXE;
      float aRadius = myX;
      float aPhi = myY;
      float aX0 = myX0;
      float x = scale*(float)(Math.cosh(myX)*Math.cos(myY));
      //System.out.println("CrdntType.cylindricalC1: ex="+ex+",aRadius="+aRadius+",aPhi="+aPhi+",aX0="+aX0+",x="+x);

      return x + aX0 + ex;
   }
   public static float ellptcClndrclC2(float myX,float myY,float myZ,float myY0,float myYE)
   {
      float ey = myYE;
      float aRadius = myX;
      float aY0 = myY0;
      float aPhi = myY;
      float y = scale*(float)(Math.sinh(myX)*Math.sin(myY));
      //System.out.println("CrdntType.cylindricalC1: ey="+ey+",aRadius="+aRadius+",aPhi="+aPhi+",aY0="+aY0+",y="+y);
      return y + aY0 + ey;
   }
   public static float ellptcClndrclC3(float myX,float myY,float myZ,float myZ0,float myZE)
   {
      float ez = myZE;
      float aZ0 = myZ0;
      float z = myZ;
      //System.out.println("CrdntType.cylindricalC1: ez="+ez+",aZ0="+aZ0+",z="+z);
      return z + aZ0 + ez;
   }   
   public static float conicalC1(float myX,float myY,float myZ,float myX0,float myXE)
   {
      float ex = myXE;
      float aRadius = myX;
      float aPhi = myY;
      float aX0 = myX0;
      float x = myX*myY*myZ/A*B;
      //System.out.println("CrdntType.cylindricalC1: ex="+ex+",aRadius="+aRadius+",aPhi="+aPhi+",aX0="+aX0+",x="+x);

      return x + aX0 + ex;
   }
   public static float conicalC2(float myX,float myY,float myZ,float myY0,float myYE)
   {
      float sign = 1.0f;
      float ey = myYE;
      float aRadius = myX;
      float aY0 = myY0;
      float aPhi = myY;
      if((myY) < 0 || (myZ) < 0){
          sign = -1.0f;
      }
      float y = (float)((myX/A)*Math.sqrt((myY*myY-A*A)*(myZ*myZ-A*A)));
      //System.out.println("CrdntType.cylindricalC1: ey="+ey+",aRadius="+aRadius+",aPhi="+aPhi+",aY0="+aY0+",y="+y);
      return y + aY0 + ey;
   }
   public static float conicalC3(float myX,float myY,float myZ,float myZ0,float myZE)
   {
      float sign = 1.0f;
      float ez = myZE;
      float aZ0 = myZ0;
      //float z = myZ;
      //System.out.println("CrdntType.cylindricalC1: ez="+ez+",aZ0="+aZ0+",z="+z);
      if((myY) < 0 || (myZ) < 0){
          sign = -1.0f;
      }

      float z = (float)((myX/B)*Math.sqrt((myY*myY-B*B)*(myZ*myZ-B*B)));
      return z + aZ0 + ez;
   }
   public static float biSphericalC1(float myX,float myY,float myZ,float myX0,float myXE)
   {
      float ex = myXE;
      float aRadius = myX;
      float aPhi = myY;
      float aX0 = myX0;
      float x = (float)(scale*(Math.sin(myX)*Math.cos(myZ))/(Math.cosh(myY)-Math.cos(myX)));
      //System.out.println("CrdntType.cylindricalC1: ex="+ex+",aRadius="+aRadius+",aPhi="+aPhi+",aX0="+aX0+",x="+x);

      return x + aX0 + ex;
   }
   public static float biSphericalC2(float myX,float myY,float myZ,float myY0,float myYE)
   {
      float sign = 1.0f;
      float ey = myYE;
      float aRadius = myX;
      float aY0 = myY0;
      float aPhi = myY;

      float y = (float)(scale*(Math.sin(myX)*Math.sin(myZ))/(Math.cosh(myY)-Math.cos(myX)));
      //System.out.println("CrdntType.cylindricalC1: ey="+ey+",aRadius="+aRadius+",aPhi="+aPhi+",aY0="+aY0+",y="+y);
      return y + aY0 + ey;
   }
   public static float biSphericalC3(float myX,float myY,float myZ,float myZ0,float myZE)
   {
      float ez = myZE;
      float aZ0 = myZ0;
      //float z = myZ;
      //System.out.println("CrdntType.cylindricalC1: ez="+ez+",aZ0="+aZ0+",z="+z);

      float z = (float)(scale*(Math.sinh(myY))/(Math.cosh(myY)-Math.cos(myX)));
      return z + aZ0 + ez;
   }   
   /*
   public static float oblateSphrdlC1(float myX,float myY,float myZ,float myX0,float myXE)
   {
      float a = 1.0f;
      float ex = myXE;
      float aRadius = myX;
      float aPhi = myY;
      float aX0 = myX0;
      float x = (float)(a*Math.cosh(myX)*Math.cos(myY)*Math.cos(myZ));
      //System.out.println("CrdntType.cylindricalC1: ex="+ex+",aRadius="+aRadius+",aPhi="+aPhi+",aX0="+aX0+",x="+x);

      return x + aX0 + ex;
   }
   public static float oblateSphrdlC2(float myX,float myY,float myZ,float myY0,float myYE)
   {
      float a = 1.0f;
      float ey = myYE;
      float aRadius = myX;
      float aY0 = myY0;
      float aPhi = myY;
      float y = (float)(a*Math.cosh(myX)*Math.cos(myY)*Math.sin(myZ));
      //System.out.println("CrdntType.cylindricalC1: ey="+ey+",aRadius="+aRadius+",aPhi="+aPhi+",aY0="+aY0+",y="+y);
      return y + aY0 + ey;
   }
   public static float oblateSphrdlC3(float myX,float myY,float myZ,float myZ0,float myZE)
   {
      float a = 1.0f;
      float ez = myZE;
      float aZ0 = myZ0;
      float z = (float)(a*Math.sinh(myX)*Math.sin(myY));
      //System.out.println("CrdntType.cylindricalC1: ez="+ez+",aZ0="+aZ0+",z="+z);
      return z + aZ0 + ez;
   }*/   
   public static float oblateSphrdlC1(float myX,float myY,float myZ,float myX0,float myXE)
   {
      float a = .50f;
      float ex = myXE;
      float aRadius = myX;
      float aPhi = myY;
      float aX0 = myX0;
      float zi1 = (float)Math.sinh(myX);
      float ziPrime = (float)Math.cosh(myX);
      float zi2 = (float)Math.cos(myY);
      float zi3 = myZ;
      float x = a*ziPrime*zi2*(float)Math.cos(zi3);
      //System.out.println("CrdntType.cylindricalC1: ex="+ex+",aRadius="+aRadius+",aPhi="+aPhi+",aX0="+aX0+",x="+x);

      return x + aX0 + ex;
   }
   public static float oblateSphrdlC2(float myX,float myY,float myZ,float myY0,float myYE)
   {
      float a = 0.50f;
      float ey = myYE;
      float aRadius = myX;
      float aY0 = myY0;
      float aPhi = myY;
      float zi1 = (float)Math.sinh(myX);
      float ziPrime = (float)Math.cosh(myX);
      float zi2 = (float)Math.cos(myY);
      float zi3 = myZ;
      
      float y = a*ziPrime*zi2*(float)Math.sin(zi3);
      //System.out.println("CrdntType.cylindricalC1: ey="+ey+",aRadius="+aRadius+",aPhi="+aPhi+",aY0="+aY0+",y="+y);
      return y + aY0 + ey;
   }
   public static float oblateSphrdlC3(float myX,float myY,float myZ,float myZ0,float myZE)
   {
      float a = 0.50f;
      float ez = myZE;
      float aZ0 = myZ0;
      
      float zi1 = (float)Math.sinh(myX);
      float ziPrime = (float)Math.cosh(myX);
      float zi2 = (float)Math.cos(myY);
      float zi3 = myZ;
      
      float z = (float)(a*Math.sqrt((ziPrime*ziPrime-1)*(1-zi2*zi2)));      

      //float z = myZ;
      //System.out.println("CrdntType.oblateSphrdl: z=" + z+", myZ = "+myZ);      
      return z + aZ0 + ez;
   }
   public static float confocalEllpsdlC1(float myX,float myY,float myZ,float myX0,float myXE)
   {
       
      float ex = myXE;

      float aX0 = myX0;
      
      float zi = myX;
      float eta = myY;
      float zeta = myZ; 
      
      float x = (float)Math.sqrt(((A*A+zi)*(A*A+eta)*(A*A+zeta))/((B*B-A*A)*(C*C-A*A)));
      //System.out.println("CrdntType.cylindricalC1: ex="+ex+",aRadius="+aRadius+",aPhi="+aPhi+",aX0="+aX0+",x="+x);
      //System.out.println("CrdntType.confocalEllpsdlC3: x =" + x);      
      return x + aX0 + ex;
   }
   public static float confocalEllpsdlC2(float myX,float myY,float myZ,float myY0,float myYE)
   {
      
      float ey = myYE;

      float aY0 = myY0;
      
      float zi = myX;
      float eta = myY;
      float zeta = myZ; 
      
      float y = (float)(Math.sqrt((B*B+zi)*(B*B+eta)*(B*B+zeta))/((A*A-B*B)*(C*C-B*B)));
      //System.out.println("CrdntType.confocalEllpsdlC3: y=" + y);      
      return y + aY0 + ey;
      
   }
   public static float confocalEllpsdlC3(float myX,float myY,float myZ,float myZ0,float myZE)
   {
       
      float ez = myZE;
      float aZ0 = myZ0;
      
      float zi = myX;
      float eta = myY;
      float zeta = myZ; 
      
      float z = (float)Math.sqrt(((C*C+zi)*(C*C+eta)*(C*C+zeta))/((A*A-C*C)*(B*B-C*C)));

      //float z = myZ;
      //System.out.println("CrdntType.confocalEllpsdlC3: z=" + z+", myZ = "+myZ);      
      return z + aZ0 + ez;
   }   
   public static float knotC1(float myX,float myY,float myZ,float myX0,float myXE)
   {
      float ex = myXE;
      float aRadius = myX;
      float aPhi = myY;
      float aX0 = myX0;

      //float aPhi = myY;
      float xOffSet = spiralXOffSet*(float)(Math.cos(myZ));
      
      //float xOffSet = spiralXOffSet*((float)Math.cos(aPhi));      
      float x = aRadius*((float)Math.cos(aPhi)) + xOffSet;
      //System.out.println("CrdntType.cylindricalC1: ex="+ex+",aRadius="+aRadius+",aPhi="+aPhi+",aX0="+aX0+",x="+x);

      return x + aX0 + ex;
   }
   public static float knotC2(float myX,float myY,float myZ,float myY0,float myYE)
   {
      float ey = myYE;
      float aRadius = myX;
      float aY0 = myY0;
      float aPhi = myY;
      float yOffSet = spiralYOffSet*(float)(Math.sin(myZ));
      float y = aRadius*(float)(Math.sin(aPhi))+yOffSet;
      //System.out.println("CrdntType.cylindricalC1: ey="+ey+",aRadius="+aRadius+",aPhi="+aPhi+",aY0="+aY0+",y="+y);
      return y + aY0 + ey;
   }
   public static float knotC3(float myX,float myY,float myZ,float myZ0,float myZE)
   {
      float ez = myZE;
      float aZ0 = myZ0;
      float z = myZ;
      //System.out.println("CrdntType.cylindricalC1: ez="+ez+",aZ0="+aZ0+",z="+z);
      return z + aZ0 + ez;
   }   
   public static float helixC1(float myX,float myY,float myZ,float myX0,float myXE)
   {
      float ex = myXE;
      float aRadius = myX;
      float aPhi = myY;
      float aX0 = myX0;

      float x = aRadius*((float)Math.cos(aPhi));
      //float x = aRadius*((float)Math.cos(myZ*2*Math.PI/pitch));
      return x + aX0 + ex;
   }
   public static float helixC2(float myX,float myY,float myZ,float myY0,float myYE)
   {
      float ey = myYE;
      float aRadius = myX;
      float aY0 = myY0;
      float aPhi = myY;
      float y = aRadius*(float)(Math.sin(aPhi));
      //float y = aRadius*(float)(Math.sin(myZ*2*Math.PI/pitch));      
      return y + aY0 + ey;
   }
   public static float helixC3(float myX,float myY,float myZ,float myZ0,float myZE)
   {
      float ez = myZE;
      float aZ0 = myZ0;
      float aZ1 = myZ;
      float aPhi = myY;
      float z = (float)(aPhi * pitch/(2*Math.PI));
      //float z = (float)(aZ1);      
      return z + aZ0 + aZ1 + ez;
   }
   public static float toroidalC1(float myX,float myY,float myZ,float myX0,float myXE)
   {
      float a = 1.00f;
      float ex = myXE;
      float aX0 = myX0;
      double sinhv = TrigFnctn.sinh(myY);
      double cosPhi = Math.cos(myZ);
      double coshv = TrigFnctn.cosh(myY);
      double cosu = Math.cos(myX);
      float x = (float)((a*sinhv*cosPhi)/(coshv-cosu));
      return x + aX0 + ex;
      //System.out.println("Point : x coord = "+xTotal);
   }
   public static float toroidalC2(float myX,float myY,float myZ,float myY0,float myYE)
   {
      float a = 1.00f;
      float ey = myYE;
      float aY0 = myY0;
      double sinhv = TrigFnctn.sinh(myY);
      double sinPhi = Math.sin(myZ);
      double coshv = TrigFnctn.cosh(myY);
      double cosu = Math.cos(myX);
      float y = (float)((a*sinhv*sinPhi)/(coshv-cosu));
      return y + aY0 + ey;
      //System.out.println("Point : y coord = "+yTotal);
  
   }
   public static float toroidalC3(float myX,float myY,float myZ,float myZ0,float myZE)
   {
      float a = 1.00f;
      float ez = myZE;
      float aZ0 = myZ0;
      double coshv = TrigFnctn.cosh(myY);
      double sinu = Math.sin(myX);
      double cosu = Math.cos(myX);
      float z = (float)((a*sinu)/(coshv-cosu));
      return z + aZ0 + ez;
      //System.out.println("Point : z coord = "+zTotal);
   }
   public static float cartesianC1(float myX,float myY,float myZ,float myX0,float myXE)
   {
      return myX+myX0+myXE;
   }
   public static float cartesianC2(float myX,float myY,float myZ,float myY0,float myYE)
   {
      return myY+myY0+myYE;
   }
   public static float cartesianC3(float myX,float myY,float myZ,float myZ0,float myZE)
   {
      return myZ+myZ0+myZE;
   }
   public static float rtrvC1(String myCrdntType,float myX,float myY,float myZ,float myX0,float myXE)
   {
      //System.out.println("CrdntType: myCrdntType = "+myCrdntType);
      if(myCrdntType!=null && myCrdntType.equals(CrdntType.getSphrclCrdntTp()))
      {
         return CrdntType.sphericalC1(myX,myY,myZ,myX0,myXE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getClndrclCrdntTp())){
         return CrdntType.cylindricalC1(myX,myY,myZ,myX0,myXE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getTrdlCrdntTp())){
         return CrdntType.toroidalC1(myX,myY,myZ,myX0,myXE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getHlxCrdntTp())){
         return CrdntType.helixC1(myX,myY,myZ,myX0,myXE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getKnotCrdntTp())){
         return CrdntType.knotC1(myX,myY,myZ,myX0,myXE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getOblateSphrdlCrdntTp())){
         return CrdntType.oblateSphrdlC1(myX,myY,myZ,myX0,myXE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getConfocalEllpsdlCrdntTp())){
         return CrdntType.confocalEllpsdlC1(myX,myY,myZ,myX0,myXE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getConicalCrdntTp())){
         return CrdntType.conicalC1(myX,myY,myZ,myX0,myXE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getEllptcClndrclCrdntTp())){
         return CrdntType.ellptcClndrclC1(myX,myY,myZ,myX0,myXE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getParabolicCrdntTp())){
         return CrdntType.parabolicC1(myX,myY,myZ,myX0,myXE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getBiSphericalCrdntTp())){
         return CrdntType.biSphericalC1(myX,myY,myZ,myX0,myXE);
      }else{
         return CrdntType.cartesianC1(myX,myY,myZ,myX0,myXE);
      }
   }
   public static float rtrvC2(String myCrdntType,float myX,float myY,float myZ,float myY0,float myYE)
   {
      //System.out.println("CrdntType: myCrdntType = "+myCrdntType);
      if(myCrdntType!=null && myCrdntType.equals(CrdntType.getSphrclCrdntTp()))
      {
         return CrdntType.sphericalC2(myX,myY,myZ,myY0,myYE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getClndrclCrdntTp())){
         return CrdntType.cylindricalC2(myX,myY,myZ,myY0,myYE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getKnotCrdntTp())){
         return CrdntType.knotC2(myX,myY,myZ,myY0,myYE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getTrdlCrdntTp())){
         return CrdntType.toroidalC2(myX,myY,myZ,myY0,myYE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getHlxCrdntTp())){
         return CrdntType.helixC2(myX,myY,myZ,myY0,myYE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getOblateSphrdlCrdntTp())){
         return CrdntType.oblateSphrdlC2(myX,myY,myZ,myY0,myYE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getConfocalEllpsdlCrdntTp())){
         return CrdntType.confocalEllpsdlC2(myX,myY,myZ,myY0,myYE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getConicalCrdntTp())){
         return CrdntType.conicalC2(myX,myY,myZ,myY0,myYE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getEllptcClndrclCrdntTp())){
         return CrdntType.ellptcClndrclC2(myX,myY,myZ,myY0,myYE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getParabolicCrdntTp())){
         return CrdntType.parabolicC2(myX,myY,myZ,myY0,myYE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getBiSphericalCrdntTp())){
         return CrdntType.biSphericalC2(myX,myY,myZ,myY0,myYE);
      }else{
         return CrdntType.cartesianC2(myX,myY,myZ,myY0,myYE);
      }
   }
   public static float rtrvC3(String myCrdntType,float myX,float myY,float myZ,float myZ0,float myZE)
   {
      //System.out.println("CrdntType: myCrdntType = "+myCrdntType);
      if(myCrdntType!=null && myCrdntType.equals(CrdntType.getSphrclCrdntTp()))
      {
         return CrdntType.sphericalC3(myX,myY,myZ,myZ0,myZE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getClndrclCrdntTp())){
         return CrdntType.cylindricalC3(myX,myY,myZ,myZ0,myZE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getKnotCrdntTp())){
         return CrdntType.knotC3(myX,myY,myZ,myZ0,myZE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getTrdlCrdntTp())){
         return CrdntType.toroidalC3(myX,myY,myZ,myZ0,myZE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getHlxCrdntTp())){
         return CrdntType.helixC3(myX,myY,myZ,myZ0,myZE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getOblateSphrdlCrdntTp())){
         return CrdntType.oblateSphrdlC3(myX,myY,myZ,myZ0,myZE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getConfocalEllpsdlCrdntTp())){
         return CrdntType.confocalEllpsdlC3(myX,myY,myZ,myZ0,myZE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getConicalCrdntTp())){
         return CrdntType.conicalC3(myX,myY,myZ,myZ0,myZE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getEllptcClndrclCrdntTp())){
         return CrdntType.ellptcClndrclC3(myX,myY,myZ,myZ0,myZE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getParabolicCrdntTp())){
         return CrdntType.parabolicC3(myX,myY,myZ,myZ0,myZE);
      }else if(myCrdntType!=null && myCrdntType.equals(CrdntType.getBiSphericalCrdntTp())){
         return CrdntType.biSphericalC3(myX,myY,myZ,myZ0,myZE);
      }else{
         return CrdntType.cartesianC3(myX,myY,myZ,myZ0,myZE);
      }
   }
}