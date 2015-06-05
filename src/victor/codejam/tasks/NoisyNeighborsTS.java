package victor.codejam.tasks;

//TODO - not yet completed

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class NoisyNeighborsTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	int R, C, N, result;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		if (R < C) {
			int tmp = R;
			R = C; C = tmp;
		}
		int clean = ((R+1)/2) * ((C+1)/2) + (R/2)*(C/2);
		
		if (N <= clean)
			return; //result = 0;
		
		boolean[][] empty = new boolean[R][C];
		
		int RM = R*C - N;
		for(int i = 0; i < RM; i++) {
			int maxJ = 0;
			int maxCnt = 0;
			for(int j = 0; j < R*C - (R + C - 1); j++) {
				int x = j % (R-1);
				int y = j / (R-1);
				if (empty[x][y])
					continue;
				int cnt = 0;
				if (x>0 && !empty[x-1][y])
					cnt++;
				if (y>0 && !empty[x][y-1])
					cnt++;
				if (!empty[x+1][y])
					cnt++;
				if (!empty[x][y+1])
					cnt++;
				if (cnt > maxCnt) {
					maxCnt = cnt;
					maxJ = j;
				}
				if (maxCnt == 4)
					break;
			}
			if (maxCnt < 3)
			for(int j = 0; j < R-1;j++) {
				if (empty[j][C-1])
					continue;
				int cnt = 0;
				if(j>0 && !empty[j-1][C-1])
					cnt++;
				if(!empty[j+1][C-1])
					cnt++;
				if (cnt > maxCnt) {
					maxCnt = cnt;
					maxJ = j;
				}
				if (maxCnt == 2)
					break;
			}
			if (maxCnt < 3)
			for(int j = 0; j < C-1;j++) {
				if (empty[R-1][j])
					continue;
				int cnt = 0;
				if(j>0 &&!empty[R-1][j-1])
					cnt++;
				if(!empty[R-1][j+1])
					cnt++;
				if (cnt > maxCnt) {
					maxCnt = cnt;
					maxJ = j;
				}
				if (maxCnt == 2)
					break;
			}
			
			empty[maxJ % (R-1)][maxJ / (R-1)] = true;
		}
		for(int j = 0; j < R*C - (R + C - 1); j++) {
			int x = j % (R-1);
			int y = j / (R-1);
			if (empty[x][y])
				continue;
			if (!empty[x+1][y])
				result++;
			if (!empty[x][y+1])
				result++;
		}
		for(int j = 0; j < R-1;j++) {
			if (empty[j][C-1])
				continue;
			if(!empty[j+1][C-1])
				result++;
		}
		for(int j = 0; j < C-1;j++) {
			if (empty[R-1][j])
				continue;
			if(!empty[R-1][j+1])
				result++;
		}
	}

	@Override
	protected TestCase parseTestCaseInput() {
		R = getInt();
		C = getInt();
		N = getInt();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
