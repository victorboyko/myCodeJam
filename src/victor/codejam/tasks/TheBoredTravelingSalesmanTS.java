package victor.codejam.tasks;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class TheBoredTravelingSalesmanTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	String result;
	LinkedList<City> cities;
	int citiesNum;
	
	
	class City implements Comparable<City> {
		final int code;
		boolean visited = false;
		LinkedList<City> in = new LinkedList<TheBoredTravelingSalesmanTS.City>();
		LinkedList<City> out = new LinkedList<TheBoredTravelingSalesmanTS.City>();
		
		public City(int code) {
			this.code = code;
		}
		
		@Override
		public int compareTo(City o) {
			return Integer.valueOf(code).compareTo(o.code);
		}
		
		@Override
		public String toString() {
			return "[" + code + "]";
		}
	}
	
	LinkedList<City> copyCities() {
		LinkedList<City> result = new LinkedList<TheBoredTravelingSalesmanTS.City>();
		
		Map<Integer, Integer> cMap = new HashMap<Integer, Integer>();
		for(int i = 0; i < citiesNum; i++) {
			cMap.put(cities.get(i).code, i);
		}
		
		for(int i = 0; i < citiesNum; i++) {
			result.add(new City(cities.get(i).code));
		}
		
		for(int i = 0; i < citiesNum; i++) {
			City city = cities.get(i);
			City cDest = result.get(cMap.get(city.code));
			for(City c2 : city.out) {
				int index = cMap.get(c2.code);
				City c2Dest = result.get(index);
				cDest.out.add(c2Dest);
			}
			for(City c2 : city.in) {
				int index = cMap.get(c2.code);
				City c2Dest = result.get(index);
				cDest.in.add(c2Dest);
			}
		}
		
		return result;
	}
	
	private boolean allVistied() {
		for(City city: cities) {
			if (!city.visited)
				return false;
		}
		return true;
	}

	int getBestIn(City city) {
		int result = 10000;
		if (!city.visited ) {
			return city.code;
		}
		
		if (!city.out.isEmpty()) {
			result = city.out.peek().code;
		}
		
		for(City c : city.in) {
			if (c == city) {
				// TODO result = Math.min(arg0, arg1)
						//TODO
			}
		}

		return result;
	}
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		for(City city : cities) {
			Collections.sort(city.in);
			Collections.sort(city.out);
		}
		Collections.sort(cities);
		
		City cur = cities.poll();
		cur.visited = true;
		result = "" + cur.code;
		while (!allVistied()) {
			
			
		}

	}

	@Override
	protected TestCase parseTestCaseInput() {
		citiesNum = getInt();
		int linksNum = getInt();
		cities = new LinkedList<TheBoredTravelingSalesmanTS.City>();
		for(int i = 0; i < citiesNum; i++) {
			City city = new City(getInt());
			cities.add(city);
		}
		for(int i = 0; i < linksNum; i++) {
			City a = cities.get(getInt());
			City b = cities.get(getInt());
			a.out.add(b);
			b.in.add(a);
		}
		
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(result);
	}

}
