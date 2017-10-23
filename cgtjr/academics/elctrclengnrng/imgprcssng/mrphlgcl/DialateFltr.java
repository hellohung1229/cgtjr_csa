/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;

/**
 *
 * @author clayton g thomas jr
 */
public class DialateFltr extends ImageFilter {

    private Dialate dialater;
    private int filteredData[];
    private ImageSet nonErddImgSet;
    private ImageSet erddImgSet;
    private StrctElmnt strctElmnt;
    private static int outputData[];    

    public DialateFltr(StrctElmnt myStrctElmnt) {
        dialater = new Dialate();
        nonErddImgSet = new ImageSet();
        erddImgSet = new ImageSet();
        strctElmnt = myStrctElmnt;
    }    
    public DialateFltr() {
        dialater = new Dialate();
        nonErddImgSet = new ImageSet();
        erddImgSet = new ImageSet();
        strctElmnt = new StrctElmnt("C:\\cthomas\\developement\\us3da_crrnt\\src\\public_html\\us3da\\data\\images\\shapes\\octgnelmnt.jpg");
    }
    public DialateFltr(String myStrctName) {
        dialater = new Dialate();
        nonErddImgSet = new ImageSet();
        erddImgSet = new ImageSet();
        strctElmnt = new StrctElmnt(myStrctName);
    }    
 
    public DialateFltr(int myPixelData[], int myImageWidth, int myImageHeight) {
        super(myPixelData, myImageWidth, myImageHeight);
        setImageWidth(myImageWidth);
        setImageHeight(myImageHeight);
        initialize(myImageWidth, myImageHeight);
    }

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        System.out.println("DialateFltr: width = "+myImageWidth+",height = "+myImageHeight);
        nonErddImgSet.setWidth(myImageWidth);
        nonErddImgSet.setHeight(myImageHeight);
        erddImgSet.setWidth(myImageWidth);
        erddImgSet.setHeight(myImageHeight);
        int pixelData[] = new int[myImageWidth * myImageHeight];
        erddImgSet.setImageData(pixelData);
        dialater.setOutputImgSet(erddImgSet);
  
        
    }

    public void filter(int myOriginalValues[], int i) {
        dialate(myOriginalValues, i);
    }

    public void dialate(int myPixelData[], int myIndex) {

        nonErddImgSet.setImageData(myPixelData);

        //System.out.println("DialateFltr: i = "+i+", j = "+j+", width = "+getImageWidth());
        dialater.dialate(myIndex, nonErddImgSet, strctElmnt);

    }

    public int[] getFltrdData() {
        filteredData = dialater.getOutputImgSet().getImageData();
        //filteredData = new int[85880];
        outputData = filteredData;   
        return filteredData;
    }
    public static int[] getOutputData() {
        return outputData;
    }    
}