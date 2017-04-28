package tools;

public class User {
	private String username;
	private String userpass;
	
	public User(){}
	
	public User(String name, String pass){
		this.username = name;
		this.userpass = pass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	
}
