package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.PntInsrtActn;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;

/**
 *
 * @author clayton g thomas jr
 */
public class EMFieldShpBndry extends TrckrShpBndry {

    private TrackerShpPntLstnr hmnCmptrLstenr;
    private TrckrBndry dcsnBndies0;
    private TrckrBndry dcsnBndies1;
    private TrckrBndry dcsnBndies2;

    public EMFieldShpBndry() {
        //dcsnBndies0 = new TrckrBndry();
        //dcsnBndies1 = new TrckrBndry();
        //dcsnBndies2 = new TrckrBndry();
    }

    public EMFieldShpBndry(ShpBndry myShpBndry, PntInsrtActn aPntInsrtActn) {
        //super(myShpBndry, aPntInsrtActn);
        //dcsnBndies0 = new TrckrBndry();
        //dcsnBndies1 = new TrckrBndry();
        //dcsnBndies2 = new TrckrBndry();
    }

    public void addHmnCmptrLstnr(TrackerShpPntLstnr myHmnCmptrListener) {
        hmnCmptrLstenr = myHmnCmptrListener;
    }

    public void updtBndry(Pnt myPoint) {
        super.updtBndry(myPoint);
        double center[] = cmptCenter();
        if (myPoint == null || center == null) {
            return;
        }
        int xPos = (int) myPoint.getX1();
        int yPos = (int) myPoint.getY1();
        int zPos = (int) myPoint.getZ1();


        int xCntr = (int) center[0];
        int yCntr = (int) center[1];
        myPoint.setColor(0x0000ff00);
        //System.out.println("HmnCmptrShpBndrdy 1: x center = "+xCntr+", y center = "+yCntr);
        if (xPos < xCntr - 40) {
            //System.out.println("HmnCmptrShpBndrdy 2: x center = "+xCntr+", y center = "+yCntr);
            //dcsnBndies1.updtBndry(xPos,yPos,zPos);
            myPoint.setColor(0x0000ff00);
        } else if (xPos > xCntr + 40) {
            //      System.out.println("HmnCmptrShpBndrdy 3: x center = "+xCntr+", y center = "+yCntr);
            //dcsnBndies2.updtBndry(xPos,yPos,zPos);
            myPoint.setColor(0x0000ff00);
        } else if (xPos > xCntr - 40 && xPos < xCntr + 40 && yPos < yCntr - 60) {
            //dcsnBndies0 = new TrckrBndry();
            //      System.out.println("HmnCmptrShpBndrdy 4: x center = "+xCntr+", y center = "+yCntr);
            //dcsnBndies0.updtBndry(xPos,yPos,zPos);
            //System.out.println("HmnCmptrShpBndrdy 5: x center = "+xCntr+", y center = "+yCntr);
            myPoint.setColor(0x0000ff00);
        }
    }
    //NOTE: consider keeping this code then drawing onto the image boundary located in shpbndry
   /*
     * public void drawBndry(int[] myPixelData, int myImgWidth,int
     * myImgHeight,int myColor) {
     * super.drawBndry(myPixelData,myImgWidth,myImgHeight,myColor);
     * dcsnBndies0.drawBndry(myPixelData, myImgWidth,myImgHeight,0x000000ff);
     * dcsnBndies1.drawBndry(myPixelData, myImgWidth,myImgHeight,0x00ff0000);
     * dcsnBndies2.drawBndry(myPixelData, myImgWidth,myImgHeight,0x00ff0000);
     *
     * }
     */
}