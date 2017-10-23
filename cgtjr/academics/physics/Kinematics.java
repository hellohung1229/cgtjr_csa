/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.physics;

import java.util.ArrayList;

/**
 *
 * @author cgthomasjr
 */
public class Kinematics {

    private double translation[];
    private double velocity[];
    private double accelleration[];
    private double postion[];
    
    private double rotation[];    
    private double rotationVlcty[];
    private double rotationAcclrtn[];
    private double rotationPstn[];
    
    private long time;
    
    private static ArrayList kinematicArrayList;
    
    public Kinematics(){
        translation = new double[3];
        velocity = new double[3];
        accelleration = new double[3];
        rotation = new double[3];
        rotationVlcty = new double[3];
        rotationAcclrtn = new double[3];
        kinematicArrayList = new ArrayList();
    }
    public double[] getTranslation() {
        return translation;
    }

    public void setTranslation(double[] myTranslation) {
        this.translation = myTranslation;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] myVelocity) {
        this.velocity = myVelocity;
    }

    public double[] getAccelleration() {
        return accelleration;
    }

    public void setAccelleration(double[] myAccelleration) {
        this.accelleration = myAccelleration;
    }

    public double[] getRotation() {
        return rotation;
    }

    public void setRotation(double[] rotation) {
        this.rotation = rotation;
    }

    public double[] getRotationVlcty() {
        return rotationVlcty;
    }

    public void setRotationVlcty(double[] myRotationVlcty) {
        this.rotationVlcty = myRotationVlcty;
    }

    public double[] getRotationAcclrtn() {
        return rotationAcclrtn;
    }

    public void setRotationAcclrtn(double[] myRotationAcclrtn) {
        this.rotationAcclrtn = myRotationAcclrtn;
    }
    public void add(Object myObject){
        
        int aSize = kinematicArrayList.size();
        if(aSize < 100){
           kinematicArrayList.add(myObject);    
        }else{
           kinematicArrayList.remove(0);
           kinematicArrayList.add(myObject);   
        }
        
    }
}
