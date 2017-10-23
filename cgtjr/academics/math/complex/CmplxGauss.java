/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.complex;

/**
 *
 * @author clayton g thomas jr
 */
public class CmplxGauss
{
   private int tmp;
   private int jtmp;
   private double a[][][];
   private double b[][];
   private double x[][];
   private double sum[];

   private double c;
   private double cmax;

   private int p[];
   private int pk;
   private int ia;
   private int n;
   private int ijob;

   public void cgauss(double aTmp[][][], double bTmp[][])
   {
      int iaTmp = aTmp.length;
      int nTmp = aTmp.length;
      int ijobTmp = 0;
      int pTmp[] = new int[nTmp];
      x = new double[nTmp][2];
   }
   public double[][] getX()
   {
      return x;
   }
   public void cgauss(double aTmp[][][],int iaTmp,int nTmp, double bTmp[][],int ijobTmp,int pTmp[])
   {
      a = aTmp;
      ia = iaTmp;
      n = nTmp;
      b = bTmp;
      ijob = ijobTmp;
      p = pTmp;

      if(ijob == 1 )
      {
         statement5();
      }
      for(int i=0;i<n;i++)
      {
          p[i] = i;
          x[i] = CmplxNmbr.rtvCmplxNmbr(0,0);
          for(int j=0;j<n;j++)
          {
              double abs1 = CmplxNmbr.getMagnitude(x[i]);
              double abs2 = CmplxNmbr.getMagnitude(a[i][j]);
              double max = Math.max(abs1,abs2);
              x[i] = CmplxNmbr.rtvCmplxNmbr(max,0);
          }
      }
      for(int k=0;k<n-1;k++)
      {
         cmax = 0;
         for(int i=k;i<n;k++)
         {
            double mag1 = CmplxNmbr.getMagnitude(a[p[i]][k]);
            double mag2 = CmplxNmbr.getMagnitude(x[p[i]]);
            c = mag1/mag2;
            if(c <= cmax)
            {

            }else{
               jtmp = i;
               cmax = c;
            }
         }
         if(cmax > 0 && tmp<2)
         {
            statement9();
            tmp++;
         }else if(cmax > 0 && tmp>=2)
         {
            statement3(jtmp,k);
            tmp++;
         }
         statement3(jtmp,k);
         for(int i = k;i<n;i++)
         {
            sum = CmplxNmbr.divide(a[p[i]][k],x[p[i]]);
            a[p[i]][k] = sum;
            for(int j=k+1;j<n;j++)
            {
               a[p[i]][j] = CmplxNmbr.sub(a[p[i]][j],CmplxNmbr.mltply(sum,a[pk][j]));
            }
            if(tmp%2 == 0)
            {
               tmp = 0;
            }
         }
         /*
         for(int j=0;j<n-1;j++)
         {
            for(int i=j+1;i<n;i++)
            {
               b[p[i]] = CmplxNmbr.sub(b[p[i]],CmplxNmbr.mltply(a[p[i]][j],b[p[j]]));
            }
         }*/

      }
      
      statement5();
      x[n] = CmplxNmbr.divide(b[p[n]],a[p[n]][n]);
      for(int i=0;i<n-1;i++)
      {
         sum = b[p[n-i]];
         for(int j=n-i+1;j<n;j++)
         {
            sum = CmplxNmbr.sub(sum,CmplxNmbr.mltply(a[p[n-i]][j],x[j]));
         }
         x[n-i] = CmplxNmbr.divide(sum,a[p[n-i]][n-i]);
      }
   }
   public void statement3(int j,int k)
   {
       pk = p[j];
       p[j] = p[k];
       p[k] = pk;
   }
   public void statement9()
   {
      ijob = -1;
      return;
   }
   public void statement5()
   {
      for(int j=0;j<n-1;j++)
      {
          for(int i=j+1;i<n;i++)
          {
             b[p[i]] = CmplxNmbr.sub(b[p[i]],CmplxNmbr.mltply(a[p[i]][j],b[p[j]]));
          }
      }
   }
}

