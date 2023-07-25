import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/*
    Description: The Main class represents the entry point of the program and provides a menu for creating and testing vending machines.
*/
public class Main {
    private List<VendingMachine> vms;

    public Main() {
        this.vms = new ArrayList<>();
    }
    /*
        Description: The main method is the entry point of the program.
        Parameters:
            @param args - The command line arguments
    */
    public static void main(String[] args) {
        Main main = new Main();
        Scanner scn = new Scanner(System.in);
        VendingMachine vm = null;

        //Display menu on a loop
        while (true) {

            System.out.println("+-------------------------------------------------+");
            System.out.println("| MENU                                            |");
            System.out.println("| [1] Create Vending Machine                      |");
            System.out.println("| [2] Test Vending Machine                        |");
            System.out.println("| [0] EXIT                                        |");
            System.out.println("+-------------------------------------------------+");
            System.out.print(">> ");

            int option;
            try {
                option = scn.nextInt();
                scn.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scn.nextLine();
                continue;
            }

            switch (option) {
                case 1 -> {
                    System.out.println("Creating...\n");
                    System.out.println("+-------------------------------------------------+");
                    System.out.println("| Choose Vending Machine type:                    |");
                    System.out.println("| [1] Regular                                     |");
                    System.out.println("| [2] Special                                     |");
                    System.out.println("+-------------------------------------------------+");
                    System.out.print(">> ");

                    int type;
                    try {
                        type = scn.nextInt();
                        scn.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scn.nextLine();
                        continue;
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
                        main.vms.add(vm);
                    }
                }
                case 2 -> {
                    int choice;
                    //Check if Vending Machine has been initialized
                    System.out.println("[1] Test Current Vending Machine");
                    System.out.println("[2] Test Another Vending Machine");
                    System.out.print(">> ");
                    choice = scn.nextInt();
                    if(choice==1) {
                        if(vm == null) {
                            System.out.println("No Vending Machine has been created yet.");
                            continue;
                        }
                        vm.testVendingMachine();
                    }
                    else if(choice==2) {
                        if(main.vms.size()>0) {
                            int j, test;

                            for(int i=0; i<main.vms.size(); i++) {
                                j = i+1;
                                System.out.println("["+j+"] "+main.vms.get(i).name+" Vending Machine");
                            }
                            do{
                                System.out.print(">> ");
                                test = scn.nextInt();
                            } while(!(test>0&&test<=main.vms.size()));

                            test--;
                            main.vms.get(test).testVendingMachine();
                        }
                        else {
                            System.out.println("No Vending Machine has been created yet");
                        }
                    }

                }
                case 0 -> {
                    System.out.println("Exiting program...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid option");
                }
            }
        }

    }
}