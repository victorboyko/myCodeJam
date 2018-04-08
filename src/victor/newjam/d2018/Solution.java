package victor.newjam.d2018;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Solution {

	private static String d(double val) {
		return new DecimalFormat("#.################").format(val);
	}
	
	// small set solved only
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tcCount = sc.nextInt(); //throws InputMismatchException, NoSuchElementException
		for(int tcIndex = 1; tcIndex <= tcCount; tcIndex++) {
			double area = sc.nextDouble();
			double L = area; // = area / 1d;
			double a = 16d;
			double b = -8d * L;
			double c = 2d * L * L - 2d;
			double d = b * b - 4d * a * c;
			double x = (-b + Math.sqrt(d)) / (2d * a);
			double y = L / 2d - x;
			System.out.println("Case #" + tcIndex + ":");
			System.out.println(d(x) + " " + d(y) +  " " + 0);
			System.out.println(d(-y)+ " " + d(x) +  " " + 0);
			System.out.println("0 0 0.5");
		}
		System.out.flush();
	}
	
}
