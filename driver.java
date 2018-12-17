package fileBuz;
import libs.Comm;
import libs.InputGetter;
import libs.fileInput;
import org.json.simple.parser.*;
import java.io.FileReader; 

public class driver {
	private static Comm tricorder = new Comm();
	private static fileInput file = new fileInput();

	private final static String colNames[] = {"PM10", "PM25", "Date", "Time"};
	private final static String colTypes[] = {"12.5", "135.5", "as", "asd"};
	
	//private final static String colNames[] = {"Class", "ControlFamily", "ControlID", "Title", "Impact", "Priority", "Description", "SupplementalGuidance"};
	//private final static String colTypes[] = {"Asd", "asd", "as", "asd", "Asd", "asd", "as", "asd"};
	private static String data[] = {"PlaceHolder", "PlaceHolder", "PlaceHolder", "PlaceHolder"};
	
	//private final static String colUNames[] = {"userName", "Password", "firstName" + "lastName" + "City" + "Country"};
	//private final static String colUTypes[] = {"12.5", "12.4", "PM", "Ti"};
	//private static String dataU[] = {"PlaceHolder", "PlaceHolder", "PlaceHolder", "PlaceHolder"};
	

	public static void main(String[] args) {
		//tricorder.createDB("air");
		
		initData();
		getData();
		writeData();

	}///Write a architecture graphic for the class
	
	private static void getData() {
		
		String line = "";
		file.outPut = "{ data: [ ";
		line = file.getNextLine();
		//file.getDate(file.getNextLine());
		//file.getTime(file.getNextLine());
		while(!line.equals("-1"))
		{
			if(line.contains("N")) {line = file.getNextLine();continue;}
			data[0] = file.getPM10(line);
			data[1] = file.getPM25(line);
			data[2] = file.getDate(line);
			data[3] = file.getTime(line);
			if(((InputGetter.isDouble(data[0]) || InputGetter.isInt(data[0]))  && (InputGetter.isDouble(data[1]) || InputGetter.isInt(data[1]))) && (data[2].length() >= 8 && data[2].length() <= 12) && (data[2].length() >= 8 && data[2].length() <= 12)) {
				file.outPut += "{ \"PM10\": " + data[0] + ", \"PM25\": " + data[1] + ", \"Date\": " + data[2] + ", \"Time\": " + data[3] + "},";
				tricorder.input("data", colNames, colTypes, data);
				//Use another Line string variable to fix object comma issue above
				tricorder.appendToTable();
			}
			
			data[0] = "";
			data[1] = "";
			data[2] = "";
			data[3] = "";
			
			
			line = file.getNextLine();
		}
		String temp = file.outPut;
		temp = temp.substring(0, temp.length() - 1);
		temp += "] }";
		file.outPut = temp;
	}
	
	public static void writeData() {
		
		file.writeData();
	}
	
	public static void initData() {
		//tricorder.createDB("air");
		tricorder.selectDB("air");
		//tricorder.dropTable("data");
		tricorder.buildTable("data", colTypes, colNames);
		//tricorder.buildTable("history ", colTypes, colNames);
		
	}

	
}
