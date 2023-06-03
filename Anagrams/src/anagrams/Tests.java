package anagrams;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Tests {
	
	@Test
	void test() {
		AnagramsGame game = new AnagramsGame("string");
		System.out.println(game.getSortedValidAnagrams());
		assertEquals(WordStatus.VALID, game.tryWord("sTriNg"));
		assertEquals(2000, game.getScore());
		assertEquals(WordStatus.WORD_USED, game.tryWord("sTriNg"));
		assertEquals(2000, game.getScore());
		assertEquals(WordStatus.VALID, game.tryWord("grist"));
		assertEquals(WordStatus.INVALID, game.tryWord("INSTR342432"));
		assertEquals(WordStatus.INVALID, game.tryWord("INSTR_"));
		assertEquals(WordStatus.INVALID, game.tryWord("INSTR "));
		assertEquals(3200, game.getScore());
		System.out.println(game.getSortedUsedWords());
	}
	
}
