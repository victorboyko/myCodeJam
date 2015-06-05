//https://code.google.com/codejam/contest/dashboard?c=3014486#s=p0
package victor.codejam.tasks;

import java.util.Collections;
import java.util.LinkedList;
import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class DataPackingTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	int N, MAX_SIZE, result;
	LinkedList<Integer> files = new LinkedList<Integer>();

	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		Collections.sort(files);
		
		while(!files.isEmpty()) {
			result ++;
			int biggest = files.pollLast();
			int left = MAX_SIZE - biggest;
			
			if (files.isEmpty())
				break;
			
			int nMin = 0;
			int nMax = files.size() - 1;
			
			int index;
			while(true) {
				index = nMin + (nMax - nMin) / 2;
				int file = files.get(index);
				if (file == left) {
					files.remove(index);
					break;
				}
				if (nMax == nMin) {
					if (file > left) {
						if (index != 0)
							files.remove(index - 1);
					} else {
						files.remove(index);
					}
					break;
				}
				
				if (file > left) {
					nMax = index - 1;
					if (nMax < nMin)
						nMax = nMin;
				} else {
					nMin = index + 1;
					if (nMin > nMax)
						nMin = nMax;
				}
				
			}

		}

	}

	static int testCaseNum;
	@Override
	protected TestCase parseTestCaseInput() {
		System.out.print("Reading test case #" + ++testCaseNum + " .. ");
		files.clear();
		N = getInt();
		MAX_SIZE = getInt();
		for (int i = 0; i < N; i++)
			files.add(getInt());
		System.out.print(" read .. ");
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
		System.out.println(" solved!");
	}

}
