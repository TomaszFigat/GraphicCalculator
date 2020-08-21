import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CreatePolynomial extends JFrame {
 private JPanel jp;
 private JTextArea jta;
 private JButton jb1; 
 private JButton jb2; 
 private JPanel bg;
 private JLabel label;
 private Pattern pattern; 
 private boolean aE=false;
 ArrayList<PolynomialListener> polynomialsy = new ArrayList<>();
 public CreatePolynomial(ArrayList<PolynomialProp> polynomials) {
	 pattern = Pattern.compile("((\\-?[0-9]*x\\^\\-?.[2-9]*)[+-]?[0-9]*)*(([+-]?[0-9]*x[+-].[0-9]*)||([+-]?[0-9]*x))?" );
	 jp = new JPanel(new GridLayout(3,1));
	 jta = new JTextArea();
	 jta.setLineWrap(true);
	 jta.setPreferredSize(new Dimension(300,50));
	 bg = new JPanel();
	 jb1 = new JButton("OK");
	 jb2 = new JButton("Cancel");
	 label = new JLabel("Wprowadz wielomian:");
	 
	 jb2.addActionListener((e)->{
		 this.dispose();
		 this.setVisible(false);
	 });
	 jb1.addActionListener((e)->{ 
		 
		 if(!jta.getText().isEmpty()) {
			
					
			 if(pattern.matcher(jta.getText()).matches()) {
				
			
				
				 for(PolynomialProp x : polynomials) {
					 if(x.polynomial.equals(jta.getText())) {
						 aE=true;
						 JOptionPane.showMessageDialog(null, "Wielomian juz istnieje");
						 break;
					 }
				 }
				 if(!aE) {
				 //JOptionPane.showMessageDialog(null, "Dobry wielomian");
				 polynomials.add(new PolynomialProp(jta.getText()));
				 for(PolynomialListener polynomialListener : polynomialsy){
		            	System.out.println(polynomialListener);
		                polynomialListener.addPolynomialEvent(e);
		            }	
				 jta.setText(""); 
				 }
				  	aE=false;
						
			 }
			 else {
				 JOptionPane.showMessageDialog(null, "Niepoprawna formu³a wielomianu");
			 }
				 
		 }
		 else {
			 JOptionPane.showMessageDialog(null, "Wprowadz wielomian");
		 }
		 
	 });
	 
	 jp.add(label);
	 bg.add(jb1);
	 bg.add(jb2);
	 jp.add(jta);
	 jp.add(bg);
	 
	 this.add(jp);
	 this.pack();
	 this.setVisible(true);
 }
 public void addPolynomialListener(PolynomialListener polynomialListener){
     polynomialsy.add(polynomialListener);
 }
}
