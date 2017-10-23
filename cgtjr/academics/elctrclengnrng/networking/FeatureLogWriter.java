/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.networking;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author cgthomasjr
 */
public class FeatureLogWriter {

    public static void main(String[] args) {
        FeatureLogWriter aFeatureLogWrite = new FeatureLogWriter();
        aFeatureLogWrite.append();
    }   
    public void append() {
        String aData = "text in the file to append";
        append(aData);
    }
    public void append(String myData) {            
        try {
            String path = "c:\\Users\\cgthomasjr\\sample.txt";

            File file = new File(path);

            FileWriter fileWriter = new FileWriter(file, true);

            BufferedWriter bufferFileWriter = new BufferedWriter(fileWriter);

            fileWriter.append(myData);

            bufferFileWriter.close();

            System.out.println("User Registration Completed");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}