/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projectlab;

import Banco.ObjectFactory;
import static Banco.ObjectFactory.getConexao;
import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLControllerAdmin implements Initializable {

    @FXML
    private TextField txt_user;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnMenu;
    
    @FXML
    private PasswordField txt_password;
    Connection connection = null;
    PreparedStatement prs = null;
    ResultSet rs = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnLogin.setDefaultButton(true);
    }

    public void confirmaLogin(ActionEvent event) throws IOException {
        String user = txt_user.getText().toString();
        String password = txt_password.getText().toString();

        String sql = "SELECT * FROM login WHERE usuario = ? and senha = ?";
        try {
            prs = getConexao().getConnection().prepareStatement(sql);

            prs.setString(1, user);
            prs.setString(2, password);
            rs = prs.executeQuery();

            if (!rs.next()) {
                infoBox("Usuário ou senha estão incorretos", "Erro de Login!", null);
            } else {

                Node source = (Node) event.getSource();
                Stage stage = new Stage();
                stage = (Stage) source.getScene().getWindow();
                stage.close();

           
                if (user.equals("laboratorio")) {
                    infoBox("Bem vindo!", "Successo", null);
                    Parent fxmlForm = FXMLLoader.load(getClass().getResource("FXMLTelaMenuAdmin.fxml"));
                    // Parent fxmlForm2 = FXMLLoader.load(getClass().getResource("FXMLMenu.fxml"));
                    stage.setResizable(false);
                    Scene scene = new Scene(fxmlForm);
                    stage.setScene(scene);
                    stage.show();

                }
            }
            prs.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void voltaMenu(javafx.event.ActionEvent event) throws IOException {
        Stage stage = new Stage();
        fechar();

        Parent fxmlForm = FXMLLoader.load(getClass().getResource("FXMLTelaMenu.fxml"));
        // Parent fxmlForm2 = FXMLLoader.load(getClass().getResource("FXMLMenu.fxml"));
        stage.setResizable(false);
        Scene scene = new Scene(fxmlForm);
        stage.setScene(scene);
        stage.show();

    }
     private void fechar() {
        Stage stage = (Stage) btnMenu.getScene().getWindow();
        stage.close();

    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

}
