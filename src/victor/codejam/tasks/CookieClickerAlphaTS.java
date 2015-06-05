package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class CookieClickerAlphaTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	private double N, D, S, result;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0d;
		//double n = (S/N - 2/D - 1);
		//n = (n < 0) ? 0 : Math.ceil(n);
		// I give up, it's hard to get the harmonic value fast. Will iterate
		
		for(long n = 0; ; n++) {
			
			double dt = N / (2 + n*D);
			double fdt = dt + S / (2 + (n+1)*D);
			double st =  S / (2 + n*D);
			
			if (st <= fdt) {
				result += st;
				return;
			}
			result += dt;
		}
		
	}

	@Override
	protected TestCase parseTestCaseInput() {
		N = getDouble();
		D = getDouble();
		S = getDouble();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putDblNoNewLine(result);
	}

}
