package victor.codejam.tasks;

import java.util.HashSet;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class MagicTrickTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	private Set<Integer> first, second;
	String result;
	
	private Set<Integer> getCardsList() {
		int r = getInt() - 1;
		
		int[][] grid = new int[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				grid[i][j] = getInt();
			}
		}
		
		Set<Integer> first = new HashSet<Integer>();
		for(int i = 0; i < 4; i++) {
			first.add(grid[r][i]);
		}
		
		return first;
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		boolean found = false;
		int foundOne = 0;
		for(int i = 0; i < 4; i++) {
			Integer x = first.iterator().next();
			first.remove(x);
			if (second.contains(x)) {
				if (found) {
					result = "Bad magician!";
					return;
				}
				found = true;
				foundOne = x;
			}
		}
		
		result = found ? String.valueOf(foundOne) : "Volunteer cheated!";

	}

	@Override
	protected TestCase parseTestCaseInput() {
		first = getCardsList();
		second = getCardsList();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(result);
	}

}
