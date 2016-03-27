package victor.codejam.tasks;

import java.util.HashMap;
import java.util.Map;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class AlwaysTurnLeftTS extends AbstractTaskSolution implements
		NoTestCasePreload {

	private String s1, s2;
	
	byte[][] field;
	
	final static byte LEFT 	 = 1;
	final static byte TOP	 = 2;
	final static byte RIGHT	 = 4;
	final static byte BOTTOM = 8;
	
	int x, y, xMin, xMax, yMax;
	int dir;
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		field = new byte[10001][19999];
		y = 0;
		x = 10000;
		xMin = xMax = x;
		yMax = 1;
		dir = BOTTOM;
		
		field[y][x] |= TOP;
		
		for(char c : s1.concat("CLL").concat(s2).toCharArray()) {
			switch (c) {
			case 'W':
				switch (dir) {
				case BOTTOM:
					field[y][x] |= BOTTOM;
					y++;
					if (y > yMax) yMax = y;
					field[y][x] |= TOP;
					break;
				case TOP:
					field[y][x] |= TOP;
					y--;
					field[y][x] |= BOTTOM;
					break;
				case LEFT:
					field[y][x] |= LEFT;
					x--;
					if (xMin > x) xMin = x;
					field[y][x] |= RIGHT;
					break;
				case RIGHT:
					field[y][x] |= RIGHT;
					x++;
					if (xMax < x) xMax = x;
					field[y][x] |= LEFT;
					break;
				default:
					throw new RuntimeException("unexpected direction: " + dir);
				}
				break;
			case 'L':
				if (dir == 1) dir = 8; else dir /= 2;
				break;
			case 'R':
				if (dir == 8) dir = 1; else dir *= 2;
				break;
			case 'C':
				switch (dir) {
					case BOTTOM:
						yMax--;
						break;
					case LEFT:
						xMin++;
						break;
					case RIGHT:
						xMax--;
						break;
				}
				break;
			default:
				throw new RuntimeException("unexpected input: " + c);
			}
		}
		
	}

	@Override
	protected TestCase parseTestCaseInput() {
		String s = getStr();
		s1 = s.split(" ")[0];
		s2 = s.split(" ")[1];
		return null;
	}

	final static Map<Integer, Character> outMap = new HashMap<Integer, Character>();
	static {
		outMap.put(0, '•'); // error!
		
		outMap.put(0 | TOP, '1');
		outMap.put(0 | BOTTOM, '2');
		outMap.put(0 | TOP | BOTTOM, '3');
		outMap.put(0 | LEFT, '4');
		outMap.put(0 | TOP | LEFT, '5');
		outMap.put(0 | BOTTOM | LEFT, '6');
		outMap.put(0 | TOP | BOTTOM | LEFT, '7');
		outMap.put(0 | RIGHT, '8');
		outMap.put(0 | TOP | RIGHT, '9');
		outMap.put(0 | BOTTOM | RIGHT, 'a');
		outMap.put(0 | TOP | BOTTOM | RIGHT, 'b');
		outMap.put(0 | LEFT | RIGHT, 'c');
		outMap.put(0 | TOP | LEFT | RIGHT, 'd');
		outMap.put(0 | BOTTOM | LEFT | RIGHT, 'e');
		outMap.put(0 | TOP | BOTTOM | LEFT | RIGHT, 'f');
	}
	
	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine("\n");
		for(int i = 1; i <= yMax; i++) {
			StringBuffer sb = new StringBuffer();
			for(int j = xMin; j <= xMax; j++) {
				sb.append(outMap.get((int)field[i][j]));
			}
			putStrNoNewLine(sb.append("\n").toString());
		}

	}
	
	

}
