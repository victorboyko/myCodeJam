package victor.codejam.tasks;

import java.util.HashSet;
import java.util.Set;

import victor.codejam.AbstractTaskSolution;
import victor.codejam.NoTestCasePreload;

public class FileFixItTaskSolution extends AbstractTaskSolution implements
		NoTestCasePreload {

	class Dir {
		String name;
		Dir parent;
		int level;
		public Dir(String name, int level, Dir parent) {
			this.name = name;
			this.level = level;
			this.parent = parent;
		}
		
		@Override
		public boolean equals(Object obj) {
			Dir dir = (Dir)obj;
			return level == dir.level && name.equals(dir.name) && (parent == dir.parent || parent.equals(dir.parent)) ;
		}
		
		@Override
		public int hashCode() {
			return name.hashCode() + level + ((parent == null) ? 5 : parent.hashCode());
		}
		
		@Override
		public String toString() {
			return name + ":" + level + ":" + parent;
		}
	}
	
	Set<Dir> readyDirs;
	int result;
	
	@Override
	protected void executeSolutionBody(TestCase testCase) {

	}

	@Override
	protected TestCase parseTestCaseInput() {
		result = 0;
		
		readyDirs = new HashSet<Dir>();
		
		int readyNum = getInt();
		int newNum = getInt();
		
		for (int i=readyNum; i > 0; i--) {
			String str = getStr();
			int count = 0;
			Dir dir = null;
			while(str.split("/").length > 1) {
				dir = new Dir(str.split("/")[1], count++, dir);
				if (str.split("/").length > 2)
					str = str.substring(str.indexOf('/', 1));
				else
					str = "";
				readyDirs.add(dir);
			}
		}
		result = readyDirs.size();
		
		for (int i=newNum; i > 0; i--) {
			String str = getStr();
			int count = 0;
			Dir dir = null;
			while(str.split("/").length > 1) {
				dir = new Dir(str.split("/")[1], count++, dir);
				if (str.split("/").length > 2)
					str = str.substring(str.indexOf('/', 1));
				else
					str = "";
				readyDirs.add(dir);
			}
		}
		result = readyDirs.size() - result;
		
		return null;
	}

	@Override
	protected void packOutputNoPrefix(TestCase testCase) {
		putIntNoNewLine(result);

	}

}
