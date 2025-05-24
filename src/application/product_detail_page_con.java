package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

import javafx.event.ActionEvent;

public class product_detail_page_con {
	
	@FXML
    private Label kategoriLabel;

    @FXML
    private Label modelLabel;

    @FXML
    private Label ozellikLabel;

    @FXML
    private Label fiyatLabel;

    @FXML
    private Label tarihLabel;

    @FXML
    private Label stokLabel;

    // setProduct fonksiyonu: ürüne ait detayları doldurur
    public void setProduct(String kategori, String model, String ozellikler, String fiyat, String stok) {
        kategoriLabel.setText(kategori);
        modelLabel.setText(model);
        ozellikLabel.setText(ozellikler);
        fiyatLabel.setText(fiyat);
        stokLabel.setText(stok);
    }

    @FXML
    private void return_fun(ActionEvent event) throws IOException{
    	Main.changeScene("homepage.fxml");
        // Geri dönme işlemi burada yapılacak (boş bırakıldı)
    }

}
