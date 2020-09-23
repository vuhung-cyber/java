public class DictionaryCommandLine {

    /**
     * size lay ra sizeOfDictionary va cac tu trong tu dien.
     */
    DictionaryManagement size = new DictionaryManagement();

    public void showAllWords() {

        System.out.printf("No\t| English\t| Vietnamese\n");
        for (int i = 0; i < size.sizeOfDictionary; i++) {
            System.out.printf("%d\t| %s\t\t| %s\n", i+1, size.insectWord.newWord[i].wordTarget,
                                                        size.insectWord.newWord[i].wordExplain);
        }
    }

    public void dictionaryBasic(){
        size.insertFromCommandline();

        showAllWords();
    }
    public static void main(String[] args){
        DictionaryCommandLine thuVien = new DictionaryCommandLine();

        thuVien.dictionaryBasic();
    }
}
