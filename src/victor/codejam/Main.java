package victor.codejam;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		//TODO - enable once going to use log4j, so far it's not used
		//org.apache.log4j.BasicConfigurator.configure(); //init log4j
		List<String> inputLines = FileHelper.read();
		
		long startMillis = System.currentTimeMillis();
		List<String> outputLines = new victor.codejam.tasks.AlwaysTurnLeftTS().processAndReply(inputLines);
		
		System.out.println("Duration: " + (System.currentTimeMillis() - startMillis) + " ms");
		//FileHelper.save(outputLines);
		FileHelper.fastSave(outputLines);

	}

}