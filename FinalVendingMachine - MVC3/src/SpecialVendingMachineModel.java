import java.util.ArrayList;
import java.util.List;

public class SpecialVendingMachineModel extends VendingMachineModel{
    protected List<SpecialItem> iceCream, myIceCream;
    protected CustomizableItem speciaItem = new CustomizableItem();
    
    public void SpecialVendingMachineModel(){
        transactions = new ArrayList<>();
        
    }
    
    @Override
    public void createVendingMachine() {
        
    }
    @Override
    public void vendingMachineFeatures() {
        
    }
    
    
    
}
