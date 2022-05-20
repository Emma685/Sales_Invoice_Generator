package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.InvoiceHeader;
import model.InvoiceLine;
import view.SigFrame;


// Class to create the Action Listener that will be used
// to excute actions when click on the form components
public class Action implements ActionListener {
    
    private SigFrame sigFrame ;
    
    // constructore that Takes SigFrame as a parameter
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
               System.out.println("Action to be Done : " + e.getActionCommand());
               break;
               
               
           case "Exit":
               System.exit(0);
               break;
               
               
           //Button Action Cases
           case "New Invoice":
               System.out.println("Action to be Done : " + e.getActionCommand());
               break;
               
               
           case "Delete Invoice":
               System.out.println("Action to be Done : " + e.getActionCommand());
               break;
               
               
           case "Add Item":
               System.out.println("Action to be Done : " + e.getActionCommand());
               break;
               
               
           case "Delete Item":
               System.out.println("Action to be Done : " + e.getActionCommand());
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
              
                 // below is to Creat an Item objet from the InvoiceLine
                 // using above data collected from one line in the CVS file
                 InvoiceLine invItem = new InvoiceLine(invNum, itemName, itemPrice, itemCount, itemInv);
                 // below is to add each invoice item(invItem) inside its
                 // invoice(itemInv)
                 itemInv.getItems().add(invItem);
          
             } // end of 2nd for-Loop
          }  // end of inner If statment
         
         // below is to pass the InoviceHeader arrayList that now contains all data 
         // that was on CVS files to SigFrame to display it and use it across the pogram.
         sigFrame.setInv_list(invList); 

         }  // end of outter If Statment
     } // end of Try Block 
     catch(FileNotFoundException e){e.printStackTrace();}
     catch(ParseException e){e.printStackTrace();} 
     catch(IOException e){e.printStackTrace();}
    
      
     }  // end of openfile Method
      
    
    
    
    
    
    
    
 }   // end of Class Action
    
    
    
    
    
    
    
    
    
    

