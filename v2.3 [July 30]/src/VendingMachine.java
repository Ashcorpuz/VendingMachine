import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
    Description: The VendingMachine abstract class represents a generic vending machine.
    It provides functionality for managing item slots, restocking items, setting item prices,
    collecting money, replenishing change, printing transaction summaries, and more.
*/
abstract class VendingMachine {
    protected List<Item> itemSlots;
    protected double totalSales;
    protected double totalChange;
    protected String name;
    protected List<Transaction> transactions;
    protected Scanner scanner;
    protected int vmType;
    protected int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
    protected int capacity;
    protected int slot;   //Specifies the slot of vending machine
    protected int balance;
    protected JTextField nameField, slotField, maxField;
    protected Item items;
    protected Item itemClicked;
    protected JDialog itemClickedDialog, mainDialog;
    protected JPanel itemClickedPanel;
    protected List<Item> pickedItems;
    protected List<Integer> pickedItemQuantity;
    protected List<Item> myFlavor, myChocolate;
    protected StoredItems storedItems = new StoredItems();
    protected StoredItems flavors = new StoredItems();

    /*
       Description: Construct a Vending Machine object. It initializes the itemSlots,
       transactions, totalChange, totalSales, capacity and balance.
    */
    public VendingMachine() {
        scanner = new Scanner(System.in);
        itemSlots = new ArrayList<>();
        transactions = new ArrayList<>();
        totalChange = 0;
        totalSales = 0;
        capacity = 0;
        balance = 0;
        vmType = 0;
        itemClickedDialog = new JDialog();
        pickedItems = new ArrayList<>();
        storedItems.readXMLFile("items.xml");
        flavors.readXMLFile("flavors.xml");
        myFlavor = flavors.getItemsList();
        myChocolate = storedItems.getItemsList();
    }
    /*
        Description: Creates the vending machine by implementing the specific details of item slots and other features.
    */
    public abstract void createVendingMachine();
    public abstract void createVendingMachine(Main main);
    /*
        Description: Provides the user interface for vending machine features.
    */
    public abstract void vendingMachineFeatures();
    public abstract void vendingMachineFeatures(Main main);
    /*
        Description: Starts the testing of the vending machine with options for vending machine features and maintenance features.
        Return Value: void
    */
    protected void testVendingMachine(Main main){
        main.mainFrame.getContentPane().removeAll();

        JPanel testPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel testLabel = new JLabel("TESTING VENDING MACHINE");
        testPanel.add(testLabel);
        testPanel.setPreferredSize(new Dimension(220, 30));
        
        JButton vmFeatureButton = new JButton("Vending Machine Features");
        vmFeatureButton.setPreferredSize(new Dimension(220, 30));
        vmFeatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                vendingMachineFeatures(main);
                main.mainFrame.revalidate();
                main.mainFrame.repaint();
            }
        });

        JButton mFeatureButton = new JButton("Maintenance Features");
        mFeatureButton.setPreferredSize(new Dimension(220, 30));
        mFeatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                maintenanceFeatures(main);
            }
        });

        main.mainFrame.add(testPanel);
        main.mainFrame.add(vmFeatureButton);
        main.mainFrame.add(mFeatureButton);
        main.goToMainMenu(main);
    }

    protected void testVendingMachine() {
        int option;
        while (true) {
            System.out.println("+------------------------------------------+");
            System.out.println("| TEST VENDING MACHINE                     |");
            System.out.println("| [1] - Vending Machine Features           |");
            System.out.println("| [2] - Maintenance Features               |");
            System.out.println("| [0] - EXIT                               |");
            System.out.println("+------------------------------------------+");
            System.out.print(">> ");

            option = getUserInput();

            if (option == 0) break;
            switch (option) {
                //Testing Vending Machine
                case 1 -> vendingMachineFeatures();

                case 2 -> {
                    int choice;
                    while (true) {
                        System.out.println("Maintenance Features.");
                        System.out.println("[1] - Restock Items");
                        System.out.println("[2] - Price Items");
                        System.out.println("[3] - Collect Money");
                        System.out.println("[4] - Replenish Change");
                        System.out.println("[5] - Print Transactions");
                        System.out.println("[0] - Exit ");

                        try {
                            choice = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            scanner.nextLine();
                            continue;
                        }

                        if (choice == 0 ) break;
                        switch (choice) {
                            case 1 -> restockItems();
                            case 2 -> setItemPrice();
                            case 3 -> collectMoney();
                            case 4 -> replenishChange();
                            case 5 ->  printTransactionSummary();
                            default -> System.out.println("Invalid selection");
                        }
                    }
                }
                default -> System.out.println("Invalid selection");

            }
        }
    }
    /*
        Description: Restocks the specified item in the vending machine.
        Return Value: void
    */
    protected void maintenanceFeatures(Main main){
        main.mainFrame.getContentPane().removeAll();

        JLabel mfLabel = new JLabel("MAINTENANCE FEATURES");
        JPanel mfPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mfPanel.add(mfLabel);
        mfPanel.setPreferredSize(new Dimension(220, 30));

        JButton restockButton = new JButton("Restock Item");
        JButton priceButton = new JButton("Price Item");
        JButton collectButton = new JButton("Collect Money");
        JButton repButton = new JButton("Replenish Change");
        JButton printButton = new JButton("Print Transaction");
        JButton backButton = new JButton("Back");
        
        restockButton.setPreferredSize(new Dimension(220, 30));
        restockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(vmType==1){
                    restockItems(main, itemSlots);
                }
                else if(vmType==2){
                    main.mainFrame.getContentPane().removeAll();
                    JButton baseButton = new JButton("ICE CREAM");
                    JButton topButton = new JButton("CHOCOLATE");
                    baseButton.setPreferredSize(new Dimension(220, 30));
                    topButton.setPreferredSize(new Dimension(220, 30));

                    baseButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            restockItems(main, myFlavor);
                        }
                    });

                    topButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            restockItems(main, myChocolate);
                        }
                    });
                    main.mainFrame.add(baseButton);
                    main.mainFrame.add(topButton);
                    main.mainFrame.revalidate();
                    main.mainFrame.repaint();
                }
            }
        });

        priceButton.setPreferredSize(new Dimension(220, 30));
        priceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(vmType==1){
                    setItemPrice(main, itemSlots);
                }
                else if(vmType==2){
                    main.mainFrame.getContentPane().removeAll();
                    JButton baseButton = new JButton("ICE CREAM");
                    JButton topButton = new JButton("CHOCOLATE");
                    baseButton.setPreferredSize(new Dimension(220, 30));
                    topButton.setPreferredSize(new Dimension(220, 30));

                    baseButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            setItemPrice(main, myFlavor);
                        }
                    });

                    topButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            setItemPrice(main, myChocolate);
                        }
                    });
                    main.mainFrame.add(baseButton);
                    main.mainFrame.add(topButton);
                    main.mainFrame.revalidate();
                    main.mainFrame.repaint();
                }
            }
        });

        collectButton.setPreferredSize(new Dimension(220, 30));
        collectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                collectMoney(main);   
            }
        });

        repButton.setPreferredSize(new Dimension(220, 30));
        repButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                replenishChange(main);
            }
        });

        printButton.setPreferredSize(new Dimension(220, 30));
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                printTransactionSummary(main);
            }
        });

        backButton.setPreferredSize(new Dimension(220, 30));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                testVendingMachine(main);
                main.mainFrame.revalidate();
                main.mainFrame.repaint();
            }
        });
        main.mainFrame.add(restockButton);
        main.mainFrame.add(priceButton);
        main.mainFrame.add(collectButton);
        main.mainFrame.add(repButton);
        main.mainFrame.add(printButton);
        main.mainFrame.add(backButton);
        main.mainFrame.revalidate();
        main.mainFrame.repaint();
    }
    protected void restockItems(Main main, List<Item> itemToRestock){
        JDialog restockDialog = new JDialog(main.mainFrame, "CHOOSING ITEM TO RESTOCK");
        restockDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        restockDialog.setPreferredSize(new Dimension(700, 500));

        JButton restock = new JButton("RESTOCK");
        restock.setPreferredSize(new Dimension(220, 30));

        JPanel mainPanel =new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel mainLabel = new JLabel("Restocking");
        mainPanel.add(mainLabel);
        mainPanel.setPreferredSize(new Dimension(100, 30));

        JPanel restockPanel = new JPanel();

        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel quantityLabel = new JLabel("Enter Quantity");
        JTextField quantityField = new JTextField(15);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantityField);
        quantityPanel.setPreferredSize(new Dimension(220, 70));

        JButton resButton = new JButton("Restock");
        resButton.setPreferredSize(new Dimension(150, 30));
        resButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JDialog resDialog = new JDialog(restockDialog);
                JLabel resLabel = new JLabel();
                JPanel resPanel = new JPanel();
                resDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                int temp;
                temp = itemClicked.getQuantity() + Integer.parseInt(quantityField.getText());
                if(temp<=capacity){
                    
                    resDialog.setTitle("RESTOCKING");
                    for(Item item: itemToRestock){
                        if(item.getItemName() == itemClicked.getItemName()){
                            item.setQuantity(temp);
                        }
                    }   
                    resLabel = new JLabel("Restocked");
                    resPanel = new JPanel();
                    resPanel.add(resLabel);
                    resPanel.setPreferredSize(new Dimension(220, 30));
                }
                else{
                    resDialog.setTitle("WARNING");
                    resLabel = new JLabel("Exceeds Capacity");
                    resPanel = new JPanel();
                    resPanel.add(resLabel);
                    resPanel.setPreferredSize(new Dimension(220, 30));
                }
                resDialog.setContentPane(resPanel);
                resDialog.setPreferredSize(new Dimension(220,100));
                resDialog.pack();
                resDialog.setLocationRelativeTo(main.mainFrame);
                resDialog.setVisible(true);
            }
        });

        displayItems(main, restockDialog, restockPanel, quantityPanel, resButton, itemToRestock);

        JButton restockAll = new JButton("Restock All");
        restockAll.setPreferredSize(new Dimension(220, 30));
        restockAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                itemClickedDialog.setTitle("ALL ITEMS");
                itemClickedDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                JLabel quantityLabel = new JLabel("Enter Quantity");
                JTextField quantityField = new JTextField(15);
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                panel.add(quantityLabel);
                panel.add(quantityField);
                panel.setPreferredSize(new Dimension(220, 70));

                JButton resAllButton = new JButton("Restock");
                resAllButton.setPreferredSize(new Dimension(150, 30));
                resAllButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    JDialog resDialog = new JDialog(itemClickedDialog);
                    JLabel resLabel = new JLabel();
                    JPanel resPanel = new JPanel();
                    resDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                    int temp=0;
                    
                    for(Item items : itemToRestock){
                        int tempQuan;
                        tempQuan = items.getQuantity() + Integer.parseInt(quantityField.getText());
                        if(tempQuan>capacity)
                        temp = capacity +1;
                    }
                    if(temp<=capacity){
                    resDialog.setTitle("RESTOCKING");
                    for(Item item : itemToRestock){
                        int itemQuantity;
                        itemQuantity = item.getQuantity() + Integer.parseInt(quantityField.getText());
                        item.setQuantity(itemQuantity); 
                    }
                    resLabel = new JLabel("Restocked");
                    resPanel = new JPanel();
                    resPanel.add(resLabel);
                    resPanel.setPreferredSize(new Dimension(220, 30));
                    }
                    else if(temp>capacity){
                        resDialog.setTitle("WARNING");
                        resLabel = new JLabel("Exceeds Capacity");
                        resPanel = new JPanel();
                        resPanel.add(resLabel);
                        resPanel.setPreferredSize(new Dimension(220, 30));
                    }
                    resDialog.setContentPane(resPanel);
                    resDialog.setPreferredSize(new Dimension(220, 150));
                    resDialog.pack();
                    resDialog.setLocationRelativeTo(main.mainFrame);
                    resDialog.setVisible(true);
                }
            });
                panel.add(resAllButton);
                panel.setPreferredSize(new Dimension(220,30));

                itemClickedDialog.setContentPane(panel);
                itemClickedDialog.setPreferredSize(new Dimension(500, 150));
                itemClickedDialog.pack();
                itemClickedDialog.setLocationRelativeTo(main.mainFrame);;
                itemClickedDialog.setVisible(true);
                
            }
        });
        
        JButton doneButton = new JButton("DONE");
        doneButton.setPreferredSize(new Dimension(220, 30));
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                restockDialog.dispose();
                itemClickedDialog.dispose();
                maintenanceFeatures(main);
                main.mainFrame.revalidate();
                main.mainFrame.repaint();
            }
        });
        mainPanel.add(restockPanel);
        mainPanel.add(restockAll);
        mainPanel.add(doneButton);
        mainPanel.setPreferredSize(new Dimension(400, 100));
        restockDialog.setContentPane(mainPanel);
        restockDialog.pack();
        restockDialog.setLocationRelativeTo(main.mainFrame);
        restockDialog.setVisible(true);
    }

    protected void restockItems() {
        displayItems(itemSlots);
        System.out.println("Enter item to restock");
        System.out.print(">> ");
        int index = getUserInput();

        if (index == 0) {
            System.out.println("Cancelled");
            return;
        }
        if (index < 0 || index > itemSlots.size()) {
            System.out.println("Invalid item");
            return;
        }
        System.out.println("Enter quantity");
        int quantity = getUserInput();

        if (quantity < 0 || quantity > capacity) {
            System.out.println("Exceeds capacity");
            return;
        }

        Item pickedItem = itemSlots.get(index - 1);
        pickedItem.setQuantity(pickedItem.getQuantity() + quantity);
        System.out.println("Restocked successfully");
    }
    /*
        Description: Changes the price of the specified item in the vending machine.
        Return Value: void
    */
    
    protected void setItemPrice(Main main, List<Item> priceToSet){
        JDialog priceDialog = new JDialog(main.mainFrame, "CHOOSING ITEM TO SET PRICE");
        priceDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        priceDialog.setPreferredSize(new Dimension(700, 500));

        JButton set = new JButton("SET PRICE");
        set.setPreferredSize(new Dimension(220, 30));

        JPanel mainPanel =new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel mainLabel = new JLabel("Setting Price");
        mainPanel.add(mainLabel);
        mainPanel.setPreferredSize(new Dimension(100, 30));

        JPanel pricPanel = new JPanel();

        JPanel setPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel setLabel = new JLabel("Enter Quantity");
        JTextField setField = new JTextField(15);
        setPanel.add(setLabel);
        setPanel.add(setField);
        setPanel.setPreferredSize(new Dimension(220, 30));

        JButton setButton = new JButton("Set Price");
        setButton.setPreferredSize(new Dimension(150, 30));
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JDialog setDialog = new JDialog(priceDialog);
                JLabel setLabel = new JLabel();
                JPanel setPanel = new JPanel();
                setDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                double temp;
                temp = Double.parseDouble(setField.getText());
                if(temp>0){
                    
                    setDialog.setTitle("SETTING PRICE");
                    for(Item item: priceToSet){
                        if(item.getItemName() == itemClicked.getItemName()){
                            item.setPrice(temp);
                        }
                    }   
                    setLabel = new JLabel("Price Set");
                    setPanel = new JPanel();
                    setPanel.add(setLabel);
                    setPanel.setPreferredSize(new Dimension(220, 30));
                }
                else{
                    setDialog.setTitle("WARNING");
                    setLabel = new JLabel("Invalid Price");
                    setPanel = new JPanel();
                    setPanel.add(setLabel);
                    setPanel.setPreferredSize(new Dimension(220, 30));
                }
                setDialog.setContentPane(setPanel);
                setDialog.setPreferredSize(new Dimension(220,100));
                setDialog.pack();
                setDialog.setLocationRelativeTo(main.mainFrame);
                setDialog.setVisible(true);
            }
        });

        displayItems(main, priceDialog, pricPanel, setPanel, setButton, priceToSet);

        JButton doneButton = new JButton("DONE");
        doneButton.setPreferredSize(new Dimension(220, 30));
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                priceDialog.dispose();
                itemClickedDialog.dispose();
                maintenanceFeatures(main);
                main.mainFrame.revalidate();
                main.mainFrame.repaint();
            }
        });
        mainPanel.add(pricPanel);
        mainPanel.add(doneButton);
        mainPanel.setPreferredSize(new Dimension(400, 100));
        priceDialog.setContentPane(mainPanel);
        priceDialog.pack();
        priceDialog.setLocationRelativeTo(main.mainFrame);
        priceDialog.setVisible(true);
    }

    protected void setItemPrice() {
        displayItems(itemSlots);
        System.out.println("[0] - Exit ");
        System.out.println("Enter item to change price");
        System.out.print(">> ");
        int index = getUserInput();

        if (index == 0) {
            System.out.println("Cancelled");
            return;
        }

        if (index < 0 || index > itemSlots.size()) {
            System.out.println("Invalid item");
            return;
        }

        System.out.println("Enter new price");
        double price = getUserInput();

        Item pickedItem = itemSlots.get(index-1);
        pickedItem.setPrice(price);
        System.out.println("Price changed successfully");
    }
    /*
        Description: Collects the specified amount of money from the vending machine.
        Return Value: void
    */
    
    protected void collectMoney(Main main) {
        main.mainFrame.getContentPane().removeAll();

        JDialog collectDialog = new JDialog(main.mainFrame, "COLLECTING");
        collectDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel collectPanel = new JPanel();
        JLabel earnLabel = new JLabel("Total Earnings");
        JLabel salesVal = new JLabel(Double.toString(totalSales));
        JTextField collectField = new JTextField(15);
        collectPanel.add(earnLabel);
        collectPanel.add(salesVal);
        collectPanel.add(collectField);

        JButton collectButton = new JButton("Collect Money");
        collectButton.setPreferredSize(new Dimension(150, 30));
        collectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JDialog collectDialog = new JDialog(main.mainFrame);
                JLabel collectLabel = new JLabel();
                JPanel collectPanel = new JPanel();
                collectDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                double temp, collect;
                collect = Double.parseDouble(collectField.getText());
                temp = totalSales - Double.parseDouble(collectField.getText());
                if(!(collect > 0 && collect <= totalSales)){
                    collectDialog.setTitle("WARNING");
                    collectLabel = new JLabel("Not Enough Value");
                    collectPanel = new JPanel();
                    collectPanel.add(collectLabel);
                    collectPanel.setPreferredSize(new Dimension(220, 30));
                }
                else{
                    double i = collect;
                    collectDialog.setTitle("COLLECTING MONEY");
                    for(int denomination : denominations){
                        while(i >= denomination){
                            collectLabel = new JLabel(Integer.toString(denomination));
                            collectPanel = new JPanel();
                            collectPanel.add(collectLabel);
                            i-=denomination;
                        }
                    }
                    collectLabel = new JLabel("Money Collected");
                    collectPanel = new JPanel();
                    collectPanel.add(collectLabel);
                    collectLabel = new JLabel(String.valueOf(totalSales));
                    collectPanel.add(collectLabel);
                    totalSales -= collect;
                }
                collectDialog.setContentPane(collectPanel);
                collectDialog.setPreferredSize(new Dimension(220,100));
                collectDialog.pack();
                collectDialog.setLocationRelativeTo(main.mainFrame);
                collectDialog.setVisible(true);
            }
        });
        collectPanel.add(collectButton);

        collectDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e){
                maintenanceFeatures(main);
                main.mainFrame.revalidate();
                main.mainFrame.repaint();
            }
        });
        collectDialog.setContentPane(collectPanel);
        collectDialog.setPreferredSize(new Dimension(220,150));
        collectDialog.pack();
        collectDialog.setLocationRelativeTo(main.mainFrame);
        collectDialog.setVisible(true);
    }
    
    protected void collectMoney() {
        double collect;
        System.out.println("Total Earnings: " + totalSales);
        System.out.println("Enter value you want to collect.");
        System.out.print(">> ");
        collect = getUserInput();
        System.out.println("Collecting Money...");

        if (!(collect > 0 && collect <= totalSales)) {
            System.out.println("Not enough value, try again later..\n");
            return;
        }

        double i = collect;
        for (int denomination : denominations) {
            while (i >= denomination) {
                System.out.println(denomination);
                i -= denomination;
            }
        }
        System.out.println("Successfully collected Php." + collect);
        totalSales -= collect;
    }
    /*
        Description: Replenishes the change in the vending machine by inserting the specified amount of money.
        Return Value: void
    */
    
    protected void replenishChange(Main main){
        main.mainFrame.getContentPane().removeAll();
        double updateTotalChange = totalChange;
        mainDialog = new JDialog(main.mainFrame, "REPLENISHING");
        mainDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel repPanel = new JPanel();
        JLabel repLabel = new JLabel("Enter Value of Change");
        JTextField repField = new JTextField(15);
        repPanel.add(repLabel);
        repPanel.add(repField);

        JButton repButton = new JButton("Replenish Change");
        repButton.setPreferredSize(new Dimension(150, 30));
        repButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JDialog repDialog = new JDialog(main.mainFrame);
                JLabel repLabel = new JLabel();
                JPanel repPanel = new JPanel();
                repDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                double temp;
                temp = Double.parseDouble(repField.getText());
                if(temp>0){
                    repDialog.setTitle("REPLENISHING CHANGE");
                    totalChange += temp; 
                    repLabel = new JLabel("Total Change");
                    JLabel repVal = new JLabel(String.valueOf(totalChange));
                    repPanel = new JPanel();
                    repPanel.add(repLabel);
                    repPanel.add(repVal);
                    repPanel.setPreferredSize(new Dimension(220, 30));
                    mainDialog.dispose();
                }
                else{
                    repDialog.setTitle("WARNING");
                    repLabel = new JLabel("Invalid Price");
                    repPanel = new JPanel();
                    repPanel.add(repLabel);
                    repPanel.setPreferredSize(new Dimension(220, 30));
                }

                repDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e){
                        maintenanceFeatures(main);
                        main.mainFrame.revalidate();
                        main.mainFrame.repaint();
                    }    
                });
                repDialog.setContentPane(repPanel);
                repDialog.setPreferredSize(new Dimension(220,100));
                repDialog.pack();
                repDialog.setLocationRelativeTo(main.mainFrame);
                repDialog.setVisible(true);
            }
        });
        repPanel.add(repButton);
        mainDialog.setContentPane(repPanel);
        mainDialog.setPreferredSize(new Dimension(220,150));
        mainDialog.pack();
        mainDialog.setLocationRelativeTo(main.mainFrame);
        mainDialog.setVisible(true);
    }

    protected void replenishChange() {
        double change;
        System.out.println("Enter value of change");
        System.out.print(">> ");
        change = getUserInput();
        System.out.println("Inserting money...");
        if(change > 0) {
            totalChange += change;
        }
        else {
            System.out.println("Something went wrong");
        }
        System.out.println("Total value of change: " + totalChange);
    }
    /*
        Description: Prints the summary of transactions including item names, quantities, and total earnings.
        Return Value: void
    */
    
    protected void printTransactionSummary(Main main){
        double totalEarning = 0;
        JPanel printPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        printPanel.setLayout(new GridLayout(0, 3, 10, 10));
        JLabel printLabel = new JLabel("TRANSACTIONS");
        printPanel.add(printLabel);

        printLabel = new JLabel("Name");
        printPanel.add(printLabel);

        printLabel = new JLabel("Quantity");
        printPanel.add(printLabel);

        printLabel = new JLabel("Price");
        printPanel.add(printLabel);

        for(Transaction transaction : transactions) {
            Item item = transaction.getItem();
            totalEarning += transaction.getTotalPrice();
            printLabel = new JLabel(item.getItemName());
            printPanel.add(printLabel);

            printLabel = new JLabel(String.valueOf(item.getQuantity()));
            printPanel.add(printLabel);

            printLabel = new JLabel(String.valueOf(transaction.getTotalPrice()));
            printPanel.add(printLabel);
        }
        
        printLabel = new JLabel("Total Earnings");
        printPanel.add(printLabel);
        printLabel = new JLabel(String.valueOf(totalEarning));
        printPanel.add(printLabel);
        printPanel.setPreferredSize(new Dimension(220, 200));
        main.mainFrame.add(printPanel);
        main.mainFrame.revalidate();
        main.mainFrame.repaint();
    }

    protected void printTransactionSummary() {
        double totalEarning = 0;
        System.out.println("TRANSACTIONS");
        System.out.println("Name\t\t\t\t\tQuantity\tPrice");
        for(Transaction transaction : transactions) {
            Item item = transaction.getItem();
            System.out.printf("%-30s \t%d\t%.2f\n", item.getItemName(), item.getQuantity(), transaction.getTotalPrice());
            totalEarning += transaction.getTotalPrice();
            System.out.println("TOtal Earning: "+totalEarning);
        }
        System.out.println("Total Earnings: " + totalEarning);
    }
    /*
        Description: Prints the summary of transactions including item names, quantities, and total earnings.
        Parameters:
            @param items - The list of items to be displayed.
        Return Value: void
    */
    
    protected void displayItems(Main main, JDialog displayDialog, JPanel itemsPanel, JPanel addPanel, JButton addButton, List<Item> setItem){
        itemsPanel.setLayout(new GridLayout(0, 3, 10, 10));
        itemClickedDialog.setLocationRelativeTo(displayDialog);
        
        for(Item items : setItem){
            JButton itemButton = new JButton(items.getItemName());
            itemButton.setPreferredSize(new Dimension(220, 30));
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    itemClicked = items;
                    itemClickedDialog.setTitle(items.getItemName());
                    itemClickedDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    
                    JPanel panel = new JPanel(new GridLayout(4, 2));

                    JLabel calories = new JLabel("Calories");
                    panel.add(calories);
                    panel.setPreferredSize(new Dimension(100, 30));
                    JLabel calValue = new JLabel(String.valueOf(items.getCalories()));
                    panel.add(calValue);
                    panel.setPreferredSize(new Dimension(100, 30));
                    
                    JLabel price = new JLabel("Price");
                    panel.add(price);
                    panel.setPreferredSize(new Dimension(100, 30));
                    JLabel priceValue = new JLabel(String.valueOf(items.getPrice()));
                    panel.add(priceValue);
                    panel.setPreferredSize(new Dimension(100, 30));

                    JLabel quantity = new JLabel("Quantity");
                    panel.add(quantity);
                    panel.setPreferredSize(new Dimension(100, 30));
                    JLabel quaValue = new JLabel(String.valueOf(items.getQuantity()));
                    panel.add(quaValue);
                    panel.setPreferredSize(new Dimension(100, 30));
                    
                    itemClickedDialog.setContentPane(panel);
                    itemClickedDialog.add(addPanel);
                    itemClickedDialog.add(addButton);
                    itemClickedDialog.setPreferredSize(new Dimension(500, 250));
                    itemClickedDialog.pack();
                    itemClickedDialog.setLocationRelativeTo(main.mainFrame);
                    itemClickedDialog.setVisible(true);
                }
            });
            itemsPanel.add(itemButton);
        }
    }

    protected void itemClickedDialogListener(){
        itemClickedDialog.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                itemClickedDialog.removeAll();
            }
        });
    }
    protected void displayItems(List<Item> items) {
        System.out.println("ITEMS");
        System.out.println("Index\tName\t\t\t\t\tPrice\tCalorie\tQuantity");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.printf("%-6d %-30s\t%.2f\t%.2f\t%d\n", (i + 1), item.getItemName(), item.getPrice(),
                    item.getCalories(),item.getQuantity());
        }
        System.out.println();
    }
    /*
        Description: Retrieves user input from the console.
        Return Value: The user input as an integer.
    */
    protected int getUserInput() {
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                input = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        scanner.nextLine(); // Clear the scanner buffer
        return input;
    }
    protected void addComponent(JLabel label, JTextField field) {
        JLabel addComponentLabel = label;
        JTextField addCompField = field;

        itemClickedPanel.add(addComponentLabel);
        itemClickedPanel.add(addCompField);
        itemClickedDialog.add(itemClickedPanel);
    }
    protected void calculate(Main main, double price){
        double calories = 0;
        price = 0;
        int i =0;
        for(Item items : pickedItems){
            calories += items.getCalories() * pickedItemQuantity.get(i);
            price += items.getPrice() * pickedItemQuantity.get(i);
            i++;
        }
        JPanel calcuPanel = new JPanel();
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
                double Price = 0;
                int i = 0;
                for(Item item : pickedItems){
                    Price += item.getPrice() * pickedItemQuantity.get(i);
                    i++;
                }
                transaction(main, Price);   
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                vendingMachineFeatures(main);
            }
        });

        main.mainFrame.add(calcuPanel);
        main.mainFrame.add(payButton);
        main.mainFrame.add(cancelButton);
        main.mainFrame.revalidate();
        main.mainFrame.repaint();
    }

    protected void transaction(Main main, double price){
        JDialog transacDialog = new JDialog(main.mainFrame, "TRANSACTION");

        JPanel rpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel rpLabel = new JLabel();
        rpLabel = new JLabel("Total Balance");
        rpPanel.add(rpLabel);
        rpPanel.setPreferredSize(new Dimension(220, 30));
        rpLabel = new JLabel(String.valueOf(balance));
        rpPanel.add(rpLabel);
        rpPanel.setPreferredSize(new Dimension(220, 30));
        rpLabel = new JLabel("Insert money [20 / 50 / 100 / 200 / 500 /1000]");
        rpPanel.add(rpLabel);
        rpPanel.setPreferredSize(new Dimension(220, 30));
        main.mainFrame.add(rpPanel);
        
        JTextField rpField = new JTextField(15);
        main.mainFrame.add(rpField);
        JDialog rpDialog = new JDialog(main.mainFrame);
        JButton enterButton = new JButton("ENTER AMOUNT");
        enterButton.setPreferredSize(new Dimension(220, 30));
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JLabel rpLabel = new JLabel();

                if(!(Integer.parseInt(rpField.getText())==20||
                Integer.parseInt(rpField.getText())==50||
                Integer.parseInt(rpField.getText())==100||
                Integer.parseInt(rpField.getText())==200||
                Integer.parseInt(rpField.getText())==500||
                Integer.parseInt(rpField.getText())==1000)){
                    rpDialog.setTitle("WARNING");
                    rpDialog.setSize(220, 100);
                    JLabel warnLabel = new JLabel("Invalid Money Value!");
                    warnLabel.setPreferredSize(new Dimension(220, 30));
                    rpDialog.setContentPane(warnLabel);
                    rpDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    rpDialog.pack();
                    rpDialog.setLocationRelativeTo(main.mainFrame);
                    rpDialog.setVisible(true);
            }
            else{
                balance += Double.parseDouble(rpField.getText());
                if(balance < price){
                    rpDialog.setTitle("WARNING");
                    rpDialog.setSize(220, 100);
                    JLabel warnLabel = new JLabel("Insufficient Money!");
                    warnLabel.setPreferredSize(new Dimension(220, 30));
                    rpDialog.setContentPane(warnLabel);
                    rpDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    rpDialog.pack();
                    rpDialog.setLocationRelativeTo(main.mainFrame);
                    rpDialog.setVisible(true);
                }
                else{
                    totalChange -= balance;
                    totalSales += price;
                    rpLabel = new JLabel("Computing change");
                    rpPanel.add(rpLabel);
                    rpPanel.setPreferredSize(new Dimension(220, 30));
                    balance -= price;

                    rpLabel = new JLabel("Total Change");
                    rpPanel.add(rpLabel);
                    rpPanel.setPreferredSize(new Dimension(220, 30));

                    rpLabel = new JLabel(String.valueOf(balance));
                    rpPanel.add(rpLabel);
                    rpPanel.setPreferredSize(new Dimension(220, 30));

                    rpLabel = new JLabel("Vending Machine Credits");
                    rpPanel.add(rpLabel);
                    rpPanel.setPreferredSize(new Dimension(220, 30));
                    
                    rpLabel = new JLabel(String.valueOf(totalChange));
                    rpPanel.add(rpLabel);
                    rpPanel.setPreferredSize(new Dimension(220, 30));
                    int i = 0;
                    for(Item item : pickedItems){
                        transactions.add(new Transaction(new Item(item.getItemName(), pickedItemQuantity.get(i)), price));    
                        System.out.println("PPrice: "+price);
                        item.setQuantity(item.getQuantity() - item.getQuantity());
                        i++;
                    }

                    rpLabel = new JLabel("Transaction Completed");
                    rpPanel.add(rpLabel);
                    rpPanel.setPreferredSize(new Dimension(220, 30));     
                    
                    JButton doneButton = new JButton("DONE");
                    doneButton.setPreferredSize(new Dimension(220, 30));
                    doneButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            transacDialog.dispose();
                        }
                    });
                    transacDialog.addWindowListener(new WindowAdapter(){
                        @Override
                        public void windowClosed(WindowEvent e){
                            testVendingMachine(main);
                            main.mainFrame.revalidate();
                            main.mainFrame.repaint();
                        }
                    });
                    transacDialog.add(doneButton);
                    transacDialog.revalidate();
                    transacDialog.repaint();
                }
                
            }
            }
        }); 

        transacDialog.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                JButton okButton = new JButton("OK");
                okButton.setPreferredSize(new Dimension(220, 30));

                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        testVendingMachine(main);
                        main.mainFrame.revalidate();
                        main.mainFrame.repaint();
                    }
                });
                main.mainFrame.add(okButton);
            }
        });
        transacDialog.setContentPane(rpPanel);
        transacDialog.add(rpField);
        transacDialog.add(enterButton);
        transacDialog.setPreferredSize(new Dimension(250, 500));
        transacDialog.pack();
        transacDialog.setLocationRelativeTo(main.mainFrame);
        transacDialog.setVisible(true);
    }
}
