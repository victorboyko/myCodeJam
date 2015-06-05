package victor.codejam.tasks;

import java.util.Collections;
import java.util.LinkedList;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//TODO - didn't solve it
//http://code.google.com/codejam/contest/2075486/dashboard#s=p1&a=0
public class UpstairsDownstairsTaskSolution extends AbstractTaskSolution
		implements NoTestCasePreload {
	
	private int nActivities, actRequired;
	private LinkedList<Activity> acts;
	private double result;

	static class Activity implements Comparable<Activity>{
		double p;
		int count;
		
		@Override
		public int compareTo(Activity o) {
			return p - o.p > 0 ? 1 : (p == o.p) ? 0 : -1;
		}
		
		@Override
		public String toString() {
			return "[" + p + ": " + count + "]";
		}
		
		@Override
		protected Activity clone() throws CloneNotSupportedException {
			Activity a = new Activity();
			a.count = count;
			a.p = p;
			return a;
		}
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		Collections.sort(acts);
		double awake = 1d;
		double notGetAwaken = 1d;
		int counter = 0;
//		while (counter++ < actRequired) {
//			
//			Activity a;
//			if (counter < actRequired && acts.size() > 2) {
//				double first = (1 - acts.getFirst().p * (1d - awake)) * (1 - acts.get(1).p * (1d - acts.getFirst().p));
//				double last  = (1 - acts.getLast().p * (1d - awake))  * (1 - acts.getFirst().p * (1d - acts.getLast().p));
//				a = (first > last) ? acts.getFirst() : acts.getLast();
//			} else {
//				a = acts.getFirst();
//			}
//
//			if (--a.count == 0)
//				acts.remove(a);
//			double wakeUp = (1d - awake) * a.p;
//			notGetAwaken *= (1d - wakeUp);
//			awake = a.p;
//		}
		result = 0d;
		for(int i = 0; i < actRequired + 2; i++) {
			awake = 1d;
			notGetAwaken = 1d;
			counter = 0;
			LinkedList<Activity> actsCopy = new LinkedList<Activity>();
			for(Activity a : acts)
				try {
					actsCopy.add(a.clone());
				} catch (CloneNotSupportedException e) {
					throw new RuntimeException(e);
				}
			while (counter++ < actRequired) {
				Activity a = counter-1 < i ? actsCopy.getLast() : actsCopy.get(i-counter+1);
				if (--a.count == 0)
					actsCopy.remove(a);
				double wakeUp = (1d - awake) * a.p;
				notGetAwaken *= (1d - wakeUp);
				awake = a.p;	
			}
			if (result < notGetAwaken)
				result = notGetAwaken;
		}
		result = 1d - result;
	}

	@Override
	protected TestCase parseTestCaseInput() {
		acts = new LinkedList<Activity>();
		nActivities = getInt();
		actRequired = getInt();
		for(int i = 0; i < nActivities; i++) {
			String str = getStr();
			double p = Double.parseDouble(str.split("/")[0]) / Double.parseDouble(str.split("/")[1].split(" ")[0]);
			int c = Integer.parseInt(str.split("/")[1].split(" ")[1]);
			Activity a = new Activity();
			a.count = c;
			a.p = p;
			acts.add(a);
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putDblNoNewLine(result, 7);
	}

}
