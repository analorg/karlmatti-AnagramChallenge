package helmes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class AnagramFinder {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        String result = "";
        //LinkedList list = magic("Aleksandre", "C:\\Users\\karlm\\IdeaProjects\\diy_progepohikursus\\src\\main\\java\\harjutused\\helmes\\lemmad.txt");
        LinkedList list = magic(args[0], args[1]);
        long stop = System.nanoTime() - startTime;
        System.out.println(stop + result);
        printLinkedList(list);
    }

    public static LinkedList magic(String word, String fileName) {
        char[] wordArr = word.toCharArray();


        Map<Character, Integer> lettersInWord = new HashMap<Character, Integer>();

        for (char c : wordArr) {
            int count = 1;
            if (lettersInWord.containsKey(c)) {
                count = lettersInWord.get(c) + 1;
            }
            lettersInWord.put(c, count);
        }

        LinkedList list = new LinkedList();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while(line != null) {
                if (line.length() == wordArr.length) {
                    if (compareWords(lettersInWord, line.toCharArray())== true){
                        list.add(line);
                    }
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    return list;
    }

    public static boolean compareWords(Map<Character, Integer> lettersInWord, char[] word2) {


        for (char c : word2) {
            int count = -1;
            if (lettersInWord.containsKey(c)) {
                count = lettersInWord.get(c) - 1;
            }
            lettersInWord.put(c, count);
        }

        for (char c : lettersInWord.keySet()) {
            if (lettersInWord.get(c) != 0) {
                return false;
            }
        }

        return true;
    }

    public static void printLinkedList(LinkedList list) {
        Object[] array = list.toArray();

        // print the array
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Anagrams:" + array[i]);
        }
    }
}
