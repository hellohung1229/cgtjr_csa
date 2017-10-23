/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl.Erode;
import cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl.ImageSet;
import cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl.StrctElmnt;

/**
 *
 * @author clayton g thomas jr
 */
public class ErodeFltr extends ImageFilter {

    private Erode eroder;
    private int filteredData[];
    private ImageSet nonErddImgSet;
    private ImageSet erddImgSet;
    private StrctElmnt strctElmnt;


    public ErodeFltr() {
        eroder = new Erode();
        nonErddImgSet = new ImageSet();
        erddImgSet = new ImageSet();
        strctElmnt = new StrctElmnt("C:\\cthomas\\developement\\us3da_crrnt\\src\\public_html\\us3da\\data\\images\\shapes\\ringelmnt.jpg");
    }
    public ErodeFltr(StrctElmnt myStrctElmnt) {
        eroder = new Erode();
        nonErddImgSet = new ImageSet();
        erddImgSet = new ImageSet();
        strctElmnt = myStrctElmnt;
    }    
    public ErodeFltr(String myStrctName) {
        eroder = new Erode();
        nonErddImgSet = new ImageSet();
        erddImgSet = new ImageSet();
        strctElmnt = new StrctElmnt(myStrctName);
    }    
 
    public ErodeFltr(int myPixelData[], int myImageWidth, int myImageHeight) {
        super(myPixelData, myImageWidth, myImageHeight);
        setImageWidth(myImageWidth);
        setImageHeight(myImageHeight);
        initialize(myImageWidth, myImageHeight);
    }

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
System.out.println("ErodeFltr: width = "+myImageWidth+",height = "+myImageHeight);
        nonErddImgSet.setWidth(myImageWidth);
        nonErddImgSet.setHeight(myImageHeight);
        erddImgSet.setWidth(myImageWidth);
        erddImgSet.setHeight(myImageHeight);
        int pixelData[] = new int[myImageWidth * myImageHeight];
        erddImgSet.setImageData(pixelData);
        eroder.setOutputImgSet(erddImgSet);
    }

    public void filter(int myOriginalValues[], int i) {
        erode(myOriginalValues, i);
    }
    public void erode(int myPixelData[], int myIndex) {
        nonErddImgSet.setImageData(myPixelData);
        //System.out.println("ErodeFltr: i = "+i+", j = "+j+", width = "+getImageWidth());
        eroder.erode(myIndex, nonErddImgSet, strctElmnt);
    }

    public int[] getFltrdData() {
        filteredData = eroder.getOutputImgSet().getImageData();
        //filteredData = new int[85880];
        return filteredData;
    }
}