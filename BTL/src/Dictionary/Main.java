package Dictionary;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application{

    DictionaryCommandLine app = new DictionaryCommandLine();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("InterFace.fxml"));
        primaryStage.setTitle("GGH");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

        //readWord();


    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    javafx.scene.control.TextArea textSearch = new javafx.scene.control.TextArea();

    @FXML
    javafx.scene.control.Button buttonSearch = new javafx.scene.control.Button();

    @FXML
    ListView<String> listView = new ListView<String>();

    @FXML
    javafx.scene.control.Label label = new javafx.scene.control.Label();


    public void getText(javafx.event.ActionEvent event) throws Exception {
        String wordSearch = textSearch.getText();
        app.size.insertFromFile();
        String wordFound = app.size.dictionaryLookup(wordSearch);
        System.out.println(wordFound);
        System.out.println(wordSearch);
        showText(wordFound);
        readWord();
    }

    public void showText(String s) {
        label.setText(s);
    }

    public void readWord() throws Exception {
        int count = app.size.insertFromFile();
        ObservableList<String> list;
        ArrayList<String> taget = new ArrayList<String>();
        for(int i=0; i<count; i++) {
            String s = app.size.addWord.words.get(i).word_target;
            taget.add(s);
            System.out.println(s);
        }
        list = FXCollections.observableList(taget);
        listView.setItems(list);
    }
}