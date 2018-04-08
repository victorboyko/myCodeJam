package victor.newjam.c2018;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolutionTest {

	@Test 
	public void testCompressDecompressMatch() {
		int x = 123;
		int y = 456;
		int compressed = Solution.compressCoordinates(y, x);
		assertEquals(y, Solution.getY(compressed));
		assertEquals(x, Solution.getX(compressed));
	}

//  Does not work with negatives	
//	
//	@Test
//	public void testCompressDecompressMatchNegative() {
//		int x = -1;
//		int y = -1;
//		int compressed = Solution.compressCoordinates(y, x);
//		assertEquals(y, Solution.getY(compressed));
//		assertEquals(x, Solution.getX(compressed));
//	}
	
}
