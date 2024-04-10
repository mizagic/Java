/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectlab;

import static Banco.ObjectFactory.getConexao;
import Domain.Cadastro;
import Domain.Status;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author deividi-silva
 */
public class FXMLControllerBuscaAdmin implements Initializable {
    @FXML
    private TableView<Status> tableBusca;
    @FXML
    private ImageView imageBanco;
    @FXML
    private TableView<Status> tableMetodo;
    @FXML
    private TableView<Status> tableMaquinas;
    @FXML
    private TableView<Status> tablePessoas;
    @FXML
    private TableView<Status> tableAmbiente;
    @FXML
    private TableView<Status> tableMateriais;
    @FXML
    private TableView<Status> tableInformacoes;
    @FXML
    private TableView<Status> tableAnalise;
    @FXML
    private TableView<Status> tableOque;
    @FXML
    private TableView<Status> tableQuem;
    @FXML
    private TableView<Status> tableOnde;
    @FXML
    private TableView<Status> tablePorque;
    @FXML
    private TableView<Status> tableQuando;
    @FXML
    private TableView<Status> tableComo;
    @FXML
    private TableView<Status> tableQuanto;

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
    private TableColumn<Status, String> empresa;
    @FXML
    private TableColumn<Status, String> acao;
    @FXML
    private TableColumn<Status, String> area;
    @FXML
    private TableColumn<Status, String> emissao;
    @FXML
    private TableColumn<Status, String> encerramento;
    @FXML
    private TableColumn<Status, String> descMetodo;
    @FXML
    private TableColumn<Status, String> descPessoas;
    @FXML
    private TableColumn<Status, String> descAmbiente;
    @FXML
    private TableColumn<Status, String> descMateriais;
    @FXML
    private TableColumn<Status, String> descMaquinas;
    @FXML
    private TableColumn<Status, String> descInformacoes;
    @FXML
    private TableColumn<Status, String> descAnalise;
    @FXML
    private TableColumn<Status, String> descOque;
    @FXML
    private TableColumn<Status, String> descQuem;
    @FXML
    private TableColumn<Status, String> descOnde;
    @FXML
    private TableColumn<Status, String> descPorque;
    @FXML
    private TableColumn<Status, String> descQuando;
    @FXML
    private TableColumn<Status, String> descComo;
    @FXML
    private TableColumn<Status, String> descQuanto;

    public void initTableStatus() {

        paid.setCellValueFactory(new PropertyValueFactory("paid"));
        empresa.setCellValueFactory(new PropertyValueFactory("empresa"));
        data.setCellValueFactory(new PropertyValueFactory("data"));
        indicador.setCellValueFactory(new PropertyValueFactory("indicador"));
        acao.setCellValueFactory(new PropertyValueFactory("acao"));
        area.setCellValueFactory(new PropertyValueFactory("area"));
        responsavel.setCellValueFactory(new PropertyValueFactory("responsavel"));
        status.setCellValueFactory(new PropertyValueFactory("status"));
        emissao.setCellValueFactory(new PropertyValueFactory("emissao"));
        encerramento.setCellValueFactory(new PropertyValueFactory("encerramento"));

        tableBusca.setItems(atualizaTabela());

    }

