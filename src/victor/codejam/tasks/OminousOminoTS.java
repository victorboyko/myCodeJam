package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class OminousOminoTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	int X, R, C;
	boolean isRichard;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		if (C > R) {
			int tmp = C;
			C = R; R = tmp;
		}
		
		isRichard = false;
		if (X > R) {
			isRichard = true; return;
		}
		if (X >= 7) {
			isRichard = true; return;
		}
		if ((R * C) % X != 0) {
			isRichard = true; return;
		}
		
		if (X <= 2) {
			isRichard = false; return;
		}
		if (X == 3) {
			if (C == 1) {
				isRichard = true;
				return;
			}
			isRichard = false;
			return;
		}
		
		if (X == 4 || X == 5) {
			if (C >= 3) {
				isRichard = false; return;
			}
			isRichard = true; return;
		}
		
		if (X == 6) {
			if (R < 6 || C < 4) {
				isRichard = true; return;
			}
			isRichard = false; return;
		}
	}

	@Override
	protected TestCase parseTestCaseInput() {
		X = getInt();
		R = getInt();
		C = getInt();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(isRichard ? "RICHARD" : "GABRIEL");
	}

}
