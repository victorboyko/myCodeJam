package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class TechnobabbleTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	int N;
	
	class Pair {
		String s1, s2;
		
		Pair(String s1, String s2) {
			this.s1 = s1;
			this.s2 = s2;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Pair)) return false;
			if (obj == this) return true;
			Pair p = (Pair)obj;
			return p.s1.equals(this.s1) && p.s2.equals(this.s2);
		}
		
		@Override
		public int hashCode() {
			return s1.hashCode() * 37 + s2.hashCode();
		}
	}
	List<Pair> pairs;

	Map<String, Integer> cnt1st, cnt2nd;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		cnt1st = new HashMap<String, Integer>();
		cnt2nd = new HashMap<String, Integer>();
		
		for(Pair p : pairs) {
			if (!cnt1st.containsKey(p.s1)) {
				cnt1st.put(p.s1, 1);
			} else {
				cnt1st.put(p.s1, cnt1st.get(p.s1) + 1);
			}
			
			if (!cnt2nd.containsKey(p.s2)) {
				cnt2nd.put(p.s2, 1);
			} else {
				cnt2nd.put(p.s2, cnt2nd.get(p.s2) + 1);
			}
		}
		
		
		result = 0;
		List<Pair> doubles = new LinkedList<TechnobabbleTS.Pair>();
		
		for(Pair p : pairs) {
			if (cnt1st.get(p.s1) > 1 && cnt2nd.get(p.s2) > 1) {
				doubles.add(p);
//				result++;
//				cnt1st.put(p.s1, cnt1st.get(p.s1) - 1);
//				cnt2nd.put(p.s2, cnt2nd.get(p.s2) - 1);
			}
		}
		
        int factorial = doubles.size();
        for(int i = doubles.size() - 1; i > 1; i--) {
            factorial = factorial * i;
        }
        
        
        for(int i = 0; i < /* factorial */ doubles.size() * 1000; i++) {
        	Map<String, Integer> cnt1stCpy = new HashMap<String, Integer>(cnt1st);
        	Map<String, Integer> cnt2ndCpy = new HashMap<String, Integer>(cnt2nd);
        	
            int localMax = 0;
        	
        	for(int j = 0; j < 100; j++) {
        		int a = rnd.nextInt(doubles.size());
        		Pair buf = doubles.remove(a);
        		doubles.add(buf);
        	}
        	
        	for(int j = 0; j < doubles.size(); j++) {
        		int index = 0;//??
        		Pair p = doubles.get(index);
        		if (cnt1stCpy.get(p.s1) > 1 && cnt2ndCpy.get(p.s2) > 1) {
    				localMax++;
    				cnt1stCpy.put(p.s1, cnt1stCpy.get(p.s1) - 1);
    				cnt2ndCpy.put(p.s2, cnt2ndCpy.get(p.s2) - 1);
    			}
        	}
        	if (localMax > result) {
        		result = localMax;
        	}
        	
        }
		
	}
	
	int result;
	Random rnd = new Random();

	@Override
	protected TestCase parseTestCaseInput() {
		pairs = new ArrayList<Pair>();
		int N = getInt();
		for(int i = 0; i < N; i++) {
			String s = getStr();
			pairs.add(new Pair(s.split(" ")[0], s.split(" ")[1]));
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
