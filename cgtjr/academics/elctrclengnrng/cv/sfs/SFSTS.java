/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.sfs;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.videotmp.ColorCode;
import cgtjr.academics.general.gui.UgotImage;
import java.awt.Image;
import java.io.File;

public class SFSTS {

    private static int Size = 128;
    private static int row, col;
    private static PIC aPIC;
    private static float Zn[][],
            Zn1[][],
            Si1[][],
            Si[][];
    /*
     * private class PIC { public int type; public int maxX,maxY; public int
     * image[];
   }
     */

    private final static double MAX(double a, double b) {
        return (((a) < (b)) ? (b) : (a));
    }

    public static void main(String args[]) {
        process(args[0]);
    }
    public static void process(Image myImage) {
        aPIC = rtrvData(myImage);
        process(aPIC);        
    }
    public static void process(String myFileName) {
        aPIC = rtrvData(myFileName);
        System.out.println("SFSTS image length = " + aPIC.image.length);

        char filename[] = new char[80];
        File outfile, infile;
        process(aPIC);
    }
    public static void process(int myImgData[],int myWidth,int myHeight) { 
        aPIC = rtrvData(myImgData,myWidth,myHeight);
        process(aPIC);        
    }
    public static void process(PIC aPIC) {
        int i, j, I, iter = 1;
        float Ps = 0.01f, Qs = 0.01f, p = 0f, q = 0f, pq = 0f, PQs = 0f, fZ = 0f, dfZ = 0f, Eij = 0f, Wn = (float) (0.0001 * 0.0001), Y = 0f, K = 0f;
        col = aPIC.maxX;
        row = aPIC.maxY;
        Size = col;
        System.out.println("SFSTS: Size = " + Size);
        Zn = new float[row][col];
        Zn1 = new float[row][col];
        Si1 = new float[row][col];
        Si = new float[row][col];

        //assume the initial estimate zero at time n-1
        for (i = 0; i < Size; i++) {
            for (j = 0; j < Size; j++) {
                Zn1[i][j] = 0.0f;
                Si1[i][j] = 0.01f;
            }
        }
        printf("Input number of iterations : ");
        //scanf("%d",&iter);
        printf("\nInput the image filename : ");
        //scanf("%s",filename);
        //infile = fopen(filename,"r");
        //aPIC = UCFReadPic(infile);
        printf("\nInput the light source direction : ");
        printf("\nPs = ");
        //scanf("%f",&Ps);
        printf("\nQs = ");
        //scanf("%f",&Qs);

        //************************************************************************
        for (I = 1; I <= iter; I++) {
            for (i = 0; i < Size; i++) {
                for (j = 0; j < Size; j++) { // calculate -f(Zij) & df(Zij)

                    if (j - 1 < 0 || i - 1 < 0) // take care boundary
                    {
                        p = q = 0.0f;
                    } else {
                        p = Zn1[i][j] - Zn1[i][(j - 1)];
                        q = Zn1[i][j] - Zn1[i - 1][j];
                    }
                    pq = 1.0f + p * p + q * q;
                    PQs = 1.0f + Ps * Ps + Qs * Qs;
                    Eij = ColorCode.convertRGBToY(aPIC.image[i * aPIC.maxX + j]) / 255.0f;
                    fZ = (float) (-1.0 * (Eij - MAX(0.0, (1 + p * Ps + q * Qs) / (Math.sqrt(pq) * Math.sqrt(PQs)))));
                    dfZ = (float) (-1.0 * ((Ps + Qs) / (Math.sqrt(pq) * Math.sqrt(PQs)) - (p + q) * (1.0 + p * Ps + q * Qs)
                            / (Math.sqrt(pq * pq * pq) * Math.sqrt(PQs))));
                    Y = fZ + dfZ * Zn1[i][j];
                    K = Si1[i][j] * dfZ / (Wn + dfZ * Si1[i][j] * dfZ);
                    Si[i][j] = (1.0f - K * dfZ) * Si1[i][j];
                    Zn[i][j] = Zn1[i][j] + K * (Y - dfZ * Zn1[i][j]);
                }
            }
            printf("\nOutput depth map !\n");
            //sprintf(filename,"tmap%d.out",I);
            //outfile = fopen(filename,"w");
            for (i = 0; i < Size; i++) {
                for (j = 0; j < Size; j++) {
                    //fprintf(outfile,"%f\n",Zn[i][j]);
                    Zn1[i][j] = Zn[i][j];
                    Si1[i][j] = Si[i][j];                  
                }
            }
            //Image anImage = ImageTool.rtrvImage(Zn);
            //ImageTool.wrtImageFile("heightfile", myImage);
            //fclose(outfile);
            System.out.println("SFSTS: Zn.length = " + Zn.length);
        }
    }

    public static float[][] rtrvZn() {
        System.out.println("SFSTS: Zn.length = " + Zn.length);
        return Zn;
    }

    public static void printf(String myString) {
        System.out.println(myString);
    }

    public static void printf(File myFile, String myString) {
        System.out.println(myString);
    }

    public static void scanf(String myString) {
        System.out.println(myString);
    }

    public static PIC rtrvData(Image myImage) {
        PIC aPIC = new PIC();
        aPIC.maxX = myImage.getWidth(null);
        aPIC.maxY = myImage.getHeight(null);
        aPIC.image = ImageTool.rtrv1DPxls(myImage);
        return aPIC;
    }    
    
    public static PIC rtrvData(String myString) {
        PIC aPIC = new PIC();
        //Image myImage = ImageTool.rdImageFile(myString);
        Image myImage = UgotImage.createImage(myString);
        aPIC.maxX = myImage.getWidth(null);
        aPIC.maxY = myImage.getHeight(null);
        aPIC.image = ImageTool.rtrv1DPxls(myImage);
        return aPIC;
    }
    
    public static PIC rtrvData(int myImgData[],int myWidth,int myHeight) {
        PIC aPIC = new PIC();
        aPIC.maxX = myWidth;
        aPIC.maxY = myHeight;
        aPIC.image = myImgData;
        return aPIC;
    }

    public static int[] rtrvPxlData() {
        return aPIC.image;
    }

    public static void fclose(File myFile) {
    }
}