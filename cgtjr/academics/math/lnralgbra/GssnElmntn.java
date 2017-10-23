/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.lnralgbra;

/**
 *
 * @author finitesystem
 */
public class GssnElmntn {

    private int pivot[];
    private int slctdPivot;

    private void inititalize() {
        pivot = new int[8];
        pivot[0] = 0;
        pivot[1] = 1;
        pivot[2] = 2;
        pivot[3] = 3;
        pivot[4] = 4;
        pivot[5] = 5;
        pivot[6] = 6;
        pivot[7] = 7;
        //pivot[8] = 8;        
    }
    private void inititalize(int n) {
        pivot = new int[n];
        for(int i=0;i<n;i++){
           pivot[i] = i;
        }
        //pivot[8] = 8;        
    }
    public int[] getPivot(){
        return pivot;
    }
    private int selectPivot(int myPivot[], double myMatrix[][], int myRow, int myCol) {
        double aValueTmp = 0;
        //double aValue = -Integer.MAX_VALUE;
        int aRowNmbr = myPivot[myRow];
        double aValue = myMatrix[aRowNmbr][myCol];
        int rIndex = myRow;
        int aPivot = 0;
        for (int r = myRow; r < myMatrix.length; r++) {
            aPivot = myPivot[r];
            aValueTmp = myMatrix[aPivot][myCol];
            if (Math.abs(aValueTmp) >= Math.abs(aValue)) {
                aValue = aValueTmp;
                // rIndex = aPivot;
                rIndex = r;
            }
        }
        return rIndex;
    }

    private void exchangePivot(int myPivot[], int myIndex1, int myIndex2) {
        int tmpValue = 0;
        tmpValue = myPivot[myIndex1];
        myPivot[myIndex1] = myPivot[myIndex2];
        myPivot[myIndex2] = tmpValue;
    }

    public double[][] rtrvePivotdMatrix(int myPivot[], double myMatrix[][]) {
        double aMatrix[][] = new double[myMatrix.length][myMatrix[0].length];
        for (int i = 0; i < myMatrix.length; i++) {
            int pi = myPivot[i];
            for (int j = 0; j < myMatrix[0].length; j++) {
                aMatrix[i][j] = myMatrix[pi][j];
            }
        }
        return aMatrix;
    }

    public double[][] process(double myMatrix[][]) {
        int aLength1 = myMatrix[0].length;
        int aLength2 = myMatrix.length;        

        inititalize(aLength2);
        double multiplier[][] = new double[aLength1][aLength1];

        for (int i = 0; i < aLength2; i++) {
            //int p = pivot[i];
            int currentRow = myMatrix.length - i + 1;
            int p = selectPivot(pivot, myMatrix, i, i);
            int pi = pivot[p];
            exchangePivot(pivot, i, p);

            for (int k = i + 1; k < aLength2; k++) {
                int pk = pivot[k];
                if(myMatrix[pk][i] == 0.0){
                    multiplier[pk][i] = 0.0;
                }else{
                   multiplier[pk][i] = myMatrix[pk][i] / myMatrix[pi][i];
                }
                for (int j = i ; j < aLength1; j++) {
                    //System.out.println("myMatrix["+pk+"]["+i+"] = "+myMatrix[pk][i]);
                    //System.out.println("myMatrix["+pi+"]["+i+"] = "+myMatrix[pi][i]);                    
                    //System.out.println("multiplier["+pk+"]["+i+"] = "+multiplier[pk][i]);                    
                    myMatrix[pk][j] = myMatrix[pk][j] - multiplier[pk][i] * myMatrix[pi][j];

                    //System.out.println("myMatrix["+p+"]["+j+"] = "+myMatrix[p][j]); 
                    //System.out.println("multiplier["+pk+"]["+i+"] * myMatrix["+p+"]["+j+"] = "+multiplier[pk][i] * myMatrix[p][j]);                     
                    //System.out.println(" myMatrix["+pk+"]["+j+"] = "+"myMatrix["+pk+"]["+j+"] - multiplier["+pk+"]["+i+"] * myMatrix[p][j] ");                
                }
                //System.exit(0);
            }
        }
        //Matrix.print(pivot);
        double aMatrix[][] = rtrvePivotdMatrix(pivot, myMatrix);
        //Matrix.print(aMatrix);
        return aMatrix;
    }
}