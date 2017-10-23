/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.general.atoms;

import com.sun.j3d.loaders.Scene;
import java.net.URL;

/**
 *
 * @author clayton g thomas jr
 */
public class Mol2DataLoader extends MlclDataLoader {

    public Scene load(String fileName, float mySphereRadius, float myCylinderRadius) {
        Mol2File theMol2File = new Mol2File();
        MoleculeModel thePDBMolecule = theMol2File.readFile(fileName);
        return load(thePDBMolecule, mySphereRadius, myCylinderRadius);
    }
    public Scene load(URL fileNameURL) {
        String fileName = fileNameURL.toString();
        Mol2File theMol2File = new Mol2File();
        MoleculeModel thePDBMolecule = theMol2File.readFile(fileName);
        return load(thePDBMolecule, getLargeRadius(), getSmallRadius());
    }      
}
