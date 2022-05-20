package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoiceListTable extends AbstractTableModel{

    private ArrayList<InvoiceHeader> invlist;
    
    // we get a copy from the Invoices List so we can display its data
    // in the table - we get te list in class constructor as a parameter.

    public InvoiceListTable(ArrayList<InvoiceHeader> invlist) {
        this.invlist = invlist;
    }
    
    
    
    @Override
    public int getRowCount() {
    
       return invlist.size();
    }

    @Override
    public int getColumnCount() {
       
        return 5;   // return number of colums to show on Table
    }

    @Override
    public String getColumnName(int columnNumber) {
        String str = "";
        if (columnNumber == 0) {str = "Invoice No.";}
        else if (columnNumber == 1) {str = "Date";}
        else if (columnNumber == 2) {str = "Customer Name";}
        else if (columnNumber == 3) {str = "Total";}
        else if (columnNumber == 4) {str = "Last Modified Date";}
        
        return str;  // return Column Names
    }

    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
