package victor.codejam.tasks;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class CoinJamTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	int N, J;
	Random rn = new Random();
	StringBuffer result;
	static List<Integer> oddPrimes = new ArrayList<Integer>(6541);
	static {
//		oddPrimes.add(1);
		oddPrimes.add(2);
		fillPrimes16();
	}
	

//	public static void main(String[] args) throws Exception {
	
	public static void fillPrimes16() {
		
		// https://www.cs.rit.edu/~ark/cscl/lib/edu/rit/nt/List32BitPrimes.java
		
//		FileOutputStream fos = new FileOutputStream("/Users/Victor/Downloads/primes16.txt");
//		PrintWriter pw = new PrintWriter(fos);
		
		// List of odd primes up to 2^16 and their squares.
		long[] primeList = new long [6541];
		long[] primeSquareList = new long [6541];
		int primeCount = 0;
		
		// Generate all odd primes up to 2^16, write them in the file, and store
		// them in the list.
		long prevPrime = 0L;
		long threshold = 0x100L;
		for (long p = 3; p < 0x10000L; p += 2)
			{
			// Do trial division primality test.
			boolean isPrime = true;
			int i = 0;
			while (isPrime && i < primeCount && primeSquareList[i] <= p)
				{
				isPrime = (p % primeList[i]) != 0;
				++ i;
				}

			if (isPrime)
				{
				
//				pw.println(p);
				oddPrimes.add((int)p);

				// Store prime and its square in the lists.
				primeList[primeCount] = p;
				primeSquareList[primeCount] = p * p;
				++ primeCount;

				// Record previous prime.
				prevPrime = p;
				}
			}
//		pw.close();
//		fos.close();

	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = new StringBuffer();
		
		
		Set<Long> processedNumbers = new HashSet<Long>();
		out1:
		for(int i = 0; i < J; i++) {
			long candidate;
			String binaryCandidate;
			boolean fits;
			List<Integer> divisors = null;
			do {
				
				StringBuffer binaryCandidateSB = new StringBuffer();
				binaryCandidateSB.append("1");
				for(int j = 0; j < N-2; j++) {
					binaryCandidateSB.append(Math.random() > 0.5d ? "1" : "0");
				}
				binaryCandidateSB.append("1");
				candidate = Long.parseLong(binaryCandidate = binaryCandidateSB.toString(), 2);
				
				String[] binParts = new String[8];
				int blockSize = N / binParts.length;
				for(int j = 0; j < binParts.length; j++) {
					binParts[j] = binaryCandidate.substring(j * blockSize, (j+1) * blockSize);
				}
				
//				if (N == 16) {
//					candidate = (rn.nextInt(0x10000) | 0x8001);
//				} else { //N = 32 
//					candidate = ((Math.abs(rn.nextLong())| 0x0000000080000001) & 0x00000000FFFFFFFF);
//				}
				fits = false;
				if (processedNumbers.contains(candidate)) continue;
				processedNumbers.add(candidate);
				divisors = new LinkedList<Integer>(); // 2..10
				fits = true;
				out2:
				for(int j = 2; j <= 10; j++) {
					BigInteger localCandidate = BigInteger.ZERO;
					for(int k = 0; k < binParts.length; k++) {
						localCandidate = localCandidate.multiply(BigInteger.valueOf(j).pow(blockSize));
						localCandidate = localCandidate.add(BigInteger.valueOf(Long.parseLong(binParts[k], j)));
					}
					
					for(int k : oddPrimes) {
						if (k > N / 2) break;
						if (localCandidate.mod(BigInteger.valueOf(k)).equals(BigInteger.ZERO)) {
							divisors.add(k);
							continue out2;
						}
					}
					fits = false;
					break;
				}
			} while (!fits);
			result.append("\n").append(binaryCandidate);
			for(int divisor : divisors) {
				result.append(" ").append(divisor);
			}
		}
	}

	@Override
	protected TestCase parseTestCaseInput() {
		N = getInt();
		J = getInt();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(result.toString());
	}
	

}
