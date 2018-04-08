package victor.codejam.util;

public class IntHashMap<K> extends InitHashMap<K, Integer> {

	
	public IntHashMap(Integer initVal) {
		super(initVal);
	}
	
	public IntHashMap() {
		super(0);
	}
	
	public Integer inc(K k) {
		Integer result;
		this.put(k, result = (this.get(k) + 1));
		return result;
	}
}
