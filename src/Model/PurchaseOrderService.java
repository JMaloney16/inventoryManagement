package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class PurchaseOrderService {

    public static void selectAll(List<PurchaseOrder> targetList, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("SELECT poID, invoiceNo, sku FROM PurchaseOrder ORDER By poID}}");

        try {
            if (statement != null) {

                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new PurchaseOrder(results.getInt("poID"), results.getInt("invoiceNo"), results.getInt("sku"), results.getInt("quantity"), results.getInt("unitCost")));
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
                    result = new PurchaseOrder(results.getInt("poID"), results.getInt("invoiceNo"), results.getInt("sku"), results.getInt("quantity"), results.getInt("unitCost"));
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }

        return result;
    }

    public static void save(PurchaseOrder itemToSave, DatabaseConnection database) {

        PurchaseOrder existingItem = null;
        if (itemToSave.getPoID() != 0) existingItem = selectById(itemToSave.getPoID(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO PurchaseOrder (POID, InvoiceNumber, SKU, Quantity, Unit Cost) VALUES (?, ?, ?, ?, ?))");
                statement.setInt(1, itemToSave.getPoID());
                statement.setInt(2, itemToSave.getInvoiceNo());
                statement.setInt(3, itemToSave.getSku());
                statement.setInt(4, itemToSave.getQuantity());
                statement.setInt(5, itemToSave.getUnitCost());
                database.executeUpdate(statement);
            } else {
                PreparedStatement statement = database.newStatement("UPDATE PurchaseOrder SET POID = ?, InvoiceNumber = ?, SKU = ?, Quantity = ?, Unit Cost = ? WHERE id = ?");
                statement.setInt(1, itemToSave.getPoID());
                statement.setInt(2, itemToSave.getInvoiceNo());
                statement.setInt(3, itemToSave.getSku());
                statement.setInt(4, itemToSave.getQuantity());
                statement.setInt(5, itemToSave.getUnitCost());
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