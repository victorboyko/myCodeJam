package victor.codejam.tasks;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class FractilesTS extends AbstractTaskSolution implements NoTestCasePreload {

	int K, C, S;
	Collection<String> result;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = null;
		if (C * S < K) return;
		
		result = new HashSet<String>();
		BigInteger index = BigInteger.ZERO;
		for(int i = 0; i < K;) {
			index = index.multiply(BigInteger.valueOf(K));
			index = index.add(BigInteger.valueOf(i));
			i++;	
			if ((i % C == 0 && i / C > 0) || i == K) {
				result.add(index.add(BigInteger.ONE).toString()); // as tiles numbering starts from 1
				index = BigInteger.ZERO;
			}
		}
		
	}

	@Override
	protected TestCase parseTestCaseInput() {
		K = getInt();
		C = getInt();
		S = getInt();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		if (result == null) putStrNoNewLine("IMPOSSIBLE"); else {
			for(String val : result) {
				putStrNoNewLine(val);
			}
		}
	}

}
