package libs;
import libs.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Comm {
	private JDBCCreateDB Database;
	private BasicQueries queries;
	private Connection connect;
	private CreateTable tableMaker;
	private Runner run;
	private BasicQueries Qs = new BasicQueries();
	private String colNames[];
	private String colTypes[];
	private String data[];
	private String tableName;
	private InputGetter validator = new InputGetter();

	
	
	public Comm() {
		init();
	}
	
	public void init() {
		Database = new JDBCCreateDB();
		queries = new BasicQueries();
		connect = Database.connectDBMS("test", "root", "root");
		tableMaker = new CreateTable();	
		run = new Runner();
	}
	
	public void input(String inTableName, String inColNames[], String inColTypes[], String inData[]) {
		colNames = inColNames;
		colTypes = inColTypes;
		data = inData;
		tableName = inTableName;
		
	}
	public void input(String inData[]) {
		data = inData;
	}
	public void input(String inData[], String inTable) {
		data = inData;
		tableName = inTable;
	}
	
	public void selectDB(String name)
	{
		run.Change(connect, queries.UseDB(name));
	}
	
	public void createDB(String name){
		Database.createDB(connect, queries.CreateDB(name));
	}
	
	public void buildTable(String inTableName, String inColNames[], String inColTypes[]) {
		colNames = inColNames;
		colTypes = inColTypes;
		tableName = inTableName;
		
		String SQL = tableMaker.buildTable(tableName, colTypes, colNames);
		System.out.println(SQL);

		run.Change(connect, SQL);
		
	}
	
	public void buildTable() {
		String SQL = tableMaker.buildTable(tableName, colTypes, colNames);
		//System.out.println(SQL);
		run.Change(connect, SQL);
		
	}
	
	public void appendToTable() {
		ArrayList<String> aLdata = new ArrayList<String>();
		Statement stmnt;
		ResultSet resultset = null;
		for(int i = 0; i < data.length; i++) aLdata.add(data[i]);
		
		try {
			stmnt = connect.createStatement();
			resultset = stmnt.executeQuery(queries.getTable(tableName));
			tableMaker.input(colTypes, colNames);
			String SQL = tableMaker.insertRow(tableName, resultset.getMetaData(), aLdata);
			System.out.println(SQL);
			//System.out.println(SQL);
			stmnt.executeUpdate(SQL);
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.exit(0);

		}
		
	}
	
	public String[] getUsers() {
		run.Qrun(connect, queries.getUserNames("users"));
		ArrayList<String> data = new ArrayList<String>();
		
		int index = 1;
		try {
			while(run.rs.next())
			{
				try {
					data.add(run.rs.getString("userName"));
					index++;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String retString[] = new String[data.size()];
		for(int i = 0; i < data.size(); i++)
		{
			retString[i] = data.get(i);
		}
		
		return retString;
	}
	
	public boolean findUser(String userName)
	{
		if(validator.isAlpha(userName))
		{
			run.Qrun(connect, Qs.getUserName("users", userName));
			try {
				if(run.rs.getString("userName") == userName)
				{
					run.rs = null;
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				run.rs = null;
				e.printStackTrace();
				return false;
			}
		}
		///Set run.rs to null each time used.
		run.rs = null;
		return false;
	}
	
	public double getAmount(String uName){
		double temp = -1;
		run.Qrun(connect, queries.getAmountHeld("userData", uName));
		
		if(run.sent)
		{
			try {
				run.rs.next();
				temp = Double.parseDouble(run.rs.getString("Dollar"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		run.rs = null;
		return temp;
	}
	
	public void changeValue(String table,  String col, String value, String uName) {
		//System.out.println(queries.setValue(table, col, value, uName));
		run.Change(connect, queries.setValue(table, col, value, uName));
		
	}
	

	
	/*public void getUserValues() {
		for(int i = 0; i < bank.size(); i++) {
			bank.get(i).getValue();
		}
	}*/
	
	public boolean loginUser(String uName, String uP)
	{
		if(validator.isAlpha(uName) && validator.isAlpha(uP))
		{
			run.Qrun(connect, Qs.verifyUserCreds("users", uName, uP));
			run.rs = null;
			return run.sent;
		}
		run.rs = null;
		return false;
	}
	
	public void dropTable(String name)
	{
		run.Change(connect, queries.dropTables(name));
		run.rs = null;
	}
	public void terminate() {
		Database.closeConnections();
		System.exit(0);
	}



	
}
