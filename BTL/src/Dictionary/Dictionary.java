package Dictionary;

import java.util.*;

public class Dictionary {
    static ArrayList<Word> words = new ArrayList<Word>();

    public int length_target(int i) {
        return words.get(i).word_target.length();
    }

    // Sắp xếp theo Word taget
    public void sortToWordTaget() {
        Collections.sort(words);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        for(int i=0; i<5; i++){
            Word w = new Word();
            w.word_target = s.next();
            w.word_explain = s.next();
            words.add(w);
        }
        Dictionary w1 = new Dictionary();
        w1.sortToWordTaget();

        for(int i=0; i<5; i++){
            System.out.println(words.get(i).word_target);
        }
    }
    
}
