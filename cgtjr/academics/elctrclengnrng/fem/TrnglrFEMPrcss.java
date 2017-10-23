/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.general.colorspace.ColorUpdtr;
import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.elctrclengnrng.imgprcssng.Variance;
import cgtjr.academics.general.*;
import cgtjr.academics.math.complex.CmplxMatrix;
import cgtjr.academics.math.geometry.elmnt.TrnglPnts;
import cgtjr.academics.math.lnralgbra.Matrix;
import java.util.Vector;
import javax.swing.JEditorPane;

/**
 *
 * @author clayton g thomas jr
 */
public class TrnglrFEMPrcss extends DataPrcss {

    private ShapePnt shp;
    private Vector bndryPnts;
    private NdlElmnts anNdlElmnts1;
    private NdlElmnts anNdlElmnts2;
    private NdlElmnts anNdlElmnts3;
    private NdlElmnts anNdlElmnts4;
    private BssFnctn basisFnctn1;
    private BssFnctn basisFnctn2;
    private BssFnctn basisFnctn3;
    private BssFnctn basisFnctn4;
    private BssFnctn basisFnctn5;
    private SparseMtrxVar sprsMtrxVar;
    private JEditorPane editorPane;

    public TrnglrFEMPrcss(MeshShp myShape, NdlElmnts myNdlElmnts) {
        shp = myShape;
        anNdlElmnts1 = myNdlElmnts;
        sprsMtrxVar = new SparseMtrxVar();
    }

    public TrnglrFEMPrcss(ShapePnt myShape, ShpBndry myShpBndry) {
        shp = myShape;
        Vector aQuadVctr = shp.getQuadElmnts();
        Vector aTrnglVctr = TrnglPnts.cnvrtRctnglToTrngl(aQuadVctr);
        int glblSize = shp.getNodes().size();
        //TrnglPnts.prntGlblNmbrs(aTrnglVctr);
        //TrnglPnts.prntCrdnts(aTrnglVctr);
        anNdlElmnts1 = new NdlElmnts(glblSize, 3);
        anNdlElmnts1.insrtElmntLclNds(aTrnglVctr);
        anNdlElmnts1.insrtXYZByGlblNmbr(shp.getNodes());

        anNdlElmnts2 = new NdlElmnts(glblSize, 3);
        anNdlElmnts2.insrtElmntLclNds(aTrnglVctr);
        anNdlElmnts2.insrtXYZByGlblNmbr(shp.getNodes());

        anNdlElmnts3 = new NdlElmnts(glblSize, 2);
        anNdlElmnts3.insrtElmntLclNds(myShape.getLineBndry2D());
        anNdlElmnts3.insrtXYZByGlblNmbr(myShape.getNodeBndry2D());

        anNdlElmnts4 = new NdlElmnts(glblSize, 2);
        anNdlElmnts4.insrtElmntLclNds(myShape.getLineBndry2D());
        anNdlElmnts4.insrtXYZByGlblNmbr(myShape.getNodeBndry2D());

        basisFnctn1 = new TrnglIntgrlDvrgncBss();
        basisFnctn2 = new TrnglIntgrlMtlplyBss();
        basisFnctn3 = new LineIntgrlMltlplyBss();
        basisFnctn4 = new LineIntgrlDrvtvBss();
        basisFnctn5 = new LineRdlBss(myShape.getXMax());

        sprsMtrxVar = new SparseMtrxVar();
    }

    public void prcss() {
        MatrixACrtr aMatrixACrtr1 = new MatrixACrtr(anNdlElmnts1, basisFnctn1);
        MatrixACrtr aMatrixACrtr2 = new MatrixACrtr(anNdlElmnts2, basisFnctn2);
        MatrixACrtr aMatrixACrtr3 = new MatrixACrtr(anNdlElmnts3, basisFnctn3);
        MatrixACrtr aMatrixACrtr4 = new MatrixACrtr(anNdlElmnts3, basisFnctn4);

        double aMatrix1[][] = aMatrixACrtr1.create();
        double aMatrix2[][] = aMatrixACrtr2.create();
        double aMatrix3[][] = aMatrixACrtr3.create();
        double aMatrix4[][] = aMatrixACrtr4.create();

        BGTMatrixB aBGTMatrixB = new BGTMatrixB(anNdlElmnts3, basisFnctn5);
        double matrixB[][] = aBGTMatrixB.create();

        //Matrix.print(aMatrix1);
        //Matrix.print(aMatrix2);
        //Matrix.print(aMatrix3);
        //Matrix.print(aMatrix4);
        //Matrix.print(matrixB);

        MtrxSlvr aMtrxSlvr = new MtrxSlvr();
        double x[][] = aMtrxSlvr.solve(aMatrix1, aMatrix2, aMatrix3, aMatrix4, matrixB);

        //sprsMtrxVar.setSprsMtrx(matrixB);
        //SparseMtrxDflt.setDfltVar(sprsMtrxVar);

        //double aMatrix[][] = sprsMtrxVar.getSprsMtrx();
        //int aHeight = aMatrix.length;
        //int aWidth = aMatrix[0].length;      
        //int myPxlData[] = ArrayTool.rtrv1DIntArry(aMatrix,.00001);   

        double values[] = CmplxMatrix.magnitudeX(x);
        //Matrix.print(values);

        Variance aVariance = new Variance(values);
        //double maxValue = aVariance.rtrvStndrdDvtnMaxAvrg();
        //double minValue = aVariance.rtrvStndrdDvtnMinAvrg();
        double maxValue = aVariance.getMaxValue();
        double minValue = aVariance.getMinValue();
        //Note: a ShapePnt constructor should exist; values should be a parameter of update      
        ValueUpdtr aValueUpdtr = new ValueUpdtr(values);
        aValueUpdtr.update(shp);

        //NodePnts.prntGlblVls(bndryPnts);
        //Vector aBndryNodeVctr = crdntShp.getNodes();
        editorPane = LabStation.getEditorPane();
        //NodePnts.prntGlblVls(bndryPnts,editorPane);
        //Note: a ShapePnt constructor should exist; values should be a parameter of update
        ColorUpdtr aColorUpdtr = new ColorUpdtr(values, minValue, maxValue);
        aColorUpdtr.update(shp);
        prcssBndryByRadius(shp);
    }

    public void prcssBndryByRadius(ShapePnt myShapePnt) {
        Vector bndryPnts1 = myShapePnt.getNodes();
        bndryPnts = new Vector();
        int aSize = bndryPnts1.size();
        for (int i = 0; i < aSize; i++) {
            Pnt aPnt = (Pnt) bndryPnts1.get(i);
            if (aPnt.getX1() == myShapePnt.getXMax() &&
                aPnt.getY1() >= Math.PI && 
                aPnt.getY1() <= 2*Math.PI ) {
                //System.out.println("TrnglrFEMPrcss, x = "+aPnt.getX1()+", y = "+aPnt.getY1()+", value = "+aPnt.getValue());
                System.out.println(aPnt.getX1()+", "+(aPnt.getY1()-Math.PI)+", "+aPnt.getValue());                
                bndryPnts.add(aPnt);
            }
        }
    }

    public Vector getBndryPnts() {
        return bndryPnts;
    }
}