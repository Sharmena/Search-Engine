package websearch;

import java.util.HashMap;

import jsjf.ArrayUnorderedList;
import jsjf.PriorityQueue;

public class SearchEngine {

	HashMap<String, String> cache = new HashMap<String, String>();

	private ArrayUnorderedList<URLWords> searchResults = new ArrayUnorderedList<URLWords>();
	private final int maxSearch = 10;

	public SearchEngine(ArrayUnorderedList<URLWords> List) {
		searchResults = List;
	}

	public String search(String s) {

		String[] searchTerms = s.split("\\s+");

		String result = "";

		if (cache.containsKey(s)) {
			result = cache.get(s);
			System.out.println("Query found in cache");
		} else {
			PriorityQueue<String> queue = new PriorityQueue<String>();
			for (URLWords websites : searchResults) {
				int frequency = 0;
				for (String temp : searchTerms) {
					frequency += websites.getFrequency(temp);
				}
				queue.addElement(websites.getURL(), -frequency);
			}

			int i = 0;
			while (!queue.isEmpty() && i < maxSearch) {
				result += queue.removeNext() + "\n";
				i++;
			}
			cache.put(s, result);
			System.out.println("Query not found in cache");
		}
		return result;
	}

}
