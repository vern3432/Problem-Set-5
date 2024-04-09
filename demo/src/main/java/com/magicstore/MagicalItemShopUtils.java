package com.magicstore;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MagicalItemShopUtils {
    public  HashMap<String, Integer> getMinAndMaxDangerLevel(Connection connection) {
        HashMap<String, Integer> dangerLevels = new HashMap<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MIN(DangerLevel) AS MinDangerLevel, MAX(DangerLevel) AS MaxDangerLevel FROM Item");

            if (resultSet.next()) {
                int minDangerLevel = resultSet.getInt("MinDangerLevel");
                int maxDangerLevel = resultSet.getInt("MaxDangerLevel");

                dangerLevels.put("Min", minDangerLevel);
                dangerLevels.put("Max", maxDangerLevel);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dangerLevels;
    }

        public  ArrayList<String> getAllUniqueMagic(Connection connection) {
        ArrayList<String> magicTypes = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT MagicName FROM Magic");

            while (resultSet.next()) {
                String magicName = resultSet.getString("MagicName");
                magicTypes.add(magicName);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return magicTypes;
    }

    public  HashMap<String, Double> getMinAndMaxPrice(Connection connection) {
        HashMap<String, Double> prices = new HashMap<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MIN(PurchasePrice) AS MinPrice, MAX(PurchasePrice) AS MaxPrice FROM Item");

            if (resultSet.next()) {
                double minPrice = resultSet.getDouble("MinPrice");
                double maxPrice = resultSet.getDouble("MaxPrice");

                prices.put("Min", minPrice);
                prices.put("Max", maxPrice);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prices;
    }
    public ArrayList<String> searchItems(Connection connection, int minDanger, int maxDanger, ArrayList<String> magicTypes, double minPrice, double maxPrice) {
        ArrayList<String> results = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Item INNER JOIN Magic ON Item.MagicID = Magic.MagicID WHERE 1=1 ");

        // Add danger level conditions
        if (minDanger != -1 && maxDanger != -1) {
            queryBuilder.append("AND DangerLevel BETWEEN ").append(minDanger).append(" AND ").append(maxDanger).append(" ");
        }

        // Add magic type conditions
        if (!magicTypes.isEmpty()) {
            queryBuilder.append("AND (");
            for (int i = 0; i < magicTypes.size(); i++) {
                if (i > 0) {
                    queryBuilder.append(" OR ");
                }
                queryBuilder.append("MagicName = '").append(magicTypes.get(i)).append("'");
            }
            queryBuilder.append(") ");
        }

        // Add price conditions
        if (minPrice != -1 && maxPrice != -1) {
            queryBuilder.append("AND PurchasePrice BETWEEN ").append(minPrice).append(" AND ").append(maxPrice).append(" ");
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryBuilder.toString());

            while (resultSet.next()) {
                int itemId = resultSet.getInt("ItemID");
                String name = resultSet.getString("Name");
                int quantity = resultSet.getInt("Quantity");
                results.add("Item ID: " + itemId + ", Name: " + name + ", Quantity: " + quantity);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public  void purchaseItemsFromCustomer(Connection connection) {
        System.out.println("Workflow One: Purchasing items from a customer");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Item");

            while (resultSet.next()) {
                int itemID = resultSet.getInt("ItemID");
                String name = resultSet.getString("Name");
                int quantity = resultSet.getInt("Quantity");
                System.out.println("Item ID: " + itemID + ", Name: " + name + ", Quantity: " + quantity);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public  void generateMonthlySalesReport(Connection connection, String filename, int month, int year) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Query the database for sales data for the specified month and year
            String query = "SELECT s.SaleID, i.Name AS ItemName, c.Name AS CustomerName, s.SaleDate, s.SaleAmount, m.MagicName, i.DangerLevel " +
                           "FROM Sales s " +
                           "INNER JOIN Item i ON s.ItemID = i.ItemID " +
                           "INNER JOIN Customer c ON s.CustomerID = c.CustomerID " +
                           "INNER JOIN Magic m ON i.MagicID = m.MagicID " +
                           "WHERE MONTH(s.SaleDate) = " + month + " AND YEAR(s.SaleDate) = " + year;
    
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
    
            // Write the report header
            writer.write("Monthly Sales Report - " + month + "/" + year);
            writer.newLine();
            writer.newLine();
    
            // Write column headers
            writer.write("Sale ID\tItem Name\tCustomer Name\tSale Date\tSale Amount\tMagic Type\tDanger Level");
            writer.newLine();
    
            // Write sales data to the report
            while (resultSet.next()) {
                int saleID = resultSet.getInt("SaleID");
                String itemName = resultSet.getString("ItemName");
                String customerName = resultSet.getString("CustomerName");
                String saleDate = resultSet.getString("SaleDate");
                double saleAmount = resultSet.getDouble("SaleAmount");
                String magicType = resultSet.getString("MagicName");
                int dangerLevel = resultSet.getInt("DangerLevel");
    
                // Write the sales data to the report
                writer.write(saleID + "\t" + itemName + "\t" + customerName + "\t" + saleDate + "\t" + saleAmount + "\t" + magicType + "\t" + dangerLevel);
                writer.newLine();
            }
    
            // Close resources
            resultSet.close();
            statement.close();
        } catch (IOException | SQLException e) {
            System.out.println("Error generating monthly sales report: " + e.getMessage());
        }
    }


    


    public static void main(String[] args) {
        // try {
        //     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/invManagement", "linuxuser3", "password");
        //     HashMap<String, Integer> dangerLevels = getMinAndMaxDangerLevel(connection);
        //     System.out.println("Minimum Danger Level: " + dangerLevels.get("Min"));
        //     System.out.println("Maximum Danger Level: " + dangerLevels.get("Max"));
        //     connection.close();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
    }
}
