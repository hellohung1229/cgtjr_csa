package cgtjr.academics.elctrclengnrng.imgprcssng.general;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.FrameParser;
import cgtjr.academics.general.gui.*;
import java.awt.*;

public class ImgArryParser implements FrameParser//extends Filter //implements ImageObserver
{

    public static final String type = "ImageParser";
    private Image inputImage;
    private Image outputImage;
    private int rgbColors[];
    private int imageLength;
    private int imageWidth = 0;
    private int imageHeight = 0;
    private ImageFilter prsdImageFilter;
    private static boolean isInitialized;
    private int topOffset;
    private int bottomOffset;
    private int startOffSet;
    private int stopOffSet;
    
    public ImgArryParser(Image myInputImage, ImageFilter myImageFilter) {
        prsdImageFilter = myImageFilter;
        inputImage = myInputImage;
    }

    public ImgArryParser(String fileName, ImageFilter myImageFilter) {
        prsdImageFilter = myImageFilter;
        inputImage = UgotImage.createImage(fileName);
    }

    public ImgArryParser(ImageFilter myImageFilter) {
        prsdImageFilter = myImageFilter;
        System.out.println("ImgParser ... constructor");
    }

    public void setInputImage(Image anImage) {
        //System.out.println("ImgParser.setInputImage: width = "+anImage.getWidth(null));
        inputImage = anImage;
    }

    public void bgnFrames() {
        if (isInitialized == true) {
            return;
        }
        imageWidth = 0;
        imageHeight = 0;
        int transparency = 0;

        if (inputImage != null) {
            imageWidth = inputImage.getWidth(null);
            imageHeight = inputImage.getHeight(null);
            int c = 0;
                //System.out.println("ImageParser: test a = ");
            while (imageWidth <= 1 || imageHeight <= 1) {                
                imageWidth = inputImage.getWidth(null);
                imageHeight = inputImage.getHeight(null);
                System.out.println("ImageParser: test c = "+c);
                c++;                
            }
            rgbColors = new int[imageWidth * imageHeight];
            imageLength = imageWidth * imageHeight;

        }
        startOffSet = topOffset*imageWidth;
        stopOffSet = bottomOffset*imageWidth;
        UgotImage.handlepixels(inputImage, 0, 0, imageWidth, imageHeight, rgbColors);
        //System.out.println("ImgParser: startOffSet = "+startOffSet+ ",stopOffSet = "+stopOffSet);
        //System.out.println("ImgParser.bgnFrames: width = "+inputImage.getWidth(null));
        //TODO: this may need optimization
        //isInitialized = true; 
    }

    public void parse() {


        //prsdImageFilter.startPrsng(imageHeight,imageWidth);
        prsdImageFilter.initialize(imageWidth, imageHeight);
        prsdImageFilter.setImputPxlData(rgbColors);
        //System.out.println("ImgParser: startOffSet = "+startOffSet+", stopOffSet = "+stopOffSet);
        //for (int j = startOffSet; j < imageLength-stopOffSet; j++) {
            prsdImageFilter.filter(rgbColors,0);
        //}
        prsdImageFilter.finish();
        int imgData[] = prsdImageFilter.getFltrdData();

        //System.out.println("ImageParser.parse(): length = " + imgData.length + ", width = " + imageWidth + ", height = " + imageHeight);
        outputImage = UgotImage.createRGBImage(imgData, imageWidth, imageHeight, true);
    }

    public Image getOutputImage() {
        //System.out.println("ImageParser.getOutputImage(): width = " + outputImage.getWidth(null) + ", outputImage.getHeight(null) = " + imageHeight);
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

    public void endFrames() {
    }

    public void fnshPrsng() {
    }

    public void strtPrsng() {
    }

    public int getTopOffset() {
        return topOffset;
    }

    public void setTopOffset(int startIndex) {
        this.topOffset = startIndex;
    }

    public int getBottomOffset() {
        return bottomOffset;
    }

    public void setBottomOffset(int stopIndex) {
        this.bottomOffset = stopIndex;
    }    
}