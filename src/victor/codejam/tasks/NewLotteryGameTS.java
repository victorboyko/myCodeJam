package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class NewLotteryGameTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	long A, B, K, result;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		for(int i = 0; i < A; i++) {
			for(int j = 0; j < B; j++) {
				if ((i & j) < K)
					result++;
			}
		}

	}

	@Override
	protected TestCase parseTestCaseInput() {
		A = getLong();
		B = getLong();
		K = getLong();

		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine("" + result);
	}

}
