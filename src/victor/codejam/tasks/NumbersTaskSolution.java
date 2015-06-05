package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;

public class NumbersTaskSolution extends AbstractTaskSolution {
	
	double[] numbers = new double[100];
	static final int[] seq = {5, 7, 3, 1}; 
	
	public NumbersTaskSolution() {
		init();
	}
	
	public double get3to3Double(double d) {
		d *= 10000d;
		d = Math.floor(d) / 100000000d;
		d -= Math.floor(d);
		d *= 10000d;
		return d;
	}
	
	
	
	public void init() {
		numbers[0] = 3d + Math.sqrt(5d);
		int i;
		for(i = 1; i < 7 && i < numbers.length; i++) {
			numbers[i] = numbers[i-1] * numbers[0];
			if (String.format("%.3f", numbers[i]).endsWith("000")) {
				break;
			}
		}
		
		for(; i < numbers.length; i++) {
			double d = numbers[i-1];//debug
			d = get3to3Double(numbers[i-1] * numbers[0]);
			String dStr = String.valueOf(d);
			if (dStr.charAt(dStr.indexOf('.')-1) != '0' + seq[i % 4]) {
				d = Double.valueOf( dStr.substring(0, dStr.indexOf('.')-1) + (char)('0' + seq[i % 4]) + ".9999");// + dStr.substring(dStr.indexOf('.')+1) );
				d = get3to3Double(d);
			}
			numbers[i] = d;
		}
 	}
	
	// log(base 2) x=log(base e) x/log(base e) 2
	
	public static double getLog(double x, double base) {
		return Math.log(x) / Math.log(base);
	}
	
	public static int getIntLog(double x, int base) {
		return (int)(Math.floor(Math.log(x) / Math.log(base)));
	}
	
	class Tc implements TestCase {
		int n;
		String result;
	}

	public final static double D3_PLUS_SQRT_5 = 3 + Math.sqrt(5);;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		Tc tc = (Tc)testCase;
		tc.result = "00" + String.valueOf(numbers[tc.n - 1]);
		tc.result = tc.result.substring(tc.result.indexOf('.') - 3, tc.result.indexOf('.'));
	}

	@Override
	protected TestCase parseTestCaseInput() {
		Tc tc = new Tc();
		tc.n = getInt();
		return tc;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(((Tc)testCase).result);

	}

}
