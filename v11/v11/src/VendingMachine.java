import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
    /*
        Description: Provides the user interface for vending machine features.
    */
    public abstract void vendingMachineFeatures();
    /*
        Description: Starts the testing of the vending machine with options for vending machine features and maintenance features.
        Return Value: void
    */
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
        System.out.println("Index\tName\t\t\t\t\t\t\tPrice\tCalorie\tQuantity");
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