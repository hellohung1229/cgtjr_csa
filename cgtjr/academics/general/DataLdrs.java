/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.chmstry.general.atoms.PDBDataLoader;
import cgtjr.academics.chmstry.general.atoms.Mol2DataLoader;
import cgtjr.academics.general.loaders.ImageDataLoader;
import com.sun.j3d.loaders.Loader;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.loaders.lw3d.Lw3dLoader;
//import com.sun.j3d.loaders.vrml97.VrmlLoader;
//import com.microcrowd.loader.java3d.max3ds.Loader3DS;           

import java.util.*;


public class DataLdrs {

    private String fileName;
    private Loader dtLoader;
    private Scene loaderScene;
    private static String pdbFlTp = "pdb";
    private static String jpgFlTp = "jpg";
    private static String pngFlTp = "png";
    private static String bmpFlTp = "bmp";
    private static String objFlTp = "obj";
    private static String lwoFlTp = "lwo";
    private static String ml2FlTp = "ml2";
    private static String wrlFlTp = "wrl";
    private static String ds3FlTp = "3ds";
    private static String rarFlTp = "rar";    
    
    private static HashMap ldrHashMap;

    static {
        ObjectFile objctFlLdr = new ObjectFile();
        Lw3dLoader lw3dLdr = new Lw3dLoader(Loader.LOAD_ALL);
        PDBDataLoader pdbLdr = new PDBDataLoader();
        Mol2DataLoader ml2Ldr = new Mol2DataLoader();
        //VrmlLoader wrlLdr = new VrmlLoader();
        //Loader3DS ds3Ldr = new Loader3DS();
        ImageDataLoader imageLdr = new ImageDataLoader();
        
        ldrHashMap = new HashMap();
        ldrHashMap.put(objFlTp, objctFlLdr);
        ldrHashMap.put(lwoFlTp, lw3dLdr);
        ldrHashMap.put(pdbFlTp, pdbLdr);
        ldrHashMap.put(ml2FlTp, ml2Ldr);
        ldrHashMap.put(jpgFlTp, imageLdr);           
        ldrHashMap.put(pngFlTp, imageLdr);   
        ldrHashMap.put(bmpFlTp, imageLdr);           
        //ldrHashMap.put(ds3FlTp, ds3Ldr);                   
        //ldrHashMap.put(rarFlTp, ds3Ldr);           
    }

    public static void insrtDtLdr(String myFileInfo, Loader myLoader) {
        ldrHashMap.put(myFileInfo, myLoader);
    }

    public static Loader rtrvDtLdr(String myFileInfo) {
        String anExtnsn = rtrvExtnsn(myFileInfo);
        System.out.println("DataLdrs: file extension = "+anExtnsn);
        Loader aLoader = (Loader) ldrHashMap.get(anExtnsn);
        return aLoader;
    }

    public static String rtrvExtnsn(String myFileName) {
        int anIndex = 1 + myFileName.lastIndexOf('.');
        String aString = myFileName.substring(anIndex);
        return aString;
    }

    public DataLdrs() {
    }
}
