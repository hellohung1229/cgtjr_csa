/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general;

/**
 *
 * @author Nit
 */
public class TimeAnlss 
{
   private long time1;
   private long time2;
   
   public void setTime1(long myTime1)
   {
      time1 = myTime1;
   }
   public void setTime2(long myTime2)
   {
      time2 = myTime2;
   }
   public void setTime1()
   {
      long startTime = System.currentTimeMillis();
      setTime1(startTime);
   }
   public void setTime2()
   {
      long endTime = System.currentTimeMillis();
      setTime2(endTime);
   }
   public long getTime1()
   {
      return time1;
   }
   public long getTime2()
   {
      return time2;
   }
   public long rtrvTmDiff()
   {
      long diff = time2 - time1;
      return diff;
   }
}
