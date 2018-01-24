package View;

import Controllers.mainController;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png"))) ;
        primaryStage.setOnCloseRequest(event -> { // This is the code for the dialog box to close the program
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit Inventory Management");
            alert.setHeaderText("Are you sure you want to exit?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                mainController.disconnectDB(); // Safely disconnects from the database
                System.exit(0);
            } else {
                event.consume();
            }
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
