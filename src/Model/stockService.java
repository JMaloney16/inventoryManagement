package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class stockService {

    public static void selectAll(List<Stock> destination, DatabaseConnection database){
        PreparedStatement statement = database.newStatement("SELECT SKU, Name, Quantity, Category, SupplierID FROM Stock ORDER BY SKU");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);
                if(results != null) {
                    while (results.next()){
                        destination.add(new Stock(
                                results.getInt("SKU"),
                                results.getString("Name"),
                                results.getInt("Quantity"),
                                results.getString("Category"),
                                results.getInt("SupplierID")
                        ));
                    }
                }
            }
        } catch (SQLException resultsException){
            System.out.println("Database select all error: " + resultsException.getMessage());
        }

    }
    public static Stock selectById(int id, DatabaseConnection database){
        Stock result = null;

        PreparedStatement statement = database.newStatement("SELECT sku, name, quantity, category, SupplierID FROM Stock WHERE sku = ?");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results != null){
                    result = new Stock(results.getInt("sku"), results.getString("name"), results.getInt("quantity"), results.getString("category"), results.getInt("SupplierID"));
                }
            }
        } catch (SQLException resultsException){
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }

        return result;
    }
    public static void save(Stock itemToSave, DatabaseConnection database){
        Stock existingItem = null;
        if (itemToSave.getSku() != 0){
            existingItem = selectById(itemToSave.getSku(), database);
        }

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Stock (sku, name, quantity, category, SupplierID) VALUES (?, ?, ?, ?, ?)");
                statement.setInt(1, itemToSave.getSku());
                statement.setString(2, itemToSave.getName());
                statement.setInt(3, itemToSave.getQuantity());
                statement.setString(4, itemToSave.getCategory());
                statement.setInt(5, itemToSave.getSupplierID());
                database.executeUpdate(statement);

            } else {
                PreparedStatement statement = database.newStatement("UPDATE Stock SET sku = ?, name = ?, quantity = ?, category = ?, SupplierID = ? WHERE sku = ?");
                statement.setInt(1, itemToSave.getSku());
                statement.setString(2, itemToSave.getName());
                statement.setInt(3, itemToSave.getQuantity());
                statement.setString(4, itemToSave.getCategory());
                statement.setInt(5, itemToSave.getSupplierID());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }
    public static void deleteById(int id, DatabaseConnection database){
        PreparedStatement statement = database.newStatement("DELETE FROM Stock WHERE sku = ?");

        try {
            if (statement != null) {
                statement.setInt(1, id);
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database deletion error: " + resultsException.getMessage());
        }
    }


}
