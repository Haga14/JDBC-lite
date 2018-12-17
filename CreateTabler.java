package libs;
import java.sql.*;

import java.util.Scanner;

//import liimo.InputGetterd;

public class CreateTabler {
	//Assemble the necessary SQL commands to create a table.
	public String buildTable(String tableName)
	{
		String SQL = "CREATE TABLE " + tableName + "(" + addColumn() + ");";
		
		return SQL;
		
	}
	
	//Assemble the necessary SQL commands to add a column to an MySQL table.
	//Assemble the necessary SQL statements to add columns into an SQL table.
	public String addColumn()
	{
		System.out.println("We will now start adding columns to your MySQL database Table.");
		String SQL = "";
		String tempCol = "";
		String tempName = "";
		int index = 0;
	l1:	while(true)
		{
			System.out.println("Please choose one of the following types of columns:\nType				Code\nInteger Numbers			: IN\nString Characters: 		: SC\nDecimal Point			: DP"
					+ "\nWhen done please enter done\n");
			tempCol = InputGetterd.getAlphaString(new Scanner(System.in));
			if(tempCol.equals("done"))
			{
				break;
			}
			System.out.println("Please enter the name of the column");
			tempName = InputGetterd.getAlphaString(new Scanner(System.in));
			
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
					SQL += "," + tempName + " " + "INT";
				}
				break;
			}
			case "SC":
			{
				if(index == 0)
				{
					SQL = tempName + " " + "VARCHAR(255)";
				}
				else
				{
					SQL += "," +tempName + " " + "VARCHAR(255)";
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
					SQL += "," +tempName + " " + "FLOAT";
				}
				break;
			}
			case "done":
			{
				System.out.println("You entered: done\nLiimo will now proceed to create the columns in your database");
				break l1;
			}
			default:
			{
				System.out.println("You didn't enter a valid code, would you like to try again? If so, enter yes, else enter anything.");
				if(InputGetterd.getAlphaString(new Scanner(System.in)).equals("yes"))
				{
					continue;
				}
				else
				{
					break l1;
				}
			}
			}
			index++;
		}
		
		return SQL;
		
	}
	
	//Assemble the necessary SQL commands to insert a row into an MySQL table.
	//Assemble the necessary SQL statements to insert rows into an SQL table.
	public String insertRow(String tableName, ResultSetMetaData columnTypes)
	{
		System.out.println("We will now start adding rows to your MySQL database Table.");
		String SQL = "INSERT INTO " + tableName.toUpperCase() + " (";
		String tempStr = "";
		int tempInt = -1;
		double tempDou = -1;
		String tempop = "";
		int colCount = 1;
		String columnName = "";
		int colType = -1;
		
		//Fetch the column names and prepare them for the insertion query
		try
		{
			for(int i = 1; i < columnTypes.getColumnCount() + 1; i++)
			{
				columnName = columnTypes.getColumnName(i);
				if(i != 1)
				{
					SQL += "," + columnName + "";
				}
				else
				{
					SQL += " " + columnName + "";
				}
				
				if(i == columnTypes.getColumnCount())
				{
					SQL += ") VALUES (";
				}
			}
			colCount = columnTypes.getColumnCount();
		}
		catch(SQLException e)//Catch the SQLException from the getColumnName method. 
		{
			colCount = -1;
			System.out.println("There are no columns in " + tableName);
		}

		
		
		for(int i = 1; i < colCount + 2; i++)//Prompts the user for one value for each column, prepares the appropriate SQL code
		{
			columnName = "";
			colType = 0;
			tempStr = "";
			tempInt = 0;
			tempDou = 0;
			tempop = "";
			
			
			
			try
			{
				columnName = columnTypes.getColumnName(i);
			}
			catch(SQLException e)
			{
				
			}
			
			System.out.println("Please enter the corresponding value for the " + columnName + " column.\nWhen you are done enter done");
			colType = columnType(columnTypes, i);

			
			if(colType == 12)
			{
				tempStr = InputGetterd.getString(new Scanner(System.in));
				tempop = tempStr;
			}
			else if(colType == 4)
			{
				tempInt = InputGetterd.getInt(new Scanner(System.in));
				tempop = tempStr;
			}
			else if(colType == 7)
			{
				tempDou = InputGetterd.getDouble(new Scanner(System.in));
				tempop = tempStr;
			}
			
			try
			{
				if(tempop.equals("done"))
				{
					SQL += ");";
					break;
				}
				if(tempInt == -1)
				{
					SQL += ");";
					break;
				}
				if(tempDou == -1)
				{
					SQL += ");";
					break;
				}
				else
				{
					
				}
			}
			catch(Exception e)
			{
				continue;
			}
		
			
			if(i < colCount)
			{
				if(colType == 12)
				{
					SQL += "'" + tempStr + "',";

				}
				else if(colType == 4)
				{
					SQL += tempInt + ",";
				}
				else if(colType == 7)
				{
					SQL +=  tempDou + ",";

				}
			}
			else 
			{
				if(colType == 12)
				{
					SQL += "'" + tempStr + "'";

				}
				else if(colType == 4)
				{
					SQL += "" + tempInt + "";
				}
				else if(colType == 7)
				{
					SQL += "" + tempDou + "";

				}				
			}
		}
		 
		SQL += ");";
		
		return SQL;
		
		
	}
	
	//Fetch the data type of a given column in MySQL table.
	//Fetch the SQL Data type of a column.
	public int  columnType(ResultSetMetaData columnTypes, int index) 
	{
		
		try
		{
			return columnTypes.getColumnType(index);
		}
		catch(SQLException e)
		{
			return -1;
		}
	}
	
	
}
