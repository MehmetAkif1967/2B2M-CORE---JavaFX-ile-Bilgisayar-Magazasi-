package application;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import urunler.Board;
import urunler.CPU;
import urunler.GPU;
import urunler.PreparedSystem;
import urunler.Product;
import urunler.RAM;
import urunler.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class prod_sett_con  {

	@FXML
	private Label notification_label;
	
    @FXML
    private TextField prod_add_specs;

    @FXML
    private TextField prod_add_price;

    @FXML
    private TextField prod_add_stock;

    @FXML
    private TextField prod_add_model;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private Button handleAddProduct;

    @FXML
    private void handleAddProduct() {
    	Product prd;
    	String specs = prod_add_specs.getText().trim();
        String price = prod_add_price.getText().trim();
        String stock = prod_add_stock.getText().trim();
        String model = prod_add_model.getText().trim();
        String category = categoryChoiceBox.getValue();
        switch(category) {
        case "Ekran Kartı":
        	prd = new GPU();break;
        case "CPU":
        	prd = new CPU();break;
        case "RAM":
        	prd = new RAM();break;
        case "Anakart":
        	prd = new Board();break;
        case "SSD":
        	prd = new Storage();break;
        case "Hazır Sistemler":
        	prd = new PreparedSystem();break;
        	default: prd = new Product();
        }
        
        
        if(specs.isEmpty() || price.isEmpty() || stock.isEmpty() || model.isEmpty() || category == null || category.isEmpty()) {
        	notification_label.setText("Lütfen tüm boşlukları doldurunuz!");
        	return;
        }
        
        prd.setStock(Integer.parseInt(stock));
        prd.setPrice(Double.parseDouble(price));
        prd.setBrand(model.stripLeading());
        prd.setModel(model.substring(model.stripLeading().length()));
        try {
            prd.addProduct(specs);}
            catch (Exception e) {
            	e.printStackTrace();
            	notification_label.setText("Girdiğiiz formatta hata var! Lütfen uygun şekilde doldurunuz");
            	return ;
    		}
        
       
        prod_add_specs.clear();
        prod_add_price.clear();
        prod_add_stock.clear();
        prod_add_model.clear();
        categoryChoiceBox.setValue(null);
        prod_add_specs.setPromptText("");
        notification_label.setText("Ürün Eklendi!");
        
        
    }

    @FXML
    public void initialize() {
        categoryChoiceBox.getItems().addAll("CPU", "RAM", "Ekran Kartı", "Hazır Sistemler", "SSD", "Anakart");

        categoryChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) {
                prod_add_model.setPromptText("");
                return;
            }
            switch (newVal) {
                case "CPU":
                    prod_add_specs.setPromptText("Çekirdek sayısı,Thread sayısı, Hız aşımı(true/false),Frekans, Soket, Bellek desteği");
                    break;
                case "RAM":
                	prod_add_specs.setPromptText("Kapasite(Gb), Frekans(MHz), Bellek tipi, Hız Aşımı");
                    break;
                case "Ekran Kartı":
                	prod_add_specs.setPromptText("Vram Kapasitesi, Hız Aşımı (true/false), Bellek Tipi, Çekirdek Hızı");
                    break;
                case "Hazır Sistemler":
                	prod_add_specs.setPromptText(" GPU ID,CPU ID, RAM ID, ANAKART ID, SSD ID");
                    break;
                case "SSD":
                	prod_add_specs.setPromptText("Bağlantı Tipi, Kapasite, Arayüz tipi, Okuma hızı, Yazma Hızı");
                    break;
                case "Anakart":
                	prod_add_specs.setPromptText("Soket, Yonga Seti, Bellek Tipi, Bellek Yuva Sayısı, Form Faktörü");
                    break;
                default:
                	prod_add_specs.setPromptText("");
                    break;
            }
        });
    }

    
    @FXML
    private void exit() throws IOException {
    	Main.changeScene("login.fxml",400,550);
    }

}
