import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/*
    Description: The RegularVendingMachine class represents a regular vending machine that extends the VendingMachine class.
    It provides methods to create and operate a regular vending machine.
*/
public class RegularVendingMachine extends VendingMachine{
    StoredItems storedItems = new StoredItems();
    /*
    Description: Constructs a RegularVendingMachine object.
    Initializes the storedItems by reading an XML file and initializes the transactions list.
    */
    public RegularVendingMachine() {
        super();
        storedItems.readXMLFile("items.xml");
        transactions = new ArrayList<>();
    }
    /*
        Description: Creates a regular vending machine by prompting the user for inputs and configuring the machine.
    */
    @Override
    public void createVendingMachine(Main main) {
        String tempText;
        System.out.println("+------------------------------------------+");
        System.out.println("| CREATE REGULAR VENDING MACHINE           |");
        System.out.println("+------------------------------------------+");
        System.out.println();
        System.out.println("Enter Name for your vending machine");
        System.out.print(">> ");

        main.mainFrame.getContentPane().removeAll();

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel creatLabel = new JLabel("CREATING REGULAR VENDING MACHINE");
        panel.add(creatLabel);
        panel.setPreferredSize(new Dimension(220, 30));
        main.mainFrame.add(panel);

        nameField = new JTextField();
        nameField.setColumns(15);
        JLabel nameLabel = new JLabel("Enter Name");
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.setPreferredSize(new Dimension(250, 30));
        main.mainFrame.add(panel);
        tempText = nameField.getText();

        slotField = new JTextField();
        slotField.setColumns(15);
        JLabel slotLabel = new JLabel("Enter Slot");
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(slotLabel);
        panel.add(slotField);
        panel.setPreferredSize(new Dimension(250, 30));
        main.mainFrame.add(panel);

        maxField = new JTextField();
        maxField.setColumns(15);
        JLabel maxLabel = new JLabel("Enter Capacity");
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(maxLabel);
        panel.add(maxField);
        panel.setPreferredSize(new Dimension(300, 30));
        main.mainFrame.add(panel);

        JButton createButton = new JButton("Create");
        JButton cancelButton = new JButton("Cancel");
        createButton.setPreferredSize(new Dimension(220, 30));
        cancelButton.setPreferredSize(new Dimension(220, 30));


        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.selected = 5;
                String temp;
                name = nameField.getText();
                temp = slotField.getText();
                slot = Integer.parseInt(temp);
                temp = maxField.getText();
                capacity = Integer.parseInt(temp);

                if((slot<8 || slot>10) && (capacity<10 || capacity> 20)){
                    System.out.println("Invalid input");
                    JDialog invalidDialog = new JDialog(main.mainFrame, "Warning");
                    invalidDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    JLabel invalidSlotLabel = new JLabel("Invalid Slot and Capacity Number");
                    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    panel.add(invalidSlotLabel);
                    panel.setPreferredSize(new Dimension(250, 30));
                
                    invalidDialog.setContentPane(panel);
                    invalidDialog.setPreferredSize(new Dimension(300, 150));
                    invalidDialog.pack();
                    invalidDialog.setLocationRelativeTo(main.mainFrame);
                    invalidDialog.setVisible(true);
                }
                else if(slot<8 || slot>10){
                    System.out.println("Invalid input, please enter a value between 8 and 10");
                    JDialog invalidDialog = new JDialog(main.mainFrame, "Warning");
                    invalidDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    JLabel invalidSlotLabel = new JLabel("Invalid Slot Number");
                    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    panel.add(invalidSlotLabel);
                    panel.setPreferredSize(new Dimension(250, 30));
                
                    invalidDialog.setContentPane(panel);
                    invalidDialog.setPreferredSize(new Dimension(300, 150));
                    invalidDialog.pack();
                    invalidDialog.setLocationRelativeTo(main.mainFrame);
                    invalidDialog.setVisible(true);
                }
                else if(capacity<10 || capacity> 20){
                    System.out.println("Invalid input, please enter a value between 10 and 20");
                    JDialog invalidDialog = new JDialog(main.mainFrame, "Warning");
                    invalidDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    
                    JLabel invalidMaxLabel = new JLabel("Invalid Capacity Number");
                    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    panel.add(invalidMaxLabel);
                    panel.setPreferredSize(new Dimension(250, 30));

                    invalidDialog.setContentPane(panel);
                    invalidDialog.setPreferredSize(new Dimension(300, 150));
                    invalidDialog.pack();
                    invalidDialog.setLocationRelativeTo(main.mainFrame);
                    invalidDialog.setVisible(true);
                }
                else{
                    nameField.setEnabled(false);
                    slotField.setEnabled(false);
                    maxField.setEnabled(false);
                    List<Item> itemList = storedItems.getItemsList();
                    System.out.println("Name: "+name+"  Slot: "+slot+"  Capacity: "+capacity);
                    JDialog chooseDialog = new JDialog(main.mainFrame, "CHOOSING ITEMS");
                    chooseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                    JLabel chooseLabel = new JLabel("Choose Item");
                    JPanel panel = new JPanel();
                    panel.add(chooseLabel);
                    panel.setPreferredSize(new Dimension(220, 30));

                    JPanel itemsPanel = new JPanel();
                    storedItems.displayItemsButton(main, chooseDialog, itemSlots, slot);

                    chooseDialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e){
                            JLabel successLabel = new JLabel("Successfully Added"); 
                            JPanel panel = new JPanel();
                            panel.add(successLabel);
                            panel.setPreferredSize(new Dimension(220, 30));

                            
                            main.mainFrame.add(panel);
                            main.mainFrame.add(main.goToMainMenu(main));
                            main.mainFrame.revalidate();
                            main.mainFrame.repaint();
                        }
                    });
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.selected = 6;
                System.out.println("Cancelled");
                JLabel discardLabel = new JLabel("Discarding...");
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                panel.add(discardLabel);
                panel.setPreferredSize(new Dimension(250, 30));
                main.mainFrame.add(panel);
                main.createMenu(main);
            }
        });
        main.mainFrame.add(createButton);
        main.mainFrame.add(cancelButton);

        main.mainFrame.revalidate();
        main.mainFrame.repaint();

        // selected was not initialized in this part meaning the create button nor cancel button was not initialized
        if(!(main.selected==3)) {
            List<Item> itemList = storedItems.getItemsList();
            
           if(!(tempText == null)){
                name = scanner.nextLine();
                tempText = null;
            }
            
            System.out.println("Enter max slots");
            System.out.print(">> ");
            do {
                slot = getUserInput();
                if(slot < 8 || slot > 10) {
                    System.out.println("Invalid input, please enter a value between 8 and 10");
                }
                System.out.print(">> ");
            } while (slot < 8 || slot > 10);
            System.out.println();

            System.out.println("Enter max capacity");
            System.out.print(">> ");
            do {
                capacity = getUserInput();
                if(capacity < 10 || capacity > 20) {
                    System.out.println("Invalid input, please enter a value between 10 and 20");
                }
                System.out.print(">> ");
            } while (capacity < 10 || capacity > 20);

        }
    }

    @Override
    public void createVendingMachine() {
        List<Item> itemList = storedItems.getItemsList();
        System.out.println("+------------------------------------------+");
        System.out.println("| CREATE REGULAR VENDING MACHINE           |");
        System.out.println("+------------------------------------------+");
        System.out.println();
        System.out.println("Enter Name for your vending machine");
        System.out.print(">> ");
        name = scanner.nextLine();

        System.out.println("Enter max slots");
        System.out.print(">> ");
        do {
            slot = getUserInput();
            if(slot < 8 || slot > 10) {
                System.out.println("Invalid input, please enter a value between 8 and 10");
            }
            System.out.print(">> ");
        } while (slot < 8 || slot > 10);
        System.out.println();

        System.out.println("Enter max capacity");
        System.out.print(">> ");
        do {
            capacity = getUserInput();
            if(capacity < 10 || capacity > 20) {
                System.out.println("Invalid input, please enter a value between 10 and 20");
            }
            System.out.print(">> ");
        } while (capacity < 10 || capacity > 20);

        System.out.println();
        System.out.println("Choose products to sell: ");
        storedItems.displayItems();

        for (int i = 0; i < slot; i++) {
            int itemIndex;
            System.out.print(">> ");
            itemIndex = getUserInput();

            if (!(itemIndex >= 0 && itemIndex < itemList.size())) {
                System.out.println("Invalid selection, Please choose a valid item index");
                i--;
                continue;
            }
            Item item = itemList.get(itemIndex);
            if (itemSlots.contains(item)) {
                System.out.println("Invalid selection, Item already exist");
                i--;
                continue;
            }
            itemSlots.add(item);
        }
    }
    /*
        Description: Displays the vending machine features and handles the vending process.
    */
    @Override 
    public void vendingMachineFeatures(Main main){
        main.mainFrame.getContentPane().removeAll();

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel vmNameLabel = new JLabel(name);
        panel.add(vmNameLabel);
        panel.setPreferredSize(new Dimension(220, 30));
        main.mainFrame.add(panel);

        JLabel vfLabel = new JLabel("Vending Machine");
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(vfLabel);
        panel.setPreferredSize(new Dimension(220, 30));
        main.mainFrame.add(panel);

        JLabel productsLabel = new JLabel("PRODUCTS FOR SALE");
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(productsLabel);
        panel.setPreferredSize(new Dimension(220, 30));
        main.mainFrame.add(panel);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(0, 3, 10, 10));

        for(Item items: itemSlots){
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

                    JLabel quantityLabel = new JLabel("Enter Quantity");
                    JTextField quantityField = new JTextField(15);
                    panel.add(quantityLabel);
                    panel.add(quantityField);
                    panel.setPreferredSize(new Dimension(220, 30));
                    
                    JButton addToCartButton = new JButton("Add");
                    addToCartButton.setPreferredSize(new Dimension(150, 30));
                    addToCartButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            JDialog addDialog = new JDialog(main.mainFrame);
                            JLabel addLabel = new JLabel();
                            JPanel addPanel = new JPanel();
                            addDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                            if(items.getQuantity()>=Integer.parseInt(quantityField.getText())){
                                int temp;
                                temp = items.getQuantity() - Integer.parseInt(quantityField.getText());

                                items.setQuantity(temp);
                                addLabel = new JLabel("Added to Cart");
                                addPanel = new JPanel();
                                addPanel.add(addLabel);
                                addPanel.setPreferredSize(new Dimension(220, 30));
                            }
                            else if(items.getQuantity()==0){
                                addLabel = new JLabel("Out of Stock");
                                addPanel = new JPanel();
                                addPanel.add(addLabel);
                                addPanel.setPreferredSize(new Dimension(220, 30));
                            }
                            else{
                                addLabel = new JLabel("Doesn't have enough stock");
                                addPanel = new JPanel();
                                addPanel.add(addLabel);
                                addPanel.setPreferredSize(new Dimension(220, 30));
                            }
                            addDialog.setContentPane(addPanel);
                            addDialog.setPreferredSize(new Dimension(220, 100));
                            addDialog.pack();
                            addDialog.setLocationRelativeTo(main.mainFrame);
                            addDialog.setVisible(true);
                        }
                    });
                    panel.add(addToCartButton);
                    panel.setPreferredSize(new Dimension(220,30));

                    itemDialog.setContentPane(panel);
                    itemDialog.setPreferredSize(new Dimension(500, 300));
                    itemDialog.pack();
                    itemDialog.setLocationRelativeTo(main.mainFrame);;
                    itemDialog.setVisible(true);
                }
            });
            itemsPanel.add(itemButton);
        }

        

        main.mainFrame.add(itemsPanel);

        JButton proceedButton = new JButton("PROCEED");
        proceedButton.setPreferredSize(new Dimension(220, 30));
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                main.vm.transaction();
            }
        });
        main.mainFrame.add(proceedButton);

    }

    @Override
    public void vendingMachineFeatures() {
        int choice, quantity;
        Item pickedItem;
        System.out.println();
        while (true) {
            //Display's Vending Machine Name
            System.out.println(name + " Vending Machine\n");

            //Display Products
            System.out.println("Products for sale: ");
            displayItems(itemSlots);
            System.out.println("[0] - Exit");
            System.out.println("Choose an item.");
            System.out.print(">> ");

            //Check if choice is valid
            choice = getUserInput();

            if (choice == 0) {
                System.out.println("Transaction Cancelled");
                break;
            }

            if (choice < 0 || choice > slot) {
                System.out.println("Invalid choice.");
                continue;
            }

            pickedItem = itemSlots.get(choice - 1);

            if (pickedItem.getQuantity() <= 0) {
                System.out.println(pickedItem.getItemName() + " is out of stock");
                continue;
            }

            System.out.println("Name " + pickedItem.getItemName());

            while (true) {
                System.out.print("Enter quantity: ");
                quantity = getUserInput();

                if (quantity <= pickedItem.getQuantity()) {
                    break;
                }

                System.out.println(pickedItem.getItemName() + " doesn't have enough stock...");
                System.out.println("Type 0 to cancel");
            }

            if (!(quantity > 0 && quantity <= pickedItem.getQuantity())) {
                System.out.println("Transaction cancelled");
            }

            //COMPUTING
            double amount, price, calories;

            //CALCULATE TOTAL CALORIES AND PRICE
            calories = pickedItem.getCalories() * quantity;
            price = pickedItem.getPrice() * quantity;
            System.out.println();
            System.out.println("Total amount of calorie: " + calories);
            System.out.println("Total amount: " + price);
            System.out.println("Type 0 to cancel.");

            //ENTER MONEY
            while (true) {
                System.out.println("Total balance: " + balance);
                System.out.println("Insert money [20 / 50 / 100 / 200 / 500 / 1000]");
                System.out.println("Enter 0 if done inserting...");
                System.out.print(">> ");
                amount = getUserInput();
                if (amount == 20 || amount == 50 || amount == 100 || amount == 200 || amount == 500 || amount == 1000) {
                    balance += amount;
                } else if (amount == 0) {
                    break;
                } else {
                    System.out.println("Invalid money value! Please insert a valid amount.");
                }
            }

            if (balance < price) {
                System.out.println("Insufficient Money. Transaction cancelled.");
                continue;
            }

            System.out.println("Computing change");
            balance -= price;
            System.out.println("Total change: " + balance);
            //Transaction cancels if vending machine doesn't have enough change
            if(totalChange < balance) {
                System.out.println("Vending Machine doesn't have enough change...");
                System.out.println("We're sorry for the inconvenience");
                System.out.println("Cancelling transaction.");
                System.out.println("Handling balance back...");
                balance += price;
                for (int denomination : denominations) {
                    while (balance >= denomination) {
                        System.out.println(denomination);
                        balance -= denomination;
                    }
                }
                break;
            }
            //If Vending machine has enough change.
            totalChange -= balance;
            totalSales += price;

            System.out.println("Handling changes...");
            for (int denomination : denominations) {
                while (balance >= denomination) {
                    balance -= denomination;
                    System.out.println(denomination);
                }
            }
            System.out.println("Vending Machine credits: " + totalChange);
            transactions.add(new Transaction(new Item(pickedItem.getItemName(), quantity), price));
            pickedItem.setQuantity(pickedItem.getQuantity() - quantity);
            System.out.println("Done...");
            System.out.println("Transaction Complete.");
        }
    }
}