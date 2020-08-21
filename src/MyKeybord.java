

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

public
    class MyKeybord
    extends JPanel implements SystemListener {

    JButton[] buttons;
    String[] labels = {
       "1","2","3","+","C","sin",
       "4","5","6","-","^2","cos",
       "7","8","9","*","sqrt","(",
       "<","0","=","/","ln(x)",")",
       "a",".","c","d","e","f"
    };

    ArrayList<CalcListener> calcListeners = new ArrayList<>();


    public MyKeybord(){
        buttons = new JButton[30];

        this.setLayout(new GridLayout(5,6));

        for(int i=0; i<buttons.length;i++){
        	
            buttons[i] = new JButton(labels[i]);
            this.add(buttons[i]);
            switch (labels[i]){             	
                case "C":
                case "<":
                	buttons[i].addActionListener((e)->{
                		  for(CalcListener calcListener : calcListeners){
                          	
                              calcListener.deletingEvent(e);
                          }
                	});
                	break;
               
                case "=":
                case "^2":
                case "sqrt":
                case "ln(x)":
                case "sin":
                case "cos":
                	buttons[i].addActionListener((e)->{
              		  for(CalcListener calcListener : calcListeners){
                        	
                            calcListener.functionEvent(e);
                        }
              	});
              	break;
                case "(":
                case ")":
                case "+":    
                case "-":
                case "*":
                case "/":
                    buttons[i].addActionListener(
                        (e)->{
//                            System.out.println(
//                                    ((JButton)e.getSource()).getText()
//                            );
                            for(CalcListener calcListener : calcListeners){
                            	
                                calcListener.operationEvent(e);
                            }
                        }
                    );
                    break;
                default:
                    buttons[i].addActionListener(
                        (e)->{
                       
                            for(CalcListener calcListener : calcListeners){
                                calcListener.digitEvent(e);
                            }
                        }
                    );
            }
            if(i>23&&i!=25)
        		buttons[i].setEnabled(false);
        }
    }

    public void addCalcListener(CalcListener calcListener){
        calcListeners.add(calcListener);
    }

	@Override
	public void changeSystemEvent(ActionEvent evt) {
		
		switch(evt.getActionCommand()) {
		case "DEC":
			Pattern patternD = Pattern.compile("[a-f]");
			for(int i=0 ; i<this.buttons.length; i++) {
				if(patternD.matcher(this.buttons[i].getText()).matches()) {
					this.buttons[i].setEnabled(false);
					if(this.buttons[i].getText()=="b") {
						this.buttons[i].setText(".");
						this.buttons[i].setEnabled(true);
					}
				}
				else
					this.buttons[i].setEnabled(true);
			}
			break;
		case "OCT":
			Pattern patternH = Pattern.compile("[a-f8-9]||\\.||sin||cos");
			for(int i=0 ; i<this.buttons.length; i++) {
				if(patternH.matcher(this.buttons[i].getText()).matches()) {
					if(this.buttons[i].getText()==".")
						this.buttons[i].setText("b");
					this.buttons[i].setEnabled(false);
				}
				else
					this.buttons[i].setEnabled(true);
			}
			break;
		case "HEX":
			
			for(int i=0 ; i<this.buttons.length ; i++) {
				
					if(this.buttons[i].getText()==".")
						this.buttons[i].setText("b");
					
					this.buttons[i].setEnabled(true);
					if(this.buttons[i].getText()=="sin"||this.buttons[i].getText()=="cos")
						this.buttons[i].setEnabled(false);
				
				
			}
			break;

		case "BIN":
			
			Pattern patternB = Pattern.compile("([2-9a-f]||\\.||sin||cos)");
			for(int i=0 ; i<this.buttons.length ; i++) {
				if(patternB.matcher(this.buttons[i].getText()).matches()){
					if(this.buttons[i].getText()==".")
						this.buttons[i].setText("b");
					this.buttons[i].setEnabled(false);
				}
				else
					this.buttons[i].setEnabled(true);
				
			}
			break;
		}
		
	}
}
