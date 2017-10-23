/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.radiosity.gui.RadMode;

import java.util.ArrayList;

/**
 *
 * @author cgthomasjr
 */
public class RadSurface {
   private double rr;
   private double rg;
   private double rb;
   private double er;
   private double eg;
   private double eb;
   
   ArrayList patchArrayList;
   
   public RadSurface(){
      patchArrayList = new ArrayList();    
   }
   public void setRflctnceRGB(double r,double g,double b){
       rr = r;
       rg = g;
       rb = b;
   }
   public void setExtnceRGB(double r,double g,double b){
       er = r;
       eg = g;
       eb = b;
   }   

    public double getRr() {
        return rr;
    }

    public void setRr(double rr) {
        this.rr = rr;
    }

    public double getRg() {
        return rg;
    }

    public void setRg(double rg) {
        this.rg = rg;
    }

    public double getRb() {
        return rb;
    }

    public void setRb(double rb) {
        this.rb = rb;
    }

    public double getEr() {
        return er;
    }

    public void setEr(double er) {
        this.er = er;
    }

    public double getEg() {
        return eg;
    }

    public void setEg(double eg) {
        this.eg = eg;
    }

    public double getEb() {
        return eb;
    }

    public void setEb(double eb) {
        this.eb = eb;
    }   
    public void addPatch(RadPatch myRadPatch){
        patchArrayList.add(myRadPatch);
    }    
    public ArrayList getPatchArrayList(){
        return patchArrayList;
    }    
}
