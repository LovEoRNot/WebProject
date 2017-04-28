package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tools.ConnDB;

//���ݿ����
public class BaseDao {
	private ConnDB conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public BaseDao(){
		this.conn = new ConnDB();
	}
	
	//���ݲ�ѯ
	public ResultSet doQuery(String sql, Object...objects) throws Exception{
		
		Connection conn = this.conn.getConnection();
		this.ps = conn.prepareStatement(sql);
		if(objects != null){
			for(int i=0; i<objects.length; i++){
				this.ps.setObject(i+1, objects[i]);
			}
		}
		this.rs = this.ps.executeQuery();
		
		return this.rs;
	}
	//���ݸ���
	public int doUpdate(String sql, Object...objects)throws Exception{
		
		int num = 0;
		Connection conn = this.conn.getConnection();
		this.ps = conn.prepareStatement(sql);
		if(objects != null){
			for(int i=0; i<objects.length; i++){
				this.ps.setObject(i+1, objects[i]);
			}
		}
		
		num = this.ps.executeUpdate();
		return num;
	}
	
}
