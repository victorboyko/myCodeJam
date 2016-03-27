package victor.codejam.tasks;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

// https://code.google.com/codejam/contest/4224486/dashboard#s=p1

public class HaircutTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	int nBarbers, place, result;
	
	List<Integer> m = new ArrayList<Integer>();
	
	private int getInd(List<Integer> t) {
		
		int min = Collections.min(t);
		int minInd = Collections.indexOfSubList(t, Collections.singletonList(min));
		for(int i = 0; i < t.size(); i++) {
			t.set(i, t.get(i) - min);
		}
		t.set(minInd, m.get(minInd));

		return minInd;
	}
	
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		if (place == 1) {
			result = 1;
			return;
		}
		
		
		BigInteger fullCycleTime = BigInteger.ONE;
		List<Integer> m1 = new ArrayList<Integer>(m);
		for(int i = 0; i < m1.size(); i++) {
			for(int j = i + 1; j < m1.size(); j++) {
				if (m1.get(i).equals(m1.get(j))) {
					m1.remove(j);
					j--;
				}
			}
		}
		
		
		List<Integer> m2 = new ArrayList<Integer>();
		out:
		for(int i : m1) {
			for(int j :m1) {
				if (j % i == 0 && j / i > 1)
					continue out;
			}
			m2.add(i);
			
		}
		for(int i = 2; i < Collections.max(m2); i++) {
			for(int j = 0; j < m2.size(); j++) {
				int res;
				if (m2.get(j) % i == 0 && (res = m2.get(j) / i) > 1) {
					m2.add(res);
					m2.add(i);
					m2.remove(j);
					i--;
					break;
				}
			}
		}
		
		for(int i: m2) {
			fullCycleTime = fullCycleTime.multiply(BigInteger.valueOf(i));
		}
		BigInteger fullCycleClients = BigInteger.ZERO;
		for(int i : m) {
			fullCycleClients = fullCycleClients.add(fullCycleTime.divide(BigInteger.valueOf(i)));
		}
		place--;
		place = BigInteger.valueOf(place).mod(fullCycleClients).intValue();
		place++;
				
		List<Integer> t = new ArrayList<Integer>();
		for(int i = 0; i < nBarbers; i++) {
			t.add(0);
		}
		for(; place!=0; place--) {
			if (place % 100000 == 0) System.out.print(".");
			result = getInd(t);
		}
		result++;
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
