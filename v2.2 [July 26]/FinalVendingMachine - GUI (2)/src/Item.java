/*
    Description: The Item class represents an item in a vending machine.
    It contains information about the item's name, quantity, price, and calories.
*/
public class Item {
    private final String itemName;
    private int quantity;
    private double price;
    private double calories;
    public Item(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }
    /*
        Description: Creates an item object with the given name, quantity, price and calores
        Parameters:
            @param itemName - name of the item
            @param quantity - quantity of the item
            @param price - price of the item
            @param calories - calorie count of the item
    */
    public Item(String itemName, int quantity, double price, double calories) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.calories = calories;
    }
    /*
        Return Value: Name of the Item
    */
    public String getItemName() {
        return itemName;
    }
    /*
        Return Value: Quantity of the Item
    */
    public int getQuantity() {
        return quantity;
    }
    /*
        Return Value: Sets the quantity of the item
    */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /*
        Return Value: Price of the Item
    */
    public double getPrice() {
        return price;
    }
    /*
        Return Value: Sets the price of the item
    */
    public void setPrice(double price) {
        this.price = price;
    }
    /*
        Return Value: Calorie count of the Item
    */
    public double getCalories() {
        return calories;
    }

}
