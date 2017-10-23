/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.radiosity.gui.RadMode;

import cgtjr.academics.math.lnralgbra.MathVector;
import java.util.ArrayList;

/**
 *
 * @author cgthomasjr
 */
public class RadElement {

    private RadVertex radVertex1;
    private RadVertex radVertex2;
    private RadVertex radVertex3;
    private RadVertex radVertex4;
    private ArrayList arryLst;
    
    int nmbrVertices;

    public RadElement(RadVertex myV1, RadVertex myV2, RadVertex myV3, RadVertex myV4) {
        arryLst = new ArrayList();
        
        radVertex1 = myV1;
        arryLst.add(myV1);
        radVertex2 = myV2;
        arryLst.add(myV2);
        radVertex3 = myV3;
        arryLst.add(myV3);
        radVertex4 = myV4;
        arryLst.add(myV4);
    }

    public RadVertex getV1() {
        return radVertex1;
    }

    public void setV1(RadVertex v1) {
        this.radVertex1 = v1;
    }

    public RadVertex getV2() {
        return radVertex2;
    }

    public void setV2(RadVertex v2) {
        this.radVertex2 = v2;
    }

    public RadVertex getV3() {
        return radVertex3;
    }

    public void setV3(RadVertex v3) {
        this.radVertex3 = v3;
    }

    public RadVertex getV4() {
        return radVertex4;
    }

    public void setV4(RadVertex v4) {
        this.radVertex4 = v4;
    }

    public RadVertex rtrveCntrd(){
        float x = (radVertex1.getX()+radVertex2.getX()+radVertex3.getX()+radVertex4.getX())/4;
        float y = (radVertex1.getY()+radVertex2.getY()+radVertex3.getY()+radVertex4.getY())/4;
        float z = (radVertex1.getZ()+radVertex2.getZ()+radVertex3.getZ()+radVertex4.getZ())/4;        
        RadVertex aRadVertex = new RadVertex(x,y,z);
        return aRadVertex;        
    }
    public MathVector rtrveNormal(){
          RadVertex aRV1 = getV1();
          RadVertex aRV2 = getV2();
          RadVertex aRV4 = getV4();        
          
          MathVector aMV1 = new MathVector(aRV2.getX() - aRV1.getX(), aRV2.getY() - aRV1.getY(), aRV2.getZ() - aRV1.getZ());
          MathVector aMV2 = new MathVector(aRV4.getX() - aRV1.getX(), aRV4.getY() - aRV1.getY(), aRV4.getZ() - aRV1.getZ());

          MathVector aNormal1 = MathVector.crssPrdct(aMV1, aMV2);
       return aNormal1;
    }
    public ArrayList rtrveArryLst() {
        return arryLst;
    }    
}
