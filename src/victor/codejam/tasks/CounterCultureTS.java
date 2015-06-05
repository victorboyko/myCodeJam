package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class CounterCultureTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	
	long N, result;
	
	boolean isWorthFlipping(Long l, long max ) {
		if (flip(l) > max)
			return false;
		String s = l.toString();
		for(int i = 0; i < s.length() / 2; i++) {
			if (s.charAt(i) == s.charAt(s.length()-1-i))
				continue;
			if (s.charAt(i) < s.charAt(s.length()-1-i))
				return true; else return false;
		}
		return false;
	}
	
	long flip(Long l) {
		String s = l.toString();
		String newS = "";
		for(int i = 0; i < s.length(); i++) {
			newS += s.charAt(s.length()-1-i);
		}
		return Long.valueOf(newS);
	}
	
	
	// log(base 10) x=log(base e) x/log(base e) 10
	public static double log10(double x) {
		int base = 2;
		return Math.log(x) / Math.log(base);
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		if (N <= 19) {
			result = N;
			return;
		}
		
		Long i = result = 19l;
			
		int rangeDiff = (int)(Math.ceil(log10(N)) - Math.ceil(log10(i)));
		
		result +=  Math.max(0, 18*(rangeDiff-1) + rangeDiff - 1 -1);
		
		
		
		//System.out.println("i : = " + i);
		for(; i < N; result++) {
			if (isWorthFlipping(i, N))
				i = flip(i);
			else i++;
		}
	}

	@Override
	protected TestCase parseTestCaseInput() {
		N = getLong();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
