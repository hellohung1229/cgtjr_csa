/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.quadrature;

/**
 * The GssLgndr (Gauss Legendre) class is responsible for computing the 
 * numerical quadrature.  The rules require both sampling points and 
 * weighting functions.  Currently three and five point sampling is implemented.
 * Single, double, and triple integration is supported.  This approach has been
 * optimized to deal only with orthogonal regions.
 * @author clayton g thomas jr
 */
public class GssLgndr {

    Integration qdrtrIntrfc;
    private static double smplPnts5[] = {-0.9061798,
        -0.5384693,
        0.0000000,
        0.5384693,
        0.9061798};
    private static double smplWghts5[] = {0.2369268,
        0.4786286,
        0.5688888,
        0.4786286,
        0.2369268};
    private static double smplPnts3[] = {-0.7745966,
        0.0000000,
        0.7745966};
    private static double smplWghts3[] = {0.5555555,
        0.8888888,
        0.5555555};
    private int nmbrPnts = 5;
    private double xMax = 3;
    private double xMin = -1;
    private double yMax = 6;
    private double yMin = 0;
    private double zMax = 4;
    private double zMin = -4;
    private double intlX = 0;
    private double intlY = 0;
    private double intlZ = 0;

    protected GssLgndr() {
    }

    /**
     * The constructor instantiates a GssLgndr (Gauss Legendre) object.
     * @param myQuadrature The parameter is an interface of type Integration.
     */
    
    public GssLgndr(Integration myQuadrature) {
        qdrtrIntrfc = myQuadrature;
    }
    public double[] getSmplPnts5()
    {
       return smplPnts5;
    }
    public double[] getSmplWghts5()
    {
       return smplWghts5;
    }    
    /**
     * The function is responsible for getting the initial x value.
     * @return The method returns type double.
     */
    public double getIntlX() {
        return intlX;
    }

    /**
     * The getXMax (get maximum x) method returns the maximum x value.  This maximum 
     * represents the upper limit of integration.
     * @return The function returns type double.
     */
    public double getXMax() {
        return xMax;
    }

    /**
     * The getYMax (get maximum y) function returns the maximum y value.  This maximum
     * represents the upper limit of integration.
     * @return The method returns type double.
     */
    public double getYMax() {
        return yMax;
    }

    /**
     * The getXMin (get minimum x) function returns the minimum x value.  This 
     * value represents the lower limits of integration.
     * @return The method returns type double.
     */
    public double getXMin() {
        return xMin;
    }

    /**
     * The getYMin (get minimum y) function returns the minimum y value.  The minimum
     * value represents the lower limit of integration.
     * @return The method returns type double.
     */
    public double getYMIn() {
        return yMin;
    }

    /**
     * The setIntlX (set initial x) method sets the initial x value to the value 
     * of the parameter.
     * @param myX The parameter is of type double.
     */
    public void setIntlX(double myX) {
        intlX = myX;
    }

    /**
     * The setIntlY (set initial y) method sets the initial y value to the value 
     * of the parameter.
     * @param myY The parameter is of type double.
     */
    public void setIntlY(double myY) {
        intlY = myY;
    }

    /**
     * The setIntlZ (set initial z) method sets the initial z value to the value 
     * of the parameter.
     * @param myZ The parameter is of type double.
     */
    public void setIntlZ(double myZ) {
        intlZ = myZ;
    }

    /**
     * The setXMax (set maximum x) method sets the maximum x value to the value 
     * of the parameter.  This value represents the upper limit of integration.
     * @param myXmax The parameter is of type double.
     */
    public void setXMax(double myXMax) {
        xMax = myXMax;
    }

    /**
     * The setXMin (set minimum x) method sets the minimum x value to the value 
     * of the parameter.  This value represents the lower limit of integration.
     * @param myXMin The parameter is of type double.
     */
    public void setXMin(double myXMin) {
        xMin = myXMin;
    }

    /**
     * The setXMax (set maximum y) method sets the maximum y value to the value 
     * of the parameter.  This value represents the upper limit of integration.
     * @param myYMax The parameter is of type double.
     */
    public void setYMax(double myYMax) {
        yMax = myYMax;
    }

