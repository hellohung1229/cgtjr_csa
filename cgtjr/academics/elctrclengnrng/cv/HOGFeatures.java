/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

/**
 *
 * @author finitesystem
 */

import java.lang.Math;

public class HOGFeatures {
    
    private double[] histogram;
    private int numberOfBins;
    private double binSize;

    /**
     * @param nbins -- number of bins (one for each orientation)
     */
    public HOGFeatures(int nbins) {
        numberOfBins = nbins;
        histogram = new double[2*nbins];
        this.binSize = 2*Math.PI/nbins;
    }

    // default with 8 bins
    public HOGFeatures() {
        this(8);
    }
    public static HOGFeatures[][] retrieveHOGGrid(int myXBlocks,int myYBlocks){
        HOGFeatures HOGGrid[][] = new HOGFeatures[myXBlocks][myYBlocks];
        return HOGGrid;
    }
    public void insert(double phi, double gradient) {

        double aPhiAngle = phi;
        /*
        if(phi >= 2*Math.PI) {
            aPhiAngle = phi%(2*Math.PI);
        }*/
        if(phi < 0){
            aPhiAngle = 2*Math.PI+phi;
        }

        int binIndex;
        binIndex = (int)(aPhiAngle/binSize);
        
        //histogram[binIndex] += gradient;

        histogram[binIndex] += (gradient/(2*361));
        //System.out.println("HistogramOG: phi = "+phi+", modified phi = "+aPhiAngle+", binSize = "+binSize+", binIndex = "+binIndex+", nmbrBins = "+numberOfBins+", gradient = "+gradient+", histogram["+binIndex+"] = "+histogram[binIndex]);
        
        /*
        // distant to the center of current bin
        d = phi/binSize - (binIndex+0.5);
         
        // adjacent bin
        int j = 0; 
       
        int length = histogram.length;
        if(d<= 0) 
            j = ((binIndex-1)%length + length)%length; // java modolu is crappy
        else 
            j = (binIndex+1)%length;

        double w0,w1;

        // compute weights 

        w1 = Math.abs(d); // weight for adjacent bin
        w0 = 1-w1;

        histogram[binIndex] += w0*gradient;
        histogram[j] += w1*gradient;
        */ 
    }


    public double getMaxGradient() {
        double max = 0.0;
        for(int i=0;i<numberOfBins;i++) {
            if(histogram[i] > max) max = histogram[i];
        }
        return max;
    }
    public double getGradientByIndex(int bin) {
        return histogram[bin];
    }
    public double getGradientByAngle(double phi) {
        double aPhiAngle = phi;        
        if(phi < 0){
            aPhiAngle = 2*Math.PI+phi;
        }
        int binIndex;
        binIndex = (int)(aPhiAngle/binSize);        
        return histogram[binIndex];
    }
    public double computeBinAngle(double myAngle){
        double aPhiAngle = myAngle;
        /*
        if(phi >= 2*Math.PI) {
            aPhiAngle = phi%(2*Math.PI);
        }*/
        if(myAngle < 0){
            aPhiAngle = 2*Math.PI+myAngle;
        }

        int binIndex = (int)(aPhiAngle/binSize);
        double angle = (binIndex*binSize);
        return angle;
            
    }
    public double[] getHistogram() {
        return histogram;
    }
    public int getNumberOfBins() {
        return numberOfBins;
    }
    public double getBinSize() {
        return binSize;
    }
}