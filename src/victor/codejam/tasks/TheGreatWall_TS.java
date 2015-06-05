package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//TODO incomplete

//http://code.google.com/codejam/contest/2437488/dashboard#s=p2
public class TheGreatWall_TS extends AbstractTaskSolution implements NoTestCasePreload {

	int nTribes, result;
	
	class Tribe implements Comparable<Tribe> {
		int d;
		int nAttacks;
		int w, e;
		int s;
		int delta_d, delta_p, delta_s;
		
		@Override
		public int compareTo(Tribe o) {
			return d - o.d;
		}
	}
	
	Set<Tribe> tribes;
	
	class Pair implements Comparable<Pair> {
		int a, b;
		
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair)obj;
			return a == p.a && b == p.b;
		}
		
		@Override
		public int hashCode() {
			return a + b * 65239;
		}
		
		@Override
		public int compareTo(Pair o) {
			return a - o.a;
		}
	}
	
	private void optimize(Map<Pair, Integer> wall) {
		List<Pair> wallKeys = new ArrayList<Pair>(wall.keySet());
		for(int i = 0; i < wallKeys.size() - 1; i++) {
			Pair key1 = wallKeys.get(i);
			Pair key2 = wallKeys.get(i+1);
			int val1 = wall.get(key1);
			int val2 = wall.get(key2);
			if (val1 == val2 && (key1.b >= key2.a || key2.b >= key1.a) ) {
				Pair p = new Pair(Math.min(key1.a, key2.a), Math.max(key1.b, key2.b));
				wall.remove(key1);
				wall.remove(key2);
				wall.put(p, val1);
				i--;
				continue;
			}
		}
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		Map<Pair, Integer> wall = new TreeMap<Pair, Integer>();
		
		while(!tribes.isEmpty()) {
			
			int day = tribes.iterator().next().d;
			Iterator<Tribe> it = tribes.iterator();
			Tribe t;
			while((t = it.next()).d == day) {
				List<Pair> wallKeys = new ArrayList(wall.keySet());
				int ind = Collections.binarySearch(wallKeys, new Pair(t.d, 0));
//				for(int i = ind + 1; i < wall.size() && wall.get(i).a <=; i++) {
//					
//				}
			}
			
		}
		
		// TODO Auto-generated method stub

	}

	@Override
	protected TestCase parseTestCaseInput() {
		nTribes = getInt();
		tribes = new HashSet<Tribe>(nTribes);
		for(int i = 0; i < nTribes; i++) {
			Tribe t = new Tribe();
			t.d = getInt();
			t.nAttacks = getInt();
			t.w = getInt();
			t.e = getInt();
			t.s = getInt();
			t.delta_d = getInt();
			t.delta_p = getInt();
			t.delta_s = getInt();
			
			tribes.add(t);
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
