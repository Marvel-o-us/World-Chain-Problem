import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by rahul on 2/4/16.
 */
public class DicSort {
    Map<Integer, Map<Integer, List<String>>> wordMap = new HashMap<>();
    String source, dest;
    int len;

    DicSort(String source, String dest) {
        this.source = source;
        this.dest = dest;
        this.len = source.length();

        sort_dictionary();
    }

    void sort_dictionary() {

        String fName =  len + "_word_dictionary.txt";
        BufferedReader br = null;

        try {
            String word;
            br = new BufferedReader(new FileReader(fName));
            while ((word = br.readLine()) != null) {
                //System.out.println("ERR:: "+word);
                Integer diff = differenceFromSource(word);
                Integer hValue = heuristicFunction(word);

                Map<Integer, List<String>> hStringPair = wordMap.get(diff);
                if (hStringPair == null)
                    hStringPair = new HashMap<>();

                List<String> stringList = hStringPair.get(hValue);
                if (stringList == null)
                    stringList = new ArrayList<>();

                stringList.add(word);
                hStringPair.put(hValue, stringList);

                wordMap.put(diff, hStringPair);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    int differenceFromSource(String word) {
        int difference = 0;

        for (int i = 0; i < len; i++) {
            if (word.charAt(i) != source.charAt(i)) {
                difference++;
            }
        }
        return difference;
    }

    Integer heuristicFunction(String word) {
        Integer hValue = 0;

        for (int i = 0; i < len; i++) {
            if (word.charAt(i) == dest.charAt(i)) {
                hValue++;
            } else {
                hValue--;
            }
        }

        return hValue;
    }

    void printSortedDictionary() {
        for (int i = 0; i <= len; i++) {
            System.out.println("Row " + i + " : ");
            for (int j = -len; j <= len; j++) {
                List<String> list = new ArrayList<>();
                try {
                    list = wordMap.get(i).get(j);
                    if(list == null)
                        list = new ArrayList<>();
                }catch (Exception e){

                }finally{
                    System.out.println("\t" + " hValue = " + j + "Word = " + list);
                }

            }
        }
    }

    Stack<String> st = new Stack<>();
    List<String> visitedWords = new ArrayList<>();
    int destinationLevel = differenceFromSource(dest);

    void printWordChain() {
        //System.out.println("PUSHING" + source);
        st.push(source);
        visitedWords.add(source);

        int hValue = heuristicFunction(source);
        printWordChain(1, hValue+2, source);
        printWordChain(1, hValue, source);
        printWordChain(1, hValue-2, source);
        System.out.println(":( Could not find a word chain");
    }


    void printWordChain(int level, int hValue, String prevWord){
        //System.out.print("\nDBG:: " + " level = " + level + " hValue = " + hValue + " prevWord = " + prevWord + " preHValue = " + prevHValue);

        if (hValue < -len ) {
            //System.out.println("\nhvalue<-len");
            return;
        }

        List<String> wordList = null;
        if(wordMap.get(level) != null)
            wordList = wordMap.get(level).get(hValue);

        if (wordList == null) {
            //System.out.println(" WordList = NULL");
            return;
        } else if (wordList.contains(dest)) {
            if (isDeservingSuccessor(dest, prevWord)) {
                //System.out.println("PUSHING" + dest);
                st.push(dest);
                printStack();
                System.exit(0);
            }
        }

        for(String word : wordList){
            if(isDeservingSuccessor(word,prevWord) && !visitedWords.contains(word)){
                st.push(word);
                visitedWords.add(word);

                //System.out.println("PUSHING" + word);
                if(level < destinationLevel) {

                    printWordChain(level + 1, hValue + 2, word);
                    printWordChain(level, hValue + 2, word);
                    printWordChain(level - 1, hValue + 2, word);
                    printWordChain(level + 1, hValue, word);
                    printWordChain(level, hValue, word);
                    printWordChain(level - 1, hValue, word);
                    printWordChain(level + 1, hValue - 2, word);
                    printWordChain(level, hValue - 2, word);
                    printWordChain(level - 1, hValue - 2, word);

                }else if(level == destinationLevel){

                    printWordChain(level, hValue + 2, word);
                    printWordChain(level - 1, hValue + 2, word);
                    printWordChain(level + 1, hValue + 2, word);
                    printWordChain(level, hValue, word);
                    printWordChain(level - 1, hValue, word);
                    printWordChain(level + 1, hValue, word);
                    printWordChain(level, hValue - 2, word);
                    printWordChain(level - 1, hValue - 2, word);
                    printWordChain(level + 1, hValue - 2, word);

                }else{


                    printWordChain(level - 1, hValue + 2, word);
                    printWordChain(level, hValue + 2, word);
                    printWordChain(level + 1, hValue + 2, word);
                    printWordChain(level - 1, hValue, word);
                    printWordChain(level, hValue, word);
                    printWordChain(level + 1, hValue, word);
                    printWordChain(level - 1, hValue - 2, word);
                    printWordChain(level, hValue - 2, word);
                    printWordChain(level + 1, hValue - 2, word);

                }
                st.pop();
            }
        }

    }
    boolean isDeservingSuccessor(String word, String parentWord) {
        int difference = 0;

        for (int i = 0; i < len; i++) {
            if (word.charAt(i) != parentWord.charAt(i)) {
                difference++;
            }
        }

        return difference == 1;
    }

    void printStack() {
        optimiseStack();
        List<String> list = new ArrayList<String>(st);
        for (String x : list) {
            System.out.println(x);
        }
    }

    void optimiseStack(){
        int SIZE = st.size();
        for(int i = 0; !Objects.equals(st.get(i), dest); i++){
            String word1 = st.get(i);
            for(int j=st.size()-1; j>i;j--){
                String word2 = st.get(j);
                if(isDeservingSuccessor(word2,word1)){
                    List<String> toRemove = new ArrayList<>();
                    for(int k=i+1;k<j;k++){
                        String w = st.get(k);
                        toRemove.add(w);
                    }
                    st.removeAll(toRemove);
                    break;
                }
            }
        }
    }

}
