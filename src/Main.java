import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len, dLen;
        String source, destination;

        System.out.print("Enter the source word : ");
        source = sc.next();

        System.out.print("Enter the destination word : ");
        destination = sc.next();

        source = source.toLowerCase();
        destination = destination.toLowerCase();

        len = source.length();
        dLen = destination.length();

        if(dLen != len){
            System.out.println("source and destination have unequal lengths");
            System.exit(1);
        }

        DicSort ds = new DicSort(source,destination);

        //ds.printSortedDictionary();
        ds.printWordChain();
    }



}
