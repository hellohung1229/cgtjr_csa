/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.cv.activecontour;

/**
 *
 * @author cthomas
 */
public class CircularQue
{
   private int index;
   private int count;
   protected int size = 100000;

   public CircularQue(){}

   public CircularQue(int mySize){
       size = mySize;
   }
   /*
   public void addItem(int myItem)
   {
      items[count] = myItem;
      count++;
   }*/
   public void setCount(int myCount)
   {
      count = myCount;
   }
   public int getCount()
   {
      return count;
   }
   public void setIndex(int myIndex)
   {
      index = myIndex;
   }
   public int getIndex()
   {
      return index;
   }
   public int getPrvsIndex()
   {
      //int index = 0;
      if(index == 0)
      {
          index = count - 1;
      }else{
          index = index - 1;
      }
      return index;
   }
   public int getNxtIndex()
   {
      //int index = 0;
      if(index == count - 1)
      {
          index = 0;
      }else{
          index = index + 1;
      }
      return index;
   }

}
