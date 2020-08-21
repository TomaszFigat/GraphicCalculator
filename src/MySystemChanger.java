import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MySystemChanger extends JPanel{
	private JRadioButton hex;
	private JRadioButton dec;
	private JRadioButton oct;
	private JRadioButton bin;
	private ButtonGroup group;
	ArrayList<SystemListener> systemListeners = new ArrayList<>();
	MySystemChanger() {
		hex = new JRadioButton("HEX",false);
		dec = new JRadioButton("DEC",true);
		oct = new JRadioButton("OCT",false);
		bin = new JRadioButton("BIN",false);
		
		this.add(hex);
		this.add(dec);
		this.add(oct);
		this.add(bin);

		group = new ButtonGroup();
		group.add(hex);
		group.add(dec);
		group.add(oct);
		group.add(bin);
		
		hex.addActionListener((e)->
		{
			
			for(SystemListener systemListener : systemListeners){
				
                systemListener.changeSystemEvent(e);
            }
		});
		dec.addActionListener((e)->
		{
			
			for(SystemListener systemListener : systemListeners){
            	
                systemListener.changeSystemEvent(e);
            }
		});
		oct.addActionListener((e)->
		{
			
			for(SystemListener systemListener : systemListeners){
            	
                systemListener.changeSystemEvent(e);
            }
		});
		bin.addActionListener((e)->
		{
			
			for(SystemListener systemListener : systemListeners){
            	
                systemListener.changeSystemEvent(e);
            }
		});
		
	}
	
	 public void addSystemListener(SystemListener systemListener){
	        systemListeners.add(systemListener);
	    }
	
}
