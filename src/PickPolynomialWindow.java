import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class PickPolynomialWindow extends JFrame implements PolynomialListener {
	JTextField jtf;
	JButton button;
	JTable jTable;
	Boolean delt=false;
	
	ArrayList<PickPolynomialListener> pickpolynomials = new ArrayList<>();
	ArrayList<PolynomialProp> polynomials;
	public PickPolynomialWindow(ArrayList<PolynomialProp> polynomials) {
		jtf = new JTextField();
		this.polynomials = polynomials;
		
		TableModel tableModel = new AbstractTableModel() {

			@Override
			public void addTableModelListener(TableModelListener arg0) {
				
				super.addTableModelListener(arg0);
			}

			@Override
			public int getColumnCount() {
				
				return 3;
			}

			@Override
			public int getRowCount() {
				
				return polynomials.size();
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				
				if(columnIndex==1) {
					return polynomials.get(rowIndex).polynomial;
				}
				if(columnIndex==2) {
					return polynomials.get(rowIndex).color;
				}
				return polynomials.get(rowIndex).checkbox;
			}
			 @Override
	            public Class<?> getColumnClass(int columnIndex) {
	                switch(columnIndex){
	                    case 0:
	                        return Boolean.class;
	                    case 1:
	                        return String.class;
	                    case 2:
	                    	return Color.class;

	                }
	                return super.getColumnClass(columnIndex);
	            }
			 @Override
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
				 if(delt) {
					 return false;
				 }
	                if(columnIndex == 0 || columnIndex == 2){
	                    return true;
	                }
	                return super.isCellEditable(rowIndex, columnIndex);
	            }
			 @Override
	            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				 	if(columnIndex==0)
	                polynomials.get(rowIndex).checkbox= (Boolean) aValue;
				 	
//				 	if(columnIndex==2) {
//		                polynomials.get(rowIndex).color= (Color) aValue;
//	                System.out.println("uuhasc");
//	 
//				 	}
				 	
	               // super.setValueAt(aValue, rowIndex, columnIndex);
	                
	                for(PickPolynomialListener pickpolynomialListener : pickpolynomials){
		            	System.out.println("ti");
		                pickpolynomialListener.pickPolynomialEvent(new Event(aValue, columnIndex, aValue));
		            }	
				   repaint();
	              
	            }
		

//			@Override
//			public void fireTableChanged(TableModelEvent e) {
//				// TODO Auto-generated method stub
//				System.out.println(e);
//				
//				super.fireTableChanged(e);
//			}
			 
			 
			
			
		};
		
		this.jTable = new JTable(tableModel);
		button = new JButton("Delete polynomials");
		jTable.setSelectionBackground(Color.LIGHT_GRAY);
		button.addActionListener((e)->{
			this.delt=true;
			button.setVisible(false);
			JPanel delButtons = new JPanel();
			JButton b1 = new JButton("Delete Selected");
			JButton b2 = new JButton("Cancel");
			delButtons.add(b1);
			delButtons.add(b2);
			System.out.println("delete");
			this.add(delButtons,BorderLayout.PAGE_END);
			jTable.setSelectionBackground(Color.RED);
			System.out.println(polynomials.size()+ " rozmiar listy");
			b1.addActionListener((e1)->{
				ArrayList<PolynomialProp> pCopy = new ArrayList<>();
				
				for(int i=0; i < polynomials.size() ; i++) {
					pCopy.add(polynomials.get(i));
					
					if(jTable.isRowSelected(i)) {
						pCopy.get(i).polynomial="";
//						polynomials.remove(i);
				
				
			            }	
					}
				int count=0;
				this.polynomials.removeAll(polynomials);
				for( PolynomialProp x : pCopy) {
					if(x.polynomial!="")
					this.polynomials.add(x);
				}
				
				for(PickPolynomialListener pickpolynomialListener : pickpolynomials){
			            	
			                pickpolynomialListener.pickPolynomialEvent(new Event(e1, 0, e1));
				}
				delButtons.setVisible(false);
				this.delt=false;
				button.setVisible(true);
				jTable.setSelectionBackground(Color.LIGHT_GRAY);
			});
			b2.addActionListener((e2)->{
				delButtons.setVisible(false);
				this.delt=false;
				button.setVisible(true);
				jTable.setSelectionBackground(Color.LIGHT_GRAY);
			});
			System.out.println(jTable.getSelectedRowCount());
			
		});
		JScrollPane scrollPane = new JScrollPane(jTable); 
		
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS); 
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_ALWAYS); 
//		this.add(scrollPane, BorderLayout); //
//		(Add should be called for any container....FRame or JPAnel)
//		jTable.getScrollableTracksViewportHeight();
//		   jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//	        JScrollPane pane = new JScrollPane(jTable);
//	        add(pane, BorderLayout.CENTER);
        jTable.setRowHeight(50);
        jTable.setGridColor(Color.BLUE);
        jTable.setAutoscrolls(true);
        jTable.setDefaultRenderer(Color.class, new MyColorCellRenderer());
        jTable.setDefaultEditor(
                Color.class, new PickPolynomialColor()
                
        );
      //  this.setLayout(new GridLayout(2,1));
    
        this.add(button,BorderLayout.PAGE_END);
        this.add(jTable);
        this.setSize(500,400);
		this.setVisible(true);
		
	}
	@Override
	public void addPolynomialEvent(ActionEvent evt) {
		System.out.println("fadhis");
		jTable.repaint();
		
		((AbstractTableModel)jTable.getModel()).fireTableDataChanged();
		
	}
	
	 public void addPickPolynomialListener(PickPolynomialListener polynomialListener){
	     pickpolynomials.add(polynomialListener);
	 }
	
}
