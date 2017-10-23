/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.cv.activemesh;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.elctrclengnrng.cv.CornerDetect;


public class GrdntEnrgyFltr extends CornerDetect
{
   private int minGrdntE;
   private int grdntIndex;

   public void filter3x3(int dataValues[],int i)
   {
      //super.filter(dataValues,i);
      //////////grdntFilter3x3(dataValues,i);
      cmptGrdntsIntl(i);
      cmptGrdnts3x3(i);
   }
   public void filter9x9(int dataValues[],int i)
   {
      //super.filter(dataValues,i);
      ////////////grdntFilter9x9(dataValues,i);
      cmptGrdntsIntl(i);
      cmptGrdnts9x9(i);
   }
   public void cmptGrdntsIntl(int anIndex)
   {
       int energyCrv5 = cmptGrdnt(anIndex);
       setMinGrdntE(energyCrv5);
       setGrdntIndex(anIndex);      
   }
   public void cmptGrdnts9x9(int anIndex)
   {
       int aWidthx3 = 3;///////////*getImageWidth();
       //System.out.println("GrdntEnrgyFltr width = "+aWidthx3);

       cmptGrdnts3x3(anIndex-aWidthx3-3);
       cmptGrdnts3x3(anIndex-aWidthx3);
       cmptGrdnts3x3(anIndex-aWidthx3+3);
       cmptGrdnts3x3(aWidthx3-3);
       cmptGrdnts3x3(anIndex);
       cmptGrdnts3x3(aWidthx3+3);
       cmptGrdnts3x3(anIndex+aWidthx3-3);
       cmptGrdnts3x3(anIndex+aWidthx3);
       cmptGrdnts3x3(anIndex+aWidthx3+3);      
   }
   public void cmptGrdnts3x3(int anIndex)
   {
       int aWidth = 1;/////////getImageWidth();
       //System.out.println("GradientFilter: aWidth = "+aWidth);
       //int anIndex = cmptImageIndex(myCntrIndex);

       int energyCrv1 = cmptGrdnt(anIndex-aWidth-1);
       updtGrdntEnergy(energyCrv1,anIndex-aWidth-1);
       int energyCrv2 = cmptGrdnt(anIndex-aWidth);
       updtGrdntEnergy(energyCrv2,anIndex-aWidth);
       int energyCrv3 = cmptGrdnt(anIndex-aWidth+1);
       updtGrdntEnergy(energyCrv3,anIndex-aWidth+1);
       int energyCrv4 = cmptGrdnt(anIndex-1);
       updtGrdntEnergy(energyCrv4,anIndex-1);

       //int energyCrv5 = cmptGrdnt(anIndex);
       //updtGrdntEnergy(energyCrv5,anIndex);

       int energyCrv6 = cmptGrdnt(anIndex+1);
       updtGrdntEnergy(energyCrv6,anIndex+1);
       int energyCrv7 = cmptGrdnt(anIndex+aWidth-1);
       updtGrdntEnergy(energyCrv7,anIndex+aWidth-1);
       int energyCrv8 = cmptGrdnt(anIndex+aWidth);
       updtGrdntEnergy(energyCrv8,anIndex+aWidth);
       int energyCrv9 = cmptGrdnt(anIndex+aWidth+1);
       updtGrdntEnergy(energyCrv9,anIndex+aWidth+1);
       //System.out.println("GrdntEngryFltr: test 2");
   }
   public int cmptGrdnt(int myIndex)
   {
      int aGradient[] = null;////////getGrdntMgntd();
      if(false)/////!isInBounds3x3(myIndex))
       {
          return 1;
       }else{
         int aValue = -1*aGradient[myIndex];
         return aValue;
       }
   }
   public void updtGrdntEnergy(int myEnergy,int myIndex)
   {
      //System.out.println("GrdntEnrgyFltr: energy = "+myEnergy+", min energy= "+minGrdntE+", index = "+myIndex+", min index = "+grdntIndex);

       if(myEnergy < minGrdntE && myEnergy < -140)
       {
           minGrdntE = myEnergy;
           grdntIndex = myIndex;
       }
      //System.out.println("GrdntEnrgyFltr: index = "+myIndex+", new index= "+grdntIndex);
   }
   public void setMinGrdntE(int myMinGrdntE)
   {
      minGrdntE = myMinGrdntE;
   }
   public void setGrdntIndex(int myGrdntIndex)
   {
      grdntIndex = myGrdntIndex;
   }
   public int getGrdntIndex()
   {
      //System.out.println("GrdntEnrgyFltr: grdntIndex = "+grdntIndex);
      return grdntIndex;
   }
}
