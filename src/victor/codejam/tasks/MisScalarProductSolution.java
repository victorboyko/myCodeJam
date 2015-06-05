package victor.codejam.tasks;

import java.util.Arrays;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//http://code.google.com/codejam/contest/32016/dashboard#s=p0
public class MisScalarProductSolution extends AbstractTaskSolution implements NoTestCasePreload {

	long[] v1, v2;
	long result;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		Arrays.sort(v1);
		Arrays.sort(v2);
		for(int i=0; i<v1.length; i++)		
			result += v1[i] * v2[v1.length-1-i];
	}	
	
	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(result + "");
	}
	
	@Override
	protected TestCase parseTestCaseInput() {
		int n = getInt();
		v1 = new long[n];
		v2 = new long[n];
		for(int i = 0; i < n; i++)
			v1[i] = getInt();
		for(int i = 0; i < n; i++)
			v2[i] = getInt();
		return null;
	}
	
}
