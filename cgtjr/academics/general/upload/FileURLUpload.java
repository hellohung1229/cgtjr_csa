/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general.upload;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author finitesystem
 */
public class FileURLUpload {

    private String webUrl;

    FileURLUpload() {
        try {
            URL url = new URL(webUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(20000);// milliseconds
            conn.setConnectTimeout(25000);// milliseconds
            conn.setRequestMethod("POST");
        } catch (MalformedURLException ex) {
            Logger.getLogger(FileURLUpload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileURLUpload.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
