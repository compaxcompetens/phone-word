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

//            System.out.println("*****************\n\n");
//            GetArraySizeTest();
//            System.out.println("*****************\n\n");
            CheckArrayTest();
            System.out.println("*****************\n\n");
            BuildWordsRTest();
        }

    }

    public static void PhoneWord(Integer[] numberArray){
        if (!CheckArray(numberArray))
            return;

        String[] newWords=null;
        String[] allWords = BuildWordsR(numberArray, (numberArray.length-1), newWords);

        for (int i = 0, n = allWords.length; i < n; i++) {
            if (allWords[i] != null)
               System.out.println(allWords[i]);
        }
    }

    public static String[] BuildWordsR(Integer[] myNumberArray,Integer thisPointer, String[] arrayWords ){
        Integer thisNumber=myNumberArray[thisPointer];
        String[] phoneLetters=phonePad.get(thisNumber);

        if (thisPointer == 0) {
            return(phoneLetters);
        }
        else {
            return(matchString( BuildWordsR(myNumberArray,(thisPointer-1), arrayWords), phoneLetters));
        }
    }


    public static String[] matchString( String[] arrayWords, String[] phoneLetters){
        Integer thisArraySize = phoneLetters.length *arrayWords.length;
        String[] newWords = new String[thisArraySize];
        Integer count=0;
        for (int i = 0, n = arrayWords.length; i < n; i++) {

            for (int j = 0, m = phoneLetters.length; j < m; j++) {
                String newWord = String.format("%s%s", arrayWords[i], phoneLetters[j]);
                newWords[count]=newWord;
                count++;
            }
        }
        return(newWords);
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


    public static void BuildWordsRTest(){
        Boolean testPassed=true;
        //Test Build words on single valid integer and null pre-sized array


        String[] newWordsTest = BuildWordsR(new Integer[]{3}, 0, null);
        if (!(Arrays.toString(newWordsTest).equals("[D, E, F]")))
            testPassed=false;

        //Test existing arrayWords with valid number with existing array
        String[] newWordsTest2 = new String[9];
        newWordsTest2 = BuildWordsR(new Integer[]{3,4}, 1,new String[] {"D","E","F"});
        if (!(Arrays.toString(newWordsTest2).equals("[DG, DH, DI, EG, EH, EI, FG, FH, FI]")))
            testPassed=false;

        if (testPassed)
            System.out.println("BuildWordsR Test Passed!");
        else
            System.out.println("BuildWordsR Test Failed!");

    }
}
