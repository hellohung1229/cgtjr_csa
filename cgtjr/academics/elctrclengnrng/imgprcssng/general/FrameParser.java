/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.general;

/**
 *
 * @author clayton g thomas jr
 */
import java.awt.Image;

public interface FrameParser {

    public void parse();

    public void bgnFrames();

    public void endFrames();

    public void strtPrsng();

    public void fnshPrsng();

    public void setInputImage(Image anImage);

    public Image getOutputImage();

    public int getTopOffset();

    public void setTopOffset(int startIndex);

    public int getBottomOffset();

    public void setBottomOffset(int stopIndex);
}