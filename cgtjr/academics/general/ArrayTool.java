package cgtjr.academics.general;



public class ArrayTool
{
   public static final String type = "ArrayTool";

   public ArrayTool()
   {
   }
   public static int[][] retrieveTwoDArray(int aOneDArray[],int aWidth,int aHeight)
   {  

      int lengthIndex = 0;
      int myTwoDArray[][] = new int[aHeight][aWidth];      

         for(int row = 0;row<aHeight;row++)
         { 
            for(int col=0;col<aWidth;col++)
            {                 
               myTwoDArray[row][col] = aOneDArray[lengthIndex];
               lengthIndex++;
            }
         }
      
      return myTwoDArray;
   }
   public static double[] retrieveOneDArray(double aTwoDArray[][])
   {  
      int aWidth = aTwoDArray[0].length;
      int aHeight = aTwoDArray.length;
      int aLength = aWidth * aHeight;
      int lengthIndex = 0;
      double myOneDArray[] = new double[aLength];     

      for(int row = 0;row<aHeight;row++)
      {   
         for(int col=0;col<aWidth;col++)
         {                
            myOneDArray[lengthIndex] = aTwoDArray[row][col];
            lengthIndex++;
         }
      }
      return myOneDArray;
   }
   public static int[] rtrv1DIntArry(double aTwoDArray[][],double myThrshld)
   {
      int aWidth = aTwoDArray[0].length;
      int aHeight = aTwoDArray.length;
      int aLength = aWidth * aHeight;
      int lengthIndex = 0;
      double aValue = 0;
      int a1DArray[] = new int[aLength];
      
      //System.out.println("ArrayTool: width = "+aWidth+", height = "+aHeight);
              
      for(int row = 0;row<aHeight;row++)
      {   
         for(int col=0;col<aWidth;col++)
         {              
            aValue = aTwoDArray[row][col];
            if(aValue != 0)
            {
               a1DArray[lengthIndex] = 0x00000000;
            }else{
               //a1DArray[lengthIndex] = 0x00f0f0f0;               
               a1DArray[lengthIndex] = 0x00ffffff;                             
            }            
            lengthIndex++;
         }
      }
      return a1DArray;
   }
   public static int[] retrieveOneDArray(int aTwoDArray[][])
   {  
      int aWidth = aTwoDArray[0].length;
      int aHeight = aTwoDArray.length;
      int aLength = aWidth * aHeight;
      int lengthIndex = 0;
      int myOneDArray[] = new int[aLength];     

      for(int row = 0;row<aHeight;row++)
      {
         for(int col=0;col<aWidth;col++)
         {                   
            myOneDArray[lengthIndex] = aTwoDArray[row][col];
            //System.out.println("myOneDArray["+lengthIndex+"] = "+myOneDArray[lengthIndex]+"aTwoDArray["+col+"]["+row+"]"+aTwoDArray[col][row]);
            lengthIndex++;
         }
      }
      return myOneDArray;
   }
   public static double[][] retrieveTwoDArray(double myData4D[][][][])
   {
      int T = myData4D[0][0][0].length;
      int R = myData4D[0][0].length;
      int N = myData4D[0].length;
      int M = myData4D.length;
      int a = 0;
      int b = 0;
      
      double output[][] = new double[M*R][N*T];

      for(int v=0;v<N;v++)
      {
         for(int u=0;u<M;u++)
         {
            for(int x=0;x<R;x++,a=v*8)
            {               
               for(int y=0;y<T;y++,b=u*8)
               {
                  output[x+a][y+b] = myData4D[u][v][x][y];
               }
               System.out.println("\n");
            }
            System.out.println("\n");
         }
         System.out.println("\n");
      }
      return output;
   }   
}