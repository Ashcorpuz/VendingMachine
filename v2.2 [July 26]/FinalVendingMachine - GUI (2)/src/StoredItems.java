import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/*
    Description: The StoredItems class represents a collection of items stored in an XML file.
    It provides methods to read the XML file, retrieve the list of items, and display the items.
*/
public class StoredItems {
    private final List<Item> itemsList = new ArrayList<>();
    private int itemNo = 0;
    /*
        Description: Retrieves the list of items.
        Return Value: The list of items
    */
    public List<Item> getItemsList() {
        return itemsList;
    }
    /*
        Description: Reads an XML file and populates the itemsList with the items from the file.
        Parameters:
            @param filePath - The path of the XML file to be read.
    */
    public void readXMLFile(String filePath) {
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse(new File(filePath));

            // Normalize the XML structure
            document.getDocumentElement().normalize();

            // Get the root element
            Element root = document.getDocumentElement();

            // Get all child nodes of the root element
            NodeList itemList = root.getElementsByTagName("item");

            // Iterate over the item nodes
            for (int i = 0; i < itemList.getLength(); i++) {
                Node itemNode = itemList.item(i);

                // Display only if the node is an element node
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    String itemName = itemElement.getAttribute("name");

                    // Get the calorie element
                    Element calorieElement = (Element) itemElement.getElementsByTagName("calorie").item(0);
                    float calorieValue = Float.parseFloat(calorieElement.getAttribute("value"));

                    // Get the price element
                    Element priceElement = (Element) itemElement.getElementsByTagName("price").item(0);
                    float priceValue = Float.parseFloat(priceElement.getAttribute("value"));

                    Item item = new Item(itemName, 0, priceValue, calorieValue);
                    itemsList.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
        Description: Displays the items in the itemsList.
    */
    public void displayItems() {
        for (int i = 0; i < itemsList.size(); i++) {
            Item item = itemsList.get(i);
            System.out.println("[" + i + "]==========================");
            System.out.println("    Item: " + item.getItemName());
            System.out.println("    Price: " + item.getPrice());
            System.out.println("    Calorie: " + item.getCalories());
            System.out.println();
        }
    }

    public void displayItems(JFrame mainFrame) {
        DefaultTableModel itemTableModel;
        JTable table;
        String[] colNames = {"Item", "Calories", "Price", "Quantity"};
        itemTableModel = new DefaultTableModel(colNames, 0);
        
        for(Item item : itemsList){
            Object[] data = {item.getItemName(), item.getCalories(), item.getPrice(), item.getQuantity()};
            itemTableModel.addRow(data);
        }
        
        table = new JTable(itemTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        mainFrame.add(scrollPane, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    public void displayItemsButton(Main main, JDialog chooseDialog, List<Item> vmItem, int slot) {
        chooseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 3, 10, 10));

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel itemNoLabel = new JLabel("Vending Machine Item Number: ");
        mainPanel.add(itemNoLabel);
        mainPanel.setPreferredSize(new Dimension(220, 30));
        JLabel itemValLabel = new JLabel(String.valueOf(itemNo));
        mainPanel.add(itemValLabel);
        mainPanel.setPreferredSize(new Dimension(100, 30));

        for(Item item: itemsList){
            
            //JButton itemButton = new JButton(item.getItemName());
            //itemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton itemButton = new JButton(item.getItemName());
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog itemDialog = new JDialog(chooseDialog, item.getItemName());
                    itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    
                    JPanel panel = new JPanel(new GridLayout(3, 2));

                    JLabel calories = new JLabel("Calories");
                    panel.add(calories);
                    panel.setPreferredSize(new Dimension(100, 30));
                    JLabel calValue = new JLabel(String.valueOf(item.getCalories()));
                    panel.add(calValue);
                    panel.setPreferredSize(new Dimension(100, 30));
                    
                    JLabel price = new JLabel("Price");
                    panel.add(price);
                    panel.setPreferredSize(new Dimension(100, 30));
                    JLabel priceValue = new JLabel(String.valueOf(item.getPrice()));
                    panel.add(priceValue);
                    panel.setPreferredSize(new Dimension(100, 30));

                    JButton addToCartButton = new JButton("Add");
                    addToCartButton.setPreferredSize(new Dimension(150, 30));
                    addToCartButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int temp = 0;
                            for(int j=0; j<vmItem.size(); j++){
                                if(item.getItemName().equals(vmItem.get(j).getItemName()))
                                temp = 1;
                            }

                            if(temp==0){
                                if(itemNo<slot){
                                    itemNo++;
                                    vmItem.add(item);
                                    JLabel addedLabel = new JLabel("Successfully Added");
                                    panel.add(addedLabel);
                                    panel.setPreferredSize(new Dimension(220, 30));
                                    itemValLabel.setText(String.valueOf(itemNo));
                                    itemDialog.dispose();
                                }
                                else{
                                    JDialog warningDialog = new JDialog(chooseDialog, "Warning");
                                    JLabel messLabel = new JLabel("Vending Machine Slot is full");
                                    JPanel warnPanel = new JPanel();
                                    warnPanel.add(messLabel);
                                    warnPanel.setPreferredSize(new Dimension(220, 30));
                                    warningDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                                    warningDialog.setContentPane(warnPanel);
                                    warningDialog.setPreferredSize(new Dimension(250, 100));
                                    warningDialog.pack();
                                    warningDialog.setLocationRelativeTo(itemDialog);
                                    warningDialog.setVisible(true);
                                }
                            }
                            else{
                                JDialog warningDialog = new JDialog(chooseDialog, "Warning");
                                JLabel messLabel = new JLabel("Item is already added");
                                JPanel warnPanel = new JPanel();
                                warnPanel.add(messLabel);
                                warnPanel.setPreferredSize(new Dimension(220, 30));
                                warningDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                                warningDialog.setContentPane(warnPanel);
                                warningDialog.setPreferredSize(new Dimension(250, 100));
                                warningDialog.pack();
                                warningDialog.setLocationRelativeTo(itemDialog);
                                warningDialog.setVisible(true);
                            }
                        }
                    });

                    panel.add(addToCartButton);
                    panel.setPreferredSize(new Dimension(220,30));

                    itemDialog.setContentPane(panel);
                    itemDialog.setPreferredSize(new Dimension(250, 150));
                    itemDialog.pack();
                    itemDialog.setLocationRelativeTo(chooseDialog);
                    itemDialog.setVisible(true);
                }
            });
            buttonPanel.add(itemButton);
        }

        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100, 30));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("SLot: "+slot+" itemsSlot:"+main.vm.itemSlots.size());
                if(main.vm.itemSlots.size()==slot){
                    //main.mainFrame.removeAll();
                    chooseDialog.dispose();
                }
                else{
                    JDialog warningDialog = new JDialog(chooseDialog, "Warning");
                    warningDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    JLabel warnLabel = new JLabel("Add more items");
                    JPanel warnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    warnPanel.add(warnLabel);
                    warnPanel.setPreferredSize(new Dimension(220, 30));

                    warningDialog.setContentPane(warnPanel);
                    warningDialog.setPreferredSize(new Dimension(300, 100));
                    warningDialog.pack();
                    warningDialog.setLocationRelativeTo(chooseDialog);
                    warningDialog.setVisible(true);
                }
            }
        });
        mainPanel.add(buttonPanel);
        mainPanel.add(okButton);
        mainPanel.setPreferredSize(new Dimension(400, 100));
        chooseDialog.setContentPane(mainPanel);
        chooseDialog.setPreferredSize(new Dimension(700, 500));
        chooseDialog.pack();
        chooseDialog.setLocationRelativeTo(chooseDialog);
        chooseDialog.setVisible(true);
    }
}
