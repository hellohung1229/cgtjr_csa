package cgtjr.academics.general;

import java.awt.TextField; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileTextField extends TextField implements ActionListener
{
   SceneRoot theSceneRoot;

   public FileTextField(SceneRoot mySceneRoot)
   {
      theSceneRoot = mySceneRoot;
      setText("moleculewts.jar!/data/pdb/glyhelix1.ml2");
      addActionListener(this);
   }
   public void actionPerformed(ActionEvent e)
   {
      String aFileName = getText();
      theSceneRoot.loadScene(aFileName);
   }
}