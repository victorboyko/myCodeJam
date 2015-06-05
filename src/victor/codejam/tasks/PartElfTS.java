package victor.codejam.tasks;

//TODO - large set didn't work

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class PartElfTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	long P, Q;
	Long result;
	
	long getLog2(long a) {
		long result = (long)(Math.floor(Math.log(a) / Math.log(2)));
		return result;
	}
	
	boolean isPurePow2(long a) {
		double log2 = Math.log(a)/Math.log(2d);
		
		return log2 - Math.floor(log2) == 0.0d;// < 0.0000000000001d;
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		
		for(int a : simples) {
			if (a == 1) continue;
			while (P % a == 0 && P / a > 0 && Q % a == 0 && Q / a > 0) {
				P /= a;
				Q /= a;
			}	
		}
		
		if (!isPurePow2(Q)) {
			result = null;
			return;
		}
		
		while(P > 1) {
			long log2 = getLog2(P);
			P = (long)(Math.pow(2, log2));
			//Q /= 2;
			while(P % 2 == 0 && P/2 > 0 && Q % 2 == 0 && Q / 2 > 0) {
				P /= 2;
				Q /= 2;
			}
		}
		
		result = (long)(Math.log(Q)/Math.log(2d));
	}

	static int testCaseNum;
	@Override
	protected TestCase parseTestCaseInput() {
		System.out.println(++testCaseNum);
		String s = getStr();
		P = Long.parseLong(s.split("/")[0]);
		Q = Long.parseLong(s.split("/")[1]);
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		if (result == null) 
			putStrNoNewLine("impossible");
		else putIntNoNewLine(result);
	}
	
	static int[] GetSimpleNumbers(int Value)
    {
        if (Value < 2)
        {
            int[] A = new int[0];
            return A;
        }
        else
        {
            int[] T = new int[Value];
            T[0] = 2;
            int K = 1, I = 3;
            boolean B = true;
            while (I <= Value)
            {
                B = true;
                for (int J = 0; J < K; J++)
                    if (I % T[J] == 0)
                    {
                        B = false;
                        break;
                    }
                if (B) T[K++] = I;
                I += 2;
            }
            int[] A = new int[K];
            for (int J = 0; J < A.length; J++)
                A[J] = T[J];
            return A;
        }
    }
	
	static int[] simples = GetSimpleNumbers(1000000);

}
