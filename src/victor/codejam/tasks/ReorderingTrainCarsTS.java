package victor.codejam.tasks;

import java.util.LinkedList;
import java.util.List;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class ReorderingTrainCarsTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	List<String> cars;
	int NUM;
	
	long result;

	@Override
	protected void executeSolutionBody(TestCase testCase) {
		//TODO 

	}

	@Override
	protected TestCase parseTestCaseInput() {
		cars = new LinkedList<String>();
		NUM = getInt();
		for(int i = 0; i < NUM; i++) {
			cars.add(getStr());
		}
		
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
