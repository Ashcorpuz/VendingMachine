import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegularVendingMachineView extends VendingMachineView{
    protected JPanel mainPanel, warnPanel;
    protected JLabel mainLabel, warnLabel;
    protected JPanel regularPanel;
    
    public RegularVendingMachineView(){
        super();  
        this.regularPanel =  new JPanel(new FlowLayout(FlowLayout.CENTER));
        regularPanel.setLayout(new GridLayout(0,1));
    }
    
    @Override
    public void chooseItems(JFrame mainFrame, List<Item> itemList, List<Item> vmItem, int slot, int capacity){
        chooseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 3, 10, 10));
        itemNo = 0;
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel itemNoLabel = new JLabel("Vending Machine Item: ");
        mainPanel.add(itemNoLabel);
        mainPanel.setPreferredSize(new Dimension(220, 30));
        JLabel itemValLabel = new JLabel(String.valueOf(itemNo));
        mainPanel.add(itemValLabel);
        mainPanel.setPreferredSize(new Dimension(100, 30));

        for(Item item: itemList){
            
            JButton itemButton = new JButton(item.getItemName());
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog itemDialog = new JDialog(chooseDialog, item.getItemName());
                    itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    
                    JPanel panel = new JPanel(new GridLayout(4, 2));

                    JLabel calories = new JLabel("Calories");
                    panel.add(calories);
                    panel.setPreferredSize(new Dimension(100, 30));
                    JLabel calValue = new JLabel(String.valueOf(item.getCalories()));
                    panel.add(calValue);
                    panel.setPreferredSize(new Dimension(100, 30));
                    
                    JLabel price = new JLabel("Price");
                    panel.add(price);
                    panel.setPreferredSize(new Dimension(100, 30));
                    JLabel priceValue = new JLabel(String.valueOf(item.getPrice()));
                    panel.add(priceValue);
                    panel.setPreferredSize(new Dimension(100, 30));

                    JLabel quantityLabel = new JLabel("Quantity");
                    panel.add(quantityLabel);
                    panel.setPreferredSize(new Dimension(100, 30));
                    JLabel quaValue = new JLabel(String.valueOf(item.getQuantity()));
                    panel.add(quaValue);
                    panel.setPreferredSize(new Dimension(100, 30));

                    JButton addToCartButton = new JButton("Add");
                    addToCartButton.setPreferredSize(new Dimension(150, 30));
                    addToCartButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int temp = 0;
                            for(int j=0; j<vmItem.size(); j++){
                                if(item.getItemName().equals(vmItem.get(j).getItemName()))
                                temp = 1;
                            }
                            if(temp==0 ){
                                if(itemNo<slot){
                                    itemNo++;
                                    vmItem.add(item);
                                    itemButton.setEnabled(false);
                                    itemValLabel.setText(String.valueOf(itemNo));
                                    itemDialog.dispose();
                                }
                                else{
                                    String text = "Slot is already full";
                                    displayWarning(text);
                                    itemDialog.dispose();
                                }
                            }
                        }
                    });
                    panel.add(addToCartButton);
                    panel.setPreferredSize(new Dimension(220,30));

                    itemDialog.setContentPane(panel);
                    itemDialog.setPreferredSize(new Dimension(250, 150));
                    itemDialog.pack();
                    itemDialog.setLocationRelativeTo(chooseDialog);
                    itemDialog.setVisible(true);
                }
            });
            buttonPanel.add(itemButton);
        }
        mainPanel.add(buttonPanel);
        mainPanel.add(proceed);
        mainPanel.setPreferredSize(new Dimension(400, 100));
        chooseDialog.setContentPane(mainPanel);
        chooseDialog.setPreferredSize(new Dimension(700, 500));
        chooseDialog.pack();
        chooseDialog.setLocationRelativeTo(chooseDialog);
        chooseDialog.setVisible(true);
    }

    @Override
    public void createVendingMachine(JFrame mainFrame) {
        String text;
        mainFrame.getContentPane().removeAll();
        nameField.setEnabled(true);
        slotField.setEnabled(true);
        capacityField.setEnabled(true);
        nameField.setText("");
        slotField.setText("");
        capacityField.setText("");
        create.setEnabled(true);
        cancel.setEnabled(true);
        regularPanel.setLayout(new GridLayout(0,1));
        JLabel regularLabel = new JLabel("Creating REGULAR Vending Machine");
        regularPanel.add(regularLabel);
        mainFrame.add(regularPanel);

        JLabel nameLabel = new JLabel("Enter Name");
        regularPanel.add(nameLabel);
        regularPanel.add(nameField);
        mainFrame.add(regularPanel);

        JLabel slotLabel = new JLabel("Enter Slot");
        regularPanel.add(slotLabel);
        regularPanel.add(slotField);
        mainFrame.add(regularPanel);

        JLabel maxLabel = new JLabel("Enter Capacity");
        regularPanel.add(maxLabel);
        regularPanel.add(capacityField);

        regularPanel.add(create);
        regularPanel.add(cancel);
        
        regularPanel.setPreferredSize(new Dimension(300, 300));
        mainFrame.add(regularPanel);
        mainFrame.revalidate();
        mainFrame.repaint();

    }
    @Override
    public void vendingMachineFeatures(List<Item> itemSlots, JFrame mainFrame) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(0, 3, 10, 10));

        JLabel vfLabel = new JLabel("Vending Machine");
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(vfLabel);
        panel.setPreferredSize(new Dimension(220, 30));
        mainFrame.add(panel);

        JLabel productsLabel = new JLabel("PRODUCTS FOR SALE");
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(productsLabel);
        panel.setPreferredSize(new Dimension(220, 30));
        mainFrame.add(panel); 

        for(Item items : itemSlots){
            JButton itemButton = new JButton(items.getItemName());
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    JDialog itemDialog = new JDialog(mainFrame, items.getItemName());
                    itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    
                    JPanel panel = new JPanel(new GridLayout(4, 2));

                    JLabel itemDialogLabel = new JLabel("Calories");
                    panel.add(itemDialogLabel);
                    panel.setPreferredSize(new Dimension(100, 30));
                    itemDialogLabel = new JLabel(String.valueOf(items.getCalories()));
                    panel.add(itemDialogLabel);
                    panel.setPreferredSize(new Dimension(100, 30));
                    
                    itemDialogLabel = new JLabel("Price");
                    panel.add(itemDialogLabel);
                    panel.setPreferredSize(new Dimension(100, 30));
                    itemDialogLabel = new JLabel(String.valueOf(items.getPrice()));
                    panel.add(itemDialogLabel);
                    panel.setPreferredSize(new Dimension(100, 30));

                    itemDialogLabel = new JLabel("Quantity");
                    panel.add(itemDialogLabel);
                    panel.setPreferredSize(new Dimension(100, 30));
                    itemDialogLabel = new JLabel(String.valueOf(items.getQuantity()));
                    panel.add(itemDialogLabel);
                    panel.setPreferredSize(new Dimension(100, 30));

                    itemDialogLabel = new JLabel("Enter Quantity");
                    JTextField quantityField = new JTextField(15);
                    panel.add(itemDialogLabel);
                    panel.add(quantityField);
                    panel.setPreferredSize(new Dimension(220, 30));
                    
                    panel.add(addToCart);
                    panel.setPreferredSize(new Dimension(220,30));

                    itemDialog.setContentPane(panel);
                    itemDialog.setPreferredSize(new Dimension(500, 300));
                    itemDialog.pack();
                    itemDialog.setLocationRelativeTo(mainFrame);;
                    itemDialog.setVisible(true);
                }
            });
            itemsPanel.add(itemButton);
        }
        mainFrame.add(itemsPanel);
        
        mainFrame.add(checkOut);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    
}
