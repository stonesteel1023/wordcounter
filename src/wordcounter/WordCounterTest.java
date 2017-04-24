package wordcounter;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;

public class WordCounterTest {

	Map<String, Long> wordCounts;

	@Test(expected = IOException.class)
	public void testFileDoesNotExists() throws IOException {
		wordCounts = new WordCounter(new File("dosnotexists.txt").getCanonicalPath()).countWords();
	}

	@Test
	public void testCountWords() throws IOException {
		wordCounts = new WordCounter(new File("tempest.txt").getCanonicalPath()).countWords();
		assertEquals(514, wordCounts.get("and").longValue());
		assertEquals(513, wordCounts.get("the").longValue());
		assertEquals(446, wordCounts.get("i").longValue());
		assertEquals(324, wordCounts.get("to").longValue());
		assertEquals(310, wordCounts.get("a").longValue());
		assertEquals(295, wordCounts.get("of").longValue());
		assertEquals(288, wordCounts.get("my").longValue());
		assertEquals(211, wordCounts.get("you").longValue());
		assertEquals(188, wordCounts.get("that").longValue());
		assertEquals(185, wordCounts.get("this").longValue());
	}

}
