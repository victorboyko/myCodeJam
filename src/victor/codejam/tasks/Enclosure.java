package victor.codejam.tasks;
//TODO

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class Enclosure extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	int N, M, K, result;

	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		if (K <= 4) {
			result = K;
			return;
		}
		if (N < M) {
			int buf = N;
			N = M;
			M = buf;
		}
		int surface = K+4;
		int side = (int)Math.ceil(Math.sqrt(surface));
		int vert = side;
		if (vert > M) {
			vert = M;
		}
		if (M <= 2) {
			result = K;
			return;
		}
		
		int hor = (int)Math.ceil((double)surface / vert);
		
		int cnt = (hor - 2) * 2 + (vert - 2)*2;
		int cnt2 = 0;
		int oldCnt = 0;
		do {
			oldCnt = cnt2;
			hor--;
			cnt2 = (hor - 2) * 2 + (vert - 2)*2;
			cnt2 += K - ( (hor-2)*(vert-2) + cnt2);
		
		} while(oldCnt != cnt2 && cnt2 < cnt);
		
		if (cnt2 > cnt) 
			result = cnt;
		else result = cnt;
		
	}

	@Override
	protected TestCase parseTestCaseInput() {
		N = getInt();
		M = getInt();
		K = getInt();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
