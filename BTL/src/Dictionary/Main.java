package Dictionary;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
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

    @FXML
    private TextField workChange = new TextField();

    @FXML
    private Button ok = new Button();

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
    void pressChange() {
        workChange.setVisible(true);
        ok.setVisible(true);
    }

    @FXML
    void pressOk() {
        String searchWord = textSearch.getText();
        String explain = workChange.getText();
        for (int i = 0; i < app.dicManage.dictionary.words.size(); i++) {
            if (searchWord.equals(app.dicManage.dictionary.words.get(i).word_target)) {
                app.dicManage.dictionary.words.get(i).word_explain = explain;
                break;
            }
        }
        label.setText(explain);
        app.dicManage.dictionaryExportToFile();
        workChange.setVisible(false);
        ok.setVisible(false);
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
        textSearch.setText(wordView.getSelectionModel().getSelectedItem());
        if (textSearch.getText() != null) {
            String wordSearchFromList = textSearch.getText();
            String wordFoundFromSearch = app.dicManage.dictionaryLookup(wordSearchFromList);
            showText(wordFoundFromSearch);
        }
    }
    
}