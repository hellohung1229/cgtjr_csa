package cgtjr.academics.math.geometry.gui.app;

import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.math.geometry.gui.tab.GmtryTab;
import java.awt.Component;
import javax.swing.JApplet;

public class GmtryApp {

    private GmtryStation aGmtryStation;

    public GmtryApp() {
    }

    public Component crtUI() {
        SceneRoot aSceneRoot = new SceneRoot();
        aGmtryStation = new GmtryStation(aSceneRoot);

        GmtryTab anGmtryTab = new GmtryTab(aSceneRoot);
        String anAppTabNm = anGmtryTab.getClass().getName();

        aGmtryStation.insertLabPanel(anAppTabNm, anGmtryTab);
        aGmtryStation.displayLabPanel(anAppTabNm);

        return aGmtryStation;
    }

    public Component crtUI(JApplet myLabWndwPnl) {
        SceneRoot aSceneRoot = new SceneRoot();
        aGmtryStation = new GmtryStation(aSceneRoot, myLabWndwPnl);
        GmtryTab anGmtryTab = new GmtryTab(aSceneRoot, myLabWndwPnl);
        String anAppTabNm = anGmtryTab.getClass().getName();

        aGmtryStation.insertLabPanel(anAppTabNm, anGmtryTab);
        aGmtryStation.displayLabPanel(anAppTabNm);
        return aGmtryStation;
    }
}