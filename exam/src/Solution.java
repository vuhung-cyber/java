import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class Solution extends Application {

    public Stage window;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("source.fxml"));
        window.setTitle("Test");
        Scene scene = new Scene(root);

        VBox layout = new VBox();
        ObservableList<String> items = FXCollections.observableArrayList (
                "Single", "Double", "Suite", "Family App");
        listView.setItems(items);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE  );

        layout.getChildren().addAll(listView,label);

        window.setScene(scene);
        window.show();
    }

    @FXML
    public Label label = new Label();
    public ListView<String> listView = new ListView<String>();
}