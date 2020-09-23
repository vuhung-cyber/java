import java.util.Scanner;

public class DictionaryManagement {
    Scanner insect = new Scanner(System.in);

    public int sizeOfDictionary = 0;//khoi tao size.
    public Dictionary insectWord = new Dictionary();

    public void insertFromCommandline() {
        System.out.println("nhap size: ");
        sizeOfDictionary = insect.nextInt();
        insect.nextLine();
        for (int i = 0; i < sizeOfDictionary; i++){
            Word w = new Word();
            w.wordTarget = insect.nextLine();//nhap tieng anh.
            w.wordExplain = insect.nextLine();//nhap tieng viet.

            insectWord.newWord[i] = w;
        }

    }
}
