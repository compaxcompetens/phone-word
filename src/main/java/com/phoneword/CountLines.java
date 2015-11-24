package com.phoneword;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by margie on 10/13/15.
 */
public class CountLines {

    private static HashMap<Integer, String[]> phonePad;
    static {
        phonePad = new HashMap<Integer, String[]>();
        phonePad.put(2, new String[]{"A","B","C"});
        phonePad.put(3, new String[]{"D","E","F"});
        phonePad.put(4, new String[]{"G","H","I"});
        phonePad.put(5, new String[]{"J","K","L"});
        phonePad.put(6, new String[]{"M","N","O"});
        phonePad.put(7, new String[]{"P","Q","R","S"});
        phonePad.put(8, new String[]{"T","U","V"});
        phonePad.put(9, new String[]{"W","X","Y","Z"});
    }

    public static void main(String[] args) {

        System.out.println("*****************");
        System.out.println("********test1*********\n\n");
        PhoneWord(new Integer[] {2,5});
    }

    public static void PhoneWord(Integer[] numberArray){
        if (!CheckArray(numberArray))
            return;

        Integer wordArraySize=GetArraySize(numberArray);
        String[] newWords = new String[wordArraySize];
        for (int i=0; i<numberArray.length; i++){
            newWords = BuildWords(numberArray[i], newWords);
        }

        for (int i = 0, n = newWords.length; i < n; i++) {
            if (newWords[i] != null)
                System.out.println(newWords[i]);
        }
    }

    public static Integer GetArraySize(Integer[] numberArray){
        Integer wordArraySize;
        if (numberArray.length == 0) wordArraySize=0;
        else wordArraySize=1;

        for (int i=0; i<numberArray.length; i++){
            wordArraySize=wordArraySize*phonePad.get(numberArray[i]).length;
        }
        return wordArraySize;
    }

    public static String[] BuildWords(Integer thisNumber, String[] arrayWords ){
        String[] newWords;
        String[] phoneLetters=phonePad.get(thisNumber);

        if (arrayWords[0] == null) {
            newWords=phoneLetters;
        }
        else {
            Integer newArraySize = phonePad.get(thisNumber).length *arrayWords.length;
            newWords = new String[newArraySize];
            Integer count=0;
            for (int i = 0, n = arrayWords.length; i < n; i++) {

                for (int j = 0, m = phoneLetters.length; j < m; j++) {
                    String newWord = String.format("%s%s", arrayWords[i], phoneLetters[j]);
                    newWords[count]=newWord;
                    count++;
                }
            }
        }
        return newWords;
    }
/*
    //TODO MARGIE - Re-Write recursively

    public static String[] BuildWordsR(Integer thisNumber, String[] arrayWords ){
        String[] newWords;
        String[] phoneLetters=phonePad.get(thisNumber);

        if (arrayWords[0] == null) {
            newWords=phoneLetters;
        }
        else {
            Integer newArraySize = phonePad.get(thisNumber).length *arrayWords.length;
            newWords = new String[newArraySize];
            Integer count=0;
            for (int i = 0, n = arrayWords.length; i < n; i++) {

                for (int j = 0, m = phoneLetters.length; j < m; j++) {
                    String newWord = String.format("%s%s", arrayWords[i], phoneLetters[j]);
                    newWords[count]=newWord;
                    count++;
                }
            }
        }
        return newWords;
    }

*/

    public static boolean CheckArray(Integer [] numberArray){
        for (int i=0; i<numberArray.length; i++){
            if (!phonePad.containsKey(numberArray[i])) {
                System.out.println("Array of Integers must contain values 2 through 9!");
                System.out.println(Arrays.toString(numberArray));
                return false;
            }
        }
        return true;
    }

}
