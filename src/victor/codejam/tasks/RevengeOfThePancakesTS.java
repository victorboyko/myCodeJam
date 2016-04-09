package victor.codejam.tasks;

import java.util.HashMap;
import java.util.Map;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class RevengeOfThePancakesTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	int result;
	boolean[] bArr;
	
	void reverse(boolean[] array) {
		for (int i = 0; i < array.length / 2; i++) {
			  boolean temp = array[i];
			  array[i] = array[array.length - 1 - i];
			  array[array.length - 1 - i] = temp;
		}
	}
	
	void negate(boolean[] array) {
		for(int i = 0; i < array.length; i++) {
			array[i] = !array[i];
		}
	}
	
	static Map<boolean[], Integer> CACHE = new HashMap<boolean[], Integer>();
	
	int getDepth(boolean bArr[]) {
		
		if (CACHE.containsKey(bArr)) {
			return CACHE.get(bArr);
		}
		
		int lastIndex = bArr.length - 1;
		for(;lastIndex >=0 && bArr[lastIndex];) {
			lastIndex--;
		}
		if (lastIndex != bArr.length - 1) {
			boolean[] bCArr = new boolean[lastIndex+1];
			System.arraycopy(bArr, 0, bCArr, 0, bCArr.length);
			bArr = bCArr;
			if (CACHE.containsKey(bArr)) {
				return CACHE.get(bArr);
			}
		}
		
		if (bArr.length == 0) return 0;
		if (bArr.length == 1) return (bArr[0]) ? 0 : 1;
		
		boolean allNegative = true;
		for(int i = 0; i < bArr.length; i++) {
			allNegative &= !bArr[i];
		}
		if (allNegative) {
			CACHE.put(bArr, 1);
			return 1;
		}
		
		int res = 10000000;
		
		boolean[] bCArr = new boolean[bArr.length];
		System.arraycopy(bArr, 0, bCArr, 0, bCArr.length);
		reverse(bCArr);
		negate(bCArr);
		
		for (int i = 1; i <= bArr.length-1; i++) {
			
			
			boolean[] b1Arr = new boolean[i];
			boolean[] b2Arr = new boolean[bArr.length - b1Arr.length];
			
			System.arraycopy(bCArr, 0, b1Arr, 0, b1Arr.length);
			System.arraycopy(bCArr, b1Arr.length, b2Arr, 0, b2Arr.length);
			
			res = Math.min(res, getDepth(b1Arr) + getDepth(b2Arr) + 1);
		}
		
		CACHE.put(bArr, res);		
		return res;
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = getDepth(bArr);
	}

	@Override
	protected TestCase parseTestCaseInput() {
		String s = getStr();
		bArr = new boolean[s.length()];
		for(int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+') {
				bArr[i] = true;
			}
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
