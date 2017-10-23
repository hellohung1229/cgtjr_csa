/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.general.atoms;

/**
 *
 * @author clayton g thomas jr
 */
public class MoleculeFile {
    static MoleculeModel  thePDBMolecule;
    
    public static MoleculeModel getPDBMolecule() {
        return thePDBMolecule;
    }  
    public void setMoleculeModel(MoleculeModel myMoleculeModel)
    {
        thePDBMolecule = myMoleculeModel;
    }
}
