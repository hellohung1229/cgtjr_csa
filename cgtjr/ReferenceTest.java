/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr;

/**
 *
 * @author cgthomasjr
 */
public class ReferenceTest {

    public static void main(String args[]) {
        Integer anInteger0 = null;

        {
            Integer anInteger1 = new Integer(100);
            Integer anInteger2 = anInteger1;
            anInteger0 = anInteger1;
        }

        System.out.println("Integer0 = " + anInteger0.intValue());
        //System.out.println("Integer1 = "+anInteger1.intValue());
        //System.out.println("Integer2 = "+anInteger2.intValue());





    }
}
