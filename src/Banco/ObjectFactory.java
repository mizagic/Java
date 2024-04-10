/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.ResultSet;
import javax.mail.Message.RecipientType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author deividi-silva
 */
public class ObjectFactory {

    @FXML
    private TextField txt_os;
    @FXML
    private TextField txt_setor;
    @FXML
    private TextField txt_data;
    @FXML
    private TextField txt_cracha;
    @FXML
    private Button btn_salvar;
    static DataBaseConnection conexao;

    public static DataBaseConnection getConexao() {
        if (conexao == null) {
            conexao = new DataBaseConnection();
            conexao.setUrl("jdbc:postgresql://192.168.0.225:5432/fleckprd");
            conexao.setUsuario("fleckprd");
            conexao.setSenha("fleckprd");
            conexao.getConnection();
        }
        return conexao;
    }

    public void limpar() {

    }

    public static void executeSql(String sql) {

        try {

            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            prs.execute();

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Salvo com sucesso...");
            dialogoInfo.setContentText("");
            dialogoInfo.showAndWait();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void executeFoto(File file) throws FileNotFoundException {
        int id = buscaUltimoId();

        String sql = "INSERT INTO public.foto(imagem,id_diagrama)VALUES(? ," + id + ");";

        FileInputStream fin = new FileInputStream(file);
        int len = (int) file.length();
        try {

            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            prs.setBinaryStream(1, fin, len);
            prs.execute();

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Salvo com sucesso...");
            dialogoInfo.setContentText("");
            dialogoInfo.showAndWait();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void loginExecuteSql(String sql) {

        try {

            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            prs.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void executeSqlUpdate(String sql) {

        try {

            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            prs.execute();

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Salvo com sucesso...");
            dialogoInfo.setContentText("");
            dialogoInfo.showAndWait();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void executeSqlEmail(String destinatario, String assunto, String sql, String emailCopia, String emailCopia2, File file) throws IOException {
        int ids = 0;
        final String username = "bruna-santos@flecksteel.com.br";  // like yourname@outlook.com
        final String password = "bruna2024";
         //final String username = "deividi-silva@flecksteel.com.br";  // like yourname@outlook.com
        //   final String password = "mizael2021";
        try {

            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            if (rs.next()) {
                ids = rs.getInt(1);
            }
            System.out.println("Id gerado pelo insert foi " + ids);

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Salvo com sucesso...");
            dialogoInfo.setContentText("");
            dialogoInfo.showAndWait();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //###############   EMAIL ############################################################
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            message.addRecipient(RecipientType.CC, new InternetAddress(
                    emailCopia));// copia de email
            message.addRecipient(RecipientType.CC, new InternetAddress(
                    emailCopia2));// lcopia de email
            message.setSubject("PLANO DE AÇÃO");
            //message.setText("Olá! tem um novo plano de ação atribuído a você!\n" + "Não conformidade: " + assunto + "\nPlano de ação nº: " + ids);

            MimeBodyPart part1 = new MimeBodyPart();
            part1.setText("Olá!" + " \nTem um novo plano de ação atribuído a você!" + "Não conformidade: " + assunto + "\nPlano de ação nº: " + ids);

            MimeBodyPart part2 = new MimeBodyPart();
            part2.attachFile(file);

            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(part1);
            mimeMultipart.addBodyPart(part2);

            message.setContent(mimeMultipart);

            Transport.send(message);

            System.out.println("Done");

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Email enviado!");
            dialogoInfo.setHeaderText("Um email foi enviado para " + destinatario);
            dialogoInfo.setContentText("");
            dialogoInfo.showAndWait();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static int buscaUltimoId() {
        int ids = 0;
        String sql = "SELECT MAX(paid) from diagramalab d ";

        try {

            PreparedStatement prs = getConexao().getConnection().prepareStatement(sql);
            ResultSet rs = prs.executeQuery();

            if (rs.next()) {
                ids = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ids;

    }
}
