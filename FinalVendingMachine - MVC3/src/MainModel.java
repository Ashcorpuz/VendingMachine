import java.util.ArrayList;
import java.util.List;

public class MainModel {
    protected List<VendingMachineModel> vms;
    protected VendingMachineModel vm;
    protected int vmType;
    protected StoredItems chocolates = new StoredItems();
    protected StoredItems flavors = new StoredItems();

    public MainModel(){
        this.vms = new ArrayList<>();
    }


}
