package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class PurchaseOrderDetailsService {

    public static void selectAll(List<PurchaseOrderDetails> targetList, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("SELECT invoiceNo, supplierID, cost, shippingDate FROM PurchaseOrderDetails ORDER By invoiceNo");

        try {
            if (statement != null) {

                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new PurchaseOrderDetails(results.getInt("invoiceNo"), results.getInt("supplierID"), results.getInt("cost"), results.getDate("Shipping Date")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }

    public static PurchaseOrderDetails selectById(int id, DatabaseConnection database) {

        PurchaseOrderDetails result = null;

        PreparedStatement statement = database.newStatement("SELECT invoiceNo, supplierID, cost FROM PurchaseOrderDetails WHERE id = ?");

        try {
            if (statement != null) {

                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    result = new PurchaseOrderDetails(results.getInt("invoiceNo"), results.getInt("supplierID"), results.getInt("cost"), results.getDate("shippingDate"));
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }

        return result;
    }

    public static void save(PurchaseOrderDetails itemToSave, DatabaseConnection database) {

        PurchaseOrderDetails existingItem = null;
        if (itemToSave.getInvoiceNo() != 0) existingItem = selectById(itemToSave.getInvoiceNo(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO PurchaseOrderDetails (Invoice Number, SupplierID, Total Cost, Shipping Date) VALUES (?, ?, ?, ?))");
                statement.setInt(1, itemToSave.getInvoiceNo());
                statement.setInt(2, itemToSave.getSupplierID());
                statement.setInt(3, itemToSave.getCost());
                database.executeUpdate(statement);
            } else {
                PreparedStatement statement = database.newStatement("UPDATE PurchaseOrderDetails SET Invoice Number = ?, SupplierID = ?, Total Cost = ?, Shipping Date = ? WHERE Invoice Number = ?");
                statement.setInt(1, itemToSave.getInvoiceNo());
                statement.setInt(2, itemToSave.getSupplierID());
                statement.setInt(3, itemToSave.getCost());
                statement.setDate(4, itemToSave.getShippingDate());
                statement.setInt(5, itemToSave.getInvoiceNo());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }

    public static void deleteById(int id, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("DELETE FROM PurchaseOrderDetails WHERE invoiceNo = ?");

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