import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainView {
    protected JFrame mainFrame = new JFrame("Vending Maching Factory");;
    protected JButton mainMenu, createVM, testVM, regular, special, exit, current, another, // Main
                        cancel,     // createMenu
                        vendingFeatures, maintenanceFeatures,
                        restockItem, setPrice, collectMoney, replenishChange, printSummary, back, //maintenance features
                        proceed, itemButton, addToCart, restockAll, restock, set, collect, replenish, print, done;                                       // vending features
    protected JLabel mainLabel, createLabel, testLabel, regularLabel, specialLabel, addLabel;
    protected JPanel mainPanel, createPanel, testPanel, regularPanel, specialPanel, warnPanel, featuresPanel, maintenancPanel,
                    itemsPanel, addPanel, restockPanel, successPanel;
    protected JTextField quantityField, priceField, collectField, changField;
    protected JDialog warningDialog, chooseDialog, itemDialog, successDialog, maintenanceDialog;
    protected Item chosenItem;
    protected VendingMachineView vm;
    
    public MainView(){
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.mainFrame.setSize(700, 500);
        this.mainFrame.setVisible(true);
        
        this.mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.createPanel = new JPanel();
        this.testPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.specialPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.maintenancPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.featuresPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.itemsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        itemsPanel.setLayout(new GridLayout(0, 3, 10, 10));
        this.addPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.restockPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.successPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        this.quantityField = new JTextField(15);
        this.quantityField = new JTextField();
        this.collectField = new JTextField();
        this.changField = new JTextField();

        this.warningDialog = new JDialog();
        warningDialog.setTitle("WARNING");
        this.successDialog = new JDialog();
        this.maintenanceDialog = new JDialog();
        this.chooseDialog = new JDialog(mainFrame);
        chooseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);;
        chooseDialog.setPreferredSize(new Dimension(700, 500));
        this.itemDialog = new JDialog();
        itemDialog.setPreferredSize(new Dimension(250, 150));

        this.createVM = new JButton("Create");
        this.testVM = new JButton("Test");
        this.createVM.setPreferredSize(new Dimension(220, 30));
        this.testVM.setPreferredSize(new Dimension(220, 30));
        this.exit = new JButton("Exit");
        this.exit.setPreferredSize(new Dimension(220, 30));
        this.regular = new JButton("Regular");
        this.special = new JButton("Special");
        this.regular.setPreferredSize(new Dimension(220, 30));
        this.special.setPreferredSize(new Dimension(220, 30));
        this.current = new JButton("Current VM");
        this.another = new JButton("Another VM");
        this.current.setPreferredSize(new Dimension(220, 30));
        this.another.setPreferredSize(new Dimension(220, 30));
        this.mainMenu = new JButton("MainMenu");
        this.mainMenu.setPreferredSize(new Dimension(220, 30));
        this.cancel = new JButton("Cancel");
        this.cancel.setPreferredSize(new Dimension(220, 30));
        this.restockItem = new JButton("Restock Items");
        this.restockItem.setPreferredSize(new Dimension(220, 30));
        this.setPrice = new JButton("Set Price");
        this.setPrice.setPreferredSize(new Dimension(220, 30));
        this.collectMoney = new JButton("Collect Money");
        this.collectMoney.setPreferredSize(new Dimension(220, 30));
        this.replenishChange = new JButton("Replenish Change");
        this.replenishChange.setPreferredSize(new Dimension(220, 30));
        this.printSummary = new JButton("Print Summary");
        this.printSummary.setPreferredSize(new Dimension(220, 30));
        this.back = new JButton("Back");
        this.back.setPreferredSize(new Dimension(220, 30));
        this.vendingFeatures = new JButton("Vending Features");
        this.vendingFeatures.setPreferredSize(new Dimension(220, 30));
        this.maintenanceFeatures = new JButton("Maintenance Features");
        this.maintenanceFeatures.setPreferredSize(new Dimension(220, 30));
        this.itemButton = new JButton("Maintenance Features");
        this.itemButton.setPreferredSize(new Dimension(220, 30));
        this.proceed = new JButton("Proceed");
        this.proceed.setPreferredSize(new Dimension(220, 30));
        this.addToCart = new JButton("Add");
        this.addToCart.setPreferredSize(new Dimension(220, 30));
        this.restockAll = new JButton("Restock All");
        this.restockAll.setPreferredSize(new Dimension(220, 30));
        this.restock = new JButton("Restock");
        this.restock.setPreferredSize(new Dimension(220, 30));
        this.set = new JButton("Set");
        this.set.setPreferredSize(new Dimension(220, 30));
        this.collect = new JButton("Collect");
        this.collect.setPreferredSize(new Dimension(220, 30));
        this.replenish = new JButton("Replenish");
        this.replenish.setPreferredSize(new Dimension(220, 30));
        this.print = new JButton("Print");
        this.print.setPreferredSize(new Dimension(220, 30));
        this.done = new JButton("Done");
        this.done.setPreferredSize(new Dimension(220, 30));

        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mainFrame.getContentPane().removeAll();
                MainMenu();
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });
        this.mainFrame.setVisible(true);
    }

    public void MainMenu(){
        this.mainFrame.getContentPane().removeAll();
        this.mainLabel = new JLabel("MENU", 0);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));  
        mainPanel.removeAll();

        this.mainPanel.add(mainLabel);
        this.mainPanel.add(createVM);
        this.mainPanel.add(testVM);
        this.mainPanel.add(exit);
        this.mainFrame.add(mainPanel);
        this.mainFrame.setVisible(true);
    }

    public void createMenu(){
        this.mainFrame.getContentPane().removeAll();
        createPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        createPanel.setLayout(new GridLayout(0, 1));
        this.createLabel = new JLabel("CREATING VENDING MACHINE");
        createPanel.add(createLabel);
        createPanel.add(regular);
        createPanel.add(special);
        createPanel.add(mainMenu);
        
        mainFrame.add(createPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    public void testMenu(){
        this.mainFrame.getContentPane().removeAll();
        testPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        testPanel.setLayout(new GridLayout(0, 1));
        this.testLabel = new JLabel("TESTING VENDING MACHINE");
        testPanel.add(testLabel);

        testPanel.add(current);
        testPanel.add(another);
        testPanel.add(mainMenu);
        mainFrame.add(testPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }    

    public void displayWarning(String text){
        JLabel warnLabel = new JLabel(text);
        this.warnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        warnPanel.add(warnLabel);
        warnPanel.setPreferredSize(new Dimension(300, 100));

        warningDialog.setContentPane(warnPanel);
        warningDialog.setPreferredSize(new Dimension(300, 100));
        warningDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        warningDialog.pack();
        warningDialog.setVisible(true);
    }

    public void displaySuccess(String text, String title){
        JLabel successLabel = new JLabel(text);
        this.successPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        successPanel.add(successLabel);
        successPanel.setPreferredSize(new Dimension(300, 100));

        successDialog.setTitle(title);
        successDialog.setContentPane(successPanel);
        successDialog.setPreferredSize(new Dimension(300, 100));
        successDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        successDialog.pack();
        successDialog.setVisible(true);
    }

    public void testVendingMachineMenu(){
        mainFrame.getContentPane().removeAll();
        mainLabel = new JLabel("TEST VENDING MACHINE");
        featuresPanel.removeAll();
        featuresPanel.setLayout(new GridLayout(0,1));
        featuresPanel.add(mainLabel);
        featuresPanel.add(vendingFeatures);
        featuresPanel.add(maintenanceFeatures);
        featuresPanel.add(mainMenu);
        mainFrame.add(featuresPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void maintenanceFeaturesMenu(){
        mainFrame.getContentPane().removeAll();
        mainLabel = new JLabel("PRODUCTS FOR SALE");
        maintenancPanel.setLayout(new GridLayout(0,1));
        maintenancPanel.add(restockItem);
        maintenancPanel.add(setPrice);
        maintenancPanel.add(collectMoney);
        maintenancPanel.add(replenishChange);
        maintenancPanel.add(printSummary);
        maintenancPanel.add(back);
        mainFrame.add(maintenancPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void restockMenu(JDialog restockDialog, JPanel restockPanel){
        restockDialog.setTitle("RESTOCKING ITEMS");
        restockDialog.setPreferredSize(new Dimension(700, 500));
        mainLabel = new JLabel("Restocking");
        restockPanel = new JPanel();
        restockPanel.setPreferredSize(new Dimension(700, 500));
        restockPanel.add(mainLabel);
        restockDialog.setContentPane(restockPanel);
        restockDialog.pack();
        restockDialog.setLocationRelativeTo(mainFrame);
        restockDialog.setVisible(true);
    }

    public void collectMenu(JDialog collectDialog, double totalSales){
        collectDialog = new JDialog(mainFrame, "COLLECTING MONEY");
        collectDialog.setPreferredSize(new Dimension(250, 200));
        collectDialog.setLayout(new GridLayout(0, 1, 10, 10));
        mainLabel = new JLabel("Total Earnings ");
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(700, 500));
        mainPanel.add(mainLabel);
        mainLabel = new JLabel(String.valueOf(totalSales));
        mainPanel.setPreferredSize(new Dimension(700, 500));
        mainPanel.add(mainLabel);
        collectField.setColumns(15);
        collectField.setText(" ");
        collectField.setEnabled(true);
        mainPanel.add(collectField);
        mainPanel.add(collect);
        collectDialog.setContentPane(mainPanel);
        collectDialog.pack();
        collectDialog.setLocationRelativeTo(mainFrame);
        collectDialog.setVisible(true);
    }

    public void setItemPriceMenu(JDialog setPriceDialog){
        setPriceDialog.setTitle("SETTING PRICE OF ITEMS");
        setPriceDialog.setPreferredSize(new Dimension(700, 500));
        mainLabel = new JLabel("Setting Price");
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(700, 500));
        mainPanel.add(mainLabel);
        setPriceDialog.setContentPane(mainPanel);
        setPriceDialog.pack();
        setPriceDialog.setLocationRelativeTo(mainFrame);
        setPriceDialog.setVisible(true);
    }

    public void replenishMenu(JDialog replenishDialog, double totalChange){
        replenishDialog = new JDialog(mainFrame, "REPLENISHING CHANGE");
        replenishDialog.setPreferredSize(new Dimension(700, 500));
        mainLabel = new JLabel("Enter Value of Change");
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(700, 500));
        mainPanel.add(mainLabel);
        mainLabel = new JLabel(String.valueOf(totalChange));
        mainPanel.setPreferredSize(new Dimension(700, 500));
        mainPanel.add(mainLabel);
        changField.setColumns(15);
        changField.setText(" ");
        changField.setEnabled(true);
        mainPanel.add(changField);
        mainPanel.add(replenish);
        replenishDialog.setContentPane(mainPanel);
        replenishDialog.pack();
        replenishDialog.setLocationRelativeTo(mainFrame);
        replenishDialog.setVisible(true);
    }

    public void setCreateVMListener(ActionListener actionListener){
        this.createVM.addActionListener(actionListener);
    }

    public void setRegularListener(ActionListener actionListener){
        this.regular.addActionListener(actionListener);
    }

    public void setSpecialListener(ActionListener actionListener){
        this.special.addActionListener(actionListener);
    }

    public void setCancelListener(ActionListener actionListener){
        this.cancel.addActionListener(actionListener);
    }

    public void setTestVMListener(ActionListener actionListener){
        this.testVM.addActionListener(actionListener);
    }

    public void setExitListener(ActionListener actionListener){
        this.exit.addActionListener(actionListener);
    }

    public void setCurrentListener(ActionListener actionListener){
        this.current.addActionListener(actionListener);
    }

    public void setAnotherListener(ActionListener actionListener){
        this.another.addActionListener(actionListener);
    }

    public void setRestockItemListener(ActionListener actionListener){
        this.restockItem.addActionListener(actionListener);
    }

    public void setPriceListener(ActionListener actionListener){
        this.setPrice.addActionListener(actionListener);
    }

    public void setCollectMoneyListener(ActionListener actionListener){
        this.collectMoney.addActionListener(actionListener);
    }

    public void setCollectListener(ActionListener actionListener){
        this.collect.addActionListener(actionListener);
    }

    public void setReplenishChangeListener(ActionListener actionListener){
        this.replenishChange.addActionListener(actionListener);
    }

    public void setReplenishListener(ActionListener actionListener){
        this.replenish.addActionListener(actionListener);
    }

    public void setPrintSummaryListener(ActionListener actionListener){
        this.printSummary.addActionListener(actionListener);
    }

    public void setPrintListener(ActionListener actionListener){
        this.print.addActionListener(actionListener);
    }

    public void setBackListener(ActionListener actionListener){
        this.back.addActionListener(actionListener);
    }

    public void setVendingFeaturesistener(ActionListener actionListener){
        this.vendingFeatures.addActionListener(actionListener);
    }

    public void setMaintenanceFeaturesListener(ActionListener actionListener){
        this.maintenanceFeatures.addActionListener(actionListener);
    }

    public void setProceedListener(ActionListener actionListener){
        this.proceed.addActionListener(actionListener);
    }

    public void setItemButtonListener(ActionListener actionListener){
        this.itemButton.addActionListener(actionListener);
    }

    public void setAddToCartListener(ActionListener actionListener){
        this.addToCart.addActionListener(actionListener);
    }

    public void setRestockListener(ActionListener actionListener){
        this.restock.addActionListener(actionListener);
    }

    public void setRestockAllListener(ActionListener actionListener){
        this.restockAll.addActionListener(actionListener);
    }
    
    public void setSetListener(ActionListener actionListener){
        this.set.addActionListener(actionListener);
    }

    public void setDoneListener(ActionListener actionListener){
        this.done.addActionListener(actionListener);
    }

    public void setChooseDialogListner(WindowAdapter windowAdapter){
        this.chooseDialog.addWindowListener(windowAdapter);
    }

    public void setSuccessDialogListner(WindowAdapter windowAdapter){
        this.successDialog.addWindowListener(windowAdapter);
    }

    public void setChooseDialogContent(String title, JPanel panel){
        this.chooseDialog = new JDialog(mainFrame, title);
        chooseDialog.setContentPane(panel);
        chooseDialog.pack();
        chooseDialog.setLocationRelativeTo(mainFrame);
        chooseDialog.setPreferredSize(new Dimension(700, 500));
        chooseDialog.setVisible(true);
    }

}