package victor.newjam.c2018;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	
	static int compressCoordinates(int y, int x) {
		return (x << 10) + y;
	}
	
	static int getX(int compressedCoodinates) {
		return compressedCoodinates >> 10;
	}
	
	static int getY(int compressedCoodinates) {
		return compressedCoodinates & 0b1111111111;
	}
	
	static int getPreparedFrom3x3(Set<Integer> prepared, int topY, int leftX) {
		int result = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if (prepared.contains(compressCoordinates(topY+i, leftX+j))) {
					result++;
				}
			}
		}
		return result;
	}
	
	static int getMinPrepared3x3(Set<Integer> prepared, int topY, int leftX, int height, int width) {
		int resultPrepared = getPreparedFrom3x3(prepared, topY, leftX); // all 3x3 prepared
		int resultCoordinates = compressCoordinates(topY, leftX);
		for(int i = 0; i <= height - 3; i++) {
			for(int j = 0; j <= width - 3; j++) {
				int preparedValue = getPreparedFrom3x3(prepared, topY+i, leftX+j);
				if (preparedValue < resultPrepared) {
					resultPrepared = preparedValue;
					resultCoordinates = compressCoordinates(topY+i, leftX+j);
				}
			}
		}
		return resultCoordinates;
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tcCount = sc.nextInt(); //throws InputMismatchException, NoSuchElementException
		int errorCoordinates = compressCoordinates(-1, -1);
		int finishCoordinates = compressCoordinates(0, 0);
		nextTest:
		for(int tcIndex = 1; tcIndex <= tcCount; tcIndex++) {
			int area = sc.nextInt();
			int width = (int)Math.floor(Math.sqrt(area));
			int height = (area % width == 0) ? area / width : ((area / width) + 1);
			int x = ((999-2-width)/2 ) / 100 * 100; // % 100 for debug simplicity
			int y = ((999-2-height)/2) / 100 * 100;
			Set<Integer> prepared = new HashSet<>();
			//while(prepared.size() < width * height) {
			
			while(true) {
				int coordinates = getMinPrepared3x3(prepared, y, x, height, width);
				System.out.printf("%d %d\n",getX(coordinates)+1, getY(coordinates)+1);
				System.out.flush();
				int xGopher = sc.nextInt();
				int yGopher = sc.nextInt();
				coordinates = compressCoordinates(yGopher, xGopher);
				if (coordinates == errorCoordinates) {
					return;
				}
				if (coordinates == finishCoordinates) {
					continue nextTest;
				}
				prepared.add(coordinates);
			}
			
			
		}
	}

}
