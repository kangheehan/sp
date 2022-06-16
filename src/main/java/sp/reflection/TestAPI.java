package sp.reflection;

import java.util.ArrayList;
import java.util.List;

public class TestAPI {

	public List<String> getWordList(String str) {
		List<String> wordList = new ArrayList<String>();
		
		String[] tokens = str.split(" ");
		for(String token : tokens) {
			wordList.add(token);
		}
		
		return wordList;
	}
	
	// 콜백 방식
	public void getWordList(String str, ResultHandler handler) {
		List<String> wordList = new ArrayList<String>();
		
		String[] tokens = str.split(" ");
		for(String token : tokens) {
			wordList.add(token);
		}
		
		handler.handler(wordList);
	}
	
	// 콜백 핸들러
	public interface ResultHandler {
		public void handler(List<String> result);
	}
}
