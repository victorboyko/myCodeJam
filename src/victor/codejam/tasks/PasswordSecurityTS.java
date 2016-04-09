package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class PasswordSecurityTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	int N;
	String result;
	List<String> passwords;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = null;
		
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Set<Character>[] possibleChars = new Set[alphabet.length()];
		for(int i = 0; i < possibleChars.length; i++) {
			possibleChars[i] = new HashSet<Character>();
			for(char c : alphabet.toCharArray()) {
				possibleChars[i].add(c);
			}
		}
		
		out:
		for(int i = 0; i < passwords.size(); i++) {
			String password = passwords.get(i);
			for(char c : alphabet.toCharArray()) {
				int cnt = 0;
				for (char c2 : password.toCharArray()) {
					if (c == c2) cnt++;
				}
				if (cnt > 1) {
					passwords.remove(i--);
					break out;
				}
			}
		}
		
		
		//TODO - revise algorithm
		for(String password : passwords) {
			for(int i = 0; i <= alphabet.length() - password.length(); i++) {
				boolean fits = true;
				for(int j = 0; j < password.length(); j++) {
					fits &= possibleChars[i+j].contains(password.charAt(j)); 
				}
				if (fits) {
					for(int j = 0; j < password.length(); j++) {
						possibleChars[i+j].remove(password.charAt(j));
					}
				}
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < possibleChars.length; i++) {
			if (possibleChars[i].isEmpty()) return;
			char c = possibleChars[i].iterator().next();
			sb.append(c);
			for(int j = i + 1; j < possibleChars.length; j++) {
				possibleChars[j].remove(c);
			}
		}
		result = sb.toString();

	}

	@Override
	protected TestCase parseTestCaseInput() {
		N = getInt();
		passwords = new ArrayList<String>(N);
		String pwStr = getStr();
		for(int i = 0; i < N; i++) {
			passwords.add(pwStr.split(" ")[i]);
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(result == null ? "IMPOSSIBLE" : result);
	}

}
