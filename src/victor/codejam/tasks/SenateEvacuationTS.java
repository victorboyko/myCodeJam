package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class SenateEvacuationTS extends AbstractTaskSolution implements NoTestCasePreload {

	int N;
	List<Pair> nums;
	StringBuilder result;
	
	class Pair implements Comparable<Pair> {
		int val;
		char ind;
		Pair(char ind, int val) {
			this.ind = ind;
			this.val = val;
		}
		
		@Override
		public int compareTo(Pair o) {
			//return this.ind - o.ind;
			return o.val - this.val; // sort descending
		}
		
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = new StringBuilder();
		int sum = getSum(nums);

		
		while(sum > 0) {
			Collections.sort(nums);
			result.append((char)('A' + nums.get(0).ind)); sum--; nums.get(0).val = nums.get(0).val - 1;
			
			if (nums.size() > 2 && nums.get(2).val > nums.get(0).val) {
				result.append(' ');
				continue;
			}
			
			
			if (sum == 0) return;
			if (nums.get(1).val > ( nums.get(0).val )) {
				result.append((char)('A' + nums.get(1).ind)); sum--; nums.get(1).val = nums.get(1).val - 1;
			} else {
				result.append((char)('A' + nums.get(0).ind)); sum--; nums.get(0).val = nums.get(0).val - 1;
			}
			
			if (sum > 0) {
				result.append(' ');
			}
		}
		
	}
	
	static int getSum(List<Pair> nums) {
		int res = 0;
		for(Pair p : nums) {
			res += p.val;
		}
		return res;
	}

	@Override
	protected TestCase parseTestCaseInput() {
		N = getInt();
		nums = new ArrayList<Pair>(N);
		for(char i = 0; i < N; i++) {
			nums.add(new Pair(i, getInt()));
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(result.toString());
	}

}
