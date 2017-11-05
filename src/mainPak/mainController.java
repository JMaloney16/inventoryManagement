package mainPak;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class mainController {

    @FXML
    TextField sku;



    public void workInProgress(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Work in progress!");
        alert.setHeaderText(null);
        alert.setContentText("This feature is Work-In-Progress (WIP), sorry!");

        alert.showAndWait();
    }

    public void openFile(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("file.fxml"));
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


}
