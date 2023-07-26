import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
    Description: The Main class represents the entry point of the program and provides a menu for creating and testing vending machines.
*/
public class Main {
    protected List<VendingMachine> vms;
    protected VendingMachine vm;
    public int selected;
    private Scanner scn;
    public JFrame mainFrame;
    public JButton mainMenuButton;
    public Main() {
        this.vms = new ArrayList<>();
        this.selected = -1;
        this.scn = new Scanner(System.in);
        this.mainFrame = new JFrame("Vending Machine Factory");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.mainFrame.setSize(300, 700);
        this.mainFrame.setVisible(true);
        this.mainMenuButton = new JButton("MainMenu");
    }

    public void MainMenu(Main main) {
        mainFrame.getContentPane().removeAll();
        JLabel menuLabel = new JLabel("MENU");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(menuLabel);
        panel.setPreferredSize(new Dimension(220, 30));

        mainFrame.add(panel);
        JButton createButton = new JButton("Create");
        createButton.setPreferredSize(new Dimension(220, 30));
        JButton testButton = new JButton("Test");
        testButton.setPreferredSize(new Dimension(220, 30));
        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(220, 30));

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                createMenu(main);
            }    
        });

        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                testMenu(main);
            }    
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                exit();
            }    
        });

        mainFrame.add(createButton);
        mainFrame.add(testButton);
        mainFrame.add(exitButton);
        mainFrame.setVisible(true);

        mainFrame.revalidate();
        mainFrame.repaint();

        System.out.println("+-------------------------------------------------+");
        System.out.println("| MENU                                            |");
        System.out.println("| [1] Create Vending Machine                      |");
        System.out.println("| [2] Test Vending Machine                        |");
        System.out.println("| [0] EXIT                                        |");
        System.out.println("+-------------------------------------------------+");
        System.out.print(">> ");

        int option=-1;
        if(selected==-1||!(selected==1||selected==2||selected==0)){
            try {
                option = scn.nextInt();
                scn.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scn.nextLine();
            }

            switch (option) {
                    case 1 -> {
                        createMenu(main);
                    }
                    case 2 -> {
                        testMenu(main);
                    }
                    case 0 -> {
                        exit();
                        System.out.println("Exiting program...");
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Invalid option");
                    }
                }
        }
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public JButton goToMainMenu(Main main){
        mainMenuButton.setPreferredSize(new Dimension(220, 30));
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = 0;
                MainMenu(main);
            }
        });
        mainFrame.add(mainMenuButton);
        mainFrame.revalidate();
        mainMenuButton.repaint();
        return mainMenuButton;
    }

    public void createMenu(Main main) {

        System.out.println("Creating...\n");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| Choose Vending Machine type:                    |");
        System.out.println("| [1] Regular                                     |");
        System.out.println("| [2] Special                                     |");
        System.out.println("+-------------------------------------------------+");
        System.out.print(">> ");
        
        selected = 1;
        mainFrame.getContentPane().removeAll();
        
        JLabel creatLabel = new JLabel("Choose Vending Machine Type");
        JButton regButton = new JButton("Regular");
        JButton specButton = new JButton("Special");

        regButton.setPreferredSize(new Dimension(220, 30));
        specButton.setPreferredSize(new Dimension(220, 30));
        

        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = 3;
                vm = new RegularVendingMachine();
                vm.createVendingMachine(main);
                vms.add(vm);
            }
        });

        specButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = 4;
                vm = new SpecialVendingMachine();
                vm.createVendingMachine(main);
                vms.add(vm);
            }
        });

        

        JPanel createPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        createPanel.add(creatLabel);
        createPanel.setPreferredSize(new Dimension(220, 30));
        
        mainFrame.add(createPanel);
        mainFrame.add(regButton);
        mainFrame.add(specButton);
        mainFrame.add(goToMainMenu(main));
        mainFrame.setVisible(true);

        main.mainFrame.revalidate();
        main.mainFrame.repaint();
        

        if(selected!=1) {
            int type = -1;
            try {
                type = scn.nextInt();
                scn.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scn.nextLine();
            }

            if(type == 1) {
                vm = new RegularVendingMachine();
                vm.createVendingMachine();
            }
            else if (type == 2) {
                vm = new SpecialVendingMachine();
                vm.createVendingMachine();
            }
            else System.out.println("Invalid option");

            if(type==1 || type==2) {
                vms.add(vm);
            }
        }
    }

    public void createMenu() {
        System.out.println("Creating...\n");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| Choose Vending Machine type:                    |");
        System.out.println("| [1] Regular                                     |");
        System.out.println("| [2] Special                                     |");
        System.out.println("+-------------------------------------------------+");
        System.out.print(">> ");
        
        selected = 1;
        mainFrame.getContentPane().removeAll();
        
        JLabel creatLabel = new JLabel("Choose Vending Machine Type");
        JButton regButton = new JButton("Regular");
        JButton specButton = new JButton("Special");
        JButton backButton = new JButton("MainMenu");

        regButton.setPreferredSize(new Dimension(220, 30));
        specButton.setPreferredSize(new Dimension(220, 30));
        backButton.setPreferredSize(new Dimension(220, 30));

        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = 3;
                vm = new RegularVendingMachine();
                vm.createVendingMachine();
                vms.add(vm);
            }
        });

        specButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = 4;
                vm = new SpecialVendingMachine();
                vm.createVendingMachine();
                vms.add(vm);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = 0;
                mainFrame.getContentPane().removeAll();
                
            }
        });

        JPanel createPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        createPanel.add(creatLabel);
        createPanel.setPreferredSize(new Dimension(220, 30));

        mainFrame.add(createPanel);
        mainFrame.add(regButton);
        mainFrame.add(specButton);
        mainFrame.add(backButton);
        mainFrame.setVisible(true);

        mainFrame.revalidate();
        mainFrame.repaint();

        if(selected!=1) {
            int type = -1;
            try {
                type = scn.nextInt();
                scn.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scn.nextLine();
            }

            if(type == 1) {
                vm = new RegularVendingMachine();
                vm.createVendingMachine();
            }
            else if (type == 2) {
                vm = new SpecialVendingMachine();
                vm.createVendingMachine();
            }
            else System.out.println("Invalid option");

            if(type==1 || type==2) {
                vms.add(vm);
            }
        }
    }

    public void testMenu(Main main) {
        System.out.println("[1] Test Current Vending Machine");
        System.out.println("[2] Test Another Vending Machine");
        System.out.print(">> ");
        selected = 2;
        mainFrame.getContentPane().removeAll();

        JLabel testLabel = new JLabel("Testing Vending Mmachine");
        JButton currentButton = new JButton("Current VM");
        JButton anotherButton = new JButton("Another VM");
        JButton backButton = new JButton("MainMenu");

        currentButton.setPreferredSize(new Dimension(220, 30));
        anotherButton.setPreferredSize(new Dimension(220, 30));
        backButton.setPreferredSize(new Dimension(220, 30));

        currentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vm == null) {
                    JDialog warningDialog = new JDialog(mainFrame, "Warning");
                    warningDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    JLabel warnMessLabel = new JLabel("No Vending Maching has been created yet");
                    JPanel warnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    warnPanel.add(warnMessLabel);
                    warnPanel.setPreferredSize(new Dimension(220, 30));

                    warningDialog.setContentPane(warnPanel);
                    warningDialog.setPreferredSize(new Dimension(300, 100));
                    warningDialog.pack();
                    warningDialog.setLocationRelativeTo(mainFrame);
                    warningDialog.setVisible(true);
                }
                else
                vm.testVendingMachine(main);
                main.mainFrame.revalidate();
                main.mainFrame.repaint();
            }
        });

        anotherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vms.size()>1) {
                    for(int i = 1 ; i<vms.size(); i++){
                        JLabel label = new JLabel(vms.get(i).name);
                        JPanel panel = new JPanel();
                        panel.add(label);
                        panel.setPreferredSize(new Dimension(220,30));    
                        mainFrame.add(panel);
                        main.mainFrame.revalidate();
                        main.mainFrame.repaint();
                    }
                }
            }
        });

        JPanel testPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        testPanel.add(testLabel);
        testPanel.setPreferredSize(new Dimension(220, 30));

        mainFrame.add(testPanel);
        mainFrame.add(currentButton);
        mainFrame.add(anotherButton);
        mainFrame.add(goToMainMenu(main));
        mainFrame.setVisible(true);
        
        mainFrame.revalidate();
        mainFrame.repaint();

        if(selected!=2) {
            int choice;
            choice = scn.nextInt();
            if(choice==1) {
                if(vm == null) {
                    System.out.println("No Vending Machine has been created yet.");
                }
                vm.testVendingMachine();
            }
            else if(choice==2) {
                if(vms.size()>0) {
                    int j, test;

                    for(int i=0; i<vms.size(); i++) {
                        j = i+1;
                        System.out.println("["+j+"] "+vms.get(i).name+" Vending Machine");
                    }
                    do{
                        System.out.print(">> ");
                        test = scn.nextInt();
                    } while(!(test>0&&test<=vms.size()));

                    test--;
                    vms.get(test).testVendingMachine();
                }
                else {
                    System.out.println("No Vending Machine has been created yet");
                }
            }
        }
    }
    
    public void exit() {
        selected = 0;
        mainFrame.dispose();
    }
    /*
        Description: The main method is the entry point of the program.
        Parameters:
            @param args - The command line arguments
    */
    public static void main(String[] args) {
        Main main = new Main();
        VendingMachine vm = null;
        
        main.MainMenu(main);
    }
}