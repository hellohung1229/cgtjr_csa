/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

/**
 *
 * @author clayton g thomas jr
 */
import javax.swing.*;
import java.util.*;

public class ButtonTool {
   
   public static String rtrvSlctdJRB(ButtonGroup aButtonGroup)
   {
      JRadioButton aJRadioButton = null;
      String jBttnNm = "";
      Enumeration anEnumeration = aButtonGroup.getElements();
      while(anEnumeration.hasMoreElements())
      {
         aJRadioButton = (JRadioButton) anEnumeration.nextElement();
         if(aJRadioButton.isSelected())
         {
            jBttnNm = aJRadioButton.getName();
         }
      }
      return jBttnNm;
   }
}
