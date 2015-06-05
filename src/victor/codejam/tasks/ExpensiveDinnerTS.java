package victor.codejam.tasks;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

// https://code.google.com/codejam/contest/1150486/dashboard#s=p2

//TODO - works for small set only
// for big set - combine all possible prime submultipliers where at least one of prime submultipliers is met more then once.
// multiplication results (all that <= 10^12) should be sorted then

public class ExpensiveDinnerTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	
	private static String formatDouble(double d, int digitsNum) {
		String s = String.format("%."+digitsNum+"f", d);
		if (!s.endsWith("0"))
			return s;
		int i;
		for(i = s.length()-1; s.charAt(i) == '0'; i--) { }
		return s.charAt(i) == '.' ? s.substring(0, i+2) : s.substring(0, i+1);
	}

	static Map<Long, Long> primes = new TreeMap<Long, Long>();
	static Map<Long, Long> results = new TreeMap<Long, Long>();
	
	static {
		results.put(1L, 0L);
		results.put(2L, 1L);
		primes.put(2L, 1L);
		
		long oldRes = 1L, res = 1L;
		for(long i = 3L; i < 1000 /* 000000000L*/; i++) {
//			if (i % 1000L == 0)
//				System.out.println(formatDouble(((double)i)/10000000000L, 8) +"%");

			for(long j=2L, n=i; n != 1; j++) {
				long localRes = 1;
				if (primes.containsKey(j))
					localRes = primes.get(j);
				
				while (n % j == 0) {
					n /= j;
					localRes--;
					if (localRes < 0)
						res++;
				}
				if (localRes < 0 || !primes.containsKey(j))
					primes.put(j, (primes.containsKey(j) ? primes.get(j) : 1) - localRes);
				if (res != oldRes) {
					results.put(i, res);
					oldRes = res;
				}
			}
			
		}
		
	}
	
	long N, res;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		Long[] keys = results.keySet().toArray(new Long[results.size()]);
		int index = Arrays.binarySearch(keys, N);
		res = results.get(keys[(index < 0) ? -(index+2) : index]);
	}

	@Override
	protected TestCase parseTestCaseInput() {
		N = getLong();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(res);
	}

}
