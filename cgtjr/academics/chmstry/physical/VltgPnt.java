package cgtjr.academics.chmstry.physical;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.*;


public class VltgPnt extends Pnt
{
   private float permittivity = 1;
   private double voltage;
   private float charge;
   private double aGrdSpc = 1;
   private float debyeHuckel = 0;
   private boolean isFixedVltg = false;


   public void setIsFixedVltg(boolean myIsFixedVltg)
   {
      isFixedVltg = myIsFixedVltg;
   }
   public boolean getIsFixedVltg()
   {
      return isFixedVltg;
   }
   public void setPermittivity(float anPermittivity)
   {
      permittivity = anPermittivity;
   }
   public float getPermittivity()
   {
      return permittivity;
   }
   public void setVoltage(double myVoltage)
   {
      voltage = myVoltage;
   }
   public double getVoltage()
   {
      return voltage;
   }   
   public void setCharge(float myCharge)
   {
      charge = myCharge;
   }
   public float getCharge()
   {
      return charge;
   }
   public Pnt createDataPoint()
   {
      VltgPnt aVltgPnt = new VltgPnt();
      aVltgPnt.setColor(0xffffff);
      aVltgPnt.setDeltaX(getDeltaX());
      aVltgPnt.setDeltaY(getDeltaY());
      aVltgPnt.setDeltaZ(getDeltaZ());
      return (Pnt)aVltgPnt;
   }
   public Pnt createPoint()
   {
      //System.out.println("VltgPnt: createVltgPnt()");
      VltgPnt aVltgPnt = new VltgPnt();
      aVltgPnt.setColor(0xffffff);
      aVltgPnt.setDeltaX(getDeltaX());
      aVltgPnt.setDeltaY(getDeltaY());
      aVltgPnt.setDeltaZ(getDeltaZ());

      return (Pnt)aVltgPnt;
   }
   public void compute()
   {
      compute(this);
   }
   public void compute(VltgPnt myPoint)
   {
      if(getIsFixedVltg() == true)
      {
         return;
      }
      VltgPnt aVltgPnt1 = (VltgPnt)myPoint;
      VltgPnt aVltgPnt2 = null;      
      int aSize = aVltgPnt1.nmbrOfVertices();

      double aVltgPrmttvtSum = 0;
      double aPrmttvtySum = 0;
      double aVoltage = 0;
      double aVoltageSum = 0;
      double aPrmttvty = 0;
      double aIntlPrmttvty = 0;
      //System.out.println("VltgPnt: aSize = "+aSize);
      aIntlPrmttvty = aVltgPnt1.getPermittivity();
      for(int i=0;i<aSize;i++)
      {
         aVltgPnt2 = (VltgPnt)aVltgPnt1.rtrvVertex(i);
         aVoltage = aVltgPnt2.getVoltage();
         aPrmttvty = aVltgPnt2.getPermittivity();
         aVltgPrmttvtSum += aVoltage*aPrmttvty ;
         aPrmttvtySum += aPrmttvty;
         //System.out.println("VltgPnt: aVoltage = "+aVoltage +", aPrmttvty = "+aPrmttvty  );
      }
      aVoltageSum = (aVltgPrmttvtSum + 4*Math.PI*charge)/
                    (aPrmttvtySum + debyeHuckel*debyeHuckel*aGrdSpc*aGrdSpc);
      aVltgPnt1.setVoltage(aVoltageSum);         
      //System.out.println("VltgPnt: aVoltageSum = "+aVoltageSum );     
   }
}