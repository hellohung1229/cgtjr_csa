/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.lnralgbra;

/**
 *
 * @author finitesystem
 */
public class BckwrdSub {
    
    private double x[];
    private double y[];
    private double b[];
    private double m[][];
    private double u[][];
    private double sum;    
    
    public void solve(double myMatrix[][],double myB[]){
        int n = myMatrix.length;
        for(int k=n-1;k>=0;k--){
            for(int i=k+1;i<n;i++){
               sum +=myMatrix[k][i]*x[i];       
            }            
            x[k] = myB[k] - sum/myMatrix[k][k];
        }
    }
    public double[] solve(double myMatrix[][],int myPivot[]){
        int n = myMatrix.length;
        double b[] = new double[n];
        double x[] = new double[n];
        int r = 0;
        
        for(int k=n-1;k>=0;k--){
            r = myPivot[k];            
            for(int i=k+1;i<n;i++){
               sum +=myMatrix[r][i]*x[i];       
            }         
            b[r] = myMatrix[r][n];
            x[r] = (b[r] - sum)/myMatrix[r][r];
            sum=0;
        }
        return x;
    }
    public double[] solve(double myMatrix[][]){
        int aRank = myMatrix.length;
        return solve(myMatrix,aRank);
    }
            
    public double[] solve(double myMatrix[][],int myRank){
        
        //int n = myMatrix.length;
        int n = myRank;
        int p = myMatrix[0].length;
        
        double b[] = new double[n];
        double x[] = new double[n];
        
        for(int k=n-1;k>=0;k--){
         
            //try{
            for(int i=k+1;i<n;i++){
               sum +=myMatrix[k][i]*x[i];       
               //System.out.println("k = "+k+", i = "+i);
            }         
            b[k] = 1*myMatrix[k][p-1];
            x[k] = (b[k] - sum)/myMatrix[k][k];
            //System.out.println("x["+k+"]="+x[k]+", b["+k+"]="+b[k]+", sum = "+sum+", matrix["+k+"]["+k+"]="+myMatrix[k][k]);
        //}catch(java.lang.ArrayIndexOutOfBoundsException aiobe){

          //  System.out.println(aiobe.getMessage());
            //System.out.println(aiobe.getStackTrace());            
            //System.out.println("k = "+k+", myMatrix.length = "+myMatrix.length+", myMatrix[0].length "+myMatrix[0].length);
        //}            
            sum=0;
        }
        return x;
    }     
}