    /**
     * The setYMin function is responsible for setting the lower limit associated 
     * with the y boundary.
     * @param myYMin The parameter represents the lower y bound.
     */
    public void setYMin(double myYMin) {
        yMin = myYMin;
    }

    /**
     * The setZMax function is responsible for setting the upper limit of 
     * integration associated with the z boundary conditions.
     * @param myZMax The parameter is of type double.
     */
    public void setZMax(double myZMax) {
        zMax = myZMax;
    }

    /**
     * The setZMin function is responsible for setting the lower limit of
     * integration associated with the z boundary conditions
     * @param setZMin The parameter is of type double.
     */
    public void setZMin(double myZMin) {
        zMin = myZMin;
    }

    /**
     * This function performs a single integration, then returns the results.
     * @return The results are of type double.
     */
    public double intgrt1D() {
        double smplPntX = 0;
        double smplWghtX = 0;

        double rslt1 = 0;

        for (int i = 0; i < nmbrPnts; i++) {
            smplPntX = intlX + (xMax - xMin) / 2 - ((xMax - xMin) / 2) * smplPnts5[i];
            smplWghtX = (xMax - xMin) / 2 * smplWghts5[i];
            rslt1 += smplWghtX * cmptFnctn(smplPntX);
        }
        //System.out.println("rslt = "+rslt);
        return rslt1;
    }

    /**
     * The function performs a double integration.
     * @return The results is of type double.
     */
    public double intgrt2D() {
        double smplPntX = 0;
        double smplWghtX = 0;
        double smplPntY = 0;
        double smplWghtY = 0;
        double rslt = 0;

        for (int i = 0; i < 3; i++) {
            smplPntX = (xMax - xMin) / 2 - ((xMax - xMin) / 2) * smplPnts3[i];
            smplWghtX = (xMax - xMin) / 2 * smplWghts3[i];

            for (int j = 0; j < 3; j++) {
                smplPntY = (yMax - yMin) / 2 - ((yMax - yMin) / 2) * smplPnts3[j];
                smplWghtY = (yMax - yMin) / 2 * smplWghts3[j];
                //System.out.println("GaussLgndr: x="+smplPntX+",y="+smplPntY);
                rslt += smplWghtX * smplWghtY * cmptFnctn(smplPntX, smplPntY);
            }
        }
        //System.out.println("rslt = "+rslt);
        return rslt;
    }

    /**
     * The function performs a triple integration.
     * @return The results are of type double.
     */
    public double intgrt3D() {
        double smplPntX = 0;
        double smplWghtX = 0;
        double smplPntY = 0;
        double smplWghtY = 0;
        double smplPntZ = 0;
        double smplWghtZ = 0;
        double rslt1 = 0;

        for (int i = 0; i < nmbrPnts; i++) {
            smplPntX = intlX + (xMax - xMin) / 2 - ((xMax - xMin) / 2) * smplPnts5[i];
            smplWghtX = (xMax - xMin) / 2 * smplWghts5[i];

            for (int j = 0; j < nmbrPnts; j++) {
                smplPntY = intlY + (yMax - yMin) / 2 - ((yMax - yMin) / 2) * smplPnts5[j];
                smplWghtY = (yMax - yMin) / 2 * smplWghts5[j];
                for (int k = 0; k < nmbrPnts; k++) {
                    smplPntZ = intlZ + (zMax - zMin) / 2 - ((zMax - zMin) / 2) * smplPnts5[k];
                    smplWghtZ = (zMax - zMin) / 2 * smplWghts5[k];         
                    rslt1 += smplWghtX * smplWghtY * smplWghtZ * cmptFnctn(smplPntX, smplPntY, smplPntZ);
                }
            }
        }
        return rslt1;
    }
    private double cmptFnctn(double myX) {
        double rslt = qdrtrIntrfc.cmptIntgrl(myX);
        return rslt;
    }

    private double cmptFnctn(double myX, double myY) {
        double rslt = qdrtrIntrfc.cmptIntgrl(myX, myY);
        return rslt;
    }
    private double cmptFnctn(double myX, double myY, double myZ) {
        double rslt = qdrtrIntrfc.cmptIntgrl(myX, myY, myZ);
        return rslt;
    }
    /**
     * The methods returns the number of Gauss Points.
     * @return The return type is integer (int).
     */
    public int getNmbrPnts() {
        return nmbrPnts;
    }
}