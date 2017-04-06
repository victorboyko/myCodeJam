package victor.codejam.standalone;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

//https://code.google.com/codejam/contest/4304486/dashboard#s=p2
public class BBFsTS {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		for(int t = 1, tNum = sc.nextInt(); t <= tNum ; t++) {
			
			//reading test case input
			int n = sc.nextInt();
			Map<Integer, Integer> ids = new HashMap<Integer, Integer>(n);
			for(int i = 1; i <= n; i++) {
				ids.put(i, sc.nextInt());
			}
			
			// solution starts
			if (t == 4) {
				t = t * 1; //breakpoint
			}
			
			int result = 0;
			
			Map<Integer, Integer> pairs = new HashMap<Integer, Integer>(n);			
			// detecting pairs
			for(Entry<Integer, Integer> e : ids.entrySet()) {
				int val2 = ids.get(e.getValue());
				
				// pairs will be detected twice, that's ok
				if (e.getKey().equals(val2)) {
					pairs.put(e.getKey(), e.getValue());
					pairs.put(e.getValue(), e.getKey());
				}
				
			}
			// removing pairs from ids
			for(int key : pairs.keySet()) {
				ids.remove(key);
			}
			
			//detect circles 
			boolean hasCircles = true;
			
			out:
			while(hasCircles) {
				
				for (int start : ids.keySet()) {
					Map<Integer, Integer> met = new HashMap<Integer, Integer>();
					int cnt = 0;
					
					while (ids.containsKey(start)) {
						int value = ids.get(start);
						
						met.put(start, cnt++);
						if (met.containsKey(value)) { // found a circle
							// removing a circle from ids
							cnt = met.get(value); // we need to remove circle only, not the tail we started with
							int circleLength = 0;
							for (Entry<Integer, Integer> eMet : met.entrySet()) {
								if (eMet.getValue() >= cnt) {
									ids.remove(eMet.getKey());
									circleLength++;
								}
							}
							result = Math.max(result, circleLength);
							continue out;
						}
						start = value;
					}
				}
				hasCircles = false;
			}
			
			// filiing invert map
			Map<Integer, Set<Integer>> invert = new HashMap<Integer, Set<Integer>>(ids.size());
			for(Entry<Integer, Integer> e : ids.entrySet()) {
				if (!invert.containsKey(e.getValue())) {
					invert.put(e.getValue(), new HashSet<Integer>());
				}
				invert.get(e.getValue()).add(e.getKey());
			}

			// get longest tail of each of pairs
			
			int tailsTotal = 0;
			
			while(!pairs.isEmpty()) {
				int first = pairs.keySet().iterator().next();
				int second = pairs.get(first);
				tailsTotal += maxTail(2, first, invert) + maxTail(2, second, invert) - 2;
				pairs.remove(first);
				pairs.remove(second);
			}
			
			// compare sum of lengths of pairs with tail VS biggest circle
			
			result = Math.max(result, tailsTotal);
			
			// output
			System.out.printf("Case #%d: %d%n", t, result);			
		}

	}
	
	private static int maxTail(int curLength, int key, Map<Integer, Set<Integer>> invert) {
		Set<Integer> following = invert.get(key);
		if (following == null || following.isEmpty()) {
			return curLength;
		}
		int max = curLength;
		for(int next : following) {
			max = Math.max(max, maxTail(curLength+1, next, invert));
		}
		return max; 
	}

}
