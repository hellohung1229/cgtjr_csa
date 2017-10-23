package cgtjr.academics.elctrclengnrng.radiosity.gui.RadMode;

import cgtjr.academics.math.lnralgbra.MathVector;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class File2Rad {

    private static URL URLBase;
    private String recordSection;
    private ArrayList vertexArrayList;
    private ArrayList surfaceArrayList;
    private ArrayList patchArrayList;
    private ArrayList elementArrayList;
    private double MIN_VALUE;

    public File2Rad() {
        surfaceArrayList = new ArrayList();
        vertexArrayList = new ArrayList();
        patchArrayList = new ArrayList();
        elementArrayList = new ArrayList();
        recordSection = "";
    }

    public void readFile(String myFileName) {
        InputStream anInputStream = null;
        URL aURL = null;

        try {
            if ((myFileName != null && myFileName.toLowerCase().startsWith("http:"))
                    || (myFileName != null && myFileName.toLowerCase().startsWith("https:"))
                    || (myFileName != null && myFileName.toLowerCase().startsWith("jar:http:"))
                    || (myFileName != null && myFileName.toLowerCase().startsWith("ftp:"))
                    || (myFileName != null && myFileName.toLowerCase().startsWith("file:"))) {
                aURL = new URL(myFileName);
                anInputStream = aURL.openStream();
            } else if (URLBase != null) {
                aURL = new URL(URLBase, myFileName);
                anInputStream = aURL.openStream();
                //System.out.println("url = "+URLBase);
            } else {
                //FileInputStream aFileInputStream = new FileInputStream(myFileName);
                anInputStream = new FileInputStream(myFileName);
            }
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(anInputStream));
            String aLine = reader.readLine();
            while (aLine != null) {
                processRecord(aLine);
                aLine = reader.readLine();
            }
            anInputStream.close();
        } catch (Exception e) {
            System.out.println("Rad2File: message : " + e.getMessage());
            System.out.println("Rad2File: stacktrace : " + e.getStackTrace());
        }

    }

    public void processRecord(String myLine) {
        System.out.println("RadFile.processRecord(): " + myLine);

        if (myLine.toLowerCase().indexOf("end_entity") >= 0) {
            System.out.println("end entity section");
            recordSection = "end_entity";
        } else if (myLine.toLowerCase().indexOf("entity") >= 0) {
            System.out.println("start entity section");
            recordSection = "entity";
        } else if (myLine.toLowerCase().indexOf("end_vert") >= 0) {
            System.out.println("end vertex section");
            recordSection = "end_vertex";
        } else if (myLine.toLowerCase().indexOf("vertex") >= 0) {
            System.out.println("start vertex section");
            recordSection = "vertex";
        } else if (myLine.toLowerCase().indexOf("end_surf") >= 0) {
            System.out.println("end surface section");
            recordSection = "end_surf";
        } else if (myLine.toLowerCase().indexOf("surface") >= 0) {
            System.out.println("start surface section");
            recordSection = "surface";
        } else if (myLine.toLowerCase().indexOf("end_patch") >= 0) {
            System.out.println("end patch section");
            recordSection = "end_patch";
        } else if (myLine.toLowerCase().indexOf("patch") >= 0) {
            System.out.println("start patch section");
            recordSection = "patch";
        } else if (myLine.toLowerCase().indexOf("end_elem") >= 0) {
            System.out.println("end element section");
            recordSection = "end_patch";
        } else if (myLine.toLowerCase().indexOf("element") >= 0) {
            System.out.println("start element section");
            recordSection = "element";
        } else if (recordSection.equals("vertex")) {
            processVertex(myLine);
        } else if (recordSection.equals("surface")) {
            processSurface(myLine);
        } else if (recordSection.equals("patch")) {
            processPatch(myLine);
        } else if (recordSection.equals("element")) {
            processElement(myLine);
        }
        if (recordSection.equals("end_entity")) {
            updteSrfcePatchElmnt();
        }
    }

    public void processVertex(String myLine) {
        StringTokenizer aStringTokenizer = new StringTokenizer(myLine);
        String aLeftB = aStringTokenizer.nextToken();
        String xCoord = aStringTokenizer.nextToken();
        String yCoord = aStringTokenizer.nextToken();
        String zCoord = aStringTokenizer.nextToken();
        String aRightB = aStringTokenizer.nextToken();

        System.out.println("vertex : " + xCoord + " " + yCoord + " " + zCoord);
        float x = Float.parseFloat(xCoord);
        float y = Float.parseFloat(yCoord);
        float z = Float.parseFloat(zCoord);
        RadVertex aRadVertex = new RadVertex(x, y, z);
        vertexArrayList.add(aRadVertex);
    }

    public void processSurface(String myLine) {
        StringTokenizer aStringTokenizer = new StringTokenizer(myLine);
        String aLeftB1 = aStringTokenizer.nextToken();
        String rrS = aStringTokenizer.nextToken();
        String rgS = aStringTokenizer.nextToken();
        String rbS = aStringTokenizer.nextToken();
        String aRightB1 = aStringTokenizer.nextToken();

        String aLeftB2 = aStringTokenizer.nextToken();
        String erS = aStringTokenizer.nextToken();
        String egS = aStringTokenizer.nextToken();
        String ebS = aStringTokenizer.nextToken();
        String aRightB2 = aStringTokenizer.nextToken();

        double rr = Double.parseDouble(rrS);
        double rg = Double.parseDouble(rgS);
        double rb = Double.parseDouble(rbS);

        double er = Double.parseDouble(erS);
        double eg = Double.parseDouble(egS);
        double eb = Double.parseDouble(ebS);

        System.out.println("surface : " + rr + "," + rg + "," + rb + "," + er + "," + eg + "," + eb);
        RadSurface aRadSurface = new RadSurface();
        aRadSurface.setRflctnceRGB(rr, rg, rb);
        aRadSurface.setExtnceRGB(er, eg, eb);

        surfaceArrayList.add(aRadSurface);
    }

    public void processPatch(String myLine) {
        StringTokenizer aStringTokenizer = new StringTokenizer(myLine);
        String aSIndex = aStringTokenizer.nextToken();
        int anIndex = Integer.parseInt(aSIndex);
        String aLeftB = aStringTokenizer.nextToken();
        String vs1 = aStringTokenizer.nextToken();
        String vs2 = aStringTokenizer.nextToken();
        String vs3 = aStringTokenizer.nextToken();
        String vs4 = aStringTokenizer.nextToken();
        String aRightB = aStringTokenizer.nextToken();

        System.out.println("patch : " + vs1 + " " + vs2 + " " + vs3 + " " + vs4);
        int v1 = Integer.parseInt(vs1);
        int v2 = Integer.parseInt(vs2);
        int v3 = Integer.parseInt(vs3);
        int v4 = Integer.parseInt(vs4);

        RadVertex aRV1 = (RadVertex) vertexArrayList.get(v1);
        RadVertex aRV2 = (RadVertex) vertexArrayList.get(v2);
        RadVertex aRV3 = (RadVertex) vertexArrayList.get(v3);
        RadVertex aRV4 = (RadVertex) vertexArrayList.get(v4);

        RadPatch aRadPatch = new RadPatch(aRV1, aRV2, aRV3, aRV4);
        //patchArrayList.add(aRadPatch);
        RadSurface aRadSurface = (RadSurface) surfaceArrayList.get(anIndex);
        aRadSurface.addPatch(aRadPatch);
    }

    public void processElement(String myLine) {
        StringTokenizer aStringTokenizer = new StringTokenizer(myLine);
        String aSIndex = aStringTokenizer.nextToken();
        int anIndex = Integer.parseInt(aSIndex);
        String aLeftB = aStringTokenizer.nextToken();
        String vs1 = aStringTokenizer.nextToken();
        String vs2 = aStringTokenizer.nextToken();
        String vs3 = aStringTokenizer.nextToken();
        String vs4 = aStringTokenizer.nextToken();
        String aRightB = aStringTokenizer.nextToken();

        System.out.println("Element : " + vs1 + " " + vs2 + " " + vs3 + "," + vs4);

        System.out.println("patch : " + vs1 + " " + vs2 + " " + vs3 + " " + vs4);
        int v1 = Integer.parseInt(vs1);
        int v2 = Integer.parseInt(vs2);
        int v3 = Integer.parseInt(vs3);
        int v4 = Integer.parseInt(vs4);

        RadVertex aRV1 = (RadVertex) vertexArrayList.get(v1);
        RadVertex aRV2 = (RadVertex) vertexArrayList.get(v2);
        RadVertex aRV3 = (RadVertex) vertexArrayList.get(v3);
        RadVertex aRV4 = (RadVertex) vertexArrayList.get(v4);

        RadElement aRadElement = new RadElement(aRV1, aRV2, aRV3, aRV4);
        //patchArrayList.add(aRadPatch);    
        //elementArrayList.add(aRadElement);
        RadSurface aRadSurface = (RadSurface) surfaceArrayList.get(anIndex);
        ArrayList ptchArayList = aRadSurface.getPatchArrayList();
        RadPatch aRadPatch = (RadPatch) ptchArayList.get(anIndex);
        aRadPatch.addElement(aRadElement);
    }

    public void updteSrfcePatchElmnt() {
        int elmntSize = elementArrayList.size();
        for (int i = 0; i < elmntSize; i++) {
            System.out.println("Adding element " + i + " to patch " + i);
            RadPatch aRadPatch = (RadPatch) patchArrayList.get(i);
            aRadPatch.addElement((RadElement) elementArrayList.get(i));
        }
        int ptchSize = patchArrayList.size();
        for (int i = 0; i < ptchSize; i++) {
            System.out.println("Adding patch " + i + " to surface " + i);
            RadSurface aRadSurface = (RadSurface) surfaceArrayList.get(i);
            aRadSurface.addPatch((RadPatch) patchArrayList.get(i));
        }
    }

    public MathVector crssPrdct(MathVector myMV1, MathVector myMV2) {
        MathVector myMV3 = MathVector.crssPrdct(myMV1, myMV2);
        return myMV3;
    }

    public void cmpteFF(RadElement myRadElement, RadVertex myRV, MathVector myNormal2) {
        int nmbrSrfce = surfaceArrayList.size();
        double ff = 0;
        for (int i = 0; i < nmbrSrfce; i++) {
            RadSurface aRadSurface = (RadSurface) surfaceArrayList.get(i);
            ArrayList aPatchArrayList = aRadSurface.getPatchArrayList();
            int nmbrPatches = aPatchArrayList.size();
            for (int j = 0; j < nmbrPatches; j++) {
                RadPatch aRadPatch = (RadPatch) aPatchArrayList.get(j);
                ArrayList aElementArrayList = aRadPatch.rtrveArryLst();
                int nmbrElements = aElementArrayList.size();
                for (int k = 0; k < nmbrElements; k++) {
                    RadElement aRadElement = (RadElement) aElementArrayList.get(j);
                    /*
                    RadVertex aRV1 = aRadElement.getV1();
                    RadVertex aRV2 = aRadElement.getV2();
                    RadVertex aRV4 = aRadElement.getV4();
                    MathVector aMV1 = new MathVector(aRV2.getX() - aRV1.getX(), aRV2.getY() - aRV1.getY(), aRV2.getZ() - aRV1.getZ());
                    MathVector aMV2 = new MathVector(aRV4.getX() - aRV1.getX(), aRV4.getY() - aRV1.getY(), aRV4.getZ() - aRV1.getZ());
                    MathVector aNormal1 = MathVector.crssPrdct(aMV1, aMV2);
                    */
                    MathVector aNormal1 = aRadElement.rtrveNormal();
                    //MathVector aMV = new MathVector(myRV.getX()-aRV1.getX(),myRV.getY()-aRV1.getY(),myRV.getZ()-aRV1.getZ());

                    MathVector aMV = new MathVector(myNormal2);
                    aMV.scale(-1);
                    //Determining culling requires additional information!!
                    //Consider vertex on same polygon
                    //Consider vertex on facing wrong direction
                    //Consider passing in the normal of the vertex, 
                    //Consider then taking dot product of both normals
                    double aDotValue = MathVector.dotPrdct(aMV, aNormal1);
                    if (aDotValue < 0) {
                        RadVertex aCV2 = myRadElement.rtrveCntrd();
                        RadVertex aCV1 = aRadElement.rtrveCntrd();
                        MathVector cntrdMV1 = new MathVector(aCV2.getX() - aCV1.getX(), aCV2.getY() - aCV1.getY(), aCV2.getZ() - aCV1.getZ());
                        MathVector cntrdMV2 = new MathVector(aCV1.getX() - aCV2.getX(), aCV1.getY() - aCV2.getY(), aCV1.getZ() - aCV2.getZ());                        
                        
                        double cos1 = cntrdMV1.dotPrdct(aNormal1)/cntrdMV1.getMagnitude();
                        double cos2 = cntrdMV2.dotPrdct(myNormal2)/cntrdMV2.getMagnitude();
                        ff = cos1 * cos2 * aNormal1.getMagnitude() / (Math.PI ); 
                                
                        //ff += view.Dot(n_ray, nv) * view.Dot(r_ray, src_norm) / ((Math.PI
                        //* ray_len * ray_len) + ray_area);
                    }
                }

            }
        }

    }
