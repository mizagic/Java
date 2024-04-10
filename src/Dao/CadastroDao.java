/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Banco.ObjectFactory;
import Domain.Cadastro;
import Domain.Matriz;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;


public class CadastroDao {

    @FXML
    private TextField txtMetodo;
    @FXML
    private ImageView imgFoto;

    @FXML
    private ListView<?> listMateriais;

    @FXML
    private TextField txtInfo;

    @FXML
    private TextField txtPessoas;

    @FXML
    private TextField txtAnalise;
    @FXML
    private Button btnPessoas;

    @FXML
    private Button btnAmbiente;

    @FXML
    private Button btnMetodo;

    @FXML
    private TextField txtAmbiente;

    @FXML
    private TextField txtMaquina;

    @FXML
    private ListView<?> listMetodo;

    @FXML
    private ListView<?> listAmbiente;

    @FXML
    private Button btnMateriais;

    @FXML
    private Button btnInfo;

    @FXML
    private ListView<?> listMaquina;

    @FXML
    private ListView<?> listPessoas;

    @FXML
    private TextField txtMateriais;

    @FXML
    private TextField txtResponsa;
    @FXML
    private ListView<?> listInfo;

    @FXML
    private Button btnMaquina;

    public void inserir(Cadastro cadastro) throws IOException {
//INSERE NA TABELA RAIZ
        String sql;

        //INSERE NA TABELA DIAGRAMA
        sql = "INSERT INTO public.causa(descricao)"
                + " VALUES ("
                + "'" + cadastro.getDescCausa() + "');"
                + //INSERE NA TABELA ANALISE
                "INSERT INTO public.analise(descricao)"
                + " VALUES ("
                + "'" + cadastro.getDesc_analise() + "');"
                + "INSERT INTO public.diagramalab(empresa, data , indicador, acao, area,responsavel,status,emissao)"
                + " VALUES ("
                + "'" + cadastro.getEmpresa() + "',"
                + "now(),"
                + "'" + cadastro.getIndicador() + "',"
                + "'" + cadastro.getAcao() + "',"
                + "'" + cadastro.getArea() + "',"
                + "'" + cadastro.getResponsa() + "',"
                + "'Em Aberto',"
                + "'" + cadastro.getEmissao() + "');";

        
        ObjectFactory.executeSql(sql);  
    }

    public void inserirAnalise(Cadastro cadastro, String id) {
//INSERE NA TABELA RAIZ
        String sql;

        //INSERE NA TABELA DIAGRAMA
        sql
                = //INSERE NA TABELA Analise
                "INSERT INTO public.metodo(descricao,id_analise)"
                + " VALUES ("
                + "'" + cadastro.getDesc_metodo() + "'," + id + ");"
                + //INSERE NA TABELA MÁQUINAS
                "INSERT INTO public.maquinas(descricao,id_analise)"
                + " VALUES ("
                + "'" + cadastro.getDesc_maquina() + "'," + id + ");"
                + //INSERE NA TABELA PESSOAS
                "INSERT INTO public.pessoas(descricao,id_analise)"
                + " VALUES ("
                + "'" + cadastro.getDesc_pessoas() + "'," + id + ");"
                + //INSERE NA TABELA AMBIENTE
                "INSERT INTO public.ambiente(descricao,id_analise)"
                + " VALUES ("
                + "'" + cadastro.getDesc_ambiente() + "'," + id + ");"
                + //INSERE NA TABELA MATERIAIS
                "INSERT INTO public.materiais(descricao,id_analise)"
                + " VALUES ("
                + "'" + cadastro.getDesc_materiais() + "'," + id + ");"
                + //INSERE NA TABELA INFORMACOES
                "INSERT INTO public.informacoes(descricao,id_analise)"
                + " VALUES ("
                + "'" + cadastro.getDesc_informacoes() + "'," + id + ");";

        ObjectFactory.executeSql(sql);
        infoBox("Diagrama enviado!", "Successo", null);

    }

    public void inserirMatriz(Matriz matriz, String id) {
//INSERE NA TABELA RAIZ
        String sql;

        //INSERE NA TABELA DIAGRAMA
        sql
                = //INSERE NA TABELA OQUE
                "INSERT INTO public.oque(descricao,causaid)"
                + " VALUES ("
                + "'" + matriz.getOque() + "'," + id + ");"
                + //INSERE NA TABELA QUEM
                "INSERT INTO public.quem(descricao,causaid)"
                + " VALUES ("
                + "'" + matriz.getQuem() + "'," + id + ");"
                + //INSERE NA TABELA ONDE
                "INSERT INTO public.onde(descricao,causaid)"
                + " VALUES ("
                + "'" + matriz.getOnde() + "'," + id + ");"
                + //INSERE NA TABELA PORQUE
                "INSERT INTO public.porque(descricao,causaid)"
                + " VALUES ("
                + "'" + matriz.getPorque() + "'," + id + ");"
                + //INSERE NA TABELA QUANDO
                "INSERT INTO public.quando(inicio,causaid,termino)"
                + " VALUES ("
                + "'" + matriz.getQuando()+ "'," + id + ", '"+ matriz.getTermino() +"');"
                + //INSERE NA TABELA COMO
                "INSERT INTO public.como(descricao,causaid)"
                + " VALUES ("
                + "'" + matriz.getComo() + "'," + id + ");"
                + //INSERE NA TABELA QUANTO
                "INSERT INTO public.quanto(descricao,causaid)"
                + " VALUES ("
                + "'" + matriz.getQuanto() + "'," + id + ");";

        ObjectFactory.executeSql(sql);
        infoBox("Salvo com Sucesso!", "Successo", null);
        andamento(id);

    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    public void encerrar(String id) {
//INSERE NA TABELA RAIZ
        String sql;

        //INSERE NA TABELA DIAGRAMA
        sql = "Update public.diagramalab set status ='Encerrada', encerramento = now() where paid  =" + id;

        ObjectFactory.executeSql(sql);
        infoBox("Plano de ação Encerrado!", "Successo", null);
    }
     public void andamento(String id) {
//INSERE NA TABELA RAIZ
        String sql;

        //INSERE NA TABELA DIAGRAMA
        sql = "Update public.diagramalab set status ='Em andamento' where paid  =" + id;

        ObjectFactory.executeSql(sql);
       // infoBox("Plano de ação Em andamento!", "Successo", null);
    }


   

}
