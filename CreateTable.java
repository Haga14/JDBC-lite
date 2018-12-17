package libs;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateTable {
	private String colTypes[];
	private String colNames[];
	private int colCount = 0;
	public static boolean alphaString;
	//Assemble the necessary SQL commands to create a table.
	public String buildTable(String tableName, String inColNames[], String inColTypes[])
	{
		colTypes = inColTypes;
		colNames = inColNames;
		String SQL = "CREATE TABLE " + tableName + "(" + addColumn() + ");";
		
		return SQL;
		
	}
	
	public void input(String inColTypes[], String inColNames[]) {
		colTypes = inColTypes;
		colNames = inColNames;
	}
	//Assemble the necessary SQL commands to add a column to an MySQL table.
	//Assemble the necessary SQL statements to add columns into an SQL table.
	public String addColumn() throws InputMismatchException
	{
		String SQL = "";
		String tempCol = "";
		String tempName = "";
		int index = 0;
		int colIndex = 0;
		boolean colIndexInc = false;
	l1:	while(colIndex < colTypes.length)
		{

			if(colIndex < colTypes.length && InputGetter.isInt(colTypes[colIndex]))
			{
				//System.out.println("Is Int: " + colTypes[colIndex]);
				tempCol = "IN";
				colIndexInc = true;
			}
			else if(colIndex < colTypes.length && InputGetter.isDouble(colTypes[colIndex])) {
				//System.out.println("Is Double: " + colTypes[colIndex]);
				tempCol = "DP";
				colIndexInc = true;
			}
			else if(colIndex < colTypes.length && InputGetter.isAlphaString(colTypes[colIndex]))
			{
				//System.out.println("Is String Alpha: " + colTypes[colIndex]);
				tempCol = "SC";
				colIndexInc = true;
			}
			

			else if(colIndex < colTypes.length)
			{
				//System.out.println(colTypes[colIndex]);
				throw new InputMismatchException("Text contains non-alphabetic characters.");
			}
			
			if(tempCol.equals("done"))
			{
				break;
			}
			
			
			tempName = colNames[colIndex];
				
			
			
			//System.out.println(tempName + " " + tempCol);
			switch(tempCol)
			{
			case "IN":
			{
				
				if(index == 0)
				{
					SQL = tempName + " " + "INT";
				}
				else
				{
					SQL += ", " + tempName + " " + "INT";
				}
				break;
			}
			case "SC":
			{
				if(index == 0)
				{
					SQL = tempName + " " + "VARCHAR(250)";
				}
				else
				{
					SQL += ", " +tempName + " " + "VARCHAR(250)";
				}
				break;
			}
			case "DP":
			{
				if(index == 0)
				{
					SQL = tempName + " " + "FLOAT";
				}
				else
				{
					SQL += ", " +tempName + " " + "FLOAT";
				}
				break;
			}
			}
			index++;
			
			if(colIndexInc)
			{
				colIndex++;
			}
		}
		
		return SQL;
		
	}
	
	//Assemble the necessary SQL commands to insert a row into an MySQL table.
	//Assemble the necessary SQL statements to insert rows into an SQL table.
	public String insertRow(String tableName, ResultSetMetaData columnTypes, ArrayList<String> data) throws InputMismatchException
	{
		String SQL = "INSERT INTO " + tableName.toUpperCase() + " (";
		String tempStr = "";
		int tempInt = -1;
		double tempDou = -1;
		String tempop = "";
		int colCount = 1;
		String columnName = "";
		int colType = -1;
		colData(columnTypes);
		
		//Fetch the column names and prepare them for the insertion query
		colCount = colNames.length;
		//System.out.println("ColCount: " + colCount);
		for(int i = 0; i < colCount + 1; i++)
		{
			
			if(i != 0 && i < colCount)
			{
				columnName = colNames[i];
				SQL += ", " + columnName + "";
			}
			else if(i < colCount)
			{
				columnName = colNames[i];
				SQL += "" + columnName + "";
			}
			
			if(i == colCount)
			{
				SQL += ") VALUES (";
			}
		}
			
		for(int i = 0; i < colTypes.length; i++)//Prompts the user for one value for each column, prepares the appropriate SQL code
		{
			columnName = "";
			colType = 0;
			tempStr = "";
			tempInt = 0;
			tempDou = 0;
			tempop = "";
			//System.out.println("Col Length: " + colTypes[i] + "Data Length " + data.get(i));
			//System.out.println(colTypes[i] + " i = " + i);
			if(Integer.parseInt(colTypes[i]) == 12)
			{
				tempStr = data.get(i);
			}
			else if(Integer.parseInt(colTypes[i]) == 4)
			{
				if(InputGetter.isInt(data.get(i)))
				{
					tempInt = Integer.parseInt(data.get(i));
				}
				else
				{
					//System.out.println(data.get(i));
					throw new InputMismatchException("input is not an integer.");
				}
			}
			else if(Integer.parseInt(colTypes[i]) == 7)
			{
				if(InputGetter.isDouble(data.get(i)))
				{
					tempDou = Double.parseDouble(data.get(i));
				}
				else
				{
					throw new InputMismatchException("input is not a double.");
				}
			}
		
			
			if(i != colCount - 1)
			{
				if(Integer.parseInt(colTypes[i]) == 12)
				{
					SQL += "'" + tempStr + "', ";

				}
				else if(Integer.parseInt(colTypes[i]) == 4)
				{
					SQL += tempInt + ", ";
				}
				else if(Integer.parseInt(colTypes[i]) == 7)
				{
					SQL +=  tempDou + ", ";

				}
			}
			else 
			{
				if(Integer.parseInt(colTypes[i]) == 12)
				{
					SQL += "'" + tempStr + "'";

				}
				else if(Integer.parseInt(colTypes[i]) == 4)
				{
					SQL += "" + tempInt + "";
				}
				else if(Integer.parseInt(colTypes[i]) == 7)
				{
					SQL += "" + tempDou + "";

				}				
			}
		}
		//SQL = SQL.substring(0, SQL.length() -2);
		 
		SQL += ");";
		
		return SQL;
		
		
	}
	
	//Fetch the data type of a given column in MySQL table.
	//Fetch the SQL Data type of a column.
	public void  colData(ResultSetMetaData columnTypes) 
	{
		colNames = null;
		colTypes = null;
		
		
		try
		{
			colTypes = new String[columnTypes.getColumnCount()];
			colNames = new String[columnTypes.getColumnCount()];
			for(int i = 1; i < columnTypes.getColumnCount() + 1; i++)
			{
				colNames[i - 1] = columnTypes.getColumnName(i);
				colTypes[i - 1] = "" + columnTypes.getColumnType(i);
			}
			
		}
		catch(SQLException e)
		{
			//System.out.println("Intruder Alert");
			e.printStackTrace();
		}
	}
	
	
}
