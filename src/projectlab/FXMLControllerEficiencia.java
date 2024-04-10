/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectlab;

import static Banco.ObjectFactory.getConexao;
import Domain.Cadastro;
import Domain.PierChart;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static projectlab.FXMLControllerEncerra.infoBox;

/**
 *
 * @author deividi-silva
 */
public class FXMLControllerEficiencia {

    String dataTermino;
    String encerra;
    Integer totalPa;
    Integer fechado;
    Integer andamento;
    @FXML
    private TableView<Status> tableBusca;
    @FXML
    private PieChart pieChart;
    @FXML
    private TableView<Status> tableStatus;
    @FXML
    private TextField txtPa;
    @FXML
    private TextField txtEficiencia1;
    @FXML
    private TextField txtEficiencia2;
    @FXML
    private TextField txtEficiencia3;
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
    @FXML
    private TableColumn<Status, String> inicio;
    @FXML
    private TableColumn<Status, String> acao;
    @FXML
    private TableColumn<Status, String> termino;
    @FXML
    private TableColumn<Status, String> eficiencia1;
     @FXML
    private TableColumn<Status, String> eficiencia2;
      @FXML
    private TableColumn<Status, String> eficiencia3;

    public void initTableAoVivo() throws ParseException {

        id.setCellValueFactory(new PropertyValueFactory("paid"));
        ind.setCellValueFactory(new PropertyValueFactory("indicador"));
        responsa.setCellValueFactory(new PropertyValueFactory("responsavel"));
        data.setCellValueFactory(new PropertyValueFactory("data"));
        stat.setCellValueFactory(new PropertyValueFactory("status"));
        eficiencia1.setCellValueFactory(new PropertyValueFactory("eficiencia"));
        eficiencia2.setCellValueFactory(new PropertyValueFactory("eficiencia2"));
        eficiencia3.setCellValueFactory(new PropertyValueFactory("eficiencia3"));

        tableStatus.setItems(atualizaTabelaAoVivo());
    }

    public void initialize(URL location, ResourceBundle resources) {
        buscaAnalise();
        buscaAndamento();
        buscaFechado();
        ObservableList<PieChart.Data> pierCharData = FXCollections.observableArrayList(
                new PieChart.Data("Total em Aberto: " + totalPa, totalPa),
                new PieChart.Data("Total Fechado: " + fechado, fechado),
                new PieChart.Data("Total em Andamento: " + andamento, andamento)
        );
        pieChart.getData().addAll(pierCharData);
    }

    public void atualizaEficiencia(javafx.event.ActionEvent event) throws IOException {
        String id = txtPa.getText();
        String eficiencia = txtEficiencia1.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String dateStr;

        Date data = new Date();

        String sql = "update diagramalab set eficiencia = '" + eficiencia + "' where paid =" + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Salvo com sucesso...");
            dialogoInfo.setContentText("");
            dialogoInfo.showAndWait();

            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
        public void atualizaEficiencia2(javafx.event.ActionEvent event) throws IOException {
        String id = txtPa.getText();
        String eficiencia2 = txtEficiencia2.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String dateStr;

        Date data = new Date();

        String sql = "update diagramalab set eficiencia_2 = '" + eficiencia2 + "' where paid =" + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Salvo com sucesso...");
            dialogoInfo.setContentText("");
            dialogoInfo.showAndWait();

            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
         public void atualizaEficiencia3(javafx.event.ActionEvent event) throws IOException {
        String id = txtPa.getText();
        String eficiencia3 = txtEficiencia3.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String dateStr;

        Date data = new Date();

        String sql = "update diagramalab set eficiencia_3 = '" + eficiencia3 + "' where paid =" + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Salvo com sucesso...");
            dialogoInfo.setContentText("");
            dialogoInfo.showAndWait();

            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public ObservableList<Status> atualizaTabelaAoVivo() throws ParseException {

        List<Status> status = new ArrayList<>();

        //  SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        //  String monthAndYear = formatter.format(new Date());
        // SimpleDateFormat sdf1 = new SimpleDateFormat("MM/yyyy");
        // Date dataFinal, dataAgora = null;
        String sql = "SELECT paid, indicador, responsavel,data, status, eficiencia, eficiencia_2, eficiencia_3 FROM diagramalab where status = 'Encerrada'";
        String dateStr;

        Date data = new Date();
        
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
                p.setIndicador(rs.getString("indicador"));//nome da coluna no BD
                p.setResponsavel(rs.getString("responsavel"));//nome da coluna no BD
                p.setData(dateStr);
                p.setStatus(rs.getString("status"));//nome da coluna no BDz
                p.setEficiencia(rs.getString("eficiencia"));//nome da coluna no BDz
                p.setEficiencia2(rs.getString("eficiencia_2"));//nome da coluna no BDz
                p.setEficiencia3(rs.getString("eficiencia_3"));//nome da coluna no BDz

                //p.setTermino(rs.getString("termino"));//nome da coluna no BDz
                // dataTermino = p.getTermino();
                // dataFinal = sdf1.parse(dataTermino);
                // dataAgora = formatter.parse(monthAndYear);
                //  if (dataFinal.compareTo(dataAgora) < 0) {
                //     p.setEficiencia("Atrasado");
                // } else {
                //     p.setEficiencia("No prazo");
                // }
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
        if (encerra != null) {
            infoBox("Plano de ação já foi encerrado!", "Erro!", null);
        } else {

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
    }

    private void fechar() {
        Stage stage = (Stage) btnMenu.getScene().getWindow();
        stage.close();

    }

    public void buscaAnalise() {
        //String responsavel = System.getProperty("user.name");
        List<PierChart> pier = new ArrayList<>();
        String total = null;

        String sql = "SELECT COUNT(*)  FROM diagramalab d where Status = 'Em Aberto' ";
        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                PierChart p = new PierChart();

                p.setTotalPa(rs.getInt("count"));//nome da coluna no BD 
                pier.add(p);
                totalPa = p.getTotalPa();
            }

            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void buscaFechado() {
        String responsavel = System.getProperty("user.name");
        List<PierChart> pier = new ArrayList<>();

        String sql = "SELECT COUNT(*)  FROM diagramalab d where Status = 'Encerrada'";
        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                PierChart p = new PierChart();

                p.setTotalPa(rs.getInt("count"));//nome da coluna no BD 
                pier.add(p);
                fechado = p.getTotalPa();
            }

            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void buscaAndamento() {
        //String responsavel = System.getProperty("user.name");
        List<PierChart> pier = new ArrayList<>();

        String sql = "SELECT COUNT(*)  FROM diagramalab d where Status = 'Em Andamento'";
        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                PierChart p = new PierChart();

                p.setTotalPa(rs.getInt("count"));//nome da coluna no BD 
                pier.add(p);
                andamento = p.getTotalPa();
            }

            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
