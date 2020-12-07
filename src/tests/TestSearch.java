package tests;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsjf.ArrayUnorderedList;
import websearch.SearchEngine;
import websearch.URLWords;
import websearch.*;

/**
 * @author Sonya Cates <scates@rwu.edu>
 * @date Apr 5, 2016
 *
 */
public class TestSearch {
    /**
     * Use this to test the Search Engine class.  URLWords must be completed as well.
     * @param args 
     */

    public static void main(String[] args) {

        try {
            ArrayUnorderedList<URLWords> crawlResult = WebCrawlResultReader.readFromFile("crawlResultRWU.txt");

            SearchEngine se = new SearchEngine(crawlResult);

            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.println("Enter a query or -1 to quit.");

                System.out.print("Search for: ");
                String query = input.nextLine();
                if (query.equals("-1")) {
                    break;
                }
                String result = se.search(query.toLowerCase());
                System.out.println("Relevant pages:");
                System.out.println(result);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        }
    }
}
