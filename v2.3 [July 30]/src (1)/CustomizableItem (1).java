import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomizableItem {
    private final List<SpecialItem> itemsList = new ArrayList<>();
    private int itemNo = 0;

    public List<SpecialItem> getItemsList() {
        return itemsList;
    }

    public void readXMLFile(String filepath) {
        try {
        // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse(new File(filepath));

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

                    // Get the cups element
                    Element cupsElement = (Element) itemElement.getElementsByTagName("cups").item(0);
                    int cupsValue = Integer.parseInt(cupsElement.getAttribute("value"));

                    // Get the flavors element
                    Element flavorsElement = (Element) itemElement.getElementsByTagName("flavors").item(0);
                    int flavorsValue = Integer.parseInt(flavorsElement.getAttribute("value")); 

                    // Get the chocolate element
                    Element chocolatesElement = (Element) itemElement.getElementsByTagName("chocolates").item(0);
                    int chocolatesValue = Integer.parseInt(chocolatesElement.getAttribute("value"));

                    SpecialItem item = new SpecialItem(itemName, 0, cupsValue, flavorsValue, chocolatesValue);
                    itemsList.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayItemsButton(Main main, JDialog chooseDialog, List<SpecialItem> iceCream, int slot, List<Item> itemSlots){
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

        for(SpecialItem item : itemsList){
            JButton itemButton = new JButton(item.getItemName());
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    JDialog itemDialog = new JDialog(chooseDialog, item.getItemName());
                    itemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                    JPanel panel = new JPanel(new GridLayout(4, 2));

                    if(item.getItemName() == "Epic Treats [1 Tub]"){
                        JLabel cups = new JLabel("No of Cups");
                        panel.add(cups);
                        panel.setPreferredSize(new Dimension(100, 30));
                        JLabel cupValue = new JLabel(String.valueOf(item.getCups()));
                        panel.add(cupValue);
                        panel.setPreferredSize(new Dimension(100, 30));

                        JLabel maxChoc = new JLabel("No of Chocolates: ");
                        panel.add(maxChoc);
                        panel.setPreferredSize(new Dimension(100, 30));
                        JLabel maxChocValue = new JLabel("Max of 3 Chocolates");
                        panel.add(maxChocValue);
                        panel.setPreferredSize(new Dimension(100, 30));

                        JLabel maxFlavor = new JLabel("No of Flavors: ");
                        panel.add(maxFlavor);
                        panel.setPreferredSize(new Dimension(100, 30));
                        JLabel maxFlavorValue = new JLabel("Max of 2 Flavors");
                        panel.add(maxFlavorValue);
                        panel.setPreferredSize(new Dimension(100, 30));
                    }
                    else if(item.getItemName() == "Epic Treats [1 Pint]")
                    {
                        JLabel cups = new JLabel("No of Cups");
                        panel.add(cups);
                        panel.setPreferredSize(new Dimension(100, 30));
                        JLabel cupValue = new JLabel(String.valueOf(item.getCups()));
                        panel.add(cupValue);
                        panel.setPreferredSize(new Dimension(100, 30));

                        JLabel maxChoc = new JLabel("No of Chocolates: ");
                        panel.add(maxChoc);
                        panel.setPreferredSize(new Dimension(100, 30));
                        JLabel maxChocValue = new JLabel("Max of 5 Chocolates");
                        panel.add(maxChocValue);
                        panel.setPreferredSize(new Dimension(100, 30));

                        JLabel maxFlavor = new JLabel("No of Flavors: ");
                        panel.add(maxFlavor);
                        panel.setPreferredSize(new Dimension(100, 30));
                        JLabel maxFlavorValue = new JLabel("Max of 3 Flavors");
                        panel.add(maxFlavorValue);
                        panel.setPreferredSize(new Dimension(100, 30));
                    }
                    else{
                        JLabel cups = new JLabel("No of Cups");
                        panel.add(cups);
                        panel.setPreferredSize(new Dimension(100, 30));
                        JLabel cupValue = new JLabel(String.valueOf(item.getCups()));
                        panel.add(cupValue);
                        panel.setPreferredSize(new Dimension(100, 30));

                        JLabel maxChoc = new JLabel("No of Chocolates: ");
                        panel.add(maxChoc);
                        panel.setPreferredSize(new Dimension(100, 30));
                        JLabel maxChocValue = new JLabel(String.valueOf(item.getNoChocolates()));
                        panel.add(maxChocValue);
                        panel.setPreferredSize(new Dimension(100, 30));

                        JLabel maxFlavor = new JLabel("No of Flavors: ");
                        panel.add(maxFlavor);
                        panel.setPreferredSize(new Dimension(100, 30));
                        JLabel maxFlavorValue = new JLabel(String.valueOf(item.getNoFlavors()));
                        panel.add(maxFlavorValue);
                        panel.setPreferredSize(new Dimension(100, 30));
                    }

                    JButton addToCartButton = new JButton("Add");
                    addToCartButton.setPreferredSize(new Dimension(150, 30));
                    addToCartButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int temp = 0;
                            for(SpecialItem customItem : iceCream){
                                String tempName = customItem.getItemName();
                                int tempQuantity = customItem.getQuantity();
                                double tempPrice = customItem.getTotalPrice();
                                double tempCalories = customItem.getTotalCalories();
                                Item indivItem = new Item(tempName, tempQuantity, tempPrice, tempCalories);
        
                                if(itemSlots.contains(indivItem))
                                {
                                        
                                }
                                itemSlots.add(indivItem);
                            }
                            for(int j=0; j<iceCream.size(); j++){
                                if(item.getItemName().equals(iceCream.get(j).getItemName()))
                                {
                                    temp = 1;
                                    System.out.println("Item: "+item.getItemName()+"vmItem: "+iceCream.get(j).getItemName());
                                }
                            }

                            if(temp==0){
                                if(itemNo<slot){
                                    itemNo++;
                                    iceCream.add(item);
                                    String tempName = item.getItemName();
                                    int tempQuantity = item.getQuantity();
                                    double tempPrice = item.getTotalPrice();
                                    double tempCalories = item.getTotalCalories();
                                    Item item = new Item(tempName, tempQuantity, tempPrice, tempCalories);
                                    itemSlots.add(item);
                                    
                                    JDialog successDialog = new JDialog(chooseDialog, "SUCCESS");
                                    JLabel addedLabel = new JLabel(item.getItemName());
                                    JPanel successPanel =  new JPanel();
                                    successPanel.add(addedLabel);
                                    successPanel.setPreferredSize(new Dimension(220, 30));
                                    addedLabel = new JLabel("Successfully Added");
                                    successPanel.add(addedLabel);
                                    successPanel.setPreferredSize(new Dimension(220, 30));

                                    successDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                                    successDialog.setContentPane(successPanel);
                                    successDialog.setPreferredSize(new Dimension(250, 100));
                                    successDialog.pack();
                                    successDialog.setLocationRelativeTo(itemDialog);
                                    successDialog.setVisible(true);
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
                System.out.println("SLot: "+slot+" itemsSlot:"+iceCream.size());
                if(iceCream.size()==slot){
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
    /*
        Description: Displays the items in the itemsList.
    */
    public void displayItems() {
        for (int i = 0; i < itemsList.size(); i++) {
            SpecialItem item = itemsList.get(i);
            System.out.println("[" + (i+1) + "]==========================");
            System.out.println("    Item: " + item.getItemName());
            System.out.println("    Cups: " + item.getCups());
            if(item.getNoChocolates()==0 &&  item.getItemName()=="Epic Treats [1 Tub]") {
            System.out.println("    Max of 3 chocolates");
            System.out.println("    Max 2 of Flavors ");
            }
            else if(item.getNoChocolates()==0 &&  item.getItemName()=="Epic Treats [1 Pint]")
            {
            System.out.println("    Max of 5 chocolates");
            System.out.println("    Max 3 of Flavors ");
            }
            else {
            System.out.println("    Chocolates: " + item.getChocolates());
            System.out.println("    Flavors: " + item.getFlavors());
            }
            System.out.println();
        }
    }
    }
