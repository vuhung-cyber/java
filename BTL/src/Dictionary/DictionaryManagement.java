package Dictionary;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    Scanner scanner = new Scanner(System.in);

    static int sizeOfDictionary = 0;
    Dictionary addWord = new Dictionary();

    private static String FILE_NAME = "Dictionary.txt";

    public void insertFromCommandline() {
        System.out.println("Size: ");
        sizeOfDictionary = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < sizeOfDictionary; i++) {
            Word w = new Word();
            w.word_target = scanner.nextLine(); //eng
            w.word_explain = scanner.nextLine(); // vi

            addWord.words.add(i, w);
        }
    }

    public int insertFromFile() throws IOException {
        File file = new File(FILE_NAME);
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        String line = "";
        int i = 0;
        while ((line = reader.readLine()) != null) {
            int index = line.indexOf("\t");
            Word w = new Word();
            w.word_target = line.substring(0, index);
            w.word_explain = line.substring(index+1);
            addWord.words.add(i, w);
            i++;
            //System.out.println(w.word_explain + " " + w.word_target);
        }
        return i;
    }

    /**
     * Lookup and show to Lable.
     */
    public String dictionaryLookup(String searchWord) {
        String foundWord = searchWord;
        String explain = "";
        for (int i = 0; i < addWord.words.size(); i++) {
            if (foundWord.equals(addWord.words.get(i).word_target)) {
                explain = addWord.words.get(i).word_explain;
                break;
            }
        }
        return explain;
    }

    public void dictionaryDelete() {
        String deletedWord = scanner.nextLine();

    }

    public void dictionaryAddNewWord() {

    }

    public static void main(String[] args) {

    }

}
