/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectlab;

import Banco.ObjectFactory;
import Dao.CadastroDao;
import Domain.Cadastro;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author deividi-silva
 */
public class FXMLControllerCadastro {

    @FXML
    private TextField txtCopia2;
    @FXML
    private TextField txtCopia;
    File foto;
    @FXML
    private TextField txtDestinatario;
    @FXML
    private TextField txtIndicador;

    @FXML
    private TextField txtArea;
    @FXML
    private ImageView imgFoto;

    @FXML
    private TextField txtEmpresa;

    @FXML
    private TextField txtAcao;

    @FXML
    private Label lblResponsa;
    @FXML
    private Button btnMenuVolta;

    @FXML
    public void connectButton(javafx.event.ActionEvent event) throws IOException {

        if (txtCopia.getText().isEmpty() || txtCopia2.getText().isEmpty()) {

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("FAVOR PREENCHER TODOS OS CAMPOS");
            dialogoInfo.setContentText("");
            dialogoInfo.showAndWait();

        } else {
            String destinatario;
            String assunto;
            String emailcopia;
            String emailCopia2;

            Cadastro cadastro = new Cadastro();
            CadastroDao cadastroDao = new CadastroDao();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            //DIAGRAMALAB
            cadastro.setEmpresa(txtEmpresa.getText());
            cadastro.setData(dateFormat.format(date));
            cadastro.setIndicador(txtIndicador.getText());
            cadastro.setAcao(txtAcao.getText());
            cadastro.setArea(txtArea.getText());
            cadastro.setResponsa(txtDestinatario.getText());
            cadastro.setEmissao(lblResponsa.getText());
            cadastro.setEmailcopia(txtCopia.getText());
            cadastro.setEmailcopia2(txtCopia2.getText());
            //ANALISE DESCRICAO
            cadastro.setDesc_analise(txtIndicador.getText());
            destinatario = txtDestinatario.getText() + "@flecksteel.com.br";
            emailcopia = txtCopia.getText() + "@flecksteel.com.br";
            emailCopia2 = txtCopia2.getText() + "@flecksteel.com.br";
            assunto = txtIndicador.getText();

            //File foto = selecionaFoto();
            cadastroDao.inserir(cadastro);

            //busca ultimo id do banco
            String sql = "SELECT MAX(paid) from diagramalab d ";
            ObjectFactory.executeSqlEmail(destinatario, assunto, sql, emailcopia, emailCopia2, foto);
            ObjectFactory.executeFoto(foto);

            limpar();

            voltaMenu(event);
        }
    }

    public void teste() {
        String responsavel = System.getProperty("user.name");

        lblResponsa.setText(responsavel);
    }

    public void preencheTxt() {
        String empresa;

        empresa = txtIndicador.getText();

    }

    public void selecionaFoto() throws IOException {
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.png", "*.jpeg"));
        File file = f.showOpenDialog(new Stage());
        BufferedImage bf;

        bf = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bf, null);
        imgFoto.setImage(image);
        foto = file;
        //return file;
    }

    public void voltaMenu(javafx.event.ActionEvent event) throws IOException {
        Stage stage = new Stage();
        fechar();

        Parent fxmlForm = FXMLLoader.load(getClass().getResource("FXMLTelaMenuAdmin.fxml"));
        // Parent fxmlForm2 = FXMLLoader.load(getClass().getResource("FXMLMenu.fxml"));
        stage.setResizable(false);
        Scene scene = new Scene(fxmlForm);
        stage.setScene(scene);
        stage.show();
    }

    private void fechar() {
        Stage stage = (Stage) btnMenuVolta.getScene().getWindow();
        stage.close();
    }

    private void limpar() {
        txtEmpresa.setText("");
        txtIndicador.setText("");
        txtAcao.setText("");
        txtArea.setText("");
        lblResponsa.setText("");
        txtDestinatario.setText("");
    }

}
