package cgtjr.academics.general;

import java.util.*;
/**
 *
 * @author clayton g thomas jr
 */
public class DataLstPrcss extends DataPrcss
{
   Vector aVector;
   public DataLstPrcss()
   {
      aVector = new Vector();
   }
   public void insrtDtPrcss(DataPrcss myDataPrcss)
   {
      aVector.add(myDataPrcss);
   }
   public void prcss()
   {
      int aSize = aVector.size();
      DataPrcss aDataPrcss = null;

      for(int i=0;i<aSize;i++)
      {
         aDataPrcss = (DataPrcss)aVector.elementAt(i);
         aDataPrcss.prcss();
      }
   }
}