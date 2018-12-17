package libs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

import javax.swing.JOptionPane;


public class fileInput {
	private static File inputFile;
	private static File tempFile;
	private static Scanner reader;
	private static PrintWriter outPuter;
	public static File outPutFile;
	public String outPut;

	
	public fileInput()
	{
		tempFile = new File("C:\\Users\\Ibra\\Desktop\\temp\\Data\\DaProg\\Data 3-27-2018.txt");
		outPutFile = new File("C:\\Users\\Ibra\\Documents\\output.txt");
		try {
			reader = new Scanner(tempFile);
			outPuter = new PrintWriter(outPutFile);
			//readTemp = new Scanner(tempFile);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found");
			e.printStackTrace();
		}
	}
	
	public fileInput(String inputFile)
	{
		tempFile = new File(inputFile);
		outPutFile = new File("C:\\Users\\Ibra\\Documents\\output.txt");
		try {
			reader = new Scanner(tempFile);
			outPuter = new PrintWriter(outPutFile);
			//readTemp = new Scanner(tempFile);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found");
			e.printStackTrace();
		}
	}
	
	public void getAll() {
		while(true) {
			if(reader.hasNextLine())
			{
				outPut += reader.nextLine();
			}
			else
			{
				
				break;
			}
		}
	}
	
	public String getNextLine()
	{
		if(reader.hasNextLine())
		{
			return reader.nextLine();
		}
		else
		{
			
			return "-1";
		}
	}
	
	public int getLineCount() {
		int i = 0; 
		while(true) {
			if(reader.hasNextLine())
			{
				reader.next();
				i++;
			}
			else
			{
				try {
					reader = null;
					reader = new Scanner(tempFile);
					//readTemp = new Scanner(tempFile);
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "File not found");
					e.printStackTrace();
				}
				return i;
			}
		}
	}
	


	public String getDate(String line)
	{
		if(line.indexOf("-") != -1 && line != "-1")
		{
			line = line.substring(line.indexOf("-") - 1);
			line = line.substring(0, line.indexOf(" "));
			//System.out.println(line);
		}
			
		return line;
	}
	
	public String getTime(String line)
	{
		if(line.indexOf("h") != -1 && line != "-1")
		{
			line = line.substring(line.indexOf("h") - 2);
			line = line.substring(0, line.indexOf("s"));
		}
			
		return line;
	}
	
	public String getPM25(String line)
	{
		if(line.indexOf(":") != -1 && line != "-1")
		{
			line = line.substring(line.indexOf(":") + 1);
			line = line.substring(1, line.indexOf("\t"));
		}
			
		return line;
	}
	
	public String getPM10(String line)
	{
		if(line.indexOf(":") != -1 && line != "-1")
		{
			line = line.substring(line.indexOf(":") + 1);
			line = line.substring(line.indexOf(":") + 1);
			line = line.substring(1, line.indexOf("\t"));
		}
			
		return line;
	}
	
	public void writeData()
	{
		outPuter.println(outPut);
	}
	
	
	
	
	
	
	
}
