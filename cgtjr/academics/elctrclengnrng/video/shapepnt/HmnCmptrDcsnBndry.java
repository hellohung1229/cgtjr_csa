package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.elctrclengnrng.video.shapepnt.TrckrBndry;

/**
 * 
 * @author clayton g thomas jr
 */
public class HmnCmptrDcsnBndry extends TrckrBndry
{
   private TrckrBndry dcsnBndies[];

   public HmnCmptrDcsnBndry()
   {
      dcsnBndies = new TrckrBndry[3];
      dcsnBndies[0] = new TrckrBndry();
      dcsnBndies[1] = new TrckrBndry();
      dcsnBndies[2] = new TrckrBndry();
   }
   public void updtBndry(float xPos, float yPos, float zPos) 
   {
      super.updtBndry(xPos, yPos, zPos);
      double center[] = cmptCenter();
      int xCntr = (int)center[0];
      int yCntr = (int)center[1];
      if(xPos < xCntr-70)
      {
         dcsnBndies[1].updtBndry(xPos,yPos,zPos);
      }else if(xPos > xCntr+70)
      {
         dcsnBndies[2].updtBndry(xPos,yPos,zPos);
      }else if(xPos > xCntr-60 && xPos < xCntr+60 && yPos < 50)
      {
         dcsnBndies[0].updtBndry(xPos,yPos,zPos);
      }
   }
   public void drawBndry(int[] myPixelData, int myImgWidth,int myImgHeight,int myColor) {
      super.drawBndry(myPixelData,myImgWidth,myImgHeight,myColor);
      dcsnBndies[0].drawBndry(myPixelData, myImgWidth,myImgHeight,0x000000ff);
      dcsnBndies[1].drawBndry(myPixelData, myImgWidth,myImgHeight,0x00ff0000);
      dcsnBndies[2].drawBndry(myPixelData, myImgWidth,myImgHeight,0x00ff0000);

   }

   //Could this be change to ShpBndry ... for dynamic boundary insertion?!



 
}