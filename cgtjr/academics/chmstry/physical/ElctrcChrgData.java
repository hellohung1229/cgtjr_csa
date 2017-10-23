package cgtjr.academics.chmstry.physical;

import java.io.*;
import java.util.*;
import java.net.*;

public class ElctrcChrgData
{
   private boolean connectStatus = false;
   private String fileName;
   private HashMap dipoleHashMap;

   public ElctrcChrgData()
   {
      dipoleHashMap = new HashMap();
   }
   public HashMap rdDipoleFile(String myFileName)
   {
      InputStream anInputStream = null;
      URL aURL = null;
      String aLine  = null;
      BufferedReader reader = null;

   	try {
         if(
            (myFileName != null && myFileName.toLowerCase().startsWith("http:")) ||
            (myFileName != null && myFileName.toLowerCase().startsWith("https:")) ||
            (myFileName != null && myFileName.toLowerCase().startsWith("file:")) ||
            (myFileName != null && myFileName.toLowerCase().startsWith("ftp:"))
           )
         {
            aURL = new URL(myFileName);
	      anInputStream = aURL.openStream();
         }else{
            anInputStream = new FileInputStream(myFileName);
         }
	   reader = new BufferedReader(new InputStreamReader(anInputStream));
	   aLine = reader.readLine();
	   while (aLine != null) {
            parseLineByToken(aLine);
		aLine = reader.readLine();
	   }
	   anInputStream.close();
	} catch (Exception e) {
	    System.out.println("DipoleMomentFile: file error"+e.getMessage());
          System.exit(0);
	}
	return dipoleHashMap;
   }
   public HashMap rdElctrcChrgFile(String myFileName)
   {
      InputStream anInputStream = null;
      URL aURL = null;
      String aLine  = null;
      BufferedReader reader = null;

   	try {
         if(
            (myFileName != null && myFileName.toLowerCase().startsWith("http:")) ||
            (myFileName != null && myFileName.toLowerCase().startsWith("https:")) ||
            (myFileName != null && myFileName.toLowerCase().startsWith("file:")) ||
            (myFileName != null && myFileName.toLowerCase().startsWith("ftp:"))
           )
         {
            aURL = new URL(myFileName);
	      anInputStream = aURL.openStream();
         }else{
            anInputStream = new FileInputStream(myFileName);
         }
	   reader = new BufferedReader(new InputStreamReader(anInputStream));
	   aLine = reader.readLine();
	   while (aLine != null) {
            parseLineByToken(aLine);
		aLine = reader.readLine();
	   }
	   anInputStream.close();
	} catch (Exception e) {
	    System.out.println("DipoleMomentFile: file error"+e.getMessage());
          System.exit(0);
	}
	return dipoleHashMap;
   }
   public void parseLineByToken(String myData)
   {
      System.out.println("DipoleMomentFile.parseLineByToken():"+myData);

      String tempStr = "";
      StringTokenizer aStringTokenizer = new StringTokenizer(myData,",");

      String atom1 = aStringTokenizer.nextToken();
      String atom2 = aStringTokenizer.nextToken();
      String value = aStringTokenizer.nextToken();
      String molecule = atom1+"-"+atom2;
      dipoleHashMap.put(molecule,new Float(value));
   }
   public static void main(String args[])
   {
      String fileName= "data/chemistry/physical/dipoledata.csv";
      ElctrcChrgData aDipoleMomentFile = new ElctrcChrgData();
      aDipoleMomentFile.rdDipoleFile(fileName);
   }
}