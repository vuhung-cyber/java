package Dictionary;

import java.util.ArrayList;

public class DictionaryCommandLine {
    DictionaryManagement dicManage = new DictionaryManagement();
    Dictionary dictionary = new Dictionary();

    // in ra màn hình
    public void showAllWords() {
        System.out.println("No\t| English\t| Vietnamese");
        for (int i = 0; i < Dictionary.words.size(); i++) {
            System.out.printf("%d\t| %s\t\t| %s\n", i+1, Dictionary.words.get(i).word_target,
                    Dictionary.words.get(i).word_explain);
        }
    }

    // in từ bàn phím
    public void dictionaryBasic() {
        dicManage.insertFromCommandline();
        showAllWords();
    }

    // in từ bàn file
    public void dictionaryAdvanced() {
        dicManage.insertFromFile();
        showAllWords();
        //dicManage.dictionaryLookup();
        //dicManage.dictionaryExportToFile();
        showAllWords();

    }

    // tìm bằng chữ cái đầu
    public ArrayList<String> dictionarySearcher(String t) {
        ArrayList<String> result = new ArrayList<>();
        int length_t = t.length();

        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (dictionary.length_target(i) >= length_t) {
                String f = Dictionary.words.get(i).word_target;
                String temp = f.substring(0, length_t);
                if (temp.equals(t)) {
                    result.add(f);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        DictionaryCommandLine library = new DictionaryCommandLine();
        //library.dictionaryBasic();
        library.dictionaryAdvanced();
        //library.dictionarySearcher();
    }
}
