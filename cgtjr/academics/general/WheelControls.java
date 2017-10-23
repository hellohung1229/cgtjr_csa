package cgtjr.academics.general;


import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class WheelControls extends Canvas implements RotationControls, MouseMotionListener, MouseListener {

    private final static int NONE=0;
    private final static int SLIDE_Y=1;
    private final static int SLIDE_X=2;
    private final static int SLIDE_Z=3;

    private int mode = NONE;

    private Dimension size;
    private Dimension canvasSize;
    private int thickness;
    private int diameter;
    private int space;
    private int pipSize;
    private int pipOffset;	// Amount pip is below wheel
    private int margin=10;		// Margin between edge of Canvas and
				// controls

    private Polygon yPip;
    private Rectangle yBackClip;

    private Polygon xPip;
    private Rectangle xBackClip;

    private Polygon zPip;

    private Rectangle yArea;
    private Rectangle xArea;
    private Rectangle zArea;

    private Point oldMousePos = new Point();

    float yAngle = 0.0f;
    float xAngle = 0.0f;
    float zAngle = 0.0f;

    float yOrigAngle;
    float xOrigAngle;
    float zOrigAngle;

    float angleStep = (float)Math.PI/30.0f;
    //margin = 10;

    public WheelControls() {
	this(0.0f, 0.0f, 0.0f);
    }

    public WheelControls( float rotX, float rotY, float rotZ ) {
	size = new Dimension( 75, 75 );
      canvasSize = new Dimension(3*size.width-margin,size.height);

	xAngle = constrainAngle(rotX);
	yAngle = constrainAngle(rotY);
	zAngle = constrainAngle(rotZ);

        yOrigAngle = yAngle;
        xOrigAngle = xAngle;
        zOrigAngle = zAngle;

	setSizes();

	yPip = new Polygon();
	yPip.addPoint( diameter/2, pipOffset  );
	yPip.addPoint( diameter/2-pipSize/2, pipOffset-pipSize );
	yPip.addPoint( diameter/2+pipSize/2, pipOffset-pipSize );

	xPip = new Polygon();
	xPip.addPoint(diameter/2, pipOffset);
	xPip.addPoint( diameter/2-pipSize/2, pipOffset-pipSize );
	xPip.addPoint( diameter/2+pipSize/2, pipOffset-pipSize  );

	zPip = new Polygon();
	zPip.addPoint( diameter/2, pipOffset );
	zPip.addPoint( diameter/2-pipSize/2, pipOffset-pipSize );
	zPip.addPoint( diameter/2+pipSize/2, pipOffset-pipSize );

	addMouseListener( this );
	addMouseMotionListener( this );
    }

    private void setSizes() {

	int width = size.width - margin*2;
	thickness = width * 20 / 100;
	//diameter = width * 70 / 100;
	diameter = width;
	space = width * 10 / 100;
	pipSize = width * 20 / 100;
	pipOffset = thickness/2;

    }
    public void paint( Graphics g ) {
	Graphics2D g2 = (Graphics2D)g;
	g2.drawOval( margin,margin, diameter, diameter );
	xArea = new Rectangle( margin, margin, diameter, diameter );
	g2.drawString( "X", margin+diameter/2- 3, margin+diameter/2 +5);
	g2.drawString( "Y", diameter+2*margin+diameter/2- 3, margin+diameter/2+5 );
	g2.drawString( "Z", 2*diameter+3*margin+diameter/2 - 3, margin+diameter/2+5 );
	drawXPip( g2, xAngle );
	g2.drawOval( diameter+2*margin,margin, diameter, diameter );
	yArea = new Rectangle( diameter+2*margin, margin, diameter, diameter );
	drawYPip( g2, yAngle );
	g2.drawOval( 2*diameter+3*margin,margin, diameter, diameter );
	zArea = new Rectangle( 2*diameter+3*margin, margin, diameter, diameter );
	drawZPip( g2, zAngle );
    }

    public float getXAngle() {
      //System.out.println("Wheelcontrol ... x angle = "+xAngle);
	return xAngle;
    }

    public float getYAngle() {
      //System.out.println("Wheelcontrol ... y angle = "+yAngle);
	return yAngle;
    }

    public float getZAngle() {
      //System.out.println("Wheelcontrol ... z angle = "+zAngle);
	return zAngle;
    }


    public void reset() {
                // Overwrite the old pip
                drawYPip( (Graphics2D)(this.getGraphics()),yAngle );
                yAngle = yOrigAngle;
                // Draw the new Pip
                drawYPip( (Graphics2D)(this.getGraphics()),yAngle );

                // Overwrite the old pip
                drawXPip( (Graphics2D)(this.getGraphics()),xAngle );
                xAngle = xOrigAngle;
                // Draw the new Pip
                drawXPip( (Graphics2D)(this.getGraphics()),xAngle );

                drawZPip( (Graphics2D)(this.getGraphics()),zAngle );
 
                zAngle =  zOrigAngle;

                drawZPip( (Graphics2D)(this.getGraphics()),zAngle );
                oldMousePos.setLocation(0,0);
    }

 
    private void drawZPip( Graphics2D g2, float zAngle ) {
	AffineTransform trans = new AffineTransform();
	Color origColor = g2.getColor();

	trans.translate( 2*diameter+3*margin, margin );
	trans.rotate(zAngle, diameter/2, diameter/2 );

	g2.setXORMode( getBackground() );
	g2.setTransform(trans);
	g2.setColor( Color.green );
	g2.fillPolygon( zPip );

	// Reset graphics context
	trans.setToIdentity();
	g2.setTransform( trans );
	g2.setColor( origColor );
	g2.setPaintMode();
    }
    private void drawYPip( Graphics2D g2, float yAngle ) {
	AffineTransform trans = new AffineTransform();
	Color origColor = g2.getColor();

	trans.translate( diameter+2*margin, margin );
	trans.rotate(yAngle, diameter/2, diameter/2 );

	g2.setXORMode( getBackground() );
	g2.setTransform(trans);
	g2.setColor( Color.green );
	g2.fillPolygon( yPip );

	// Reset graphics context
	trans.setToIdentity();
	g2.setTransform( trans );
	g2.setColor( origColor );
	g2.setPaintMode();
    }
    private void drawXPip( Graphics2D g2, float xAngle ) {
	AffineTransform trans = new AffineTransform();
	Color origColor = g2.getColor();

	trans.translate( margin, margin );
	trans.rotate(xAngle, diameter/2, diameter/2 );

	g2.setXORMode( getBackground() );
	g2.setTransform(trans);
	g2.setColor( Color.green );
	g2.fillPolygon( xPip );

	// Reset graphics context
	trans.setToIdentity();
	g2.setTransform( trans );
	g2.setColor( origColor );
	g2.setPaintMode();
    }
    public Dimension getPreferredSize() {
	return canvasSize;
    }
    /*
    public void setSize( Dimension d ) {
	// Set size to smallest dimension
	if (d.width<d.height)
	    size.width = size.height = d.width;
	else
	    size.width = size.height = d.height;
	setSizes();
    }*/

    public void mouseClicked( MouseEvent e ) {
    }

    public void mouseEntered( MouseEvent e ) {
    }

    public void mouseExited( MouseEvent e ) {
    }

    public void mousePressed( MouseEvent e ) {
	if ( yArea.contains( e.getPoint() )) {
	    mode = SLIDE_Y;
	    oldMousePos = e.getPoint();
	} else if (xArea.contains( e.getPoint() )) {
	    mode = SLIDE_X;
	    oldMousePos = e.getPoint();
	} else if (zArea.contains( e.getPoint() )) {
	    mode = SLIDE_Z;
	    oldMousePos = e.getPoint();
	}
    }

    public void mouseReleased( MouseEvent e ) {
	mode = NONE;
    }

    public void mouseDragged( MouseEvent e ) {
	Point pos = e.getPoint();

	int diffX = pos.x - oldMousePos.x;
	int diffY = pos.y - oldMousePos.y;
      System.out.println("WheelControl.mouseDragged() : mode = "+mode);
	switch(mode) {
	    case NONE:
		break;
	    case SLIDE_Y:
		// Overwrite the old pip
		drawYPip( (Graphics2D)((Canvas)e.getSource()).getGraphics(),
			  yAngle );
		if (diffX<0)
		    yAngle -= angleStep;
		else if (diffX>0)
		    yAngle += angleStep;

		yAngle = constrainAngle(yAngle);

		// Draw the new Pip
		drawYPip( (Graphics2D)((Canvas)e.getSource()).getGraphics(),
			  yAngle );
	        oldMousePos = pos;
		break;
	    case SLIDE_X:
		// Overwrite the old pip
		drawXPip( (Graphics2D)((Canvas)e.getSource()).getGraphics(),
			  xAngle );
                //Use diffY for up/down angle change
		//if (diffY<0)
		//    xAngle -= angleStep;
		//else if (diffY>0)
		//    xAngle += angleStep;
                
		if (diffX<0)
		    xAngle -= angleStep;
		else if (diffX>0)
		    xAngle += angleStep;
                
		xAngle = constrainAngle(xAngle);

		// Draw the new Pip
		drawXPip( (Graphics2D)((Canvas)e.getSource()).getGraphics(),
			  xAngle );
	        oldMousePos = pos;
		break;
	    case SLIDE_Z:
		drawZPip( (Graphics2D)((Canvas)e.getSource()).getGraphics(),
			  zAngle );

		if (diffX<0)
		    zAngle -= angleStep;
		else if (diffX>0)
		    zAngle += angleStep;

		zAngle = constrainAngle( zAngle );
		drawZPip( (Graphics2D)((Canvas)e.getSource()).getGraphics(),
			  zAngle );
	        oldMousePos = pos;
		break;
	    default:
		throw( new RuntimeException("Internal Error"));
	}
    }

    public void mouseMoved( MouseEvent e ) {
    }

    /**
      * Constrain angle to be 0<angle<2PI
      */
    private float constrainAngle( float angle ) {
        if ( angle > (float)Math.PI*2 ) return angle-(float)Math.PI*2;
        if ( angle < 0.0f) return angle+(float)Math.PI*2;
	return angle;
    }
}
