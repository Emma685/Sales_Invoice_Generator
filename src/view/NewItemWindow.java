package view;

import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewItemWindow extends JDialog {
    
    private JLabel jLabel1;  // to show the words "Invoice No. : "
    private JLabel itmInvNumLbl;   // to display Invoice Number of the new Item
    private JLabel jLabel2;  // to show the words "Item Name :"
    private JTextField itemNameTxt;  // to get new Item Name from user
    private JLabel jLabel3;  // to display the words "Unit Price :"
    private JTextField unitPriceTxt;  // to get unit price from user
    private JLabel jLabel4;  // to display the words "Item Count : "
    private JTextField countTxt;  // to get item count from user
    private JButton itmOkButton;
    private JButton itmCancelButton;

    public NewItemWindow(SigFrame frame) {
        super(frame,"Add New Item");
        jLabel1 = new JLabel("Invoice No. : ");
        itmInvNumLbl = new JLabel("---");
        jLabel2 = new JLabel("Item Name : ");
        itemNameTxt = new JTextField(20);
        jLabel3 = new JLabel("Unit Price");
        unitPriceTxt = new JTextField(20);
        jLabel4 = new JLabel("Item Count");
        countTxt = new JTextField(20);
        itmOkButton = new JButton("Add New Item");
        itmCancelButton = new JButton("Cancel");
        
        // set action command to be used in ActionPerformed method in Action Listener Class
        itmOkButton.setActionCommand("Dialog Add New Item");  
        itmCancelButton.setActionCommand("Dialog Cancel New Item");
        
        // assign action listener to the buttons
        itmOkButton.addActionListener(frame.getAction());
        itmCancelButton.addActionListener(frame.getAction());
        
        setLayout(new GridLayout(5, 2));
        
        // add the components in order you want it to appear on the dialog at creation
        add(jLabel1);
        add(itmInvNumLbl);
        add(jLabel2);
        add(itemNameTxt);
        add(jLabel3);
        add(unitPriceTxt);
        add(jLabel4);
        add(countTxt);
        add(itmOkButton);
        add(itmCancelButton);
        
        pack();   // make the dialog fit its components
        setLocationRelativeTo(frame);  // attache dialog to frame as its parent
                                       //and make it apear in center
        
     }  // end of Constructor
    
    

    public JLabel getItmInvNumLbl() {
        return itmInvNumLbl;
    }

    public JTextField getItemNameTxt() {
        return itemNameTxt;
    }

    public JTextField getUnitPriceTxt() {
        return unitPriceTxt;
    }

    public JTextField getCountTxt() {
        return countTxt;
    }
    
   
    
}  // end of Class NewItemWindow Dialog
