package victor.codejam.tasks;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class InfiniteHouseOfPancakesTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	int result, num;
	TreeSet<Integer> plates = new TreeSet<Integer>(Collections.reverseOrder());
	Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
	
	// log(base 2) x=log(base e) x/log(base e) 2
	public static double log2(double x) {
		int base = 2;
		return Math.log(x) / Math.log(base);
	}
	
/* 	bruteforcing	
 
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = plates.first();
		
		TreeSet<Integer> iPlates = new TreeSet<Integer>(Collections.reverseOrder());
		Map<Integer, Integer> iCounts = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < Math.pow(3, plates.size()); i++) {
			
			iCounts.clear();
			iCounts.putAll(counts);
			iPlates.clear();
			iPlates.addAll(plates);
			
			int locRes = 0;
			
			int j = i;
			for(int max : plates) {
				if (j % 3 > 0) {
					int k = 1 + (j % 3);
					int maxCnt = iCounts.get(max);
					iCounts.remove(max);
					iPlates.remove(max);
					
					locRes += maxCnt*(k-1);
					
					class Pair {
						int cntMul, val;
					}
					
					Pair[] sliced = new Pair[2];
					sliced[0] = new Pair();
					sliced[1] = new Pair();
					
					sliced[0].val = (max / k) + (max % k);
					sliced[0].cntMul = 1;
					
					sliced[1].val = (max / k);
					sliced[1].cntMul = k-1;
					
					
					
					for (Pair slice : sliced) {
						if (!iPlates.contains(slice.val))
							iPlates.add(slice.val);
						if (!iCounts.containsKey(slice.val))
							iCounts.put(slice.val, maxCnt * slice.cntMul);
						else iCounts.put(slice.val, iCounts.get(slice.val) + maxCnt * slice.cntMul);
					}
					
					
				}
				j /= 3;
			}
		
			result = Math.min(result, locRes + iPlates.first());
		}
		
	}
	
	*/
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		
		
		int max;
		while(!plates.isEmpty()) {
			max = plates.first();
			int maxCnt = counts.get(max);
			
			int oldWin = -1;
			int win = 0;
			int k;
			for (k = 2; win > oldWin ; k++) {
				
				oldWin = win;			
				
				win = max - ((max + k - 1) / k) - maxCnt*(k-1);
				

				
				outer:
				if (win > 0)
				for(int i : plates) {
					if (plates.first().equals(i))
						continue;
					
					
					for(int j = 0; j < counts.get(i); j++) {
						if (i < max - win)
							break outer;
						win --;
					}
				}
				

			
			}
			k -= 2;
			
			if (oldWin > 0) {
				
				plates.remove(max);
				counts.remove(max);
				
				class Pair {
					int cntMul, val;
				}
				
				Pair[] sliced = new Pair[2];
				sliced[0] = new Pair();
				sliced[1] = new Pair();
				
				sliced[0].val = (max / k) + (max % k);
				sliced[0].cntMul = 1;
				
				sliced[1].val = (max / k);
				sliced[1].cntMul = k-1;
				
				
				
				for (Pair slice : sliced) {
					if (!plates.contains(slice.val))
						plates.add(slice.val);
					if (!counts.containsKey(slice.val))
						counts.put(slice.val, maxCnt * slice.cntMul);
					else counts.put(slice.val, counts.get(slice.val) + maxCnt * slice.cntMul);
				}
				
				result += maxCnt*(k-1);
				
			} else {
				Map<Integer, Integer> newCounts = new HashMap<Integer, Integer>();
				
				for(Map.Entry<Integer, Integer> entry : counts.entrySet()) {
					if (entry.getKey() !=1)
						newCounts.put(entry.getKey()-1, entry.getValue());
				}
				counts = newCounts;
				
				TreeSet<Integer> newPlates = new TreeSet<Integer>(Collections.reverseOrder());
				for(int i : plates)
					if (i != 1)
						newPlates.add(i-1);
				plates = newPlates;
				
				result++;
			}
			
		}
		
	
		
	}

	@Override
	protected TestCase parseTestCaseInput() {
		plates.clear();
		counts.clear();
		for(int i = (num = getInt()); i > 0; i--) {
			int val = getInt();
			if (!counts.containsKey(val)) {
				counts.put(val, 1);
				plates.add(val);
			} else counts.put(val, counts.get(val) + 1);
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
