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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainController {
    private MainModel mainmodel;
    private MainView mainview;
    private VendingMachineView vmView;
    private VendingMachineModel vmModel;
    

    public MainController(MainModel model, MainView view){
        this.mainmodel = model;
        this.mainview = view;

        mainview.setCreateVMListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mainview.mainFrame.getContentPane().removeAll();
                view.createMenu();
            }
        });

        mainview.setTestVMListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mainview.mainFrame.getContentPane().removeAll();
                view.testMenu();
            }
        });

        mainview.setExitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mainview.mainFrame.dispose();
            }
        });

        mainview.setRegularListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mainview.mainFrame.getContentPane().removeAll();
                model.vmType = 1;
                mainview.vm = new RegularVendingMachineView();
                mainmodel.vm = new RegularVendingMachineModel();
                mainview.vm.createVendingMachine(mainview.mainFrame);

                mainview.vm.setCreateListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        String text;
                        mainmodel.vm.setName(mainview.vm.nameField.getText());
                        mainmodel.vm.setSlot(Integer.parseInt(mainview.vm.slotField.getText()));
                        mainmodel.vm.setCapacity(Integer.parseInt(mainview.vm.capacityField.getText()));
                        mainmodel.vms.add(mainmodel.vm);

                        boolean result = model.vm.enterSlots(model.vm.slot);
        
                        if(model.vm.enterSlots(model.vm.slot)==false&&model.vm.enterCapacity(model.vm.capacity)==false){
                            text = "Invalid Capacity and Slots";
                            view.displayWarning(text);
                        }
                        else if(result){
                            result = model.vm.enterCapacity(model.vm.capacity);
                            if(!result){
                                text = "Invalid Capacity";
                                view.displayWarning(text);
                            }
                        }
                        else{
                            text = "Invalid Slots";
                            view.displayWarning(text);
                        }
                        
                        if(result){
                            mainview.vm.chooseItems(mainview.mainFrame, mainmodel.vm.mychocolates, mainmodel.vm.itemSlots,mainmodel.vm.slot, mainmodel.vm.capacity);
                            mainview.vm.setProceedListener(new ActionListener() {
                                @Override
                                public void actionPerformed (ActionEvent e){
                                    String title, text;
                                    System.out.print("ItemSlotSize: "+mainmodel.vm.itemSlots.size()+" item slots: "+mainmodel.vm.slot);
                                    if(mainmodel.vm.itemSlots.size() == mainmodel.vm.slot){
                                        title = "Message";
                                        text = "Vending Machine Successfully Created";
                                        mainview.displaySuccess(text, title);
                                        mainview.setSuccessDialogListner(new WindowAdapter() {
                                            @Override
                                            public void windowClosed(WindowEvent e){
                                                mainview.vm.chooseDialog.dispose();
                                                mainview.mainFrame.getContentPane().removeAll();
                                                mainview.MainMenu();
                                                mainview.mainFrame.revalidate();
                                                mainview.mainFrame.repaint();
                                            }
                                        });
                                    }
                                    else{
                                        text = "Add More Items";
                                        mainview.displayWarning(text);
                                    }
                                }
                            });

                        }
                    }
                });
                mainview.vm.setCancelListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        mainview.createMenu();
                    }
                });
                mainview.mainFrame.revalidate();
                mainview.mainFrame.repaint();
            }
        });

        mainview.setSpecialListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mainview.mainFrame.getContentPane().removeAll();
                model.vmType = 2;
                mainview.vm = new SpecialVendingMachineView();
                mainmodel.vm = new SpecialVendingMachineModel();
                mainview.vm.createVendingMachine(mainview.mainFrame);
                mainview.vm.setCreateSListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        String title, text;
                        mainmodel.vm.setName(mainview.vm.nameField.getText());
                        mainmodel.vm.setSlot(Integer.parseInt(mainview.vm.slotField.getText()));
                        mainmodel.vm.setCapacity(Integer.parseInt(mainview.vm.capacityField.getText()));
                        mainmodel.vms.add(mainmodel.vm);

                        boolean result = model.vm.enterSlots(model.vm.slot);
        
                        if(model.vm.enterSlots(model.vm.slot)==false&&model.vm.enterCapacity(model.vm.capacity)==false){
                            text = "Invalid Capacity and Slots";
                            view.displayWarning(text);
                        }
                        else if(result){
                            result = model.vm.enterCapacity(model.vm.capacity);
                            if(!result){
                                text = "Invalid Capacity";
                                view.displayWarning(text);
                            }
                        }
                        else{
                            text = "Invalid Slots";
                            view.displayWarning(text);
                        }   
                        
                        if(result){
                            mainview.vm.chooseItemsSVM(mainview.mainFrame, mainmodel.vm.myIceCream, mainmodel.vm.iceCream, mainmodel.vm.slot,mainmodel.vm.capacity);
                            mainview.vm.setProceedListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    System.out.print("ItemSlotSize: "+mainmodel.vm.iceCream.size()+" item slots: "+mainmodel.vm.slot);
                                    String text, title;
                                    if(mainmodel.vm.iceCream.size() == mainmodel.vm.slot){
                                        title = "Message";
                                        text = "Vending Machine Successfully Created";
                                        mainview.displaySuccess(text, title);
                                        mainview.setSuccessDialogListner(new WindowAdapter() {
                                            @Override
                                            public void windowClosed(WindowEvent e){
                                                mainview.vm.chooseDialog.dispose();
                                                mainview.mainFrame.getContentPane().removeAll();
                                                mainview.MainMenu();
                                                mainview.mainFrame.revalidate();
                                                mainview.mainFrame.repaint();
                                            }
                                        });
                                    }
                                    else{

                                    }
                                }
                            });
                        }
                    }
                });

                mainview.vm.setCancelListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        mainview.createMenu();
                    }   
                });

                mainview.mainFrame.revalidate();
                mainview.mainFrame.repaint();
            }
        });

        mainview.setCancelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mainview.mainFrame.getContentPane().removeAll();
                view.createMenu();
                mainview.mainFrame.revalidate();
                mainview.mainFrame.repaint();
            }
        });
    
        mainview.setCurrentListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                if(mainmodel.vm==null){
                    String text = "No Vending Machine has been created yet";
                    mainview.displayWarning(text);
                }
                else{
                    mainview.mainFrame.getContentPane().removeAll();
                    mainview.testVendingMachineMenu();
                    mainview.mainFrame.revalidate();
                    mainview.mainFrame.repaint();
                }
            }
        });

        mainview.setAnotherListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                if(mainmodel.vms.size()>1){
                    for(VendingMachineModel vm : mainmodel.vms){
                        JButton vmButton = new JButton(vm.name);
                        vmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e){
                                mainmodel.vm = vm;
                                mainview.mainFrame.getContentPane().removeAll();
                                mainview.testVendingMachineMenu();
                                mainview.mainFrame.revalidate();
                                mainview.mainFrame.repaint();
                            }
                        });
                    }
                }   
                else{
                    String text = "No Vending Machine has been created yet";
                    mainview.displayWarning(text);
                }
            }
        });
