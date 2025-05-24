package application;

import java.io.BufferedReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class register_con {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private CheckBox consentCheckBox;

    @FXML
    private Label errorLabel;
    
    @FXML
    public void initialize() {
    	gender.setItems(FXCollections.observableArrayList("Erkek","Kadın","Belirtmiyorum"));
    }

    @FXML
    private void handleRegister() throws IOException{
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String genderValue = gender.getValue();
        
        if (gender.getValue() != null) {
            genderValue = gender.getValue();
        } else {
            genderValue = "-";
        }
        
        boolean consent = consentCheckBox.isSelected();

        StringBuilder errors = new StringBuilder();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || gender.getValue() == null) {
            errors.append("Lütfen tüm alanları doldurun.\n");
        }

        if (!consent) {
            errors.append("Lütfen onay kutusunu işaretleyin.\n");
        }
            for (Customer cst : Customer.customers) {
                    if (cst.getUserName().equalsIgnoreCase(name)) {
                        errors.append("Bu isim zaten kullanılıyor.\n");
                    }
        } 

        // Hatalar varsa göster
        if (errors.length() > 0) {
            errorLabel.setText(errors.toString().trim());
            errorLabel.setVisible(true);
            return;
        }
        Customer.customers.add(new Customer(name, password));
        Customer.customers.getLast().setEmail(email);
        Main.changeScene("login.fxml");
    }

}
