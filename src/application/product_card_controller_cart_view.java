package application;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import urunler.Product;

public class product_card_controller_cart_view {
	
	 @FXML
	 private Label prod_ID;
	 @FXML
	 private Label prod_PRICE;
	 @FXML
	 private Label stock_state;
	 
	 
	 @FXML
	 public void deletefromcart() throws IOException {
		 removeFromStockFile("src/Cart/Cart.txt",prod_ID.getText());
	 }
	 public static void removeFromStockFile(String filePath, String prod_ID) throws IOException {
		   for (Product prd : Main.cst.basket) {
			   if(prod_ID.equals(prd.title)) {Main.cst.removeOrder(prd);}
		   }
		}
 
	 @FXML
	 private void openProductScreen() {
		 Product prd = new Product();
		 for (Product prds : Product.products) {
			if(prod_ID.getText().equals(prds.title)) {prd = prds;break;}
		}   
		 try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("product_detail.fxml")); // FXML dosya ismi doğru olmalı
		        Parent root = loader.load();

		        // Controller'a veri gönder
		        product_detail_page_con controller = loader.getController();

		        // Örnek veriler, sen kendi ürün bilgilerini buraya koyacaksın
		        String kategori = prd.getCategory();   // Örneğin ürün kategorisi label
		        String model = prd.title ;
		        String ozellikler = prd.getFeatures();
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



	 public void setProductCartView(String name, String price) {

	        prod_ID.setText(name);
	        prod_PRICE.setText(price);
	        	        
	    }
	
}
