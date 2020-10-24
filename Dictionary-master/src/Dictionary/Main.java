package Dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller t = new Controller();
        t.actions();
        Parent root = FXMLLoader.load(getClass().getResource("InterFace.fxml"));
        primaryStage.setTitle("Dictionary");

        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("main.css").toExternalForm());

        Image dictionary = new Image("/Dictionary/images/dictionary.png");
        primaryStage.getIcons().add(dictionary);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}