package websearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import jsjf.ArrayUnorderedList;

/**
 * @author Sonya Cates <scates@rwu.edu>
 * @date Apr 7, 2016
 *
 */
public class WebCrawlResultReader {

    private static final String DELIMITER = "*******************";

    /**
     * Read the result of crawling the web from a file.  File format is: 
     * DELIMITER
     * URL address 
     * List of words and their counts
     * 
     * @param filename
     * @return
     * @throws FileNotFoundException 
     */
    public static ArrayUnorderedList<URLWords> readFromFile(String filename) throws FileNotFoundException {
        ArrayUnorderedList<URLWords> URLWordList = new ArrayUnorderedList<URLWords>();

        File f = new File(filename);
        Scanner input = new Scanner(f);
        if (!input.nextLine().equals(DELIMITER)) {
            System.out.println("Error in file read");
        }
        while (input.hasNext()) {
            String temp = input.nextLine();
            URLWords newURLWords = new URLWords(temp);
            while (input.hasNext()) {
                temp = input.nextLine();
                if (temp.equals(DELIMITER)) {
                    break;
                }
                String[] s = temp.split("\\s+");
                Word w = new Word(s[0]);

                w.setCount(Integer.parseInt(s[1]));
                newURLWords.addWord(w);

            }
            URLWordList.addToRear(newURLWords);

        }
        input.close();
        return URLWordList;
    }
}
