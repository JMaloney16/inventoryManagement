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

import java.util.ArrayList;

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


    public void connectDatabase(){
        System.out.println("Connecting to database");
        database = new DatabaseConnection("inventory.db");
        updateTables();
    }

    public void workInProgress(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Work in progress!");
        alert.setHeaderText(null);
        alert.setContentText("This feature is Work-In-Progress (WIP), sorry!");

        alert.showAndWait();
    }

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

    public void updateTables(){
        stockArrayList.clear();
        stockService.selectAll(stockArrayList, database);

        aStockList.setItems(FXCollections.observableArrayList(stockArrayList));
    }


}
