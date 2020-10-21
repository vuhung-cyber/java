package Dictionary;

import java.util.ArrayList;

public class Dictionary {
    static ArrayList<Word> words = new ArrayList<>();
    public int length_target(int i) {
        return words.get(i).word_target.length();
    }
}
