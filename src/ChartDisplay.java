import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;

public class ChartDisplay extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener, PickPolynomialListener, PolynomialListener{

	private double zoomFactor = 1;
	private double prevZoomFactor = 1;
	private boolean zoomer;
	private boolean dragger;
	private boolean released;
	private double xOffset = 0;
	private double yOffset = 0;
	private int xDiff;
	private int yDiff;
	private Point startPoint;



	private int x0;
	private int y0;
	private int sizer;
	private int sizerro;
	private boolean net=false;
	private boolean dnet=false;
	ArrayList<PolynomialProp> polynomials;

	public ChartDisplay(ArrayList<PolynomialProp> polynomials){
		this.polynomials=polynomials;
		//	repaint();
		x0 = this.getWidth()/2;
		y0 = this.getHeight()/2;System.out.println(x0 + "  " + y0);
		initComponent();
		//zoomFactor=0.20;
		//		this.setPreferredSize(new Dimension(500,500));
	}
	private void initComponent() {
		addMouseWheelListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public void paintComponent (Graphics g){
		super.paintComponent(g);

		x0 = this.getWidth()/2;
		y0 = this.getHeight()/2;
		// int size =x0*2+100+sizerro;
		this.sizerro=500;//this.sizerro+sizer;
		int size =x0*2+100+3*sizer;
		System.out.println("to jest sajizk  "+ size);
		int[] x= new int[size];
		System.out.println(size);
		int []y= new int[size];
		System.out.println(sizerro + " to je sizerot");
		System.out.println(-765/10d+ " llalalalalla");
		Graphics2D g2 = (Graphics2D) g;

		if (zoomer) {
			AffineTransform at = new AffineTransform();
			// if(zoomFactor>0.063d) {
			double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
			double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();
			System.out.println(xRel+ " x rel");
			double zoomDiv = zoomFactor / prevZoomFactor;

			xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
			yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

			at.translate(xOffset, yOffset);
			at.scale(zoomFactor, zoomFactor);

			prevZoomFactor = zoomFactor;
			System.out.println("to jest zoom factor:  "+zoomFactor);

			g2.transform(at);
			zoomer = false;

		}

		if (dragger) {
			AffineTransform at = new AffineTransform();
			at.translate(xOffset + xDiff, yOffset + yDiff);
			at.scale(zoomFactor, zoomFactor);
			g2.transform(at);

			if (released) {
				xOffset += xDiff;
				yOffset += yDiff;
				if(xOffset>6000)
					xOffset=6000;
				if(xOffset<-6000)
					xOffset=-6000;
				dragger = false;
			}

		}

		g2.setColor(Color.BLACK);

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);



		if(dnet) {
			g2.setColor(Color.LIGHT_GRAY);
			g2.setStroke(new BasicStroke(1));
			for(int i= x0; i < 18000 ; i+=10)
				g2.drawLine(i-9000, -9000, i-9000 ,9000);
			for(int i= y0; i < 18000 ; i+=10)
				g2.drawLine(-9000, i-9000, 9000 ,i-9000);
		}
		if(net) {
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(2));
			for(int i= x0; i < 18000 ; i+=100)
				g2.drawLine(i-9000, -9000, i-9000 ,9000);
			for(int i= y0; i < 18000 ; i+=100)
				g2.drawLine(-9000, i-9000, 9000 ,i-9000);
		}
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(-8000-sizerro, this.getHeight()/2, 8000+sizerro, this.getHeight()/2);
		g2.drawLine(this.getWidth()/2, -8000-sizerro, this.getWidth()/2, 8000+sizerro);

		for(PolynomialProp polynomialsList : polynomials) {
			ChartPolynomialEquation cpe = new ChartPolynomialEquation(polynomialsList.polynomial);
			if(polynomialsList.checkbox) {
				for(int i=0; i < size ; i++) {
					x[i]=i-size/2;
					y[i]=(-1)*(int)(100*(cpe.score(x[i]/100d)));
					//	if(y[i]=="Infinity")
					//		y[i]=0;
					x[i]+=x0;
					y[i]+=y0;
				}

				g2.setColor(polynomialsList.color);
				g2.setStroke(new BasicStroke(3));
				g2.drawPolyline( x ,y, size);
			}
		}	
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		zoomer = true;

		//Zoom in
		if (e.getWheelRotation() < 0) {
			zoomFactor *= 1.1;
			repaint();
		}
		//Zoom out
		if (e.getWheelRotation() > 0) {
			zoomFactor /= 1.1;
			repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point curPoint = e.getLocationOnScreen();
		xDiff = curPoint.x - startPoint.x;
		yDiff = curPoint.y - startPoint.y;

		//   sizer=x0+(-1)*(xDiff)+(-1)*yDiff;
		dragger = true;
		repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		released = false;
		startPoint = MouseInfo.getPointerInfo().getLocation();
		int x,y;
		x=startPoint.x-x0;
		y=startPoint.y-y0;
		if(x<0)
			x=x*(-1);
		if(y<0)
			y=y*(-1);
		sizer=startPoint.x-x0 + startPoint.y-y0;
		// x0+=startPoint.x;
		//   repaint();
		//        if(startPoint.x>6000)
		//        	startPoint.x=6000;
		//        if(startPoint.y<-6000)
		//        	startPoint.y=-6000;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		released = true;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	@Override
	public void pickPolynomialEvent(Event evt) {
		//System.out.println(evt);
		repaint();

	}
	@Override
	public void addPolynomialEvent(ActionEvent evt) {
		if(evt.getActionCommand()=="Net") {
			if(this.net)
				this.net=false;
			else
				this.net=true;
		}

		if(evt.getActionCommand()=="Decimal Net") {

			if(this.dnet)
				this.dnet=false;
			else
				this.dnet=true;
		}
		repaint();
	}


}
