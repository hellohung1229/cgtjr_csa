/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.annotate;

import cgtjr.academics.elctrclengnrng.networking.HTTPTransmit;
import cgtjr.academics.general.ThemeSet;
import java.util.HashMap;

/**
 *
 * @author finitesystem
 */
public class FrameAnnotator implements Runnable {

    private AnnotateFeatureVar theAnnotationBoxVar;
    private int frameNumber;
    private int frameDelay = 90;

    public FrameAnnotator() {
        theAnnotationBoxVar = new AnnotateFeatureVar();
    }
    public void process(String myDate, int myFrameNumber, int myWidth, int myHeight, int myIDNmbr, int myXMin, int myYMin, int myXMax, int myYMax, String myDescription) {    
       process(myDate,myFrameNumber,myWidth,myHeight,myIDNmbr,myXMin,myYMin,myXMax,myYMax,-1,-1,myDescription);    
    }
    //public void process(String myDate, int myFrameNumber, int myWidth, int myHeight, int myXMin, int myYMin, int myXMax, int myYMax, String myDescription) {

    public void process(String myDate, int myFrameNumber, int myWidth, int myHeight, int myIDNmbr, int myXMin, int myYMin, int myXMax, int myYMax, float myObjectHeight,float myObjectWidth,String myDescription){
       frameNumber = myFrameNumber;
        theAnnotationBoxVar.setUserLogonIdValue(ThemeSet.getUserLogonIdValue());
        theAnnotationBoxVar.setDateTimeValue(myDate);
        theAnnotationBoxVar.setFrameNumberValue("" + myFrameNumber);
        theAnnotationBoxVar.setWidthValue("" + myWidth);
        theAnnotationBoxVar.setHeightValue("" + myHeight);
        theAnnotationBoxVar.setTrackNumberValue(""+myIDNmbr);
        theAnnotationBoxVar.setXMinCoordValue("" + myXMin);
        theAnnotationBoxVar.setYMinCoordValue("" + myYMin);
        theAnnotationBoxVar.setYMinCoordValue("0");        
        theAnnotationBoxVar.setXMaxCoordValue("" + myXMax);
        theAnnotationBoxVar.setYMaxCoordValue("" + myYMax);
        theAnnotationBoxVar.setZMaxCoordValue("0");        
        theAnnotationBoxVar.setDescriptionValue("" + myDescription);
        theAnnotationBoxVar.setObjectHeightValue(""+myObjectHeight);
        theAnnotationBoxVar.setObjectWidthValue(""+myObjectWidth);        
        theAnnotationBoxVar.setUrlValue("http://featuredetection.appspot.com/admin/databasecreation/DataBaseDMDL.jsp");        
        startAnnotation();
    }
   public void process(AnnotateFeatureVar myAnnotationBoxVar) {    
        //frameNumber = Integer.valueOf(myAnnotationBoxVar.getFrameNumberValue()).intValue();
        theAnnotationBoxVar.setUserLogonIdValue(ThemeSet.getUserLogonIdValue());
        theAnnotationBoxVar.setDateTimeValue(myAnnotationBoxVar.getDateTimeValue());
        theAnnotationBoxVar.setFrameNumberValue(myAnnotationBoxVar.getFrameNumberValue());       
        theAnnotationBoxVar.setWidthValue(myAnnotationBoxVar.getWidthValue());
        theAnnotationBoxVar.setHeightValue(myAnnotationBoxVar.getHeightValue());
        theAnnotationBoxVar.setTrackNumberValue(myAnnotationBoxVar.getTrackNumberValue());
        theAnnotationBoxVar.setXMinCoordValue(myAnnotationBoxVar.getXMinCoordValue());
        theAnnotationBoxVar.setYMinCoordValue(myAnnotationBoxVar.getYMinCoordValue());
        theAnnotationBoxVar.setXMaxCoordValue(myAnnotationBoxVar.getYMaxCoordValue());
        theAnnotationBoxVar.setYMaxCoordValue(myAnnotationBoxVar.getYMaxCoordValue());
        theAnnotationBoxVar.setEigenThresholdValue(myAnnotationBoxVar.getEigenThresholdValue());
        theAnnotationBoxVar.setFeatureCountValue(myAnnotationBoxVar.getFeatureCountValue());
        theAnnotationBoxVar.setDescriptionValue(myAnnotationBoxVar.getDescriptionValue()); 
        theAnnotationBoxVar.setUrlValue("http://featuredetection.appspot.com/admin/databasecreation/DataBaseDMDL.jsp");
        
        startAnnotation();
    }
    public void startAnnotation() {
        int aFrameDelayNmbr = frameNumber % frameDelay;
        if (aFrameDelayNmbr == 0) {
            Thread aThread = new Thread(this);
            aThread.start();
        }
    }

