package model;

import java.util.ArrayList;
import java.util.Date;

// Class to contain the data of each invoice header
// one invoice header might have multi invoice line(multi Items)
public class InvoiceHeader {

 private int inv_num;
 private Date date;
 private String cust_name;
 //private double total;
 private Date last_modified;
 private ArrayList<InvoiceLine> items;

   
    public InvoiceHeader() {}

    public InvoiceHeader(int inv_num, Date date, String cust_name) {
        this.inv_num = inv_num;
        this.date = date;
        this.cust_name = cust_name;
    }

    
    
    public void setInv_num(int inv_num) {
        this.inv_num = inv_num;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    
    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }

    public int getInv_num() {
        return inv_num;
    }

    public Date getDate() {
        return date;
    }

    public String getCust_name() {
        return cust_name;
    }

    // below method is to calculate the Invoice total price
    public double getTotal() {
        
     double total = 0.00;
     if(items == null) {total = 0.00;}
     else
     {
         for(InvoiceLine item : items)
          {
             total += item.getItem_total();
          }  
     }
         
     return total;
     
    }  // end of method getTotal() 

    
    public Date getLast_modified() {
        return last_modified;
    }

    public ArrayList<InvoiceLine> getItems() {
     
     if (items == null)
     {items = new ArrayList<>();}
        return items;
    }

    @Override
    public String toString() {
        return "InvoiceHeader{" + "inv_num=" + inv_num + ", date=" + date + ", cust_name=" + cust_name + ", last_modified=" + last_modified + '}';
    }

     

} //end of Class InvoiceHeader 
