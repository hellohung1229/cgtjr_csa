/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.radiosity;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.elmnt.Nodes;
import cgtjr.academics.math.geometry.elmnt.QuadPnts;
import java.util.Iterator;
import java.util.Vector;
import javax.vecmath.Vector3d;
import sun.security.jca.GetInstance.Instance;

/**
 *
 * @author cgthomasjr
 */
public class RayCast {

// Maximum number of rays to be cast
    static int RC_NumRays = 4;
    double ray_area;            // Intersection area
    double src_area;            // Source patch area
    double selector;            // Triangle selector
    Vector psrc;               // Source patch pointer
    Vector pcache;             // Last occluding patch
    Vector3d end;                // Intersection vector
    Vector3d ray_dir;            // Ray direction
    Vector3d src_center;         // Source patch center
    Vector3d src_norm;           // Source patch normal
    Vector3d start;              // Receiver Pnt vector
    Vector3d v0, v1, v2, v3;     // Pnt vectors

    //void Select( Vector3d * );
    //short CheckOcclusion( Instance * );
    //short TestPatch( Patch3 * );
    //public:
    //void Init( Patch3 * );
    //double CalcFormFactor( Vertex3 *, Instance * );
    double CalcFormFactor(Pnt pPnt, Instance[] penv) {
        /*
         int i;            // Loop index
         double ff;        // Pnt-source form factor
         double ray_len;   // Ray length
         Vector3d nv;       // Pnt normal
         Vector3d n_ray;    // Normalized ray direction
         Vector3d r_ray;    // Reverse normalized ray direction
         Vector3d view;     // Source patch view vector

         start = Vector3d(pPnt.GetPosn());
         nv = pPnt.GetNormal();
         view = start - src_center;

         // Determine whether source patch is backface
         if (view.Dot(src_norm, view) < MIN_VALUE) {
         return 0.0;
         }

         ff = 0.0;
         for (i = 0; i < RC_NumRays; i++) {
         // Select random point on source patch
         Select(end);

         // Generate ray to shoot from Pnt to source
         ray_dir = end - start;

         // Check for source point behind Pnt
         if (view.Dot(nv, ray_dir) < MIN_VALUE) {
         continue;
         }

         // Test for ray-element intersection
         if (CheckOcclusion(penv) == FALSE) {
         // Calculate ray length
         ray_len = ray_dir.Length();

         // Calculate normalized ray direction
         n_ray = ray_dir;
         n_ray.Norm();

         // Determine reverse normalized ray direction
         r_ray = -n_ray;

         // Update form factor estimation
         ff += view.Dot(n_ray, nv) * view.Dot(r_ray, src_norm) / ((Math.PI
         * ray_len * ray_len) + ray_area);
         }
         }

         // Multiply by ray-source patch intersection area
         ff *= ray_area;

         return ff;
         */
        return 0;
    }

// Initialize parameters for source patch
    void Init(Vector ppatch) {
        /*
         double a1, a2;        // Triangle areas
         Vector3d temp;         // Temporary 3-D vector
         Vector3d e0, e1, e2;   // Edge vectors

         psrc = ppatch;
         pcache = null;
         src_area = psrc.GetArea();
         src_norm = psrc.GetNormal();
         src_center = Vector3d(psrc.GetCenter());
         ray_area = src_area / RC_NumRays;

         // Get patch Pnt vectors
         v0 = Vector3d(ppatch.GetPntPtr(0).GetPosn());
         v1 = Vector3d(ppatch.GetPntPtr(1).GetPosn());
         v2 = Vector3d(ppatch.GetPntPtr(2).GetPosn());
         v3 = Vector3d(ppatch.GetPntPtr(3).GetPosn());

         // Calculate patch edge vectors
         e0 = new Vector3d().operatorSUBSTRACT(v1, v0);
         e1 = new Vector3d().operatorSUBSTRACT(v2, v0);

         // Calculate first triangle area
         temp = temp.Cross(e0, e1);
         a1 = temp.Length() / 2.0;

         if (ppatch.IsQuad() == TRUE) {
         // Calculate patch edge vector
         e2 = new Vector3d().operatorSUBSTRACT(v3, v0);

         // Calculate second triangle area
         temp = temp.Cross(e1, e2);
         a2 = temp.Length() / 2.0;
         } else {
         a2 = 0.0;
         }

         // Calculate fractional area of first triangle
         selector = a1 / (a1 + a2);
         */
    }

// Select random point within source patch area
    void Select(Vector3d[] ppoint) {
        /*
         double s, t;      // Random point parameters

         // Get random point parameters
         s = GetNormRand();
         t = GetNormRand();

         // Ensure random point is inside triangle
         if (s + t > 1.0) {
         s = 1.0 - s;
         t = 1.0 - t;
         }

         // Calculate random point co-ordinates
         if (GetNormRand() <= selector) {
         // Locate point in first triangle
         * ppoint = (1.0 - s - t) * v0 + s * v1 + t * v2;
         } else {
         // Locate point in second triangle
         * ppoint = (1.0 - s - t) * v0 + s * v2 + t * v3;
         }
         */
    }

// Check for ray occlusion
    short CheckOcclusion(Pnt myPnt) {
        

        
        Vector allOrigins1 = myPnt.getAdjcntVertices();
        Iterator originsIterator1 = allOrigins1.iterator();

        while (originsIterator1.hasNext()) {
            ShapePnt aShapePnt1 = (ShapePnt) originsIterator1.next();
            Vector anElmntVctr1 = aShapePnt1.rtrvElmnts();              
            Iterator anElmntItrtr1 = anElmntVctr1.iterator();
            
            while (anElmntItrtr1.hasNext()) {
                Nodes elmntVctr1 = (Nodes) anElmntItrtr1.next();
            
                Vector allOrigins2 = myPnt.getAdjcntVertices();
                Iterator originsIterator2 = allOrigins2.iterator();           
                while (originsIterator2.hasNext()) {
                    ShapePnt aShapePnt2 = (ShapePnt) originsIterator2.next();
                    Vector anElmntVctr2 = aShapePnt2.rtrvElmnts();              
                    Iterator anElmntItrtr2 = anElmntVctr2.iterator();
                
                    while (anElmntItrtr2.hasNext()) {
                        Nodes elmntVctr2 = (Nodes) anElmntItrtr2.next();
                           if (elmntVctr1 != elmntVctr2){
                              TestPatch(elmntVctr1,elmntVctr2,myPnt);
                           }
                    }                    
                }
            }
        }
        /*
         Vector ppatch;       // Patch pointer
         Surface3[] psurf;      // Surface pointer

         // Test cached patch for ray-patch intersection
         if (TestPatch(pcache) == TRUE) {
         return TRUE;
         }

         // Walk the instance list
         while (pinst != null) {
         // Walk the surface list
         psurf = pinst.GetSurfPtr();
         while (psurf != null) {
         // Walk the patch list
         ppatch = psurf.GetPatchPtr();
         while (ppatch != null) {
         if (ppatch != psrc) // Ignore source patch
         {
         // Test for ray-patch intersection
         if (TestPatch(ppatch) == TRUE) {
         // Cache occluding patch
         pcache = ppatch;

         return TRUE;
         }
         }
         ppatch = ppatch.GetNext();
         }
         psurf = psurf.GetNext();
         }
         pinst = pinst.GetNext();
         }

         return FALSE;
         */
        return 0;
    }
// Check for ray-patch intersection (Badouel's Algorithm)

