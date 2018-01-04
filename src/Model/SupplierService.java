package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SupplierService {

    public static void selectAll(List<Supplier> destination, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("SELECT supplierID, name, address, city, postcode, phoneNo, email FROM Supplier ORDER BY supplierID");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        destination.add(new Supplier(results.getInt("supplierID"), results.getString("name"), results.getString("address"), results.getString("city"), results.getString("postcode"),results.getString("phoneNo"), results.getString("email")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }

    }

    public static Supplier selectById(int id, DatabaseConnection database) {
        Supplier result = null;

        PreparedStatement statement = database.newStatement("SELECT supplierID, name, address, city, postcode, phoneNo, email FROM Supplier WHERE supplierID = ?");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    result = new Supplier(results.getInt("supplierID"), results.getString("name"), results.getString("address"), results.getString("city"), results.getString("postcode"),results.getString("phoneNo"), results.getString("email"));
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }

        return result;
    }

    public static void save(Supplier itemToSave, DatabaseConnection database) {
        Supplier existingItem = null;
        if (itemToSave.getSupplierID() != 0) {
            existingItem = selectById(itemToSave.getSupplierID(), database);
        }

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Supplier (supplierID, name, address, city, postcode, phoneNo, email) VALUES (?, ?, ?, ?, ?, ?, ?)");
                statement.setInt(1, itemToSave.getSupplierID());
                statement.setString(2, itemToSave.getName());
                statement.setString(3, itemToSave.getAddress());
                statement.setString(4, itemToSave.getCity());
                statement.setString(5, itemToSave.getPostcode());
                statement.setString(6, itemToSave.getPhoneNo());
                statement.setString(7, itemToSave.getEmail());
                database.executeUpdate(statement);

            } else {
                PreparedStatement statement = database.newStatement("UPDATE Supplier SET supplierID = ?, phoneNo = ?, name = ?, address = ?, city = ?, postcode = ?, phoneNo = ?, email = ? WHERE supplierID = ?");
                statement.setInt(1, itemToSave.getSupplierID());
                statement.setString(2, itemToSave.getName());
                statement.setString(3, itemToSave.getAddress());
                statement.setString(4, itemToSave.getCity());
                statement.setString(5, itemToSave.getPostcode());
                statement.setString(6, itemToSave.getPhoneNo());
                statement.setString(7, itemToSave.getEmail());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }

    public static void deleteById(int id, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("DELETE FROM Supplier WHERE supplierID = ?");

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
