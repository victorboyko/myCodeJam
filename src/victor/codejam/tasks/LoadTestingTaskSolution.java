package victor.codejam.tasks;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//https://code.google.com/codejam/contest/dashboard?c=619102#s=p1
public class LoadTestingTaskSolution extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	final static Logger logger = Logger.getLogger(LoadTestingTaskSolution.class);
	{ logger.setLevel(Level.INFO); }
	
	int L, P, C, result;
	
	// log(base 2) x=log(base e) x/log(base e) 2
	
	public static int getLogarithm(int base, double x) {
		return (int)(Math.ceil(Math.log(x) / Math.log(base)));
	}
	
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		// TODO
		result = getLogarithm(C, (double)P / (double)L);
		result = getLogarithm(2, result);
		//logger.info(getLogarithm(2, 8));
	}

	static int counter;
	@Override
	protected TestCase parseTestCaseInput() {
		L = getInt();
		P = getInt();
		C = getInt();
		logger.debug("Another testcase " + ++counter + ", L=" + L + ", P=" + P + ", C=" + C);
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
