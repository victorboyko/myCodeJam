package victor.codejam.tasks;

import java.util.HashMap;
import java.util.Map;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class AramTS extends AbstractTaskSolution implements NoTestCasePreload {
	
	private int N, G;
	double R;
	private double[] in;
	
	Map<Integer, Double> cache = new HashMap<Integer, Double>();

	
	double getProb(int r) {
		if (r < 0)
			return 0;
		if (cache.containsKey(r))
			return cache.get(r);
		
		double result = 0;
		double pMax = 0;
		
		//TODO 
		
		for(double p : in) {
			
		}
		
		cache.put(r, result);
		return result;
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		cache.clear();
		
		// TODO

	}

	@Override
	protected TestCase parseTestCaseInput() {
		N = getInt();
		R = getDouble();
		G = getInt();
		in = new double[N];
		for (int i = 0; i < N; i++) {
			in[i] = getDouble();
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		// TODO Auto-generated method stub

	}

}
