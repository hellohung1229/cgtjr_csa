/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.complex;

/**
 *
 * @author clayton g thomas jr
 */
public class CmplxMatrix {

   public static double[][] add(double m1[][],double m2[][])
   {
      int r = m1.length;
      int c = m1[0].length;

      double matrix[][] = new double[r][c];

      for(int i=0;i<r;i++)
      {
         for(int j=0;j<c;j++)
         {
            matrix[i][j] = m1[i][j] + m2[i][j];
         }
      }
      return matrix;
   }
   public static double[][][] print(double m1[][][])
   {
      int r = m1.length;
      int c = m1[0].length;

      double matrix[][][] = new double[r][c][2];
      System.out.println("... Cmplx Matrix Print ...");
      for(int i=0;i<r;i++)
      {
         System.out.println("");
         for(int j=0;j<c;j++)
         {
            System.out.print(CmplxNmbr.toString(m1[i][j])+"  ");
         }
      }
      System.out.println("");
      return matrix;
   }
   public static double[][][] add(double m1[][][],double m2[][][])
   {
      int r = m1.length;
      int c = m1[0].length;

      double matrix[][][] = new double[r][c][2];

      for(int i=0;i<r;i++)
      {
         for(int j=0;j<c;j++)
         {
            matrix[i][j] = CmplxNmbr.add(m1[i][j],m2[i][j]);
         }
      }
      return matrix;
   }
   public static double[] magnitude(double m1[][])
   {
      int r = m1.length;

      double matrix[] = new double[r];

      for(int i=0;i<r;i++)
      {
         matrix[i] = CmplxNmbr.getMagnitude(m1[i]);
      }
      return matrix;
   }
   public static double[] magnitudeX(double m1[][])
   {
      int r = m1.length;

      double matrix[] = new double[r];

      for(int i=0;i<r;i++)
      {
         matrix[i] = m1[i][0]/CmplxNmbr.getMagnitude(m1[i]);
      }
      return matrix;     
   }
   /*
   public static double[] magnitude(double m1[][])
   {
      int r = m1.length;

      double matrix[] = new double[r];

      for(int i=0;i<r;i++)
      {
         matrix[i] = CmplxNmbr.getMagnitude(m1[i]);         
      }
      return matrix;     
   }*/  
   public static double[][][] scale(double cn[],double m1[][])
   {
      int r = m1.length;
      int c = m1[0].length;

      double matrix[][][] = new double[r][c][2];

      for(int i=0;i<r;i++)
      {
         for(int j=0;j<c;j++)
         {
             matrix[i][j] = CmplxNmbr.scale(cn,m1[i][j]);
         }
      }
      return matrix;
   }
   public static double[][][] inverse(double sx[][][])
   {
      double one[] = CmplxNmbr.rtvCmplxNmbr(1,0);
      int n = sx.length;
      //System.out.println("n = "+n+", idim = "+idim);
      double esp = Math.pow(1,-9);
      for(int k=0;k<n;k++)
      {
         for(int j=0;j<n;j++)
         {
            if(j != k)
            {
               sx[k][j] = CmplxNmbr.divide(sx[k][j],sx[k][k]);
               //System.out.println("k,j sx["+k+"]["+j+"] = "+sx[k][j]+", sx[k][k] = "+sx[k][k]);
            }
         }
         sx[k][k] = CmplxNmbr.divide(one,sx[k][k]);
         //System.out.println("k,k sx["+k+"]["+k+"] = "+sx[k][k]);
         for(int i=0;i<n;i++)
         {
            if(i != k)
            {
               for(int j=0;j<n;j++)
               {
                  if(j != k)
                  {
                     sx[i][j] = CmplxNmbr.sub(sx[i][j],CmplxNmbr.mltply(sx[k][j],sx[i][k]));
                     //System.out.println("i,j sx["+i+"]["+j+"] = "+sx[i][j]);
                  }
               }
            }
         }
         for(int i=0;i<n;i++)
         {
            if(i != k)
            {
               sx[i][k]  = CmplxNmbr.mltply(CmplxNmbr.scale(sx[i][k],-1),sx[k][k]);
               //System.out.println("i,k sx["+i+"]["+k+"] = "+sx[i][k]);
            }
         }
      }
      return sx;
   }
   public static double[][] mltplyMMxM(double m1[][][],double m2[][])
   {
      int aLength = m1.length;
      double m3[][] = new double[aLength][2];
      for(int i=0;i<aLength;i++)
      {
         for(int t=0;t<aLength;t++)
         {
            for(int j=0;j<aLength;j++)
            {
               m3[i] = CmplxNmbr.add(m3[i],CmplxNmbr.mltply(m1[i][j],m2[j]));
               //System.out.println("m1[i][j]="+m1[i][j]+"m2[j][t]+"+m2[j][t]+",m3[i][t]="+m3[i][t]);
            }
         }
      }
      return m3;
   }

}