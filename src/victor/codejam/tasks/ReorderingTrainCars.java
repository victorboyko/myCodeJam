package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class ReorderingTrainCars extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	int N;
	String[] cars;
	
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		// TODO Auto-generated method stub

	}

	@Override
	protected TestCase parseTestCaseInput() {
		N = getInt();
		cars = new String[N];
		for(int i = 0 ; i < N; i++) {
			cars[i] = getStr();
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		// TODO Auto-generated method stub

	}

}
