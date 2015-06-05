package victor.codejam.tasks;

import java.util.LinkedList;
import java.util.List;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class SortAScrambledItineraryTS extends AbstractTaskSolution implements
		NoTestCasePreload {
	
	class TicketChain {
		private String begin, end, body;
		
		public String getBegin() {
			return begin;
		}
		
		public String getEnd() {
			return end;
		}
		
		public TicketChain(String begin, String end) {
			this.begin = begin;
			this.end = end;
			body = begin + "-" + end;
		}
		
		void append(TicketChain chain) {
			body += " " + chain.body;
			end = chain.getEnd();
		}
		
		void putPrefix(TicketChain chain) {
			body = chain.body + " " + body;
			begin = chain.begin;
		}
		
		@Override
		public String toString() {
			return body;
		}
	}
	
	List<TicketChain> chains = new LinkedList<TicketChain>();

	@Override
	protected void executeSolutionBody(TestCase testCase) {
		TicketChain c1 = chains.get(0);
		
		while (chains.size() > 1) {
			for(int i = 1; i < chains.size(); i++) {
				TicketChain c2 = chains.get(i);
				if (c1.end.equals(c2.begin)) {
					c1.append(c2);
					chains.remove(i--);
				} else if (c1.begin.equals(c2.end)) {
					c1.putPrefix(c2);
					chains.remove(i--);
				}
			}
		}

	}

	@Override
	protected TestCase parseTestCaseInput() {
		chains.clear();
		for(int i = getInt(); i>0; i--) {
			chains.add(new TicketChain(getStr(), getStr()));
		}
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putStrNoNewLine(chains.get(0).toString());
	}

}
