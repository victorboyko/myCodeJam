package victor.codejam.tasks;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import victor.codejam.AbstractTaskSolution;

//https://code.google.com/codejam/contest/dashboard?c=619102#s=p0
public class RopeIntranetTaskSolution extends AbstractTaskSolution {
	
	private class LeftRight {
		int left, right;
		@Override
		public String toString() {
			return "C" + left + ":" + right + ")";
		}
	}
	
	protected class TestCaseImp implements TestCase {
		List<LeftRight> wires = new LinkedList<RopeIntranetTaskSolution.LeftRight>();
		
		int result=0;
		
	}
	
	
	
	@Override
	protected TestCase parseTestCaseInput() {
		TestCaseImp testCase = new TestCaseImp();
		
		int wiresNum = getInt();
		for (int i=0; i< wiresNum; i++) {
			LeftRight wire = new LeftRight();
			wire.left = getInt();
			wire.right = getInt();
			testCase.wires.add(wire);
		}
		
		return testCase;
	}
	

	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		TestCaseImp tc = (TestCaseImp)testCase;
		
		Comparator<Integer> descComparator = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2-o1; //DESC order
			};
		};
		
		TreeMap<Integer, Integer> leftMap = new TreeMap<Integer, Integer>(descComparator);
		TreeMap<Integer, Integer> rightMap = new TreeMap<Integer, Integer>(descComparator);
		
		for(LeftRight wire: tc.wires) {
			leftMap.put(wire.left, wire.right);
			rightMap.put(wire.right, wire.left);
		}
		
		
		while (!rightMap.isEmpty()) {
			LeftRight rightWire;
			{
				Entry<Integer, Integer> rightFirstEntry = rightMap.pollFirstEntry();
				rightWire = new LeftRight();
				rightWire.left = rightFirstEntry.getValue();
				rightWire.right = rightFirstEntry.getKey();
			}
			
			for (Integer left: leftMap.keySet()) {
				if (left == rightWire.left)
					break;
				tc.result++;
			}
			leftMap.remove(rightWire.left);
		}
		
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(((TestCaseImp)testCase).result);
	}

}
