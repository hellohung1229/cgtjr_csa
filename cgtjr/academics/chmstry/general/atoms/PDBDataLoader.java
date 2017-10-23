/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
//import cgtjr.academics.chmstry.general.atoms.MlclDataLoader;
import com.sun.j3d.loaders.Scene;

/**
 *
 * @author clayton g thomas jr
 */
public class PDBDataLoader extends MlclDataLoader {

    public Scene load(String fileName, float mySphereRadius, float myCylinderRadius) {
        PDBMoleculeFile thePDBMoleculeFile = new PDBMoleculeFile();
        MoleculeModel thePDBMolecule = thePDBMoleculeFile.readFile(fileName);
        return load(thePDBMolecule, mySphereRadius, myCylinderRadius);
    }
}
