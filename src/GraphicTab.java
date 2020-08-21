import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class GraphicTab extends JPanel{
private JButton createPolynomial, pickPolynomials, net,dnet;
private ChartDisplay chart;
private boolean cp=false,dp=false;
ArrayList<PolynomialProp> polynomialList;
ArrayList<PolynomialListener> polynomialnet = new ArrayList<>();
PickPolynomialWindow ppw;
private JPanel buttons;
boolean cpE=false;
public GraphicTab() {
	polynomialList = new ArrayList();
	polynomialList.add(new PolynomialProp("x^2"));
	polynomialList.add(new PolynomialProp("x"));
	polynomialList.get(0).checkbox=true;
	polynomialList.get(1).checkbox=false;
	this.ppw =new PickPolynomialWindow(polynomialList);
	ppw.setVisible(false);
	this.setLayout(new BorderLayout());
	createPolynomial = new JButton("Create Polynomial");
	pickPolynomials = new JButton("Pick Polynomials");
	net = new JButton("Net");
	dnet = new JButton("Decimal Net");
	//xSlider = new JSlider();
	//ySlider = new JSlider();
	//ySlider.setOrientation((SwingConstants.VERTICAL));
	JPanel nets = new JPanel(new GridLayout(1,2));
	nets.add(net);
	nets.add(dnet);
	chart = new ChartDisplay(polynomialList);
	chart.setBackground(Color.WHITE);
	chart.setPreferredSize(new Dimension(600,600));
	ppw.addPickPolynomialListener(chart);
	buttons = new JPanel(new GridLayout(3,1));
	//buttons.add(createPolynomial);
	Box right = Box.createVerticalBox();
    buttons.add(createPolynomial);
    buttons.add(pickPolynomials);
    buttons.add(nets);
//	buttons.add(pickPolynomials);
//	buttons.add(deletePolynomial);
	//this.add(createPolynomial, BorderLayout.LINE_END);
    this.addPolynomialListener(chart);
	this.add(buttons, BorderLayout.LINE_END);
	//this.add(buttons);
	this.add(chart,BorderLayout.CENTER);
	//this.add(xSlider,BorderLayout.PAGE_END);
	//this.add(ySlider,BorderLayout.LINE_START);
//	CreatePolynomial cp= new CreatePolynomial(polynomials);
//	PickPolynomialWindow ppw = new PickPolynomialWindow(polynomials);
	//cp.addPolynomialListener(ppw);
	createPolynomial.addActionListener((e)->{
		
		CreatePolynomial cp =new CreatePolynomial(polynomialList);
		cp.addPolynomialListener(ppw);
		
		
		
	});
	pickPolynomials.addActionListener((e)->{
		ppw.setVisible(true);
		
//		ppw.setVisible(true);
	}
	
	
	);
	
	net.addActionListener((e)->{
		
		 for(PolynomialListener polynomialListener : polynomialnet){
         	System.out.println(polynomialListener);
             polynomialListener.addPolynomialEvent(e);
         }	
		});
	dnet.addActionListener((e)->{
		
		 for(PolynomialListener polynomialListener : polynomialnet){
        	System.out.println(polynomialListener);
            polynomialListener.addPolynomialEvent(e);
        }	
		});
}
public void addPolynomialListener(PolynomialListener chart2){
    polynomialnet.add(chart2);
}
}
