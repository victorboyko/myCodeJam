package victor.codejam.tasks;

import java.util.HashSet;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class SlidesTS extends AbstractTaskSolution implements NoTestCasePreload {

	int B;
	long M;
	long[][] res;
	
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		res = new long[B][B];
		Set<Long> tried = new HashSet<Long>();
		
		
//		for(int i = 0; i < B-2; i++) {
//			res[i][i+1] = 1;
//		}
//		long curCnt = 1;
//		
//		
		
		for(int i = 0; i < B-2; i++) {
			for(int j = i+1; j < B-1; j++) {
				
				
			}
		}


	}

	@Override
	protected TestCase parseTestCaseInput() {
		B = getInt();
		M = getLong();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(res == null ? "IMPOSSIBLE" : "POSSIBLE");
		for(int i = 0; i < B; i++) {
			putStrNoNewLine("\n");
			for(int j = 0; j < B; j++) {
				putStrNoNewLine(String.valueOf(res[i][j]));
			}
		}

	}

}