// to do
        mainview.setVendingFeaturesistener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                if(mainmodel.vmType==1){
                    mainview.vm.vendingMachineFeatures(mainview.vm.itemSlots, mainview.mainFrame);
                }
                else if(mainmodel.vmType == 2){
                    //mainview.vm.vmFeaturesSVM(mainview.mainFrame, mainmodel.vm.iceCream);
                    JDialog chooseDialog = new JDialog();
                    chooseDialog.setPreferredSize(new Dimension(700, 500));
                    chooseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.setLayout(new GridLayout(0, 3, 10, 10));
                    JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    JLabel productsLabel = new JLabel("PRODUCTS FOR SALE");
                    mainPanel.add(productsLabel);
                    mainPanel.setPreferredSize(new Dimension(220, 30));
                    
                    for(SpecialItem item : mainmodel.vm.iceCream){
                        JButton itemButton = new JButton(item.getItemName());
                        itemButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e){
                                SpecialItem pickedIceCream = item;
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
                                        mainview.vm.customizeDialog.setTitle("CUSTOMIZING");
                                        
                                        //JDialog customizeDialog = new JDialog(itemDialog, "CUSTOMIZING");
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
                                                JDialog itemDialog = new JDialog(mainview.mainFrame, item.getItemName());
                                                mainview.vm.chooseItem(mainmodel.vm.myflavors, mainmodel.vm.pickedFlavors, pickedIceCream.getNoFlavors());
                                                itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);   
                                            }
                                        });

                                        chocButton.addActionListener(new ActionListener() {
                                            @Override 
                                            public void actionPerformed(ActionEvent e){
                                                JDialog itemDialog = new JDialog(mainview.mainFrame, item.getItemName());
                                                mainview.vm.chooseItem(mainmodel.vm.mychocolates, mainmodel.vm.pickedChocolates, pickedIceCream.getNoChocolates());
                                                itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
                                            }
                                        });
                                    
                                        JButton proceedButton = new JButton("PROCEED");
                                        proceedButton.setPreferredSize(new Dimension(220, 30));

                                        proceedButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e){
                                                    if(mainmodel.vm.pickedChocolates.size() == pickedIceCream.getNoChocolates() && mainmodel.vm.pickedFlavors.size() == pickedIceCream.getNoFlavors()){
                                                        String text, title;
                                                        text = "Ice Cream Complete!";
                                                        title = "Message";
                                                        mainview.displaySuccess(text, title);
                                                        mainview.setSuccessDialogListner(new WindowAdapter() {
                                                            @Override
                                                            public void windowClosed(WindowEvent e){
                                                                //COMPUTING
                                                                double amount, price = 0, calories = 0, iceCreamCal = 0;

                                                                //CALCULATE TOTAL CALORIES AND PRICE 
                                                                calories = pickedIceCream.getTotalCalories();
                                                                price = pickedIceCream.getTotalPrice();
                                                                
                                                                mainview.vm.checkingOut(mainview.mainFrame, calories, price);
                                                                mainview.vm.setCheckOutListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e){
                                                                        double price = pickedIceCream.getTotalPrice();
                                                                        mainview.vm.transaction(mainview.mainFrame, mainmodel.vm.balance);
                                                                        mainview.vm.setCheckOutListener(new ActionListener() {
                                                                            @Override
                                                                            public void actionPerformed(ActionEvent e){
                                                                                double amount = Double.parseDouble(mainview.vm.enterAmountField.getText());
                                                                                if(!(amount==20||amount==50||amount==100||amount==200||
                                                                                amount==500||amount==1000)){   
                                                                                    String text = "Invalid Money";
                                                                                    mainview.displayWarning(text);
                                                                                }
                                                                                else{
                                                                                    mainmodel.vm.balance += amount;
                                                                                    if(mainmodel.vm.balance < price){
                                                                                        String text = "Insufficient Money";
                                                                                        mainview.displayWarning(text);
                                                                                    }
                                                                                    else{
                                                                                        mainmodel.vm.totalChange-=mainmodel.vm.balance;
                                                                                        mainmodel.vm.totalSales += price;
                                                                                        JPanel rpPanel = new JPanel(new GridLayout(0, 1, 10, 10));
                                                                                        JLabel rpLabel = new JLabel("Computing change");
                                                                                        rpPanel.add(rpLabel);
                                                                                        rpPanel.setPreferredSize(new Dimension(220, 30));
                                                                                        mainmodel.vm.balance -= price;
                                                                                        rpLabel = new JLabel("Vending Machine Credits");
                                                                                        rpPanel.add(rpLabel);
                                                                                        rpPanel.setPreferredSize(new Dimension(220, 30));
                                                                                        
                                                                                        rpLabel = new JLabel(String.valueOf(mainmodel.vm.totalChange));
                                                                                        rpPanel.add(rpLabel);
                                                                                        rpPanel.setPreferredSize(new Dimension(220, 30));
                                                                                        
                                                                                        for(Item item : mainmodel.vm.pickedItems){
                                                                                            mainmodel.vm.addTransaction(item, item.getPrice());
                                                                                            for(int i=0; i<mainmodel.vm.mychocolates.size();i++){
                                                                                                if(mainmodel.vm.mychocolates.get(i).getItemName()==item.getItemName()){
                                                                                                    mainmodel.vm.mychocolates.get(i).setQuantity(mainmodel.vm.mychocolates.get(i).getQuantity()-item.getQuantity());

                                                                                                }   
                                                                                                else if(mainmodel.vm.myflavors.get(i).getItemName()==item.getItemName()){
                                                                                                    mainmodel.vm.myflavors.get(i).setQuantity(mainmodel.vm.myflavors.get(i).getQuantity()-item.getQuantity());
                                                                                                    
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        rpLabel = new JLabel("Transaction Completed");
                                                                                        rpPanel.add(rpLabel);
                                                                                        rpPanel.setPreferredSize(new Dimension(220, 30));     

                                                                                        JButton doneButton = new JButton("DONE");
                                                                                        doneButton.setPreferredSize(new Dimension(220, 30));
                                                                                        doneButton.addActionListener(new ActionListener() {
                                                                                            @Override
                                                                                            public void actionPerformed(ActionEvent e){
                                                                                                mainview.MainMenu();
                                                                                                mainview.mainFrame.getContentPane().removeAll();
                                                                                            }
                                                                                        });
                                                                                        mainview.mainFrame.add(rpPanel);
                                                                                        mainview.mainFrame.revalidate();
                                                                                        mainview.mainFrame.repaint();
                                                                                    }
                                                                                }
                                                                            }
                                                                        });
                                                                    }
                                                                });

                                                                mainview.vm.setCancelCheckOutListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e){
                                                                        mainview.MainMenu();
                                                                        mainview.mainFrame.revalidate();
                                                                        mainview.mainFrame.repaint();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        mainview.vm.customizeDialog.dispose();
                                                       
                                                        mainview.vm.customizeDialog.revalidate();
                                                        mainview.vm.customizeDialog.repaint();
                                                    }
                                                    else{
                                                        String text = "Add more items";
                                                        mainview.displayWarning(text);
                                                    }
                                            }
                                        });
                                        
                                        customizePanel.add(flavButton);
                                        customizePanel.add(chocButton);
                                        customizePanel.add(proceedButton);
                                        mainview.vm.customizeDialog.setContentPane(customizePanel);
                                        mainview.vm.customizeDialog.setPreferredSize(new Dimension(250,200));
                                        mainview.vm.customizeDialog.setLocationRelativeTo(itemDialog);
                                        mainview.vm.customizeDialog.pack();
                                        mainview.vm.customizeDialog.setVisible(true);
                                    }
                                });
                                
                                JButton cancel = new JButton("CANCEL");
                                cancel.setPreferredSize(new Dimension(220, 30));
                                cancel.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e){
                                        //mainDialog.dispose();
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
                mainview.mainFrame.revalidate();
                mainview.mainFrame.repaint();
            }
        });

        mainview.setMaintenanceFeaturesListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                mainview.mainFrame.getContentPane().removeAll();
                mainview.maintenanceFeaturesMenu();
                mainview.mainFrame.revalidate();
                mainview.mainFrame.repaint();
            }
        });
    
        mainview.setBackListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                mainview.mainFrame.getContentPane().removeAll();
                mainview.testVendingMachineMenu();
                mainview.mainFrame.revalidate();
                mainview.mainFrame.repaint();
            }
        });
        
        mainview.setRestockItemListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                mainview.itemsPanel.removeAll();
                mainview.vm.nameField.setEnabled(false);
                mainview.vm.slotField.setEnabled(false);
                mainview.vm.capacityField.setEnabled(false);
                mainview.cancel.setEnabled(false);

                mainview.chooseDialog = new JDialog(mainview.mainFrame, "RESTOCKING ITEMS");
                mainview.chooseDialog.setPreferredSize(new Dimension(700, 500));
                mainview.mainLabel = new JLabel("Choose Item to Restock");
                mainview.addPanel = new JPanel();
                mainview.addPanel.setPreferredSize(new Dimension(700, 500));
                mainview.addPanel.add(mainview.mainLabel);
                mainview.chooseDialog.setContentPane(mainview.addPanel);
                for(Item item : model.vm.itemSlots){
                    JButton itemButton = new JButton(item.getItemName());
                    
                    itemButton.addActionListener(new ActionListener() {
                        @Override 
                        public void actionPerformed(ActionEvent e){
                            model.vm.itemClicked = item;
                            JDialog itemDialog = new JDialog(view.chooseDialog, item.getItemName());
                            itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                            
                            JPanel panel = new JPanel(new GridLayout(5, 2));

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

                            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                            quantityLabel = new JLabel("Enter Quantity");
                            JTextField quantityField = new JTextField(15);
                            panel.add(quantityLabel);
                            panel.add(quantityField);
                            panel.setPreferredSize(new Dimension(110, 30));
                            
                            mainview.setRestockListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    int temp;
                                    boolean result;
                                    temp = model.vm.itemClicked.getQuantity() + Integer.parseInt(quantityField.getText());
                                    result = mainmodel.vm.checkRestock(temp, mainmodel.vm.itemSlots);
                                    String title, text;
                                    if(result){
                                        text = model.vm.itemClicked.getItemName()+" Restocked";
                                        title = "Message";
                                        mainview.displaySuccess(text, title);
                                    }   
                                    else{
                                        text = "Exceeds Capacity";
                                        mainview.displayWarning(text);
                                    }
                                    
                                }
                            });
                            panel.add(mainview.restock);
                            panel.setPreferredSize(new Dimension(220,30));

                            itemDialog.setContentPane(panel);
                            itemDialog.setPreferredSize(new Dimension(250, 150));
                            itemDialog.pack();
                            itemDialog.setLocationRelativeTo(view.chooseDialog);
                            itemDialog.setVisible(true);
                        }
                    });
                    mainview.itemsPanel.add(itemButton);
                }
                
                mainview.setChooseDialogListner(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e){
                        mainview.maintenanceFeaturesMenu();
                    }
                });
                mainview.setDoneListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        mainview.chooseDialog.dispose();
                    }
                });
                mainview.setRestockAllListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        JDialog maintenanceDialog = new JDialog(mainview.chooseDialog, "RESTOCKING ALL ITEMS");
                        maintenanceDialog.setPreferredSize(new Dimension(22, 100));
                        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                        panel.setLayout(new GridLayout(0, 2, 0, 0));
                        JLabel quantityLabel = new JLabel("Enter Quantity");
                        JTextField quantityField = new JTextField(15);
                        panel.add(quantityLabel);
                        panel.add(quantityField);
                        panel.setPreferredSize(new Dimension(220, 30));

                        JButton restockButton = new JButton("Restock");
                        restockButton.setPreferredSize(new Dimension(220, 30));
                        mainview.maintenanceDialog.add(restockButton);
                        restockButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e){
                                boolean result = model.vm.checkRestockAll(Integer.parseInt(quantityField.getText()), mainmodel.vm.itemSlots);
                                String title, text;
                                if(result){
                                    title = "Message";
                                    text = "All items restocked";
                                    mainview.displaySuccess(text, title);
                                }
                                else{
                                    text = "Exceeds Capacity";
                                    mainview.displayWarning(text);
                                }
                            }
                        });
                        panel.add(restockButton);
                        panel.setPreferredSize(new Dimension(220, 30));
                        maintenanceDialog.setContentPane(panel);
                        maintenanceDialog.pack();
                        maintenanceDialog.setVisible(true);
                    }
                });
                mainview.chooseDialog.add(mainview.itemsPanel);
                mainview.chooseDialog.add(mainview.restockAll);
                mainview.chooseDialog.add(mainview.done);
                
                mainview.chooseDialog.pack();
                mainview.chooseDialog.setLocationRelativeTo(mainview.mainFrame);
                mainview.chooseDialog.setVisible(true);
                
            }
        });

        mainview.setPriceListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                mainview.itemsPanel.removeAll();
                mainview.vm.nameField.setEnabled(false);
                mainview.vm.slotField.setEnabled(false);
                mainview.vm.capacityField.setEnabled(false);
                mainview.cancel.setEnabled(false);

                mainview.chooseDialog = new JDialog(mainview.mainFrame, "SETTING PRICE");
                mainview.chooseDialog.setPreferredSize(new Dimension(700, 500));
                mainview.mainLabel = new JLabel("Choose Item to set price");
                mainview.addPanel = new JPanel();
                mainview.addPanel.setPreferredSize(new Dimension(700, 500));
                mainview.addPanel.add(mainview.mainLabel);
                mainview.chooseDialog.setContentPane(mainview.addPanel);
                for(Item item : model.vm.itemSlots){
                    JButton itemButton = new JButton(item.getItemName());
                    
                    itemButton.addActionListener(new ActionListener() {
                        @Override 
                        public void actionPerformed(ActionEvent e){
                            model.vm.itemClicked = item;
                            JDialog itemDialog = new JDialog(view.chooseDialog, item.getItemName());
                            itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                            
                            JPanel panel = new JPanel(new GridLayout(5, 2));

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

                            JLabel priceLabel = new JLabel("Enter Price");
                            JTextField priceField = new JTextField(15);
                            panel.add(priceLabel);
                            panel.add(priceField);
                            panel.setPreferredSize(new Dimension(110, 30));
                            
                            mainview.setSetListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    double temp;
                                    boolean result;
                                    temp = Double.parseDouble(priceField.getText());
                                    result = mainmodel.vm.checkPrice(temp, model.vm.itemClicked);
                                    String title, text;
                                    if(result){
                                        text = model.vm.itemClicked.getItemName()+" price set";
                                        title = "Message";
                                        mainview.displaySuccess(text, title);
                                    }   
                                    else{
                                        text = "Invalid Money";
                                        mainview.displayWarning(text);
                                    }
                                    
                                }
                            });
                            panel.add(mainview.set);
                            panel.setPreferredSize(new Dimension(220,30));

                            itemDialog.setContentPane(panel);
                            itemDialog.setPreferredSize(new Dimension(250, 150));
                            itemDialog.pack();
                            itemDialog.setLocationRelativeTo(view.chooseDialog);
                            itemDialog.setVisible(true);
                        }
                    });
                    mainview.itemsPanel.add(itemButton);
                }
                mainview.setChooseDialogListner(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e){
                        mainview.maintenanceFeaturesMenu();
                    }
                });
                mainview.setDoneListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        mainview.chooseDialog.dispose();
                    }
                });
            
                mainview.chooseDialog.add(mainview.itemsPanel);
                mainview.chooseDialog.add(mainview.done);
                
                mainview.chooseDialog.pack();
                mainview.chooseDialog.setLocationRelativeTo(mainview.mainFrame);
                mainview.chooseDialog.setVisible(true);
            }
        });

        mainview.setCollectMoneyListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                mainview.collectMenu(mainview.maintenanceDialog, mainmodel.vm.totalSales);    
            }
        });

        mainview.setCollectListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                boolean result;
                double money = Double.parseDouble(mainview.collectField.getText());
                result = mainmodel.vm.checkMoney(money);
                String title, text;
                if(result){
                    title = "Message";
                    text = "Money Collected: "+money;
                    mainview.displaySuccess(text, title);
                    mainview.setSuccessDialogListner(new WindowAdapter() {
                        public void windowClosed(WindowEvent e){
                            mainview.maintenanceDialog.dispose();
                        }
                    });
                }
                else{
                    text = "Not Enough Value";
                    mainview.displayWarning(text);
                }
                
            }
        });

        mainview.setReplenishChangeListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                mainview.replenishMenu(mainview.maintenanceDialog, mainmodel.vm.totalChange);
            }
        });

        mainview.setReplenishListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                boolean result;
                double change = Double.parseDouble(mainview.changField.getText());
                result = mainmodel.vm.checkChange(change);
                String title, text;
                if(result){
                    title = "Message";
                    text = "Total Change "+mainmodel.vm.totalChange;
                    mainview.displaySuccess(text, title);
                    mainview.setSuccessDialogListner(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e){
                            mainview.maintenanceDialog.dispose();
                        }
                    });
                }
                else{
                    text = "Invalid Money";
                    mainview.displayWarning(text);
                }
            }
        });

        mainview.setPrintSummaryListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                mainview.mainFrame.getContentPane().removeAll();
                
                mainview.mainFrame.revalidate();
                mainview.mainFrame.repaint();
            }
        });
    }
}
