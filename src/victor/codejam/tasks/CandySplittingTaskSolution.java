package victor.codejam.tasks;

import java.util.LinkedList;
import java.util.List;

import victor.codejam.AbstractTaskSolution;

// https://code.google.com/codejam/contest/975485/dashboard#s=p2
public class CandySplittingTaskSolution extends AbstractTaskSolution {
	
	protected class TestCaseImp implements TestCase {
		List<Integer> candies;
		Integer SeansRemains;
		
		
	}

	@Override
	protected void executeSolutionBody(TestCase testCase) {
		TestCaseImp tc = (TestCaseImp)testCase;
		int PatrickMathTotal = 0;
		int SeansMathTotal = 0;
		int min = tc.candies.get(0);
		for(int candie: tc.candies) {
			PatrickMathTotal ^= candie;
			SeansMathTotal += candie;
			min = Math.min(min, candie);
		}
		if (PatrickMathTotal !=0) {
			tc.SeansRemains = null;
		} else {
			tc.SeansRemains = SeansMathTotal - min;
		}
	}

	@Override
	protected TestCase parseTestCaseInput() {
		TestCaseImp testCase = new TestCaseImp();
		testCase.candies = new LinkedList<Integer>();
		int candiesNum = getInt();
		for(int i=0; i< candiesNum; i++) {
			testCase.candies.add(getInt());
		}
		return testCase;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		TestCaseImp tc = (TestCaseImp)testCase;
		if (tc.SeansRemains==null) {
			putStrNoNewLine("NO");
		} else {
			putIntNoNewLine(tc.SeansRemains);
		}
	}

}
