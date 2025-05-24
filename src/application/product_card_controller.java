package application;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import urunler.Board;
import urunler.CPU;
import urunler.GPU;
import urunler.PreparedSystem;
import urunler.Product;
import urunler.RAM;
import urunler.Storage;

public class product_card_controller {
	
	public Customer custm ; 
	private Product prodCart ;
	
	@FXML
	 private Label prod_ID;
	 @FXML
	 private Label prod_PRICE;
	 @FXML
	 private Label stock_state;
	 @FXML
	 private Label warning_Add;
	 @FXML
	 private Button add_to_cart;
	 
	 @FXML
	 private void addToCart() {
		 if ("Stokta yok".equals(stock_state.getText().trim())) {
			 warning_Add.setText("Stokta bulunamadığı için eklenemedi"); 				//stok boşsa işlemi durdur
	         return; 
	     }
	         // Stok varsa ekle
		for (Product prd : Product.products) {
			if(prd.title.equals(prod_ID.getText())) {prodCart = prd ;Main.cst.addOrder(prd);;break;}
		}
	 }

	 
	 @FXML
	 private void openProductScreen() {
		 String classname = "Object" ;
		 Product prd = new Product();
		 for (Product prds : Product.products) {
			if(prds.title.equals(prod_ID.getText())) {classname = prds.getClass().getName();prd = prds;break;}
		}
		 try {
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("product_detail.fxml")); // FXML dosya ismi doğru olmalı
		    Parent root = loader.load();

		    // Controller'a veri gönder
		    product_detail_page_con controller = loader.getController();
		    
		    // Örnek veriler, sen kendi ürün bilgilerini buraya koyacaksın
			 String kategori = prd.getCategory();   // Örneğin ürün kategorisi label
		    String ozellikler = prd.getFeatures();
		    String model = prd.title;
		    String fiyat = Double.toString(prd.getPrice());
		    String stok = Integer.toString(prd.getStock());

		    controller.setProduct(kategori, model, ozellikler, fiyat, stok);

		    Stage stage = (Stage) prod_ID.getScene().getWindow(); // someLabel: tıklanan label veya bir node
		    stage.setScene(new Scene(root));
		    stage.show();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}




	public void setProduct(String name, String price,String state) {

        prod_ID.setText(name);
        prod_PRICE.setText(price);
        
        if(!"0".equals(state)) {
        	stock_state.setText("Stokta var");
        }
        else {
        	stock_state.setText("Stokta yok");
        }
        
    }
}