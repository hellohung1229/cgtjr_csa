/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

/**
 *
 * @author clayton g thomas jr
 */
import javax.vecmath.*;

import javax.media.j3d.*;


public class BGNode
{
   public static Background rtrvBGNode(BoundingSphere myBounds)
   {
      //Color3f bgColor = new Color3f(0.94f, 0.94f,0.94f);
      Color3f bgColor = new Color3f(1.0f, 1.0f,1.0f);
      Background bgNode = new Background(bgColor);
      bgNode.setApplicationBounds(myBounds);
      return bgNode;
   }
}