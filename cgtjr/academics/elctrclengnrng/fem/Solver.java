package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.lnralgbra.Matrix;

public class Solver {

    private double x[];
    private double y[];
    private double z[];
    private double c[][];
    private double ce[][];
    private int nl[][];
    private int ndp[];
    private double val[];
    private int lf[];
    private double v[];
    private double p[];
    private double q[];
    private double xl[];
    private double yl[];
    private double zl[];
    private double er[];
    private int ni;
    private double pie;
    private double eo;
    private int k;
    private int ir;
    private int ic;
    private int nf;
    private double sum;
    private NdlElmnts hxhdrlNdElmnts;
    private double prscrbNdVl[];
    private int lclSz;
    private BssFnctn basisFnctn;
    private int glblSz;
    private double maxValue;
    private double minValue;

    public Solver(NdlElmnts myNdlElmnts, BssFnctn myBasisFnctn) {
        basisFnctn = myBasisFnctn;
        lclSz = myNdlElmnts.getLclSize();
        glblSz = myNdlElmnts.getNodeCnt();
        hxhdrlNdElmnts = myNdlElmnts;

        x = new double[glblSz];
        y = new double[glblSz];
        z = new double[glblSz];
        c = new double[glblSz][glblSz];
        ce = new double[glblSz][glblSz];
        nl = new int[glblSz][lclSz];
        ndp = new int[glblSz];
        val = new double[glblSz];
        lf = new int[glblSz];
        v = new double[glblSz];
        p = new double[lclSz];
        q = new double[lclSz];
        xl = new double[lclSz];
        yl = new double[lclSz];
        zl = new double[lclSz];
        er = new double[glblSz];
        ni = 20000;
        maxValue = -Double.MAX_VALUE;
        minValue = Double.MAX_VALUE;
    }

    public void prcss() {
        int aNE = hxhdrlNdElmnts.getElmntCnt();

        int aND = hxhdrlNdElmnts.getNodeCnt();
        int aNP = hxhdrlNdElmnts.getPrscrbNdCnt();
        this.nf = hxhdrlNdElmnts.getFrNdCnt();
        this.lf = hxhdrlNdElmnts.getFrNdIndx();
        this.nl = hxhdrlNdElmnts.getElmntLclNds();
        this.prscrbNdVl = hxhdrlNdElmnts.getPrscrbNdVls();
        this.x = hxhdrlNdElmnts.getXGlbl();
        this.y = hxhdrlNdElmnts.getYGlbl();
        this.z = hxhdrlNdElmnts.getZGlbl();
        this.ndp = hxhdrlNdElmnts.getPrscrbNdIndx();
        calculate(aNE, aND, aNP);
    }
    public void calculate(int ne, int nd, int np) {
        pie = 4 * Math.atan(1.0);
        eo = 1.0 * Math.pow(10, -9) / (36.0 * pie);

        for (int i = 0; i < ne; i++) {
            for (int j = 0; j < lclSz; j++) {
                k = nl[i][j];
                xl[j] = x[k];
                yl[j] = y[k];
                zl[j] = z[k];
                System.out.println("Solver: element = "+i+" xl["+j+"]="+xl[j]+",yl["+j+"]="+yl[j]+",zl["+j+"]="+zl[j]);
            }
            basisFnctn.setBssNds(xl, yl, zl);
            for (int m = 0; m < lclSz; m++) {
                for (int n = 0; n < lclSz; n++) {
                    ce[m][n] = basisFnctn.cmptCffcntMtrx(m, n);
                }
            } 
            for (int j = 0; j < lclSz; j++) {
                ir = nl[i][j];
                for (int l = 0; l < lclSz; l++) {
                    ic = nl[i][l];
                    c[ir][ic] = c[ir][ic] + ce[j][l];
                    System.out.println("Solver:  c["+ir+"]["+ic+"]="+ c[ir][ic]+",ce["+j+"]["+l+"]="+ce[j][l]);
                }
            }
        }
        for (int i = 0; i < np; i++) {
            int glblNmbr = ndp[i];
            double aValue = prscrbNdVl[i];
            v[glblNmbr] = aValue;
            updtMinMaxVal(v[glblNmbr]);            
            System.out.println("Solver: prescribe v["+glblNmbr+"]="+v[glblNmbr]);
        }
        System.out.println("nf = "+nf+", nd-np = " +(nd-np));
        for (int n = 0; n < ni; n++) {
            for (int i = 0; i < nf; i++) {
                sum = 0.0;
                k = lf[i];
                for (int j = 0; j < nd; j++) {
                    if (j == k) {
                    } else {
                        sum = sum + v[j] * c[j][k];
                    }
                }
                v[k] = -1 * sum / c[k][k];
            }
            updtMinMaxVal(v[k]);
        }
        System.out.println("Solver: ..... ");
        //Matrix.print(c);
    }
    public void updtMinMaxVal(double myValue) {
        if (myValue < minValue) {
            minValue = myValue;
        }
        if (myValue >= maxValue) {
            maxValue = myValue;
        }
    }
    public double getMaxValue() {
        return maxValue;
    }
    public double getMinValue() {
        return minValue;
    }
    public void imposedPotentialInput(int myIndex, double myValue) {
        val[myIndex] = myValue;
    }
    public double[] getValue() {
        return v;
    }
    public double[][] getSprstyMtrx() {
        return c;
    }
}