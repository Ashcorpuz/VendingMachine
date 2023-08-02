import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.jar.JarFile;

public class SpecialVendingMachineView extends VendingMachineView {
    private JPanel specialPanel;
    protected JPanel mainPanel, warnPanel;
    protected JLabel mainLabel, warnLabel;
    
    public SpecialVendingMachineView(){
        super();
        this.specialPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        specialPanel.setLayout(new GridLayout(0,1));
    }

    @Override
    public void createVendingMachine(JFrame mainFrame) {
        String text;
        mainFrame.getContentPane().removeAll();
        nameField.setEnabled(true);
        slotField.setEnabled(true);
        capacityField.setEnabled(true);
        nameField.setText("");
        slotField.setText("");
        capacityField.setText("");
        create.setEnabled(true);
        cancel.setEnabled(true);
        specialPanel.setLayout(new GridLayout(0,1));
        JLabel regularLabel = new JLabel("Creating SPECIAL Vending Machine");
        specialPanel.add(regularLabel);
        mainFrame.add(specialPanel);
        
        JLabel nameLabel = new JLabel("Enter Name");
        specialPanel.add(nameLabel);
        specialPanel.add(nameField);
        mainFrame.add(specialPanel);

        JLabel slotLabel = new JLabel("Enter Slot");
        specialPanel.add(slotLabel);
        specialPanel.add(slotField);
        mainFrame.add(specialPanel);

        JLabel maxLabel = new JLabel("Enter Capacity");
        specialPanel.add(maxLabel);
        specialPanel.add(capacityField);

        specialPanel.add(createS);
        specialPanel.add(cancel);
        
        specialPanel.setPreferredSize(new Dimension(300, 300));
        mainFrame.add(specialPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    @Override
    public void vendingMachineFeatures(List<Item> itemSlot, JFrame mainFrame) {
        // TODO Auto-generated method stub
    }

    @Override
    public void chooseItems(JFrame mainFrame, List<Item> itemList, List<Item> itemSlots, int slot, int capacity) {
                
        }

    public void chooseItems(JFrame mainFrame){
        mainFrame.getContentPane().removeAll();
        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setLayout(new GridLayout(0, 1, 10, 10));
    }
}
