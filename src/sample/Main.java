import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage stage)throws Exception{
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add("stylesheet.css");

        stage.setTitle("Inventory Management");
        stage.setScene(scene);
        stage.show();

        VBox menuRoot = new VBox();

        MenuBar menu = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem chooseFile = new MenuItem("File Path");
        fileMenu.getItems().addAll(chooseFile);
        menu.getMenus().addAll(fileMenu);
        menuRoot.getChildren().add(menu);
        root.setCenter(menuRoot);

        TabPane tab = new TabPane();
        Tab stock = new Tab();
        stock.setText("Stock");
        stock.setClosable(false);
        Pane stockPane = new Pane();

        TableView stockTable = new TableView();
        stockTable.setPrefSize(600,696);

        stockPane.getChildren().add(stockTable);
        stock.setContent(stockPane);



        tab.getTabs().addAll(stock);
        root.setCenter(tab);

    }

    public static void main(String[] args){
        launch(args);
    }
}