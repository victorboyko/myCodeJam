package victor.codejam;

public class Util {

	private static short[][] cloneArray(short[][] src, short[][] target) {
	    int length = src.length;
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}
	
	public static short[][] cloneArray(short[][] src) {
	    int length = src.length;
	    short[][] target = new short[length][src[0].length];
	    return cloneArray(src, target);
	}
	
	public static boolean compareArrays(short[][] src, short[][] dst) {
		if (src.length != dst.length)
			throw new IllegalArgumentException();
		for(int i = 0; i < src.length;i++) {
			if (src[i].length != dst[i].length)
				throw new IllegalArgumentException();
			for(int j = 0; j < src[i].length; j++)
				if (src[i][j] != (dst[i][j]))
					return false;
		}
		return true;
	}
	
}
