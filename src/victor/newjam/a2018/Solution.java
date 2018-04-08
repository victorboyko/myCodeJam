package victor.newjam.a2018;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tcCount = sc.nextInt(); //throws InputMismatchException, NoSuchElementException
		for(int tcIndex = 1; tcIndex <= tcCount; tcIndex++) {
			int shield = sc.nextInt();
			String program = sc.next();
			char[] programChars = program.toCharArray();
			int strength = 1;
			int[] stregths = new int[programChars.length];
			int damage = 0;
			for(int i = 0; i < programChars.length; i++) {
				stregths[i] = strength;
				char command = programChars[i];
				switch (command) {
				case 'C':
					strength <<= 1;
					break;
				case 'S':
					damage += strength;
					break;
				default:
					throw new IllegalStateException("unexpected command " + command);
				}
			}
			int result = 0;
			for(int i =0; i < programChars.length-1 && damage > shield; i++) {
				if (programChars[i] == 'C' && programChars[i+1] == 'S') {
					damage -= stregths[i];
					programChars[i] = 'S';
					programChars[i+1] = 'C';
					stregths[i+1]=stregths[i];
					result++;
					i -= 2;
					if (i < 0) {
						i = 0;
					}
				}
			}
			
			
			System.out.printf("Case #%d: %s\n", tcIndex, (damage > shield) ? "IMPOSSIBLE" : result);
		}
		System.out.flush();
	}
	
}
