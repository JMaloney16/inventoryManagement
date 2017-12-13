package Controllers;

import Model.DatabaseConnection;
import Model.Stock;
import Model.Supplier;
import Model.stockService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.Optional;

public class mainController {

    @FXML
    TextField sku;
    @FXML
    TableView stockTable;
    @FXML
    TableView supplierTable;
    @FXML
    ListView<Stock> aStockList;

    private DatabaseConnection database;
    private ArrayList<Stock> stockArrayList = new ArrayList<>();
    private ArrayList<Supplier> supplierArrayList = new ArrayList<>();

    // Connects the database to the application, will not run till called from a menu button as defined in the FXML
    public void connectDatabase(){
        System.out.println("Connecting to database");
        database = new DatabaseConnection("inventory.db");
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
    // Used to select a new database file on the system
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

    public void updateTables(int selectedStockId, int selectedSupplierId){
        stockArrayList.clear();
        stockService.selectAll(stockArrayList, database);
        System.out.println("StockArrayList: " + stockArrayList);
        aStockList.setItems(FXCollections.observableArrayList(stockArrayList));

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
    }

    public void exitPrompt(WindowEvent we) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Pizza Project");
        alert.setHeaderText("Are you sure you want to exit?");

        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            database.disconnect();
            System.exit(0);
        } else {
            we.consume();
        }

    }


}
