package Dictionary;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private Button buttonSearch;

    @FXML
    private Label label;

    @FXML
    private TextField textSearch;

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private Button change;

    @FXML
    private TextField wordAdd;

    @FXML
    private TextField wordMean;

    @FXML TextField wordChange;


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
            getSelection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // wordView input
    public void readWord() throws IOException {
        ObservableList list = FXCollections.observableArrayList();
        for (int i = 0; i < Dictionary.words.size(); i++) {
            list.add(Dictionary.words.get(i).word_target);
        }
        wordView.getItems().addAll(list);
    }

    // search button
    @FXML
    void pressSearch() {
        String wordSearch = textSearch.getText();
        String wordFound = app.dicManage.dictionaryLookup(wordSearch);
        showText(wordFound);
    }

    // label
    public void showText(String s) {
        label.setText(s);
    }

    // search with character
    @FXML
    void searching() {
        textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!(newValue.trim().isEmpty())) {
                ArrayList<String> lookUp = app.dictionarySearcher(newValue);
                if (lookUp.size() > 0) {
                    ObservableList<String> tmp = FXCollections.observableArrayList();
                    for (String s : lookUp) {
                        tmp.addAll(s);
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

    // get mean by pointing at word
    @FXML
    void getSelection() {
        textSearch.setText(wordView.getSelectionModel().getSelectedItem());
        if (textSearch.getText() != null) {
            String wordSearchFromList = textSearch.getText();
            String wordFoundFromSearch = app.dicManage.dictionaryLookup(wordSearchFromList);
            showText(wordFoundFromSearch);
        }
    }

    // delete button
    @FXML
    void setButtonDelete() {
        String word_remove = textSearch.getText();
        if (!app.dicManage.isExist(word_remove)) {
            Alert infor = new Alert(Alert.AlertType.ERROR);
            infor.setTitle("Lỗi");
            infor.setContentText("Từ bạn muốn xóa không có trong từ điển");
            infor.show();
        }
        else {
            app.dicManage.dictionaryDelete(word_remove);
            Alert infor = new Alert(Alert.AlertType.CONFIRMATION);
            infor.setTitle("Congratulations");
            infor.setContentText("Bạn đã xóa thành công!");
            infor.show();
            app.dicManage.dictionaryExportToFile();
        }
        textSearch.clear();
    }

    // add button
    @FXML
    void setButtonAdd() {
        String eng = wordAdd.getText();
        String vi = wordMean.getText();

        if (!eng.isEmpty()) {
            if (app.dicManage.isExist(eng)) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Lỗi");
                al.setContentText("Từ đã có trong từ điển rồi!!");
                al.show();
            }
            else {
                app.dicManage.dictionaryAddNewWord(eng, vi);
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Congratulations");
                al.setContentText("Bạn đã thêm thành công!");
                al.show();
                app.dicManage.dictionaryExportToFile();
            }
            wordAdd.clear();
            wordMean.clear();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Bạn chưa nhập từ");
            alert.show();
        }
    }

    //press button Change.
    @FXML
    void pressChange() {
        String searchWord = textSearch.getText();
        String explain = wordChange.getText();//lấy nghĩa từ Text Change.
        for (int i = 0; i <Dictionary.words.size(); i++) {
            if (searchWord.equals(Dictionary.words.get(i).word_target)) {
                Dictionary.words.get(i).word_explain = explain;//gán
                break;
            }
        }
        label.setText(explain);
        app.dicManage.dictionaryExportToFile();//update file.
        wordChange.setText("");//xóa Text Change.
    }
}