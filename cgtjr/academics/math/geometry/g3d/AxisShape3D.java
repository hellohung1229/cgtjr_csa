/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.g3d;


import javax.media.j3d.*;

/**
 *
 * @author clayton g thomas jr
 */

public class AxisShape3D extends Shape3D
{ 
   private LineArray xAxisLine;
   private LineArray yAxisLine;
   private LineArray zAxisLine;
   
   public AxisShape3D()
   {
     super();
     xAxisLine = new LineArray(2,LineArray.COORDINATES|LineArray.COLOR_3);
     yAxisLine = new LineArray(2,LineArray.COORDINATES|LineArray.COLOR_3); 
     zAxisLine = new LineArray(2,LineArray.COORDINATES|LineArray.COLOR_3); 
     
     float x0[] = {10.0f,0.0f,0.0f};
     float x1[] = {-10.0f,0.0f,0.0f};     
     xAxisLine.setCoordinates(0, x0);
     xAxisLine.setCoordinate(1, x1);
     float cX0[] = {1.0f,0.0f,0.0f};
     float cX1[] = {1.0f,0.0f,0.0f};     
     xAxisLine.setColor(0,cX0);     
     xAxisLine.setColor(1,cX1);     
     
     float y0[] = {0.0f,10.0f,0.0f};
     float y1[] = {0.0f,-10.0f,0.0f};     
     yAxisLine.setCoordinates(0, y0);
     yAxisLine.setCoordinate(1, y1);     
     float cY0[] = {0.0f,1.0f,0.0f};
     float cY1[] = {0.0f,1.0f,0.0f};     
     yAxisLine.setColor(0,cY0);     
     yAxisLine.setColor(1,cY1);  
          
     float z0[] = {0.0f,0.0f,10.0f};
     float z1[] = {0.0f,0.0f,-10.0f};     
     zAxisLine.setCoordinates(0, z0);
     zAxisLine.setCoordinate(1, z1);     
     float cZ0[] = {0.0f,0.0f,1.0f};
     float cZ1[] = {0.0f,0.0f,1.0f};     
     zAxisLine.setColor(0,cZ0);     
     zAxisLine.setColor(1,cZ1); 
     
     addGeometry(xAxisLine);     
     addGeometry(yAxisLine);
     addGeometry(zAxisLine);     
   }
}