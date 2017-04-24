package wordcounter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCounter {

	Path textFilePath;

	public WordCounter(String path) {
		textFilePath = Paths.get(path);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Long> countWords() throws IOException {
		return    Files.lines(this.textFilePath, Charset.defaultCharset())
				 .map(line -> line.replaceAll("[^a-zA-Z0-9\'\"\\s-]"," "))
	            .map(line -> line.split("\\s+"))
	            .flatMap(Arrays::stream)
	            .map(word -> word.trim().toLowerCase())
	            .filter(word -> word.length() > 0)         
	            .collect( Collectors.groupingByConcurrent(Function.identity(), Collectors.counting()))
	            .entrySet()
	            .stream()
	            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(10)
				.collect(Collectors.toMap(
			                Map.Entry::getKey, 
			                Map.Entry::getValue, 
			                (e1, e2) -> e1, 
			                LinkedHashMap::new
			              ));
	}
	
	public static void main(String[] args) throws IOException {
		
		Map<String, Long> wordCounts  = new WordCounter(new File("tempest.txt").getCanonicalPath()).countWords();
		wordCounts.forEach((k, v) -> System.out.println(String.format("%s ==>> %d", k, v)));
	}

}
