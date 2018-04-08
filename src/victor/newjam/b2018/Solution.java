package victor.newjam.b2018;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tcCount = sc.nextInt(); //throws InputMismatchException, NoSuchElementException
		for(int tcIndex = 1; tcIndex <= tcCount; tcIndex++) {
			int len = sc.nextInt();
			if (len < 3) throw new IllegalStateException();
			int array[] = new int[len];
			for(int i = 0; i < len; i++) {
				array[i] = sc.nextInt();
			}
						
			List<Integer> even = new LinkedList<Integer>();
			List<Integer> odd = new LinkedList<>();
			
			for(int i = 0; i < len; i++) {
				((i % 2 == 0) ? even : odd).add(array[i]); 
			}
			
			Integer result = null;			

			int min = Collections.min(even);
			even.remove(Integer.valueOf(min));
			for(int i = 1; i < len; i++){
				List<Integer> list = (i % 2 == 0) ? even : odd;
				int nextMin = Collections.min(list);
				list.remove(Integer.valueOf(nextMin));
				if (nextMin < min) {
					result = i - 1;
					break;
				}
				min = nextMin;
			}
			
			System.out.printf("Case #%d: %s\n", tcIndex, (result == null) ? "OK" : result);
		}
		System.out.flush();
	}	
	
}
