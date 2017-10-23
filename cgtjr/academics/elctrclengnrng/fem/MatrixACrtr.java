package cgtjr.academics.elctrclengnrng.fem;


public class MatrixACrtr
{
   private NdlElmnts nodeElmnts;
   private BssFnctn bssFnctn;

   private int k;
   private double x[];
   private double y[];
   private double z[];
   private double c[][];
   private double ce[][];
   private int nl[][];
   private int ir;
   private int ic;
   private double xl[];
   private double yl[];
   private double zl[];

   private int lclSz;
   private int glblSz;
   
   public MatrixACrtr(NdlElmnts myNdlElmnts,BssFnctn myBssFnctn)
   {
      bssFnctn  = myBssFnctn;
      lclSz = myNdlElmnts.getLclSize();
      glblSz = myNdlElmnts.getGlblSize();
      nodeElmnts = myNdlElmnts;

      x = new double[glblSz];
      y = new double[glblSz];
      z = new double[glblSz];   
      c = new double[glblSz][glblSz];
      ce = new double[glblSz][glblSz];
      nl = new int[glblSz][lclSz];

      xl = new double[lclSz];
      yl = new double[lclSz];
      zl = new double[lclSz];
   }
   public double[][] create()
   {
      int aNE = nodeElmnts.getElmntCnt();

      int aND = nodeElmnts.getNodeCnt();
      int aNP = nodeElmnts.getPrscrbNdCnt();


      this.nl = nodeElmnts.getElmntLclNds();

      this.x = nodeElmnts.getXGlbl();
      this.y = nodeElmnts.getYGlbl();
      this.z = nodeElmnts.getZGlbl();
      //System.out.println("MatrixACrtr: lclsz = "+lclSz+", global size = "+glblSz+",np = "+aNP +", nodes = "+aND+" ne = "+aNE+" ,nl length = "+nl.length);
      return create(aNE,aNP);
   }
   private double[][] create(int ne,int np)
   {

      for(int i = 0;i<ne;i++)
      {
         for(int j = 0;j<lclSz;j++)
         {
            k = nl[i][j];
            xl[j] = x[k];
            yl[j] = y[k];
            zl[j] = z[k];

            //System.out.println("Solver: lclSz = "+lclSz +", element = "+i+" xl["+j+"]="+xl[j]+",yl["+j+"]="+yl[j]+",zl["+j+"]="+zl[j]);
         }
         //bssFnctn.setBssNds(xl[0],xl[6],yl[0],yl[6],zl[6],zl[0]);

         bssFnctn.setBssNds(xl,yl,zl);
         for(int m=0;m<lclSz;m++)
         {
            for(int n=0;n<lclSz;n++)
            {
               ce[m][n] = bssFnctn.cmptCffcntMtrx(m,n);
               //System.out.println("MatrixACtr: ce["+m+"]["+n+"] = "+ce[m][n]);
            }
         }
         for(int j=0;j<lclSz;j++)
         {
            ir = nl[i][j];
            for(int l=0;l<lclSz;l++)
            {
               ic = nl[i][l];
               c[ir][ic] = c[ir][ic] + ce[j][l];
               //System.out.println("Solver:  c["+ir+"]["+ic+"]="+ c[ir][ic]+",ce["+j+"]["+l+"]="+ce[j][l]);
            }
         }
      }

      return c;
   }
   public double[][] getMatrix()
   {
       return c;
   }
}