/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.math.lnralgbra.BckwrdSub;
import cgtjr.academics.math.lnralgbra.Cholesky;
import cgtjr.academics.math.lnralgbra.GssnElmntn;
import cgtjr.academics.math.lnralgbra.MathVector;
import cgtjr.academics.math.lnralgbra.Matrix;

/**
 *
 * @author clayton g thomas jr
 */
public class KConstraints {

    private double conicMatrix[][];
    private double conicVector[];
    private double b[];
    
    private ImageDrawData imageDrawPixels;
    private GssnElmntn aGssnElmntn;
    private BckwrdSub aBckwrdSub;
    private double matrix3x4[][];
    private int type;
    private double normal[];
    private double vanishingLine[];
    private double homagraphy[][];
    private double xVector[];
    private double yVector[];
    private double KMatrix[][];
    private double RMatrix[][];
    
    public KConstraints() {
        conicMatrix = new double[3][3];
        conicVector = new double[6];
        conicVector[1] = 0;
        b = new double[4];
        normal = new double[3];
        vanishingLine = new double[3];
        xVector = new double[3];
        yVector = new double[3];
    }
    public double[][] createHomography(){
        double KInverse[][] = Matrix.inverse(KMatrix);
        double RxKI[][] = Matrix.multiply3x3(RMatrix, KInverse);
        double KxRxKI[][] = Matrix.multiply3x3(KMatrix, RxKI);
        return KxRxKI;
    }
    public void solveK(double conicVector[]) {
    }
    public double[][] createRotationMatrix(double myNormal[]){
        double aRotationMatrix[][] = new double[3][3];
        aRotationMatrix[0][0] = xVector[0];
        aRotationMatrix[0][1] = xVector[1];
        aRotationMatrix[0][2] = xVector[2];        
        aRotationMatrix[1][0] = yVector[0];
        aRotationMatrix[1][1] = yVector[1];
        aRotationMatrix[1][2] = yVector[2];
        aRotationMatrix[2][0] = normal[0];
        aRotationMatrix[2][1] = normal[1];
        aRotationMatrix[2][2] = normal[2];
        return aRotationMatrix;
    }
    public double[] computeSolution(double myMatrix[][]) {
        //super.computeHOG1x1(myHOGPosition, myHOGArrayList2); //To change body of generated methods, choose Tools | Templates.

        aGssnElmntn = new GssnElmntn();
        aBckwrdSub = new BckwrdSub();

        //Matrix.print(myMatrix);
        double matrixC[][] = aGssnElmntn.process(myMatrix);
        //Matrix.print(matrixC);
        int aPivot[] = aGssnElmntn.getPivot();
        double x[] = aBckwrdSub.solve(matrixC);
        //Matrix.print(x);
        return x;
    }
    public void computeFocalKMatrix(VanishingHeightWidth myVanishingHeightWidth){
        double aMatrix3x4[][] = stackFocalConstraintVectors(myVanishingHeightWidth);
        double aSolution[] = computeSolution(aMatrix3x4);
        double aConic[][] = createFocalConic(aSolution);
        double aKMatrix[][] = computeCholesky(aConic);
        double aKMatrixT[][] = Matrix.transpose(aKMatrix);
        normal = Matrix.mltply3x3x3x1(aKMatrixT, vanishingLine);
        KMatrix  = aKMatrix;
    }
    public void computeKMatrix(VanishingHeightWidth myVanishingHeightWidth){
        double aMatrix3x4[][] = stackConstraintVectors(myVanishingHeightWidth);
        double aSolution[] = computeSolution(aMatrix3x4);
        double aConic[][] = createConic(aSolution);
        double aKMatrix[][] = computeCholesky(aConic);
    }
    public double[][] stackConstraintVectors(VanishingHeightWidth myVanishingHeightWidth) {

        double matrix3x4[][] = new double[3][4];

        MathVector aMathVector1 = myVanishingHeightWidth.getVanishPoint1();
        MathVector aMathVector2 = myVanishingHeightWidth.getVanishPoint2();
        MathVector aMathVector3 = myVanishingHeightWidth.getVanishPoint3();
        MathVector aMathVector4 = myVanishingHeightWidth.getVLineGround();

        double constraintVector1[] = createConstraintVector(aMathVector1, aMathVector2);
        double constraintVector2[] = createConstraintVector(aMathVector2, aMathVector3);
        double constraintVector3[] = createConstraintVector(aMathVector3, aMathVector2);

        matrix3x4[0] = constraintVector1;
        matrix3x4[1] = constraintVector2;
        matrix3x4[2] = constraintVector3;
        //createConstraintVector(aMathVector4,aMathVector1);
        return matrix3x4;
    }
    public double[][] stackFocalConstraintVectors(VanishingHeightWidth myVanishingHeightWidth) {

        double matrix3x4[][] = new double[3][4];

        MathVector aMathVector1 = myVanishingHeightWidth.getVanishPoint1();
        MathVector aMathVector2 = myVanishingHeightWidth.getVanishPoint2();
        MathVector aMathVector3 = myVanishingHeightWidth.getVanishPoint3();
        MathVector aMathVector4 = myVanishingHeightWidth.getVLineGround();

        double constraintVector1[] = createFocalConstraintVector(aMathVector1, aMathVector2);
        //double constraintVector2[] = createConstraintVector(aMathVector2, aMathVector3);
        //double constraintVector3[] = createConstraintVector(aMathVector3, aMathVector2);

        matrix3x4[0] = constraintVector1;
        //matrix3x4[1] = constraintVector2;
        //matrix3x4[2] = constraintVector3;
        //createConstraintVector(aMathVector4,aMathVector1);
        return matrix3x4;
    }
    private double[] createConstraintVector(MathVector myMathVector1, MathVector myMathVector2) {
        double aMatrixA[] = new double[4];

        if (myMathVector1 == null) {
            System.out.println("myMathVector1 is null");
        }
        if (myMathVector2 == null) {
            System.out.println("myMathVector2 is null");
        }

        float u1 = myMathVector1.getX2();
        float u2 = myMathVector1.getY2();
        float u3 = myMathVector1.getZ1();

        float v1 = myMathVector2.getX2();
        float v2 = myMathVector2.getY2();
        float v3 = myMathVector2.getZ1();

        aMatrixA[0] = v1 * u1;
        //aMatrixA[1] = v1*u2+v2*u1;
        //aMatrixA[2] = v2*u2;
        aMatrixA[1] = v1 * u3 + v3 * u1;
        aMatrixA[2] = v2 * u3 + v3 * u2;
        aMatrixA[3] = -1 * v3 * u3;//Convert last column to b, as in solution Ax = b

        return aMatrixA;
    }
    private double[] createFocalConstraintVector(MathVector myMathVector1, MathVector myMathVector2) {
        double aMatrixA[] = new double[2];

        if (myMathVector1 == null) {
            System.out.println("myMathVector1 is null");
        }
        if (myMathVector2 == null) {
            System.out.println("myMathVector2 is null");
        }

        float u1 = myMathVector1.getX2();
        float u2 = myMathVector1.getY2();
        float u3 = myMathVector1.getZ1();

        float v1 = myMathVector2.getX2();
        float v2 = myMathVector2.getY2();
        float v3 = myMathVector2.getZ1();

        aMatrixA[0] = v1 * u1;
        aMatrixA[1] = -1*v3*u3;
        //aMatrixA[1] = v1*u2+v2*u1;        
        //aMatrixA[1] = v1 * u3 + v3 * u1;
        //aMatrixA[2] = v2 * u3 + v3 * u2;
        //aMatrixA[3] = -1 * v3 * u3;//Convert last column to b, as in solution Ax = b

        return aMatrixA;
    }    
    public double computeFocalLength(double aConic[]){
        double focalLength = 0;
        focalLength = 1/aConic[0];
        return focalLength;
    }
    public double[][] createConic(double myVector[]){
        double aConic[][] = new double[3][3];
        aConic[0][0] = myVector[0];
        aConic[0][1] = 0;
        aConic[0][2] = myVector[1];
        aConic[1][0] = 0;
        aConic[1][1] = aConic[0][0];
        aConic[1][2] = myVector[2];
        aConic[2][0] = aConic[0][2];
        aConic[2][1] = aConic[1][2];
        aConic[2][2] = 1;//myVector[3];
        return aConic;
    }
    public double[][] createFocalConic(double myVector[]){
        double aConic[][] = new double[3][3];
        aConic[0][0] = myVector[0];
        aConic[0][1] = 0;
        aConic[0][2] = 0;
        aConic[1][0] = 0;
        aConic[1][1] = myVector[1];
        aConic[1][2] = 0;
        aConic[2][0] = 0;
        aConic[2][1] = 0;
        aConic[2][2] = 1;
        return aConic;
    }
    
    public double[][] computeCholesky(double myConic[][]){
        double inverseMatrix[][] = Matrix.inverse(myConic);
        Cholesky aCholeskyMatrix = new Cholesky(inverseMatrix);   
        double aKMatrix[][] = aCholeskyMatrix.getL();
        return aKMatrix;
    }
    public void createVectorB() {
    }
    public void drawHOGArrow(HOGPosition matchedHOGPosition, HOGPosition myHOGPosition) {
        //if(shouldDraw == true){
        //drawArrow(matchedHOGPosition,myHOGPosition );
        //}
    }

    public double[] getVanishingLine() {
        return vanishingLine;
    }

    public void setVanishingLine(double[] myVanishingLine) {
        this.vanishingLine = myVanishingLine;
    }

    public double[] getxVector() {
        return xVector;
    }

    public void setxVector(double[] myXVector) {
        this.xVector = myXVector;
    }

    public double[] getyVector() {
        return yVector;
    }

    public void setyVector(double[] myYVector) {
        this.yVector = myYVector;
    }
    
}