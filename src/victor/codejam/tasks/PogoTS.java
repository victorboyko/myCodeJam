package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.management.RuntimeErrorException;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class PogoTS extends AbstractTaskSolution implements NoTestCasePreload {

	int x, y;
	String result;
	
	private List<Character> getWay(int x, int y, int step) {
		List<Character> result = new LinkedList<Character>();
		if (x == 0 && y == 0)
			return result;
		
		boolean xFirst = Math.abs(x) > Math.abs(y);
		
		
		return result;
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		List<Character> way = new ArrayList<Character>();
		
		int step = 0;
		while ((x != 0 || y != 0) /*&& step <= 501*/) {
			step++;
			if (x!= 0) {
				if (Math.abs(x) > step/2d) {
					if (x > 0)
						{ x -= step; way.add('E'); continue; }
					{ x += step; way.add('W'); continue; }
				}
				if (x > 0)
					{ x += step; way.add('W'); continue; }
				{ x -= step; way.add('E'); continue; }
			}
				
			if (Math.abs(y) > step/2d) {
				if (y > 0)
					{ y -= step; way.add('N'); continue; }
				{ y += step; way.add('S'); continue; }
			}
			if (y > 0)
				{ y += step; way.add('S'); continue; }
			{ y -= step; way.add('N'); continue; }

		}
		
//		if (step > 501)
//			throw new IllegalStateException("step > 501");
		
		
		char[] res = new char[way.size()];
		for(int i = 0; i < res.length; i++)
			res[i] = way.get(i);
		result = new String(res);
	}

	@Override
	protected TestCase parseTestCaseInput() {
		x = getInt();
		y = getInt();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(result);
	}

}
