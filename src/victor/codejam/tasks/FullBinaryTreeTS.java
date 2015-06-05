package victor.codejam.tasks;
// TODO INCOMPLETE

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class FullBinaryTreeTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	int n;
	ArrayList<Set<Integer>> in;
	int result;
	
	class Point { 
		int x, y; 
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "[" + x + " ," + y + "]";
		}
	}
	
	boolean isBinary() {
		int n2s = 0;
		for(int i = 0; i < n; i++) {
			int n = in.get(i).size();
			if (n > 3) return false;
				if (n == 2) n2s ++;
		}
		return n2s == 1;
	}
	
	Point getPathToAbnorm(int a, int from) {
		Set<Integer> vals = in.get(a);
		Point min = new Point(this.n + 1, 0);
		if (vals.size() == 1) return min;
		if (vals.size() != 3) return new Point(0, a);
		
		for(int val : vals) {
			if (val == from) continue;
			Point c = getPathToAbnorm(val, a);
			if (c.x < min.x) {
				min = c;
				min.x ++;
			}
		}
		return min;
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;

		while (!isBinary()) {
			
			Point min = new Point(this.n + 1, 0);
			int minA = 0;
			int minB = 0;
			
			for(int i = 0; i < n; i++) {
				Set<Integer> vals = in.get(i);
				if (vals.size() == 1) {
					Point c = getPathToAbnorm(vals.iterator().next(), i);
					if (c.x < min.x) {
						min = c;
						minA = i;
						minB = vals.iterator().next();
					}
				}
			}
			result ++;
			in.get(minA).remove(minB);
			in.get(minB).remove(minA);
		}
		
		
	}

	static int testCaseNum;
	@Override
	protected TestCase parseTestCaseInput() {
		System.out.println("" + ++testCaseNum);
		n = getInt();
		in = new ArrayList<Set<Integer>>(n);
		for(int i = 0; i < n; i++) {
			in.add(new HashSet<Integer>());
		}
		for(int i = 0; i < n-1; i++) {
			int a = getInt()-1;
			int b = getInt()-1;
			in.get(a).add(b);
			in.get(b).add(a);
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
