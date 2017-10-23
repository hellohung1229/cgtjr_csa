/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.cv.activecontour;

/**
 *
 * @author cthomas
 */
public class CrclrQueCrdnt extends CircularQue
{
   private int xCrdnt[];
   private int yCrdnt[];

   public CrclrQueCrdnt()
   {
      xCrdnt = new int[size];
      yCrdnt = new int[size];
   }
   public CrclrQueCrdnt(int myXCrdnt[],int myYCrdnt[])
   {
      xCrdnt = myXCrdnt;
      yCrdnt = myYCrdnt;
      int aCount = myXCrdnt.length;
      setCount(aCount);
   }
   public void addCrdnt(int myX,int myY)
   {
      int count = getCount();
      xCrdnt[count] = myX;
      yCrdnt[count] = myY;

      count++;
      setCount(count);
   }
   public int getXCrdnt()
   {
      int anIndex = getIndex();
      return xCrdnt[anIndex];
   }
   public int getYCrdnt()
   {
      int anIndex = getIndex();
      return yCrdnt[anIndex];
   }
   public int getXCrdnt(int myIndex)
   {
      return xCrdnt[myIndex];
   }
   public int getYCrdnt(int myIndex)
   {
      return yCrdnt[myIndex];
   }
   public int getNxtXCrdnt()
   {
      int anIndex = getNxtIndex();
      return xCrdnt[anIndex];
   }
   public int getNxtYCrdnt()
   {
      int anIndex = getNxtIndex();
      return yCrdnt[anIndex];
   }
   public int getPrvsXCrdnt()
   {
      int anIndex = getPrvsIndex();
      return xCrdnt[anIndex];
   }
   public int getPrvsYCrdnt()
   {
      int anIndex = getPrvsIndex();
      return yCrdnt[anIndex];
   }
}
