package Dictionary;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Main extends Application implements Initializable {
    DictionaryCommandLine app = new DictionaryCommandLine();

    @FXML
    private ListView<String> wordView;

    @FXML
    private Button buttonSearch = new Button();

    @FXML
    private Label label = new Label();

    @FXML
    private TextField textSearch = new TextField();

    @FXML
    private Button add = new Button();

    @FXML
    private Button delete = new Button();

    @FXML
    private Button change = new Button();

    @Override
    public void start(Stage primaryStage) throws Exception {
        app.dicManage.insertFromFile();
        Parent root = FXMLLoader.load(getClass().getResource("InterFace.fxml"));
        primaryStage.setTitle("Dictionary");

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            readWord();
            wordView.setVisible(true);
            getSelection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readWord() throws IOException {
        ObservableList list = FXCollections.observableArrayList();
        //app.dicManage.insertFromFile();
        for (int i = 0; i < app.dicManage.dictionary.words.size(); i++) {
            list.add(app.dicManage.dictionary.words.get(i).word_target);
        }
        wordView.getItems().addAll(list);
    }

    @FXML
    void pressSearch() {
        String wordSearch = textSearch.getText();
        String wordFound = app.dicManage.dictionaryLookup(wordSearch);
        showText(wordFound);
    }

    public void showText(String s) {
        label.setText(s);
    }

    @FXML
    void searching() {
        textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!(newValue.trim().isEmpty())) {
                ArrayList<String> lookUp = app.dictionarySearcher(newValue);
                if (lookUp.size() > 0) {
                    ObservableList<String> tmp = FXCollections.observableArrayList();
                    for (int i = 0; i < lookUp.size(); i++) {
                        tmp.addAll(lookUp.get(i));
                    }
                    wordView.setItems(tmp);
                }
            }
            else {
                try {
                    readWord();
                    showText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void selection() {
        wordView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    void getSelection() {
        //wordView.getSelectionModel().getSelectedItem();
        wordView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("ListView selection changed from oldValue = "
                        + oldValue + " to newValue = " + newValue);
                String wordSearchFromList = newValue;
                String wordFoundFromSearch = app.dicManage.dictionaryLookup(wordSearchFromList);
                showText(wordFoundFromSearch);
                //textSearch.setText(newValue);
            }
        });
    }


    @FXML
    void setButtonDelete() {
        String wordSearch = textSearch.getText();
        app.dicManage.dictionaryDelete(wordSearch);
    }
}