package Dictionary;

import java.util.Comparator;

public class Word implements Comparable<Word>{
    String word_target;
    String word_explain;

    public Word(){}

    public Word(String wt, String we) {
        word_target = wt;
        word_explain = we;
    }

    //override lại compareTo so sánh theo Word taget.
    @Override
    public int compareTo(Word o) {
        String compareWordTaget = ((Word) o).word_target;

        return this.word_target.compareTo(compareWordTaget);
    }

    /**public static Comparator<Word> WordSortTaget = new Comparator<Word>() {
        @Override
        public int compare(Word o1, Word o2) {
            return o1.word_target.compareTo(o2.word_target);
        }
    };*/

}
