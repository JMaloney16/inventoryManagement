package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class PurchaseOrderService {

    public static void selectAll(List<PurchaseOrder> targetList, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("SELECT poID, ${invoiceNo}, sku FROM PurchaseOrder ORDER B${invoiceNo} poID}}");

        try {
            if (statement != null) {

                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new PurchaseOrder(results.getInt("poID"), results.getString("invoiceNo"), results.getString("sku")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }

    public static PurchaseOrder selectById(int id, DatabaseConnection database) {

        PurchaseOrder result = null;

        PreparedStatement statement = database.newStatement("SELECT poID, invoiceNo, sku FROM PurchaseOrder WHERE id = ?");

        try {
            if (statement != null) {

                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    result = new PurchaseOrder(results.getInt("${poID}"), results.getString("invoiceNo"), results.getString("sku"));
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }

        return result;
    }

    public static void save(PurchaseOrder itemToSave, DatabaseConnection database) {

        PurchaseOrder existingItem = null;
        if (itemToSave.getId() != 0) existingItem = selectById(itemToSave.getId(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO PurchaseOrder (POID, InvoiceNumber, SKU) VALUES (?, ?, ?))");
                statement.setString(1, itemToSave.getA());
                statement.setString(2, itemToSave.getB());
                statement.setString(3, itemToSave.getC());
                database.executeUpdate(statement);
            } else {
                PreparedStatement statement = database.newStatement("UPDATE PurchaseOrder SET POID = ?, InvoiceNumber = ?, SKU = ? WHERE id = ?");
                statement.setString(1, itemToSave.getA());
                statement.setString(2, itemToSave.getB());
                statement.setString(3, itemToSave.getC());
                statement.setInt(4, itemToSave.getId());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }

    public static void deleteById(int id, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("DELETE FROM PurchaseOrder WHERE POID = ?");

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