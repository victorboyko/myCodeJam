package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class CloseMatchTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		
		
		while (!defined(a) || !defined(b)) {
		
			//leading ?? run
			for(int i = 0; i < (a.length - 1); i++) {
				if (a[i] <= 0 && a[i+1] <= 0 && b[i] <= 0 && b[i+1] <= 0) {
					a[i] = b[i] = 0;
				} else {
					break;
				}
			}
			
			//first bigger or less
			for(int i = 0; i < a.length; i++) {
				if (a[i] == -1 || b[i] == -1) {
					break;
				}
				if (a[i] > b[i]) {
					fillGaps(a, 0);
					fillGaps(b, 9);
					return;
				}
				if (a[i] < b[i]) {
					fillGaps(a, 9);
					fillGaps(b, 0);
					return;
				}
			}
			// end - first bigger or less
			
			// ?9
			// ?X  - pattern
			//
			
			for(int i = 0; i < (a.length - 1); i++) {
				if (a[i] == -1 && a[i+1] == 9 && b[i] == -1) {
					int[] a1 = cloneArray(a);
					int[] b1 = cloneArray(b);
					fillGaps(a1, 0);
					b1[i] = 1;
					fillGaps(b1, 9);
					long diff1 = getDiff(a1, b1);
					int[] a2 = cloneArray(a);
					int[] b2 = cloneArray(b);
					
					
				}
				
				
			}
			
		}
		

	}
	
	int[] a, b;
	
	long getDiff(int[] a, int[] b) {
		return Math.abs(getLongOfArray(a) - getLongOfArray(b));
	}
	
	void fillGaps(int[] arr, int val) {
		for(int i = 0; i < arr.length; i++) {
			if (arr[i] == -1) {
				arr[i] = val;
			}
		}
	}
	
	boolean defined(int[] arr) {
		for(int el : arr) {
			if (el == -1) return false;
		}
		return true;
	}
	
	int[] cloneArray(int[] arr) {
		int[] result = new int[arr.length];
		System.arraycopy(arr, 0, result, 0, arr.length);
		return result;
	}
	
	long getLongOfArray(int[] arr) {
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < arr.length; i++) {
			res.append("" + arr[i]);
		}
		return Long.valueOf(res.toString());
	}

	@Override
	protected TestCase parseTestCaseInput() {
		String aStr = getStr();
		a = new int[aStr.length()];
		for(int i = 0; i < aStr.length(); i++) {
			char c = aStr.charAt(i);
			if (c == '?') {
				a[i] = -1;
			} else {
				a[i] = c - '0';
			}
		}
		
		String bStr = getStr();
		b = new int[bStr.length()];
		for(int i = 0; i < bStr.length(); i++) {
			char c = bStr.charAt(i);
			if (c == '?') {
				b[i] = -1;
			} else {
				b[i] = c - '0';
			}
		}
		
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(getLongOfArray(a) + " " + getLongOfArray(b));
	}

}