    public void connectButton(javafx.event.ActionEvent event) throws IOException {
        initTableStatus();
        atualizaFoto();
        initTableMetodo();
        initTableMaquinas();
        initTablePessoas();
        initTableAmbiente();
        initTableMateriais();
        initTableInformacoes();
        initTableAnalise();
        initTableOque();
        initTableQuem();
        initTableOnde();
        initTablePorque();
        initTableQuando();
        initTableComo();
        initTableQuanto();

    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    public ObservableList<Status> atualizaTabela() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String dateStr;
        String encerraData;
        String novaDataencerrada;

        Date data = new Date();

        String sql = "SELECT paid, empresa, \"data\", indicador, acao, area, responsavel, status, emissao, encerramento FROM public.diagramalab where paid =" + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                dateStr = rs.getString("data");
                encerraData = rs.getString("encerramento");

                novaDataencerrada = dataEncerrada(encerraData);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dataformatada = sdf.parse(dateStr);
                sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                dateStr = sdf.format(dataformatada);

                p.setPaid(rs.getString("paid"));//nome da coluna no BD 
                p.setEmpresa(rs.getString("empresa"));
                p.setData(dateStr);
                p.setIndicador(rs.getString("indicador"));//nome da coluna no BD
                p.setAcao(rs.getString("acao"));
                p.setArea(rs.getString("area"));
                p.setResponsavel(rs.getString("responsavel"));//nome da coluna no BD
                p.setStatus(rs.getString("status"));//nome da coluna no BDz
                p.setEmissao(rs.getString("emissao"));
                p.setEncerramento(novaDataencerrada);

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(FXMLControllerEncerra.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FXCollections.observableArrayList(status);

    }

    public ObservableList<Status> atualizaFoto() throws FileNotFoundException, IOException {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();

        String sql = "SELECT imagem FROM public.foto where id_diagrama =" + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();
                InputStream is = rs.getBinaryStream("imagem");
                OutputStream os = new FileOutputStream(new File("imagem.jpg"));
                byte[] content = new byte[1024];
                int size = 0;
                while ((size = is.read(content)) != -1) {
                    os.write(content, 0, size);
                }
                os.close();
                is.close();
                Image imagex = new Image("file:imagem.jpg", 250, 250, true, true);
                imageBanco.setImage(imagex);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return FXCollections.observableArrayList(status);

    }

    public String dataEncerrada(String data) {
        String datanew = null;

        if (data == null) {
            datanew = "Não encerrado";

        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dataformatada = sdf.parse(data);
                sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                data = sdf.format(dataformatada);
            } catch (ParseException ex) {
                Logger.getLogger(FXMLControllerBusca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return datanew;
    }
// >>>>>>>>>>>>>>>>>>>>>>>>>>  TABELA MÉTODO  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void initTableMetodo() {

        descMetodo.setCellValueFactory(new PropertyValueFactory("desc_metodo"));

        tableMetodo.setItems(atualizaTabelaMetodo());

    }

    public ObservableList<Status> atualizaTabelaMetodo() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT descricao FROM public.metodo where id_analise = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setDesc_metodo(rs.getString("descricao"));//nome da coluna no BD 

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

    }
// >>>>>>>>>>>>>>>>>>>>>>>>>>  TABELA MÁQUINAS  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void initTableMaquinas() {

        descMaquinas.setCellValueFactory(new PropertyValueFactory("desc_maquina"));

        tableMaquinas.setItems(atualizaTabelaMaquinas());

    }

    public ObservableList<Status> atualizaTabelaMaquinas() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT descricao FROM public.maquinas where id_analise = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setDesc_maquina(rs.getString("descricao"));//nome da coluna no BD 

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

    }
// >>>>>>>>>>>>>>>>>>>>>>>>>>  TABELA PESSOAS  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void initTablePessoas() {

        descPessoas.setCellValueFactory(new PropertyValueFactory("desc_pessoas"));

        tablePessoas.setItems(atualizaTabelaPessoas());

    }

    public ObservableList<Status> atualizaTabelaPessoas() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT descricao FROM public.pessoas where id_analise = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setDesc_pessoas(rs.getString("descricao"));//nome da coluna no BD 

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

    }
// >>>>>>>>>>>>>>>>>>>>>>>>>>  TABELA AMBIENTE  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void initTableAmbiente() {

        descAmbiente.setCellValueFactory(new PropertyValueFactory("desc_ambiente"));

        tableAmbiente.setItems(atualizaTabelaAmbiente());

    }

    public ObservableList<Status> atualizaTabelaAmbiente() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT descricao FROM public.ambiente where id_analise = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setDesc_ambiente(rs.getString("descricao"));//nome da coluna no BD 

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

    }
// >>>>>>>>>>>>>>>>>>>>>>>>>>  TABELA MATERIAIS  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void initTableMateriais() {

        descMateriais.setCellValueFactory(new PropertyValueFactory("desc_materiais"));

        tableMateriais.setItems(atualizaTabelaMateriais());

    }

    public ObservableList<Status> atualizaTabelaMateriais() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT descricao FROM public.materiais where id_analise = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setDesc_materiais(rs.getString("descricao"));//nome da coluna no BD 

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

    }
