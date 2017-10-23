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
public class DaeExport {

   private String lbrryVslScnsStrtTags;
   private String lbrryVslScnsEndTags;
   private String asstTags = "";
   private String xmlStrtTags = "";
   private String xmlEndTags = "";
   private String lbrryGmtryStrtTags = "";
   private String lbrryGmtryEndTags = "";
   private String gmtryTags = "";
   private String lbrryMtrlsTags = "";
   private String scneTags = "";
   private String lbrryEffctsTags = "";
   private String instnceGmtryTags = "";
   
   private String glblIndex;
   private String glblCrdnt;
   private String colorVls;


   public static void prntKMLByQuads(Vector myVector, JEditorPane myJEditorPane) {

      String info = "";
      QuadPnts aQuadPnts = null;
      Pnt aPoint1 = null;
      Pnt aPoint2 = null;
      Pnt aPoint3 = null;
      Pnt aPoint4 = null;
      int aSize = myVector.size();
      System.out.println("QuadPnts: printing global coordinates ... aSize = " + aSize);
      for (int i = 0; i < aSize; i++) {
         aQuadPnts = (QuadPnts) myVector.elementAt(i);
         aPoint1 = aQuadPnts.getPoint0();
         aPoint2 = aQuadPnts.getPoint1();
         aPoint3 = aQuadPnts.getPoint2();
         aPoint4 = aQuadPnts.getPoint3();
         if (isNullVl(aPoint1, aPoint2, aPoint3, aPoint4) == false) {
            //glblIndex += " "+aPoint1.getCounter()+" "+aPoint2.getCounter()+" "+aPoint3.getCounter()+" "+aPoint4.getCounter();
            //glblCrdnt += " "+aPoint1.getX1()+" "+aPoint1.getY1()+" "+aPoint1.getZ1()+" "+aPoint2.getX1()+" "+aPoint2.getY1()+" "+aPoint2.getZ1()+" "+
            //                 aPoint3.getX1()+" "+aPoint3.getY1()+" "+aPoint3.getZ1()+" "+aPoint4.getX1()+" "+aPoint4.getY1()+" "+aPoint4.getZ1();
            //colorVls += " "+aPoint1.getColor()+" "+aPoint2.getColor()+" "+aPoint3.getColor()+" "+aPoint4.getColor();
         }
      }

   }

   public static boolean isNullVl(Pnt myPoint1, Pnt myPoint2, Pnt myPoint3, Pnt myPoint4) {
      boolean isNllVl = false;
      if (myPoint1 == null || myPoint2 == null ||
              myPoint3 == null || myPoint4 == null) {
         isNllVl = true;
      }
      return isNllVl;
   }

   /*
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
   }*/
   public String displayDae(Vector aVector,JEditorPane myJEditorPane)
   {
      String output = rtrveDae(aVector);
      myJEditorPane.setText(output);
      return output;
   }
   public String rtrveDae(Vector aVector) {
      gnrtModifiedXML(aVector);
      String xmlStrtTags = rtrveXMLStrtTags();
      String asstTags = rtrveAsstTags();
      String lbrryVslScnsStrtTags = rtrveLbrryVslScnsStrtTags();
      String instnceGmtry = getInstnceGmtryTags();
      String lbrryVslScnsEndTags = rtrveLbrryVslScnsEndTags();
      String lbrryGmtryStrtTags = rtrveLbrryGmtryStrtTags();
      String gmtryTags = getGmtryTags();
      String lbrryGmtryEndTags = rtrveLbrryGmtryEndTags();
      String lbrryMtrlsTags = rtrveLbrryMtrlsTags();
      String lbrryEffctsTags = rtrveLbrryEffctsTags();
      String scneTags = rtrveScneTags();
      String xmlEndTags = rtrveXMLEndTags();

      String output = xmlStrtTags+asstTags+lbrryVslScnsStrtTags+instnceGmtry+
              lbrryVslScnsEndTags+lbrryGmtryStrtTags+gmtryTags+lbrryGmtryEndTags+
              lbrryMtrlsTags+lbrryEffctsTags+scneTags+xmlEndTags;

      return output;
   }
   public void gnrtModifiedXML(Vector myVector)
   {
      LinePnts aLinePnts = null;
      int aSize = myVector.size();
      //System.out.println("LinePnts.prntGlblNmbrs(): size = "+aSize);
      for (int i = 0; i < aSize; i++) {
         aLinePnts = (LinePnts) myVector.elementAt(i);
         gnrteGmtryTags(aLinePnts);
         gnrteInstnceGmtryTags(aLinePnts);
      }
   }
   public String rtrveLbrryVslScnsStrtTags() {
      String lbrryVslScnsStrtTags =
              "<library_visual_scenes>\n" +
              "<visual_scene id=\"ID1\">\n" +
              "<node name=\"tws\">\n";
      return lbrryVslScnsStrtTags;
   }

   public String rtrveLbrryVslScnsEndTags() {
      String lbrryVslScnsEndTags =
              "</node>\n" +
              "</visual_scene>\n" +
              "</library_visual_scenes>\n";
      return lbrryVslScnsEndTags;
   }
   public String rtrveAsstTags() {
      String asstTags =
              "<asset>\n" +
              "<contributor>\n" +
              "<authoring_tool>tws</authoring_tool>\n" +
              "</contributor>\n" +
              "<created>2012-01-15T22:10:19Z</created>\n" +
              "<modified>2012-01-15T22:10:19Z</modified>\n" +
              "<unit meter=\"0.02539999969303608\" name=\"inch\" />\n" +
              "<up_axis>Z_UP</up_axis>\n" +
              "</asset>\n";
      return asstTags;
   }

