package cgtjr.academics.math.geometry.gui.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.net.URL;
import javax.swing.JApplet;

public class GmtryApplt extends JApplet {

    public static URL codeBaseURL;
    GmtryApp aGmtryApp;

    public GmtryApplt() {
        aGmtryApp = new GmtryApp();
    }

    public static URL getCodeBaseURL() {
        return codeBaseURL;
    }

    public static void setCodeBaseURL(URL myURL) {
        System.out.println("GmtryApplet.setCodeBaseURL : " + myURL);
        codeBaseURL = myURL;
    }

    public void init() {

        //GmtryApp aGmtryApp = new GmtryApp();
        codeBaseURL = getCodeBase();
        setCodeBaseURL(codeBaseURL);
        Component aComponent = aGmtryApp.crtUI(this);
        add(BorderLayout.CENTER, aComponent);
        setVisible(true);
    }

    public void destroy() {
    }
}