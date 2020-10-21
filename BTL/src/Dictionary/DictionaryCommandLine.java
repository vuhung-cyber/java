package Dictionary;
import java.io.IOException;
import java.util.ArrayList;

public class DictionaryCommandLine {
    DictionaryManagement dicManage = new DictionaryManagement();
    //public boolean helpToSearching = true;

    // in ra màn hình
    public void showAllWords() {
        System.out.printf("No\t| English\t| Vietnamese\n");
        for (int i = 0; i < dicManage.dictionary.words.size(); i++) {
            System.out.printf("%d\t| %s\t\t| %s\n", i+1, dicManage.dictionary.words.get(i).word_target,
                    dicManage.dictionary.words.get(i).word_explain);
        }
    }

    // in từ bàn file
    public void dictionaryAdvanced() throws IOException {
        dicManage.insertFromFile();
        showAllWords();
        //dicManage.dictionaryLookup();
        //dicManage.dictionaryAddNewWord();
        dicManage.dictionaryExportToFile();

    }

    // tìm bằng chữ cái đầu
    public ArrayList<String> dictionarySearcher(String s) {
        String str = s.trim().toLowerCase();
        int number = dicManage.dictionary.words.size();
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < number; i++) {
            String st = dicManage.dictionary.words.get(i).word_target;
            if (st.contains(str) && st.charAt(0) == str.charAt(0))
                result.add(st);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandLine library = new DictionaryCommandLine();
        //library.dictionaryBasic();
        library.dictionaryAdvanced();
        //library.dictionarySearcher();
    }
}