// >>>>>>>>>>>>>>>>>>>>>>>>>>  TABELA INFORMACOES  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void initTableInformacoes() {

        descInformacoes.setCellValueFactory(new PropertyValueFactory("desc_informacoes"));

        tableInformacoes.setItems(atualizaTabelaInfo());

    }

    public ObservableList<Status> atualizaTabelaInfo() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT descricao FROM public.informacoes where id_analise = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setDesc_informacoes(rs.getString("descricao"));//nome da coluna no BD 

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

    }
// >>>>>>>>>>>>>>>>>>>>>>>>>>  TABELA ANALISE  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void initTableAnalise() {

        descAnalise.setCellValueFactory(new PropertyValueFactory("descricaoAnalise"));

        tableAnalise.setItems(atualizaTabelaAnalise());

    }

    public ObservableList<Status> atualizaTabelaAnalise() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT descricao FROM public.analise where id_analise = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setDescricaoAnalise(rs.getString("descricao"));//nome da coluna no BD 

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

    }

    public void initTableOque() {

        descOque.setCellValueFactory(new PropertyValueFactory("oque"));

        tableOque.setItems(atualizaTabelaOque());

    }

    public ObservableList<Status> atualizaTabelaOque() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT descricao FROM public.oque where causaid = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setOque(rs.getString("descricao"));//nome da coluna no BD 

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

    }

    public void initTableQuem() {

        descQuem.setCellValueFactory(new PropertyValueFactory("quem"));

        tableQuem.setItems(atualizaTabelaQuem());

    }

    public ObservableList<Status> atualizaTabelaQuem() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT descricao FROM public.quem where causaid = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setQuem(rs.getString("descricao"));//nome da coluna no BD 

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

    }

    public void initTableOnde() {

        descOnde.setCellValueFactory(new PropertyValueFactory("onde"));

        tableOnde.setItems(atualizaTabelaOnde());

    }

    public ObservableList<Status> atualizaTabelaOnde() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT descricao FROM public.onde where causaid = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setOnde(rs.getString("descricao"));//nome da coluna no BD 

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

    }

    public void initTablePorque() {

        descPorque.setCellValueFactory(new PropertyValueFactory("porque"));

        tablePorque.setItems(atualizaTabelaPorque());

    }

    public ObservableList<Status> atualizaTabelaPorque() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT descricao FROM public.porque where causaid = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setPorque(rs.getString("descricao"));//nome da coluna no BD 

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

    }

    public void initTableQuando() {

        descQuando.setCellValueFactory(new PropertyValueFactory("quando"));

        tableQuando.setItems(atualizaTabelaQuando());

    }

    public ObservableList<Status> atualizaTabelaQuando() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT inicio FROM public.quando where causaid = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setQuando(rs.getString("inicio"));//nome da coluna no BD 

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

    }

    public void initTableComo() {

        descComo.setCellValueFactory(new PropertyValueFactory("como"));

        tableComo.setItems(atualizaTabelaComo());

    }

    public ObservableList<Status> atualizaTabelaComo() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT descricao FROM public.como where causaid = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setComo(rs.getString("descricao"));//nome da coluna no BD 

                status.add(p);
            }
            prs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(status);

    }

    public void initTableQuanto() {

        descQuanto.setCellValueFactory(new PropertyValueFactory("quanto"));

        tableQuanto.setItems(atualizaTabelaQuanto());

    }

    public ObservableList<Status> atualizaTabelaQuanto() {
        String id = txtPa.getText();

        List<Status> status = new ArrayList<>();
        Cadastro cad = new Cadastro();

        String sql = "SELECT descricao FROM public.quanto where causaid = " + id;

        try {
            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            while (rs.next()) {
                Status p = new Status();

                p.setQuanto(rs.getString("descricao"));//nome da coluna no BD 

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

    private void fechar() {
        Stage stage = (Stage) btnMenu.getScene().getWindow();
        stage.close();

    }
}
