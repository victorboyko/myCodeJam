package victor.codejam.tasks;

import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class GettingTheDigitsTS extends AbstractTaskSolution implements NoTestCasePreload {

	Map<Character, Integer> all = new HashMap<Character, Integer>();
	
	int[] counts;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		counts = new int[10];
		
		counts[0] = all.containsKey('Z') ? all.get('Z') : 0;
		if (counts[0] > 0)
		for(Character c : "ZERO".toCharArray()) {
			all.put(c, all.get(c) - counts[0]);
		}
		
		counts[6] = all.containsKey('X') ? all.get('X') : 0;
		if (counts[6] > 0)
		for(Character c : "SIX".toCharArray()) {
			all.put(c, all.get(c) - counts[6]);
		}
		
		counts[8] = all.containsKey('G') ? all.get('G') : 0;
		if (counts[8] > 0)
		for(Character c : "EIGHT".toCharArray()) {
			all.put(c, all.get(c) - counts[8]);
		}
		
		counts[4] = all.containsKey('U') ? all.get('U') : 0;
		if (counts[4] > 0)
		for(Character c : "FOUR".toCharArray()) {
			all.put(c, all.get(c) - counts[4]);
		}
		
		counts[2] = all.containsKey('W') ? all.get('W') : 0;
		if (counts[2] > 0)
		for(Character c : "TWO".toCharArray()) {
			all.put(c, all.get(c) - counts[2]);
		}
		
		// non unique further
		
		counts[1] = all.containsKey('O') ? all.get('O') : 0;
		if (counts[1] > 0)
		for(Character c : "ONE".toCharArray()) {
			all.put(c, all.get(c) - counts[1]);
		}
		
		counts[3] = all.containsKey('R') ? all.get('R') : 0;
		if (counts[3] > 0)
		for(Character c : "THREE".toCharArray()) {
			all.put(c, all.get(c) - counts[3]);
		}
		
		counts[5] = all.containsKey('F') ? all.get('F') : 0;
		if (counts[5] > 0)
		for(Character c : "FIVE".toCharArray()) {
			all.put(c, all.get(c) - counts[5]);
		}
		
		counts[7] = all.containsKey('V') ? all.get('V') : 0;
		if (counts[7] > 0)
		for(Character c : "SEVEN".toCharArray()) {
			all.put(c, all.get(c) - counts[7]);
		}
		
		counts[9] = all.containsKey('I') ? all.get('I') : 0;
		if (counts[9] > 0)
		for(Character c : "NINE".toCharArray()) {
			all.put(c, all.get(c) - counts[9]);
		}
		
		for(Character c : all.keySet()) {
			if (all.containsKey(c) && all.get(c) != 0)
				throw new RuntimeException("count for " + c + " is " + all.get(c));
		}
		
	}
	
	@Override
	protected TestCase parseTestCaseInput() {
		// TODO Auto-generated method stub
		String in = getStr();
		for(Character c : in.toCharArray()) {
			if (!all.containsKey(c)) {
				all.put(c, 1);
			} else {
				all.put(c, all.get(c) + 1);
			}
		}
		
		return null;
	}
	
	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		StringBuilder res = new StringBuilder();
		for(int i = 0; i <= 9; i++) {
			for(int j = 0; j < counts[i]; j++) {
				res.append("" + i);
			}
		}
		putStrNoNewLine(res.toString());
		
	}
	
}
