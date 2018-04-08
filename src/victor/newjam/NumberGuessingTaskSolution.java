package victor.newjam;

import java.util.Scanner;
import java.io.PrintWriter;

public class NumberGuessingTaskSolution {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        if (t < 1) {
        	System.err.println("wrong test case count");
        	return;
        }
        for(int tc = 1; tc <= t; tc++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a < 0 | b > 1000000000) {
            	System.err.println("wrong range");
                continue;
            }
            if (b <= a) {
            	System.err.println("(a,b] cannot be empty, so a > b expected");
            	continue;
            }
            
            do {
                int guess = (a + b + 1)/2;
                System.out.println(guess);
                System.out.flush();
                String response = sc.next();
                if ("WRONG_ANSWER".equals(response)) {
                	System.err.println("wrong answer");
                	continue;
                }
                if ("CORRECT".equals(response)) {
                    return; // success
                }
                if ("TOO_BIG".equals(response)) {
                    b = guess - 1;
                } else {
                    if ("TOO_SMALL".equals(response)) {
                        a = guess;
                    }
                }
            } while (true);
            
        }
        
     
    
       
    }
    
    
    
}