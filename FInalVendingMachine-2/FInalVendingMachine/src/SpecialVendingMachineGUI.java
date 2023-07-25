import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class SpecialVendingMachineGUI extends VendingMachine {
    private JFrame frame;
    private JButton purchaseButton;
    private JButton restockButton;
    private JButton setPriceButton;
    private JButton collectMoneyButton;
    private JButton replenishChangeButton;
    private JButton printTransactionButton;
    private JLabel backgroundImageLabel;

    public SpecialVendingMachineGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Special Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create a panel to hold components
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Add the background image label to the panel
        backgroundImageLabel = new JLabel();
        mainPanel.add(backgroundImageLabel, BorderLayout.CENTER);

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        purchaseButton = new JButton("Purchase");
        restockButton = new JButton("Restock Items");
        setPriceButton = new JButton("Set Item Price");
        collectMoneyButton = new JButton("Collect Money");
        replenishChangeButton = new JButton("Replenish Change");
        printTransactionButton = new JButton("Print Transactions");

        // Add the buttons to the button panel
        buttonPanel.add(purchaseButton);
        buttonPanel.add(restockButton);
        buttonPanel.add(setPriceButton);
        buttonPanel.add(collectMoneyButton);
        buttonPanel.add(replenishChangeButton);
        buttonPanel.add(printTransactionButton);

        // Add action listeners to the buttons
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform purchase action here
                JOptionPane.showMessageDialog(frame, "Purchase button clicked!");
            }
        });

        restockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform restock action here
                JOptionPane.showMessageDialog(frame, "Restock Items button clicked!");
            }
        });

        setPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform set price action here
                JOptionPane.showMessageDialog(frame, "Set Item Price button clicked!");
            }
        });

        collectMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform collect money action here
                JOptionPane.showMessageDialog(frame, "Collect Money button clicked!");
            }
        });

        replenishChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform replenish change action here
                JOptionPane.showMessageDialog(frame, "Replenish Change button clicked!");
            }
        });

        printTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform print transaction action here
                JOptionPane.showMessageDialog(frame, "Print Transactions button clicked!");
            }
        });

        // Add the button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        frame.add(mainPanel);

        // Show the frame
        frame.setVisible(true);
    }

    // Method to set the background image
    public void setBackgroundImage(String imagePath) {
        if (imagePath != null) {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                try {
                    ImageIcon backgroundImageIcon = new ImageIcon(imagePath);
                    Image scaledImage = backgroundImageIcon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
                    backgroundImageIcon = new ImageIcon(scaledImage);
                    backgroundImageLabel.setIcon(backgroundImageIcon);
                } catch (Exception e) {
                    System.out.println("Error loading the background image: " + e.getMessage());
                }
            } else {
                System.out.println("Background image not found!");
            }
        }
    }

    @Override
    public void createVendingMachine() {
        // Implement the specific details for creating a Special Vending Machine
        // For example, set the item slots, capacity, etc.
        // You can add additional components to the GUI as needed.
        // For this example, we've added buttons for various actions.
    }

    @Override
    public void vendingMachineFeatures() {
        // Implement the specific details for the features of the Special Vending Machine
        // For example, handling the purchase of items, displaying the available items, etc.
    }

    public static void main(String[] args) {
        // Create the SpecialVendingMachineGUI object
        SpecialVendingMachineGUI specialVendingMachineGUI = new SpecialVendingMachineGUI();

        // Set the background image (Change the path to your desired image)
        String imagePath = "C:\\Users\\user\\Downloads\\FInalVendingMachine-2\\FInalVendingMachine\\images\\icecreamgui.jpg";
        specialVendingMachineGUI.setBackgroundImage(imagePath);
    }
}
