/*
 * @(#)VideoRndrr.java
 *
 */
package cgtjr.academics.elctrclengnrng.video.general;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.FrameParser;
import javax.media.*;
import javax.media.Format;
import javax.media.format.VideoFormat;
import javax.media.format.RGBFormat;
import java.awt.*;
import java.awt.image.*;

import cgtjr.academics.elctrclengnrng.imgprcssng.*;
import javax.media.renderer.VideoRenderer;

/**
 * Renderer for RGB images using AWT Image.
 */
public class VideoRndrr implements VideoRenderer,ParserIntrfce {

    /**
     * ***********************************************************************
     * Variables and Constants
     * ***********************************************************************
     */
    // The descriptive name of this renderer
    private static final String name = "Video Component Renderer";
    protected RGBFormat inputFormat;
    protected RGBFormat supportedRGB;
    protected Format[] supportedFormats;
    protected MemoryImageSource sourceImage;
    protected Image destImage;
    protected Image renderedImage;
    protected Buffer lastBuffer = null;
    protected int inWidth = 0;
    protected int inHeight = 0;
    protected Component component = null;
    protected Rectangle reqBounds = null;
    protected Rectangle bounds;// = new Rectangle();
    protected boolean started;// = false;
    private FrameParser videoParser;

    /**
     * ***********************************************************************
     * Constructor
     * ***********************************************************************
     */
    public VideoRndrr() {
        this(null);
    }

    public VideoRndrr(FrameParser myFrameParser) {
        videoParser = myFrameParser;
        System.out.println("VideoRndrr() ");
        bounds = new Rectangle();
        // Prepare supported input formats and preferred format
        int rMask = 0x000000FF;
        int gMask = 0x0000FF00;
        int bMask = 0x00FF0000;

        supportedRGB = new RGBFormat(null, // size
                Format.NOT_SPECIFIED, // maxDataLength
                int[].class, // buffer type
                Format.NOT_SPECIFIED, // frame rate
                32, // bitsPerPixel
                rMask, gMask, bMask, // component masks
                1, // pixel stride
                Format.NOT_SPECIFIED, // line stride
                Format.FALSE, // flipped
                Format.NOT_SPECIFIED // endian
                );
        supportedFormats = new VideoFormat[1];
        supportedFormats[0] = supportedRGB;
    }

    public void setParser(FrameParser myParser) {
        videoParser = myParser;
    }

    /**
     * **************************************************************
     * Controls implementation
     * **************************************************************
     */
    /**
     * Returns an array of supported controls
     *
     */
    public Object[] getControls() {
        // No controls
        return (Object[]) new Control[0];
    }

    /**
     * Return the control based on a control type for the PlugIn.
     */
    public Object getControl(String controlType) {
        try {
            Class cls = Class.forName(controlType);
            Object cs[] = getControls();
            for (int i = 0; i < cs.length; i++) {
                if (cls.isInstance(cs[i])) {
                    return cs[i];
                }
            }
            return null;
        } catch (Exception e) {   // no such controlType or such control
            return null;
        }
    }

    /**
     * ***********************************************************************
     * PlugIn implementation
     * ***********************************************************************
     */
    public String getName() {
        return name;
    }

    /**
     * Opens the plugin
     */
    public void open() throws ResourceUnavailableException {
        sourceImage = null;
        destImage = null;
        lastBuffer = null;
        renderedImage = null;
    }

    /**
     * Resets the state of the plug-in. Typically at end of media or when media
     * is repositioned.
     */
    public void reset() {
        // Nothing to do
    }

    public void close() {
        // Nothing to do
    }

    /**
     * ***********************************************************************
     * Renderer implementation
     * ***********************************************************************
     */
    public void start() {
        started = true;
    }

    public void stop() {
        started = false;
    }

    /**
     * Lists the possible input formats supported by this plug-in.
     */
    public Format[] getSupportedInputFormats() {
        return supportedFormats;
    }

    /**
     * Set the data input format.
     */
    public Format setInputFormat(Format format) {
        if (format != null && format instanceof RGBFormat
                && format.matches(supportedRGB)) {

            inputFormat = (RGBFormat) format;
            Dimension size = inputFormat.getSize();
            inWidth = size.width;
            inHeight = size.height;
            return format;
        } else {
            return null;
        }
    }

