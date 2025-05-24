package application;

import java.util.ArrayList;

public abstract class User {
	
	protected String userName ;
	protected String password ;
	protected String userType ;
	protected boolean loggedin;
	
	public User(String userName, String password, String userType) {
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		loggedin = false ;
	}
	
	public boolean isLoggedin() {
		return loggedin;
	}

	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User() {}
	
	public String getUserName() {
		return userName ;
	}
	
	public static ArrayList<User> users = new ArrayList<>();
	
	
}
