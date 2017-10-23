package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.FrameParser;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ParseAction;
import cgtjr.academics.general.gui.UgotImage;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;


public class PointParser implements FrameParser {

    private static int frameNumber;
    private Image inputImage;
    private Image outputImage;
    private int imageData1[];
    private int imageData2[];
    private int imageWidth = 0;
    private int imageHeight = 0;
    private ParseAction parseActn;
    private int startIndex;
    private int stopIndex;    

    public PointParser(ParseAction myParseAction) {
        parseActn = myParseAction;
    }

    public void setInputImage(String fileName) {
        inputImage = UgotImage.createImage(fileName);
    }

    public void setInputImage(URL fileName) {
        inputImage = UgotImage.createImage(fileName);
    }

    public void setInputImage(Image anImage) {
        inputImage = anImage;
    }

    public void bgnFrames() {
        //TODO: consider optimizing this function ... remove new int & image width & height
        if (inputImage != null) {
            imageWidth = inputImage.getWidth(null);
            imageHeight = inputImage.getHeight(null);
            while (imageWidth < 1 || imageHeight < 1) {
                imageWidth = inputImage.getWidth(null);
                imageHeight = inputImage.getHeight(null);
                //System.out.println("PointParser: in while loop");
            }
            //imageData1 = new int[imageWidth*imageHeight];
            //imageData2 = new int[imageWidth*imageHeight];
            frameNumber++;
            //System.out.println("PointParser: framenumber = "+frameNumber);
        }
    }

    public void strtPrsng() {
    }

    public void fnshPrsng() {
    }

    public void endFrames() {
        //meshPoint.setCntrlNmbr(meshPoint.getCntrlNmbr()+1);
    }

    public void parse() {
        //System.out.println("PointParser: test 1000");
        outputImage = parseActn.actn(inputImage, imageWidth, imageHeight);
         //outputImage = inputImage;
        //outputImage = UgotImage.createRGBImage(inputImage,imageWidth,imageHeight,true);
    }

    public Image getOutputImage() {
        return outputImage;
    }

    public Image getOutputImage(Component aComponent) {
        Graphics aGraphics = null;
        Image anImage = null;

        if (aComponent != null) {
            anImage = aComponent.createImage(imageWidth, imageHeight);
            aGraphics = anImage.getGraphics();
            aGraphics.drawImage(outputImage, 0, 0, null);
        } else {
            anImage = outputImage;
        }
        return anImage;
    }
    public int getTopOffset() {
        return startIndex;
    }

    public void setTopOffset(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getBottomOffset() {
        return stopIndex;
    }

    public void setBottomOffset(int stopIndex) {
        this.stopIndex = stopIndex;
    }      
}