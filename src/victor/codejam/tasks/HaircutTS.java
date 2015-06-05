package victor.codejam.tasks;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

// https://code.google.com/codejam/contest/4224486/dashboard#s=p1

public class HaircutTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	int nBarbers, place, result;
	
	List<Integer> m = new ArrayList<Integer>();
	List<Double> speeds = new ArrayList<Double>();
	
	class Pair implements Comparable<Pair>{
		double val; int index;
		Pair(double val, int index) {
			this.val = val;
			this.index = index;
		}
		
		@Override
		public int hashCode() {
			return (int)val + index * 37;
		}
		
		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair)obj;
			return p.val == val && p.index == index;
		}
		
		public int compareTo(Pair o) {
			if (Double.valueOf(val).equals(o.val))
				return Integer.valueOf(index).compareTo(o.index);
			return Double.valueOf(val).compareTo(o.val);
		};
		
		@Override
		public String toString() {
			return String.format("(%.7f, %d)", new Object[] {val, index});
		}
	}

	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
	


		
		BigDecimal allTimes = BigDecimal.ONE;
		for(int i = 0; i < nBarbers; i++) {
			allTimes = allTimes.multiply(BigDecimal.valueOf(m.get(i)));
		}
		BigDecimal allTimesCnt = BigDecimal.ZERO;
		for(int i = 0; i < nBarbers; i++) {
			allTimesCnt = allTimesCnt.add(allTimes.divide(BigDecimal.valueOf(m.get(i))));
		}
		
		int allTimesCntInt = allTimesCnt.intValue();
		
		if (BigDecimal.valueOf(allTimesCntInt).equals(allTimesCnt)) {
			place %= allTimesCntInt;
			if (place == 0)
				place += allTimesCntInt;
		}
		
		result = 1;
		
		// Brute force solution
//		
//		TreeSet<Pair> pairs = new TreeSet<HaircutTS.Pair>();
//		
//		for(int i = 0; i < nBarbers; i++) {
//			pairs.add(new Pair(0d, i));
//		}
//			
//		for(int i = 0; i < place; i++) {
//			pairs.first().val += m.get((result = pairs.first().index + 1) -1);
//			pairs.add(pairs.pollFirst());
//		}
		
		speeds.clear();
		
		if (place <= nBarbers) {
			result = place; return;
		}
		
		if (nBarbers == 1) {
			result = 1; return;
		}

		boolean allHaveSameSpeed = true;;
		for(int i = 1; i < nBarbers && allHaveSameSpeed; i++) {
			allHaveSameSpeed &= m.get(i-1).equals(m.get(i));
		}
		if (allHaveSameSpeed) {
			result = 1 + (--place % nBarbers); return;
		}
		
		double sumSpeed = 0;
		for(int i = 0; i < nBarbers; i++) {
			double speed;
			speeds.add(speed = (1d / m.get(i)));
			sumSpeed += speed;
		}
		
		
		
		//double time = (place-nBarbers) / sumSpeed;
		BigDecimal bTime = BigDecimal.valueOf(place)./*subtract(BigDecimal.valueOf(nBarbers)).*/divide(BigDecimal.valueOf(sumSpeed), 1000, RoundingMode.HALF_UP);
		
		TreeSet<Pair> pairs = new TreeSet<HaircutTS.Pair>();
		
		//place -= nBarbers;
		for(int i = 0; i < nBarbers; i++) {
			//double offset = time - m.get(i) * Math.ceil(time / m.get(i));
			BigDecimal bOffset = bTime.subtract(bTime.divide(BigDecimal.valueOf(m.get(i)), 0, RoundingMode.CEILING).multiply(BigDecimal.valueOf(m.get(i))));
			
			//place -= Math.floor(time / m.get(i));
			place -= bTime.divide(BigDecimal.valueOf(m.get(i)), 0,  RoundingMode.FLOOR).intValue();
			
			pairs.add(new Pair(bOffset.doubleValue(), i));
		}
		
		if (place == 0) {
			place = nBarbers;
			for (Pair p : pairs)
				p.val -= m.get(p.index);
		}
		TreeSet<HaircutTS.Pair> cpy = pairs;
		pairs = new TreeSet<HaircutTS.Pair>(); // assure sorting
		for (Pair p : cpy)
			pairs.add(p);
		
		
		Set<Pair> tmp = new TreeSet<HaircutTS.Pair>();
		for( ;place > 0 ; ) {

//			int sameCnt = 0;
//			double ethalon = pairs.first().val;
//			for(Pair p : pairs)
//				if (p.val == ethalon)
//					sameCnt++;
//				else break;
			
			tmp.clear();
			tmp.addAll(pairs);
			pairs.clear();
			
			
//			{
//				Iterator<Pair> it = pairs.iterator();
//				for(int i = 0; i < sameCnt; i++) {
//					Pair p = it.next();
//					it.remove();
//					tmp.add(p);
//				}
//			}
			
//			if (sameCnt < place) {
//				for(Pair p : tmp)
//					p.val += m.get(p.index);
//				pairs.addAll(tmp);
//				place -= sameCnt;
//				continue;
//			} else 
			{
				Iterator<Pair> it = tmp.iterator();
				Pair p = it.next();
				Pair targetPair = p;
				if (p.val + m.get(p.index) > 0)
				while(it.hasNext()) {
					p = it.next();
					if (p.val > 0)
						break;
					if (p.val + m.get(p.index) <= 0) {
						targetPair = p;
						break;
					}
					
					if (m.get(p.index) + p.val > m.get(targetPair.index) + targetPair.val || (m.get(p.index) + p.val == m.get(targetPair.index) + targetPair.val && p.index < targetPair.index)) {
						targetPair = p;
					}
				}
				
				targetPair.val += m.get((result = targetPair.index + 1) -1);
				for (Pair p1 : tmp)
					pairs.add(p1);
				place--;
			}
			
			
			
		}

		
	}
	

	static Set<Integer> getIndexes(List<Pair> pairs) {
		Set<Integer> res = new HashSet<Integer>();
		for(Pair p : pairs)
			res.add(p.index);
		return res;
	}
	
	Pair[] getOffPairs(int place, double sumSpeed) {
		double time = (place-nBarbers) / sumSpeed;
		
		Pair[] offPairs = new Pair[nBarbers];
		double[] offsets = new double[nBarbers];
		
		for(int i = 0; i < nBarbers; i++) {
			offsets[i] = m.get(i) * Math.ceil(time / m.get(i)) - time;
			offPairs[i] = new Pair(offsets[i], i);
		}

		Arrays.sort(offPairs);
		return offPairs;
	}

	int tstCnt = 0;
	@Override
	protected TestCase parseTestCaseInput() {
		System.out.printf("Test %d is running ..\n", ++tstCnt);
		nBarbers = getInt();
		place = getInt();
		m.clear();
		for(int i = 0; i < nBarbers; i++)
			m.add(getInt());
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
