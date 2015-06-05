package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class OsmosTS extends AbstractTaskSolution implements NoTestCasePreload {

	
	List<Integer> motes;
	int nMotes, result, initial;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		Collections.sort(motes);
		result = 0;
		int maxRes = nMotes;
		out:
		for(int cnt = 0; cnt < nMotes; cnt++) {
			int i = motes.get(cnt);
			if (initial <= i) {
				maxRes = Math.min(maxRes, result + nMotes - cnt);
			}
			while(initial <= i) {
				initial += initial - 1;
				result++;
				if (result >= maxRes) {
					result = maxRes;
					break out;
				}
			}
			initial += i;
		}
	}

	@Override
	protected TestCase parseTestCaseInput() {
		initial = getInt();
		nMotes = getInt();
		motes = new ArrayList<Integer>();
		for(int i = 0; i < nMotes; i++)
			motes.add(getInt());
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
