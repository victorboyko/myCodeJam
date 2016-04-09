package victor.codejam.tasks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class CountingSheepTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	int N;
	Integer result;
	final static Set<Character> ALL_DIGITS = new HashSet<Character>();
	static {
		for(Character c : "0123456789".toCharArray()) {
			ALL_DIGITS.add(c);
		}
	}

	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = null;
		Set<Character> digitsMet = new HashSet<Character>(ALL_DIGITS);
		for(int i = 1; i <= 101; i++) {
			Integer x = N * i;
			for(Character j : x.toString().toCharArray()) {
				digitsMet.remove(j);
			}
			if (digitsMet.isEmpty()) {
				result = x;
				return;
			}
		}
	}

	@Override
	protected TestCase parseTestCaseInput() {
		N = getInt();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(result == null ? "INSOMNIA" : result.toString());
	}

}
