package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoiceListTable extends AbstractTableModel{

    private ArrayList<InvoiceHeader> invlist;
    
    // we get a copy from the Invoices List so we can display its data
    // in the table - we get the list in class constructor as a parameter.

    public InvoiceListTable(ArrayList<InvoiceHeader> invlist) {
        this.invlist = invlist;
    }
    
    
    // number of invoices in invlist represent the number of Rows in the table
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
        
        InvoiceHeader invoice = invlist.get(rowIndex);  // to get the invoice that should be loated in that row index
        
        // below is to add the invoice data in its crossponding column
        switch (columnIndex)
        {
            case 0 : 
                return invoice.getInv_num();
            case 1 :
            {
                 String strDate= new SimpleDateFormat("dd-MM-YYYY").format(invoice.getDate());
                 return strDate;
            } 
            case 2 :
                return invoice.getCust_name();
            case 3 :
                return invoice.getTotal();
            case 4 :
            {
                if(invoice.getLast_modified() != null)
                {
                  String strLastModifed= new SimpleDateFormat("dd-MM-YYYY").format(invoice.getLast_modified());
                  return strLastModifed;
                }
                else { return "";}
            }
            default : 
                return "";
        }
        
    }
    
} // end of Class InvoiceListTable
