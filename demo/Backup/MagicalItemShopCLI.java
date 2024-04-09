package com.magicstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MagicalItemShopCLI {

    private Scanner scanner = new Scanner(System.in);
    private final String DB_URL = "jdbc:mysql://localhost:3306/invManagement";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "password";
    private Connection connection;

    int current_minimum_Danger = -1;
    int current_maximum_Danger = -1;
    int selected_minimum_Danger = -1;
    int selected_maximum_Danger = -1;

    double  current_minimum_Price = -1;
    double current_maximum_Price = -1;
    int selected_minimum_Price = -1;
    int selected_maximum_Price = -1;
    public boolean reset_search_flag=false;
    ArrayList<String> selectedMagic = new ArrayList<>();
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    Scanner selectScanner;

    public MagicalItemShopCLI() {
        createConnection();
    }

    private void createConnection() {
        try {
            // Load the MySQL Connector driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the database!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Connector for Java not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MagicalItemShopCLI cli = new MagicalItemShopCLI();
        cli.displayMenu();
    }

    public void displayMenu() {
        System.out.println("\n \n \n Welcome to William's Magical Item Shop!");
        System.out.println("Choose an option:");
        System.out.println("1. Purchase items from a customer");
        System.out.println("2. View current inventory");
        System.out.println("3. Save search results to a file");
        System.out.println("4. Generate monthly sales report");
        System.out.println("0. Exit");

        int choice = getUserInput(0, 4);
        switch (choice) {
            case 1:
                workflowOne();
                break;
            case 2:
                workflowTwo();
                break;
            case 3:
                workflowThree();
                break;
            case 4:
                workflowFour();
                break;
            case 0:
                closeConnection();
                System.out.println("Exiting program. Goodbye!");
                break;
        }
    }

    private void workflowOne() {

        new MagicalItemShopUtils().purchaseItemsFromCustomer(this.getConnection());

        displayMenu();
    }

    private void workflowTwo() {
        
        if(reset_search_flag==true){
                int current_minimum_Danger = -1;
                int current_maximum_Danger = -1;
                int selected_minimum_Danger = -1;
                int selected_maximum_Danger = -1;

                double  current_minimum_Price = -1;
                double current_maximum_Price = -1;
                int selected_minimum_Price = -1;
                int selected_maximum_Price = -1;
                ArrayList<String> selectedMagic = new ArrayList<>();


            
        }



        System.out.println("Choose an option:");
        System.out.println("1. Filter By Danger Level");
        if (selected_maximum_Danger != -1 && selected_minimum_Danger != -1) {
            System.out.println("Current Filter" + selected_minimum_Danger + "-" + selected_maximum_Danger);
        }
        System.out.println("2. Filter by Magic Type");

        if (!selectedMagic.isEmpty()) {
            System.out.println("Current Selected Magic:" + selectedMagic);

        }
        System.out.println("3. Filter by Price");
        System.out.println("4. Begin Search");

        System.out.println("0. Exit Search");
        int choice = getUserInput(0, 4);
        switch (choice) {
            case 1:

                HashMap<String, Integer> dangerLevels = new MagicalItemShopUtils()
                        .getMinAndMaxDangerLevel(this.getConnection());
                current_minimum_Danger = dangerLevels.get("Min");
                current_maximum_Danger = dangerLevels.get("Max");
                System.out.println("Enter Danger Level Minimum," + "Current Min=" + current_minimum_Danger);
                selected_minimum_Danger = getUserInput(0, selected_maximum_Danger + 100);
                System.out.println("Enter Danger Level Mininmum" + "Current Max=" + current_maximum_Danger);
                selected_maximum_Danger = getUserInput(0, selected_maximum_Danger + 100);
                workflowTwo();
                break;
            case 2:
                ArrayList<String> avalibeMagic = new MagicalItemShopUtils().getAllUniqueMagic(this.getConnection());
                System.out.println("Select Magic Type:");
                for(int i=0;i<avalibeMagic.size();i++){
                    System.out.println((i+1)+"."+avalibeMagic.get(i));

                }
                System.out.println(avalibeMagic.toString());
                int selected_magic = getUserInput(1, avalibeMagic.size()+1);
                selectedMagic.add(avalibeMagic.get(selected_magic-1));
                reset_search_flag=false;
                workflowTwo();
                break;
            case 3:

                HashMap<String, Double> priceHashMap = new MagicalItemShopUtils().getMinAndMaxPrice(connection);
                current_minimum_Price = priceHashMap.get("Min");
                current_maximum_Price = priceHashMap.get("Max");
                System.out.println("Enter Danger Level Minimum," );
                selected_minimum_Price = getUserInput(0,500000);
                System.out.println("Enter Price Level Maximum" );
                selected_maximum_Price = getUserInput(0,500000);
                reset_search_flag=false;
                workflowTwo();


                break;
            case 4:
            ArrayList<String> searchResults = new MagicalItemShopUtils().searchItems(connection, current_minimum_Danger, current_maximum_Danger, selectedMagic, selected_minimum_Danger, selected_maximum_Danger);
            for (String result : searchResults) {
                System.out.println(result);
            }


            
            reset_search_flag=true;

                break;
            case 0:
            reset_search_flag=true;

                displayMenu();
                System.out.println("Exiting program. Goodbye!");
                break;
        }

    }

    private void workflowThree() {
        System.out.println("Workflow Three: Saving search results to a file");

        // Get search results
        ArrayList<String> searchResults = new MagicalItemShopUtils().searchItems(connection, current_minimum_Danger, current_maximum_Danger, selectedMagic, selected_minimum_Price, selected_maximum_Price);

        // Prompt user for filename
        System.out.print("Enter filename to save search results: ");
        String filename = scanner.next();

        // Save search results to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String result : searchResults) {
                writer.write(result);
                writer.newLine();
            }
            System.out.println("Search results saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving search results: " + e.getMessage());
        }

        displayMenu();
    }

    private void workflowFour() {
        System.out.println("Workflow Four: Generating monthly sales report");
    
        // Prompt user for the month and year of the report
        System.out.print("Enter the month (1-12): ");
        int month = getUserInput(1, 12);
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
    
        // Generate the filename for the report
        String filename = "sales_report_" + year + "_" + month + ".txt";
    
        // Generate the report and save it to the file
         new MagicalItemShopUtils().generateMonthlySalesReport(this.getConnection(),filename, month, year);
    
        System.out.println("Monthly sales report generated and saved to " + filename);
    
        displayMenu();
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
