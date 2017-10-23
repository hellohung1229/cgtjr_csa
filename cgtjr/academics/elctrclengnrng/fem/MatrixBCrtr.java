package cgtjr.academics.elctrclengnrng.fem;


public class MatrixBCrtr
{
   private double x[];
   private double y[];
   private double z[];
   private double c[];
   private double ce[];
   private int nl[][];

   private double xl[];
   private double yl[];
   private double zl[];

   private int k;
   private int ir;
   private int nf;

   private NdlElmnts nodeElmnts;
   private double prscrbNdVl[];
   private int lclSz;
   private BssFnctn bssFnctn;
   private int glblSz;

   public MatrixBCrtr(NdlElmnts myNdlElmnts,BssFnctn myBssFnctn)
   {
      //bssFnctn = new BssFnctn();

      bssFnctn  = myBssFnctn;
      lclSz = myNdlElmnts.getLclSize();
      glblSz = myNdlElmnts.getNodeCnt();
      nodeElmnts = myNdlElmnts;

      x = new double[glblSz];
      y = new double[glblSz];
      z = new double[glblSz];   
      c = new double[glblSz];
      ce = new double[lclSz];
      nl = new int[glblSz][lclSz];

      xl = new double[lclSz];
      yl = new double[lclSz];
      zl = new double[lclSz];

   }
   public void prcss()
   {
      int aNE = nodeElmnts.getElmntCnt();

      int aND = nodeElmnts.getNodeCnt();
      int aNP = nodeElmnts.getPrscrbNdCnt();
      this.nf = nodeElmnts.getFrNdCnt();
      System.out.println("MatrixBCrtr: np = "+aNP +", nodes = "+aND);
      this.nl = nodeElmnts.getElmntLclNds();
      this.prscrbNdVl = nodeElmnts.getPrscrbNdVls();
      this.x = nodeElmnts.getXGlbl();
      this.y = nodeElmnts.getYGlbl();
      this.z = nodeElmnts.getZGlbl();

      calculate(aNE,aND,aNP);
   }
   public void calculate(int ne,int nd,int np)
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
            c[ir] = c[ir] + ce[j];
            //System.out.println("Solver:  c["+ir+"]["+ic+"]="+ c[ir][ic]+",ce["+j+"]["+l+"]="+ce[j][l]);            
         }
      }
      System.out.println("nd = "+nd+", ne = "+ne+", np = "+np);
   }
   protected double cmptFnctn(int m)
   {
      return ((LineBss)bssFnctn).cmptBssFnctn(m);
   }
   public BssFnctn getBssFnctn()
   {
      return bssFnctn;
   }
   public double[] getMatrix()
   {
       return c;
   }
}