package tools;
import java.sql.*;

public class ConnDB {
	private Connection conn = null;
	private final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	private final String DBURL = "jdbc:mysql://localhost:3306/database1";
	private final String DBUSER = "root";
	private final String DBPASS = "0000";
	
	public ConnDB(){
		
	}
	
	public Connection getConnection() throws Exception{
		try{
			if(this.conn == null){
				Class.forName(DBDRIVER);
				this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return this.conn;
	}
	
	public void close(PreparedStatement ps, ResultSet rs){
		try{
			if(rs != null){
				rs.close();
			}
			if(ps != null){
				ps.close();
			}
			if(this.conn != null){
				this.conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

