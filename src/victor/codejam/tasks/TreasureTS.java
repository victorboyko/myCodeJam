package victor.codejam.tasks;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//http://code.google.com/codejam/contest/2270488/dashboard#s=p3
public class TreasureTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	int chestsNum;
	Map<Integer, Integer> keys;
	List<Chest> chests;
	List<Integer> result;
	
	class Chest {
		int index;
		int key;
		Map<Integer, Integer> keys;
		
		public Chest(int key, int index) {
			this.key = key;
			this.index = index;
			keys = new TreeMap<Integer, Integer>();
		}
		
		//increasing safety
		public void freezeKeys() {
			keys = Collections.unmodifiableMap(keys);
		}
		
		@Override
		public String toString() {
			return "(" + index + ")[" + key + ":" + keys + "]";
		}
	}
	
	
	private List<Integer> getSequence(List<Chest> chests, Map<Integer, Integer> keys) {
		if (cache.containsKey(chests)) {
			Map<Integer, Integer> cacheVal = cache.get(chests);
			boolean covers = cacheVal.keySet().containsAll(keys.keySet());
			
/*			if (covers) // seems this check is not needed :-/
				for(Integer key : keys.keySet()) {
					if (cacheVal.get(key) < keys.get(key)) {
						covers = false;
						break;
					}
				}*/
			if (covers)
				return null;
		} else {
			cache.put(chests, keys);
		}
		
		
		boolean none = true;
		for(Chest chest: chests)
			if (keys.containsKey(chest.key)) {
				none = false;
				break;
			}
		if (none)
			return null;
		if (chests.size() == 1) {
			List<Integer> result = new LinkedList<Integer>();
			result.add(chests.get(0).index);
			return result;
		}
		
		
		//check dead locks
//		List<Chest> chestsCpy = new LinkedList<Chest>(chests);
//		Set<Integer> accessibleKeys = new HashSet<Integer>(keys.keySet());
//		boolean proceed;
//		do {
//			proceed = false;
//			Iterator<Chest> it = chestsCpy.iterator();
//			while(it.hasNext()) {
//				Chest chest = it.next();
//				if (proceed |= accessibleKeys.contains(chest.key)) {
//					accessibleKeys.addAll(chest.keys.keySet());
//					it.remove();
//				}
//			}
//		} while (proceed);
//		if (chestsCpy.size() != 0) {
//			return null;
//		}
//		Map<Integer, Integer> kExist = new HashMap<Integer, Integer>(keys);
//		Map<Integer, Integer> kNeeded = new HashMap<Integer, Integer>();
//		for(Chest c: chests) {
//			kNeeded.put(c.key, kNeeded.containsKey(c.key) ? kNeeded.get(c.key) + 1 : 1);
//			for(Entry<Integer, Integer> e : c.keys.entrySet()) {
//				kExist.put(e.getKey(), kExist.containsKey(e.getKey()) ? kExist.get(e.getKey()) + e.getValue() : e.getValue());
//			}
//		}
//		for(Entry<Integer, Integer> e : kNeeded.entrySet()) {
//			if (!kExist.containsKey(e.getKey()) || e.getValue() < kExist.get(e.getKey())) {
//				return null;
//			}
//		}
		
		
		for(Chest chest : chests) {
			if (keys.containsKey(chest.key)) {
				List<Chest> restOfChests = new LinkedList<Chest>(chests);
				restOfChests.remove(chest);
				Map<Integer, Integer> restOfKeys = new HashMap<Integer, Integer>(keys);
				for(Integer key : chest.keys.keySet()) {
					restOfKeys.put(key, restOfKeys.containsKey(key) 
							? restOfKeys.get(key) + chest.keys.get(key): chest.keys.get(key));
				}
				int keysNum = restOfKeys.get(chest.key);
				if (keysNum == 1)
					restOfKeys.remove(chest.key);
				else restOfKeys.put(chest.key, keysNum - 1);
				
				List<Integer> subResult = getSequence(restOfChests, restOfKeys);
				if (subResult != null) {
					subResult.add(0, chest.index);
					return subResult;
				}
			}
			
		}
		
		return null;
	}

	Map<List<Chest>, Map<Integer, Integer>> cache;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		cache = new HashMap<List<Chest>, Map<Integer,Integer>>();
		
		//check dead locks
		List<Chest> chestsCpy = new LinkedList<Chest>(chests);
		Set<Integer> accessibleKeys = new HashSet<Integer>(keys.keySet());
		boolean proceed;
		do {
			proceed = false;
			Iterator<Chest> it = chestsCpy.iterator();
			while(it.hasNext()) {
				Chest chest = it.next();
				if (proceed |= accessibleKeys.contains(chest.key)) {
					accessibleKeys.addAll(chest.keys.keySet());
					it.remove();
				}
			}
		} while (proceed);
		if (chestsCpy.size() != 0) {
			System.out.println("info: preliminary possible check didn't pass");
			result = null;
			return;
		}
		Map<Integer, Integer> kExist = new HashMap<Integer, Integer>(keys);
		Map<Integer, Integer> kNeeded = new HashMap<Integer, Integer>();
		for(Chest c: chests) {
			kNeeded.put(c.key, kNeeded.containsKey(c.key) ? kNeeded.get(c.key) + 1 : 1);
			for(Entry<Integer, Integer> e : c.keys.entrySet()) {
				kExist.put(e.getKey(), kExist.containsKey(e.getKey()) ? kExist.get(e.getKey()) + e.getValue() : e.getValue());
			}
		}
		for(Entry<Integer, Integer> e : kNeeded.entrySet()) {
			if (!kExist.containsKey(e.getKey()) || e.getValue() < kExist.get(e.getKey())) {
				result = null;
				return;
			}
		}
		
		result = getSequence(chests, keys);
	}
	
	int tastcase_count = 0;

	@Override
	protected TestCase parseTestCaseInput() {
		System.out.println(++tastcase_count);
		int keyNum = getInt();
		chestsNum = getInt();
		keys = new TreeMap<Integer, Integer>();
		for(int i = 0; i < keyNum; i++) {
			int key = getInt();
			keys.put(key, keys.containsKey(key) ? keys.get(key) + 1 : 1);
		}
		chests = new LinkedList<Chest>();
		for(int i = 0; i < chestsNum; i++) {
			Chest c = new Chest(getInt(), i+1);
			int keysNum = getInt();
			for (int j = 0; j < keysNum; j++) {
				int key = getInt();
				c.keys.put(key, c.keys.containsKey(key) ? c.keys.get(key) + 1 : 1);
			}
			c.freezeKeys();
			chests.add(c);
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		if (result == null)
			putStrNoNewLine("IMPOSSIBLE");
		else 
			putInASameLine(result.toArray());
	}

}
