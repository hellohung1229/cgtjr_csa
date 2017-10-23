package cgtjr.academics.elctrclengnrng.cv.activecontour;

public class Drvtv1Krnl
{
   private int hrzntlKrnl[][];
   private int vrtclKrnl[][];
   private int defaultWidth = 3;
   private int defaultHeight = 3;
   private int scale;   

   public Drvtv1Krnl()
   {
      setScale(2);
      initializeGradient();
   }
   public void setScale(int myScale)
   {
      scale = myScale;
   }
   public int getScale()
   {
      return scale;
   }
   public void initializeGradient()
   {
      hrzntlKrnl = new int[defaultWidth][defaultHeight];          
      vrtclKrnl = new int[defaultWidth][defaultHeight];  

      hrzntlKrnl[0][0] = 0;
      hrzntlKrnl[0][1] = 0;
      hrzntlKrnl[0][2] = 0;
      hrzntlKrnl[1][0] = -1;
      hrzntlKrnl[1][1] = 1;
      hrzntlKrnl[1][2] = 0;
      hrzntlKrnl[2][0] = 0;
      hrzntlKrnl[2][1] = 0;
      hrzntlKrnl[2][2] = 0;
 
      vrtclKrnl[0][0] = 0;                 
      vrtclKrnl[0][1] = -1;                 
      vrtclKrnl[0][2] = 0;                 
      vrtclKrnl[1][0] = 0;                 
      vrtclKrnl[1][1] = 1;
      vrtclKrnl[1][2] = 0;                 
      vrtclKrnl[2][0] = 0;                 
      vrtclKrnl[2][1] = 0;
      vrtclKrnl[2][2] = 0;                 
   }
   public int[][] getHrzntlKrnl()
   {
      return hrzntlKrnl;
   }
   public int[][] getVrtclKrnl()
   {
      return vrtclKrnl;
   }
}