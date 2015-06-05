package victor.codejam.tasks;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//https://code.google.com/codejam/contest/dashboard?c=619102#s=p2
public class MakingChessBoardsTaskSolution extends AbstractTaskSolution implements NoTestCasePreload {
	
	final static Logger logger = Logger.getLogger(MakingChessBoardsTaskSolution.class);
	{ logger.setLevel(Level.INFO); }
	
	//use instance variables for task, no multy-threading then
	int boardHeight, boardWidth;
	private byte[][] board;
	private Map<Integer, Integer> result;

	
	private int getCell(int y, int x) {
		if (!(x >=0 && y >=0 && x < boardWidth && y < boardHeight))
			throw new IllegalArgumentException(new Point(x,y).toString());
		return this.board[y][x];
	}
	
	private void setCell(int y, int x, byte value) {
		this.board[y][x] = value;
	}

	
	private short getSizes(short[][] sizes, Point p) {

		int pSize;
		if (p == null) {
			p = new Point(0, 0);
			pSize = Math.max(boardWidth, boardHeight);
		} else {
			pSize = sizes[p.y][p.x];
			for(int a = 0; a < pSize; a++)
				for (int b = 0; b < pSize; b++)
					sizes[p.y-a][p.x-b] = 0;
		}
		
		short maxSize = 0;
		
		if (p.x == 0)
		for(int i = Math.max(0, p.y - pSize - 1); i < Math.min(boardHeight, p.y + pSize); i++)
			if (sizes[i][0] != 0)
				maxSize = sizes[i][0]=1;
		if (p.y == 0)
		for(int j = Math.max(0, p.x - pSize - 1); j < Math.min(boardWidth, p.x + pSize); j++)
			if (sizes[0][j] != 0)
				maxSize = sizes[0][j]=1;
		
		
		
		for(int i = Math.max(1, p.y - pSize - 1); i < Math.min(boardHeight, p.y + pSize); i++) {
			for (int j = Math.max(1, p.x - pSize - 1); j < Math.min(boardWidth, p.x + pSize); j++) {
				if (sizes[i][j] == 0)
					continue;
				if (!(board[i-1][j] == board[i][j] && board[i][j-1] == board[i][j] && sizes[i-1][j-1] != 0)
						|| sizes[i-1][j] == 0 || sizes[i][j-1]==0) {
					sizes[i][j] = 1;
					if (maxSize == 0)
						maxSize = 1;
					continue;
				}
				short minOfTwo = (short)Math.min(sizes[i-1][j], sizes[i][j-1]);
				sizes[i][j] = minOfTwo;
				if (board[i-minOfTwo][j-minOfTwo]==board[i][j] && sizes[i-minOfTwo][j-minOfTwo]!=0) {
					sizes[i][j]++;
					if (sizes[i][j] > maxSize)
						maxSize = sizes[i][j];
				}
			}
		}
		
		
		return maxSize;
	}
	
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		result = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1; //descending order
			}
		});
		
		final short[][] sizes = new short[boardHeight][boardWidth];
		for(int i=0; i < boardHeight; i++)
			for(int j=0; j < boardWidth; j++)
				sizes[i][j] = -1;

		short maxSize;
		
		while ((maxSize = getSizes(sizes, null)) > 0) {
			if (logger.isDebugEnabled()) {
				for (int i = 0; i < boardHeight; i++) {
					putInt(sizes[i][0]);
					for (int j = 1; j < boardWidth; j++) {
						putIntNoNewLine(sizes[i][j]);
					}
				}
				putStrNoNewLine("\n -------- \n");
			}
			
			for(int i=0; i < boardHeight; i++)
				for(int j=0; j < boardWidth; j++)
					if (sizes[i][j] == maxSize) {
						getSizes(sizes, new Point(j, i));
						addToResult(maxSize);
					}
		
		}
		
		if (logger.isDebugEnabled())
		for(int i=0; i < boardHeight; i++) {
			putInt(getCell(i, 0));
			for (int j=1; j < boardWidth; j++) {
				putIntNoNewLine(getCell(i, j));
			}
		}
		
		
	}
	

	
	private void addToResult(int size) {
		addToResult(size, 1);
	}
	
	private void addToResult(int size, int amount) {
		Integer count = this.result.get(size);
		if (count == null)
			count = amount; else count+=amount;
		this.result.put(size, count);
	}

	static int counter;
	@Override
	protected TestCase parseTestCaseInput() {
		int m = this.boardHeight = getInt();
		int n = this.boardWidth = getInt();
		logger.debug("Another testcase " + ++counter + ", m=" + m + ", n=" + n);
		this.board = new byte[m][n];
		for(int i = 0; i < m; i++) {
			String line = getStr();
			for (int j=0; j < n / 4; j++) {
				int octet = Integer.parseInt(line.substring(j, j+1), 16);
				int mask = 8;// 0b1000
				for (int k = 0; k < 4; k++) {
					setCell(i, j*4+k, (byte)(((octet & mask) / mask) ^ ((i+j*4+k) % 2)) );
					mask >>= 1;
				}
			}
		}

		return null; //hack to use local variables instead of context, no multithreading therefore
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(this.result.size());
		for(Entry<Integer, Integer> entry: this.result.entrySet()) {
			putIntInANewLine(entry.getKey(), entry.getValue());
		}
	}

}

class Point {
	public final int x,y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point))
			return false;
		Point p = (Point)obj;
		return x == p.x && y == p.y;
	}
	
	@Override
	public int hashCode() {
		return x + (y << 16);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
