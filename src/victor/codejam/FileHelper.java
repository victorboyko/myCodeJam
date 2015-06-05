package victor.codejam;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;

public class FileHelper {
	
	public static List<String> read() {

		final JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File("/Users/Victor/Downloads/A-large-practice.in.txt"));
		final int returnVal = fc.showOpenDialog(null);
		final File file;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            System.out.println("Opening: " + file.getName() + ".");
        } else {
        	throw new IllegalStateException("Open command cancelled by user.");
        }
		return readFromFile(file);
	}
	
	public static List<String> fastRead() {
		final File file = new File("/Users/Victor/Downloads/input_taskSolution.txt");
		return readFromFile(file);
	}
	
	public static List<String> readFromFile(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
		    try {
		    	List<String> res = new LinkedList<String>();
		        String line = br.readLine();
		        while (line != null) {
		            res.add(line);
		            line = br.readLine();
		        }
		        return res;
		    } finally {
		        br.close();
		    }
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	
	
	public static void save(List<String> lines) {
		final JFileChooser fc = new JFileChooser();
		String dateTime = new SimpleDateFormat("MMM_dd__HH.mm.ss").format(new Date());
		fc.setSelectedFile(new File("/Users/Victor/Downloads/codejam_output_" + dateTime + ".txt"));
		final int returnVal = fc.showSaveDialog(null);
		final File file;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            System.out.println("Saving: " + file.getName() + ".");
        } else {
        	throw new IllegalStateException("Save command cancelled by user.");
        }
			
		saveToFile(file, lines);
	}
	
	public static void fastSave(List<String> lines) {
		final File file = new File("/Users/Victor/Downloads/output_taskSolution.txt");	
		saveToFile(file, lines);
	}
	
	public static void saveToFile(File file, List<String> lines) {
		try {
			BufferedWriter writer = new BufferedWriter( new FileWriter(file));
			for (String line: lines) {
				writer.append(line);
				writer.newLine();
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		System.out.println("Done!");
	}

}
