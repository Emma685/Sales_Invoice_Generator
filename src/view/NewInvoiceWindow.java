package view;

import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewInvoiceWindow extends JDialog{
    
    private JLabel jLabel1;     // to show the words "Invoice No. : "
    private JLabel newInvNumLbl;   // to display Auto-generated New Invoice Number
    private JLabel jLabel2;     // to show the words " Date : "
    private JLabel newInvDateLbl;  // to display Auto-generated new invoice date with today's date
    private JLabel jLabel3;     // to show the words "Customer Name : "
    private JTextField custNameTxt;  // to get customer name from user
    private JButton okButton;
    private JButton cancelButton;

    public NewInvoiceWindow(SigFrame frame) {
        super(frame,"Create New Invoice");
        
        jLabel1 = new JLabel("Invoice No. : ");
        newInvNumLbl = new JLabel("---");
        jLabel2 = new JLabel("Date : ");
        newInvDateLbl = new JLabel("---");
        jLabel3 = new JLabel("Customer Name : ");
        custNameTxt = new JTextField(20);
        okButton = new JButton("Create New Invoice");
        cancelButton = new JButton("Cancel");
        
        // set action command to be used in ActionPerformed method in Action Listener Class
        okButton.setActionCommand("Dialog Create New Invoice");  
        cancelButton.setActionCommand("Dialog Cancel New Invoice");
        
        // assign action listener to the buttons
        okButton.addActionListener(frame.getAction());
        cancelButton.addActionListener(frame.getAction());
        
        setLayout(new GridLayout(4,2)); 
        
        // add the components in order you want it to appear on the dialog at creation
        add(jLabel1);
        add(newInvNumLbl);
        add(jLabel2);
        add(newInvDateLbl);
        add(jLabel3);
        add(custNameTxt);
        add(okButton);
        add(cancelButton);
        
        pack();  // make the dialog fit its components
        setLocationRelativeTo(frame);  // attache dialog to frame as its parent
                                       //and make it apear in center
                                       
              
        
    }  // end of Constructor

    
    
    public JLabel getNewInvNumLbl() {
        return newInvNumLbl;
    }

    public JLabel getNewInvDateLbl() {
        return newInvDateLbl;
    }

    public JTextField getCustNameTxt() {
        return custNameTxt;
    }
    
     
    
}  // end of Class NewInvoiceWindow Dialog
