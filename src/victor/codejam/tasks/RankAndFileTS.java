package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class RankAndFileTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	int N;
	class Line extends ArrayList<Integer> {}
	
	List<Line> lists;
	
	Line result;
	
	
	int compare(List<Integer> a, List<Integer> b) {
		for(int i = 0; i < N; i++) {
			int res = a.get(i).compareTo(b.get(i));
			if (res != 0) {
				return res;
			}
		}
		return 0;
	}
	
	boolean place(int[][] matrix, List<Integer> list, int ind, boolean horizontally) {
		
		if (horizontally) {
			for(int i = 0; i < N; i++) {
				int inVal = list.get(i);
				if (matrix[ind][i] != 0 && matrix[ind][i] != inVal) return false;
				matrix[ind][i] = inVal;
			}
		} else {
			for(int i = 0; i < N; i++) {
				int inVal = list.get(i);
				if (matrix[i][ind] != 0 && matrix[i][ind] != inVal) return false;
				matrix[i][ind] = inVal;
			}
		}
		return true;
	}
	
	boolean isValidMatrix(int[][] matrix) {
		return isValidMatrixOneWay(matrix) && isValidMatrixOneWay(transpon(matrix));
	}
	
	boolean isValidMatrixOneWay(int[][] matrix) {
		for(int i = 0; i < N-1; i++) {
			int minX = matrix[i][0] == 0 ? 2501 : matrix[i][0];
			for(int j = 1; j < N-1; j++) {
				if (matrix[i][j] == 0) continue;
				if (matrix[i][j] < minX) return false;
				minX = matrix[i][j];
			}
		}
		return true;
	}
	
	
	int[][] createMatrix() {
		int[][] matr = new int[N][];
		for(int i = 0; i < N; i++) {
			matr[i] = new int[N];
		}
		return matr;
	}
	
	int[][] createCopy(int[][] matrix) {
		return transpon(transpon(matrix));
	}
	
	int[][] transpon(int[][] matrix) {
		int[][] matr = createMatrix();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				matr[i][j] = matrix[j][i];
			}
		}
		return matr;
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = new Line();
		
		Collections.sort(lists, new Comparator<Line>() {
			@Override
			public int compare(Line o1, Line o2) {
				return RankAndFileTS.this.compare(o1, o2);
			}
		});
		
		
		
		
		out1:
		for(int cnt = 0; cnt < N; cnt++) {
			
			int[][] matr = createMatrix();
			List<Line> listsCp = new LinkedList<RankAndFileTS.Line>(lists);
			boolean horizResult = false;
			
			for(int i = 0; i < N; i++) {
				
				int[][] cp = createCopy(matr);
				boolean validOp = true;
				
				if (i == cnt) {
					Line l = listsCp.remove(0);
					validOp = place(cp, l, i, true);
					if (isValidMatrix(cp) && validOp) {
						matr = cp;
						horizResult = false;
						continue;
					}
					horizResult = true;
					validOp = place(matr, l, i, false);
				} else {
					Line l1 = listsCp.remove(0);
					Line l2 = listsCp.remove(0);
					validOp = place(cp, l1, i, true);
					validOp &= place(cp, l2, i, false);
					if (isValidMatrix(matr) && validOp) {
						matr = cp;
						continue;
					}
					validOp = place(matr, l1, i, false);
					validOp &= place(matr, l2, i, true);	
				}
				
				if ((!isValidMatrix(matr)) || (!validOp)) {
					continue out1;
				}
				
			}
			
			if (horizResult) {
				for(int i = 0; i < N; i++) {
					result.add(matr[cnt][i]);
				}
			} else {
				for(int i = 0; i < N; i++) {
					result.add(matr[i][cnt]);
				}
			}
			
		}
		

	}

	@Override
	protected TestCase parseTestCaseInput() {
		N = getInt();
		lists = new LinkedList<Line>();
		for(int i = 0; i < 2*N-1; i++) {
			Line row = new Line();
			for(int j = 0; j < N; j++) {
				row.add(getInt());
			}
			lists.add(row);
		}
		return null;
	}

	static int casenum;
	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		System.out.println("\n case: " + ++casenum );
		for(int i = 0; i < N; i++) {
			putIntNoNewLine(result.get(i));
		}
	}

}
