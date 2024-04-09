package com.magicstore;


import java.util.Scanner;

public class MagicalItemShopPresentation {
    private Scanner scanner = new Scanner(System.in);
    private MagicalItemShopLogic logic;

    public MagicalItemShopPresentation() {
        logic = new MagicalItemShopLogic(this);
    }

    public void start() {
        displayMenu();
    }

    public void displayMenu() {
        System.out.println("\n \n \n Welcome to William's Magical Item Shop!");
        System.out.println("Choose an option:");
        System.out.println("1. Purchase items from a customer(Add Item to Inventory)");
        System.out.println("2. View current inventory");
        System.out.println("3. Save search results to a file");
        System.out.println("4. Generate monthly sales report");
        System.out.println("0. Exit");

        int choice = getUserInput(0, 4);
        switch (choice) {
            case 1:
                logic.workflowOne();
                this.displayMenu();

                break;
            case 2:
                logic.workflowTwo();
                this.displayMenu();

                break;
                
            case 3:
                logic.workflowThree();
                this.displayMenu();

                break;
            case 4:
                logic.workflowFour();
                this.displayMenu();
                break;
            case 0:
                logic.closeConnection();
                System.out.println("Exiting program. Goodbye!");
                break;
        }
    }

    private int getUserInput(int min, int max) {
        int choice;
        do {
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                System.out.print("Enter your choice: ");
                scanner.next(); // Consume invalid input
            }
            choice = scanner.nextInt();
        } while (choice < min || choice > max);
        return choice;
    }
}
