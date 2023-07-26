import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
    protected int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
    protected int capacity;
    protected int slot;   //Specifies the slot of vending machine
    protected int balance;
    protected JTextField nameField, slotField, maxField;

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
        vmFeatureButton.setPreferredSize(new Dimension(250, 50));
        vmFeatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                vendingMachineFeatures(main);
                main.mainFrame.revalidate();
                main.mainFrame.repaint();
            }
        });

        JButton mFeatureButton = new JButton("Maintenance Features");
        mFeatureButton.setPreferredSize(new Dimension(250, 50));
        mFeatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
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
                        main.mainFrame.getContentPane().removeAll();
                        JPanel panel = new JPanel();
                        JLabel itemLabel = new JLabel("CHOOSE ITEM TO RESTOCK");
                        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                        panel.add(itemLabel);
                        panel.setPreferredSize(new Dimension(220, 30));
                        main.mainFrame.add(panel);
                        
                        JPanel itemsPanel = new JPanel();
                        itemsPanel.setLayout(new GridLayout(0, 3, 10, 10));
                        
                        for(Item items : itemSlots){
                            JButton itemButton = new JButton(items.getItemName());
                            itemButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    JDialog itemDialog = new JDialog(main.mainFrame, items.getItemName());
                                    itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                                    
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

                                    JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                    JLabel quantityLabel = new JLabel("Enter Quantity");
                                    JTextField quantityField = new JTextField(15);
                                    quantityPanel.add(quantityLabel);
                                    quantityPanel.add(quantityField);
                                    quantityPanel.setPreferredSize(new Dimension(220, 30));
                                    
                                    JButton resToCartButton = new JButton("Restock");
                                    resToCartButton.setPreferredSize(new Dimension(150, 30));
                                    resToCartButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e){
                                            JDialog resDialog = new JDialog(main.mainFrame);
                                            JLabel resLabel = new JLabel();
                                            JPanel resPanel = new JPanel();
                                            resDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                                            int temp;
                                            temp = items.getQuantity() + Integer.parseInt(quantityField.getText());
                                            if(temp<=capacity){
                                                
                                                resDialog.setTitle("RESTOCKING");
                                                items.setQuantity(temp);   
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
                                    panel.add(resToCartButton);
                                    panel.setPreferredSize(new Dimension(220,30));

                                    itemDialog.add(panel, BorderLayout.CENTER);
                                    itemDialog.add(quantityPanel, BorderLayout.AFTER_LINE_ENDS);
                                    itemDialog.setPreferredSize(new Dimension(500, 150));
                                    itemDialog.pack();
                                    itemDialog.setLocationRelativeTo(main.mainFrame);;
                                    itemDialog.setVisible(true);
                                }
                            });
                            itemsPanel.add(itemButton);
                        }

                        main.mainFrame.add(itemsPanel);
                        
                        JButton restockAll = new JButton("Restock All");
                        restockAll.setPreferredSize(new Dimension(220, 30));
                        restockAll.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e){
                                JDialog itemDialog = new JDialog(main.mainFrame,"ALL ITEMS");
                                itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                                JLabel quantityLabel = new JLabel("Enter Quantity");
                                JTextField quantityField = new JTextField(15);
                                JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                panel.add(quantityLabel);
                                panel.add(quantityField);
                                panel.setPreferredSize(new Dimension(220, 30));

                                JButton resToCartButton = new JButton("Restock");
                                resToCartButton.setPreferredSize(new Dimension(150, 30));
                                resToCartButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    JDialog resDialog = new JDialog(main.mainFrame);
                                    JLabel resLabel = new JLabel();
                                    JPanel resPanel = new JPanel();
                                    resDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                                    for(Item items : itemSlots){
                                        int temp;
                                        temp = items.getQuantity() + Integer.parseInt(quantityField.getText());

                                        if(temp<=capacity){
                                        resDialog.setTitle("RESTOCKING");
                                        items.setQuantity(temp); 
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
                                    }
                                    resDialog.setContentPane(resPanel);
                                    resDialog.setPreferredSize(new Dimension(220, 100));
                                    resDialog.pack();
                                    resDialog.setLocationRelativeTo(main.mainFrame);
                                    resDialog.setVisible(true);
                                }
                            });
                            panel.add(resToCartButton);
                            panel.setPreferredSize(new Dimension(220,30));

                            itemDialog.setContentPane(panel);
                            itemDialog.setPreferredSize(new Dimension(500, 150));
                            itemDialog.pack();
                            itemDialog.setLocationRelativeTo(main.mainFrame);;
                            itemDialog.setVisible(true);
                            }
                        });

                        JButton doneButton = new JButton("DONE");
                        doneButton.setPreferredSize(new Dimension(220, 30));
                        doneButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e){
                                testVendingMachine(main);
                                main.mainFrame.revalidate();
                                main.mainFrame.repaint();
                            }
                        });
                        main.mainFrame.add(restockAll);
                        main.mainFrame.add(doneButton);
                        main.mainFrame.revalidate();
                        main.mainFrame.repaint();
                    }
                });

                priceButton.setPreferredSize(new Dimension(220, 30));
                priceButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                    }
                });

                collectButton.setPreferredSize(new Dimension(220, 30));
                collectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                    }
                });

                repButton.setPreferredSize(new Dimension(220, 30));
                repButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                    }
                });

                printButton.setPreferredSize(new Dimension(220, 30));
                printButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                    }
                });

                backButton.setPreferredSize(new Dimension(220, 30));
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        testVendingMachine(main);
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
        });

        JButton backButton = new JButton("MainMenu");
        backButton.setPreferredSize(new Dimension(250, 50));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                main.goToMainMenu(main);
            }
        });
        main.mainFrame.add(testPanel);
        main.mainFrame.add(vmFeatureButton);
        main.mainFrame.add(mFeatureButton);
        main.mainFrame.add(backButton);
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
    protected void transaction(){

    }
    /*
        Description: Restocks the specified item in the vending machine.
        Return Value: void
    */
    protected void restockItems(Main main){
        main.mainFrame.getContentPane().removeAll();
        
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
    protected void printTransactionSummary() {
        double totalEarning = 0;
        System.out.println("TRANSACTIONS");
        System.out.println("Name\t\t\t\t\tQuantity\tPrice");
        for(Transaction transaction : transactions) {
            Item item = transaction.getItem();
            System.out.printf("%-30s \t%d\t%.2f\n", item.getItemName(), item.getQuantity(), transaction.getTotalPrice());
            totalEarning += transaction.getTotalPrice();
        }
        System.out.println("Total Earnings: " + totalEarning);
    }
    /*
        Description: Prints the summary of transactions including item names, quantities, and total earnings.
        Parameters:
            @param items - The list of items to be displayed.
        Return Value: void
    */
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
}
