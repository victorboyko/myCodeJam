package victor.codejam;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class AbstractTaskSolution implements TaskSolution {

	private LinkedList<String> input;
	protected int testCasesNum;
	protected Queue<TestCase> testCases;

	protected boolean isInputEmpty() {
		return input.isEmpty();
	}

	@Override
	public List<String> processAndReply(List<String> input) {
		this.input = new LinkedList<String>(input);
		this.testCasesNum = getInt(); // Caution! Probably can be not common
		this.testCases = new LinkedList<AbstractTaskSolution.TestCase>();
		if (!(this instanceof NoTestCasePreload))
			for (int i = 0; i < testCasesNum; i++) {
				testCases.add(parseTestCaseInput());
			}
		for (int i = 0; i < testCasesNum; i++) {
			if (this instanceof NoTestCasePreload)
				testCases.add(parseTestCaseInput());
			executeSolutionBody(testCases.peek());
			packOutput(testCases.poll(), i + 1);
		}
		return output;
	}

	abstract protected void executeSolutionBody(TestCase testCase);

	abstract protected TestCase parseTestCaseInput();

	protected LinkedList<String> output = new LinkedList<String>();

	private void packOutput(TestCase testCase, int index) {
		output.add("Case #" + index + ":");
		packOutputNoPrefix(testCase);
	}

	// use 'output' property, start with "..NoNewLine" method
	abstract protected void packOutputNoPrefix(TestCase testCase);

	// gets next int from input
	protected int getInt() {
		return Integer.parseInt(getLongStr());
	}
	
	private String getLongStr() {
		String string = input.poll();
		if (string.contains(" ")) {
			int beginIndex = string.indexOf(" ");
			String rest = string.substring(beginIndex + 1); // 1 - the length of
															// space
															// delimiter" "
			input.addFirst(rest);
			string = string.substring(0, beginIndex);
		}
		return string;
	}

	
	protected long getLong() {
		return Long.parseLong(getLongStr());
	}

	protected double getDouble() {
		return Double.parseDouble(getLongStr());
	}
	
	protected String getStr() {
		return input.poll();
	}

	protected void putInt(int intValue) {
		output.add(String.valueOf(intValue));
	}

	protected void putStrNoNewLine(String str) {
		output.add(output.pollLast() + " " + str);
	}

	//! it accepts long
	protected void putIntNoNewLine(long intValue) {
		output.add(output.pollLast() + " " + intValue);
	}

	protected void putDblNoNewLine(double dblValue) {
		output.add(output.pollLast() + " " + dblValue);
	}
	
	protected void putDblNoNewLine(double dblValue, int digitsAfterDot) {
		output.add(output.pollLast() + " " + formatDouble(dblValue, digitsAfterDot));
	}

	private static String formatDouble(double d, int digitsNum) {
		String s = String.format("%."+digitsNum+"f", d);
		if (!s.endsWith("0"))
			return s;
		int i;
		for(i = s.length()-1; s.charAt(i) == '0'; i--) { }
		return s.charAt(i) == '.' ? s.substring(0, i+2) : s.substring(0, i+1);
	}
	
	protected void putInASameLine(Object[] values) {
		putInASameLine(values, " ");
	}
	
	
	protected void putInASameLine(Object[] values, String separator) {
		StringBuilder lineBuilder = new StringBuilder(
				String.valueOf(values[0]));
		for (int i = 1; i < values.length; i++) {
			lineBuilder.append(separator).append(values[i]);
		}
		output.add(output.pollLast() + " " + lineBuilder);
	}
	
	protected void putIntInANewLine(int... intValues) {
		StringBuilder lineBuilder = new StringBuilder(
				String.valueOf(intValues[0]));
		for (int i = 1; i < intValues.length; i++) {
			lineBuilder.append(" " + intValues[i]);
		}
		output.add(lineBuilder.toString());
	}

	public interface TestCase {
	};

}
