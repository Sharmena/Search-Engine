package websearch;

public class Word implements Comparable<Word>{
	private String word;
	private int count;
	
	public Word(String s) {
		word = s;
		count = 1;
	}
	public Word(String s, int i) {
		word = s;
		count = i;
	}
	public void setWord(String s) {
		word = s;
	}
	public void setCount(int i) {
		count = i;
	}
	public String getWord() {
		return word;
	}
	public int getCount() {
		return count;
	}
	public void incrementCount() {
		count++;
	}
	public String toString() {
		return (word + " " + count);
	}
	
	@Override
	
	public boolean equals(Object o) {
		return word.equals(((Word)o).getWord());
	}
	@Override
	
	public int compareTo(Word w) {
		return word.compareTo(w.getWord());
	}

}
