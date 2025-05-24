package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class laborer_login_con {

    @FXML
    private TextField laborerID;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private Button return_login;

    @FXML
    private Label Wrong_login;

    @FXML
    private void handleLogin() throws IOException {
    	boolean loginSuccess = false;
    	try {
    	for (Labor lbr : Labor.labors) {
			if(lbr.getLaborid()==Integer.parseInt(laborerID.getText())&& lbr.getPassword().equals(password.getText())){
				loginSuccess = true;
			}
		}}catch (NumberFormatException e) {
			Wrong_login.setText("Lütfen id değerini girin");
		}
	       

	    if (loginSuccess) {
	        Wrong_login.setText("Giriş başarılı");
	        Main.changeScene("ProductSettings.fxml",900,600);
	    } else if (laborerID.getText().isEmpty() || password.getText().isEmpty()) {
	    	Wrong_login.setText("Bişi yaz Lan!");
	    } else {
	    	Wrong_login.setText("Hatalı şifre veya id");
	    }
    }

    @FXML
    private void returnLogin() throws IOException {
       Main.changeScene("login.fxml");
    }
}
