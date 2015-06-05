package victor.codejam.tasks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;

//http://code.google.com/codejam/contest/90101/dashboard#s=p0
public class AlienLanguageTS extends AbstractTaskSolution {
	
	int nWordLen, nWords;
	Set<String> words;
	
	class Key {
		int n;
		char c;
		public Key(int n, char c) {
			this.n = n;
			this.c = c;
		}
		
		@Override
		public boolean equals(Object obj) {
			Key key = (Key)obj;
			return key.n == n && key.c == c;
		}
		
		@Override
		public int hashCode() {
			return n + c << 8;
		}
		
		@Override
		public String toString() {
			return "(" + n + ":" + c + ")";
		}
	}
	
	Map<Key, Set<String>> wordsSets;
	
	class Tc implements TestCase {
		String pattern;
		int result;
		public Tc(String pattern) {
			this.pattern = pattern;
		}
	}

	@Override
	public List<String> processAndReply(List<String> input) {
		if (nWordLen == 0) {
			String[] strs = input.remove(0).split(" ");
			nWordLen = Integer.parseInt(strs[0]);
			nWords = Integer.parseInt(strs[1]);
			words = new HashSet<String>(nWords);
			wordsSets = new HashMap<Key, Set<String>>();
			for(int i = 0; i < nWords; i++) {
				String word = input.remove(0);
				words.add(word);
				for(int j = 0; j < nWordLen; j++) {
					Key key = new Key(j, word.charAt(j));
					Set<String> wordsSet;
					if (wordsSets.containsKey(key))
						wordsSet = wordsSets.get(key);
					else 
						wordsSets.put(key, wordsSet = new HashSet<String>());
					wordsSet.add(word);
				}
			}
			input.add(0, strs[2]);
		}
		return super.processAndReply(input);
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		Tc tc = (Tc)testCase;
		Set<String> wordsLeft = new HashSet<String>(words);
		Set<String> wordsLocal = new HashSet<String>();
		int counter = 0;
		boolean open = false;
		for(int i = 0; i < tc.pattern.length(); i++) {
			char c = tc.pattern.charAt(i);
			if (c == '(') {
				open = true;
				continue;
			}
			if (c == ')') {
				counter++;
				wordsLeft.retainAll(wordsLocal);
				wordsLocal.clear();
				open = false;
				continue;
			}
			
			Key key = new Key(counter, c);
			if (wordsSets.containsKey(key))
				wordsLocal.addAll(wordsSets.get(key));
			
			if (!open) {
				counter++;
				wordsLeft.retainAll(wordsLocal);
				wordsLocal.clear();
			}
		}
		tc.result = wordsLeft.size();

	}

	@Override
	protected TestCase parseTestCaseInput() {
		return new Tc(getStr());
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		Tc tc = (Tc)testCase;
		putIntNoNewLine(tc.result);
	}

}
