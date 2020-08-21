import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class PickPolynomialColor extends AbstractCellEditor implements TableCellEditor {
	
	Color polynomialColor;
	
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return polynomialColor;
	}

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
		polynomialColor = JColorChooser.showDialog(arg0, "Kolor", Color.BLACK);
		JPanel colorPanel = new JPanel();
		colorPanel.setBackground(polynomialColor);
		return colorPanel;
	}

	
}
