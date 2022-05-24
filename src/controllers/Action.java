package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.InvoiceHeader;
import model.InvoiceItemsTable;
import model.InvoiceLine;
import model.InvoiceListTable;
import view.NewInvoiceWindow;
import view.NewItemWindow;
import view.SigFrame;


// Class to create the Action Listener that will be used
// to excute actions when click on the form components
public class Action implements ActionListener {
    
    private SigFrame sigFrame ;
    private NewInvoiceWindow newInvWin;
    private NewItemWindow newItmWin;
    
    // constructor that Takes SigFrame as a parameter
    public Action(SigFrame frame) {
        this.sigFrame = frame;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
       switch (e.getActionCommand()){
           
           //File Menu Action Cases
           case "Open":
               openfile();
               break;
           
               
           case "Save":
               saveFile();
               break;
               
               
           case "Exit":
               System.exit(0);
               break;
               
               
           //Button Action Cases
           case "New Invoice":
               newInvoice();  // to open Create New Invoie Dialog
               break;
               
               
           case "Delete Invoice":
               deleteInvoice();
               break;
               
               
           case "Add Item":
               addItem();  // to Open Add New Item Dialog
               break;
               
               
           case "Delete Item":
               deleteItem();
               break;
              
               
           //NewInvoiceWindow Dialog Buttons Action Cases
           case "Dialog Create New Invoice":
               dialogCreateNewInv();
               break;
               
               
           case "Dialog Cancel New Invoice":
               newInvWin.setVisible(false);  // make Dialog Disappeare
               newInvWin.dispose();  // remove Dialog from Memory
               newInvWin = null; 
               break;
               
               
           //NewItemWindow Dialog Buttons Action Cases    
           case "Dialog Add New Item":
               dialogAddNewItem();
               break;
               
               
           case "Dialog Cancel New Item":
               newItmWin.setVisible(false);
               newItmWin.dispose();
               newItmWin = null;
               break;
               
    
         } // End Of Switch
               
        
        
    }  // end Of action Performed Method

    
    private void openfile() { 
           
     JOptionPane.showMessageDialog(sigFrame, 
        "Open InvoiceHeader CSV File \n to Display in the Invoice List Table");
     JFileChooser fc = new JFileChooser();
          
     try { // read the InvoiceHeader CVS file and add its lines to invLines arraylist
       if(fc.showOpenDialog(sigFrame) == JFileChooser.APPROVE_OPTION )
       { 
         String invoicePath = fc.getSelectedFile().getPath();
         File invFile = new File(invoicePath);
                 
         if ("InvoiceHeader.csv".equals(invFile.getName()))
         {
             FileReader fr = new FileReader(invoicePath);
             BufferedReader br = new BufferedReader(fr);
             ArrayList<String> invLines = new ArrayList<>();
             while (br.ready())
             { invLines.add(br.readLine()); }  // File read Successful
         
             // below is to create an arraylist of type InvoiceHeader To collect
             // all data in its fields and use it Later across the program.    
             ArrayList<InvoiceHeader> invList = new ArrayList<>();
         
             // below Code to Split each line into the invoiceHeader object members
             // and create the InvoiceHeader objects then save it into an array 
             //of invoiceHeader type.
                 
             for(int i=0 ; i< invLines.size() ; i++)
             {
                 String[] invParts = invLines.get(i).split(",");
                 int invNum = Integer.parseInt(invParts[0]);
                 Date invDate = new SimpleDateFormat("dd-MM-yyyy").parse(invParts[1]);
                 String custName = invParts[2];
                 
                 // below is to Creat an Invoice objet from the InvoiceHeader
                 // using above data collected from one line in the CVS file
                 InvoiceHeader inv = new InvoiceHeader(invNum, invDate, custName);  
                 invList.add(inv);  // add the invoice to the invList Array
          
             }  // end of 1st for-Loop
                  
             // below code to open the InvoiceLine CVS file and add its lines to an arraylist
             JOptionPane.showMessageDialog(sigFrame, 
             "Open LineHeader CSV File \n to Display in the Invoice Item Table");
             if (fc.showOpenDialog(sigFrame) == JFileChooser.APPROVE_OPTION )
             {
                 String itemPath = fc.getSelectedFile().getPath();
                 File itemFile = new File(itemPath);
                 if ("InvoiceLine.csv".equals(itemFile.getName()))
                 {
                     FileReader fr1 = new FileReader(itemPath);
                     BufferedReader br1 = new BufferedReader(fr1);
                     ArrayList<String> itemLines = new ArrayList<>();
                     while (br1.ready())
                     { itemLines.add(br1.readLine()); }  // File read Successful
                 
                     // below Code to Split each line into the invoiceLine object members
                     // and create InvoiceLine objects then save it into an array 
                     //of invoiceLine type.
                     for(int i=0 ; i< itemLines.size() ; i++)
                     {
                       String[] itemParts = itemLines.get(i).split(",");
                       int invNum = Integer.parseInt(itemParts[0]);
                       String itemName = itemParts[1];
                       double itemPrice = Double.parseDouble(itemParts[2]);
                       int itemCount = Integer.parseInt(itemParts[3]);
                       InvoiceHeader itemInv = null;
                       
                       // below code is to get the invoice associated with that 
                       // invoice number, so we can add each item to its invoice.
                       for(InvoiceHeader inv : invList)
                       {
                         if(inv.getInv_num() == invNum)
                         {
                             itemInv = inv;
                             break;
                         }
                       }               
                        
                       // below is to Create an Item objet from the InvoiceLine
                       // using above data collected from one line in the CVS file
                       InvoiceLine invItem = new InvoiceLine(invNum, itemName, itemPrice, itemCount, itemInv);
                       // below is to add each invoice item(invItem) inside its
                       // invoice(itemInv)
                       itemInv.getItems().add(invItem);
                       
                     } // end of 2nd for-Loop
                  
                  // below is to pass the InoviceHeader arrayList that now contains all data 
                 // that was on CVS files to SigFrame to display it and use it across the pogram.
                 sigFrame.setInv_list(invList); 
             
                 // below is to pass the InoviceHeader arrayList that now contains all data 
                 // to the InvoiceListTable model to use in bulding the Invoice List Table
                 // and display the invoices values in it.
                 InvoiceListTable invListTableModel = new InvoiceListTable(invList);
                 sigFrame.setInvTableModel(invListTableModel);  // to connect the frame and invoice list table model 
                                                               //and make sure that invTableModel variable in the Frame is not Null
                 sigFrame.getInvoiceTable().setModel(invListTableModel);  // connect InvoiceList Table with the table model
                 sigFrame.getInvTableModel().fireTableDataChanged();  // to tell table model to update itseld with new changes
                 
                 
                              
                 }
                 else
                 {
                     JOptionPane.showMessageDialog(sigFrame, 
                     "Wrong File \n Please select file InvoiceLine.csv","Warning",JOptionPane.WARNING_MESSAGE); 
                 }
                 
             }  // end of inner If statment
             
          }
          else
          {
              JOptionPane.showMessageDialog(sigFrame, 
             "Wrong File \n Please select file InvoiceHeader.csv","Warning",JOptionPane.WARNING_MESSAGE);   
          }
         
       }  // end of outter If Statment
     } // end of Try Block 
     catch(FileNotFoundException e){e.printStackTrace();}
     catch(ParseException e){e.printStackTrace();} 
     catch(IOException e){e.printStackTrace();}
    
      
     }  // end of openfile Method

    
    private void deleteInvoice() {
        
     if(sigFrame.getInv_list() == null)
     {
         JOptionPane.showMessageDialog(sigFrame, 
          "Please Open InvoiceHeader and LineHeader CSV Files First", "Warning" ,JOptionPane.WARNING_MESSAGE); 
     }
     else
     {
         // to get seleted row index wich represent the invoice index in The invoices ArrayList
         int rowIndex = sigFrame.getInvoiceTable().getSelectedRow();
         if (rowIndex != -1) // -1 means No selection
         {
             sigFrame.getInv_list().remove(rowIndex);  // Delete selected invoice from the invoices ArrayList
         }
         else
         {
             JOptionPane.showMessageDialog(sigFrame, 
              "NO Invoice Selected \n Please select an Invoice and try again","Warning",JOptionPane.WARNING_MESSAGE);
         }
         
         sigFrame.getInvTableModel().fireTableDataChanged();  // to refresh and update Invoice List table changes
     
     }
    } // end of deleteInvoice Method

    
    private void deleteItem() {
        
     if(sigFrame.getInv_list() == null)
     {
         JOptionPane.showMessageDialog(sigFrame, 
          "Please Open InvoiceHeader and LineHeader CSV Files First", "Warning" ,JOptionPane.WARNING_MESSAGE); 
     }
     else
     {
         // selectedInvIndex represents the invoice index in invoices ArrayList
         //note: invoice Index and selected row index are the same
         int selectedInvIndex = sigFrame.getInvoiceTable().getSelectedRow();
        
         // selectedItemIndex represent the Item index in Invoie Items ArrayList
         int selectedItemIndex = sigFrame.getItemsTable().getSelectedRow();
         
         if (selectedInvIndex != -1  &&  selectedItemIndex != -1 )
         {
             // to get the invoice which contain the selected item
             InvoiceHeader selectedInv = sigFrame.getInv_list().get(selectedInvIndex);
         
             // to delete the selected itemfrom the items ArrayList in the selected Invoice
             selectedInv.getItems().remove(selectedItemIndex);
             
             // below code is to update and refresh the Items Table after changes
             InvoiceItemsTable itemsTableModel = new InvoiceItemsTable(selectedInv.getItems());
             sigFrame.getItemsTable().setModel(itemsTableModel);
             itemsTableModel.fireTableDataChanged();
         
             // below is to update and refresh Invoie List Table and Last Modified Label in right side 
             // as last Modified Date and Invoice Total Got Changed
             selectedInv.setLast_modified(new Date()); // add today's date as Last Modified date of selected invoice
         
             sigFrame.getInvTableModel().fireTableDataChanged();
             sigFrame.getInvoiceTable().setRowSelectionInterval(selectedInvIndex, selectedInvIndex);
        
             // case all Invoive Items Got Deleted and the invoive items List is now empty
             if (selectedInv.getItems().isEmpty())
             {
                 JOptionPane.showMessageDialog(sigFrame, 
                  ("Invoice No. " + selectedInv.getInv_num()+ 
                    " is now empty and has no items \n If you want to remove it, Please Click on Delete invoice \n If you want to add new item, Please Click on Add Item.")
                    ,"Warning",JOptionPane.WARNING_MESSAGE);
             }
         
         } // end of Outter If
         else
         {
             JOptionPane.showMessageDialog(sigFrame, 
              "NO Item Selected \n Please select an Item and try again","Warning",JOptionPane.WARNING_MESSAGE);  
         }
        
     } 
     
    }  // end of deleteItem Method

    
    private void newInvoice()  {  
        
        if(sigFrame.getInv_list() == null)
               {
                 JOptionPane.showMessageDialog(sigFrame, 
                  "Please Open InvoiceHeader and LineHeader CSV Files First", "Warning" ,JOptionPane.WARNING_MESSAGE); 
               }
               else
               {
                 newInvWin = new NewInvoiceWindow(sigFrame);
                 newInvWin.setVisible(true);
                 
                 // below code is to Auto-generate new Invoice Number and Invoice Date
                 int lastInvIndex = (sigFrame.getInv_list().size()) - 1;
                 int lastInvNumber = sigFrame.getInv_list().get(lastInvIndex).getInv_num();
                 int newInvNumber = lastInvNumber + 1;
                 
                 Date date = new Date();  // to get today's Date
                 String strDate = new SimpleDateFormat("dd-MM-YYYY").format(date);
                 
                 // to show new Invoice Number and Date in the dialog corrosponding labels
                 newInvWin.getNewInvNumLbl().setText(String.valueOf(newInvNumber));
                 newInvWin.getNewInvDateLbl().setText(strDate);
               
               }
        
    }  // end of newInvoice Method
            
    
    private void dialogCreateNewInv() {
        
        try {
         // below to read info from Dialog Components 
         int newInvNum = Integer.parseInt(newInvWin.getNewInvNumLbl().getText());
         String strDate = newInvWin.getNewInvDateLbl().getText();
         Date invDate = new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
         String custName = newInvWin.getCustNameTxt().getText();
         
         if ("".equals(custName))
         {
             JOptionPane.showMessageDialog(sigFrame, 
              "Please Enter Customer Name","Warning",JOptionPane.WARNING_MESSAGE); 
         }
         else
         {
             // use info to create a new invoice
             InvoiceHeader newInv = new InvoiceHeader(newInvNum, invDate, custName);
         
             sigFrame.getInv_list().add(newInv);  // add new invoice to Invoices ArrayList
             sigFrame.getInvTableModel().fireTableDataChanged(); // update Invoice List Table
         
             // below is to close the dialog and make it disappear
             newInvWin.setVisible(false);  // make Dialog Disappeare
             newInvWin.dispose();  // remove Dialog from Memory
             newInvWin = null; 
         
             // to select the new invoice in the Invoice List Table
             int newInvIndex = sigFrame.getInv_list().size() - 1;
             sigFrame.getInvoiceTable().setRowSelectionInterval(newInvIndex, newInvIndex);
         }
         
        }  // end of Try Block
        catch(ParseException e){e.printStackTrace();} 
     
        
    }  // end of dialogCreateNewInv Method

    
    private void addItem() {
        
        if(sigFrame.getInv_list() == null)
        {
         JOptionPane.showMessageDialog(sigFrame, 
         "Please Open InvoiceHeader and LineHeader CSV Files First", "Warning" ,JOptionPane.WARNING_MESSAGE); 
        }
        else
        {
         // selectedInvIndex represents the invoice index in invoices ArrayList
         //note: invoice Index and selected row index are the same
         int selectedInvIndex = sigFrame.getInvoiceTable().getSelectedRow();
         
          if (selectedInvIndex == -1)
          {
             JOptionPane.showMessageDialog(sigFrame, 
             "NO Invoice Selected \n Please select an Invoice and try again","Warning",JOptionPane.WARNING_MESSAGE);
          }
          else
          {
             // to get the invoice you want to add new item to it
             InvoiceHeader selectedInv = sigFrame.getInv_list().get(selectedInvIndex);
             String selectedInvNum = String.valueOf(selectedInv.getInv_num());
             
             newItmWin = new NewItemWindow(sigFrame);
             newItmWin.setVisible(true);
             
             // Automatically add selected invoice number to the Add Item Dialog
             newItmWin.getItmInvNumLbl().setText(selectedInvNum); 
             
          } // end of inner else
         
        } // end of outter else
        
        
    }  // end of addItem Method

    
    private void dialogAddNewItem() {
        
     // to get the invoice you want to add new item to it
     int selectedInvIndex = sigFrame.getInvoiceTable().getSelectedRow();
     InvoiceHeader selectedInv = sigFrame.getInv_list().get(selectedInvIndex);
     int selectedInvNum = selectedInv.getInv_num();
        
     // below to read info from Dialog Components
     String itemName = newItmWin.getItemNameTxt().getText();
     String strUnitPrice = newItmWin.getUnitPriceTxt().getText();
     String strCount = newItmWin.getCountTxt().getText();
     
     if ("".equals(itemName) || "".equals(strUnitPrice) || "".equals(strCount))
     {
         JOptionPane.showMessageDialog(sigFrame, 
          "Please Complete all Item Data","Warning",JOptionPane.WARNING_MESSAGE); 
     }
     else
     {
         double unitPrice = Double.parseDouble(strUnitPrice);
         int count = Integer.parseInt(strCount);
         
         // use info to create a new item and add it to its invoice
         InvoiceLine item = new InvoiceLine(selectedInvNum, itemName, unitPrice, count, selectedInv);
         selectedInv.getItems().add(item);
         
         selectedInv.setLast_modified(new Date()); // add today's date as Last Modified date of selected invoice
         
         // below code is to update and refresh InvoiceList Table and Items Table after changes
         sigFrame.getInvTableModel().fireTableDataChanged();
         sigFrame.getInvoiceTable().setRowSelectionInterval(selectedInvIndex, selectedInvIndex);
         
         // below is to close the dialog and make it disappear
         newItmWin.setVisible(false);
         newItmWin.dispose();
         newItmWin = null;
         
     }
         
         
    }  // end of dialogAddNewItem Method

    
    private void saveFile() {
        
        ArrayList<InvoiceHeader> invoices = sigFrame.getInv_list();
        String invHeader = "";
        String invLine = "";
        
        for(InvoiceHeader inv : invoices)
        {
            String strInvNum = String.valueOf(inv.getInv_num());
            String strDate = new SimpleDateFormat("dd-MM-YYYY").format(inv.getDate());
            
            String strH = strInvNum + "," + strDate + "," + inv.getCust_name() + "\n";
            
            invHeader += strH;
            
            for(InvoiceLine item : inv.getItems())
            {
                String strItmInvNum = String.valueOf(item.getInv_num());
                String itemName = item.getItem();
                String strPrice = String.valueOf(item.getPrice());
                String strCount = String.valueOf(item.getCount());
                
                String strL = strItmInvNum + "," + itemName + "," + strPrice + "," + strCount + "\n";
                
                invLine += strL;
            }
         } // end of outter for Loop
        
        JFileChooser fc = new JFileChooser();
        
        JOptionPane.showMessageDialog(sigFrame,"1st Save InvoiceHeader.csv File");
        
        try {
         if(fc.showSaveDialog(sigFrame) == JFileChooser.APPROVE_OPTION )
         {
             String headerPath = fc.getSelectedFile().getPath();
             File headerFile = new File(headerPath);
             
             if (!headerFile.exists())
             {  headerFile.createNewFile(); }
             
             FileWriter hfw = new FileWriter(headerFile);
             hfw.write(invHeader);
             hfw.flush();
             hfw.close();
             
             System.out.print(invHeader + "\n -------------- \n");
             
             JOptionPane.showMessageDialog(sigFrame,"2nd Save InvoiceLine.csv File");
             if(fc.showSaveDialog(sigFrame) == JFileChooser.APPROVE_OPTION )
             {
                 String linePath = fc.getSelectedFile().getPath();
                 File lineFile = new File(linePath);
             
                 if (!lineFile.exists())
                 {  lineFile.createNewFile(); }
             
                 FileWriter lfw = new FileWriter(lineFile);
                 lfw.write(invLine);
                 lfw.flush();
                 lfw.close();
                 System.out.print(invLine + "\n -------------- \n");
             }
             
         }
         
        }  // end of Try Block
        catch(IOException e){e.printStackTrace();}
        
    
    }  // end of saveFile Method

    
    
    
    
    
    
    
    
 }   // end of Class Action
    
    
    
    
    
    
    
    
    
    

