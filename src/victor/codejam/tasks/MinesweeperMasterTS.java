package victor.codejam.tasks;

import java.util.Arrays;
import java.util.Random;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class MinesweeperMasterTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	private int Y, X, M;
	private Random rnd = new Random();
	private char[][] result;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		if (M >= Y * X) {
			result = null;
			return;
		}
		
		result = new char[Y][X];
		
		if (M == Y * X - 1) {
			for (int i = 0; i < Y; i++) {
				Arrays.fill(result[i], '*');
			}
			result[rnd.nextInt(Y)][rnd.nextInt(X)] = 'c';
			return;
		}
		
		if ((Y == 2 || X == 2) && (M % 2 == 1 || (X * Y == M + 2)&&(M != 0) )) {
			result = null;
			return;
		}
		
			
		for (int i = 0; i < Y; i++) {
			Arrays.fill(result[i], '.');
		}
		
		if (M == 0) {
			result[rnd.nextInt(Y)][rnd.nextInt(X)] = 'c';
			return;
		}
		
		if  ((X!=1 && Y!=1) && ((M > X*Y-4) || (M == X*Y-5) || (M == X*Y-7))) {
			result = null;
			return;
		}
		
		if (X == 1 || X == 2) {
			result[0][0] = 'c';
			for (int i = 0; i < M / X; i++)
				Arrays.fill(result[Y-i-1], '*');
			return;
		}
		if (Y == 1 || Y == 2) {
			result[0][0] = 'c';
			for (int j = 0; j < M / Y; j++) {
				result[0][X-j-1] = '*';
				if (Y == 2)
					result[1][X-j-1] = '*';
			}
			return;
		}
		
		int hull = (X-2)*(Y-2);
		
		if (M  == X*Y-4) {
			for (int i = 0; i < Y; i++) {
				Arrays.fill(result[i], '*');
			}
			result[0][0] = 'c';
			result[0][1] = result[1][0] = result[1][1] = '.';
			return;
		}
		
		result[0][0] = 'c';
		int cnt = 0;
		for(int i = Y-1; i >= 2; i--) {
			for (int j = X-1; j >=2; j--) {
				result[i][j] = '*';
				if (++cnt == M)
					return;
				if (i == 2 && j == 2) {
					result[i][j] = '.';
				}
			}
		}
		
		int remainder = M - (hull - 1);
		
		if (remainder % 2 == 1) {
			result[2][2] = '*';
			remainder--;
		}
		
		for (int i = 0; i < Y - 3 && i < remainder / 2; i++) {
			result[Y-1-i][0] = result[Y-1-i][1] = '*';
		}
		remainder -= 2 * (Y-3);
		for (int j = 0; j < X - 3 && j < remainder / 2; j++) {
			result[0][X-1-j] = result[1][X-1-j] = '*';
		}
		remainder -= 2 * (X-3);
		if (remainder > 0) {
			result[0][2] = result[1][2] = '*';
			remainder -= 2;
		}
		if (remainder > 0) {
			result[2][0] = result[1][2] = '*';
			remainder -= 2;
		}
		return;

	}

	@Override
	protected TestCase parseTestCaseInput() {
		Y = getInt();
		X = getInt();
		M = getInt();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		if (result == null) {
			putStrNoNewLine("\nImpossible");
		} else {
			for (int i = 0; i < Y; i++) {
				putStrNoNewLine("\n" + new String(result[i]));
			}
			
		}
	}

}
