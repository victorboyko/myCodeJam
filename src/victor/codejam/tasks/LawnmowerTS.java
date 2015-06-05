package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;
import victor.codejam.Util;

//http://code.google.com/codejam/contest/2270488/dashboard#s=p1
public class LawnmowerTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	int height, width;
	short[][] b;
	String result;
	
	final static String YES = "YES";
	final static String NO = "NO";
	
	private short getMaxHeigh(short[][] board) {
		short result = 0;
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++)
				if (board[i][j] != -1 && board[i][j] > result)
					result = board[i][j];
		return result;
	}
	
//	private short getMinHeigh(short[][] board) {
//		short result = 101;
//		for(int i = 0; i < height; i++)
//			for(int j = 0; j < width; j++)
//				if (board[i][j] != -1 && board[i][j] < result)
//					result = board[i][j];
//		return result;
//	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		short bMax;
		while((bMax = getMaxHeigh(b)) != 0) {
			short[][] bCopy = Util.cloneArray(b);
			for(int i = 0; i < height; i++) {
				out:
				for(int j = 0; j < width; j++) {
					if (b[i][j] == bMax) {
						bCopy[i][j] = -1;
						boolean stop = true;
						in:
						for(int k = 0; k < width; k++)
							if (b[i][k] == -1) {
								stop = false;
								break in;
							}
						if (stop)
							continue out;
						for(int k = 0; k < height; k++)
							if (b[k][j] == -1) {
								result = NO;
								return;
							}
					}
				}
			}
			b = bCopy;
		}
		result = YES;
	}

	@Override
	protected TestCase parseTestCaseInput() {
		height = getInt();
		width = getInt();
		b = new short[height][width];
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++)
				b[i][j] = (short)getInt();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(result);
	}

}
