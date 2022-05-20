
package model;

// Test Class Only - Not used within the Program
// created to Test Reading and Writing the CVS Files

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;



public class FileOperations {
    
   
    public ArrayList<InvoiceHeader> readFile()
    {
        ArrayList<InvoiceHeader> invList = null;
                
        JFileChooser fc = new JFileChooser();
          
     try { // read the InvoiceHeader CVS file and add its lines to invLines arraylist
       if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION )
       { 
         String invoicePath = fc.getSelectedFile().getPath();
         FileReader fr = new FileReader(invoicePath);
         BufferedReader br = new BufferedReader(fr);
         ArrayList<String> invLines = new ArrayList<>();
         while (br.ready())
          { invLines.add(br.readLine()); }  // File read Successful
         
         System.out.println("InvoiceHeader CSV File read Successful");
         
         invList = new ArrayList<>();
         
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
         
         
         if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION )
         {
             String itemPath = fc.getSelectedFile().getPath();
             FileReader fr1 = new FileReader(itemPath);
             BufferedReader br1 = new BufferedReader(fr1);
             ArrayList<String> itemLines = new ArrayList<>();
             while (br1.ready())
             { itemLines.add(br1.readLine()); }  // File read Successful
             
             System.out.println("InvoiceLine CSV File read Successful");
             
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
                 
                 InvoiceLine invItem = new InvoiceLine(invNum, itemName, itemPrice, itemCount, itemInv);
                 // below is to add each invoice item(invItem) inside its
                 // invoice(itemInv)
                 itemInv.getItems().add(invItem);
             }    
             
         }  // end of inner If Statment
     
       }  // end of Outter If Satment
       
     } // end of Try Block
     catch(FileNotFoundException e){e.printStackTrace();}
     catch(ParseException e){e.printStackTrace();} 
     catch(IOException e){e.printStackTrace();}
     
     return invList;
     }  
   
    public void writeFile(ArrayList<InvoiceHeader> invList)
    {
      for(InvoiceHeader inv : invList)
      {
          int invNum = inv.getInv_num();
          String date = inv.getDate().toString();
          String custName = inv.getCust_name();
          System.out.println("\n Invice No. " + invNum + "\n { \n Invoice Date: "
          + date + "\n Customer Name: " + custName);
          ArrayList<InvoiceLine> items = inv.getItems();
          for(InvoiceLine item : items)
          {
              System.out.println("Item: " + item.getItem() + " , " 
                + item.getPrice() + " , " + item.getCount());
          }
          
          System.out.println(" } \n \n ---------------------------- \n");
      }
        
        
    }
    
    
    
    
    
    
   // Test main method for this class only 
   public static void main(String[] args)
   {
       FileOperations file = new FileOperations();
       ArrayList<InvoiceHeader> invoices = file.readFile();
       file.writeFile(invoices);
              
   }
    
    
    
} // end of FileOperations Class
    
