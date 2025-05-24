package application;

import java.util.ArrayList;
import java.util.Scanner;

import urunler.Product;

public class Customer extends User{
	private String email;
	public Customer() {super();}
	public static ArrayList<Customer> customers = new ArrayList<>();
	public ArrayList<Product> basket = new ArrayList<Product>();
	private ArrayList<String> adresses = new ArrayList<String>();
	
	public Customer(String userName, String password) {
		super(userName, password, "Customer");
	}

	public void addAdress() {
		adresses.add("Arayüzden gelecek yazı"); 					//Arayüz
	}

	public void addOrder(Product theProduct) {												//Arayüzden alınacak ürün id değeri
			basket.add(theProduct);
	}
	public void removeOrder(Product theProduct) {
			basket.remove(theProduct);
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void sentOrder() {
		if(basket.isEmpty()) {System.out.println("sepet boş");}
		for (Product orders : basket) {
			System.out.println(orders.id/*+this.id*/); 				//Veri Tabanına yazılacak 
		}															//Yazılırken kullanıcı adı ile birlikte yazılabilir
		basket.clear();
	}
	
}
	
	
