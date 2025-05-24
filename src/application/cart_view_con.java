package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import urunler.Product;
public class cart_view_con {
	@FXML
    private  Label totalLabel;
	

	@FXML
	private VBox cart_vb;	
	
	@FXML
	private void refresh() {
		TotalPriceHandle();
		cart_vb.getChildren().clear();
		loadProductsFromFile(cart_vb);
	}
	@FXML
	private void finish() throws IOException {
		StockManaging.reduceStock();
		handleClearCart();
		Main.changeScene("siparisonaylandi.fxml");
		
	}
	@FXML
	public void initialize() {
		TotalPriceHandle();
		loadProductsFromFile(cart_vb);
	}
	private void loadProductsFromFile(VBox targetContainer) {
        try {
            for (int i=0;i<Main.cst.basket.size();i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("product_card_cart_view.fxml"));
                    AnchorPane productCard = loader.load();

                    product_card_controller_cart_view controller = loader.getController();
                    controller.setProductCartView(Main.cst.basket.get(i).title, Double.toString(Main.cst.basket.get(i).getPrice()));

                    targetContainer.getChildren().add(productCard);
                }
            

            targetContainer.setPrefHeight(targetContainer.getChildren().size() * 150);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	
	public void TotalPriceHandle() {
	    double total = 0.0;
	        for (Product prd : Main.cst.basket) {
	            try {
	                total += prd.getPrice();
	            } catch (NumberFormatException e) {
	                System.err.println("Fiyat formatı hatası: ");
	            }
	        }
	    totalLabel.setText(String.format("%.2f",total));
	}


    // Alışverişe devam et butonuna basıldığında çağrılır
    @FXML
    private void handleBack() throws IOException {
        Main.changeScene("homepage.fxml");
    }

    @FXML
    public void handleClearCart() {
    	//ÖNEMLİ!! Sipariş veritabanaına basılacak
    	Main.cst.basket.clear();
    }
    
    //overload  
    public static void handleClearCarttxt() {
    	Main.cst.basket.clear();
    }
    
}