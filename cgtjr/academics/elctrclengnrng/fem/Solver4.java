package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.complex.CmplxNmbr;

public class Solver4
{
   private double matrix1[][];
   private double matrix2[][];
   private double matrix3[][];
   private double matrix4[][];
   private double matrixB[][];
   
   private double cmplxnmbr1[];
   private double cmplxnmbr2[];
   private double cmplxnmbr3[];
   private double cmplxnmbr4[];
   
   private int ndp[];
   private double val[];
   private int lf[];
   private double v[][];
   private double mgntd[];

   private int ni;
   private int k;
   private int nf;
   private double sum[];
   private NdlElmnts nodeElmnts;
   private double prscrbNdVl[];
   private int lclSz;
   private int glblSz;
   private double maxValue;
   private double minValue;
   private double matrix[][][];

   public Solver4(NdlElmnts myNdlElmnts,double myMatrix1[][],double myMatrix2[][],double myMatrix3[][],double myMatrix4[][],double myMatrixB[][])
   {

      cmplxnmbr1 = CmplxNmbr.rtvCmplxNmbr(1,0);
      cmplxnmbr2 = CmplxNmbr.rtvCmplxNmbr(1,0);
      cmplxnmbr3 = CmplxNmbr.rtvCmplxNmbr(1,0);
      cmplxnmbr4 = CmplxNmbr.rtvCmplxNmbr(1,0);
      
      matrix1 = myMatrix1;
      matrix2 = myMatrix2;
      matrix3 = myMatrix3;
      matrix4 = myMatrix4;

      matrixB = myMatrixB;

      lclSz = myNdlElmnts.getLclSize();
      glblSz = myNdlElmnts.getNodeCnt();
      matrix = new double[glblSz][glblSz][2];

      nodeElmnts = myNdlElmnts;

      ndp = new int[glblSz];
      val = new double[glblSz];
      lf = new int[glblSz];
      v = new double[glblSz][2];
      mgntd = new double[glblSz];

      ni = 800;//number of iterations
      maxValue = -1*Double.MAX_VALUE;
      minValue = Double.MAX_VALUE;
      System.out.println("Solver4: lclsize = "+ lclSz+", glbSize = "+glblSz);
   }
   public void prcss()
   {
      int aND = nodeElmnts.getNodeCnt();
      int aNP = nodeElmnts.getPrscrbNdCnt();
      this.nf = nodeElmnts.getFrNdCnt();
      this.lf = nodeElmnts.getFrNdIndx();
      System.out.println("Solver4: free = "+this.nf+", np = "+aNP +", nodes = "+aND);

      this.prscrbNdVl = nodeElmnts.getPrscrbNdVls();

      this.ndp = nodeElmnts.getPrscrbNdIndx();

      calculate(aND,aNP);
   }
   public void calculate(int nd,int np)
   {
      double term1[] = null;
      double term2[] = null;
      double term3[] = null;
      double term4[] = null;
      double term5[] = null;
      double term6[] = null;
      double term7[] = null;

      for(int i=0;i<np;i++)
      {
         int glblNmbr = ndp[i];
         double aValue = prscrbNdVl[i];
         v[glblNmbr] = CmplxNmbr.rtvCmplxNmbr(aValue, 0);
         System.out.println("Solver: prescribe v["+glblNmbr+"]="+v[glblNmbr][0]+", j"+v[glblNmbr][1]);
      }
      for(int n = 0;n<ni;n++)
      {
         for(int i = 0;i<nf;i++)
         {
            sum = CmplxNmbr.rtvCmplxNmbr(0,0);
            k = lf[i];
            //System.out.println("Solver: k = "+k+",lf["+i+"]="+lf[i]);
            for(int j=0;j<nd;j++)
            {
               if(j == k)
               {
                  //statement130();
               }else{
                  term1 = CmplxNmbr.scale(cmplxnmbr1,matrix1[k][j]);
                  term2 = CmplxNmbr.scale(cmplxnmbr2,-1*matrix2[k][j]);
                  term3 = CmplxNmbr.scale(cmplxnmbr3,-1*matrix3[k][j]);
                  term4 = CmplxNmbr.scale(cmplxnmbr4,-1*matrix4[k][j]);
                  term5 = CmplxNmbr.add(term1,term2);
                  term6 = CmplxNmbr.add(term3,term4);
                  matrix[k][j] = CmplxNmbr.add(term5,term6);
                  System.out.println("term1 = "+CmplxNmbr.toString(term1));
                  System.out.println("term2 = "+CmplxNmbr.toString(term2));
                  System.out.println("term3 = "+CmplxNmbr.toString(term3));
                  System.out.println("term4 = "+CmplxNmbr.toString(term4));
                  System.out.println("term5 = "+CmplxNmbr.toString(term5));
                  System.out.println("term5 = "+CmplxNmbr.toString(term6));
                  sum = CmplxNmbr.add(sum,CmplxNmbr.mltply(v[j],matrix[k][j]));
                  System.out.println("sum = "+CmplxNmbr.toString(sum));
                  //System.out.println("Solver:"+",v["+j+"]="+v[j]);
               }
            }
            term7 = CmplxNmbr.sub(matrixB[k],sum);
            v[k] = CmplxNmbr.divide(term7,CmplxNmbr.scale(matrix[k][k],-1));
            mgntd[k] = CmplxNmbr.getMagnitude(v[k]);
            updtMinMaxVal(mgntd[k]);
           // System.out.println("Solver: v["+k+"]="+v[k]+",sum = "+sum+",matrix1["+k+"]["+k+"]="+matrix1[k][k]);
         }
      }
   }
   //TODO: create a boundary listener (min max listener)
   public void updtMinMaxVal(double myValue)
   {
      if(myValue < minValue)
      {
         minValue = myValue;
      }
      if(myValue >= maxValue)
      {
         maxValue = myValue;
      }
      System.out.println("Solver4 : myValue = "+myValue+", minValue = "+minValue+", maxValue = "+maxValue);
   }

   public void setScale1(double myScale1[])
   {
      cmplxnmbr1 = myScale1;
   }
   public void setScale2(double myScale2[])
   {
      cmplxnmbr2 = myScale2;
   }
   public void setScale3(double myScale3[])
   {
      cmplxnmbr3 = myScale3;
   }
   public void setScale4(double myScale4[])
   {
      cmplxnmbr4 = myScale4;
   }
   public double getMaxValue()
   {
      return maxValue;
   }
   public double getMinValue()
   {
      return minValue;
   }
   public void imposedPotentialInput(int myIndex,double myValue)
   {
      val[myIndex] = myValue;
   }
   public double[][] getValues()
   {
      return v;
   }
   public double[] getMgntds()
   {
      return mgntd;
   }
}