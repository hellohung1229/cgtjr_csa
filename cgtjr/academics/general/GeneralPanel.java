package cgtjr.academics.general;

import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

public class GeneralPanel extends JPanel {
   private URL documentURL = null;
   Canvas3DUniverse canvas3DUniv;
   JEditorPane editPanel;
   FileTextField urlTextField;

   public GeneralPanel()
   {
      super();
   }

   public GeneralPanel(Canvas3DUniverse myCanvas3DUniverse) 
   {
      super();
      canvas3DUniv = myCanvas3DUniverse;
   }
   public GeneralPanel(Canvas3DUniverse myCanvas3DUniverse,FileTextField myFileTextField,JEditorPane myJEditorPane)
   {
      super();
      canvas3DUniv = myCanvas3DUniverse;
      urlTextField = myFileTextField;
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
   public void setCanvas3DUniv(Canvas3DUniverse myCanvas)
   {
      canvas3DUniv = myCanvas;
   }
   public Canvas3DUniverse getCanvas3DUniv()
   {
      return canvas3DUniv;
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