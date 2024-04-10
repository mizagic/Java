/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectlab;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author deividi-silva
 */
public class ProjectLab extends Application {

  
    private static Stage stage;
    private static Scene form;
    private static Scene menu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Menu");

        // Parent fxmlForm = FXMLLoader.load(getClass().getResource("FXMLFormulario.fxml"));
        // form = new Scene(fxmlForm);
        Parent fxmlMenu = FXMLLoader.load(getClass().getResource("FXMLTelaMenu.fxml"));
        menu = new Scene(fxmlMenu);
        primaryStage.setResizable(false);
        primaryStage.setScene(menu);
        primaryStage.show();
        
   

    }

}
