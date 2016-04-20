package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class TheLastWordTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	String S;
	String result;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = "";
		result += S.charAt(0);
		for(int i = 1; i < S.length(); i++) {
			char c = S.charAt(i);
			if (c < result.charAt(0)) {
				result += c;
			} else {
				result = c + result;
			}
		}
	}

	@Override
	protected TestCase parseTestCaseInput() {
		S = getStr();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(result);
	}

}
