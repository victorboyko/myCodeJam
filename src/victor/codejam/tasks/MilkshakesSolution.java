package victor.codejam.tasks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//http://code.google.com/codejam/contest/32016/dashboard#s=p1
public class MilkshakesSolution extends AbstractTaskSolution implements NoTestCasePreload {

	private final static Logger logger = Logger.getLogger(MilkshakesSolution.class);
	{ logger.setLevel(Level.INFO); }
	
	private int nFlavours;
	private List<Customer> customers;
	private boolean[] result;
	
	class Customer {
		Set<Integer> malted = new HashSet<Integer>();
		Set<Integer> notMalted = new HashSet<Integer>();
		void add(int flavour, boolean malted) {
			if (malted)
				this.malted.add(flavour);
			else this.notMalted.add(flavour);
		}
	}
	 
	private static boolean checkAllSatisfied(List<Customer> customers, boolean[] malted) {
		List<Customer> customersCopy = new LinkedList<Customer>(customers);
		for(int i = 0; i < malted.length; i++) {
			Iterator<Customer> it = customersCopy.iterator();
			while(it.hasNext()) {
				Customer customer = it.next();
				if (!malted[i] && customer.notMalted.contains(i) ||
						malted[i] && customer.malted.contains(i))
					it.remove();
			}
		}
		return customersCopy.isEmpty();
	}
	
	private static boolean isSatisfied(Customer customer, boolean[] malted) {
		return checkAllSatisfied(Arrays.asList(customer), malted);
	}
	
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		
		boolean[] malted = new boolean[nFlavours];
		
		while (!checkAllSatisfied(customers, malted)) {
			out:
			for(Customer customer: customers)
				if (!isSatisfied(customer, malted)) {
					if (customer.malted.size() == 0) {
						result = null;
						return;
					} else {
						malted[customer.malted.iterator().next()] = true;
						break out;
					}
					
				}
		}
		
		result = malted;
		
	}
	
	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		if (result == null)
			putStrNoNewLine("IMPOSSIBLE");
		else
			for(int i=0; i<nFlavours; i++)
				putIntNoNewLine(result[i] ? 1 : 0);
	}
	
	@Override
	protected TestCase parseTestCaseInput() {
		logger.debug("Stating another testcase");
		nFlavours = getInt();
		int m = getInt();
		customers = new LinkedList<Customer>();
		for (int i=0; i < m; i++) {
			int t = getInt();
			Customer customer = new Customer();
			for(int j=0; j < t; j++) {
				customer.add(getInt() -1, getInt() == 1);
			}
			customers.add(customer);
		}
		return null;
	}
	
}
