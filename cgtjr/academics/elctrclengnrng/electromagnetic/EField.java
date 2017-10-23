/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.electromagnetic;

/**
 *
 * @author clayton g thomas jr
 */
public class EField {

    private float omega;
    private float time;
    private float waveNmbr;
    private float waveLength;
    private float z;
    private float mgntde;
    private float frequency;

    public EField() {
        mgntde = (float) Math.pow(10, -4);
        frequency = (float)(100*Math.pow(10,6)); 
        waveLength = 1/frequency;
        waveNmbr = (float)(2*Math.PI/waveLength);
    }
    public float cmptMgntdeByZ(float myZ) {
        z = myZ;
        float omega = (float)(frequency*2*Math.PI);
        float aMag = (float)(mgntde*Math.cos(omega*time - waveNmbr*z));
        return aMag;
    }    
    public float getMgntde() {
        return mgntde;
    }

    public float getOmega() {
        return omega;
    }

    public float getTime() {
        return time;
    }

    public float getWaveNmbr() {
        return waveNmbr;
    }

    public float getZ() {
        return z;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setMgntde(float mgntde) {
        this.mgntde = mgntde;
    }

    public void setOmega(float omega) {
        this.omega = omega;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setWaveNmbr(float waveNmbr) {
        this.waveNmbr = waveNmbr;
    }

    public void setZ(float z) {
        this.z = z;
    }
    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }
}
