package victor.codejam.tasks;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

// https://code.google.com/codejam/contest/32013/dashboard#s=p0

public class SavingTheUniverseTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	private Set<String> engines = new HashSet<String>();
	private List<String> queries = new LinkedList<String>();
	private int result;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		Set<String> eCpy = new HashSet<String>(engines);
		for(String q : queries) {
			eCpy.remove(q);
			if (eCpy.isEmpty()) {
				result++;
				eCpy = new HashSet<String>(engines);
				eCpy.remove(q);
			}
		}

	}

	@Override
	protected TestCase parseTestCaseInput() {
		engines.clear();
		queries.clear();
		for(int i = getInt(); i >=1; i--)
			engines.add(getStr());
		for(int i = getInt(); i >=1; i--)
			queries.add(getStr());
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
