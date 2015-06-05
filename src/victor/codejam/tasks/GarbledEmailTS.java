package victor.codejam.tasks;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.FileHelper;
import victor.codejam.NoTestCasePreload;

//TODO
//http://code.google.com/codejam/contest/2434486/dashboard#s=p2
public class GarbledEmailTS extends AbstractTaskSolution implements	NoTestCasePreload {

	Set<String> dictionary;
	String letter;
	int result;
	
//	MAX_LEN = 0;
//	for (String s : dictionary) {
//		if (MAX_LEN < s.length())
//			MAX_LEN = s.length();
//	}
	private static final int MAX_LEN = 10;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = getCount(letter, 0, 0);
	}
	
	private int getCount(String inStr, int index, int recCnt) {
		if (inStr.length() == 0)
			return 0;
		int result = Integer.MAX_VALUE;
		for(int i = Math.min(MAX_LEN-1, inStr.length()-1); i >= 0 ; i--) {
			if (recCnt == 0) {
				recCnt = recCnt;
			}
			String s = inStr.substring(0, i+1);
			if (dictionary.contains(s))
				result = getCount(inStr.substring(i), 0, recCnt+1);
			for(int j = index; j < s.length(); j++) {
				for(char c = 'a'; c <= 'z'; c++) {
					String s1 = s.substring(0, j) + c + s.substring(j+1);
					if (dictionary.contains(s1)) {
						int subres = 1 + getCount(inStr.substring(i+1), Math.min(0, 5 - (i - j)), recCnt+1);
						if (subres < result)
							result = subres;
					}
					for(int k = j + 5; k < s.length(); k++) {
						for(char c2 = 'a'; c <= 'z'; c++) {
							String s2 = s1.substring(0, j) + c2 + s1.substring(j+1);
							if (dictionary.contains(s2)) {
								int subres = 2 + getCount(inStr.substring(i+1), Math.min(0, 5 - (i - k)), recCnt+1);
								if (subres < result)
									result = subres;
							}
						}
							
					}
					
				}
			}
		}
		
		return result;
	}

	boolean inited = false;
	@Override
	protected TestCase parseTestCaseInput() {
		if (!inited) {
			//File f = new File("https://code.google.com/codejam/contest/static/garbled_email_dictionary.txt");
			File f = new File("/Users/Victor/Downloads/garbled_email_dictionary.txt");
			dictionary = new HashSet<String>( FileHelper.readFromFile(f) );
			inited = true;
		}
		letter = getStr();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
