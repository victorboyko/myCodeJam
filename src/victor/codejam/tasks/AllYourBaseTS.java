package victor.codejam.tasks;

import java.util.HashMap;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class AllYourBaseTS extends AbstractTaskSolution implements NoTestCasePreload {

	private String input;
	private long result;
	
	static class PowKey {
		int x, y;
		
		public PowKey(int x_, int y_) {
			x = x_;
			y = y_;
		}
		
		@Override
		public boolean equals(Object obj) {
			PowKey objPow = (PowKey)obj;
			return obj == PowKey.this || (objPow.x == x && objPow.y == y);
		}
		
		@Override
		public int hashCode() {
			return x + y << 4;
		}
	}
	
	static HashMap<PowKey, Long> powCashe = new HashMap<AllYourBaseTS.PowKey, Long>();
	
	public static long pow(int a, int b) {
		long res = 0;
		if (b == 0)
			return 1;
		PowKey powKey = new PowKey(a, b);
		if (powCashe.containsKey(powKey)) {
			return powCashe.get(powKey);
		}
		res = pow(a, b / 2) * pow(a, b / 2);
		if (b % 2 == 1)
			res *= a;
		return res;
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		boolean usedZero = false;
		boolean[] used = new boolean[input.length()];
		int base = 1;
		int[] decodedNumber = new int[input.length()]; 
		for(int i = 0; i < input.length(); i++) {
			if (!used[i]) {
				int localBase; 
				if (!usedZero && base == 2) {
					localBase = 0;
				} else {
					localBase = base;
				}
				
				char c = input.charAt(i);
				decodedNumber[i] = localBase;
				used[i] = true;
				for(int j = i + 1; j < input.length(); j++) {
					if (input.charAt(j) == c) {
						decodedNumber[j] = localBase;
						used[j] = true;
					}
				}
				if (!(!usedZero && base == 2)) base++;
					else usedZero = true;
			}
		}

		for(int i = input.length() - 1; i >= 0; i--) {
			result += decodedNumber[i] * pow(base, input.length() - 1 - i);
		}
	}

	@Override
	protected TestCase parseTestCaseInput() {
		input = getStr();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
