package victor.codejam.tasks;

import java.math.BigInteger;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//http://code.google.com/codejam/contest/2418487/dashboard
public class BullseyeTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	long minRad, result;
	BigInteger nPaint;
	
	private static BigInteger getS(long radius) {
		BigInteger result = BigInteger.valueOf(radius);
		result = result.multiply(result.add(BigInteger.ONE));
		result = result.divide(BigInteger.valueOf(2L));
		return result;
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {		
		nPaint = nPaint.add(getS(minRad - 1L));
		result = (-1L + bigIntSqRootFloor(nPaint.multiply(BigInteger.valueOf(8L)).add(BigInteger.ONE)).longValue()) / 2L;
		result -= minRad - 1L;
		result /= 2L;
		
	}

	@Override
	protected TestCase parseTestCaseInput() {
		minRad = getLong();
		nPaint = BigInteger.valueOf(getLong());
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}
	
	public static BigInteger bigIntSqRootFloor(BigInteger x)
	        throws IllegalArgumentException {
	    if (x.compareTo(BigInteger.ZERO) < 0) {
	        throw new IllegalArgumentException("Negative argument.");
	    }
	    // square roots of 0 and 1 are trivial and
	    // y == 0 will cause a divide-by-zero exception
	    if (x == BigInteger.ZERO || x == BigInteger.ONE) {
	        return x;
	    } // end if
	    BigInteger two = BigInteger.valueOf(2L);
	    BigInteger y;
	    // starting with y = x / 2 avoids magnitude issues with x squared
	    for (y = x.divide(two);
	            y.compareTo(x.divide(y)) > 0;
	            y = ((x.divide(y)).add(y)).divide(two));
	    return y;
	} // end bigIntSqRootFloor

	public static BigInteger bigIntSqRootCeil(BigInteger x)
	        throws IllegalArgumentException {
	    if (x.compareTo(BigInteger.ZERO) < 0) {
	        throw new IllegalArgumentException("Negative argument.");
	    }
	    // square roots of 0 and 1 are trivial and
	    // y == 0 will cause a divide-by-zero exception
	    if (x == BigInteger.ZERO || x == BigInteger.ONE) {
	        return x;
	    } // end if
	    BigInteger two = BigInteger.valueOf(2L);
	    BigInteger y;
	    // starting with y = x / 2 avoids magnitude issues with x squared
	    for (y = x.divide(two);
	            y.compareTo(x.divide(y)) > 0;
	            y = ((x.divide(y)).add(y)).divide(two));
	    if (x.compareTo(y.multiply(y)) == 0) {
	        return y;
	    } else {
	        return y.add(BigInteger.ONE);
	    }
	} // end bigIntSqRootCeil

}


