package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import urunler.Board;
import urunler.CPU;
import urunler.GPU;
import urunler.PreparedSystem;
import urunler.Product;
import urunler.RAM;
import urunler.Storage;

public class homepage_con {

	    @FXML
	    private ScrollPane anakart_sp, ekrankarti_sp, hazirsistemler_sp, ram_sp, ssd_sp, islemci_sp;

	    @FXML
	    private VBox anakart_vb, ekrankarti_vb, hazirsistemler_vb, ram_vb, ssd_vb, islemci_vb;

	    @FXML
	    private TextField minPriceField, maxPriceField,search_prod;

	    
	    @FXML 
	    private Button search_prod_button;
	    
	    @FXML 
	    private Button go_to_cart;
	    
	    @FXML 
	    private Button Hazirsistem;  
	    
	    @FXML
	    private Button ekrankarti;
	    
	    @FXML
	    private Button Anakart;
	    
	    @FXML
	    private Button Ram;
	    
	    @FXML
	    private Button SSD;
	    
	    @FXML
	    private Button Islemci;
	    
	    @FXML
	    public void initialize() {
	    	Hazirsistem.setOnAction(e -> showCategory(1));
	    	ekrankarti.setOnAction(e -> showCategory(2));
	    	Anakart.setOnAction(e -> showCategory(3));
	    	Ram.setOnAction(e -> showCategory(4));
	    	SSD.setOnAction(e -> showCategory(5));
	    	Islemci.setOnAction(e -> showCategory(6));
	    }
	    
	    @FXML
	    private void goToCart() throws IOException{
	    	
	    	Main.changeScene("cart_view.fxml");
	    }
	    
	    private void showCategory(int id) {
	    	hazirsistemler_sp.setVisible(id == 1);
	    	hazirsistemler_sp.setManaged(id == 1);

	    	ekrankarti_sp.setVisible(id == 2);
	    	ekrankarti_sp.setManaged(id == 2);

	    	anakart_sp.setVisible(id == 3);
	    	anakart_sp.setManaged(id == 3);
	        
	    	ram_sp.setVisible(id == 4);
	    	ram_sp.setManaged(id == 4);

	    	ssd_sp.setVisible(id == 5);
	    	ssd_sp.setManaged(id == 5);

	    	islemci_sp.setVisible(id == 6);
	    	islemci_sp.setManaged(id == 6);
	    }
	    
	    
	    public void refresh_func() {
	    	anakart_vb.getChildren().clear();
	        ekrankarti_vb.getChildren().clear();
	        hazirsistemler_vb.getChildren().clear();
	        ram_vb.getChildren().clear();
	        ssd_vb.getChildren().clear();
	        islemci_vb.getChildren().clear();
	        // Her kategori için ürünleri yükle
	    	//ayrı yaptıgım loadproductsfromfile fonksiyonlarını her kategori için ayrı çağırıyor
	    	//her yeni kategori eklendiğinde buraya kaynak txt dosyasyı ve ekleneceği vbox yazılması yeterli
	        loadProductsFromFile(Board.boards,anakart_vb);
	        loadProductsFromFile(GPU.gpus,ekrankarti_vb); 
	        loadProductsFromFile(PreparedSystem.systems,hazirsistemler_vb);
	        loadProductsFromFile(RAM.rams,ram_vb);
	        loadProductsFromFile(Storage.storages,ssd_vb); 
	        loadProductsFromFile(CPU.cpus,islemci_vb);
	    }
	    
