package victor.codejam.tasks;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class SpeakingInTonguesTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	static final Map<Character, Character> DICT = new HashMap<Character, Character>();
	static {
		DICT.put('y', 'a');
		DICT.put('e', 'o');
		DICT.put('q', 'z');
		DICT.put(' ', ' ');
		String googlereseExample = 
				"ejp mysljylc kd kxveddknmc re jsicpdrysi" +
				"rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd" +
				"de kr kd eoya kw aej tysr re ujdr lkgc jv";
		String translationToNormal =
				"our language is impossible to understand" +
				"there are twenty six factorial possibilities" +
				"so it is okay if you want to just give up";
		for(int i = 0; i < googlereseExample.length() && DICT.size() < 27; i++) {
			char g = googlereseExample.charAt(i);
			if (!DICT.containsKey(g))
				DICT.put(g, translationToNormal.charAt(i));
		}
		
		Set<Character> alphabet = new HashSet<Character>();
		for(char c = 'a'; c <= 'z'; c++)
			alphabet.add(Character.valueOf(c));
		Set<Character> alphabetCpy = new HashSet<Character>(alphabet);
		alphabet.removeAll(DICT.keySet());
		if (alphabet.size() != 1)	
			System.out.println("Error! impossible to indentify the full dictionary");
		alphabetCpy.removeAll(DICT.values());
		if (alphabetCpy.size() != 1)	
			System.out.println("Error! several chars left to map to 1 last char in Googlerese alphabet");
		DICT.put(alphabet.iterator().next(), alphabetCpy.iterator().next());
	}
	
	String input, output;

	@Override
	protected void executeSolutionBody(TestCase testCase) {
		char[] outChars = new char[input.length()];
		for(int i = 0; i < input.length(); i++)
			outChars[i] = DICT.get(input.charAt(i));
		output = new String(outChars);
	}

	@Override
	protected TestCase parseTestCaseInput() {
		input = getStr();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(output);
	}

}
