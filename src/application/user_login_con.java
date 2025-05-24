package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class user_login_con {


    @FXML
    private TextField userName;

    @FXML
    private Label Wrong_login;
    
    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;
    
    @FXML
    private Button lab_log;


    @FXML
    private Button registerButton;
    

    @FXML
    private void laborerloginscene() throws IOException {
    	Main.changeScene("laborer_login.fxml");
    }
    
    @FXML
    private void handleLogin()throws IOException {
    	boolean loginSuccess = false;

	    for (Customer cst : Customer.customers) {

	            if (cst.getUserName().equals(userName.getText()) && cst.password.equals(password.getText())) {
	                loginSuccess = true;
	                break;
	                
	            }
	        
	    }


	    if (loginSuccess) {
	        Wrong_login.setText("Giriş başarılı");
	        Main.changeScene("homepage.fxml",900,600);
	    } else if (userName.getText().isEmpty() || password.getText().isEmpty()) {
	    	Wrong_login.setText("Tün boşlukları doldurunuz");
	    } else {
	    	Wrong_login.setText("Hatalı şifre veya kullanıcı adı");
	    }
	}

    

    @FXML
    private void handleRegister()throws IOException {
    	userName.clear();
    	password.clear();

    	Main.changeScene("register.fxml");
        
    }
}
