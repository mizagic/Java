/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectlab;

import static Banco.ObjectFactory.getConexao;
import Dao.CadastroDao;
import Domain.Cadastro;

import Domain.Status;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FXMLControllerEncerra implements Initializable {

    Integer totalPa;
    Integer fechado;
    Integer andamento;
    String encerra;
    @FXML
    private TableView<Status> tableBusca;
    @FXML

    private TextField txtPa;
    @FXML
    private Button btnMenu;

    @FXML
    private TableColumn<Status, String> paid;
    @FXML
    private TableColumn<Status, String> data;
    @FXML
    private TableColumn<Status, String> indicador;
    @FXML
    private TableColumn<Status, String> responsavel;
    @FXML
    private TableColumn<Status, String> status;

    @FXML
    private TableColumn<Status, String> id;
    @FXML
    private TableColumn<Status, String> ind;
    @FXML
    private TableColumn<Status, String> responsa;
    @FXML
    private TableColumn<Status, String> stat;

    public void initTableStatus() {

        paid.setCellValueFactory(new PropertyValueFactory("paid"));
        data.setCellValueFactory(new PropertyValueFactory("data"));
        indicador.setCellValueFactory(new PropertyValueFactory("indicador"));
        responsavel.setCellValueFactory(new PropertyValueFactory("responsavel"));
        status.setCellValueFactory(new PropertyValueFactory("status"));

        tableBusca.setItems(atualizaTabela());

    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    public ObservableList<Status> atualizaTabela() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String dateStr;

        Date data = new Date();

        String sql = "select paid, data, indicador,responsavel,status,encerramento from diagramalab d  where paid =" + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                dateStr = rs.getString("data");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dataformatada = sdf.parse(dateStr);
                sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                dateStr = sdf.format(dataformatada);

                p.setPaid(rs.getString("paid"));//nome da coluna no BD 
                p.setData(dateStr);
                p.setIndicador(rs.getString("indicador"));//nome da coluna no BD
                p.setResponsavel(rs.getString("responsavel"));//nome da coluna no BD
                p.setStatus(rs.getString("status"));//nome da coluna no BDz
                p.setEncerramento(rs.getString("encerramento"));

               
                status.add(p);
                 encerra = p.getEncerramento();
                 
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(FXMLControllerEncerra.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FXCollections.observableArrayList(status);

    }

    public ObservableList<Status> atualizaTabelaAoVivo() {
        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        Date data = new Date();

        String sql = "select paid, indicador,responsavel status from diagramalab d order by paid ";

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setPaid(rs.getString("paid"));//nome da coluna no BD 
                p.setIndicador(rs.getString("indicador"));//nome da coluna no BD
                p.setResponsavel(rs.getString("responsavel"));//nome da coluna no BD
                p.setStatus(rs.getString("status"));//nome da coluna no BDz

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

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

    public void abreCadastro(javafx.event.ActionEvent event) throws IOException {
        String passarId = txtPa.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTelaNew.fxml"));
        Parent root = (Parent) loader.load();

//passa variavel para outro controller
        FXMLControllerTela controllerTela = loader.getController();
        controllerTela.pegaInfo(passarId);

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
        //stage.setTitle("Texto aqui");
        stage.setScene(scene);

        stage.show();

        fechar();
    }

    private void fechar() {
        Stage stage = (Stage) btnMenu.getScene().getWindow();
        stage.close();

    }

    public void Encerra(javafx.event.ActionEvent event) throws IOException {
        String passarId = txtPa.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTelaNew.fxml"));
        Parent root = (Parent) loader.load();

//passa variavel para outro controller
        FXMLControllerTela controllerTela = loader.getController();
        controllerTela.pegaInfo(passarId);

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
        //stage.setTitle("Texto aqui");
        stage.setScene(scene);

        stage.show();

        fechar();
    }

    public void encerrarPa() {
        if (encerra != null) {
            infoBox("Plano de ação já foi encerrado!", "Erro!", null);
        } else {
            String pa = txtPa.getText();
            CadastroDao cad = new CadastroDao();
            cad.encerrar(pa);
        }
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

}
