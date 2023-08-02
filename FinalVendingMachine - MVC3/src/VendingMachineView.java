import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

abstract class VendingMachineView {
    protected JButton create, cancel, createS, addToCart, checkOut, cancelCheckOut, proceed, pickFlavor, pickChoc, enterAmount;
    protected JTextField nameField, slotField, capacityField, enterAmountField;
    protected JDialog itemClickedDialog, mainDialog, chooseDialog, warningDialog, forSaleDialog, customizeDialog;
    protected JPanel itemClickedPanel, itemsPanel, specialPanel, mainPanel;
    protected JLabel mainLabel;
    protected Item itemClicked;
    protected SpecialItem pickedIceCream;
    protected List<Item> itemSlots;
    protected int itemNo;

    public VendingMachineView(){
        itemSlots = new ArrayList<>();
        itemClickedDialog = new JDialog();
        this.cancelCheckOut = new JButton("Cancel");
        cancelCheckOut.setPreferredSize(new Dimension(220, 30));
        this.enterAmount = new JButton("Enter Amount");
        enterAmount.setPreferredSize(new Dimension(220, 30));
        
        this.create = new JButton("Create");
        this.cancel = new JButton("Cancel");
        create.setPreferredSize(new Dimension(220, 30));
        cancel.setPreferredSize(new Dimension(220, 30));
        this.createS = new JButton("Create");
        createS.setPreferredSize(new Dimension(220, 30));
        this.pickChoc = new JButton("Choose Chocolates");
        pickChoc.setPreferredSize(new Dimension(220, 30));
        this.pickFlavor = new JButton("Choose Flavors");
        pickFlavor.setPreferredSize(new Dimension(220, 30));
        addToCart = new JButton("Add to cart");
        addToCart.setPreferredSize(new Dimension(220, 30));
        checkOut = new JButton("Check Out");
        checkOut.setPreferredSize(new Dimension(220, 30));
        this.proceed = new JButton("Proceed");
        proceed.setPreferredSize(new Dimension(220, 30));
        this.itemsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        itemsPanel.setLayout(new GridLayout(0, 3, 10, 10));
        this.nameField = new JTextField(15);
        this.slotField = new JTextField(15);
        this.enterAmountField = new JTextField(15);
        this.capacityField = new JTextField(15);
        this.chooseDialog = new JDialog();
        chooseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);;
        chooseDialog.setPreferredSize(new Dimension(700, 500));
        this.customizeDialog = new JDialog();
        customizeDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        customizeDialog.setPreferredSize(new Dimension(700, 500));
        this.forSaleDialog = new JDialog();
        forSaleDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        forSaleDialog.setPreferredSize(new Dimension(700, 500));
        this.specialPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        specialPanel.setLayout(new GridLayout(0,1));

