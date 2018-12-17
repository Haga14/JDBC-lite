package libs;

import java.sql.*;
public class Runner {
	
	public ResultSet rs;
	public boolean sent = false;
	
	public ResultSet Qrun(Connection conn, String q)
	{
		Statement St;
		try {
			St = conn.createStatement();
			rs = St.executeQuery(q);
			sent = true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			sent = false;
		}
		return rs;
	
	}
	
	public void Change(Connection conn, String q)
	{
		Statement St;
		
		try
		{
			St = conn.createStatement();
			//System.out.println(q);
			St.executeUpdate(q);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