    public void run() {
        String output = ""
                + theAnnotationBoxVar.getDateTimeValue() + ", "
                + theAnnotationBoxVar.getUserLogonIdValue()+ ", "                
                + theAnnotationBoxVar.getFrameNumberValue() + ", "
                + theAnnotationBoxVar.getWidthValue() + ", "
                + theAnnotationBoxVar.getHeightValue() + ", "
                + theAnnotationBoxVar.getTrackNumberValue() + ", "                
                + theAnnotationBoxVar.getXMinCoordValue() + ", "
                + theAnnotationBoxVar.getYMinCoordValue() + ", "
                + theAnnotationBoxVar.getXMaxCoordValue() + ", "
                + theAnnotationBoxVar.getYMaxCoordValue() + ", "
                + theAnnotationBoxVar.getFeatureCountValue() + ", " 
                + theAnnotationBoxVar.getEigenThresholdValue() + ", "                
                + theAnnotationBoxVar.getDescriptionValue();
        System.out.println(output);
        HTTPTransmit aHTTPTransmit = new HTTPTransmit();
        HashMap aHashMap = new HashMap();
        aHashMap.put(theAnnotationBoxVar.getUserLogonIdKey(), theAnnotationBoxVar.getUserLogonIdValue());
        aHashMap.put(theAnnotationBoxVar.getDateTimeKey(), theAnnotationBoxVar.getDateTimeValue());
        aHashMap.put(theAnnotationBoxVar.getFrameNumberKey(), theAnnotationBoxVar.getFrameNumberValue());
        aHashMap.put(theAnnotationBoxVar.getUrlKey(), theAnnotationBoxVar.getUrlValue());  
        aHashMap.put(theAnnotationBoxVar.getTrackNumberKey(), theAnnotationBoxVar.getTrackNumberValue());
        aHashMap.put(theAnnotationBoxVar.getWidthKey(), theAnnotationBoxVar.getWidthValue());        
        aHashMap.put(theAnnotationBoxVar.getHeightKey(), theAnnotationBoxVar.getHeightValue());
        aHashMap.put(theAnnotationBoxVar.getXMinCoordKey(), theAnnotationBoxVar.getXMinCoordValue());        
        aHashMap.put(theAnnotationBoxVar.getYMinCoordKey(), theAnnotationBoxVar.getYMinCoordValue());        
        aHashMap.put(theAnnotationBoxVar.getZMinCoordKey(), "0");                
        aHashMap.put(theAnnotationBoxVar.getXMaxCoordKey(), theAnnotationBoxVar.getXMaxCoordValue());                
        aHashMap.put(theAnnotationBoxVar.getYMaxCoordKey(), theAnnotationBoxVar.getYMaxCoordValue());                
        aHashMap.put(theAnnotationBoxVar.getZMaxCoordKey(), "0");                        
        aHashMap.put(theAnnotationBoxVar.getDescriptionKey(), theAnnotationBoxVar.getDescriptionValue());  
                  
        aHashMap.put("dmdlcommand","insert");  
        aHashMap.put("tablename","annotatefeature"); 
        
        aHTTPTransmit.post("http://featuredetection.appspot.com/admin/databasecreation/DataBaseDMDL.jsp", aHashMap);

    }
    public int getFrameNumber() {
        return frameNumber;
    }
    public void setFrameNumber(int myFrameNumber) {
        this.frameNumber = myFrameNumber;
    }
    public int getFrameDelay() {
        return frameDelay;
    }
    public void setFrameDelay(int myFrameDelay) {
        this.frameDelay = myFrameDelay;
    }    
}