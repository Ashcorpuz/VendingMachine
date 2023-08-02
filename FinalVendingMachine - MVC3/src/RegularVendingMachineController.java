import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegularVendingMachineController {
    private RegularVendingMachineModel model;
    private RegularVendingMachineView view;

    public RegularVendingMachineController(RegularVendingMachineModel model, RegularVendingMachineView view){
        this.model = model;
        this.view = view;

        this.view.setCreateListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                boolean result = model.enterSlots(Integer.parseInt(view.slotField.getText()));
                if(result){ // to proceed checking 
                    result = model.enterSlots(Integer.parseInt(view.cancel.getText()));
                    if(!result){
                        view.warnLabel.setText("Invalid Capacity");
                        view.warnPanel.add(view.warnLabel);

                        
                    }
                }
                else{
                    view.warnLabel.setText("Invalid Slot");
                    view.warnPanel.add(view.warnLabel);

                }
                
                if(result){
                    view.nameField.setEnabled(false);
                    view.slotField.setEnabled(false);
                    view.capacityField.setEnabled(false);
                    view.create.setEnabled(false);
                    view.cancel.setEnabled(false);
                    
                    // TODO Auto-generated method stub
                }
            }
        });
    }
}
