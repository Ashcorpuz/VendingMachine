import java.util.ArrayList;
import java.util.List;

public class SpecialItem {
    private final String itemName;
    private int quantity, cups, noChocolates, noFlavors;
    private double totalPrice;
    private double totalCalories;
    private List<Item> chocolates;
    private List<Item> flavors;
    private List<Item> soloItems;

    public SpecialItem(String itemName, int cups, int noFlavors, int noChocolates) {
        this.itemName = itemName;
        this.cups = cups;
        this.noFlavors = noFlavors;
        this.noChocolates = noChocolates;
        this.chocolates = new ArrayList<>();
        this.flavors = new ArrayList<>();
    }

    public SpecialItem(String itemName, int quantity, double totalPrice, double totalCalories) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalCalories = totalCalories;
        this.totalPrice =totalPrice;
    }

    public void displayIceCream() {
        System.out.println(itemName+"           |   "+cups+"    |   "+noChocolates+"    |   "+noFlavors);
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        double tempPrice = 0, tempIcePrice = 0;
        int tempCups;
        for(Item choc : this.chocolates){
            tempPrice += choc.getPrice();
        }
        for(Item flav : this.flavors) {
            if(this.itemName == "Classic Delight [1 Pint]" || this.itemName == "Classic Delight [1 Tub]" 
                || this.itemName == "Supreme Indulgence [1 Pint]" || this.itemName == "Supreme Indulgence [1 Tub]")
            tempIcePrice = flav.getPrice() * cups;
            else {
                tempCups = this.cups/this.noFlavors;
                tempIcePrice = flav.getPrice() * tempCups;
            }
            tempPrice += tempIcePrice;
        }
        totalPrice = tempPrice;
        return  totalPrice;
    }

    public double getTotalCalories() {
        double tempCal = 0, tempIceCal = 0;
        int tempCups;
        for(Item choc : this.chocolates) {
            tempCal += choc.getCalories();
        }
        for(Item flav : this.flavors) {
            if(this.itemName == "Classic Delight [1 Pint]" || this.itemName == "Classic Delight [1 Tub]" 
                || this.itemName == "Supreme Indulgence [1 Pint]" || this.itemName == "Supreme Indulgence [1 Tub]")
            tempIceCal = flav.getCalories() * cups;
            else {
                tempCups = this.cups/this.noFlavors;
                tempIceCal = flav.getCalories() * tempCups;
            }
            tempCal += tempIceCal;
        }
        totalCalories = tempCal;
        return totalCalories;
    }

    public int getCups(){
        return cups;
    }

    public int getNoChocolates() {
        return noChocolates;
    }

    public int getNoFlavors(){
        return noFlavors;
    }

    public void addChocolates(Item chocolates){
        this.chocolates.add(chocolates);
    }

    public void addFlavors(Item flavors) {
        this.flavors.add(flavors);
    }

    public List<Item> getChocolates() {
        return chocolates;
    }

    public List<Item> getFlavors() {
        return flavors;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double price) {
    }
}