short TestPatch(RadElement myRadElement, MathVector myStart, MathVector myRadDir)
{
        short i_flag;          // Intersection flag
        int i;                // Loop index
        int i0, i1, i2;       // Projection plane axis indices
        double alpha;         // Scaling parameter
        double beta;          // Scaling parameter
        double dist;          // Patch plane distance
        double d, t;          // Temporary variables
        double isect[] = new double[3];      // Ray-patch intersection [3]
        double n_mag[] = new double[3];      // Patch normal axis magnitudes[3]
        double vert[][] = new double[4][3];    // Patch vertices [4][3]
        double s0, s1, s2;    // Projected vector co-ordinates
        double t0, t1, t2;    // Projected vector co-ordinates
        RadVertex pvp;          // Vertex position pointer
        MathVector normal;       // Patch normal
        MathVector temp;         // Temporary 3-D vector
        MathVector aRadDir = new MathVector(myRadDir);
        // Check for valid patch
        if (myRadElement == null) {
            return 0;
        }
        // Get patch normal
        normal = myRadElement.rtrveNormal();
        // Calculate divisor
        d = normal.dotPrdct(myRadDir);
        // Determine whether ray is parallel to patch
        if (Math.abs(d) < MIN_VALUE) {
            return 0;
        }        
        temp = new MathVector(myRadElement.getV1().getX(),myRadElement.getV1().getY(),myRadElement.getV1().getZ());        
        // Calculate patch plane distance
        dist = MathVector.dotPrdct(normal, temp);
        // Calculate ray hit time parameter
        
        t = (dist - MathVector.dotPrdct(normal, myStart)) / d;

        // Check whether patch plane is behind receiver vertex or
        // source patch point
        //
        // NOTE: MIN_VALUE offsets are required to prevent
        //       interpretation of adjoining surface vertices as
        //       occlusions
        if (t < MIN_VALUE || t > (1.0 - MIN_VALUE)) {
            return 0;
        }
        // Calculate ray-patch plane intersection
        aRadDir.scale((float)t);
        myStart.add(aRadDir);
        temp = myStart;

        // Get intersection axes
        isect[0] = temp.getX2();
        isect[1] = temp.getY2();
        isect[2] = temp.getZ2();        
        // Get patch normal axis magnitudes
        n_mag[0] = Math.abs(normal.getX2());
        n_mag[1] = Math.abs(normal.getY2());
        n_mag[2] = Math.abs(normal.getZ2());

        // Get patch vertex axes
        for (i = 0; i < myRadElement.rtrveArryLst().size(); i++) {
            pvp = (RadVertex)myRadElement.rtrveArryLst().get(i);
            vert[i][0] = pvp.getX();
            vert[i][1] = pvp.getY();
            vert[i][2] = pvp.getZ();
        }

        // Find patch normal dominant axis
        if ((n_mag[0] >= n_mag[1]) && (n_mag[0] >= n_mag[2])) {
            i0 = 0;
            i1 = 1;
            i2 = 2;     // X-axis dominant
        } else if ((n_mag[1] >= n_mag[0]) && (n_mag[1] >= n_mag[2])) {
            i0 = 1;
            i1 = 0;
            i2 = 2;     // Y-axis dominant
        } else {
            i0 = 2;
            i1 = 0;
            i2 = 1;     // Z-axis dominant
        }

        // Calculate projected vertex #0 co-ordinates
        s0 = isect[i1] - vert[0][i1];
        t0 = isect[i2] - vert[0][i2];

        // Check for intersection (consider quadrilateral as two
        // adjacent triangles
        i = 2;
        i_flag = 0;
        do {
            // Calculate projected vertex co-ordinates
            s1 = vert[i - 1][i1] - vert[0][i1];
            t1 = vert[i - 1][i2] - vert[0][i2];
            s2 = vert[i][i1] - vert[0][i1];
            t2 = vert[i][i2] - vert[0][i2];

            // Determine vector scaling parameters
            if (Math.abs(s1) < MIN_VALUE) // Is s1 == 0 ?
            {
                beta = s0 / s2;
                if ((beta >= 0.0) && (beta <= 1.0)) {
                    alpha = (t0 - beta * t2) / t1;
                    i_flag = ((alpha >= 0.0) && ((alpha + beta)
                            <= 1.0))?(short)1:(short)0;
                }
            } else {
                beta = (s1 * t0 - s0 * t1) / (s1 * t2 - s2 * t1);
                if ((beta >= 0.0) && (beta <= 1.0)) {
                    alpha = (s0 - beta * s2) / s1;

                    // Test for intersection
                    i_flag = ((alpha >= 0.0) && ((alpha + beta)
                            <= 1.0))?(short)1:(short)0;
                }
            }
            i++;    // Advance to next triangle (if any)
        } while (i_flag == 0 && i < myRadElement.rtrveArryLst().size());        
        /*
         * 


        return i_flag;
        */
                return 0;
    }
    public static void main(String args[]) {
        String fileName = "C:\\...\\...\\...\\COL_CUBE.ENT";
        File2Rad aRad2File = new File2Rad();
        aRad2File.readFile(fileName);
    }
}