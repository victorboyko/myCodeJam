package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class DanceAroundTheClockTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	private int nDancers, nTurns, kOne, right, left;

	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		nTurns = nTurns % nDancers;
		
		kOne--;
		
		if (kOne % 2 == 0) {
			right = (kOne + 1 + 2*nTurns) % nDancers;
			left =  (kOne - 1 + 2*nTurns + nDancers) % nDancers;
		} else {
			right = (kOne + 1 - 2*nTurns + 2*nDancers) % nDancers;
			left =  (kOne - 1 - 2*nTurns + 2*nDancers) % nDancers;
		}
		right++;
		left++;
	}

	@Override
	protected TestCase parseTestCaseInput() {
		nDancers = getInt();
		kOne = getInt();
		nTurns = getInt();
		
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(right);
		putIntNoNewLine(left);
	}

}
