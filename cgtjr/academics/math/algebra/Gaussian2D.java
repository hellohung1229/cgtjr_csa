/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.algebra;

/**
 *
 * @author cgthomasjr
 */
public class Gaussian2D {
    private double meanX;
    private double meanY;
    private double x;
    private double y;
    
    private double sigma;

    public Gaussian2D(double myMeanX,double myMeanY,double mySigma){
        meanX = myMeanX;
        meanY = myMeanY;
        sigma = mySigma;
    }
    public double getMeanX() {
        return meanX;
    }

    public void setMeanX(double meanX) {
        this.meanX = meanX;
    }

    public double getMeanY() {
        return meanY;
    }

    public void setMeanY(double meanY) {
        this.meanY = meanY;
    }

    public double getSigma() {
        return sigma;
    }

    public void setSigma(double stndrdDvtn) {
        this.sigma = stndrdDvtn;
    }
    
    public double computeGssn(double myX,double myY){
        double x2 = (myX-meanX)*(myX-meanX);
        double y2 = (myY-meanY)*(myY-meanY);
        
        double answer = Math.exp(-(x2+y2)/(2*sigma*sigma));
        return answer;
    }
}