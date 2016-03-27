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
		
		//int kPos = (kOne % 2 == 0) ? nRightTurns : (nDancers - nLeftTurns) % nTurns;
		
		if (kOne % 2 == 0) {
			right = (nTurns + kOne + 1) % nDancers;
			left = (nTurns + nDancers + kOne - 1) % nDancers;
		} else {
			right = (2*nDancers - nTurns - kOne + 1) % nDancers;
			left = (2*nDancers - nTurns - kOne - 1) % nDancers;
		}
		right++;
		left++;
		//TODO
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
