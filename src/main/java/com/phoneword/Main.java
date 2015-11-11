package com.phoneword;

import java.util.Arrays;
import java.util.HashMap;


public class Main {

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

    private static Boolean runTests=true;

    public static void main(String[] args) {

        System.out.println("*****************");
        System.out.println("********test1*********\n\n");
        PhoneWord(new Integer[] {2,5});

        if (runTests){
//            System.out.println("*****************");
//            System.out.println("********test2*********\n\n");
//            PhoneWord(new Integer[] {9,7,4,2});
//
            System.out.println("*****************");
            System.out.println("********test3*********\n\n");
            PhoneWord(new Integer[] {8,7,4,2,5,4,6});

            System.out.println("*****************\n\n");
            GetArraySizeTest();
            System.out.println("*****************\n\n");
            CheckArrayTest();
            System.out.println("*****************\n\n");
            BuildWordsTest();
        }

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

  //  Method Tests

    public static void CheckArrayTest(){
        Boolean testPassed=true;

        //Valid array of no elements
        if (!CheckArray(new Integer[] {}))
            testPassed=false;

        //Valid array of one element
        if (!CheckArray(new Integer[] {2}))
            testPassed=false;

        //Valid array of multiple elements
        if (!CheckArray(new Integer[] {2,3,4,5,6,7,8,9}))
            testPassed=false;

        //Invalid array of multiple elements
        if (CheckArray(new Integer[] {1,2,3,4,5,6,7,8,9}))
            testPassed=false;

        if (testPassed)
            System.out.println("CheckArray Test Passed!");
        else
            System.out.println("CheckArray Test Failed!");

    }

    public static void GetArraySizeTest(){
        Boolean testPassed=true;

        if (GetArraySize(new Integer[] {}) != 0)
            testPassed=false;

        if (GetArraySize(new Integer[] {2}) != 3)
            testPassed=false;

        if (GetArraySize( new Integer[] {2,3}) != 9)
            testPassed=false;

        if (GetArraySize( new Integer[] {9,7,2}) != 48)
            testPassed=false;

        if (testPassed)
            System.out.println("GetArraySizeTest Test Passed!");
        else
            System.out.println("GetArraySizeTest Test Failed!");
    }

    public static void BuildWordsTest(){
        Boolean testPassed=true;

        //Test Build words on single valid integer and null pre-sized array
        String[] newWordsTest = BuildWords(3, new String[4]);
        if (!(Arrays.toString(newWordsTest).equals("[D, E, F]")))
            testPassed=false;

        //Test existing arrayWords with valid number with existing array
        String[] newWordsTest2 = new String[9];
        newWordsTest2 = BuildWords(4,new String[] {"D","E","F"});
        if (!(Arrays.toString(newWordsTest2).equals("[DG, DH, DI, EG, EH, EI, FG, FH, FI]")))
            testPassed=false;

        if (testPassed)
            System.out.println("BuildWordsTest Test Passed!");
        else
            System.out.println("BuildWordsTest Test Failed!");

    }
}
