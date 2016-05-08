package victor.codejam.tasks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class FashionPoliceTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	int J, P, S, 	K;
	
	class Pair {
		int x, y;
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int hashCode() {
			return x + 13*y;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == this) return true;
			if (!(obj instanceof Pair)) return false;
			Pair p = (Pair)obj;
			return p.x == this.x && p.y == this.y;
		}
	}
	
	StringBuilder sb;
	long total;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		Map<Pair, Integer> JPm = new HashMap<FashionPoliceTS.Pair, Integer>();
		Map<Pair, Integer> PSm = new HashMap<FashionPoliceTS.Pair, Integer>();
		Map<Pair, Integer> JSm = new HashMap<FashionPoliceTS.Pair, Integer>();
		
		total = 0;
		sb = new StringBuilder(); 
		for(int j = 0; j < J; j++) {
			for(int p = 0; p < P; p++) {
				for(int s = 0; s < S; s++) {
					boolean added = true;
					Pair p1;
					added &= !JPm.containsKey(p1 = new Pair(j, p)) || JPm.get(p1) < K;
					added &= !PSm.containsKey(p1 = new Pair(p, s)) || PSm.get(p1) < K;
					added &= !JSm.containsKey(p1 = new Pair(j, s)) || JSm.get(p1) < K;	
					if (added) {
						sb.append("\n");
						sb.append(j+1).append(' ').append(p+1).append(' ').append(s+1);
						total++;
						if (JPm.containsKey(p1 = new Pair(j, p))) {
							JPm.put(p1, JPm.get(p1) + 1);
						} else {
							JPm.put(p1, 1);
						}

						if (PSm.containsKey(p1 = new Pair(p, s))) {
							PSm.put(p1, PSm.get(p1) + 1);
						} else {
							PSm.put(p1, 1);
						}
						
						if (JSm.containsKey(p1 = new Pair(j, s))) {
							JSm.put(p1, JSm.get(p1) + 1);
						} else {
							JSm.put(p1, 1);
						}
						
						
					}
				}
			}
		}
		
	}

	@Override
	protected TestCase parseTestCaseInput() {
		J = getInt();
		P = getInt();
		S = getInt();
		K = getInt();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(total + sb.toString());
	}

}
