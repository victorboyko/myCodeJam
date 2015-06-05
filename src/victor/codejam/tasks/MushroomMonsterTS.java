package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class MushroomMonsterTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	int num;
	List<Integer> in = new ArrayList();
	long result1, result2;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result1 = result2 = 0;

		int prev = 0;
		int maxDiff = 0;
		for(int j = 0; j < num; j++) {
			int i = in.get(j);
			
			int diff = prev - i;
			if (diff > 0)
				result1 += prev-i;
			if (diff > maxDiff)
				maxDiff = diff;
			prev = i;
		}
		
		for(int j = 0; j < num-1; j++) {
			int i = in.get(j);
			
			result2 += Math.min(i, maxDiff);
		}
		
	}

	@Override
	protected TestCase parseTestCaseInput() {
		num = getInt();
		in.clear();
		for(int i = 0; i < num; i++)
			in.add(getInt());
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result1);
		putIntNoNewLine(result2);
	}

}
