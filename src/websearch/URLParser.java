
package websearch;

import java.net.URL;
import java.util.Scanner;
import jsjf.ArrayUnorderedList;

/**
 * URLParser
 * @author Sonya Cates
 */
public class URLParser {
		
    private final static String URL_START = "http";
    private final static String URL_END1 = "'";
    private final static String URL_END2 = "\"";
    
    private final static String CONTENT_START = ">";
    private final static String CONTENT_END = "<";

    private String inputURL;
    
    /** Default constructor*/
    public URLParser(){
    
    }
    
    /** Set the inputURL*/
    public URLParser(String inputURL){
        this.inputURL = inputURL;
    }
    
    /** Set the inputURL*/
    public void setURL(String inputURL){
        this.inputURL = inputURL;
    }
    
    /** getSubURLS returns and ArrayUnorderedList of Strings containing all the
     * subURLS of the inputURL.  Only subURLs in the form http:\"..." or 
     * http:\'...' are included.
     */
    public ArrayUnorderedList<String> getSubURLS(){
        //Create the list to hold the subURLS
        ArrayUnorderedList<String> urlList = new ArrayUnorderedList<>(); 
        try { //try to open the given URL
            URL url = new URL(inputURL);
            Scanner input = new Scanner(url.openStream());

            int startIndex = 0;
            while(input.hasNext()){
                String nextLine = input.nextLine();
                //find the next instance of http:
                startIndex = (nextLine.toLowerCase()).indexOf(URL_START, startIndex);
                while(startIndex > 0){ //if http was found
                    //find the next ' or "
                    int endIndex = Math.min(nextLine.indexOf(URL_END1, startIndex), nextLine.indexOf(URL_END2, startIndex));
                    if(endIndex > 0) { //if the end of the url was found
                        //get the correct substring
                        String subURL = nextLine.substring(startIndex, endIndex);
                        //add to list
                        urlList.addToRear(subURL);
                        //find next instance of http:
                        startIndex = (nextLine.toLowerCase()).indexOf(URL_START, endIndex);
                    }
                    else{
                        startIndex = -1;
                    }
                }
            }
                input.close();
        } catch (Exception e) {//URL failed to open
                System.out.println("Error: " + e.getMessage());
        }

        return urlList;
    }

    /**
     * Print subURLs instead of returning them in an ArrayList.
     * This method may be useful for debugging, but is not needed otherwise.
     */
    public void printSubURLS(){
        try {
            URL url = new URL(inputURL);
            Scanner input = new Scanner(url.openStream());

            int startIndex = 0;
            while(input.hasNext()){
                String nextLine = input.nextLine();
                startIndex = (nextLine.toLowerCase()).indexOf(URL_START, startIndex);
                while(startIndex > 0){
                    int endIndex = Math.min(nextLine.indexOf(URL_END1, startIndex), nextLine.indexOf(URL_END2, startIndex));
                    if(endIndex > 0) {
                        String subURL = nextLine.substring(startIndex, endIndex);
                        System.out.println(subURL);
                        startIndex = (nextLine.toLowerCase()).indexOf(URL_START, endIndex);
                    }
                    else{
                        startIndex = -1;
                    }
                }
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /** getSubWords returns a URLWords object containing word counts of the words
    * that appear in the given URL.
    **/
    public URLWords getURLWords(){
        URLWords words = new URLWords(inputURL);
       
        try {
             URL url = new URL(inputURL);
             Scanner input = new Scanner(url.openStream());

             int startIndex = 0;
             while(input.hasNext()){
                 String nextLine = input.nextLine();
                 nextLine = removePunctuation(nextLine.toLowerCase());
                 startIndex = (nextLine).indexOf(CONTENT_START, startIndex) + 1;
                 while(startIndex > 0){
                     int endIndex = nextLine.indexOf(CONTENT_END, startIndex);
                     if(endIndex > 0) {
                        String content = nextLine.substring(startIndex, endIndex);
                        String[] wordArray = content.split("\\s+");
                        for(int i = 0; i< wordArray.length; i++){
                            if(!wordArray[i].trim().isEmpty())
                                 words.addWord(wordArray[i].trim());
                         }
                         startIndex = (nextLine.toLowerCase()).indexOf(CONTENT_START, endIndex) + 1;
                     }
                     else{
                         startIndex = -1;
                     }
                 }
             }
                input.close();
        } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
        }
        return words;
   }
    
    
    private String removePunctuation(String nextLine) {
        char[] punctuation = {')','(',',', '.','!', '?', ';', '\'', '-', '}','{', '&','\"'};
        String[] specialChars = {"&nbsp", "&apos"}; 
        for (int i = 0 ;i< punctuation.length; i++){
            nextLine = nextLine.replace(punctuation[i], ' ');
        }
        for (int i = 0 ;i< specialChars.length; i++){
            nextLine = nextLine.replaceAll(specialChars[i], " ");
        }
        return nextLine;
    }
    

    /**
     * Print words instead of returning them.
     * This method may be useful for debugging, but is not needed otherwise.
     */
    public void printURLWords(){
        try {
            URL url = new URL(inputURL);
            Scanner input = new Scanner(url.openStream());

            int startIndex = 0;
            while(input.hasNext()){
                String nextLine = input.nextLine();
                nextLine = removePunctuation(nextLine.toLowerCase());
                startIndex = (nextLine).indexOf(CONTENT_START, startIndex) + 1;
                while(startIndex > 0){
                    int endIndex = nextLine.indexOf(CONTENT_END, startIndex);
                    if((endIndex > 0)) {
                        String content = nextLine.substring(startIndex, endIndex);
                        String[] wordArray = content.split("\\s+");
                        for(int i = 0; i< wordArray.length; i++){
                            if(!wordArray[i].trim().isEmpty())
                                System.out.println(wordArray[i].trim());
                    
                        }
                        startIndex = (nextLine.toLowerCase()).indexOf(CONTENT_START, endIndex) + 1;
                    }
                    else{
                        startIndex = -1;
                    }
                }
            }
            input.close();
        } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
        }
    }

}