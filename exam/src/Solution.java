import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class Solution extends Application {
    public Stage window;
    public Scene scene;
    public Button button;
    ListView<String> listView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("GGH");
        button = new Button("Search");

        listView = new ListView<>();
        listView.getItems().addAll("a","b","c","d");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(listView,button);

        scene = new Scene(layout,300,300);
        window.setScene(scene);
        window.show();
    }
}