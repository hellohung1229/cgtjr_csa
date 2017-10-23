/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general.upload;

/**
 *
 * @author finitesystem
 */
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.imageio.ImageIO;

//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpStatus;
//import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
//import org.apache.commons.httpclient.methods.PostMethod;

public class FileDirUpload {

    /**
     * Created on Oct 29, 2009
     */

    public void theProgram() {
/*
        // how ever you get your image object, like if its generated, or drawn.
        BufferedImage bimg = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);

        // now that have the image, do the upload to the server.
        try {
            int statusCode = uploadImage(bimg, "http://your.server.example.com/uploadImageHandler", "myImage.png");
            System.out.println("HTTP result code: " + statusCode);
        } catch (IOException ex) {
            // failed to upload 
        }
        */ 
    }

    /**
     * uses commons-httpclient to send it to your server
     *
     * @param bimg an image object to be uploaded
     * @param uploadUrl the HTTP url to our server-side handler to receive the
     * image stream and do what ever with it.
     * @param targetFileName, the name we want this image saved as, such as in
     * the uploads folder on the server.
     * @return
     */
    /*
    public int uploadImage(RenderedImage bimg, String uploadUrl, String targetFileName) throws IOException {
        int statusCode = HttpStatus.SC_INTERNAL_SERVER_ERROR;

        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(uploadUrl);

        PipedOutputStream pout = new PipedOutputStream();
        PipedInputStream pin = new PipedInputStream(pout);

        // set any other request headers  
        postMethod.setRequestHeader("Content-Type", "image/png");

        // some header we invent to have the server able to save this stream to a file
        // the server-side handler will need to look for this.
        postMethod.setRequestHeader("Target-File-Name", targetFileName);
        postMethod.setRequestEntity(new InputStreamRequestEntity(pin));
        ImageIO.write(bimg, "png", pout);

        try {
            statusCode = httpClient.executeMethod(postMethod);
        } finally {
            postMethod.releaseConnection();
        }

        return statusCode;
    }*/
}
