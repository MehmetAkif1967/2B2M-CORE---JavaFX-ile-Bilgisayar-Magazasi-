package application;
import java.util.ArrayList;

import urunler.Product;



public class Labor extends User{
	public static ArrayList<Labor> labors = new ArrayList<Labor>();
	private int laborid ;
	
	
	public Labor(String userName, String password,int laborid) {
		super(userName, password,"Labor");
		this.laborid = laborid ;
	}

	public void setLaborid(int laborid) {
		this.laborid = laborid;
	}

	public int getLaborid() {
		return laborid;
	}

	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}
	
}
 