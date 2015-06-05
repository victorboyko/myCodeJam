package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class BrattleshipTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	int R, C, W, result;

	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		if (W == 1) {
			result = R * C;
			return;
		}
		

		result = 0;
		if (R < C) {int tmp = R; R = C; C = tmp;}

		if (C == 1 && R == W) {
			result = W; return;
		}
			
		
		int baseR = R / W;
		result += baseR * C + (C / W);


		
		result += W - 1;
		
		
		if (C == 1 && R % W == 0 && W == 2) {
			return;
		}
		
		result += ((R % W != 0) && (R != W)) ? 1 : 0;
		if (C >= W)
		result += ((C % W != 0) && (C != W)) ? 1 : 0;

	}

	@Override
	protected TestCase parseTestCaseInput() {
		R = getInt();
		C = getInt();
		W = getInt();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
