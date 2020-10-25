package Dictionary;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement {
    Scanner scanner = new Scanner(System.in);
    Dictionary newDictionary = new Dictionary();

    static int sizeOfDictionary = 0;

    private final static String FILE_NAME = "Dictionary.txt";

    // đọc từ bàn phím
    public void insertFromCommandline() {
        System.out.println("Size: ");
        sizeOfDictionary = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < sizeOfDictionary; i++) {
            Dictionary.words.add(new Word(scanner.nextLine(), scanner.nextLine()));
        }
    }

    // đọc từ file
    public void insertFromFile() {
        try {
            scanner = new Scanner(Paths.get(FILE_NAME), "UTF-8");
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                String[] parts = str.split("\t");
                Dictionary.words.add(new Word(parts[0], parts[1]));
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
            for (int i = 0; i < Dictionary.words.size(); i++) {
                out.println(Dictionary.words.get(i).word_target
                        + "\t" + Dictionary.words.get(i).word_explain);
            }
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // tìm từ
    public String dictionaryLookup(String searchWord) {
        String explain = "";
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (searchWord.equals(Dictionary.words.get(i).word_target)) {
                explain += Dictionary.words.get(i).word_explain;
                return explain;
            }
        }
        return "Not Found";
    }

    // xóa từ
    public void dictionaryDelete(String deletedWord) {
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (deletedWord.equals(Dictionary.words.get(i).word_target)) {
                Dictionary.words.remove(i);
                break;
            }
        }
    }

    // thêm từ mới
    public void dictionaryAddNewWord(String addedWord, String addedWordMean) {
        Dictionary.words.add(new Word(addedWord, addedWordMean));
        newDictionary.sortToWordTaget();
    }

    // kiểm tra nếu từ có trong file
    public boolean isExist(String s) {
        boolean checkIfExist = false;
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (s.equals(Dictionary.words.get(i).word_target)) {
                checkIfExist = true;
                break;
            }
        }
        return checkIfExist;
    }
}