   public String rtrveXMLStrtTags() {
      String xmlStrtTags = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n" +
              "<COLLADA xmlns=\"http://www.collada.org/2005/11/COLLADASchema\" version=\"1.4.1\">\n";
      return xmlStrtTags;
   }

   public String rtrveXMLEndTags() {
      String xmlEndTags = "</COLLADA>\n";
      return xmlEndTags;
   }
   public String rtrveLbrryGmtryStrtTags() {
      String lbrryGmtryStrtTags = "<library_geometries>\n";
      return lbrryGmtryStrtTags;
   }
   public String rtrveLbrryGmtryEndTags() {
      String lbrryGmtryEndTags = "</library_geometries>\n";
      return lbrryGmtryEndTags;
   }
   public String getGmtryTags()
   {
      return gmtryTags;
   }
   public String gnrteGmtryTags(LinePnts myLinePnts)
   {
      gmtryTags += rtrveGmtryTags(myLinePnts);
      return gmtryTags;
   }
   public String rtrveGmtryTags(LinePnts myLinePnts) {
      Pnt aPoint0 = myLinePnts.getPoint0();
      Pnt aPoint1 = myLinePnts.getPoint1();
      String id = aPoint0.getCounter()+""+aPoint1.getCounter();
      String coordinates = aPoint0.getX1()+" "+aPoint0.getY1()+" "+aPoint0.getZ1()+" "+
                           aPoint1.getX1()+" "+aPoint1.getY1()+" "+aPoint1.getZ1();
      String gmtryTags =
              "<geometry id=\"ID2"+id+"\">\n" +
              "<mesh>\n" +
              "<source id=\"ID5"+id+"\">\n" +
              "<float_array id=\"ID7"+id+"\" count=\"6\">"+coordinates+"</float_array>\n" +
              "<technique_common>\n" +
              "<accessor count=\"2\" source=\"#ID7"+id+"\" stride=\"3\">\n" +
              "<param name=\"X\" type=\"float\" />\n" +
              "<param name=\"Y\" type=\"float\" />\n" +
              "<param name=\"Z\" type=\"float\" />\n" +
              "</accessor>\n" +
              "</technique_common>\n" +
              "</source>\n" +
              "<vertices id=\"ID6"+id+"\">\n" +
              "<input semantic=\"POSITION\" source=\"#ID5"+id+"\" />\n" +
              "</vertices>\n" +
              "<lines count=\"1\" material=\"Material2\">\n" +
              "<input offset=\"0\" semantic=\"VERTEX\" source=\"#ID6"+id+"\" />\n" +
              "<p>1 0</p>\n" +
              "</lines>\n" +
              "</mesh>\n" +
              "</geometry>\n";
      return gmtryTags;
   }
   public String getInstnceGmtryTags()
   {
      return instnceGmtryTags;
   }
   public String gnrteInstnceGmtryTags(LinePnts myLinePnts)
   {
      instnceGmtryTags += rtrveInstnceGmtryTags(myLinePnts);
      return instnceGmtryTags;
   }
   public String rtrveInstnceGmtryTags(LinePnts myLinePnts) {
      Pnt aPoint0 = myLinePnts.getPoint0();
      Pnt aPoint1 = myLinePnts.getPoint1();
      String id = aPoint0.getCounter()+""+aPoint1.getCounter();
      String instnceGmtryTags =
              "<instance_geometry url=\"#ID2"+id+"\">\n" +
              "<bind_material>\n" +
              "<technique_common>\n" +
              "<instance_material symbol=\"Material2\" target=\"#ID3\">\n" +
              "<bind_vertex_input semantic=\"UVSET0\" input_semantic=\"TEXCOORD\" input_set=\"0\" />\n" +
              "</instance_material>\n" +
              "</technique_common>\n" +
              "</bind_material>\n" +
              "</instance_geometry>\n";
      return instnceGmtryTags;
   }
   public String getLbrryMtrlsTags() {
      return lbrryMtrlsTags;
   }
   public String rtrveLbrryMtrlsTags() {
      String lbrryMtrlsTags =
              "<library_materials>\n" +
              "<material id=\"ID3\" name=\"edge_color000255\">\n" +
              "<instance_effect url=\"#ID4\" />\n" +
              "</material>\n" +
              "</library_materials>\n";
      return lbrryMtrlsTags;
   }
   public String getLbrryEffctsTags() {
      return lbrryEffctsTags;
   }
   public String rtrveLbrryEffctsTags() {
      String lbrryEffctsTags =
              "<library_effects>\n" +
              "<effect id=\"ID4\">\n" +
              "<profile_COMMON>\n" +
              "<technique sid=\"COMMON\">\n" +
              "<constant>\n" +
              "<transparent opaque=\"A_ONE\">\n" +
              "<color>0 0 0 1</color>\n" +
              "</transparent>\n" +
              "<transparency>\n" +
              "<float>1</float>\n" +
              "</transparency>\n" +
              "</constant>\n" +
              "</technique>\n" +
              "</profile_COMMON>\n" +
              "</effect>\n" +
              "</library_effects>\n";
      return lbrryEffctsTags;
   }
   public String rtrveScneTags() {
      String scneTags =
              "<scene>\n" +
              "<instance_visual_scene url=\"#ID1\" />\n" +
              "</scene>\n";
      return scneTags;
   }
}