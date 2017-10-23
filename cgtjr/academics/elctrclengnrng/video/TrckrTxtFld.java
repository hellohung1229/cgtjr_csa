/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.video;

import javax.swing.JTextField;

/**
 *
 * @author clayton g thomas jr
 */
public class TrckrTxtFld
{
   private static JTextField txtFld;

   public TrckrTxtFld()
   {
      txtFld = new JTextField();
   }
   public static void setText(String myInfo)
   {
      txtFld.setText(myInfo);
   }
   public JTextField getTxtFld()
   {
      return txtFld;
   }
}