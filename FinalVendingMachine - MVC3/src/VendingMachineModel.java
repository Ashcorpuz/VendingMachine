import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class VendingMachineModel {
    protected List<Item> itemSlots, pickedItems;
    protected List<Item> myflavors, pickedFlavors;
    protected List<Integer> pickedItemQuantity;
    protected List<Item> mychocolates, pickedChocolates;
    protected StoredItems storedItems = new StoredItems();
    protected StoredItems flavors = new StoredItems();
    protected double totalSales;
    protected double totalChange;
    protected String name;
    protected List<Transaction> transactions;
    protected Scanner scanner;
    protected int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
    protected int capacity;
    protected int slot;   //Specifies the slot of vending machine
    protected int balance;   
    protected Item itemClicked;
    protected List<SpecialItem> iceCream, myIceCream;
    protected CustomizableItem speciaItem = new CustomizableItem();
    protected SpecialItem pickedIceCream;

    public VendingMachineModel(){
        pickedItemQuantity = new ArrayList<>();
        scanner = new Scanner(System.in);
        pickedItems = new ArrayList<>();
        itemSlots = new ArrayList<>();
        transactions = new ArrayList<>();
        iceCream = new ArrayList<>();
        pickedChocolates = new ArrayList<>();
        pickedFlavors = new ArrayList<>();
        speciaItem.readXMLFile("variations.xml");
        storedItems.readXMLFile("items.xml");
        flavors.readXMLFile("flavors.xml");
        myIceCream = speciaItem.getItemsList();
        mychocolates = storedItems.getItemsList();
        myflavors = flavors.getItemsList();
        totalChange = 0;
        totalSales = 0;
        capacity = 0;
        balance = 0;
    }
    public abstract void createVendingMachine();
    public abstract void vendingMachineFeatures();

    public void setName(String name){
        this.name =  name;
    }

    public void setCapacity(int capacity){
        this.capacity =  capacity;
    }
    
    public void setSlot(int slot){
        this.slot =  slot;
    }
    public void maintenanceFeatures(){
        
    }

    public void addTransaction(Item item, double price){
        transactions.add(new Transaction(item, price));
    }

    public boolean checkItem(Item item){
        for(Item items : itemSlots){
            if(items.getItemName() == item.getItemName())
            return false;
        }
        return true;
    }

    public void testVendingMachine(int option){
        switch(option){
            case 1 -> vendingMachineFeatures();
            case 2 -> maintenanceFeatures();
        }
    }

    public boolean enterName(String text){
        boolean result = false;

        return result;
    }

    public boolean enterSlots(int slot){
        boolean result = false;
        try{
            if(slot>=1&&slot<=10)
            result = true;
        }
        catch(Exception e){
            result = false;
        }
        return result;
    }

    public List<Item> getPickedItems(List<Item> pickedChoc, List<Item> pickedFlav){
        for(Item choc : pickedChoc)
        pickedItems.add(choc);
        for(Item flav : pickedFlav)
        pickedItems.add(flav);

        return pickedItems;
    }

    public boolean enterCapacity(int cap){
        boolean result = false;
        try{
            if(cap>=10&&cap<=20)
            result = true;
        }
        catch(Exception e){
            result = false;
        }
        return result;
    }

    public boolean checkRestock(int restockNo, List<Item> itemsToCheck){
        if(restockNo<=capacity)
        {
            for(Item items : itemsToCheck){
                if(items.getItemName()==itemClicked.getItemName())
                items.setQuantity(restockNo);
            }
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkRestockAll(int restockNo, List<Item> itemsToCheck){
        int temp = 0;
        boolean result = false;
        for(Item items : itemsToCheck){
            int tempQuan;
            tempQuan = items.getQuantity() + restockNo;
            if(tempQuan>capacity)
            temp = capacity +1;
        }
        if(temp<=capacity){
            for(Item item : itemsToCheck){
                int itemQuantity = item.getQuantity() + restockNo;
                item.setQuantity(itemQuantity);
            }
            result = true;
        }
        else if(temp>capacity){
            result = false;
        }
        return result;
    }

    public boolean checkPrice(double price, Item itemToSetPrice){
        if(price>0){
            for(Item items : itemSlots){
                if(items.getItemName() == itemToSetPrice.getItemName()){
                    items.setPrice(price);
                }
            }
            return true;    
        }
        else
        return false;
    }    

    public boolean checkMoney(double money){
        if(money>0 && money<=totalSales){
            totalSales -= money;
            return true;
        }
        else
        return false;
    }

    public boolean checkChange(double change){
        if(change>0){
            totalChange += change;
            return true;
        }
        else
        return false;
    }
}