package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class concluding_purchase_con {

    @FXML
    private Button btnAlisveriseDevam;
    
    


    @FXML
    private void AlisveriseDevam() throws IOException {
       Main.changeScene("homepage.fxml");
    }
    
}
