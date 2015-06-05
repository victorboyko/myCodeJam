package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//TODO
public class FallingDiamondsTS extends AbstractTaskSolution implements NoTestCasePreload {

	int n, x, y;
	double result;
	

	// log(base 2) x=log(base e) x/log(base e) 2
	/*
	public static int getLogarithm(int base, double x) {
		return (int)(Math.ceil(Math.log(x) / Math.log(base)));
	}
	*/
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0d;
		if (x < 0) x = -x;
		int full = (int)Math.floor((-1d + Math.sqrt(1d + 8d * n))/2d);
		if (full % 2 == 0)
			full --;
		int yMax = full - 1;
		if (x + y <= yMax) {
			result = 1d;
			return;
		}
		if (x + y > yMax + 2) {
			result = 0d;
			return;
		}
		int left = n - ((full * (full + 1)) / 2);
		full += 2; // now full is r for possible! pyramid
		yMax += 2; // yMax too 
		if (left - full >= y ) {
			result = 1d;
			return;
		}
		if (left < y + 1 || y == yMax) {
			result = 0d;
			return;
		}
		
		
		if (left > yMax) {
			full -= left - yMax;
			y -= left - yMax;
			left -= (left - yMax) * 2;
		} //full, left and y are of a different value since here
		
//		for (int i = y; i < left; i++) {
//			result += Math.pow(0.5d, i) * (1d - Math.pow(0.5d, (left - i)) );
//		}
		
		result = Math.pow(0.5d, y) * (1d - Math.pow(0.5d, (left - y)) );
	}

	@Override
	protected TestCase parseTestCaseInput() {
		n = getInt();
		x = getInt();
		y = getInt();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putDblNoNewLine(result);
	}

}
