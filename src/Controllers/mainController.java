package Controllers;

import Model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

public class mainController {

    @FXML
    TextField stockSku;
    @FXML
    TextField stockName;
    @FXML
    TextField stockSupplier;
    @FXML
    TextField stockQuantity;
    @FXML
    TextField stockCategory;

    @FXML
    TextField supplierID;
    @FXML
    TextField supplierName;
    @FXML
    TextField supplierAddress;
    @FXML
    TextField supplierCity;
    @FXML
    TextField supplierPostcode;
    @FXML
    TextField supplierPhone;
    @FXML
    TextField supplierEmail;

    @FXML
    TableView<Stock> stockTable;
    @FXML
    TableView<Supplier> supplierTable;
    @FXML
    ListView<Stock> aStockList;

    @FXML
    TableColumn skuColumn;
    @FXML
    TableColumn stockNameColumn;
    @FXML
    TableColumn stockSupplierIDColumn;
    @FXML
    TableColumn quantityColumn;
    @FXML
    TableColumn categoryColumn;
    @FXML
    TableColumn supplierIDColumn;
    @FXML
    TableColumn supplierNameColumn;
    @FXML
    TableColumn supplierAddressColumn;
    @FXML
    TableColumn supplierCityColumn;
    @FXML
    TableColumn supplierPostcodeColumn;
    @FXML
    TableColumn supplierPhoneColumn;
    @FXML
    TableColumn supplierEmailColumn;

