package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public static DatabaseConnection database;

    @Override
    public void start(Stage primaryStage) throws Exception{
        database = new DatabaseConnection("inventory.db");
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
        ArrayList<Stock> testList = new ArrayList<>();
        stockService.selectAll(testList, database);
        for (Stock s : testList) {
            System.out.println(s);
        }
    }


    public static void main(String[] args) {
        launch(args);

    }
}
