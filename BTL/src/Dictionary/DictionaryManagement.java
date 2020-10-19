package Dictionary;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement {
    Scanner scanner = new Scanner(System.in);
    Dictionary dictionary = new Dictionary();

    static int sizeOfDictionary = 0;
    static boolean notAdd = true;
    private final static String FILE_NAME = "Dictionary.txt";


    // đọc từ bàn phím
    public void insertFromCommandline() {
        System.out.println("Size: ");
        sizeOfDictionary = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < sizeOfDictionary; i++) {
            dictionary.words.add(new Word(scanner.nextLine(), scanner.nextLine()));
        }
    }

    // đọc từ file
    public void insertFromFile() {
        try {
            scanner = new Scanner(Paths.get(FILE_NAME), "UTF-8");
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                String[] parts = str.split("\t");
                dictionary.words.add(new Word(parts[0], parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // in ra file
    public void dictionaryExportToFile() {
        try {
            FileOutputStream fo = new FileOutputStream(FILE_NAME);
            PrintWriter out = new PrintWriter(fo);
            for (int i = 0; i < dictionary.words.size(); i++) {
                out.println(dictionary.words.get(i).word_target
                        + "\t" + dictionary.words.get(i).word_explain);
            }
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // tìm từ
    public String dictionaryLookup(String searchWord) {
        String explain = "";
        for (int i = 0; i < dictionary.words.size(); i++) {
            if (searchWord.equals(dictionary.words.get(i).word_target)) {
                explain += dictionary.words.get(i).word_explain;
                break;
            }
        }
        return explain;
    }

    // xóa từ
    public void dictionaryDelete(String s) {
        String deletedWord = s;
        for (int i = 0; i < dictionary.words.size(); i++) {
            if (deletedWord.equals(dictionary.words.get(i).word_target)) {
                dictionary.words.remove(i);
                break;
            }
        }
    }

    // thêm từ mới
    public void dictionaryAddNewWord() {
        String addedWord = scanner.nextLine();
        //boolean check = true;
        for (int i = 0; i < dictionary.words.size(); i++) {
            if (addedWord.equals(dictionary.words.get(i).word_target)) {
                notAdd = false;
            }
        }

        if (notAdd) {
            String addedWordMean = scanner.nextLine();
            dictionary.words.add(new Word(addedWord, addedWordMean));
        } else return;
    }
}