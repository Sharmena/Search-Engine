package tests;

import websearch.Word;
/**
 * Test code for Word class.
 * Tests constructors, incrementCount, equals, toString, compareTo.
 */
public class TestWord1 {
    public static void main(String[] args){
        Word w1 = new Word("hello");
        System.out.println("w1:");
        System.out.println(w1.toString());
        w1.incrementCount();
        System.out.println("w1 after incrementCount:");
        System.out.println(w1.toString());
        Word w2 = new Word("world", 2);
        System.out.println("w2:");
        System.out.println(w2.toString());
        w2.incrementCount();
        w2.incrementCount();
        System.out.println("w2 after incrementCount twice:");
        System.out.println(w2.toString());
        Word w3 = new Word("hello");
        w3.setCount(5);
        System.out.println("w3:"); 
        System.out.println(w3.toString());
      }
}
