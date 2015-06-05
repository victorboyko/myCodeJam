package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class DijkstraTS extends AbstractTaskSolution implements NoTestCasePreload {

	int L, X;
	String message;
	boolean result;
	List<Character> fullMessage = new ArrayList<Character>();

	
	static class Pair {
		
		char a, b;
		public Pair(char a, char b) {
			this.a = a;
			this.b = b;
		}
		
		@Override
		public int hashCode() {
			return a + 34*b;
		}
		
		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair)obj;
			return p.a == this.a && p.b == this.b;
		}
	}
	
	
	static class IPair {
		
		int a; 
		char b;
		
		public IPair(int a, char b) {
			this.a = a;
			this.b = b;
		}
		
		@Override
		public int hashCode() {
			return a + 34*b;
		}
		
		@Override
		public boolean equals(Object obj) {
			IPair p = (IPair)obj;
			return p.a == this.a && p.b == this.b;
		}
	}
	

	Set<IPair> kCache = new HashSet<IPair>();

	
	//TODO - small set brute forced
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {

		kCache.clear();
		
		
		
		result = false;	
		if (L * X < 3) {
			return; // result -> false
		}
		
		char cur0 = '1';
		for(int i = 0; i < fullMessage.size(); i++) {
			cur0 = t.get(new Pair(cur0, fullMessage.get(i)));
		}
		if (cur0 != '2')
			return; // result -> false
			
		
		char cur1 = '1';
		for(int i = 0; i < fullMessage.size(); i++) {
			cur1 = t.get(new Pair(cur1, fullMessage.get(i)));
			char cur2 = '1';
			if (cur1 == 'i') {
				for(int j = i+1; j < fullMessage.size(); j++) {
					cur2 = t.get(new Pair(cur2, fullMessage.get(j)));
					char cur3 = '1';
					if (cur2 == 'j') {
						result = true;
						return;
						

					} 
				}
				
			}
			
		}
	}
	
	@Override
	protected TestCase parseTestCaseInput() {
		System.out.print("."); // info to see if the tests getting processed fast enough
		fullMessage.clear();
		L = getInt();
		X = getInt();
		message = getStr();
		for(int i = 0; i < X; i++)
			for (int j = 0; j < L; j++)
				fullMessage.add(message.charAt(j));
		return null;
	}
	
	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(result ? "YES" : "NO");
	}
	
	
	static final Map<Pair, Character> t = new HashMap<Pair, Character>();
	static {
		t.put(new Pair('1', '1'), '1');
		t.put(new Pair('1', 'i'), 'i');
		t.put(new Pair('1', 'j'), 'j');
		t.put(new Pair('1', 'k'), 'k');
		
		t.put(new Pair('i', '1'), 'i');
		t.put(new Pair('i', 'i'), '2');
		t.put(new Pair('i', 'j'), 'k');
		t.put(new Pair('i', 'k'), 'J');
		
		t.put(new Pair('j', '1'), 'j');
		t.put(new Pair('j', 'i'), 'K');
		t.put(new Pair('j', 'j'), '2');
		t.put(new Pair('j', 'k'), 'i');
		
		t.put(new Pair('k', '1'), 'k');
		t.put(new Pair('k', 'i'), 'j');
		t.put(new Pair('k', 'j'), 'I');
		t.put(new Pair('k', 'k'), '2');
		
		
		
		t.put(new Pair('2', '1'), '2');
		t.put(new Pair('2', 'i'), 'I');
		t.put(new Pair('2', 'j'), 'J');
		t.put(new Pair('2', 'k'), 'K');
		
		t.put(new Pair('I', '1'), 'I');
		t.put(new Pair('I', 'i'), '1');
		t.put(new Pair('I', 'j'), 'K');
		t.put(new Pair('I', 'k'), 'j');
		
		t.put(new Pair('J', '1'), 'J');
		t.put(new Pair('J', 'i'), 'k');
		t.put(new Pair('J', 'j'), '1');
		t.put(new Pair('J', 'k'), 'I');
		
		t.put(new Pair('K', '1'), 'K');
		t.put(new Pair('K', 'i'), 'J');
		t.put(new Pair('K', 'j'), 'i');
		t.put(new Pair('K', 'k'), '1');
		
		
		
		t.put(new Pair('1', '2'), '2');
		t.put(new Pair('1', 'I'), 'I');
		t.put(new Pair('1', 'J'), 'J');
		t.put(new Pair('1', 'K'), 'K');
		
		t.put(new Pair('i', '2'), 'I');
		t.put(new Pair('i', 'I'), '1');
		t.put(new Pair('i', 'J'), 'K');
		t.put(new Pair('i', 'K'), 'j');
		
		t.put(new Pair('j', '2'), 'J');
		t.put(new Pair('j', 'I'), 'k');
		t.put(new Pair('j', 'J'), '1');
		t.put(new Pair('j', 'K'), 'I');
		
		t.put(new Pair('k', '2'), 'K');
		t.put(new Pair('k', 'I'), 'J');
		t.put(new Pair('k', 'J'), 'i');
		t.put(new Pair('k', 'K'), '1');
		
		
		
		t.put(new Pair('2', '2'), '1');
		t.put(new Pair('2', 'I'), 'i');
		t.put(new Pair('2', 'J'), 'j');
		t.put(new Pair('2', 'K'), 'k');
		
		t.put(new Pair('I', '2'), 'i');
		t.put(new Pair('I', 'I'), '2');
		t.put(new Pair('I', 'J'), 'k');
		t.put(new Pair('I', 'K'), 'J');
		
		t.put(new Pair('J', '2'), 'j');
		t.put(new Pair('J', 'I'), 'K');
		t.put(new Pair('J', 'J'), '2');
		t.put(new Pair('J', 'K'), 'i');
		
		t.put(new Pair('K', '2'), 'k');
		t.put(new Pair('K', 'I'), 'j');
		t.put(new Pair('K', 'J'), 'I');
		t.put(new Pair('K', 'K'), '2');
		
	}
	
}
