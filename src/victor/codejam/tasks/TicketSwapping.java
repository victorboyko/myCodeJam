package victor.codejam.tasks;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class TicketSwapping extends AbstractTaskSolution implements
		NoTestCasePreload {

	int nMax;
	BigDecimal sum1toNmax;
	static final long MOD = 1000002013;
	long result;
	
	class Point {
		public int x, y, num;
		
		public Point(Point p) {
			this(p.x, p.y, p.num);
		}
		
		public Point(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
		
		@Override
		public String toString() {
			return "(" + x + ", " + y + ")[" + num + "]";
		}
		
		@Override
		public boolean equals(Object o) {
			Point p = (Point)o;
			return x == p.x && y == p.y && num == p.num;
		}
	}
	
	PriorityQueue<Point> pairsIn, pairsOut;
	
	
	private static BigDecimal getSum1toN(long n) {
		return BigDecimal.valueOf(n).multiply(BigDecimal.valueOf(n).add(BigDecimal.ONE)).divide(BigDecimal.valueOf(2l));
		//return n*(n+1)/2l;
	}
	
	private BigDecimal getSumForN(long l) {
		return sum1toNmax.subtract(getSum1toN(nMax - l));
	}
	
	private BigDecimal getSumAtoB(long a, long b) {
		return getSumForN(Math.abs(b - a));
	}
	
	private BigDecimal getSum(Point p) {
		return getSumAtoB(p.x, p.y);
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		sum1toNmax = getSum1toN(nMax);
		result = 0;
		
		BigDecimal res = BigDecimal.ZERO;
		
		LinkedList<Point> tickets = new LinkedList<TicketSwapping.Point>();
		while(!pairsOut.isEmpty()) {
			
			int tktIndex = (pairsIn.isEmpty()) ? nMax + 1 : pairsIn.peek().x;
			int psngIndex = pairsOut.peek().y;
			if (tktIndex <= psngIndex) {
				tickets.add(pairsIn.poll());
			} else {
				Point psng = pairsOut.poll();
				while(psng.num > 0) {
					Point tkt = tickets.peekLast();
					long num = Math.min(psng.num, tkt.num);
					psng.num -= num;
					tkt.num -= num;
					if (tkt.num <= 0)
						tickets.removeLast();
					//res = res.add(new BigDecimal(num * ( getSum(psng) - getSum(new Point(tkt.x, psng.y, 0)) )));
					res = res.add(BigDecimal.valueOf(num).multiply( getSum(psng).subtract(getSum(new Point(tkt.x, psng.y, 0))) ));
				}
			}
		}

		result = res.remainder(new BigDecimal(MOD)).longValue();
	}

	@Override
	protected TestCase parseTestCaseInput() {
		nMax = getInt();
		int num = getInt();
		pairsIn = new PriorityQueue<Point>(num, new Comparator<Point>() {
			
			@Override
			public int compare(Point o1, Point o2) {
				return o1.x - o2.x;
			}
			
		});
		pairsOut = new PriorityQueue<Point>(num, new Comparator<Point>() {
			
			@Override
			public int compare(Point o1, Point o2) {
				return o1.y - o2.y;
			}
			
		});
		for(int i = 0; i < num; i++) {
			Point p = new Point(getInt(), getInt(), getInt());
			pairsIn.add(p);
		}
		Iterator<Point> it = pairsIn.iterator();
		Point p1 = it.next();
		while(it.hasNext()) {
			Point p2 = it.next();
			if (p1.x == p2.x && p1.y == p2.y) {
				p1.num += p2.num;
				it.remove();
			} else p2 = p1;
		}
		for(Point p : pairsIn)
			pairsOut.add(new Point(p));
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
