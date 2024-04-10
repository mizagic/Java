/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import com.mysql.cj.conf.StringProperty;

public class Cadastro {

    public String getEmailcopia() {
        return emailcopia;
    }

    public void setEmailcopia(String emailcopia) {
        this.emailcopia = emailcopia;
    }

    public String getEmailcopia2() {
        return emailcopia2;
    }

    public void setEmailcopia2(String emailcopia2) {
        this.emailcopia2 = emailcopia2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEncerramento() {
        return encerramento;
    }

    public void setEncerramento(String encerramento) {
        this.encerramento = encerramento;
    }

    public String getDescricaoAnalise() {
        return descricaoAnalise;
    }

    public void setDescricaoAnalise(String descricaoAnalise) {
        this.descricaoAnalise = descricaoAnalise;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescCausa() {
        return descCausa;
    }

    public void setDescCausa(String descCausao) {
        this.descCausa = descCausa;
    }

    public String getResponsa() {
        return responsa;
    }

    public void setResponsa(String responsa) {
        this.responsa = responsa;
    }

    //DIAGRAMALAB
    private static String id;
    private String empresa;
    private String data;
    private String indicador;
    private String acao;
    private String area;
    private String responsa;
    private String status;
    private String emissao;
    private String encerramento;
    private String emailcopia;
    private String emailcopia2;

    //CAUSA
    private String causa_tratada;
    private String descCausa;

    //ANÁLISE
    private String desc_analise;
    private String descricaoAnalise;

    private String desc_metodo;

    private String desc_maquina;

    private String desc_pessoas;

    private String desc_ambiente;
    private String desc_materiais;
    private String desc_informacoes;

    public String getDesc_maquina() {
        return desc_maquina;
    }

    public void setDesc_maquina(String desc_maquina) {
        this.desc_maquina = desc_maquina;
    }

    public String getDesc_pessoas() {
        return desc_pessoas;
    }

    public void setDesc_pessoas(String desc_pessoas) {
        this.desc_pessoas = desc_pessoas;
    }

    public String getDesc_ambiente() {
        return desc_ambiente;
    }

    public void setDesc_ambiente(String desc_ambiente) {
        this.desc_ambiente = desc_ambiente;
    }

    public String getDesc_materiais() {
        return desc_materiais;
    }

    public void setDesc_materiais(String desc_materiais) {
        this.desc_materiais = desc_materiais;
    }

    public String getDesc_informacoes() {
        return desc_informacoes;
    }

    public void setDesc_informacoes(String desc_informacoes) {
        this.desc_informacoes = desc_informacoes;
    }

    public String getDesc_analise() {
        return desc_analise;
    }

    public void setDesc_analise(String desc_analise) {
        this.desc_analise = desc_analise;
    }

    public String getDesc_metodo() {
        return desc_metodo;
    }

    public void setDesc_metodo(String desc_metodo) {
        this.desc_metodo = desc_metodo;
    }

    public String getCausa_tratada() {
        return causa_tratada;
    }

    public void setCausa_tratada(String causa_tratada) {
        this.causa_tratada = causa_tratada;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEmissao() {
        return emissao;
    }

    public void setEmissao(String emissao) {
        this.emissao = emissao;
    }

}
