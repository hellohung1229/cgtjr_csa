/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

import javax.media.j3d.*;

/**
 *
 * @author clayton g thomas jr
 */

public class ApprncG3D extends Appearance
{
   Material aMaterial;
   public ApprncG3D()
   {
      aMaterial = new Material();
      aMaterial.setDiffuseColor(.5f, .2f, .1f, 1f);
      aMaterial.setAmbientColor(.5f, .2f, .1f);
      setMaterial(aMaterial);
   }
}
