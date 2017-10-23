
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.lnralgbra;

/**
 *
 * @author Nit
 */
public class GssnElmntnTest 
{
   private static double matrix1[][] = {{5,1,3,1,4,7,9,1,20},
                      {10,2,3,4,1,2,6,8,2},
                      {2,1,1,4,8,1,2,6,5},
                      {1,2,4,5,1,8,1,2,8},
                      {1,1,2,1,10,1,8,1,10},
                      {1,2,1,4,1,4,7,4,1},
                      {5,10,10,1,9,10,4,1,3},
                      {7,8,3,1,1,4,1,8,1},
                      {8,10,4,10,9,1,10,1,9}};
   private static double matrix2[][] = {
 {104,  55,1,0,0,0,-9256,-4895, -89}
       ,{0,0,0,-104, -55,  -1,5720,3025,  55}
, {243,  54,1,0,0,0,-55404,-12312,-228}
,{0,0,0,-243, -54,  -1,13122,2916,  54}
,  {77, 176,1,0,0,0,-4774,-10912, -62}
,{0,0,0, -77,-176,  -1,13552,30976, 176}
, {245, 171,1,0,0,0,-56350,-39330,-230}
,{0,0,0,-245,-171,  -1,41895,29241, 171}};
;
   private static double matrix7[][] = {

       {0,0,0,-104, -55,  -1,5720,3025,  55}
 ,{104,  55,1,0,0,0,-9256,-4895, -89}       
,{0,0,0,-243, -54,  -1,13122,2916,  54}
           , {243,  54,1,0,0,0,-55404,-12312,-228}
,{0,0,0, -77,-176,  -1,13552,30976, 176}
,  {77, 176,1,0,0,0,-4774,-10912, -62}
,{0,0,0,-245,-171,  -1,41895,29241, 171}
, {245, 171,1,0,0,0,-56350,-39330,-230}

   };
   
   private static double matrix8[][] = {

       {0,0,0,-104, -55,  -1,5720,3025,  -55}
 ,{104,  55,1,0,0,0,-9256,-4895, 89}       
,{0,0,0,-243, -54,  -1,13122,2916,  -54}
           , {243,  54,1,0,0,0,-55404,-12312,228}
,{0,0,0, -77,-176,  -1,13552,30976, -176}
,  {77, 176,1,0,0,0,-4774,-10912, 62}
,{0,0,0,-245,-171,  -1,41895,29241, -171}
, {245, 171,1,0,0,0,-56350,-39330,230}

   };   
 
   //private static double matrix4[][] = {{2,3,1,-4},{0,-5,2,17},{0,0,4.3,4.3}};

   private static double matrix4[][] = {{2,3,1,-4},{4,1,4,9},{3,4,6,0}};   
   
   public static void main(String args[])
   {
       
             GssnElmntn aGssnElmntn = new GssnElmntn(); 
      //Matrix.print(matrix8);
      aGssnElmntn.process(matrix8);
      //Matrix.print(matrix8); 
      int aPivot[] = aGssnElmntn.getPivot();
      //Matrix.print(aPivot);
      
       
       
      //Matrix.print(matrix8);
       GssnElmntn aGssnElmntn2 = new GssnElmntn(); 
      double a[][] = aGssnElmntn2.process(matrix8);
      int aPivot2[] = aGssnElmntn2.getPivot();
      //Matrix.print(matrix8);
      //Matrix.print(aPivot2);
      
      BckwrdSub aBckwrdSub = new BckwrdSub();
      double x[] = aBckwrdSub.solve(a);
      //Matrix.print(x);
      
   }   
}