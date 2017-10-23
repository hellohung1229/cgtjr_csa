package cgtjr.academics.general;

import javax.swing.*;
import java.net.*;


public class GeneralTab extends JPanel {
   private URL documentURL = null;
   SceneRoot scnRoot;
   JEditorPane editPanel;
   FileTextField urlTextField;

   public GeneralTab()
   {
   }
   public GeneralTab(SceneRoot mySceneRoot,FileTextField myFileTextField,JEditorPane myJEditorPane)
   {
      scnRoot = mySceneRoot;
      urlTextField = myFileTextField;
      editPanel = myJEditorPane;
   }
   public GeneralTab(SceneRoot mySceneRoot,JEditorPane myJEditorPane)
   {
      scnRoot = mySceneRoot;
      editPanel = myJEditorPane;
   }
    public void setDocumentURL(URL myURL)
    {
       documentURL = myURL;
    }
    public URL getDocumentURL()
    {
       return documentURL;
    }
   public void setScnRoot(SceneRoot myCanvas)
   {
      scnRoot = myCanvas;
   }
   public SceneRoot getScnRoot()
   {
      return scnRoot;
   }
   public void setEditorPanel(JEditorPane myEditPane)
   {
      editPanel = myEditPane;
   }
   public JEditorPane getEditorPanel()
   {
      return editPanel;
   }
   public void setUrlTextField(FileTextField myFileTextField)
   {
      urlTextField = myFileTextField;
   }
   public FileTextField getUrlTextField()
   {
      return urlTextField;
   }
}