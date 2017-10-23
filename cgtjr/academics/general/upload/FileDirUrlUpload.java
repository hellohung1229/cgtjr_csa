/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general.upload;

/**
 *
 * @author finitesystem
 */
import cgtjr.academics.general.gui.UgotImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class FileDirUrlUpload implements Runnable {

    private URL urlInfo;
    private File fileData;
    private String fileName;
    private HashMap<String, String> parameters;
    private BufferedImage bi;

    public void uploadData(URL myUrlInfo, File myFileData, String myFileName, HashMap<String, String> myParameters) {
        urlInfo = myUrlInfo;
        fileData = myFileData;
        bi = null;
        fileName = myFileName;
        parameters = myParameters;
        Thread aThread = new Thread(this);
        aThread.start();
    }
    public void uploadData(URL myUrlInfo, BufferedImage myBufferedImage, String myFileName, HashMap<String, String> myParameters) {
        urlInfo = myUrlInfo;
        fileData = null;
        bi = myBufferedImage;
        fileName = myFileName;
        parameters = myParameters;
        Thread aThread = new Thread(this);
        aThread.start();
    }
    public void run() {
        try {
            if (bi == null && fileData != null) {
                uploadData();
            } else if (bi != null && fileData == null) {
                sendData();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private String uploadData()
            throws IOException {
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "AaB03x87yxdkjnxvi7";

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        FileInputStream fileInputStream = null;

        byte[] buffer;
        int maxBufferSize = 20 * 1024;
        try {
            //------------------ CLIENT REQUEST
            fileInputStream = new FileInputStream(fileData);

            // open a URL connection to the Servlet
            // Open a HTTP connection to the URL
            conn = (HttpURLConnection) urlInfo.openConnection();
            // Allow Inputs
            conn.setDoInput(true);
            // Allow Outputs
            conn.setDoOutput(true);
            // Don't use a cached copy.
            conn.setUseCaches(false);
            // Use a post method.
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"" + fileName
                    + "\"; filename=\"" + fileData.toString() + "\"" + lineEnd);
            dos.writeBytes("Content-Type: image/jpeg" + lineEnd);
            dos.writeBytes(lineEnd);

            // create a buffer of maximum size
            buffer = new byte[Math.min((int) fileData.length(), maxBufferSize)];
            int length;
            // read file and write it into form...

            while ((length = fileInputStream.read(buffer)) != -1) {
                dos.write(buffer, 0, length);
            }

            for (String name : parameters.keySet()) {
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                dos.writeBytes(parameters.get(name));
            }

            // send multipart form data necessary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            dos.flush();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (dos != null) {
                dos.close();
            }
        }
        //------------------ read the SERVER RESPONSE
        try {
            dis = new DataInputStream(conn.getInputStream());
            StringBuilder response = new StringBuilder();

            String line;
            while ((line = dis.readLine()) != null) {
                response.append(line).append('\n');
            }
            return response.toString();
        } finally {
            if (dis != null) {
                dis.close();
            }
        }
    }

    private String sendData()
            throws IOException {
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "AaB03x87yxdkjnxvi7";

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        FileInputStream fileInputStream = null;

        byte[] buffer;
        int maxBufferSize = 20 * 1024;
        try {
            //------------------ CLIENT REQUEST
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "JPEG", baos);
            byte[] buf = baos.toByteArray();

            // open a URL connection to the Servlet
            // Open a HTTP connection to the URL
            conn = (HttpURLConnection) urlInfo.openConnection();
            // Allow Inputs
            conn.setDoInput(true);
            // Allow Outputs
            conn.setDoOutput(true);
            // Don't use a cached copy.
            conn.setUseCaches(false);
            // Use a post method.
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"" + fileName
                    + "\"; filename=\"" + "test.jpg" + "\"" + lineEnd);
            dos.writeBytes("Content-Type: image/jpeg" + lineEnd);
            dos.writeBytes(lineEnd);

            // create a buffer of maximum size
            //buffer = new byte[Math.min((int) fileData.length(), maxBufferSize)];
            //int length;
            // read file and write it into form...
            /*
             while ((length = fileInputStream.read(buffer)) != -1) {
             dos.write(buffer, 0, length);
             }*/
            dos.write(buf);

            for (String name : parameters.keySet()) {
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                dos.writeBytes(parameters.get(name));
            }

            // send multipart form data necessary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            dos.flush();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (dos != null) {
                dos.close();
            }
        }
        //------------------ read the SERVER RESPONSE
        try {
            dis = new DataInputStream(conn.getInputStream());
            StringBuilder response = new StringBuilder();

            String line;
            while ((line = dis.readLine()) != null) {
                response.append(line).append('\n');
            }
            return response.toString();
        } finally {
            if (dis != null) {
                dis.close();
            }
        }
    }
    /*
     public void sendData() {
     try {
     //BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

     ByteArrayOutputStream baos = new ByteArrayOutputStream();
     ImageIO.write(bi, "JPEG", baos);
     byte[] buf = baos.toByteArray();
     //URL url = new URL(urlInfo);
     URL url = urlInfo;
     HttpURLConnection con = (HttpURLConnection) url.openConnection();

     con.setDoInput(true);
     con.setDoOutput(true);
     con.setRequestMethod("POST");
     con.setRequestProperty("Content-Type", "application/octet-stream");
     OutputStream os = con.getOutputStream();
     os.write(buf);
     os.flush();
     os.close();
     InputStream is = con.getInputStream();
     int c;
     while ((c = is.read()) != -1) {
     System.out.write(c);
     }
     System.out.println();
     is.close();
     //os.close();
     con.disconnect();
     } catch (Exception e) {
     System.out.println(e.toString());
     // System.out.println(e);   
     }
     }*/
}
