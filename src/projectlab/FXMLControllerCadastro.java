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
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class FXMLControllerCadastro implements Initializable {

    @FXML
    private ComboBox<String> comboBox3;

    @FXML
    private ComboBox<String> comboBox2;

    @FXML
    private ComboBox<String> comboBox1;

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
    private Label lblEmissao;
    @FXML
    private Button btnMenuVolta;

    @FXML
    public void connectButton(javafx.event.ActionEvent event) throws IOException {

        if (comboBox1.getItems().isEmpty() || comboBox1.getItems().isEmpty()) {

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("FAVOR PREENCHER TODOS OS CAMPOS");
            dialogoInfo.setContentText("");
            dialogoInfo.showAndWait();

        } else {
            String destinatario = null;
            String assunto;
            String emailcopia = null;
            String emailCopia2 = null;;

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
            cadastro.setEmissao(lblEmissao.getText());

            //ANALISE DESCRICAO
            cadastro.setDesc_analise(txtIndicador.getText());
            destinatario = comboBox1.getValue();
            emailcopia = comboBox2.getValue();
            emailCopia2 = comboBox3.getValue();

            cadastro.setResponsa(destinatario);
            cadastro.setEmailcopia(emailcopia);
            cadastro.setEmailcopia2(emailCopia2);

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

        lblEmissao.setText(responsavel);
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
        lblEmissao.setText("");
       
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboBox1.setItems(FXCollections.observableArrayList("adriani-evaldt@flecksteel.com.br", "ana-filippsen@flecksteel.com.br", "angela-flores@flecksteel.com.br", "bruna-duarte@flecksteel.com.br", "bruna-pain@flecksteel.com.br", "bruna-santos@flecksteel.com.br", "danilo-siqueira@flecksteel.com.br",
                "daniel-souza@flecksteel.com.br", "deividi-silva@flecksteel.com.br", "fabiano-santos@flecksteel.com.br", "felipe-munari@flecksteel.com.br", "gessica-blume@flecksteel.com.br", "ismael@flecksteel.com.br", "jairo-simon@flecksteel.com.br",
                "joelson-kaiser@flecksteel.com.br", "kezia-ferreira@flecksteel.com.br", "monica-sinigaglia@flecksteel.com.br", "nicoli-abreu@flecksteel.com.br", "odair-rambo@flecksteel.com.br", "pamela-camargo@flecksteel.com.br",
                "patricia-brustolin@flecksteel.com.br", "rafael-frohlich@flecksteel.com.br", "rafael-lima@flecksteel.com.br", "rosangela-hirt@flecksteel.com.br", "sandro-mello@flecksteel.com.br", "vinicius-bitencourte@flecksteel.com.br", "vinicius-petry@flecksteel.com.br", "vitor-schneider@flecksteel.com.br"));

        comboBox2.setItems(FXCollections.observableArrayList("adriani-evaldt@flecksteel.com.br", "ana-filippsen@flecksteel.com.br", "angela-flores@flecksteel.com.br", "bruna-duarte@flecksteel.com.br", "bruna-pain@flecksteel.com.br", "bruna-santos@flecksteel.com.br", "danilo-siqueira@flecksteel.com.br",
                "daniel-souza@flecksteel.com.br", "deividi-silva@flecksteel.com.br", "fabiano-santos@flecksteel.com.br", "felipe-munari@flecksteel.com.br", "gessica-blume@flecksteel.com.br", "ismael@flecksteel.com.br", "jairo-simon@flecksteel.com.br",
                "joelson-kaiser@flecksteel.com.br", "kezia-ferreira@flecksteel.com.br", "monica-sinigaglia@flecksteel.com.br", "nicoli-abreu@flecksteel.com.br", "odair-rambo@flecksteel.com.br", "pamela-camargo@flecksteel.com.br",
                "patricia-brustolin@flecksteel.com.br", "rafael-frohlich@flecksteel.com.br", "rafael-lima@flecksteel.com.br", "rosangela-hirt@flecksteel.com.br", "sandro-mello@flecksteel.com.br", "vinicius-bitencourte@flecksteel.com.br", "vinicius-petry@flecksteel.com.br", "vitor-schneider@flecksteel.com.br"));

        comboBox3.setItems(FXCollections.observableArrayList("adriani-evaldt@flecksteel.com.br", "ana-filippsen@flecksteel.com.br", "angela-flores@flecksteel.com.br", "bruna-duarte@flecksteel.com.br", "bruna-pain@flecksteel.com.br", "bruna-santos@flecksteel.com.br", "danilo-siqueira@flecksteel.com.br",
                "daniel-souza@flecksteel.com.br", "deividi-silva@flecksteel.com.br", "fabiano-santos@flecksteel.com.br", "felipe-munari@flecksteel.com.br", "gessica-blume@flecksteel.com.br", "ismael@flecksteel.com.br", "jairo-simon@flecksteel.com.br",
                "joelson-kaiser@flecksteel.com.br", "kezia-ferreira@flecksteel.com.br", "monica-sinigaglia@flecksteel.com.br", "nicoli-abreu@flecksteel.com.br", "odair-rambo@flecksteel.com.br", "pamela-camargo@flecksteel.com.br",
                "patricia-brustolin@flecksteel.com.br", "rafael-frohlich@flecksteel.com.br", "rafael-lima@flecksteel.com.br", "rosangela-hirt@flecksteel.com.br", "sandro-mello@flecksteel.com.br", "vinicius-bitencourte@flecksteel.com.br", "vinicius-petry@flecksteel.com.br", "vitor-schneider@flecksteel.com.br"));
    }

}
