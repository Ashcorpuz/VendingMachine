import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
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

            transactions.add(new Transaction(new Item(pickedItem.getItemName(), quantity), price));
            pickedItem.setQuantity(pickedItem.getQuantity() - quantity);
            System.out.println("Done...");
            System.out.println("Transaction Complete.");
        }
    }
}
