package victor.codejam.tasks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

//TODO

//http://code.google.com/codejam/contest/2418487/dashboard#s=p1
public class ManageYourEnergyTS extends AbstractTaskSolution implements NoTestCasePreload {
	
	int eMax, increase, nNeeded, result;
	Integer[] activities;

	private static boolean hasMore(Integer[] activities, Integer[] maxEnergy) {
		for(int i = 0; i < activities.length; i++)
			if (activities[i] != 0 && maxEnergy[i]!=0)
				return true;
		return false;
	}
	
//	private static int getMaxIndex(Integer[] activities, Integer[] maxEnergy) {
//		int index = activities.length - 1;
//		for(int i = activities.length - 2; i >= 0; i--) {
//			if (activities[i] > activities[index])
//				index = i;
//		}
//		return index;
//	}

	
	private int getMaxIndex(final Integer[] activities, final Integer[] maxEnergy) {
		
		int index = 0;
		for(int i = 1; i < activities.length; i++) {
			if (activities[i] > activities[index])
				index = i;
		}
		return index;
	}
	
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {
		result = 0;
		Integer[] maxEnergy = new Integer[nNeeded];
		Arrays.fill(maxEnergy, eMax);
		while(hasMore(activities, maxEnergy)) {
			int i = getMaxIndex(activities, maxEnergy);
			if (activities[i] == 0)
				break;
			result += activities[i] * maxEnergy[i];
			int incrK = 1;
			for(int j = i + 1; j < nNeeded && incrK <= maxEnergy[i] / increase/* + (maxEnergy[i] % increase == 0 ? 0 : 1)*/; j++, incrK++) {
				maxEnergy[j] = (maxEnergy[i] == eMax) ? Math.min(maxEnergy[j], incrK * increase) : maxEnergy[j] - incrK * increase;
				if (maxEnergy[j] < 0)
					maxEnergy[j] = 0;
			}
			incrK = 1;
			for(int j = i - 1; j >= 0 && incrK <= maxEnergy[i] / increase/* + (maxEnergy[i] % increase == 0 ? 0 : 1)*/; j--, incrK++) {
				maxEnergy[j] -= (maxEnergy[i] == eMax) ? maxEnergy[i] - incrK * increase : incrK * increase;
				if (maxEnergy[j] < 0)
					maxEnergy[j] = 0;
			}
			maxEnergy[i] = 0;
			activities[i] = 0;
		}
	}

	@Override
	protected TestCase parseTestCaseInput() {
		eMax = getInt();
		increase = getInt();
		nNeeded = getInt();
		activities = new Integer[nNeeded];
		for(int i = 0; i < nNeeded; i++)
			activities[i] = getInt();
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);
	}

}
