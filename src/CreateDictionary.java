import java.io.*;
import java.util.Scanner;


public class CreateDictionary {

    public static void main(String[] args) {

        String fName = "Dictionary.txt";
        BufferedReader br = null;

        for(int i=1;i<45;i++){
            boolean deleted =  new File(i+"_word_dictionary.txt").delete();
            if(deleted){
                System.out.println("Deleting existing file : " + i + "_word_dictionary.txt" );
            }
        }


        System.out.println("Scanning Dictionary.txt\nArranging words from dictionary");
        try {
            String word;
            br = new BufferedReader(new FileReader(fName));
            while ((word = br.readLine()) != null) {
                try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(word.length() + "_word_dictionary.txt", true)))) {
                    out.println(word);
                    //more code
                }catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                System.out.println(":) Created Dictionaries successfully");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}
