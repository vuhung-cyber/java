package Dictionary;

public class Word implements Comparable<Word> {
    String word_target;
    String word_explain;

    public Word(String wt, String we) {
        word_target = wt;
        word_explain = we;
    }

    @Override
    public int compareTo(Word o) {
        return this.word_target.compareTo(o.word_target);
    }
}
