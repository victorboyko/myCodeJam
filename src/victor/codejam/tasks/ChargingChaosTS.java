package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class ChargingChaosTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	int len, num;
	List<Long> in1, in2;
	Integer result;
	
	
	Integer checkIfPossible(Set<Integer>[] allPossible) {
	
		long bit = 1;
		int changesL = 0;
		for(int i = 0; i < len; i++) {
			//in1 = in1c;
			//in2 = in2c;
			int zeros1 = 0;
			int zeros2 = 0;
			for(int j = 0; i < num; j++) {
				zeros1 += ((in1.get(j) & bit) == 0) ? 1 : 0;
				zeros2 += ((in1.get(j) & bit) == 0) ? 1 : 0;
			}
			if (zeros1 == zeros2) {
				
				if (zeros1 == num - zeros2) {
					//TODO
					continue;
				}
				
				for(Integer k : allPossible[i]) {
					if ((in1.get(k) ^ bit) == 0) {
						
					}
				}
				
				continue;
			} 
			if (zeros1 == num - zeros2) {
				changesL++;
				continue;
			}
			
			bit *= 2;
		}
		
		return changesL;
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		@SuppressWarnings("unchecked")
		Set<Integer>[] allPossible = new Set[num];
		for(int i = 0; i < len; i++) {
			allPossible[i] = new HashSet<Integer>();
			for(int j = 0; i < num; j++)
				allPossible[i].add(j);
		}
		
		result = checkIfPossible(allPossible);

	}

	@Override
	protected TestCase parseTestCaseInput() {
		num = getInt();
		len = getInt();
		in1 = new ArrayList<Long>(num);
		in2 = new ArrayList<Long>(num);
		
		for(int i = 0; i < num; i++) {
			in1.add(Long.parseLong(getStr(), 2));
		}
		for(int i = 0; i < num; i++) {
			in2.add(Long.parseLong(getStr(), 2));
		}
		
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		if (result == null)
			putStrNoNewLine("");
		else putIntNoNewLine(result);

	}

}
