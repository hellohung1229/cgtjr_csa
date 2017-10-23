/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.graph;

/**
 *
 * @author clayton g thomas jr
 */
public class Edge extends Vertex {

    private short direction;

    public Edge() {
        //setVisibility(false);
    }

    public short getDirection() {
        return direction;
    }

    public void setDirection(short myDirection) {
        this.direction = myDirection;
    }
}