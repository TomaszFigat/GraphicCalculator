

import javax.swing.*;
import java.awt.*;

public
    class Main
    extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            ()->{
                new Main();
            }
        );
    }

    public Main(){
    	
    	JPanel jp0 = new JPanel();
    	jp0.setLayout(new BoxLayout(jp0,BoxLayout.Y_AXIS));
    	jp0.add(Box.createRigidArea(new Dimension(0,3)));
    	JPanel jp = new JPanel();
    	this.setTitle("ZDZ - Soft");
    	jp.setPreferredSize(new Dimension(640,240));
    	
    	MySystemChanger mySystemChanger = new MySystemChanger();
        MyDisplay myDisplay = new MyDisplay();
        MyKeybord myKeybord = new MyKeybord();
      
        mySystemChanger.setMaximumSize(new Dimension(900,60));
        myDisplay.setMaximumSize(new Dimension(900,200));
       // myKeybord.setMinimumSize(new Dimension(640,290));
        jp.add(mySystemChanger);
        jp.add(myDisplay);
        myDisplay.setPreferredSize(new Dimension(620,200));
        myKeybord.addCalcListener(myDisplay);
        mySystemChanger.addSystemListener(myDisplay);
        mySystemChanger.addSystemListener(myKeybord);
        
       jp0.add(mySystemChanger);
       jp0.add(myDisplay);
       jp0.add(myKeybord);
        //jp.add(myDisplay, BorderLayout.PAGE_START);
        //jp.add(myKeybord, BorderLayout.CENTER);
        JTabbedPane jtp = new JTabbedPane();
        //jp0.add(mySystemChanger, BorderLayout.PAGE_START);
        //jp0.add(myDisplay, BorderLayout.CENTER);
        //jp0.add(myKeybord, BorderLayout.PAGE_END);
        jtp.addTab("Kalkulator", jp0);
        jtp.addTab("Grafika", new GraphicTab());
        this.add(jtp);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.add(jp, BorderLayout.PAGE_START);
        //this.add(myKeybord, BorderLayout.CENTER);
        //this.add(jtp);
        //this.add(md1,BorderLayout.PAGE_END);
        //setSize( 640, 480);
        pack();
       setVisible(true);
    }
}
