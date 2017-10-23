package cgtjr.academics.math.geometry.linepnts;

import cgtjr.academics.math.geometry.linepnts.AngleLine;

public class Line extends AngleLine
{
   public Line()
   {
      super();
   }
   public Line(float xPos, float yPos,float zPos)
   {      
      super(xPos,yPos,zPos);
   }
   public Line(float xStartPos, float yStartPos,float zStartPos,float xEndPos , float yEndPos,float zEndPos)
   {      
      super(xStartPos,yStartPos,zStartPos,xEndPos,yEndPos,zEndPos);
   }
}