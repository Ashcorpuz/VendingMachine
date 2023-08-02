import java.awt.List;
import java.util.ArrayList;

public class RegularVendingMachineModel extends VendingMachineModel{
    
    Item chosenItem;

    public RegularVendingMachineModel(){
        transactions = new ArrayList<>();
        chosenItem = null;
    }

    @Override
    public void createVendingMachine() {
        
    }

    @Override
    public void vendingMachineFeatures() {
    
    }

    public boolean addItems(Item item){
        for(Item items : itemSlots){
            if(items.getItemName() == item.getItemName())
            return false;
            else
            itemSlots.add(item);
        }
        return true;
    }

    public boolean checkItems(){
        if(itemSlots.size()==slot)
        return true;
        else
        return false;
    }
}
