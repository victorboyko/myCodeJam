package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class TheRepeaterTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	List<LinkedList<Character>> lines;
	int N;
	Integer result;
	
	
	private int getMinLen(int[] len) {
		int min = 101;
		for(int i = 0; i < N; i++) {
			min = Math.min(min, len[i]);
		}
		return min;
	}
	
	private int getCnt(int len[], int val) {
		int res = 0;
		for(int i = 0; i < N; i++) {
			if (len[i] == val)
				res++;
		}
		return res;
	}
	
	private int getMaxLen(int[] len) {
		int max = -1;
		for(int i = 0; i < N; i++) {
			max = Math.max(max, len[i]);
		}
		return max;
	}
	
	private void addAll(int len[], int val) {
		for(int i = 0; i < N; i++) {
			len[i] += val;
		}
	}
	
	private void addPos(int len[], int val, int posVal) {
		for(int i = 0; i < N; i++) {
			if (len[i] == posVal)
				len[i] += val;
		}
	}
	
	private boolean isSameSize(int len[]) {
		for(int i = 1; i < N; i++) {
			if (len[i] != len[0]) 
				return false;
		}
		return true;
	}
	
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		for(;;) {
			
			//Empty check begin
			boolean emptyFound = false;
			boolean nonEmptyFound = false;
			for(int j = 0; j < N; j++) {
				if (lines.get(j).size() == 0)
					emptyFound = true;
				else nonEmptyFound = true;
			}
			if (emptyFound && nonEmptyFound) {
				result = null;
				return;
			}
			if (emptyFound)
				return;
			//Empty check end
			
			char c = lines.get(0).peek();
			int[] len  = new int[N];
			for(int j = 0; j < N; j++) {
				LinkedList<Character> l = lines.get(j);
				while(l.size() > 0 && l.peek().equals(c)) {
					l.poll();
					len[j]++;
				}
				if (len[j] == 0) {
					result = null;
					return;
				}
			}
			
			addAll(len, - getMinLen(len));

			while (!isSameSize(len)) {
				
				int maxLen = getMaxLen(len);
				int minLen = getMinLen(len);
				int maxCnt = getCnt(len, maxLen);
				int minCnt = getCnt(len, minLen);
				
				if (maxCnt < minCnt) {
					result += maxCnt;
					addPos(len, -1, maxLen);
				} else {
					result += minCnt;
					addPos(len, 1, minLen);
				}
				
			}
			
		}

	}

	@Override
	protected TestCase parseTestCaseInput() {
		lines = new ArrayList<LinkedList<Character>>();
		N = getInt();
		for(int i = 0; i < N; i++) {
			String s = getStr();
			LinkedList<Character> l2 = new LinkedList<Character>();
			for(int j = 0; j < s.length(); j++) {
				l2.add(s.charAt(j));
			}
			lines.add(l2);
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		if (result != null)
			putIntNoNewLine(result);
		else putStrNoNewLine("Fegla Won");
	}

}
