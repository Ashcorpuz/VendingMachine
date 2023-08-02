import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
    Description: The SpecialVendingMachine class represents a special vending machine that extends the VendingMachine class.
    It provides additional features specific to the special vending machine, such as flavors and customizable items.
*/
public class SpecialVendingMachine extends VendingMachine{
    StoredItems storedItems = new StoredItems();
    StoredItems flavors = new StoredItems();
    
    List<SpecialItem> iceCream;
    CustomizableItem customizableItem = new CustomizableItem();
    SpecialItem pickedIceCream;
    List<Item> pickedFlavors;
    List<Item> pickedChocolates;
    List<Item> pickedItem;
    Item flavor, chocolate;
    /*
        Description: Constructs a new SpecialVendingMachine object. Initializes the scanner,
        reads the XML files for stored items and flavors, and initializes the transactions and myFlavor lists.
    */
    public SpecialVendingMachine() {
        super();
        scanner = new Scanner(System.in);
        customizableItem.readXMLFile("variations.xml");
        storedItems.readXMLFile("items.xml");
        flavors.readXMLFile("flavors.xml");
        transactions = new ArrayList<>();
        myFlavor = flavors.getItemsList();
        myChocolate = storedItems.getItemsList();
        iceCream = new ArrayList<>();
        pickedIceCream = null;
        pickedItem = new ArrayList<>();
        pickedChocolates =  new ArrayList<>();
        pickedFlavors = new ArrayList<>();
    }
    /*
        Description: Overrides the createVendingMachine method in the parent class.
        Allows the user to create a special vending machine by setting its name, slot capacity, and product selection.
    */
    @Override
    public void createVendingMachine(Main main) {
        vmType = 2;
        List<SpecialItem> customItemList = customizableItem.getItemsList();
        List<Item> itemList = storedItems.getItemsList();
        String tempText;

        main.mainFrame.getContentPane().removeAll();
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        JLabel creatLabel = new JLabel("CREATING SPECIAL VENDING MACHINE");
        panel.add(creatLabel);
        main.mainFrame.add(panel);

        nameField = new JTextField();
        nameField.setColumns(15);
        JLabel nameLabel = new JLabel("Enter Name");
        panel.add(nameLabel);
        panel.add(nameField);
        main.mainFrame.add(panel);
        tempText = nameField.getText();

        slotField = new JTextField();
        slotField.setColumns(15);
        JLabel slotLabel = new JLabel("Enter Slot");
        panel.add(slotLabel);
        panel.add(slotField);
        main.mainFrame.add(panel);

        maxField = new JTextField();
        maxField.setColumns(15);
        JLabel maxLabel = new JLabel("Enter Capacity");
        panel.add(maxLabel);
        panel.add(maxField);
        

        JButton createButton = new JButton("Create");
        JButton cancelButton = new JButton("Cancel");
        createButton.setPreferredSize(new Dimension(220, 30));
        cancelButton.setPreferredSize(new Dimension(220, 30));

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String temp;
                name = nameField.getText();
                temp = slotField.getText();
                slot = Integer.parseInt(temp);
                temp = maxField.getText();
                capacity = Integer.parseInt(temp);

                if((slot<1 || slot>10) && (capacity<10 || capacity> 20)){
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
                else if(slot<1 || slot>10){
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

                    List<SpecialItem> itemList = customizableItem.getItemsList();
                    JDialog chooseDialog = new JDialog(main.mainFrame, "CHOOSING ITEMS");
                    chooseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    chooseDialog.setPreferredSize(new Dimension(700, 500));

                    JLabel chooseLabel = new JLabel("Choose Item");
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridLayout(0, 1, 10, 10));
                    panel.add(chooseLabel);

                    JPanel itemsPanel = new JPanel();
                    customizableItem.displayItemsButton(main, chooseDialog, iceCream, slot, itemSlots);

                    chooseDialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e){
                            JLabel successLabel = new JLabel("Successfully Added"); 
                            JPanel panel = new JPanel();
                            panel.setLayout(new GridLayout(0, 1, 10, 10));
                            panel.add(successLabel);
                            panel.setPreferredSize(new Dimension(220, 30));
                            panel.add(main.goToMainMenu(main));
                            main.mainFrame.add(panel);
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
        
        panel.add(createButton);
        panel.add(cancelButton);
        main.mainFrame.add(panel);
        main.mainFrame.revalidate();
        main.mainFrame.repaint();
    }
    @Override
    public void createVendingMachine() {
        List<SpecialItem> customItemList = customizableItem.getItemsList();
        List<Item> itemList = storedItems.getItemsList();
        System.out.println("+------------------------------------------+");
        System.out.println("| CREATE SPECIAL VENDING MACHINE           |");
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
        customizableItem.displayItems();

        for (int i = 0; i < slot; i++) {
            int itemIndex, temp;
            System.out.print(">> ");
            itemIndex = getUserInput();

            if (!(itemIndex >= 0 && itemIndex < customItemList.size())) {
                System.out.println("Invalid selection, Please choose a valid item index");
                i--;
                continue;
            }
            SpecialItem customItem = customItemList.get(itemIndex-1);
            iceCream.add(customItem);
            String tempName = customItem.getItemName();
            int tempQuantity = customItem.getQuantity();
            double tempPrice = customItem.getTotalPrice();
            double tempCalories = customItem.getTotalCalories();
            Item item = new Item(tempName, tempQuantity, tempPrice, tempCalories);

            if (itemSlots.contains(item)) {
                System.out.println("Invalid selection, Item already exist");
                i--;
                continue;
            }
            itemSlots.add(item);
        }
    }
    /*
        Description: Overrides the vendingMachineFeatures method in the parent class.
        Provides additional features specific to the special vending machine, such as flavor selection and toppings.
    */
    @Override 
    public void vendingMachineFeatures(Main main){
        int choice, variation, temp;

        main.mainFrame.getContentPane().removeAll();

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        JLabel vmNameLabel = new JLabel(name);
        panel.add(vmNameLabel);
        panel.setPreferredSize(new Dimension(220, 30));
        main.mainFrame.add(panel);

        JLabel vfLabel = new JLabel("Vending Machine");
        panel.add(vfLabel);
        main.mainFrame.add(panel);

        JLabel productsLabel = new JLabel("PRODUCTS FOR SALE");
        panel.add(productsLabel);
        main.mainFrame.add(panel);

        for(SpecialItem items : iceCream){
            JButton itemButton = new JButton(items.getItemName());
            itemButton.addActionListener(new ActionListener() {
                @Override 
                public void actionPerformed(ActionEvent e){
                    mainDialog = new JDialog(main.mainFrame, items.getItemName());
                    mainDialog.setPreferredSize(new Dimension(300, 200));

                    JPanel panel = new JPanel(new GridLayout(5, 2));

                    JLabel itemLabel = new JLabel("CUPS");
                    panel.add(itemLabel);
                    panel.setPreferredSize(new Dimension(100, 30));
                    itemLabel = new JLabel(String.valueOf(items.getCups()));
                    panel.add(itemLabel);

                    itemLabel = new JLabel("CHOCOLATES");
                    panel.add(itemLabel);
                    panel.setPreferredSize(new Dimension(100, 30));
                    itemLabel = new JLabel(String.valueOf(items.getNoChocolates()));
                    panel.add(itemLabel);

                    itemLabel = new JLabel("FLAVORS");
                    panel.add(itemLabel);
                    panel.setPreferredSize(new Dimension(100, 30));
                    itemLabel = new JLabel(String.valueOf(items.getNoFlavors()));
                    panel.add(itemLabel);
                    panel.setPreferredSize(new Dimension(200, 100));

                    pickedIceCream = new SpecialItem(items.getItemName(), items.getQuantity(), items.getCups(), items.getNoFlavors(), items.getNoChocolates());
                    
                    JButton choose = new JButton("CHOOSE");
                    choose.setPreferredSize(new Dimension(220, 30));
                    choose.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            JDialog customizeDialog = new JDialog(mainDialog, "CUSTOMIZING");
                            customizeDialog.setPreferredSize(new Dimension(300, 200));
                            JPanel customizePanel = new JPanel();
                            JLabel customizeLabel = new JLabel("Customizing ");
                            customizePanel.add(customizeLabel);
                            customizeLabel = new JLabel(items.getItemName());
                            customizePanel.add(customizeLabel);

                            JButton chocButton = new JButton("CHOOSE CHOCOLATES");
                            JButton flavButton = new JButton("CHOOSE FLAVORS");
                            chocButton.setPreferredSize(new Dimension(220, 30));
                            flavButton.setPreferredSize(new Dimension(220, 30));

                            flavButton.addActionListener(new ActionListener() {
                                @Override 
                                public void actionPerformed(ActionEvent e){
                                    JDialog itemDialog = new JDialog(main.mainFrame, items.getItemName());
                                    itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);   
                                    flavors.displayItemsButton(main, itemDialog, pickedIceCream.flavors, pickedIceCream.getNoFlavors(), items.getNoFlavors(),myFlavor, pickedFlavors.size());
                                    for(Item item : pickedFlavors)
                                    pickedIceCream.addFlavors(item);
                            
                                    flavButton.setEnabled(false);
                                    itemDialog.setPreferredSize(new Dimension(700, 500));
                                    itemDialog.pack();
                                    itemDialog.setLocationRelativeTo(main.mainFrame);
                                    itemDialog.setVisible(true);
                                }
                            });

                            chocButton.addActionListener(new ActionListener() {
                                @Override 
                                public void actionPerformed(ActionEvent e){
                                    JDialog itemDialog = new JDialog(main.mainFrame, items.getItemName());
                                    itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);   

                                    storedItems.displayItemsButton(main, itemDialog, pickedIceCream.chocolates, pickedIceCream.getNoChocolates(), items.getNoChocolates(), myChocolate, pickedIceCream.chocolates.size());
                                    pickedChocolates = pickedIceCream.getChocolates();
                                //    for(Item item : pickedChocolates)
                                //    pickedIceCream.addChocolates(item);
                                    
                                    chocButton.setEnabled(false);
                                    itemDialog.setPreferredSize(new Dimension(700, 500));
                                    itemDialog.pack();
                                    itemDialog.setLocationRelativeTo(main.mainFrame);
                                    itemDialog.setVisible(true);
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
                                                transaction(main, Price);   
                                            }
                                        });

                                        cancelButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e){
                                                vendingMachineFeatures(main);
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
                            
                            
                            customizeDialog.setContentPane(customizePanel);
                            customizeDialog.add(chocButton);
                            customizeDialog.add(flavButton);    
                            customizeDialog.add(proceedButton);
                            customizeDialog.setLocationRelativeTo(mainDialog);
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

                    mainDialog.setContentPane(panel);
                    mainDialog.add(choose);
                    mainDialog.add(cancel);
                    mainDialog.pack();
                    mainDialog.setLocationRelativeTo(main.mainFrame);
                    mainDialog.setVisible(true);
                }
            });
            main.mainFrame.add(itemButton);
            main.mainFrame.revalidate();
            main.mainFrame.repaint();
        }
    
    }
    @Override
    public void vendingMachineFeatures() {
        int choice, variation, temp;
        List<Item> pickedItem = new ArrayList<>();
        Item flavor, chocolate;
        List<Item> pickedFlavors = new ArrayList<>();
        List<Item> pickedChocolates =  new ArrayList<>();

        System.out.println();
        while (true) {
            //Display's Vending Machine Name
            System.out.println(name + " Vending Machine\n");

            System.out.println("Choose Ice Cream: ");
            System.out.println("Item Name           |   Cups    |   Chocolates  |   Flavors");
            for(temp=0; temp<iceCream.size(); temp++) {
                System.out.print((temp+1)+". ");
                iceCream.get(temp).displayIceCream();
            }
            System.out.println("[0] - Exit");
            System.out.println("Choose an item.");
            System.out.print(">> ");

            variation = getUserInput();
            if (variation == 0) {
                System.out.println("Transaction Cancelled");
                break;
            }

            if (variation < 0 || variation > iceCream.size()) {
                System.out.println("Invalid choice.");
                continue;
            }

            pickedIceCream = iceCream.get(variation-1);

            System.out.println("Choose a flavor: ");
            System.out.println("[Index]\tFlavor\t\t\t\t\tPrice\tCalorie\tQuantity");
            displayItems(myFlavor);
            System.out.println("[0] - Exit");
            for(temp=0; temp<iceCream.get(variation-1).getNoFlavors(); temp++) {
                System.out.println("Choose an item.");
                System.out.print(">> ");
                choice = getUserInput();
                if (choice == 0) {
                    System.out.println("Transaction Cancelled");
                    break;
                }

                if (choice < 0 || choice > myFlavor.size()) {
                    System.out.println("Invalid choice.");
                    continue;
                }

                flavor = myFlavor.get(choice - 1);
                if(flavor.getQuantity() <= 0) {
                    System.out.println(flavor.getItemName() + " is out of stock");
                    return;
                }
                pickedIceCream.addFlavors(flavor);
                pickedFlavors.add(flavor);
                pickedItem.add(flavor);
            }
            
            while (true) {
                Item item;
                System.out.println("Pick a toppings");
                displayItems(myChocolate);
                System.out.println("[0] - No Toppings");
                for(int i=0; i<iceCream.get(variation-1).getNoChocolates(); i++){
                    System.out.println("Choose an item.");
                    System.out.print(">> ");

                    choice = getUserInput();
                    if (choice == 0) {
                        break;
                    }

                    if (choice < 0 || choice > myChocolate.size()) {
                        System.out.println("Invalid choice.");
                        continue;
                    }

                    if (myChocolate.get(choice - 1).getQuantity() <= 0) {
                    System.out.println(myChocolate.get(choice - 1).getItemName() + " is out of stock");
                    } else {
                        item = myChocolate.get(choice - 1);
                        pickedIceCream.addChocolates(item);
                        pickedChocolates.add(item);
                        pickedItem.add(item);
                    }
                }
                break;
            }

            //COMPUTING
            double amount, price = 0, calories = 0, iceCreamCal = 0;

            //CALCULATE TOTAL CALORIES AND PRICE 
            calories = pickedIceCream.getTotalCalories();
            price = pickedIceCream.getTotalPrice();

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
                amount = scanner.nextInt();
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
            System.out.println("Vending Machine credits: " + totalChange);
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

            prepareItem(pickedIceCream.getItemName(), pickedFlavors.subList(0, pickedFlavors.size()), pickedChocolates.subList(0, pickedChocolates.size()));
            //If Vending machine has enough change.
            totalChange -= balance;
            totalSales += price;

            System.out.println("Handling changes...");
            for (int denomination : denominations) {
                while (balance >= denomination) {
                    System.out.println(denomination);
                    balance -= denomination;
                }
            }
            System.out.println("Vending Machine credits: " + totalChange);
            for(Item item : pickedIceCream.getChocolates()){ 
                int tempNo = 0;
                for(SpecialItem item2 : iceCream){
                    if(item.getItemName() == item2.getItemName())
                    tempNo = item2.getNoChocolates();
                }
                transactions.add(new Transaction(new Item(item.getItemName(), tempNo), price));
                item.setQuantity(item.getQuantity() - tempNo);    
            }
            for(Item item : pickedIceCream.getFlavors()){
                transactions.add(new Transaction(new Item(item.getItemName(), item.getQuantity()), price));     
            }
            System.out.println("Done...");
            System.out.println("Transaction Complete.");
        }
    }
    /*
        Description: Restocks the items in the vending machine.
    */
    @Override
    public void restockItems() {
        List<Item> items= new ArrayList<>();
        int choice;
        while(true) {
            items.removeAll(items);
            System.out.println("[1] Base Ice Cream");
            System.out.println("[2] Chocolate/Topping");
            System.out.println("[0] Exit");
            System.out.print(">> ");
            choice = getUserInput();
            if(choice==1) {
                items.addAll(myFlavor);
                displayItems(items);
                System.out.println("Enter flavor to restock");
            }
            else if(choice ==2){
                items.addAll(myChocolate);
                displayItems(items);
                System.out.println("Enter chocolate to restock");
            }
            else if(choice==0)
            break;
            else
            System.out.println("Invalid input");

            if(choice==1 || choice ==2)
            {
                int temp = items.size()+1;
                System.out.println("["+temp+"] Restock All");
                System.out.println("[0] - Exit ");
                System.out.println("Enter item to restock");
                System.out.print(">> ");
                int index = getUserInput();

                if (index == 0) {
                    System.out.println("Cancelled");
                    return;
                }
                if (index < 0 || index > items.size()+1) {
                    System.out.println("Invalid item");
                    return;
                }
                System.out.println("Enter quantity");
                int quantity = getUserInput();

                if(index==temp) {
                    for(int i=0; i<items.size();i++) {
                        if(quantity > capacity || items.get(i).getQuantity()+quantity > capacity) {
                            System.out.println("Exceeds capacity");
                            return;
                        }
                        Item pickedItem = items.get(i);
                        pickedItem.setQuantity(pickedItem.getQuantity() + quantity);
                    }
                    System.out.println("Restocked successfully");
                }
                else{
                    if (quantity > capacity || items.get(index-1).getQuantity()+quantity > capacity) {
                        System.out.println("Exceeds capacity");
                        return;
                    }

                    Item pickedItem = items.get(index - 1);
                    pickedItem.setQuantity(pickedItem.getQuantity() + quantity);
                    System.out.println("Restocked successfully");
                }
            }
        }
    }
    /*
        Description: Sets the price of an item in the vending machine.
    */
    @Override
    public void setItemPrice() {
        List<Item> items= new ArrayList<>();
        int choice;
        while(true) {
            items.removeAll(items);
            System.out.println("[1] Base Ice Cream");
            System.out.println("[2] Chocolate/Topping");
            System.out.println("[0] Exit");
            System.out.print(">> ");
            choice = getUserInput();
            if(choice==1) {
                items.addAll(myFlavor);
                displayItems(items);
                System.out.println("Enter flavor to set price");
            }
            else if(choice ==2){
                items.addAll(myChocolate);
                displayItems(items);
                System.out.println("Enter chocolate to set price");
            }
            else if(choice==0)
            break;
            else
            System.out.println("Invalid input");

            if(choice==1 || choice==2) {
                System.out.println("[0] - Exit ");
                System.out.println("Enter item to change price");
                System.out.print(">> ");
                int index = getUserInput();

                if (index == 0) {
                System.out.println("Cancelled");
                return;
            }

            if (index < 0 || index > items.size()) {
                System.out.println("Invalid item");
                return;
            }

            System.out.println("Enter new price");
            double price = getUserInput();

            Item pickedItem = items.get(index-1);
            pickedItem.setPrice(price);
            System.out.println("Price changed successfully");
            }
        }
    }
    /*
        Description: Prepares an item for purchase.
        Parameters:
            @param flavor - The selected flavor item
            @param toppings - The list of selected toppings
    */
    private void prepareItem(String pickedICeCream, List<Item> flavors, List<Item> chocolates) {
        System.out.println("-----------------------------------------------------");
        System.out.println("Preparing ");
        System.out.println("Preparing " + pickedICeCream);
        System.out.println("Adding: ");
        
        System.out.print("Base Ice Cream Flavor: ");
        for (Item top: flavors) {
            System.out.print(top.getItemName() + ", ");
        }
        System.out.println("");
        System.out.print("Chocolates: ");
        for(Item top : chocolates) {
            System.out.print(top.getItemName() + ", ");
        }

        System.out.println("");
        System.out.println("-----------------------------------------------------");
        System.out.println("");
    }

    
}
