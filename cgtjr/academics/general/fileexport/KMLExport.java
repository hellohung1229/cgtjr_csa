/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general.fileexport;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.elmnt.LinePnts;
import cgtjr.academics.math.geometry.elmnt.QuadPnts;
import java.util.Vector;
import javax.swing.JEditorPane;

/**
 *
 * @author clayton g thomas jr
 */
public class KMLExport {

   private static String glblIndex = "";
   private static String glblCrdnt = "";
   private static String colorVls = "";

   public static void prntKMLByLines(Vector myVector,JEditorPane myJEditorPane)
   {
      String info ="";
      LinePnts aLinePnts = null;
      Pnt aPoint0 = null;
      Pnt aPoint1 = null;
      int aSize = myVector.size();

      //System.out.println("LinePnts.prntGlblNmbrs(): size = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         aLinePnts = (LinePnts)myVector.elementAt(i);
         aPoint0 = aLinePnts.getPoint0();
         aPoint1 = aLinePnts.getPoint1();
         info = i+", "+aPoint0.getCounter()+" ,"+aPoint1.getCounter()+", "+aPoint0.getX1()+", "+aPoint0.getY1()+", "+aPoint0.getZ1()+", "+aPoint1.getX1()+", "+aPoint1.getY1()+", "+aPoint1.getZ1();
         if(myJEditorPane != null)
         {
            myJEditorPane.setText(myJEditorPane.getText()+info+"\n");
         }else{
            System.out.println(info);
         }
      }
   }
   public static void prntKMLByQuads(Vector myVector,JEditorPane myJEditorPane)
   {

      String info = "";
      QuadPnts aQuadPnts = null;
      Pnt aPoint1 = null;
      Pnt aPoint2 = null;
      Pnt aPoint3 = null;
      Pnt aPoint4 = null;
      int aSize = myVector.size();
      System.out.println("QuadPnts: printing global coordinates ... aSize = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         aQuadPnts = (QuadPnts)myVector.elementAt(i);
         aPoint1 = aQuadPnts.getPoint0();
         aPoint2 = aQuadPnts.getPoint1();
         aPoint3 = aQuadPnts.getPoint2();
         aPoint4 = aQuadPnts.getPoint3();
         if(isNullVl(aPoint1,aPoint2,aPoint3,aPoint4) == false)
         {
            glblIndex += " "+aPoint1.getCounter()+" "+aPoint2.getCounter()+" "+aPoint3.getCounter()+" "+aPoint4.getCounter();
            glblCrdnt += " "+aPoint1.getX1()+" "+aPoint1.getY1()+" "+aPoint1.getZ1()+" "+aPoint2.getX1()+" "+aPoint2.getY1()+" "+aPoint2.getZ1()+" "+
                             aPoint3.getX1()+" "+aPoint3.getY1()+" "+aPoint3.getZ1()+" "+aPoint4.getX1()+" "+aPoint4.getY1()+" "+aPoint4.getZ1();
            colorVls += " "+aPoint1.getColor()+" "+aPoint2.getColor()+" "+aPoint3.getColor()+" "+aPoint4.getColor();

         }
      }

   }
   public static boolean isNullVl(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      boolean isNllVl = false;
      if(myPoint1 == null || myPoint2 == null ||
         myPoint3 == null || myPoint4 == null)
      {
         isNllVl = true;
      }
      return isNllVl;
   }
   public static String prntVRML(Vector myVector,JEditorPane myJEditorPane)
   {
      String info = "";
      glblCrdnt = "";
      glblIndex = "";

      prntKMLByQuads(myVector,myJEditorPane);
      
      String vrmlStart = "<Scene>\n"+
                  "<Viewpoint description='IndexedQuadSet example'/>\n"+
                  "<Background skyColor='1 1 1'/>\n"+
                  "<Transform>\n"+
                  "<Shape>\n";

      String indexSet = "<IndexedQuadSet index='"+glblIndex+"' solid='false' ccw='true' colorPerVertex='true' normalPerVertex='true' containerField='geometry'>\n"+
                        "<Coordinate point='"+glblCrdnt+"'/>\n"+
                        "<Color color='"+colorVls+"'/>\n"+
                        "</IndexedQuadSet>\n";
      
      String vrmlEnd = "</Shape>\n" +
                       "</Transform>\n"+
                       "</Scene>\n"+
                       "</X3D>\n";
      String output = vrmlStart + indexSet+vrmlEnd;
      System.out.println("KMlExport output = "+output);
      info = output;
      if(myJEditorPane != null)
      {
         myJEditorPane.setText(myJEditorPane.getText()+info+"\n");
      }else{
         System.out.println(info);
      }
      return output;
   }
}