    public void testQuadPatch(Nodes myNodes1,Nodes myNodes2,Pnt myPnt){
        
        float normal1[] = myNodes1.getNormal();
        float normal2[] = myNodes2.getNormal();    
        float center1[] = myNodes1.getCenter();
        float center2[] = myNodes2.getCenter();   
        start = new Vector3d(center1[0],center1[1],center1[2]);
        end = new Vector3d(center2[0],center2[1],center2[2]);
        
        ray_dir.sub(end,start);
        Vector3d n1 = new Vector3d(normal1[0],normal1[1],normal1[2]);        
        double d = n1.dot(ray_dir);
        
         // Determine whether ray is parallel to patch
         //if (fabs(d) < MIN_VALUE) {
         //return FALSE;
         //}        
            
         Vector3d temp1 = new Vector3d(center1[0],center1[1],center1[2]);
         double dist = n1.dot(temp1);

         // Calculate ray hit time parameter
         double t = (dist - n1.dot(start)) / d;

         // Check whether patch plane is behind receiver Pnt or
         // source patch point
         //
         // NOTE: MIN_VALUE offsets are required to prevent
         //       interpretation of adjoining surface vertices as
         //       occlusions
         //if (t < MIN_VALUE || t > (1.0 - MIN_VALUE)) {
         //return FALSE;
         //}

         // Calculate ray-patch plane intersection
         Vector3d temp2 = new Vector3d();
         ray_dir.scale(t);
         temp2.add(start,ray_dir);
    
         // Get intersection axes
         double isect[] = new double[3];
         isect[0] = temp2.getX();
         isect[1] = temp2.getY();
         isect[2] = temp2.getZ();
         
         // Get patch normal axis magnitudes
         double n_mag[] = new double[3];
         n_mag[0] = Math.abs(n1.getX());
         n_mag[1] = Math.abs(n1.getY());
         n_mag[2] = Math.abs(n1.getZ());    
         
         // Get patch Pnt axes
         float vert[][] = new float[4][3];
         QuadPnts qudPnts = (QuadPnts)myNodes2;
         for (int i = 0; i < 4; i++) {
         
         vert[i][0] = qudPnts.getPoints()[i].getX1();
         vert[i][1] = qudPnts.getPoints()[i].getY1();
         vert[i][2] = qudPnts.getPoints()[i].getZ1();
         }         
         
    }
    boolean TestPatch(Nodes myNodes1,Nodes myNodes2,Pnt myPnt) {
        if(myNodes1 == myNodes2){
            return false;
        }
        if(myNodes1 instanceof QuadPnts && myNodes2 instanceof QuadPnts){
            testQuadPatch(myNodes1,myNodes2,myPnt);               
        }
        Pnt somePnts[] = myNodes1.getPoints();
        
        Pnt p0 = somePnts[0];
        Pnt p1 = somePnts[1];        
        Pnt p2 = somePnts[2];
        Pnt p3 = somePnts[3];

        
        /*
         short i_flag;          // Intersection flag
         int i;                // Loop index
         int i0, i1, i2;       // Projection plane axis indices
         double alpha;         // Scaling parameter
         double beta;          // Scaling parameter
         double dist;          // Patch plane distance
         double d, t;          // Temporary variables
         double isect[];      // Ray-patch intersection [3]
         double n_mag[];      // Patch normal axis magnitudes[3]
         double vert[][];    // Patch vertices [4][3]
         double s0, s1, s2;    // Projected vector co-ordinates
         double t0, t1, t2;    // Projected vector co-ordinates
         Point3[] pvp;          // Pnt position pointer
         Vector3d normal;       // Patch normal
         Vector3d temp;         // Temporary 3-D vector

         // Check for valid patch
         if (ppatch == null) {
         return FALSE;
         }

         // Get patch normal
         normal = ppatch.GetNormal();

         // Calculate divisor
         d = Dot(normal, ray_dir);

         // Determine whether ray is parallel to patch
         if (fabs(d) < MIN_VALUE) {
         return FALSE;
         }

         // Calculate patch plane distance
         temp = Vector3d(ppatch.GetPntPtr(0).GetPosn());
         dist = Dot(normal, temp);

         // Calculate ray hit time parameter
         t = (dist - Dot(normal, start)) / d;

         // Check whether patch plane is behind receiver Pnt or
         // source patch point
         //
         // NOTE: MIN_VALUE offsets are required to prevent
         //       interpretation of adjoining surface vertices as
         //       occlusions
         if (t < MIN_VALUE || t > (1.0 - MIN_VALUE)) {
         return FALSE;
         }

         // Calculate ray-patch plane intersection
         temp = start + (ray_dir * t);

         // Get intersection axes
         isect[0] = temp.GetX();
         isect[1] = temp.GetY();
         isect[2] = temp.GetZ();

         // Get patch normal axis magnitudes
         n_mag[0] = fabs(normal.GetX());
         n_mag[1] = fabs(normal.GetY());
         n_mag[2] = fabs(normal.GetZ());

         // Get patch Pnt axes
         for (i = 0; i < ppatch.GetNumVert(); i++) {
         pvp = ppatch.GetPntPtr(i).GetPosnPtr();
         vert[i][0] = pvp.GetX();
         vert[i][1] = pvp.GetY();
         vert[i][2] = pvp.GetZ();
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

         // Calculate projected Pnt #0 co-ordinates
         s0 = isect[i1] - vert[0][i1];
         t0 = isect[i2] - vert[0][i2];

         // Check for intersection (consider quadrilateral as two
         // adjacent triangles
         i = 2;
         i_flag = FALSE;
         do {
         // Calculate projected Pnt co-ordinates
         s1 = vert[i - 1][i1] - vert[0][i1];
         t1 = vert[i - 1][i2] - vert[0][i2];

         s2 = vert[i][i1] - vert[0][i1];
         t2 = vert[i][i2] - vert[0][i2];

         // Determine vector scaling parameters
         if (fabs(s1) < MIN_VALUE) // Is s1 == 0 ?
         {
         beta = s0 / s2;
         if ((beta >= 0.0) && (beta <= 1.0)) {
         alpha = (t0 - beta * t2) / t1;
         i_flag = ((alpha >= 0.0) && ((alpha + beta)
         <= 1.0));
         }
         } else {
         beta = (s1 * t0 - s0 * t1) / (s1 * t2 - s2 * t1);
         if ((beta >= 0.0) && (beta <= 1.0)) {
         alpha = (s0 - beta * s2) / s1;

         // Test for intersection
         i_flag = ((alpha >= 0.0) && ((alpha + beta)
         <= 1.0));
         }
         }
         i++;    // Advance to next triangle (if any)
         } while (i_flag == FALSE && i < ppatch.GetNumVert());

         return i_flag;
    
         */
        return false;
    }
}
