package victor.codejam.tasks;

import java.util.Collections;
import java.util.LinkedList;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class DeceitfulWarTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	int len, unfairRes, fairRes;
	LinkedList<Double> naomiBlocks, kenBlocks;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		Collections.sort(naomiBlocks);
		Collections.sort(kenBlocks);
		
		LinkedList<Double> naomiBlocksCpy = new LinkedList<Double>(naomiBlocks);
		LinkedList<Double> kenBlocksCpy = new LinkedList<Double>(kenBlocks);
	
		
		unfairRes = 0;
		for(int i = 0; i < len; i++) {
			double nao = naomiBlocks.pollFirst();
			double ken = kenBlocks.peekFirst();
			if (nao > ken) {
				unfairRes ++;
				kenBlocks.removeFirst();
			} else {
				kenBlocks.removeLast();
			}
		}
		
		naomiBlocks = naomiBlocksCpy;
		kenBlocks = kenBlocksCpy;
		
		Collections.reverse(naomiBlocksCpy); // sorry, I will not change the image in my mind to save some CPU resource here
		Collections.reverse(kenBlocksCpy);
		
		fairRes = 0;
		for(int i = 0; i < len; i++) {
			double ken = kenBlocks.peekFirst();
			double nao = naomiBlocks.pollFirst();
			if (ken > nao) {
				kenBlocks.removeFirst();
			} else {
				fairRes ++;
				kenBlocks.removeLast();
			}
		}

	}

	@Override
	protected TestCase parseTestCaseInput() {
		len = getInt();
		naomiBlocks = new LinkedList<Double>();
		kenBlocks = new LinkedList<Double>();
		for(int i = 0; i < len; i++) {
			naomiBlocks.add(getDouble());
		}
		for(int i = 0; i < len; i++) {
			kenBlocks.add(getDouble());
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putInASameLine(new Integer[] {unfairRes, fairRes} );
	}

}
