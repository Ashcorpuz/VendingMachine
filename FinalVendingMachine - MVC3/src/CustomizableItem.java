import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CustomizableItem {
    private final List<SpecialItem> itemsList = new ArrayList<>();

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

                    SpecialItem item = new SpecialItem(itemName, cupsValue, flavorsValue, chocolatesValue);
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
