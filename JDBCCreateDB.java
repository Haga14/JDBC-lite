package libs;
import java.sql.*;

public class JDBCCreateDB {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	
	private Connection connector = null;
	private Statement statemnt = null;
	
	//Load the JDBC driver with a custom Driver URL
	public void loadJDBCDriverURL(String JDBC_DRIVERin)
	{
		try
		{
			Class.forName(JDBC_DRIVERin);

		}
		catch(Exception e)
		{
			//Handle errors for JDBC
			e.printStackTrace();
			//System.out.println("The JDBC Driver isn't located in the classpath");
		}
	}
	
	//Load to the JDBC driver with the assigned URL
	public void loadJDBCDriver()
	{
		try
		{
			Class.forName(JDBC_DRIVER);

		}
		catch(Exception e)
		{
			//Handle errors for JDBC
			e.printStackTrace();
			//System.out.println("The JDBC Driver isn't located in the classpath");
		}
	}
	
	//Connect to the MySQL DBMS with a custom Database URL
	public Connection connectDBMSURL(String DB_Path,String DBName, String Username, String Password)
	{
		try
		{
			 connector = DriverManager.getConnection(DB_Path + DBName, Username, Password);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return connector;
	}
	
	//Connect to the MySQL DBMS with the assigned Database URL
	public Connection connectDBMS(String DBName,String Username, String Password)
	{
		try
		{
			 connector = DriverManager.getConnection(DB_URL + DBName, Username, Password);
			 //System.out.println("Successfuly connected...");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return connector;
	}
	
	//Executes the SQL command to create and connect to a MySQL database
	public void createDB(Connection connect1, String sql)
	{
		try {
			statemnt = connect1.createStatement();
	
			statemnt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		//System.out.println("Database created successfully!");
	}
	
	//Closes the connections
	public boolean closeConnections()
	{
		try {
			connector.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("The connection failed to close");
			return false;
		}
		
		return true;
	
	}
	

}
