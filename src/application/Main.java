package application;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import urunler.Board;
import urunler.CPU;
import urunler.GPU;
import urunler.PreparedSystem;
import urunler.RAM;
import urunler.Storage;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stg; // static stage tanımı

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage; 
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Ürün Sayfası");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        cart_view_con.handleClearCarttxt();
        Image icon = new Image(getClass().getResourceAsStream("/images/logo.png"));
        stg.getIcons().add(icon);
    }

    
    public static void changeScene(String fxml) throws IOException {
        // FXML dosyasını yükle
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent pane = loader.load();

       
     // Eğer "admin_screen.fxml" ise, refresh_fun() çağır
        if (fxml.equals("homepage.fxml")) {
            // Controller'ı al
            homepage_con controller = loader.getController();
            
            // refresh_fun fonksiyonunu çağır
            controller.refresh_func();
        }


        // Yeni sahneyi ayarla
        stg.getScene().setRoot(pane);
        
    }
    //overload
    public static void changeScene(String fxml, double width, double height) throws IOException {
        // FXML dosyasını yükle
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent pane = loader.load();

       
        if (fxml.equals("homepage.fxml")) {
            // Controller'ı al
            homepage_con controller = loader.getController();
            
            // refresh_fun fonksiyonunu çağır
            controller.refresh_func();
        }

        // Yeni sahneyi ayarla
        stg.getScene().setRoot(pane);
        stg.setWidth(width);
        stg.setHeight(height);
        
    }
    
 
public static Customer cst ;
    public static void main(String[] args) {
    	SqLiteConnection.connector();
        SqLiteConnection.fillAll();
        cst = new Customer();
    	launch(args);
    	SqLiteConnection.insertAll();
    }
}
