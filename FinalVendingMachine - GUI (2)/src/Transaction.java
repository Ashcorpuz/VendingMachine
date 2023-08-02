/*
    Description: The Transaction class represents the transaction of the vending machine.
    It contains information about the purchased items, flavors, and the total price of the transacton.
*/

import java.util.List;

public class Transaction {
    private final Item item;
    private List<Item> flavor;
    private final double totalPrice;
    /*
        Description: Constructs a transaction object with the given item, flavors and the total price.
        Parameters:
            @param item - The purchased item
            @param flavor - The list of flavors chosen in the Special Vending Machine
            @param totalPrice - The total price of the transaction
    */
    public Transaction(Item item, List<Item> flavor, double totalPrice) {
        this.item = item;
        this.flavor = flavor;
        this.totalPrice = totalPrice;
    }
    /*
        Description: Constructs a transaction object with the given item and the total price.
        Parameters:
            @param item - The purchased item
            @param totalPrice - The total price of the transaction
    */
    public Transaction(Item item, double totalPrice) {
        this.item = item;
        this.totalPrice = totalPrice;
    }
    /*
        Return Value: Ice cream flavor chosen in the transaction
    */
    public List<Item> getFlavor() {
        return flavor;
    }
    /*
        Return Value: Purchased Item in the transaction
    */
    public Item getItem() {
        return item;
    }
    /*
        Return Value: Total Price of the Item in the transactions
    */
    public double getTotalPrice() {
        return totalPrice;
    }

}