    @FXML
    public void initialize(){
        skuColumn.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("sku"));
        stockNameColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("name"));
        stockSupplierIDColumn.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("supplierID"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("quantity"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("category"));

        supplierIDColumn.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("supplierID"));
        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
        supplierAddressColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("address"));
        supplierCityColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("city"));
        supplierPostcodeColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("postcode"));
        supplierPhoneColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("phoneNo"));
        supplierEmailColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("email"));
        connectDatabase();
    }

    private static DatabaseConnection database;

    private ArrayList<Stock> stockArrayList = new ArrayList<>();
    private ArrayList<Supplier> supplierArrayList = new ArrayList<>();
    private String databaseFile = ("inventory.db");
    private String userFile;
    // Connects the database to the application, will not run till called from a menu button as defined in the FXML

    public void connectDatabase(){
        System.out.println("Connecting to database");
        database = new DatabaseConnection(databaseFile);
        updateTables(0, 0);
    }
    // Called on buttons where their action is incomplete
    public void workInProgress(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Work in progress!");
        alert.setHeaderText(null);
        alert.setContentText("This feature is Work-In-Progress (WIP), sorry!");
        alert.showAndWait();
    }
    // Used to select a new database file on the system - does not work and has been replaced by openFileDialog
    public void openFile(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/file.fxml"));
            Parent fileRoot = (Parent) fxmlLoader.load();
            Stage fileStage = new Stage();
            fileStage.setTitle("Select File");
            fileStage.setScene(new Scene(fileRoot, 600, 300));
//            fileStage.initStyle(StageStyle.UNDECORATED);
            fileStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    // Current solution to select a new database file
    public void openFileDialog() {
        TextInputDialog dialog = new TextInputDialog("inventory"); /* The textfield the user edits which
        defaults to "inventory" which is the name of the default database file */
        dialog.setTitle("Choose Inventory File");
        dialog.setHeaderText("Please enter a file name (without file extension)");
        dialog.setContentText("File: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) { // If the user closes the dialog then no changes are made to the file path
            userFile = (result.get() + ".db"); // Adds the .db file extension to the user input
            File fileCheck = new File(userFile);
            if (fileCheck.isFile()) {
                System.out.println(userFile); // Outputs the new file name to the console for testing
                databaseFile = userFile;
            } else {
                String message = ("Not a valid file!");
                System.out.println(message);
                errorDialog(message);
            }
        }

    }

    public void updateTables(int selectedStockId, int selectedSupplierId){
        stockArrayList.clear();
        supplierArrayList.clear(); // Clears the current list of items for each of the tables
        stockService.selectAll(stockArrayList, database);
        System.out.println("StockArrayList: " + stockArrayList);
        aStockList.setItems(FXCollections.observableArrayList(stockArrayList));
        stockTable.setItems(FXCollections.observableArrayList(stockArrayList)); // Set the stock table and list to the
        SupplierService.selectAll(supplierArrayList, database);
        System.out.println("supplierArrayList: " + supplierArrayList);
        supplierTable.setItems(FXCollections.observableArrayList(supplierArrayList));

        if(selectedStockId != 0) {
            for (int n = 0; n < aStockList.getItems().size(); n++){
                if (aStockList.getItems().get(n).getSku() == selectedStockId) {
                    aStockList.getSelectionModel().select(n);
                    aStockList.getFocusModel().focus(n);
                    aStockList.scrollTo(n);
                    break;
                }
            }
        }

        if(selectedSupplierId != 0){
            for (int n = 0; n < supplierTable.getItems().size(); n++){
                if (supplierTable.getItems().get(n).getSupplierID() == selectedStockId) {
                    supplierTable.getSelectionModel().select(n);
                    supplierTable.getFocusModel().focus(n);
                    supplierTable.scrollTo(n);
                    break;
                }
            }
        }
    }

    public void loadStockRow() {
        if (stockTable.getSelectionModel().getSelectedItem() != null){
            Stock selectedStock = stockTable.getSelectionModel().getSelectedItem(); // Get the selected item from the table
            stockSku.setText(String.valueOf(selectedStock.getSku()));
            stockName.setText(selectedStock.getName());
            stockQuantity.setText(String.valueOf(selectedStock.getQuantity()));
            stockCategory.setText(selectedStock.getCategory());
        }
    }

    public void loadSupplierRow(){
        if (supplierTable.getSelectionModel().getSelectedItem() != null) {
            Supplier selectedSupplier = supplierTable.getSelectionModel().getSelectedItem(); // Get the selected item from the table
            supplierID.setText(String.valueOf(selectedSupplier.getSupplierID())); // Set the value of the textfields
            supplierName.setText(selectedSupplier.getName());
            supplierAddress.setText(String.valueOf(selectedSupplier.getAddress()));
            supplierCity.setText(selectedSupplier.getCity());
            supplierPostcode.setText(selectedSupplier.getPostcode());
            supplierPhone.setText(selectedSupplier.getPhoneNo());
            supplierEmail.setText(selectedSupplier.getEmail());
        }
    }

    public void updateStock(){
        Stock selectedStock = stockTable.getSelectionModel().getSelectedItem();
        if (selectedStock != null) {
            stockService.deleteById(Integer.parseInt(stockSku.getText()), database);
            selectedStock.setSku(Integer.parseInt(stockSku.getText()));
            selectedStock.setName(stockName.getText());
            selectedStock.setQuantity(Integer.parseInt(stockQuantity.getText()));
            selectedStock.setCategory(stockCategory.getText());
            System.out.println(selectedStock);
            stockService.save(selectedStock, database); // Update the database table
            updateTables(selectedStock.getSku(),0); // Refresh changes
        } else {
            String message = ("Please select an already existing stock item first");
            System.out.println(message);
            errorDialog(message);
        }
    }

    public void updateSuppliers(){
            Supplier selectedSupplier = supplierTable.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            SupplierService.deleteById(Integer.parseInt(supplierID.getText()), database);
            selectedSupplier.setSupplierID(Integer.parseInt(supplierID.getText()));
            selectedSupplier.setName(supplierName.getText());
            selectedSupplier.setAddress(supplierAddress.getText());
            selectedSupplier.setCity(supplierCity.getText());
            selectedSupplier.setPostcode(supplierPostcode.getText());
            selectedSupplier.setPhoneNo(supplierPhone.getText());
            selectedSupplier.setEmail(supplierEmail.getText());
            System.out.println(selectedSupplier);
            SupplierService.save(selectedSupplier, database); // Update the SQL database table
            updateTables(0,selectedSupplier.getSupplierID()); // Refresh changes
        } else {
            String message = ("Please select an already existing supplier item first");
            System.out.println(message);
            errorDialog(message);
        }
    }

    public void deleteStock() {
        Stock selectedStock = stockTable.getSelectionModel().getSelectedItem();
        if (selectedStock != null) {
            stockService.deleteById(selectedStock.getSku(), database); // Deletes the selected item
            updateTables(0, 0);
        } else {
            String message = ("No stock item has been selected!");
            System.out.println(message);
            errorDialog(message);
        }
    }

    public void deleteSupplier(){
        Supplier selectedSupplier = supplierTable.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            SupplierService.deleteById(selectedSupplier.getSupplierID(), database);
            updateTables(0, 0);
        }else{
            String message = ("No supplier item has been selected!");
            System.out.println(message);
            errorDialog(message);
        }
    }

    public void errorDialog(String message){ // Creates a dialog popup that informs the user of their error
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("There has been a runtime error!");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void disconnectDB(){ // Allows the database to be disconnected when closing the program
        database.disconnect();
    }

}