	    private void loadProductsFromFile(ArrayList<?extends Product> urunler,VBox targetContainer) {
	        try {
	            for (int i=0;i<urunler.size();i++) {
	            		FXMLLoader loader = new FXMLLoader(getClass().getResource("product_card.fxml"));
	                    AnchorPane productCard = loader.load();
	                    product_card_controller controller = loader.getController();
	                    controller.setProduct( urunler.get(i).title, Double.toString(urunler.get(i).getPrice()),Integer.toString(urunler.get(i).getStock()));
	                    targetContainer.getChildren().add(productCard);
	                }
	            targetContainer.setPrefHeight(targetContainer.getChildren().size() * 150);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    //overload
	    private void loadProductsFromFile(ArrayList<?extends Product> urunler,VBox targetContainer, double minPrice, double maxPrice) {
	            for (int i =0;i<urunler.size();i++) {
	                    String productState;
	                    if(urunler.get(i).getStock()==0) {productState="Stokta var";}
	                    else {productState = "Stokta yok";}

	                    try {
	                        double price =urunler.get(i).getPrice();

	                        if (price >= minPrice && price <= maxPrice) {
	                            try {
	                                FXMLLoader loader = new FXMLLoader(getClass().getResource("product_card.fxml"));
	                                AnchorPane productCard = loader.load();

	                                product_card_controller controller = loader.getController();
	                                controller.setProduct(urunler.get(i).title, price + "₺", productState);

	                                targetContainer.getChildren().add(productCard);
	                            } catch (IOException e) {
	                                System.err.println("FXML yüklenemedi: " + e.getMessage());
	                            }
	                        }

	                    } catch (NumberFormatException e) {
	                    	e.printStackTrace();
	                    }
	                
	            }

	            targetContainer.setPrefHeight(targetContainer.getChildren().size() * 150);
	    }

	    //second overload
	    private void loadProductsFromFile(ArrayList<?extends Product> urunler,VBox targetContainer, double minPrice, double maxPrice, String name) {
	            for (int i=0;i<urunler.size();i++) {
	            	String productState;
	            	if(Product.products.get(i).getStock()==0) {productState="Stokta var";}
	            	else {productState="Stokta yok";}
	                    try {
	                        double price = urunler.get(i).getPrice();

	                        if (price >= minPrice && price <= maxPrice && urunler.get(i).title.toLowerCase().contains(name.toLowerCase())) {
	                        try {
	                            FXMLLoader loader = new FXMLLoader(getClass().getResource("product_card.fxml"));
	                            AnchorPane productCard = loader.load();

	                            product_card_controller controller = loader.getController();
	                            controller.setProduct(urunler.get(i).title, price + "₺", productState);

	                            targetContainer.getChildren().add(productCard);
	                        	} catch(IOException e) {
	                        		e.printStackTrace();
	                        	}
	                        }
	                    } catch (NumberFormatException e) {
	                        System.err.println("Geçersiz fiyat: " + Product.products.get(i).getPrice());
	                    }
	            }

	            targetContainer.setPrefHeight(targetContainer.getChildren().size() * 150);
	    }

	    @FXML
	    private void search_prod_fun() {
	    	if(search_prod.getText().equals("")) {
	    		refresh_func();
	    		return;
	    	}
	    	if(minPriceField.getText().equals("") || maxPriceField.getText().equals("")) { //min vemax fiyatlar boş olunca hata veriyor
	    		minPriceField.setText("0");
	    		maxPriceField.setText("100000");
	    	}
	    	
	    	anakart_vb.getChildren().clear();
	    	islemci_vb.getChildren().clear();
	    	ram_vb.getChildren().clear();
	    	hazirsistemler_vb.getChildren().clear();
	    	ssd_vb.getChildren().clear();
	    	ekrankarti_vb.getChildren().clear();
	    	
	    	
	    	loadProductsFromFile(Board.boards,anakart_vb,Double.parseDouble(minPriceField.getText()),Double.parseDouble(maxPriceField.getText()),search_prod.getText());
	        loadProductsFromFile(GPU.gpus,ekrankarti_vb,Double.parseDouble(minPriceField.getText()),Double.parseDouble(maxPriceField.getText()),search_prod.getText()); 
	        loadProductsFromFile(PreparedSystem.systems,hazirsistemler_vb,Double.parseDouble(minPriceField.getText()),Double.parseDouble(maxPriceField.getText()),search_prod.getText());
	        loadProductsFromFile(RAM.rams,ram_vb,Double.parseDouble(minPriceField.getText()),Double.parseDouble(maxPriceField.getText()),search_prod.getText());
	        loadProductsFromFile(Storage.storages,ssd_vb,Double.parseDouble(minPriceField.getText()),Double.parseDouble(maxPriceField.getText()),search_prod.getText()); 
	        loadProductsFromFile(CPU.cpus,islemci_vb,Double.parseDouble(minPriceField.getText()),Double.parseDouble(maxPriceField.getText()),search_prod.getText());
	    }

	    @FXML
	    private void handleFilter() {
	    	if(minPriceField.getText().equals("") || maxPriceField.getText().equals("")) { //min vemax fiyatlar boş olunca hata veriyor
	    		minPriceField.setText("0");
	    		maxPriceField.setText("100000");
	    	}
	    	anakart_vb.getChildren().clear();
	    	islemci_vb.getChildren().clear();
	    	ram_vb.getChildren().clear();
	    	hazirsistemler_vb.getChildren().clear();
	    	ssd_vb.getChildren().clear();
	    	ekrankarti_vb.getChildren().clear();

	    	loadProductsFromFile(Board.boards,anakart_vb,Double.parseDouble(minPriceField.getText()),Double.parseDouble(maxPriceField.getText()));
	        loadProductsFromFile(GPU.gpus,ekrankarti_vb,Double.parseDouble(minPriceField.getText()),Double.parseDouble(maxPriceField.getText())); 
	        loadProductsFromFile(PreparedSystem.systems,hazirsistemler_vb,Double.parseDouble(minPriceField.getText()),Double.parseDouble(maxPriceField.getText()));
	        loadProductsFromFile(RAM.rams,ram_vb,Double.parseDouble(minPriceField.getText()),Double.parseDouble(maxPriceField.getText()));
	        loadProductsFromFile(Storage.storages,ssd_vb,Double.parseDouble(minPriceField.getText()),Double.parseDouble(maxPriceField.getText())); 
	        loadProductsFromFile(CPU.cpus,islemci_vb,Double.parseDouble(minPriceField.getText()),Double.parseDouble(maxPriceField.getText()));
	    }
}
