package cgtjr.academics.chmstry.physical;


import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.chmstry.general.atoms.PDBMoleculeFile;
import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
import java.awt.Color;
import java.util.*;

import cgtjr.academics.math.geometry.*;
import cgtjr.academics.general.*;

//TODO code clean up
public class ElctrcChrgUptr// implements BndryNodeAction_I
{
   private PotentialDiff vltgPotentialDiff;
   private MoleculeModel vltgPDBMolecule;
   private float minValue;
   private float maxValue;
   private float mean;
   private int count;
   private float sum;
   private Vector aVector;
   private float average;

   public ElctrcChrgUptr()
   {
      vltgPotentialDiff = new PotentialDiff();
      vltgPDBMolecule = PDBMoleculeFile.getPDBMolecule();
      minValue = Float.MAX_VALUE;
      maxValue = -1*Float.MAX_VALUE;
   }
   public void setMinValue(float myMinValue)
   {
      minValue = myMinValue;
   }
   public void setMaxValue(float myMaxValue)
   {
      maxValue = myMaxValue;
   }
   public double getMinValue()
   {
      return minValue;
   }
   public double getMaxValue()
   {
      return maxValue;
   }
   public void updtMinMaxVal(float myValue)
   {
       sum += myValue;
       if(myValue <= minValue)
       {
           minValue = myValue;
       }
       if(myValue >= maxValue)
       {
           maxValue = myValue;
       }
   }
   public float cmptStdrdDvtn()
   {
      Pnt aPoint = null;
      int aSize = aVector.size();
      average = sum/aSize;
      float aValue = 0;
      float variance = 0;
      float stndrdDvtn = 0;
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)aVector.elementAt(i);
         aValue = (float)aPoint.getValue();
         variance += (aValue - average)*(aValue - average)/aSize;
      }
      stndrdDvtn = (float)Math.sqrt(variance);
      //System.out.println("VltgDffrncUpdtr: sum = "+sum+",stndrdDvtn="+stndrdDvtn+",average="+average+", max="+maxValue+",min="+minValue);
      return stndrdDvtn;
   }
   public float getAverage()
   {
      return average;
   }
   public void update(Vector myVector)
   {
      Pnt aPoint = null;
      int aSize = myVector.size();
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)myVector.elementAt(i);
         update(aPoint);
      }
   }
   public void update(ShapePnt myShape)
   {
      Pnt aPoint = null;
      vltgPotentialDiff.setCrdntType(myShape.getCrdntTp());
      aVector = myShape.getNodes();
      int aSize = aVector.size();
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)aVector.elementAt(i);
         update(aPoint);
      }       
   }
   private void update(Pnt myPoint7)
   {
      float aValue = vltgPotentialDiff.potentialDiffValue(vltgPDBMolecule,myPoint7);
      myPoint7.setValue(aValue);
      updtMinMaxVal(aValue);
      //System.out.println("VltgDffrncSmplr: value="+aValue+", min value ="+minValue+",max value="+maxValue);
      /*
            System.out.println("VltgDffrncSmplr: value="+aColorValue1+", min value ="+minValue+",max value="+maxValue);
      float aColorValue2 = (float)(Math.abs((aColorValue1-minValue)/(maxValue-minValue)));
      System.out.println("VltgDffrncSmplr: aColorValue2="+aColorValue2);
      float aColorValue3[] = ColorSpectra.cnvrtHSIToRGB(aColorValue2);
      Color aColor = new Color(aColorValue3[0],aColorValue3[1],aColorValue3[2]);
      myPoint7.setColor(aColor.getRGB());
      */
   }
}