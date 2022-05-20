package model;

// Class to contain the data of each item in the invoice
// Multi invoice line(multi Items)can have the same invoice header 
// as they belong to the same invoice number.
public class InvoiceLine {

 private int inv_num;
 private String item;
 private double price;
 private int count;
 //private double item_total;
 private InvoiceHeader invoice;


    public InvoiceLine() {}

    public InvoiceLine(int inv_num, String item, double price, int count, InvoiceHeader invoice) {
        this.inv_num = inv_num;
        this.item = item;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
    }

   
    
    public int getInv_num() {
        return inv_num;
    }

    public String getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
    
    // below method is to calculate Item total peice
    public double getItem_total() {
        return price * count;
    }  
    

    public void setInv_num(int inv_num) {
        this.inv_num = inv_num;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

   
    @Override
    public String toString() {
        return "InvoiceLine{" + "inv_num=" + inv_num + ", item=" + item + ", price=" + price + ", count=" + count + '}';
    }

    


    
}  // end of Class InvoiceLine