    /**
     * Processes the data and renders it to a component
     */
    public synchronized int process(Buffer buffer) {
        //System.out.println("VideoRndrr : processing ... ");
        if (component == null) {
            return BUFFER_PROCESSED_FAILED;
        }

        Format inf = buffer.getFormat();
        if (inf == null) {
            return BUFFER_PROCESSED_FAILED;
        }


        if (inf != inputFormat || !buffer.getFormat().equals(inputFormat)) {
            if (setInputFormat(inf) != null) {
                return BUFFER_PROCESSED_FAILED;
            }
        }

        Object data = buffer.getData();
        if (!(data instanceof int[])) {
            return BUFFER_PROCESSED_FAILED;
        }
        //System.out.println("VideoRndrr test 0");
        if (lastBuffer != buffer) {
            lastBuffer = buffer;
            newImage(buffer);
        }

        //sourceImage.newPixels(0, 0, inWidth, inHeight);

        
        Graphics g = component.getGraphics();
        if (g != null) {
            if (reqBounds == null) {
                bounds = component.getBounds();
                bounds.x = 0;
                bounds.y = 0;
            } else {
                bounds = reqBounds;
            }

            g.drawImage(renderedImage, bounds.x, bounds.y,
                    bounds.width, bounds.height,
                    0, 0, inWidth, inHeight, component);
            
        }
        
        /*
         * 
         */
        return BUFFER_PROCESSED_OK;
    }

    /**
     * **************************************************************
     * VideoRenderer implementation
     * **************************************************************
     */
    /**
     * Returns an AWT component that it will render to. Returns null if it is
     * not rendering to an AWT component.
     */
    public Component getComponent() {
        if (component == null) {
            component = new Canvas() {
                public Dimension getPreferredSize() {
                    return new Dimension(getInWidth(), getInHeight());
                }

                public void update(Graphics g) {
                }

                public void paint(Graphics g) {
                    // TODO: Need to repaint image if the movie is in paused state
                }
            };
        }

        return component;
    }

    /**
     * Requests the renderer to draw into a specified AWT component. Returns
     * false if the renderer cannot draw into the specified component.
     */
    public boolean setComponent(java.awt.Component comp) {
        component = comp;
        return true;
    }

    /**
     * Sets the region in the component where the video is to be rendered to.
     * Video is to be scaled if necessary. If
     * <code>rect</code> is null, then the video occupies the entire component.
     */
    public void setBounds(java.awt.Rectangle rect) {
        reqBounds = rect;
    }

    /**
     * Returns the region in the component where the video will be rendered to.
     * Returns null if the entire component is being used.
     */
    public java.awt.Rectangle getBounds() {
        return reqBounds;
    }

    /**
     * ***********************************************************************
     * Local methods
     * ***********************************************************************
     */
    int getInWidth() {
        return inWidth;
    }

    int getInHeight() {
        return inHeight;
    }

    private void newImage(Buffer buffer) {

        Object data = buffer.getData();
        if (!(data instanceof int[])) {
            return;
        }
        RGBFormat format = (RGBFormat) buffer.getFormat();

        DirectColorModel dcm = new DirectColorModel(format.getBitsPerPixel(),
                format.getRedMask(),
                format.getGreenMask(),
                format.getBlueMask());

        sourceImage = new MemoryImageSource(format.getLineStride(),
                format.getSize().height,
                dcm,
                (int[]) data, 0,
                format.getLineStride());
        ///sourceImage.setAnimated(true);
        ///sourceImage.setFullBufferUpdates(true);
        //System.out.println("VideoRndrr ... test1");
        if (component != null) {

            destImage = component.createImage(sourceImage);
            //System.out.println("VideoRndrr ... test2");
            //component.createImage(format.getSize().width, format.getSize().height);
            //videoOpticalFlowFltr.setImageComponent(component);
            //renderedImage = videoOpticalFlowFltr.cornerDetect(destImage,40000,3);
            videoParser.setInputImage(destImage);
            videoParser.bgnFrames();
            videoParser.parse();
            videoParser.endFrames();

            Image outputImage = videoParser.getOutputImage();

            //Image anImage = component.createImage(format.getSize().width, format.getSize().height);
            //Graphics aGraphics = anImage.getGraphics();
            //aGraphics.drawImage(outputImage, 0, 0, null);            
            renderedImage = outputImage;

            //component.prepareImage(renderedImage, component);
        }
    }
    /*
    public Image getOutputImage(Component aComponent) {
        Graphics aGraphics =
                null;
        Image anImage = null;

        if (aComponent != null) {
            anImage =
                    aComponent.createImage(320, 180);
            aGraphics =
                    anImage.getGraphics();
            aGraphics.drawImage(outputImage, 0, 0, null);
        } else {
            anImage = outputImage;
        }
        return anImage;
    }*/
}
