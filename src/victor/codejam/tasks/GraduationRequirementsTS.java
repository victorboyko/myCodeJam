package victor.codejam.tasks;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

// TODO - not done
// http://code.google.com/codejam/contest/2437491/dashboard
public class GraduationRequirementsTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	long carsNum, maxTime, exitsNum, result;
	List<Point> points;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		final LinkedList<Point> xSorted = new LinkedList<Point>(points);	
		final LinkedList<Point> ySorted = new LinkedList<Point>(points);
		final Comparator<Point> xComparator = new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return (int)(o1.x - o2.x);
			}
		};	
		final Comparator<Point> yComparator = new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return (int)(o1.y - o2.y);
			}
		};
		Collections.sort(xSorted, xComparator);
		Collections.sort(ySorted, yComparator);
		
		final Comparator<Point> xyComparator = new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x == o2.x)
					return (int)(o2.y - o1.y);
				return (int)(o1.x - o2.x);		
			}
		};
		
		result = 0;
		long begin = 0;
		
		while (!xSorted.isEmpty() && !ySorted.isEmpty()) {
			Point p;
			if (xSorted.isEmpty()) {
				p = ySorted.poll();
			} else if (ySorted.isEmpty()) {
				p = xSorted.poll();
			} else {
				p = (xyComparator.compare(xSorted.peek(), ySorted.peek()) > 0) ? xSorted.poll() : ySorted.poll();
			}
			
			result = Math.max(result, p.x - begin);
			begin = Math.max(begin, p.y);
			
		}

	}

	@Override
	protected TestCase parseTestCaseInput() {
		carsNum = getLong();
		maxTime = getLong();
		exitsNum = getLong();
		points = new LinkedList<Point>();
		for(long i = 0; i < carsNum; i++) {
			long x = getLong() - 1;
			long y = getLong() - 1;
			long t = getLong();
			if (y < x)
				y += exitsNum;
			points.add(new Point(x + t, y + t));
		}
		points.add(new Point(maxTime + exitsNum - 1, maxTime + exitsNum));
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

	private class Point {
		public final long x,y;
		
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
		
	}
}
