package application;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import urunler.Product;

public class StockManaging {

	public static void reduceStock() throws IOException {
	    updateStockFile(Main.cst.basket);
	}

	private static void updateStockFile(ArrayList<Product> cartProducts) throws IOException {
	        for (Product prd : Product.products) {
	                if (cartProducts.contains(prd)) {  // Burada linear arama yapılıyor
	                    try {
	                        int stock = prd.getStock();
	                        if (stock > 0) { stock--;	prd.setStock(stock);
	                        	for (Product prds : Product.products) {
									if(prd.equals(prds)) {prds.setStock(stock);}
								}
	                        }
	                    } catch (NumberFormatException e) {
	                        // stok sayısı format hatası varsa dokunma
	                    }
	                }
	        }	    
	}
}