/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.lnralgbra;

/**
 *
 * @author Nit
 */
public class QRTrnspseGrmSchmdt {

    private double rTF[][];
    private double qTF[][];
    private double r[][];
    private double q[][];    

    public void compute3x3(double myMatrix[][]) {
        //Matrix.print(myMatrix);
   
        double matrixF[][] = Matrix.flipud3x3(myMatrix);
        double matrixFT[][] = Matrix.transpose3x3(matrixF);
        
        GramSchmidt aGramSchmidt = new GramSchmidt();
        aGramSchmidt.cmpteQRByGrmSchmdt(matrixFT);
        r = aGramSchmidt.retrieveR();
        q = aGramSchmidt.retrieveQ();

        double rT[][] = Matrix.transpose3x3(r);
        double qT[][] = Matrix.transpose3x3(q);

        rTF = Matrix.flipud3x3(rT);
        qTF = Matrix.flipud3x3(qT);
        
        //Matrix.print(r);
        //Matrix.print(q);
        //Matrix.print(rT);
        //Matrix.print(qT);
    }
    public void compute3x4(double myMatrix[][]) {
        //Matrix.print(myMatrix);
   
        double matrixF[][] = Matrix.flipud3x4(myMatrix);
        double matrixFT[][] = Matrix.transpose3x4(matrixF);
        
        GramSchmidt aGramSchmidt = new GramSchmidt();
        aGramSchmidt.cmpteQRByGrmSchmdt(matrixFT);
        double r[][] = aGramSchmidt.retrieveR();
        double q[][] = aGramSchmidt.retrieveQ();

        double rT[][] = r;//Matrix.transpose3x3(r);
        double qT[][] = Matrix.transpose4x3(q);

        rTF = Matrix.flipud3x3(rT);
        qTF = Matrix.flipud3x4(qT);
       
        /*
        //Matrix.print(r);
        //Matrix.print(q);
        //Matrix.print(rTF);
        //Matrix.print(qTF);
        */ 
    }    

    public double[][] getRTF() {
        return rTF;
    }

    /*
    public void setRTF(double[][] myRTF) {
        this.rTF = myRTF;
    }*/

    public double[][] getQTF() {
        return qTF;
    }
    /*
    public void setQTF(double[][] myQTF) {
        this.qTF = myQTF;
    }*/

    public double[][] getR() {
        return r;
    }
    
    /*
    public void setR(double[][] myR) {
        this.r = myR;
    }*/

    public double[][] getQ() {
        return q;
    }

    /*
    public void setQ(double[][] myQ) {
        this.q = myQ;
    }*/    
}