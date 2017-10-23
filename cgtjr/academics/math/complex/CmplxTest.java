/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.complex;

/**
 *
 * @author clayton g thomas jr
 */
public class CmplxTest
{
   public static void main(String args[])
   {
      double cn1[] = CmplxNmbr.rtvCmplxNmbr(4,-2);
      double cn2[] = CmplxNmbr.rtvCmplxNmbr(2,-1);
      double cn3[] = CmplxNmbr.add(cn1, cn2);
      double cn4[] = CmplxNmbr.mltply(cn1, cn2);
      double cn5[] = CmplxNmbr.divide(cn1, cn2);
      double cn6[] = CmplxNmbr.scale(cn1, -1);
      double cn7[] = CmplxNmbr.sub(cn1, cn2);

      System.out.println("cn1 = "+cn1[0]+","+cn1[1]);
      System.out.println("cn2 = "+cn2[0]+","+cn2[1]);
      System.out.println("add: cn3 = "+cn3[0]+","+cn3[1]);
      System.out.println("mltply: cn4 = "+cn4[0]+","+cn4[1]);
      System.out.println("divide: cn5 = "+cn5[0]+","+cn5[1]);
      System.out.println("scale: cn6 = "+cn6[0]+","+cn6[1]);
      System.out.println("sub: cn7 = "+cn7[0]+","+cn7[1]);

      cn1 = CmplxNmbr.rtvCmplxNmbr(0,1);
      cn2 = CmplxNmbr.rtvCmplxNmbr(0,1);
      cn3 = CmplxNmbr.add(cn1, cn2);
      cn4 = CmplxNmbr.mltply(cn1, cn2);
      cn5 = CmplxNmbr.divide(cn1, cn2);
      cn6 = CmplxNmbr.scale(cn1, -1);
      cn7 = CmplxNmbr.sub(cn1, cn2);

      System.out.println("cn1 = "+cn1[0]+","+cn1[1]);
      System.out.println("cn2 = "+cn2[0]+","+cn2[1]);
      System.out.println("add: cn3 = "+cn3[0]+","+cn3[1]);
      System.out.println("mltply: cn4 = "+cn4[0]+","+cn4[1]);
      System.out.println("divide: cn5 = "+cn5[0]+","+cn5[1]);
      System.out.println("scale: cn6 = "+cn6[0]+","+cn6[1]);
      System.out.println("sub: cn7 = "+cn7[0]+","+cn7[1]);
   }
}
