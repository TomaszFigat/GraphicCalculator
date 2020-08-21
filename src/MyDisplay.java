

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public
    class MyDisplay
    extends JPanel
    implements CalcListener, SystemListener{
	private	MyCalculator myCalculator;
	private	JTextArea jtf;
	private	boolean status=true;
	private	boolean opstat=true;
	private	String system;
	private	String previousSystem;
	private	String realText="";
	private	String decR="";
	private	String dec="";
	private	String number="";
	private	String binR="";
	private	String octR="";
	private	String hexR="";
	private	String bin="";
	private	String oct="";
	private	String hex="";

	private boolean sinCos=false;
   // String bin;
    public MyDisplay(){
        jtf = new JTextArea();
        jtf.setLineWrap(true);
        jtf.setEditable(false);
        jtf.setBorder(getBorder());
        this.system = "DEC";
        
        this.setLayout(new BorderLayout());
        this.add(jtf, BorderLayout.CENTER);
    }


    @Override
    public void digitEvent(ActionEvent evt) {
    	if(sinCos) {
    		this.number="";
			this.dec="";
			sinCos=false;
    	}
    	
    	this.status=false;
    	this.opstat=true;
    	//int decimal=Integer.parseInt(binaryString,2);
    		this.realText=this.realText+evt.getActionCommand();
    		
    		
    		this.number = this.number+(evt.getActionCommand());
    	System.out.println(this.number + "--------- o to numer");
    	
    	
    	try {
    		switch (this.system) {
    		case "DEC":
    			this.dec=this.number;
    		this.bin = Integer.toBinaryString(Integer.parseInt(this.number));
    		this.hex = Integer.toHexString(Integer.parseInt(this.number));
    		this.oct = Integer.toOctalString(Integer.parseInt(this.number));
    		break;
    		case "BIN":
    			this.bin=this.number;
    		this.dec = Integer.toString(Integer.parseInt(this.number,2));
    		this.hex = Integer.toHexString(Integer.parseInt(this.number,2));
    		this.oct = Integer.toOctalString(Integer.parseInt(this.number,2));
    		break;
    		case "OCT":
    			this.oct=this.number;
        	this.dec = Integer.toString(Integer.parseInt(this.number,8));
        	this.hex = Integer.toHexString(Integer.parseInt(this.number,8));
        	this.bin = Integer.toBinaryString(Integer.parseInt(this.number,8));
        	break;
    		case "HEX":
    			this.hex=this.number;
        	this.dec = Integer.toString(Integer.parseInt(this.number,16));
        	this.bin = Integer.toBinaryString(Integer.parseInt(this.number,16));
        	this.oct = Integer.toOctalString(Integer.parseInt(this.number,16));
        	break;	
    		}
    	}catch(NumberFormatException e) {
    		System.out.println(e);
        	jtf.setText(
			"\t\t\tLiczba jest za duza!"		
			);
        	this.number=this.number.substring(0,this.number.length()-2);
        	System.out.println("opppppppppppp        "+this.number);
        	this.realText=this.realText.substring(0,this.realText.length()-2);
    	}
    	
    	show();
    	
    	System.out.println(this.number);
    }

    @Override
    public void operationEvent(ActionEvent evt) {
    	
    	if(this.opstat) {
    this.status=true;
    	this.realText=this.realText+evt.getActionCommand();
//    	jtf.setText(
//    			jtf.getText()+(evt.getActionCommand()
//    			);
    	this.decR=this.decR+this.dec+evt.getActionCommand();
    	this.binR=this.binR+this.bin+evt.getActionCommand();
    	this.octR=this.octR+this.oct+evt.getActionCommand();
    	this.hexR=this.hexR+this.hex+evt.getActionCommand();
    	this.number="";
    	
    	this.opstat=false;
    	
    	}
    	show();
    }


	@Override
	public void changeSystemEvent(ActionEvent evt) {
		if(sinCos) {
    		this.number="";
			this.dec="";
			sinCos=false;
    	}
		this.previousSystem = this.system;
		this.system=evt.getActionCommand();
				//((JRadioButton)evt.getSource()).getText();
		if(this.number!="")
		switch(this.system) {
		case "DEC":
			switch(this.previousSystem) {
			case "HEX":
				number=Integer.toString(Integer.parseInt(number,16));
				break;
			case "OCT":
				number=Integer.toString(Integer.parseInt(number,8));
				break;
			case "BIN":
				number=Integer.toString(Integer.parseInt(number,2));
				break;
			}
//			//int decimal=Integer.parseInt(number,2);
//			//System.out.println("to jest deci "+ decimal);
//			number=Integer.toString(Integer.parseInt(number,2));
//			System.out.println("to jest decimal: " + number);
			break;
		case "HEX":
			switch(this.previousSystem) {
			case "DEC":
				number=Integer.toHexString(Integer.parseInt(number));
				break;
			case "OCT":
				number=Integer.toHexString((Integer.parseInt(number,8)));
				break;
			case "BIN":
				number=Integer.toHexString(Integer.parseInt(number,2));
				break;
			}
			break;
		case "OCT":
			switch(this.previousSystem) {
			case "DEC":
				number=Integer.toOctalString(Integer.parseInt(number));
				break;
			case "HEX":
				number=Integer.toOctalString(Integer.parseInt(number,16));
				break;
			case "BIN":
				number=Integer.toOctalString(Integer.parseInt(number,2));
				break;
			}
			break;
		case "BIN":
			switch(this.previousSystem) {
			case "DEC":
				number=Integer.toBinaryString(Integer.parseInt(number));
				break;
			case "HEX":
				number=Integer.toBinaryString(Integer.parseInt(number,16));
				break;
			case "OCT":
				number=Integer.toBinaryString(Integer.parseInt(number,8));
				break;
			}
			
			break;
		}
		System.out.println(this.number);
		show();
	}
	public void show() {
		jtf.setFont(new Font("serif", Font.PLAIN, 35));
		switch(this.system) {
		case "DEC":
			jtf.setText(
					this.status ? this.decR : this.decR + this.dec
    	        );
		
			break;
		case "HEX":
			jtf.setText(
					this.status ? this.hexR : this.hexR + this.hex			
    	        );
			
			break;
		case "OCT":
			jtf.setText(
					this.status ? this.octR : this.octR + this.oct
    	        );
			
			break;
		case "BIN":
			jtf.setText(
					this.status ? this.binR : this.binR + this.bin
    	        );
			
			break;
		}
	}


	@Override
	public void deletingEvent(ActionEvent evt) {
		if((evt.getActionCommand()=="C")) {
	    	System.out.println(jtf.getText());
	    	realText="";
	    	number="";
	    	decR="";
	    	binR="";
	    	octR="";
	    	hexR="";
	    	dec="";
	    	bin="";
	    	oct="";
	    	hex="";
	    	jtf.setText("");
	    	}
	    	if((evt.getActionCommand()=="<")&&realText.length()!=0) {
	    		this.opstat=true;
	    		this.realText=this.realText.substring(0,this.realText.length()-1);
	    		
	    		System.out.println(this.dec);
	    		if(!status) {
	    			this.number=this.number.substring(0,this.number.length()-1);
	    		this.dec=this.dec.substring(0,this.dec.length()-1);
	    		this.bin=this.bin.substring(0,this.bin.length()-1);
	        	this.oct=this.oct.substring(0,this.oct.length()-1);
	        	this.hex=this.hex.substring(0,this.hex.length()-1);
	    		}
	    		else {
	    			this.decR=this.decR.substring(0,this.decR.length()-1);
	    		//System.out.println(this.decR.substring(0,this.decR.length()-1));
//	    		this.decR=this.decR.substring(0,this.decR.length()-1);
	        	this.binR=this.binR.substring(0,this.binR.length()-1);
	        	this.octR=this.octR.substring(0,this.octR.length()-1);
	        	this.hexR=this.hexR.substring(0,this.hexR.length()-1);
	    		}
	    	}
	    	show();
		
	}


	@Override
	public void functionEvent(ActionEvent evt) {
		this.myCalculator=new MyCalculator(this.status ? this.decR : this.decR + this.dec);
		if(evt.getActionCommand()=="sin") {
			this.dec="";
			this.oct="";
			this.bin="";
			this.hex="";
			this.number=Double.toString(Math.sin(this.myCalculator.solveEquation()));
			this.dec=Double.toString(Math.sin(this.myCalculator.solveEquation()));
			this.sinCos=true;
			
		}
		if(evt.getActionCommand()=="^2") {

			this.number=Integer.toString((int)Math.pow(this.myCalculator.solveEquation(),2));
			this.dec=Integer.toString((int)Math.pow(this.myCalculator.solveEquation(),2));
			this.oct=Integer.toOctalString((int)Math.pow(this.myCalculator.solveEquation(),2));
			this.bin=Integer.toBinaryString((int)Math.pow(this.myCalculator.solveEquation(),2));
			this.hex=Integer.toHexString((int)Math.pow(this.myCalculator.solveEquation(),2));
			
			
		}
		if(evt.getActionCommand()=="sqrt") {
			this.number=Integer.toString((int)Math.sqrt(this.myCalculator.solveEquation()));
			this.dec=Integer.toString((int)Math.sqrt(this.myCalculator.solveEquation()));
			this.oct=Integer.toOctalString((int)Math.sqrt(this.myCalculator.solveEquation()));
			this.bin=Integer.toBinaryString((int)Math.sqrt(this.myCalculator.solveEquation()));
			this.hex=Integer.toHexString((int)Math.sqrt(this.myCalculator.solveEquation()));
			
		}
		if(evt.getActionCommand()=="ln(x)") {
			this.number=Integer.toString((int)Math.log((this.myCalculator.solveEquation())));
			this.dec=Integer.toString((int)Math.log(this.myCalculator.solveEquation()));
			this.oct=Integer.toOctalString((int)Math.log(this.myCalculator.solveEquation()));
			this.bin=Integer.toBinaryString((int)Math.log(this.myCalculator.solveEquation()));
			this.hex=Integer.toHexString((int)Math.log(this.myCalculator.solveEquation()));
			
		}
	if(evt.getActionCommand()=="=") {
		
		this.number=Integer.toString((int)this.myCalculator.solveEquation());
		this.dec=Integer.toString((int)this.myCalculator.solveEquation());
		this.oct=Integer.toOctalString((int)this.myCalculator.solveEquation());
		this.bin=Integer.toBinaryString((int)this.myCalculator.solveEquation());
		this.hex=Integer.toHexString((int)this.myCalculator.solveEquation());
		
	}
		decR="";
    	binR="";
    	octR="";
    	hexR="";
		show();
	}


	@Override
	public void bracketEvenet(ActionEvent evt) {
		if(evt.getActionCommand()=="("||evt.getActionCommand()==")") {
	    	this.decR=this.decR+this.dec+evt.getActionCommand();
	    	this.binR=this.binR+this.bin+evt.getActionCommand();
	    	this.octR=this.octR+this.oct+evt.getActionCommand();
	    	this.hexR=this.hexR+this.hex+evt.getActionCommand();
	    	}
		
	}
}
