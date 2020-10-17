package Dictionary;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application{

    DictionaryCommandLine app = new DictionaryCommandLine();
    @FXML
    javafx.scene.control.TextArea textSearch = new javafx.scene.control.TextArea();

    @FXML
    javafx.scene.control.Button buttonSearch = new javafx.scene.control.Button();

    @FXML
    public ListView<String> listView = new ListView<String>();

    @FXML
    javafx.scene.control.Label label = new javafx.scene.control.Label();

    @Override
    public void start(Stage primaryStage) throws Exception{
        app.size.insertFromFile();
        Parent root = FXMLLoader.load(getClass().getResource("InterFace.fxml"));
        primaryStage.setTitle("GGH");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void pressSearch() throws Exception {
        String wordSearch = textSearch.getText();
        String wordFound = app.size.dictionaryLookup(wordSearch);
        showText(wordFound);
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
        listView.setVisible(true);
    }

    public void searching() {
        textSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!(newValue.trim().isEmpty())) {
                ArrayList<String> lookuping = app.dictionaryLookuping(newValue);
                if(lookuping.size() > 0) {
                    ObservableList<String> temp = FXCollections.observableArrayList();
                    for (int i=0; i<lookuping.size(); i++) {
                        temp.add(lookuping.get(i));
                    }
                    listView.setItems(temp);
                    listView.setVisible(true);
                }
            }
            else {
                listView.setVisible(false);
            }
        });
    }
}