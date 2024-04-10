package projectlab;

import static Banco.ObjectFactory.getConexao;
import Dao.CadastroDao;
import Domain.Cadastro;
import Domain.Matriz;
import Domain.Status;
import java.awt.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLControllerTela {

    public Status passarId;

    @FXML
    private TextField txtAcao;
    @FXML
    private TextField txtTermino;
    @FXML
    private TextField txtAnalise;
    @FXML
    private TextField txtEmpresa;
    @FXML
    private TextField txtPq2;

    @FXML
    private Label lblResponsa;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtResponsa;

    @FXML
    private TextField txtInicio;

    @FXML
    private TextField txtArea;

    @FXML
    private TextField txtOque;
    @FXML
    private TextField txtQuem;
    @FXML
    private TextField txtOnde;
    @FXML
    private TextField txtPorque;
    @FXML
    private TextField txtQuando;
    @FXML
    private TextField txtComo;
    @FXML
    private TextField txtQuanto;
    @FXML
    private Button btnPessoas;
    @FXML
    private Button btnMenu;

    @FXML
    private Button btnMetodo;
    @FXML
    private Button carrega;

    @FXML
    private TextField txtMaquina;

    @FXML
    private TextField txtCausa;

    @FXML
    private TextField txtIndicador;

    @FXML
    private TextField txtCausa_tratada;

    @FXML
    private TextField txtMetodo;

    @FXML
    private ListView<String> listMateriais;

    @FXML
    private TextField txtPessoas;

    @FXML
    private Button btnAmbiente;

    @FXML
    private TextField txtAmbiente;

    @FXML
    private TextField txtInfo;

    @FXML
    private ListView<String> listMetodo;

    @FXML
    private ListView<String> listAmbiente;

    @FXML
    private Button btnMateriais;

    @FXML
    private Button btnInfo;
    @FXML
    private Label lblId;
    @FXML
    private ListView<String> listMaquina;

    @FXML
    private ListView<String> listPessoas;

    @FXML
    private TextField txtMateriais;

    @FXML
    private ListView<String> listInfo;

  

    public void initialize(URL location, ResourceBundle resources) {
   MascarasFX mask = new MascarasFX();
   MascarasFX mask2 = new MascarasFX();
    mask.mascaraData(txtQuando);
     
    mask2.mascaraData(txtTermino);
    }

    @FXML
    void inserelistaMetodo(ActionEvent event) {

    }

    @FXML
    void removeMetodo(ActionEvent event) {

    }
////////////////////////////////////////MÉTODO//////////////////////////////////////////////////////////////////

    @FXML
    public void inserelistaMetodo() {
        listMetodo.getItems().add(txtMetodo.getText());
        txtMetodo.setText("");
    }

    @FXML
    public void removeMetodo() {
        int selectedID = listMetodo.getSelectionModel().getSelectedIndex();
        listMetodo.getItems().remove(selectedID);
    }
////////////////////////////////////////MÁQUINA//////////////////////////////////////////////////////////////////

    @FXML
    public void inserelistaMaq() {
        listMaquina.getItems().add(txtMaquina.getText());
        txtMaquina.setText("");
    }

    @FXML
    public void removeMaquina() {
        int selectedID = listMaquina.getSelectionModel().getSelectedIndex();
        listMaquina.getItems().remove(selectedID);
    }

    ////////////////////////////////////////Pessoas//////////////////////////////////////////////////////////////////
    @FXML
    public void inserelistaPessoas() {
        listPessoas.getItems().add(txtPessoas.getText());
        txtPessoas.setText("");
    }

    @FXML
    public void removePessoas() {
        int selectedID = listPessoas.getSelectionModel().getSelectedIndex();
        listPessoas.getItems().remove(selectedID);
    }
    ////////////////////////////////////////Ambiente//////////////////////////////////////////////////////////////////

    @FXML
    public void inserelistaAmbiente() {
        listAmbiente.getItems().add(txtAmbiente.getText());
        txtAmbiente.setText("");
    }

    @FXML
    public void removeAmbiente() {
        int selectedID = listAmbiente.getSelectionModel().getSelectedIndex();
        listAmbiente.getItems().remove(selectedID);
    }
    ////////////////////////////////////////Materiais//////////////////////////////////////////////////////////////////

    @FXML
    public void inserelistaMateriais() {
        listMateriais.getItems().add(txtMateriais.getText());
        txtMateriais.setText("");
    }

    @FXML
    public void removeMateriais() {
        int selectedID = listMateriais.getSelectionModel().getSelectedIndex();
        listMateriais.getItems().remove(selectedID);
    }

    ////////////////////////////////////////Informação//////////////////////////////////////////////////////////////////
    @FXML
    public void inserelistaInfo() {
        listInfo.getItems().add(txtInfo.getText());
        txtInfo.setText("");
    }

    @FXML
    public void removeInfo() {
        int selectedID = listInfo.getSelectionModel().getSelectedIndex();
        listInfo.getItems().remove(selectedID);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //INSERÇÃO NO BANCO DE DADOS
    //////////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void connectButton(javafx.event.ActionEvent event) {
        String id = lblId.getText();

        Cadastro cad = new Cadastro();
        CadastroDao cadastroDao = new CadastroDao();

        // DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // Date date = new Date();
        //ANALISE
        // cadastro.setDesc_analise(txtAnalise.getText());
        String metodo = listMetodo.getItems().stream()
                .map(Object::toString)
                .collect(Collectors.joining(".\n "));
        cad.setDesc_metodo(metodo);

        String maquina = listMaquina.getItems().stream()
                .map(Object::toString)
                .collect(Collectors.joining(".\n "));
        cad.setDesc_maquina(maquina);

        String pessoas = listPessoas.getItems().stream()
                .map(Object::toString)
                .collect(Collectors.joining(".\n "));
        cad.setDesc_pessoas(pessoas);

        String ambiente = listAmbiente.getItems().stream()
                .map(Object::toString)
                .collect(Collectors.joining(".\n "));
        cad.setDesc_ambiente(ambiente);

        String materiais = listMateriais.getItems().stream()
                .map(Object::toString)
                .collect(Collectors.joining(".\n "));
        cad.setDesc_materiais(materiais);

        String info = listInfo.getItems().stream()
                .map(Object::toString)
                .collect(Collectors.joining(".\n "));
        cad.setDesc_informacoes(info);

        //DIAGRAMALAB
        // cadastro.setEmpresa(txtEmpresa.getText());
        // cadastro.setData(dateFormat.format(date));
        // cadastro.setIndicador(txtIndicador.getText());
        // cadastro.setAcao(txtAcao.getText());
        // cadastro.setArea(txtArea.getText());
        // cadastro.setResponsa(lblResponsa.getText());
        cadastroDao.inserirAnalise(cad, id);
    }

    public void teste() {
        String responsavel = System.getProperty("user.name");
        lblResponsa.setText(responsavel);
    }

    public void preencheTxt() {
        String empresa;
        empresa = txtIndicador.getText();
        txtAnalise.setText(empresa);
        txtCausa_tratada.setText(empresa);
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

    public void pegaInfo(String id) {
        lblId.setText(id);
        buscaAnalise(id);
    }

    @FXML
    public void salvarMatriz(javafx.event.ActionEvent event) throws IOException {
        String id = lblId.getText();

        Matriz matriz = new Matriz();
        CadastroDao cadastroDao = new CadastroDao();

        //Matriz
        matriz.setOque(txtOque.getText());
        matriz.setQuem(txtQuem.getText());
        matriz.setOnde(txtOnde.getText());
        matriz.setPorque(txtPorque.getText());
        matriz.setQuando(txtQuando.getText());
        matriz.setComo(txtComo.getText());
        matriz.setQuanto(txtQuanto.getText());
        matriz.setTermino(txtTermino.getText());

        cadastroDao.inserirMatriz(matriz, id);

    }

    public void buscaAnalise(String id) {
        List<Cadastro> cadastro = new ArrayList<>();
        String descricao = null;
        String sql = "select descricao from analise where id_analise = '" + id + "'";
        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Cadastro p = new Cadastro();

                p.setDescricaoAnalise(rs.getString("descricao"));//nome da coluna no BD 
                cadastro.add(p);

                descricao = p.getDescricaoAnalise();
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        pegaAnalise(descricao);
    }

    public void pegaAnalise(String descricao) {
        txtAnalise.setText(descricao);

    }

    public void mascara(){
    MascarasFX mask = new MascarasFX();
    mask.mascaraData(txtQuando);
   
    }
      public void mascaraTermino(){
    MascarasFX mask = new MascarasFX();
    mask.mascaraData(txtTermino);
   
    }

}
