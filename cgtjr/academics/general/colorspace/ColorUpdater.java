package cgtjr.academics.general.colorspace;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.*;
import java.awt.Color;

import cgtjr.academics.math.geometry.*;

public class ColorUpdater {

    private double values[];
    private double maxValue;
    private double minValue;
    private double clrValue;
    private ShapePnt shp;
    private double offSet;

    public ColorUpdater(ShapePnt myShape, double myValues[], double myMinValue, double myMaxValue) {
        shp = myShape;
        minValue = myMinValue;
        maxValue = myMaxValue;
    }

    public ColorUpdater(double myValues[], double myMinValue, double myMaxValue) {
        values = myValues;
        minValue = myMinValue;
        maxValue = myMaxValue;
    }

    public ColorUpdater(double myMinValue, double myMaxValue) {
        minValue = myMinValue;
        maxValue = myMaxValue;
    }    
    public void setOffSet(double myOffSet) {
        offSet = myOffSet;
    }

    public double getOffSet() {
        return offSet;
    }

    public void update(Pnt myPoint) {
        int aColor = 0;
        int anIndex = myPoint.getCounter();
        double aValue = values[anIndex];
        //myPoint.setValue(aValue);
        clrValue = (Math.abs(minValue) + aValue) / (maxValue - minValue);

        if (clrValue < 0) {
            clrValue = 0;
        }

        if (clrValue > maxValue) {
            clrValue = maxValue;
        }

        if (clrValue >= -0.00001 && clrValue <= -0.00001) {
            aColor = Color.HSBtoRGB((float) .1, .00f, .00f);
        } else {
            aColor = Color.HSBtoRGB((float) clrValue, .500f, .500f);
        }
        myPoint.setColor(aColor);
        //myPoint.setValue(values[anIndex]);
        //System.out.println("ColorUpdtr: color = "+clrValue+",value="+aValue+",minValue="+minValue+",maxValue="+maxValue);
    }

    public void update(Pnt myPoint, double myValue) {
        int aColor = 0;

        clrValue = (Math.abs(minValue) + myValue) / (maxValue - minValue);

        if (clrValue < 0) {
            clrValue = 0;
        }

        if (clrValue > maxValue) {
            clrValue = maxValue;
        }

        if (clrValue >= -0.00001 && clrValue <= -0.00001) {
            aColor = Color.HSBtoRGB((float) .1, .00f, .00f);
        } else {
            aColor = Color.HSBtoRGB((float) clrValue, .500f, .500f);
        }
        myPoint.setColor(aColor);
    }

    public void update(ShapePnt myShape) {
        Vector aVector = (Vector) myShape.getNodes();
        update(aVector);
    }

    public void update(Vector myVector) {
        Pnt aPoint = null;
        int aSize = myVector.size();

        for (int i = 0; i < aSize; i++) {
            aPoint = (Pnt) myVector.elementAt(i);
            update(aPoint);
        }
    }

    public void update() {
        if (shp != null) {
            Vector aVector = (Vector) shp.getNodes();
            update(aVector);
        }
    }
}