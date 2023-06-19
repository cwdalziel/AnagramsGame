package anagrams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class AnagramsGame {

	private String letters;
	private Set<String> dictionary;
	private Map<String, Integer> validAnagrams;
	private Map<String, Integer> usedWords;
	private int score;

	public AnagramsGame(String letters) {
		this.letters = letters.toUpperCase();
		score = 0;
		usedWords = new HashMap<>();
		validAnagrams = new HashMap<>();
		dictionary = new HashSet<>();
		try {
			Scanner scanner = new Scanner(new File("src/engmix.txt"));
			while (scanner.hasNextLine()) {
				dictionary.add(scanner.nextLine());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Character> chars = new ArrayList<>();
		for (char c : letters.toCharArray()) {
			chars.add(c);
		}
		validAnagrams.putAll(findAnagrams("", chars));
	}

	public String getLetters() {
		return letters;
	}

	public Set<String> getDictionary() {
		return dictionary;
	}

	public Map<String, Integer> getValidAnagrams() {
		return validAnagrams;
	}
	
	public Map<String, Integer> getSortedValidAnagrams() {
		return sortWords(validAnagrams);
	}

	public Map<String, Integer> getUsedWords() {
		return usedWords;
	}
	
	public Map<String, Integer> getSortedUsedWords() {
		return sortWords(usedWords);
	}

	public int getScore() {
		return score;
	}

	public Map<String, Integer> findAnagrams(String s, ArrayList<Character> chars) {
		Map<String, Integer> map = new HashMap<>();
		if (s.length() >= 3 && dictionary.contains(s.toLowerCase())) {
			map.put(s, getWordValue(s));
		}
		for (int i = 0; i < chars.size(); i++) {
			@SuppressWarnings("unchecked")
			ArrayList<Character> charsCopy = (ArrayList<Character>) chars.clone();
			char c = charsCopy.get(i);
			charsCopy.remove(i);
			map.putAll(findAnagrams(s + c, charsCopy));
		}
		return map;
	}

	public WordStatus tryWord(String s) {
		String word = s.toUpperCase();
		if (validAnagrams.containsKey(word)) {
			if (usedWords.containsKey(word)) {
				return WordStatus.WORD_USED;
			} else {
				int points = validAnagrams.get(word);
				usedWords.put(word, points);
				score += points;
				return WordStatus.VALID;
			}
		} else {
			return WordStatus.INVALID;
		}
	}
	
	public SortedMap<String, Integer> sortWords(Map<String, Integer> map) {
		SortedMap<String, Integer> sortedMap = new TreeMap<>( (a,b) -> {
			if (a.length() > b.length()) {
				return -1;
			} else if (b.length() > a.length()) {
				return 1;
			} else {
				return a.compareTo(b);
			}
		});
		sortedMap.putAll(map);
		return sortedMap;
	}
	
	public int getWordValue(String s) {
		switch (s.length()) {
		case 3:
			return 100;
		case 4:
			return 400;
		case 5:
			return 1200;
		case 6:
			return 2000;
		case 7:
			return 3000;
		}
		return 0;
	}

}