        warningDialog = new JDialog();
    }

    public abstract void createVendingMachine(JFrame mainFrame);
    public abstract void vendingMachineFeatures(List<Item> itemSlot, JFrame mainFrame);
    public abstract void chooseItems(JFrame mainFrame, List<Item> itemList, List<Item> itemSlots, int slot, int capacity);

    public void checkingOut(JFrame mainFrame, double calories, double price){
        mainFrame.getContentPane().removeAll();
        JPanel calcuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        calcuPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JLabel calcuLabel = new JLabel();
        calcuLabel = new JLabel("Total Amount of Calorie");
        calcuPanel.add(calcuLabel);

        calcuLabel = new JLabel(String.valueOf(calories));
        calcuPanel.add(calcuLabel);

        calcuLabel = new JLabel("Total Amount");
        calcuPanel.add(calcuLabel);

        calcuLabel = new JLabel(String.valueOf(price));
        calcuPanel.add(calcuLabel);
        calcuPanel.setPreferredSize(new Dimension(220, 100));

        /*JButton payButton = new JButton("PAY");
        JButton cancelButton = new JButton("CANCEL"); */

        calcuPanel.add(checkOut);
        calcuPanel.add(cancelCheckOut);
        mainFrame.add(calcuPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void chooseItem(List<Item> itemList, List<Item> pickedItems, int limit){
        itemNo =  0;
        forSaleDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 3, 10, 10));
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel productsLabel = new JLabel("PRODUCTS FOR SALE");
        mainPanel.add(productsLabel);
        mainPanel.setPreferredSize(new Dimension(220, 30));
        for(Item item: itemList){
            JButton itemButton = new JButton(item.getItemName());
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog itemDialog = new JDialog(forSaleDialog, item.getItemName());
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
                    
                    JButton addToCart = new JButton("Add to Cart");
                    addToCart.setPreferredSize(new Dimension(220, 30));
                    addToCart.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            if(itemNo < limit){
                                itemNo++;
                                pickedItems.add(item);
                                itemDialog.dispose();
                            }
                            else{
                                String text = "Exceed number of items";
                                displayWarning(text);
                                itemDialog.dispose();
                            }
                        }
                    });
                    panel.add(addToCart);

                    itemDialog.setContentPane(panel);
                    itemDialog.setPreferredSize(new Dimension(250, 150));
                    itemDialog.pack();
                    itemDialog.setLocationRelativeTo(forSaleDialog);
                    itemDialog.setVisible(true);
                }
            });
            buttonPanel.add(itemButton);
        }
        JButton proceedButton = new JButton("Proceed");
        proceedButton.setPreferredSize(new Dimension(220, 30));
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(itemNo==limit){
                    forSaleDialog.dispose();   
                }
                else{
                    String text = "Add more items";
                    displayWarning(text);
                }
            }
        });
        mainPanel.add(buttonPanel);
        mainPanel.add(proceedButton);
        mainPanel.setPreferredSize(new Dimension(400, 100));
        forSaleDialog.setContentPane(mainPanel);
        forSaleDialog.setPreferredSize(new Dimension(700, 500));
        forSaleDialog.pack();
        forSaleDialog.setLocationRelativeTo(chooseDialog);
        forSaleDialog.setVisible(true);
    }

    public void vmFeaturesSVM(JFrame mainFrame, List<SpecialItem> vmItem){
        chooseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 3, 10, 10));
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel productsLabel = new JLabel("PRODUCTS FOR SALE");
        mainPanel.add(productsLabel);
        mainPanel.setPreferredSize(new Dimension(220, 30));
        
        for(SpecialItem item : vmItem){
            JButton itemButton = new JButton(item.getItemName());
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    JDialog itemDialog = new JDialog(chooseDialog, item.getItemName());
                    itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    
                    JPanel panel = new JPanel(new GridLayout(5, 2));
                    panel.setPreferredSize(new Dimension(250, 200));

                    JLabel cups = new JLabel("Cups");
                    panel.add(cups);
                    JLabel cupValue = new JLabel(String.valueOf(item.getCups()));
                    panel.add(cupValue);
                    
                    JLabel choc = new JLabel("No of Chocolates");
                    panel.add(choc);
                    JLabel chocValue = new JLabel(String.valueOf(item.getNoChocolates()));
                    panel.add(chocValue);

                    JLabel flav = new JLabel("No of Flavors");
                    panel.add(flav);
                    JLabel flavValue = new JLabel(String.valueOf(item.getNoFlavors()));
                    panel.add(flavValue);

                    JButton choose = new JButton("CHOOSE");
                    choose.setPreferredSize(new Dimension(220, 30));
                    choose.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            JDialog customizeDialog = new JDialog(mainDialog, "CUSTOMIZING");
                            JPanel customizePanel = new JPanel();
                            JLabel customizeLabel = new JLabel("Customizing ");
                            customizePanel.add(customizeLabel);
                            customizeLabel = new JLabel(item.getItemName());
                            customizePanel.add(customizeLabel);

                            JButton chocButton = new JButton("CHOOSE CHOCOLATES");
                            JButton flavButton = new JButton("CHOOSE FLAVORS");
                            chocButton.setPreferredSize(new Dimension(220, 30));
                            flavButton.setPreferredSize(new Dimension(220, 30));

                            flavButton.addActionListener(new ActionListener() {
                                @Override 
                                public void actionPerformed(ActionEvent e){
                                    JDialog itemDialog = new JDialog(mainFrame, item.getItemName());
                                    itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);   
                                    
                                }
                            });

                            chocButton.addActionListener(new ActionListener() {
                                @Override 
                                public void actionPerformed(ActionEvent e){
                                    JDialog itemDialog = new JDialog(mainFrame, item.getItemName());
                                    itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
                                }
                            });
                         
                            JButton proceedButton = new JButton("PROCEED");
                            proceedButton.setPreferredSize(new Dimension(220, 30));

                                proceedButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                        //COMPUTING
                                        double amount, price = 0, calories = 0, iceCreamCal = 0;

                                        //CALCULATE TOTAL CALORIES AND PRICE 
                                        calories = pickedIceCream.getTotalCalories();
                                        price = pickedIceCream.getTotalPrice();
                                        
                                        JPanel calcuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                        JLabel calcuLabel = new JLabel();
                                        calcuLabel = new JLabel("Total Amount of Calorie");
                                        calcuPanel.add(calcuLabel);
                                
                                        calcuLabel = new JLabel(String.valueOf(calories));
                                        calcuPanel.add(calcuLabel);
                                
                                        calcuLabel = new JLabel("Total Amount");
                                        calcuPanel.add(calcuLabel);

                                        calcuLabel = new JLabel(String.valueOf(price));
                                        calcuPanel.add(calcuLabel);
                                        calcuPanel.setPreferredSize(new Dimension(220, 100));

                                        JButton payButton = new JButton("PAY");
                                        JButton cancelButton = new JButton("CANCEL");

                                        payButton.setPreferredSize(new Dimension(220, 30));
                                        cancelButton.setPreferredSize(new Dimension(220, 30));
                                        
                                        payButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e){
                                                double Price = pickedIceCream.getTotalPrice();
                                                customizeDialog.dispose();
                                                mainDialog.dispose();
                                                //transaction(main, Price);   
                                            }
                                        });

                                        cancelButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e){
                                                //vendingMachineFeatures(main);
                                                customizeDialog.dispose();
                                                mainDialog.dispose();
                                            }
                                        });

                                        customizeDialog.add(calcuPanel);
                                        customizeDialog.add(payButton);
                                        customizeDialog.add(cancelButton);

                                        customizeDialog.revalidate();
                                        customizeDialog.repaint();
                                }
                            });
                            
                            customizePanel.add(flavButton);
                            customizePanel.add(chocButton);
                            customizePanel.add(proceedButton);
                            customizeDialog.setContentPane(customizePanel);
                            customizeDialog.setPreferredSize(new Dimension(250,200));
                            customizeDialog.setLocationRelativeTo(itemDialog);
                            customizeDialog.pack();
                            customizeDialog.setVisible(true);
                        }
                    });
                    
                    JButton cancel = new JButton("CANCEL");
                    cancel.setPreferredSize(new Dimension(220, 30));
                    cancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            mainDialog.dispose();
                        }
                    });

                    panel.add(choose);
                    panel.add(cancel);
                    itemDialog.setContentPane(panel);
                    itemDialog.pack();
                    itemDialog.setPreferredSize(new Dimension(700, 500));
                    itemDialog.setLocationRelativeTo(chooseDialog);
                    itemDialog.setVisible(true);
                }
            });
            buttonPanel.add(itemButton);
        }
        mainPanel.add(buttonPanel);
        chooseDialog.setContentPane(mainPanel);
        chooseDialog.pack();
        chooseDialog.setPreferredSize(new Dimension(700, 500));
        chooseDialog.setVisible(true);
    }

    public void displayWarning(String text){
        JLabel warnLabel = new JLabel(text);
        JPanel warnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        warnPanel.add(warnLabel);
        warnPanel.setPreferredSize(new Dimension(300, 100));
        warningDialog.setContentPane(warnPanel);
        warningDialog.setPreferredSize(new Dimension(300, 100));
        warningDialog.pack();
        warningDialog.setVisible(true);
    }

    public void chooseItemsSVM(JFrame mainFrame, List<SpecialItem> itemList, List<SpecialItem> vmItem, int slot, int capacity){
        itemsPanel.removeAll();
        nameField.setEnabled(false);
        slotField.setEnabled(false);
        capacityField.setEnabled(false);
        create.setEnabled(false);
        cancel.setEnabled(false);
        
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

        for(SpecialItem item: itemList){
            
            JButton itemButton = new JButton(item.getItemName());
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog itemDialog = new JDialog(chooseDialog, item.getItemName());
                    itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    
                    JPanel panel = new JPanel(new GridLayout(4, 2));

                    JLabel cups = new JLabel("Cups");
                    panel.add(cups);
                    panel.setPreferredSize(new Dimension(100, 30));
                    JLabel cupValue = new JLabel(String.valueOf(item.getCups()));
                    panel.add(cupValue);
                    panel.setPreferredSize(new Dimension(100, 30));
                    
                    JLabel choc = new JLabel("No of Chocolates");
                    panel.add(choc);
                    panel.setPreferredSize(new Dimension(100, 30));
                    JLabel chocValue = new JLabel(String.valueOf(item.getNoChocolates()));
                    panel.add(chocValue);
                    panel.setPreferredSize(new Dimension(100, 30));

                    JLabel flav = new JLabel("No of Flavors");
                    panel.add(flav);
                    panel.setPreferredSize(new Dimension(100, 30));
                    JLabel flavValue = new JLabel(String.valueOf(item.getNoFlavors()));
                    panel.add(flavValue);
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

    public void transaction(JFrame mainFrame, double balance){
        JPanel rpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel rpLabel = new JLabel();
        rpPanel.setLayout(new GridLayout(0, 1, 10, 10));
        rpLabel = new JLabel("Total Balance");
        rpPanel.add(rpLabel);
        rpPanel.setPreferredSize(new Dimension(220, 30));
        rpLabel = new JLabel(String.valueOf(balance));
        rpPanel.add(rpLabel);
        rpPanel.setPreferredSize(new Dimension(220, 30));
        rpLabel = new JLabel("Insert money [20 / 50 / 100 / 200 / 500 /1000]");
        rpPanel.add(rpLabel);
        rpPanel.setPreferredSize(new Dimension(220, 30));
        enterAmountField.setText(" ");
        rpPanel.add(enterAmountField);
        rpPanel.add(enterAmount);
        mainFrame.add(rpPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void setCheckOutListener(ActionListener actionListener){
        this.checkOut.addActionListener(actionListener);
    }

    public void setCancelCheckOutListener(ActionListener actionListener){
        this.cancelCheckOut.addActionListener(actionListener);
    }

    public void setAddToCartListener(ActionListener actionListener){
        this.addToCart.addActionListener(actionListener);
    }

    public void setCreateListener(ActionListener actionListener){
        this.create.addActionListener(actionListener);
    }

    public void setCreateSListener(ActionListener actionListener){
        this.createS.addActionListener(actionListener);
    }

    public void setCancelListener(ActionListener actionListener){
        this.cancel.addActionListener(actionListener);
    }

    public void setProceedListener(ActionListener actionListener){
        this.proceed.addActionListener(actionListener);
    }

    public void setPickChocListener(ActionListener actionListener){
        this.pickChoc.addActionListener(actionListener);
    }

    public void setPickFlavorListener(ActionListener actionListener){
        this.pickFlavor.addActionListener(actionListener);
    }
}
