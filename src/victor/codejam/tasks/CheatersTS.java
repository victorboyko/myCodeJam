package victor.codejam.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//TODO - not ready
//http://code.google.com/codejam/contest/2433487/dashboard#s=p0
public class CheatersTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	private double result;
	
	private final Map<Long, Integer> bets = new TreeMap<Long, Integer>();;
	private long budjet;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0d;
		
		List<Map.Entry<Long,Integer>> betEntries = new ArrayList<Map.Entry<Long,Integer>>(bets.entrySet());
		List<Map.Entry<Long,Integer>> es = betEntries;
		es.add(0, new Map.Entry<Long, Integer>() {
			@Override
			public Long getKey() {
				return Long.valueOf(0L);
			}
			
			@Override
			public Integer getValue() {
				return 0;
			}
			
			@Override
			public boolean equals(Object obj) {
				return obj == this;
			}
			
			@Override
			public Integer setValue(Integer value) {
				throw new IllegalStateException();
			}
		});
		
		out:
		for(int i = 0; i < betEntries.size(); i++) {
			long betsNeeded = 0;
			int aheadsNum = 0;
			for(int j = 1; j < i + 1; j++) { //TODO
				betsNeeded += es.get(i - 1).getValue() * (es.get(j).getKey() - es.get(0).getKey());
				aheadsNum += es.get(j).getValue();
				if (aheadsNum + es.get(0).getValue() >= 35)
					break out;
			}
			if (budjet < betsNeeded)
				break;
			
			long addBet = (budjet - betsNeeded) / aheadsNum;
			if (i < betEntries.size() - 1) {
				addBet = Math.min(addBet, es.get(i + 1).getKey());
			}
			int count = aheadsNum + es.get(0).getValue();
			long spent = betsNeeded + addBet * count;
			double gained = 0;
			for(int j = 0; j <= i; j++) {
				//TODO
			}
			
		}
		
		//TODO
		
	}

	@Override
	protected TestCase parseTestCaseInput() {
		budjet = getLong();
		bets.clear(); 
		int betsCnt = getInt();
		for(int i = 0; i < betsCnt; i++) {
			Long bet = getLong();
			bets.put(bet, (!bets.containsKey(bet) ? 0 : bets.get(bet)) + 1);
		}
		if (betsCnt < 37) {
			bets.put(0L, 37 - betsCnt);
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putDblNoNewLine(result);
	}

}
