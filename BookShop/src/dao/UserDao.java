package dao;

import tools.User;

public class UserDao extends BaseDao {
	
	public UserDao(){};
	
	public boolean checkUser(User user){		
		String sql = "SELECT * FROM t_user WHERE name = ? AND pass = ?";
		Object[] params = new Object[2];
		params[0] = user.getUsername();
		params[1] = user.getUserpass();
		try{
			if(doQuery(sql, params).next()){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public int updateUser(User user){
		int num = 0;
		String sql = "INSERT INTO t_user(name,pass) VALUES(?, ?)";
		Object[] params = new Object[2];
		params[0] = user.getUsername();
		params[1] = user.getUserpass();
		try{
			num = doUpdate(sql, params);
		}catch(Exception e){
			e.printStackTrace();
		}	
		return num;
	}
	
	
}
