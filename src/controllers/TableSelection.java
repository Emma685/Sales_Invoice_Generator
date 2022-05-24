package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.InvoiceHeader;
import model.InvoiceItemsTable;
import model.InvoiceLine;
import view.SigFrame;


// Class to create the Selection Listener that will be used
// to excute actions when select a row in the Invoice List Table
public class TableSelection implements ListSelectionListener{

    private SigFrame sigFrame ;

    // constructor that Takes SigFrame as a parameter to connect Listener to the Frame
    public TableSelection(SigFrame frame) {
        this.sigFrame = frame;
    }
    
    
    
    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        int selectedIndex = sigFrame.getInvoiceTable().getSelectedRow(); // return the selected row Index in Invoice List table
        
        if (selectedIndex != -1)
        {
         // to get the selected invoice data from the Invoice List Array
         InvoiceHeader currentInv = sigFrame.getInv_list().get(selectedIndex);
        
         // to get the Items Arraylist of the selected invoice
         ArrayList<InvoiceLine> invItems = currentInv.getItems();
        
         // to display current selected invoice (currentInv) data 
         // in its crosponding Labels in the right side of the frame.
         sigFrame.getInvoiceNumber().setText(String.valueOf(currentInv.getInv_num()));
         sigFrame.getCustomerName().setText(currentInv.getCust_name());
         
         // convert date to a String with a certain date format and display it.
         String strDate = new SimpleDateFormat("dd-MM-YYYY").format(currentInv.getDate()); 
         sigFrame.getInvoiceDate().setText(strDate);
         
         sigFrame.getInvoiceTotal().setText(String.valueOf(currentInv.getTotal()));
        
         if (currentInv.getLast_modified() == null)
         {
            sigFrame.getLastModifiedDate().setText("---");
         }
         else
         {
            String strLastModifed = new SimpleDateFormat("dd-MM-YYYY").format(currentInv.getLast_modified());
             sigFrame.getLastModifiedDate().setText(strLastModifed);
         }
        
        
         // below code is to display current selected invoice items list (invItems) in the Items Table
        
         // 1st create Items Table Model using ArrayList invItems that contains all item's data
         InvoiceItemsTable itemTableModel = new InvoiceItemsTable(invItems);
         // 2nd attache the created itemTableModel with the Items Table
         sigFrame.getItemsTable().setModel(itemTableModel);
         // 3rd tell table model to update itseld with new changes
         itemTableModel.fireTableDataChanged();
        }
        else  // in Case there is no selection in the Invoice List Table (i.e selectedIndex = -1)
        {     // used to clear the items Table when deleting its invoice
            InvoiceItemsTable itemTableModel = new InvoiceItemsTable(null);
            sigFrame.getItemsTable().setModel(itemTableModel);
            itemTableModel.fireTableDataChanged();
            
            // clear Labels when the selected invoice is deleted
            sigFrame.getInvoiceNumber().setText("---");
            sigFrame.getCustomerName().setText("---");
            sigFrame.getInvoiceDate().setText("---");
            sigFrame.getInvoiceTotal().setText("---");
            sigFrame.getLastModifiedDate().setText("---");
        }
        
        
    } // end of valueChanged method
    
    
}  // end of Class TableSelection Listener
