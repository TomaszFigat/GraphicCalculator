import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class PowSqrtWindow extends JFrame {
	JLabel label ;
	JTextArea jta = new JTextArea();
	JButton b1 = new JButton("ok");
	int []power;
	PowSqrtWindow(int []ll){
		this.power=ll;
		this.setLayout(new GridLayout(3,1));label=new JLabel("Podaj stopien/potege");
		this.add(label);
		this.add(jta);
		this.add(b1);
		
		
			b1.addActionListener((e)->
			{
				
				this.power[0]=pow();
				this.dispose();
				this.setVisible(false);
			});
		
		
	}
	int pow() {
		return Integer.parseInt(jta.getText());
	}

}
