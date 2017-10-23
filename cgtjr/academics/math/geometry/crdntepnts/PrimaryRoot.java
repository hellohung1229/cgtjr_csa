/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.crdntepnts;

/**
 *
 * @author cgthomasjr
 */
public class PrimaryRoot extends Pnt{
    
    private static PrimaryRoot primaryRoot;

    public static PrimaryRoot getPrimaryRoot() {
        return primaryRoot;
    }

    public static void setPrimaryRoot(PrimaryRoot myPrimaryRoot) {
        primaryRoot = myPrimaryRoot;
    }
    
    public static PrimaryRoot rtrvePrimaryRoot() {
        if(primaryRoot == null){
            primaryRoot = new PrimaryRoot();
        }
        return primaryRoot;
    }    
}