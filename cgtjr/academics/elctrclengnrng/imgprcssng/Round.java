/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng;

/**
 *
 * @author clayton g thomas jr
 */
public class Round
{
   private int maxNmbr;

   public Round()
   {
      maxNmbr = 1000;
   }
   public static int getLength3Digit(double myNmbr,int myDgtPstn)
   {
      int aNmbr = 0;
      if(myDgtPstn == 4)
      {
         aNmbr = (int)myNmbr/1000;
      }else if(myDgtPstn == 3){
         myNmbr = myNmbr-((int)myNmbr/1000)*1000;
         aNmbr = (int)myNmbr/100;
      }else if(myDgtPstn == 2){
         myNmbr = myNmbr-((int)myNmbr/1000)*1000;
         myNmbr = myNmbr-((int)myNmbr/100)*100;
         aNmbr = (int)myNmbr/10;
      }else if(myDgtPstn == 1){
         myNmbr = myNmbr-((int)myNmbr/1000)*1000;
         myNmbr = myNmbr-((int)myNmbr/100)*100;
         myNmbr = myNmbr-((int)myNmbr/10)*10;
         aNmbr = (int)myNmbr;      
      }
      return aNmbr;
   }
   public static int getLength2Digit(double myNmbr,int myDgtPstn)
   {
      int aNmbr = 0;
      if(myDgtPstn == 1){
         myNmbr = myNmbr-((int)myNmbr/10)*10;
         aNmbr = (int)myNmbr/1;
      }else if(myDgtPstn == 2){
         aNmbr = (int)myNmbr/10;     
      }
      return aNmbr;
   }   
   public static int getDgtPxlPstn(int myDgtPstn,int myMaxDgtLngth)
   {
      int aNmbr = myMaxDgtLngth*9/2 - (myDgtPstn-1)*9;
      return aNmbr;
   }
   public static int getXDgtPxlPstn(int myDgtPstn,int myMaxDgtLngth)
   {
      int aNmbr = -myDgtPstn*9;
      return aNmbr;
   }
   public static int getYDgtPxlPstn(int myDgtPstn,int myMaxDgtLngth)
   {
      int aNmbr = (myMaxDgtLngth+1-myDgtPstn)*9;
      return aNmbr;
   }
   public static int getDgtLngth(double myNmbr)
   {
      int aLength = 0;
      if((int)myNmbr/1000 >= 1)
      {
         aLength = 4;
      }else if((int)myNmbr/100 >= 1){
         aLength = 3;
      }else if((int)myNmbr/10 >= 1){
         aLength = 2;
      }else if((int)myNmbr/1 >= 1){
         aLength = 1;
      }
      return aLength;
   }
}
