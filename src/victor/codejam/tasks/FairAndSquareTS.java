package victor.codejam.tasks;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;
import victor.codejam.Util;

// Not finished
//http://code.google.com/codejam/contest/2270488/dashboard#s=p2
public class FairAndSquareTS extends AbstractTaskSolution implements NoTestCasePreload {

	String min, max;
	BigInteger minB, maxB;
	long result;

	public static long weirdFact(int val, int ext) {
		long result = 1;
        for (int i = 1 + ext; i <= val + ext; i++)
            result *= (long)i;
        return result;
    }
	
	private static int[] dromes = {1, 4, 9, 121, 484};
	
	private long getRightSquaresCount(String min, String max) {
		return getRightSquaresCount(min, max, max.length());
	}
	
	
	private static char[] nextCombination(char[] chars) {
		int len = (chars.length + 1) / 2;
		int max1onSideNum = Math.min(3, (len - 2) / 2);
		for(int i = 0; i < max1onSideNum; i++)
			for(int j = 0; j < max1onSideNum; j++)
				for(int k = 0; k < max1onSideNum; k++) {
					
				}
		return chars;
	}
	
	private static String rep(String s, int cnt) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < cnt; i++)
			sb.append(s);
		return sb.toString();
	}
	
	private static List<String> getProperToSize(int length) {
		List<String> result = new LinkedList<String>();
		if (length % 2 == 2) {
			int len = (length + 1) / 2;
			String s = "2";
			for(int i = 0; i < len; i++)
				s = "1" + rep("0", (len - 2)/2);
		}
		
		return result;
	}
	
	private long getRightSquaresCount(String min, String max, int length) {
		long result;
		if (min.length() > 2) {
			return getRightSquaresCount("0", max) - 
					getRightSquaresCount("0", new BigInteger(min).subtract(BigInteger.ONE).toString());
		}
		if (max.length() == length) {
			result = 0;
			BigInteger maxB = new BigInteger(max);
			char[] c20002 = new char[length];
			for(int i = 0; i < length; i++)
				c20002[i] = '0';
			c20002[0] = c20002[length-1]='2';
			if (new BigInteger(new String(c20002)).compareTo(maxB) <= 0)
				result++;
			int len = (length + 1) / 2;
			if (len % 2 == 1) {
				c20002[(len / 2) + (len % 2)] = '1';
				if (new BigInteger(new String(c20002)).compareTo(maxB) <= 0)
					result++;
			}
			c20002[0] = c20002[length-1]='1';
			c20002[(len / 2) + (len % 2)] = '0';
			
			
		}
		
		int maxI, minI;
		boolean parse;
		if ((parse = (max.length() <=3 && Integer.parseInt(max) <= 999)) || length <=3) {
			result = 0;
			maxI = parse ? Integer.parseInt(max) : 999;
			minI = Integer.parseInt(min);
			for(int i = 0; i < dromes.length; i++)
				if (dromes[i] >= minI && dromes[i] <= maxI)
					result++;
			return result;
		}
		if (length < max.length()) {
			int len = (length + 1) / 2;
			result = 0;
			int max1onSideNum = Math.min(3, (len - 2) / 2);
			result += weirdFact(max1onSideNum, (len - 2) / 2 - max1onSideNum);
			result *= 2 * (len % 2); // for 1..1..1 	<- i.e. 1 or 0 in a center
			result += 1; //for 2000002
			result += len % 2; //2001002
			return result + getRightSquaresCount(min, max, length - 1);
		}

		
		return 0;
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = getRightSquaresCount(min, max);
	}

	@Override
	protected TestCase parseTestCaseInput() {
		min = getStr();
		max = min.split(" ")[1];
		min = min.split(" ")[0];
		minB = new BigInteger(min);
		maxB = new BigInteger(max);
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
