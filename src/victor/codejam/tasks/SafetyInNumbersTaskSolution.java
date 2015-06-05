package victor.codejam.tasks;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//http://code.google.com/codejam/contest/1836486/dashboard
public class SafetyInNumbersTaskSolution extends AbstractTaskSolution implements
		NoTestCasePreload {

	int nPlayers;
	int[] points;
	double[] result;
	int pointsSum;
	double[][] matrix;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		points = new int[nPlayers = getInt()];
		result = new double[nPlayers];
		pointsSum = 0;
		for(int i = 0; i < nPlayers; i++) {
			pointsSum += points[i] = getInt();	
		}

		boolean more;
		do {
			
		matrix = new double[nPlayers+1][nPlayers+2];
		for(int i = 0; i < nPlayers; i++) {
			matrix[i][i] = pointsSum;
			if (points[i] != pointsSum && points[i] != 101) {
				matrix[i][nPlayers] = -1;
				matrix[i][nPlayers+1] = points[i];
			}
		}
		for(int i = 0; i < nPlayers; i++) {
			matrix[nPlayers][i] = 1;
		}
		matrix[nPlayers][nPlayers+1] = -1;
		
		for(int i = 1; i < nPlayers + 1; i++) {
			double factor = - matrix[nPlayers][i-1] / matrix[i-1][i-1];
			for(int j = i - 1; j < nPlayers + 2; j++)
				matrix[nPlayers][j] += factor * matrix[i-1][j];
		}
		
		//for(int i = nPlayers-1; i >= 0; i--) 
		{
			int i = nPlayers-1;
			for(int j = i; j >= 0; j--) {
				matrix[j][nPlayers+1] -= matrix[j][i+1] * matrix[i+1][nPlayers+1] / matrix[i+1][i+1];
				matrix[j][i+1] = 0;
			}
		}
		
		
		more=false;
		for(int i = 0; i < nPlayers; i++) {
			result[i] = -100d * matrix[i][nPlayers+1] / matrix[i][i];
			if (result[i] == -0d) result[i]=0d;
			if (more = (result[i] < 0d || result[i] > 100d) ) {
				points[i] = 101;
				break;
			}
		}
		
		} while(more);
		
		
	}

	@Override
	protected TestCase parseTestCaseInput() {
		//see executeSolutionBody
		return null;
	}
	


	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		for(int i = 0; i < nPlayers; i++)
			putDblNoNewLine(result[i]);
	}

}
