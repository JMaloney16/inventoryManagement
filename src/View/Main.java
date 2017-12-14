package View;

import Controllers.mainController;
import Model.DatabaseConnection;
import Model.Stock;
import Model.stockService;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

public class Main extends Application {
    private static mainController controller;

    //public static DatabaseConnection database;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(new Scene(root, 1200, 800));
        //primaryStage.setOnCloseRequest((WindowEvent we) -> mainController.exitPrompt(we));
        primaryStage.show();
        ArrayList<Stock> testList = new ArrayList<>();
        /*stockService.selectAll(testList, database);
        for (Stock s : testList) {
            System.out.println(s);
        }*/
    }


    public static void main(String[] args) {
        launch(args);

    }
}
