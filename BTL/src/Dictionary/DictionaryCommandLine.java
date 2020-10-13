package Dictionary;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DictionaryCommandLine {

    public DictionaryManagement size = new DictionaryManagement();

    public void showAllWords() {
        System.out.printf("No\t| English\t| Vietnamese\n");
        for (int i = 0; i < size.addWord.words.size(); i++) {
            System.out.printf("%d\t| %s\t\t| %s\n", i+1, size.addWord.words.get(i).word_target,
                    size.addWord.words.get(i).word_explain);
        }
    }

    public void dictionaryAdvanced() throws IOException {
        size.insertFromFile();
        showAllWords();
        //size.dictionaryLookup();
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandLine thu_vien = new DictionaryCommandLine();
        thu_vien.size.insertFromFile();
        thu_vien.size.dictionaryLookup("house");
        //thu_vien.dictionaryAdvanced();
    }

}
