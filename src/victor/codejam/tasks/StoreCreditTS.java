package victor.codejam.tasks;

import java.util.Collections;
import java.util.LinkedList;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class StoreCreditTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	class Pair implements Comparable<Pair> {
		int value;
		int index;
		
		Pair(int v, int i) {
			value = v; index = i;
		}
		
		@Override
		public int compareTo(Pair o) {
			return Integer.valueOf(value).compareTo(o.value);
		}
		
	}
	
	int credit;
	LinkedList<Pair> items = new LinkedList<Pair>();
	int i1, i2;

	@Override
	protected void executeSolutionBody(TestCase testCase) {
		Collections.sort(items);
		Pair v1, v2;
		for(;;) {
			v1 = items.getFirst();
			v2 = items.getLast();
			if (v1.value + v2.value == credit) {
				break;
			} else if (v1.value + v2.value < credit) {
				items.removeFirst();
			} else {
				items.removeLast();
			}
		}
		i1 = (v1.index < v2.index) ? v1.index : v2.index;
		i2 = (v1.index > v2.index) ? v1.index : v2.index;
	}

	@Override
	protected TestCase parseTestCaseInput() {
		credit = getInt();
		items.clear();
		int a = 1;
		for(int i = getInt(); i > 0; i--) {
			items.add(new Pair(getInt(), a++));
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(i1);
		putIntNoNewLine(i2);
	}

}
