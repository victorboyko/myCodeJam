package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class RotateTaskSolution extends AbstractTaskSolution implements
		NoTestCasePreload {

	char[][] board;
	int width;
	int kLength;
	String result;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		for(int i = 0; i < width; i++) {
			boolean more;
			do {
				more = false;
				for(int j = width-1; j > 0; j--) {
					if (board[i][j] == '.' && board[i][j-1] != '.') {
						board[i][j] = board[i][j-1];
						board[i][j-1] = '.';
						if (!more) more = true;
					}
				} 
			} while (more);
		}
		
		boolean blue = false;
		boolean red = false;
		for(int i = 0; i < width; i++) {
			int blueCount = 0;
			int redCount = 0;
			for(int j = 0; j < width; j++) {
				if (!blue) {
					if (board[i][j] == 'B') 
						blueCount++;
					else blueCount = 0;
					if (blueCount == kLength)
						blue = true;
				}
				if (!red) {
					if (board[i][j] == 'R') 
						redCount++;
					else redCount = 0;
					if (redCount == kLength)
						red = true;
				}
			}
		}
		
		for(int j = 0; j < width; j++) {
			int blueCount = 0;
			int redCount = 0;
			for(int i = 0; i < width; i++) {
				if (!blue) {
					if (board[i][j] == 'B') 
						blueCount++;
					else blueCount = 0;
					if (blueCount == kLength)
						blue = true;
				}
				if (!red) {
					if (board[i][j] == 'R') 
						redCount++;
					else redCount = 0;
					if (redCount == kLength)
						red = true;
				}
			}
		}
		
		for(int i = 0; i < width*2 -1; i++) {
			int blueCount = 0;
			int redCount = 0;
			for(int j = 0; j <width*2 -1; j++) {
				if (!blue) {
					int y = i - j;
					int x = j;
					if (x >= 0 && x < width && y >= 0 && y < width && board[y][x] == 'B')
						blueCount++;
					else blueCount = 0;
					if (blueCount == kLength)
						blue = true;
				}
				if (!red) {
					int y = i - j;
					int x = j;
					if (x >= 0 && x < width && y >= 0 && y < width && board[y][x] == 'R')
						redCount++;
					else redCount = 0;
					if (redCount == kLength)
						red = true;
				}
			}
		}
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < width / 2; j++) 
				if (board[i][j] != board[i][width - 1 - j]) {
					char buf = board[i][j];
					board[i][j] = board[i][width - 1 - j];
					board[i][width - 1 - j] = buf;
				}
		}
		
		for(int i = 0; i < width*2 -1; i++) {
			int blueCount = 0;
			int redCount = 0;
			for(int j = 0; j <width*2 -1; j++) {
				if (!blue) {
					int y = i - j;
					int x = j;
					if (x >= 0 && x < width && y >= 0 && y < width && board[y][x] == 'B')
						blueCount++;
					else blueCount = 0;
					if (blueCount == kLength)
						blue = true;
				}
				if (!red) {
					int y = i - j;
					int x = j;
					if (x >= 0 && x < width && y >= 0 && y < width && board[y][x] == 'R')
						redCount++;
					else redCount = 0;
					if (redCount == kLength)
						red = true;
				}
			}
		}
		
		
		if (blue && !red) {
			result = "Blue";
		} else if (!blue && red) {
			result = "Red";
		} else if (blue && red) {
			result = "Both";
		} else result = "Neither";

	}

	@Override
	protected TestCase parseTestCaseInput() {
		width = getInt();
		board = new char[width][width];
		kLength = getInt();
		for(int i = 0; i < width; i++) {
			String str = getStr();
			for(int j = 0; j < width; j++) {
				board[i][j] = str.charAt(j);
			}
		}

		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(result);
	}

}
