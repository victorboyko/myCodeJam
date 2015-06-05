package victor.codejam.tasks;

import java.util.HashSet;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//http://code.google.com/codejam/contest/2270488/dashboard#s=p0
public class TicTacToeTomekTS extends AbstractTaskSolution implements NoTestCasePreload {

	char[][] b;
	static final int width = 4;
	String result;
	
	static final String X_WON = "X won";
	static final String O_WON = "O won";
	static final String DRAW = "Draw";
	static final String GAME_NOT_COMPLETED = "Game has not completed";
	
	static final Set<Character> x_set = new HashSet<Character>();
	static final Set<Character> o_set = new HashSet<Character>();
	static {
		x_set.add('X');
		x_set.add('T');
		o_set.add('O');
		o_set.add('T');
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		boolean xWon = true;
		boolean oWon = true;
		for(int i = 0; i < width; i++) {
			xWon &= x_set.contains(b[i][i]);
			oWon &= o_set.contains(b[i][i]);
		}
		if (xWon) {
			result = X_WON;
			return;
		}
		if (oWon) {
			result = O_WON;
			return;
		}
		xWon = true;
		oWon = true;
		for(int i = 0; i < width; i++) {
			xWon &= x_set.contains(b[i][width-1-i]);
			oWon &= o_set.contains(b[i][width-1-i]);
		}
		if (xWon) {
			result = X_WON;
			return;
		}
		if (oWon) {
			result = O_WON;
			return;
		}
		// xWon == oWon == false
		for(int i = 0; i < width; i++) {
			int xCount = 0;
			int oCount = 0;
			for(int j = 0; j < width; j++) {
				if (x_set.contains(b[i][j])) {
					xCount++;
					if (xCount == width) {
						result = X_WON;
						return;
					}		
				}
				if (o_set.contains(b[i][j])) {
					oCount++;
					if (oCount == width) {
						result = O_WON;
						return;
					}		
				}
			}
		}
		
		for(int j = 0; j < width; j++) {
			int xCount = 0;
			int oCount = 0;
			for(int i = 0; i < width; i++) {
				if (x_set.contains(b[i][j])) {
					xCount++;
					if (xCount == width) {
						result = X_WON;
						return;
					}		
				}
				if (o_set.contains(b[i][j])) {
					oCount++;
					if (oCount == width) {
						result = O_WON;
						return;
					}		
				}
			}
		}

		
		boolean hasDot = false;
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < width; j++) {
				hasDot |= b[i][j] == '.';
			}
		}
		
		result = hasDot ? GAME_NOT_COMPLETED : DRAW;
	}

	@Override
	protected TestCase parseTestCaseInput() {
		b = new char[width][width];
		for(int i = 0; i < width; i++) {
			String str;
			do {
				str = getStr();
			} while (str.length() == 0);
			for(int j = 0; j < width; j++) {
				b[i][j] = str.charAt(j);
			}
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(result);
	}

}
