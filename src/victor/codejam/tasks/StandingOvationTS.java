package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.List;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class StandingOvationTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	int result, maxLvl;
	List<Integer> audience = new ArrayList<Integer>();
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		int endIndex;
		for(endIndex = maxLvl; endIndex >=0 && audience.get(endIndex) != 0; endIndex--) 
			{}
		int gap = 0;
		for(int i = 0; i <= endIndex; i++) {
			gap += Math.max(0, audience.get(i) -1);
			if (audience.get(i) == 0) {
				if (gap == 0)
					result++;
				else gap--;
			}
		}
		
	}

	@Override
	protected TestCase parseTestCaseInput() {
		audience.clear();
		maxLvl = getInt();
		for(char c : getStr().toCharArray())
			audience.add(Integer.valueOf("" + c));
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
