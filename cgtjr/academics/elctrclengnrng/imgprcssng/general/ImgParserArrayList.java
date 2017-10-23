package cgtjr.academics.elctrclengnrng.imgprcssng.general;

import cgtjr.academics.general.gui.*;
import java.awt.*;
import java.util.ArrayList;

public class ImgParserArrayList implements FrameParser//extends Filter //implements ImageObserver
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
    private ArrayList rgbArrayList;

    public ImgParserArrayList(Image myInputImage, ImageFilter myImageFilter) {
        prsdImageFilter = myImageFilter;
        inputImage = myInputImage;
    }

    public ImgParserArrayList(String fileName, ImageFilter myImageFilter) {
        prsdImageFilter = myImageFilter;
        inputImage = UgotImage.createImage(fileName);
    }

    public ImgParserArrayList(ImageFilter myImageFilter) {
        prsdImageFilter = myImageFilter;
        rgbArrayList = new ArrayList();
        System.out.println("ImgParser ... constructor");
    }

    public void setInputImage(Image anImage) {
        //System.out.println("ImgParser.setInputImage: width = "+anImage.getWidth(null));
        inputImage = anImage;
        rgbArrayList.add(anImage);
    }

    public void bgnFrames() {
        if (isInitialized == true) {
            return;
        }
        Image myInputImage = null;
        imageWidth = 0;
        imageHeight = 0;
        int transparency = 0;
        
        boolean isEmptyList = rgbArrayList.isEmpty();
        if(isEmptyList == true){
            return;
        }
        myInputImage = (Image) rgbArrayList.remove(0);
        
        if(myInputImage == null){
           //System.out.println("ImgParser: input image = null");
        }
        if (myInputImage != null) {
            imageWidth = myInputImage.getWidth(null);
            imageHeight = myInputImage.getHeight(null);
            int c = 0;
            //System.out.println("ImageParser: test bbb ... imageWidth =  "+imageWidth+", imageHeight = "+imageHeight);
            while (imageWidth <= 1 || imageHeight <= 1) {
                imageWidth = myInputImage.getWidth(null);
                imageHeight = myInputImage.getHeight(null);
                //System.out.println("ImageParser: test c = " + c);
                c++;
            }
            rgbColors = new int[imageWidth * imageHeight];
            imageLength = imageWidth * imageHeight;

        }
        
        startOffSet = topOffset * imageWidth;
        stopOffSet = bottomOffset * imageWidth;
        UgotImage.handlepixels(myInputImage, 0, 0, imageWidth, imageHeight, rgbColors);
        
        //System.out.println("ImageParser: test a ... imageWidth = "+imageWidth+", imageHeight = "+imageHeight);
        //System.out.println("ImgParser: startOffSet = "+startOffSet+ ",stopOffSet = "+stopOffSet);
        //System.out.println("ImgParser.bgnFrames: width = "+inputImage.getWidth(null));
        //TODO: this may need optimization
        //isInitialized = true; 
    }

    public void parse() {


        int imgData[] = null;
        int myRGBColors[] = rgbColors;
        if (myRGBColors != null) {
            //prsdImageFilter.startPrsng(imageHeight,imageWidth);
            prsdImageFilter.initialize(imageWidth, imageHeight);
            prsdImageFilter.setImputPxlData(myRGBColors);
            //System.out.println("ImgParser: startOffSet = "+startOffSet+", stopOffSet = "+stopOffSet);
            for (int j = startOffSet; j < imageLength - stopOffSet; j++) {
                prsdImageFilter.filter(myRGBColors, j);
            }
            prsdImageFilter.finish();
            imgData = prsdImageFilter.getFltrdData();
            //System.out.println("ImageParser: test d ... imageWidth = "+imageWidth+", imageHeight = "+imageHeight);
            outputImage = UgotImage.createRGBImage(imgData, imageWidth, imageHeight, true);
        } 
        //System.out.println("ImageParser.parse(): length = " + imgData.length + ", width = " + imageWidth + ", height = " + imageHeight);
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
    public boolean isEmptyList(){
        return rgbArrayList.isEmpty();
    }
}