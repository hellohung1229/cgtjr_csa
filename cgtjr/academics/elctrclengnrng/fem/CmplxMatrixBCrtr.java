package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.elctrclengnrng.fem.*;
import cgtjr.academics.math.complex.CmplxNmbr;
import cgtjr.academics.math.complex.CmplxNmbr;


public abstract class CmplxMatrixBCrtr
{
   private double x[];
   private double y[];
   private double z[];
   private int nl[][];
   private int ndp[];

   private double xl[];
   private double yl[];
   private double zl[];

   private int k;

   private int ir;

   private NdlElmnts nodeElmnts;
   private double prscrbNdVl[];
   private int lclSz;
   private BssFnctn bssFnctn;
   private int glblSz;


   double c[][];
   double ce[][];

   public CmplxMatrixBCrtr(NdlElmnts myNdlElmnts,BssFnctn myBssFnctn)
   {
      bssFnctn  = myBssFnctn;
      lclSz = myNdlElmnts.getLclSize();
      glblSz = myNdlElmnts.getGlblSize();
      nodeElmnts = myNdlElmnts;

      x = new double[glblSz];
      y = new double[glblSz];
      z = new double[glblSz];
      c = new double[glblSz][2];
      ce = new double[lclSz][2];
      nl = new int[glblSz][lclSz];
      ndp = new int[glblSz];
      xl = new double[lclSz];
      yl = new double[lclSz];
      zl = new double[lclSz];
   }
   public double[][] create()
   {
      int aNE = nodeElmnts.getElmntCnt();

      int aND = nodeElmnts.getGlblSize();
      int aNP = nodeElmnts.getPrscrbNdCnt();

      this.nl = nodeElmnts.getElmntLclNds();
      this.prscrbNdVl = nodeElmnts.getPrscrbNdVls();
      this.x = nodeElmnts.getXGlbl();
      this.y = nodeElmnts.getYGlbl();
      this.z = nodeElmnts.getZGlbl();
      this.ndp = nodeElmnts.getPrscrbNdIndx();

      return calculate(aNE,aND,aNP);
   }
   private double[][] calculate(int ne,int nd,int np)
   {
      for(int i = 0;i<ne;i++)
      {
         for(int j = 0;j<lclSz;j++)
         {
            k = nl[i][j];
            xl[j] = x[k];
            yl[j] = y[k];
            zl[j] = z[k];
            //System.out.println("Solver: element = "+i+" xl["+j+"]="+xl[j]+",yl["+j+"]="+yl[j]+",zl["+j+"]="+zl[j]);
         }
         bssFnctn.setBssNds(xl,yl,zl);
         for(int m=0;m<lclSz;m++)
         {
            ce[m] = cmptFnctn(m);
         }
         for(int j=0;j<lclSz;j++)
         {
            ir = nl[i][j];
            c[ir] = CmplxNmbr.add(c[ir],ce[j]);
            //System.out.println("Solver:  c["+ir+"]["+ic+"]="+ c[ir][ic]+",ce["+j+"]["+l+"]="+ce[j][l]);
         }
      }
      return c;
   }
   public abstract double[] cmptFnctn(int i);

   public double[][] getMatrix()
   {
       return c;
   }
   public BssFnctn getBssFnctn()
   {
      return bssFnctn;
   }
}