package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoiceItemsTable extends AbstractTableModel {
    
    private ArrayList<InvoiceLine> invItems;
    
    // we get a copy from the Items ArrayList of each invoice so we can display its data
    // in the Items table - we get the list in class constructor as a parameter.

    public InvoiceItemsTable(ArrayList<InvoiceLine> invItems) {
        this.invItems = invItems;
    }
    
            
    // number of Items in invItems represent the number of Rows in the table
    @Override
    public int getRowCount() 
    {
        int rowCount;
        if (invItems == null) {rowCount = 0;}
        else 
        {
            rowCount = invItems.size();
        }        
        
        return rowCount;
    }

        
    @Override
    public int getColumnCount() 
    {
        return 5;
    }

    @Override
    public String getColumnName(int columnNumber) 
    {
        String str = "";
        if (columnNumber == 0) {str = "Invoice No.";}
        else if (columnNumber == 1) {str = "Item Name";}
        else if (columnNumber == 2) {str = "Unit Price";}
        else if (columnNumber == 3) {str = "Count";}
        else if (columnNumber == 4) {str = "Item Total";}
        
        return str;  // return Column Names
    }
    
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        if (invItems == null)
         {
             switch (columnIndex)
             {
                 case 0 : return ""; 
                 case 1 : return ""; 
                 case 2 : return ""; 
                 case 3 : return ""; 
                 case 4 : return ""; 
                 default : return "";            
             }
         }
        else
        {
         InvoiceLine item = invItems.get(rowIndex);  // to get the item that should be loated in that row index
        
         // below is to add the item data in its crossponding column
         switch (columnIndex)
         {
            case 0 :
                return item.getInv_num();
            case 1 :
                return item.getItem();
            case 2 :
                return item.getPrice();
            case 3 :
                return item.getCount();
            case 4 :
                return item.getItem_total();
            default :
                return "";            
         }
        }
    }
    
      
    
    
    
    
    
    
}  // end of Class InvoiceItemTable
