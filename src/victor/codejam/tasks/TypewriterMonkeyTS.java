package victor.codejam.tasks;

import java.util.Random;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class TypewriterMonkeyTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	int K, L, S;
	double result;
	String word, alpha;

	static int containsNum(String big, String small) {
		int res = 0;
		for(int i = 0; i <= big.length() - small.length(); i++) {
			if (big.substring(i, i + small.length() - 1).equals(small))
				res++;
		}
		return res;
	}
	
	static Random rnd = new Random();
	static final long REP = 10000;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0d;

		if (S < word.length())
			return;
		
		long cnt = 0;

			// TODO :( not even close
		for(int i = 0; i < REP; i++) {
			String newWord = "";
			for(int j = 0; j < S; j++) {
				newWord += alpha.charAt(rnd.nextInt(alpha.length()));
			}
			cnt += containsNum(newWord, word);
		}
		result = cnt/((double)REP);
		
	}

	@Override
	protected TestCase parseTestCaseInput() {
		K = getInt();
		L = getInt();
		S = getInt();
		alpha = getStr();
		word = getStr();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putDblNoNewLine(result);
	}

}
