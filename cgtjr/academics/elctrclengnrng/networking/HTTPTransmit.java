/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author finitesystem
 */
public class HTTPTransmit {

    public void post3(String url, HashMap myHashMap) {
        try {
            URL u = new URL(url);
            URLConnection c = u.openConnection();

            c.setDoOutput(true);
            if (c instanceof HttpURLConnection) {
                ((HttpURLConnection) c).setRequestMethod("POST");
            }

            OutputStreamWriter out = new OutputStreamWriter(
                    c.getOutputStream());

            // output your data here

            addRequestProperties(c, myHashMap);
            out.close();

            /*
             BufferedReader in = new BufferedReader(
             new InputStreamReader(
             c.getInputStream()));

             String s = null;
             while ((s = in.readLine()) != null) {
             System.out.println(s);
             }
             in.close();
             */
        } catch (MalformedURLException ex) {
            Logger.getLogger(HTTPTransmit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HTTPTransmit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addRequestProperties(URLConnection myURLConnection, HashMap aHashMap) {
        String aKey = " ";
        String aValue = " ";

        Iterator theColumns1 = aHashMap.keySet().iterator();

        while (theColumns1.hasNext()) {
            aKey = (String) theColumns1.next();
            aValue = (String) aHashMap.get(aKey);
            myURLConnection.addRequestProperty(aKey, aValue);
        }
    }

    public void post2() {

        URL url = null;
        try {
            url = new URL("http://php");
        } catch (MalformedURLException me) {
        }
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.println("Name=vidhi\r\n");
            out.close();

        } catch (IOException ie) {
        }
        //getAppletContext().showDocument(url);

    }

    public static void post(String myURL, HashMap myHashMap) {
        URL url;
        try {
            url = new URL(myURL);

            LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
            params.putAll(myHashMap);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) {
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            for (int c; (c = in.read()) >= 0; System.out.print((char) c));
        } catch (MalformedURLException ex) {
            Logger.getLogger(HTTPTransmit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HTTPTransmit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HTTPTransmit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
