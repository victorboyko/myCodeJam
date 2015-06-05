// https://code.google.com/codejam/contest/2442487/dashboard#s=p1

package victor.codejam.tasks;

import java.math.BigInteger;

import victor.codejam.AbstractTaskSolution;

public class ManyPrizesTS extends AbstractTaskSolution {
	
	class MPTestCase implements TestCase {
		int N, P;
		BigInteger guaranteed, possible;
	}
	
	// log(base 2) x=log(base e) x/log(base e) 2
	public static double log2(double x) {
		int base = 2;
		return Math.log(x) / Math.log(base);
	}

	@Override
	protected void executeSolutionBody(TestCase testCase) {
		MPTestCase tc = (MPTestCase)testCase;
		


		//TODO
		tc.possible = BigInteger.ZERO;

	}

	@Override
	protected TestCase parseTestCaseInput() {
		MPTestCase tc = new MPTestCase();
		tc.N = getInt();
		tc.P = getInt();
		return tc;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		MPTestCase tc = (MPTestCase)testCase;
		putStrNoNewLine(tc.guaranteed.toString());
		putStrNoNewLine(tc.possible.toString());
	}

}
