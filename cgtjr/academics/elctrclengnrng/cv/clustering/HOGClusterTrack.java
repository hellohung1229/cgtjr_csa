/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.clustering;

import cgtjr.academics.elctrclengnrng.cv.CornerDetectGrdntTmp;
import cgtjr.academics.elctrclengnrng.cv.HOGCornerDetectorTmp;
import cgtjr.academics.elctrclengnrng.imgprcssng.GrdntFilterParserTmp;
import cgtjr.academics.elctrclengnrng.imgprcssng.YSclFltrTmp;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.elctrclengnrng.video.TmprlGrdntFltrTmp;

/**
 *
 * @author cgthomasjr
 */
public class HOGClusterTrack extends ImageFilter {

    private ImageFilter imageFilter;
    private YSclFltrTmp ySclFilter;
    private TmprlGrdntFltrTmp tmprlGrdntFilter;
    private GrdntFilterParserTmp grdntFilterParser;
    private CornerDetectGrdntTmp cornerDetectGrdntTmp;
    private HOGCornerDetectorTmp hogCornerDetectorTmp;
    private ClusterHOGTrackerTmp clusterHOGTrackerTmp;
    
    public HOGClusterTrack(){
        //imageFilter = new ImageFilter();
        ySclFilter = new YSclFltrTmp();
        tmprlGrdntFilter = new TmprlGrdntFltrTmp(ySclFilter);
        grdntFilterParser = new GrdntFilterParserTmp(ySclFilter);
        cornerDetectGrdntTmp = new CornerDetectGrdntTmp(ySclFilter,tmprlGrdntFilter,grdntFilterParser);
        hogCornerDetectorTmp = new HOGCornerDetectorTmp(cornerDetectGrdntTmp);
        clusterHOGTrackerTmp = new ClusterHOGTrackerTmp(hogCornerDetectorTmp);
        
        //tmprlGrdntFilter.setYsclFltr(ySclFilter);
        //grdntFilterParser.setYsclFltr(ySclFilter);
    }
    
    @Override
    public void initialize(int myImageWidth, int myImageHeight) {
        //super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.
        //imageFilter.initialize(myImageWidth, myImageHeight);
        ySclFilter.initialize(myImageWidth, myImageHeight);        
        tmprlGrdntFilter.intlzeTmprlGrndnt(myImageWidth, myImageHeight);
        grdntFilterParser.intlzeGrdntFilter(myImageWidth, myImageHeight);
        cornerDetectGrdntTmp.initialize(myImageWidth, myImageHeight);
        hogCornerDetectorTmp.initialize(myImageWidth, myImageHeight);
        clusterHOGTrackerTmp.initialize(myImageWidth, myImageHeight);

        getClusterHOGTrackerTmp().setMaxClusterDistance(25);        
        getCornerDetectGrdntTmp().setEigenThreshold(400);
        getTmprlGrdntFilter().setTmprlGrdntThrshld(-1);
        getHogCornerDetectorTmp().setMaxMatchDstnce(35);        
    }
    @Override
    public void filter(int[] myValue, int i) {
        //System.out.println("HOGCluseterTrack ... test -> "+4);
        ySclFilter.gryFltr1x1(myValue);
        tmprlGrdntFilter.tmprlFilter(ySclFilter.getGryVls());  
        grdntFilterParser.grdntFilter(ySclFilter.getGryVls());
        cornerDetectGrdntTmp.filter(grdntFilterParser.getGrdntMgntd());
        hogCornerDetectorTmp.filter(grdntFilterParser.getGrdntMgntd());
        clusterHOGTrackerTmp.filter(grdntFilterParser.getGrdntMgntd());
    }

    @Override
    public void finish() {
        //super.finish(); //To change body of generated methods, choose Tools | Templates.
        //grdntFilterParser.finish();
    }

    @Override
    public int[] getFltrdData() {
        return ySclFilter.getFltrdData(); //To change body of generated methods, choose Tools | Templates.
    }

    public YSclFltrTmp getySclFilter() {
        return ySclFilter;
    }

    public void setySclFilter(YSclFltrTmp myYSclFilter) {
        this.ySclFilter = myYSclFilter;
    }

    public TmprlGrdntFltrTmp getTmprlGrdntFilter() {
        return tmprlGrdntFilter;
    }

    public void setTmprlGrdntFilter(TmprlGrdntFltrTmp myTmprlGrdntFilter) {
        this.tmprlGrdntFilter = myTmprlGrdntFilter;
    }

    public GrdntFilterParserTmp getGrdntFilterParser() {
        return grdntFilterParser;
    }

    public void setGrdntFilterParser(GrdntFilterParserTmp myGrdntFilterParser) {
        this.grdntFilterParser = myGrdntFilterParser;
    }

    public CornerDetectGrdntTmp getCornerDetectGrdntTmp() {
        return cornerDetectGrdntTmp;
    }

    public void setCornerDetectGrdntTmp(CornerDetectGrdntTmp myCornerDetectGrdntTmp) {
        this.cornerDetectGrdntTmp = myCornerDetectGrdntTmp;
    }

    public HOGCornerDetectorTmp getHogCornerDetectorTmp() {
        return hogCornerDetectorTmp;
    }

    public void setHogCornerDetectorTmp(HOGCornerDetectorTmp myHOGCornerDetectorTmp) {
        this.hogCornerDetectorTmp = myHOGCornerDetectorTmp;
    }

    public ClusterHOGTrackerTmp getClusterHOGTrackerTmp() {
        return clusterHOGTrackerTmp;
    }

    public void setClusterHOGTrackerTmp(ClusterHOGTrackerTmp myClusterHOGTrackerTmp) {
        this.clusterHOGTrackerTmp = myClusterHOGTrackerTmp;
    }    
}