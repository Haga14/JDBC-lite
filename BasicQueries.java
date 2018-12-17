package libs;

public class BasicQueries {
	public String UseDB(String Name)
	{
		return "USE " + Name.toUpperCase();
	}
	
	public String CreateDB(String Name)
	{
		return "Create Database " + Name;
	}
	public String getTables()
	{
		return "SHOW TABLES;";
	}
	public String getDBs()
	{
		return "SHOW DATABASES;";
	}
	public String getTable(String Name)
	{
		return "SELECT * FROM " + Name + ";";
	}
	public String getUserName(String inTableName, String value) {
		return "SELECT * FROM " + inTableName  + ";";
	}
	public String getUserNames(String inTableName) {
		return "select userName from " + inTableName +  ";";
	}
	
	public String verifyUserCreds(String inTableName, String value, String pass) {
		return "SELECT * " + inTableName + " WHERE userName = " + value + " AND password = " + pass + " ;";
	}
	
	public String getAmountHeld(String inTableName, String uName)
	{
		return "select * from " + inTableName + " where username = '" + uName + "' ;";
	}
	public String dropTables(String name) {
		return "drop table " + name + " ;";
	}
	
	public String setValue(String table, String col, String value, String uName) {
		return "update " + table + " set " + col + " = " + value + " where userName = " + uName + ";";
	}
	

	
	
	
	

}
