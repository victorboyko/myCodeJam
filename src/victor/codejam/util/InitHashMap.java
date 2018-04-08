package victor.codejam.util;

import java.util.HashMap;

public class InitHashMap<K, V> extends HashMap<K, V> {
	
	private V initVal;
	public InitHashMap(V initVal) {
		this.initVal = initVal;
	}
	
	@Override
	public V get(Object key) {
		if (!this.containsKey(key)) {
			this.put((K)key, initVal);
		}
		return super.get(key);
	}

}
