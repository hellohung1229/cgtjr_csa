package cgtjr.academics.chmstry.gui.app;

import java.awt.*;
import javax.swing.*;

import cgtjr.academics.general.*;
import cgtjr.academics.chmstry.gui.tab.*;
import cgtjr.academics.math.geometry.gui.app.GmtryApplt;
import java.net.URL;

public class ChmstryApp extends JApplet {

    VirtualInputDevice theDevice;
    FileTextField aFileTextField;
    public static URL codeBaseURL;

    public ChmstryApp() {
    }
    
    public static URL getCodeBaseURL()
    {
        return codeBaseURL;
    }
    public static void setCodeBaseURL(URL myURL){
        codeBaseURL = myURL;
    }
    
    public void init() {

        codeBaseURL = getCodeBase();
        System.out.println("GmtryApplt: codeBaseURL = "+codeBaseURL);        
        GmtryApplt.setCodeBaseURL(codeBaseURL);
        crtUI();
    }

    public void crtUI() {
        setLayout(new BorderLayout());

        SceneRoot aSceneRoot = new SceneRoot();
        aSceneRoot.setIsPickable(false);
        ChmstryStation aChmstryStation = new ChmstryStation(aSceneRoot, this);
        ChmstryTab anChmstryTab = new ChmstryTab(aSceneRoot);
        String anAppTabNm = anChmstryTab.getClass().getName();

        aChmstryStation.insertLabPanel(anAppTabNm, anChmstryTab);
        aChmstryStation.displayLabPanel(anAppTabNm);

        add(BorderLayout.CENTER, aChmstryStation);
        setVisible(true);
    }

    public void destroy() {
    }

    public static void main(String args[]) {
        JFrame aJFrame = new JFrame();

        aJFrame.setLayout(new BorderLayout());

        SceneRoot aSceneRoot = new SceneRoot();
        aSceneRoot.setIsPickable(false);
        ChmstryStation aChmstryStation = new ChmstryStation(aSceneRoot);
        ChmstryTab anChmstryTab = new ChmstryTab(aSceneRoot);
        String anAppTabNm = anChmstryTab.getClass().getName();

        aChmstryStation.insertLabPanel(anAppTabNm, anChmstryTab);
        aChmstryStation.displayLabPanel(anAppTabNm);

        aJFrame.add(BorderLayout.CENTER, aChmstryStation);
        aJFrame.setVisible(true);
        
        
        //aJFrame.addWindowFocusListener(new WL());
        aJFrame.add(aChmstryStation, BorderLayout.CENTER);
        aJFrame.setSize(500, 600);
        //aChmstryApp.init();
        //aJFrame.setVisible(true);
    }
}