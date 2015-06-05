package victor.codejam.tasks;

import java.util.Random;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

// https://code.google.com/codejam/contest/32013/dashboard#s=p2

public class FlySwatterTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	Random rnd = new Random();
	
	double f, R, t, r, g, result; 
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		//TODO - bad solution, precision is not enough. needs to be solved with math.
		// soory, I'll skil this one. Math solving is boring now
		
		result = 0d;
		int actualCnt = 0;
		int TST_CNT_ROOT = 5000;
		for(int i = 0; i < TST_CNT_ROOT*TST_CNT_ROOT; i++) {
			actualCnt++;
			boolean hit = false;
			
			double x, y;
			x = y = 2*R;
			
			while(x*x + y*y >= R*R) {
				x = rnd.nextDouble()*R;
				y = rnd.nextDouble()*R;
			}
			
			hit |= x*x + y*y >= (R-t-f)*(R-t-f);
			
			long fullX = (long)(Math.floor(x/(g+2*r)));
			long fullY = (long)(Math.floor(y/(g+2*r)));
			boolean hitX = x - fullX*(g+2*r) <= r + f;
			boolean hitY = y - fullY*(g+2*r) <= r + f;
			
			hitX |= x - fullX*(g+2*r) >= r + f + (g-2*f);
			hitY |= y - fullY*(g+2*r) >= r + f + (g-2*f);
			
			hit |= hitX || hitY;
			
			if (hit)
				result += 1d;
			
		}
		result /= actualCnt;
	}

	@Override
	protected TestCase parseTestCaseInput() {
		System.out.print(".");
		f = getDouble();
		R = getDouble();
		t = getDouble();
		r = getDouble();
		g = getDouble();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putDblNoNewLine(result, 12);
	}

}
