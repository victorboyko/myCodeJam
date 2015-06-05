package victor.codejam.tasks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//http://code.google.com/codejam/contest/2075486/dashboard#s=p0&a=0
public class ZombieSmashTaskSolution extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	Logger logger = Logger.getLogger(ZombieSmashTaskSolution.class);
	{ logger.setLevel(Level.INFO); }
	
	public static PriorityQueue<Zombie> cloneZombies(Queue<Zombie> queue) {
		return new PriorityQueue<Zombie>(queue);
	}
	
	class Zombie implements Comparable<Zombie> {
		int x, y, time;
		public Zombie(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
		@Override
		public int compareTo(Zombie o) {
			return this.time - o.time;
		}
		
		@Override
		public String toString() {
			return "< " + time + " > (" + x + ", " + y + ")";
		}
	}
	
	int zNum;
	Queue<Zombie> zombies;
	int result;
	
	public static int getDistTime(int x1, int y1, int x2, int y2) {
		return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)) * 100;
	}
	
	static class CacheKey {
		int x, y;
		Set<Zombie> zombies;
		public CacheKey(int x, int y, Set<Zombie> zombies) {
			this.x = x;
			this.y = y;
			this.zombies = zombies;
		}
		
		@Override
		public int hashCode() {
			return zombies.hashCode() + x << 8 + y;
		}
		
		@Override
		public boolean equals(Object obj) {
			CacheKey key = (CacheKey)obj;
			return x == key.x && y == key.y && zombies.equals(key.zombies);
		}
	}
	
	static class CacheVal {
		int time, count;
		public CacheVal(int time, int count) {
			this.time = time;
			this.count = count;
		}
		
		@Override
		public int hashCode() {
			return time + count << 8;
		}
		
		@Override
		public boolean equals(Object obj) {
			CacheVal val = (CacheVal)obj;
			return time == val.time && count == val.count;
		}
	}
	
	HashMap<CacheKey, CacheVal> cache;
	
	
	private int getNextChainStep(int time, int x, int y, Queue<Zombie> zombiesSrc, boolean firstRun, int count) {
		Set<Zombie> keySet = new HashSet<Zombie>(this.zombies);
		keySet.removeAll(zombiesSrc);
		CacheKey key = new CacheKey(x, y, keySet);
		CacheVal val;
		if (cache.containsKey(key) && ( ((val = cache.get(key)).count >= count && val.time <= time) )) {
			return 0;
		} else {
			cache.put(key, new CacheVal(time, count));
		}
		
		int result = 0;
		Queue<Zombie> aliveZombies = cloneZombies(zombiesSrc);
		if (firstRun) {
			while(!aliveZombies.isEmpty() && aliveZombies.peek().time + 1000 < time) {
				aliveZombies.remove();
			}
			while(!aliveZombies.isEmpty() && aliveZombies.peek().time + 1000 <
					time + Math.max(getDistTime(x, y, aliveZombies.peek().x, aliveZombies.peek().y), !firstRun ? 750 : 0)) {
				aliveZombies.remove();
			}
		}
		
		Queue<Zombie> zombies = cloneZombies(aliveZombies);

		out:
		while(!zombies.isEmpty()) {
			Zombie z1 = zombies.poll();
			
			int timeZombieSmash = time + Math.max(getDistTime(x, y, z1.x, z1.y), !firstRun ? 750 : 0);
			timeZombieSmash = Math.max(timeZombieSmash, z1.time);
			
			if ( timeZombieSmash <= z1.time + 1000) {
				
				
				Queue<Zombie> innerZombies = cloneZombies(aliveZombies);
				while(!innerZombies.isEmpty()) {
					Zombie z2 = innerZombies.poll();
					
					if (z2.time > timeZombieSmash)
						break;
					
					int timeZ2Smash = time + Math.max(getDistTime(x, y, z2.x, z2.y), !firstRun ? 750 : 0);
					timeZ2Smash = Math.max(timeZ2Smash, z2.time);
					
					int timeZ2Z1Smash = timeZ2Smash + Math.max(getDistTime(z2.x, z2.y, z1.x, z1.y), 750);
					timeZ2Z1Smash = Math.max(timeZ2Z1Smash, z1.time);
					
					if (timeZombieSmash >= timeZ2Z1Smash && z2!=z1) {
						continue out;
					}
				}
				
				
				Queue<Zombie> destZombies = cloneZombies(aliveZombies);
				destZombies.remove(z1);
				while(!destZombies.isEmpty() && destZombies.peek().time + 1000 < timeZombieSmash) {
					destZombies.remove();
				}
				while(!destZombies.isEmpty() && destZombies.peek().time + 1000 <
						timeZombieSmash + Math.max(getDistTime(z1.x, z1.y, destZombies.peek().x, destZombies.peek().y), 750)) {
					destZombies.remove();
				}
				
 				result = Math.max(result, 1 + getNextChainStep(timeZombieSmash, z1.x, z1.y, destZombies, false, count + 1));
 				if (result == zombiesSrc.size())
 					return result;
			}
		}
		
		return result;
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		cache = new HashMap<CacheKey, CacheVal>();
		
		if (logger.isDebugEnabled()) {
			Queue<Zombie> c_zombies = cloneZombies(zombies);
			logger.debug(c_zombies.size());
			while (!c_zombies.isEmpty())
				logger.debug(c_zombies.poll());

		}
		
		result = getNextChainStep(0, 0, 0, zombies, true, 0);
		
	}

	@Override
	protected TestCase parseTestCaseInput() {
		zNum = getInt();
		zombies = new PriorityQueue<Zombie>();
		for(int i = 0; i < zNum; i++) {
			zombies.add(new Zombie(getInt(), getInt(), getInt())); 
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
