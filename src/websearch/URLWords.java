package websearch;

import jsjf.LinkedBinarySearchTree;

public class URLWords {
	
	String URL = "http://www.acm.org";
	LinkedBinarySearchTree<Word> words = new LinkedBinarySearchTree<Word>();	
	
	public URLWords() {
		URL = "";
	}
	public URLWords(String s){
		URL = s;
	}
	public String getURL() {
		return URL;
	}
	public void addWord(String s) {
		Word w = new Word(s);
		if (words.contains(w))
			w.incrementCount();
		else
			words.addElement(w);
	}
	public void addWord(Word w) {
		if (words.contains(w))
			w.incrementCount();
		else 
			words.addElement(w);
	}
	public boolean contains(String s) {
		Word w = new Word(s);
		return words.contains(w);
	}
	public int getFrequency(String s) {
		Word w = new Word(s);
		if (words.contains(w))
			return (words.find(w)).getCount();
		else
			return 0;
		
	}
	public String toString() {
		String result = "URL: " + URL + "\n";
		for(Word w:words) {
			result = result + w.getWord() + ": " + w.getCount() + "\n";
		}
		return result;
	}

}
