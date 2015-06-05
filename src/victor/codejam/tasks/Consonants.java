package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//http://code.google.com/codejam/contest/2437488/dashboard#s=p0
public class Consonants extends AbstractTaskSolution implements NoTestCasePreload {
	
	String in;
	int n, result;
	static Set<Character> vowels;
	static {
		vowels = new HashSet<Character>();
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
	}
	
	private static boolean onlyCons(String s) {
		for(char c : s.toCharArray())
			if (vowels.contains(c))
				return false;
		return true;
	}
	
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		List<Integer> inds = new ArrayList<Integer>();
		for(int i = 0; i <= in.length() - n; i++) {
			String s = in.substring(i, i + n);
			if (onlyCons(s))
				inds.add(i);
		}
		
		
		for(int j = 0; j < inds.size(); j++) {
			int i = inds.get(j);
			int l2 = j < inds.size() - 1 ? inds.get(j+1) - i : in.length() - i - n + 1;
			int l1 = i + 1;
			result += l1 * l2;
		}
	}

	@Override
	protected TestCase parseTestCaseInput() {
		in = getStr();
		n = Integer.parseInt(in.split(" ")[1]);
		in = in.split(" ")[0];
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
