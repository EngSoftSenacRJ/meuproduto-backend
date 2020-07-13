package senac.edu.engsoft.meuproduto.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.model.Usuario;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    static final String FROM = "meuprodutosenac@gmail.com";
    static final String FROMNAME = "MeuProduto";
//    static final String SMTP_USERNAME = "AKIA5DE5DCNOEFAP4W6N";
static final String SMTP_USERNAME = "AKIA5DE5DCNOF6DDGY5X";
//    static final String SMTP_PASSWORD = "BCLTS9PXdBnjUt8CuaB2qHjf5U+vfDqJ+vYqGJwsui6M";
static final String SMTP_PASSWORD = "BO+0U1M4JDj94I05PpN0gxe6aY9z1BXybJmuLVWvgjsn";
    static final String HOST = "email-smtp.us-east-1.amazonaws.com";
    static final int PORT = 587;
    static final String SUBJECT = "Bem Vindo ao MeuProduto - Valide seu E-Mail";

    private JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
        Usuario usuario = new Usuario();
        usuario.setUsername("rderociml@gmail.com");
        usuario.setId(1L);
        usuario.setTokenValidacaoEmail("abc");
        new EmailServiceImpl(null).sendEmailValidacaoNovoUsuario(usuario, "baseUrl");
    }

    @Async
    @Override
    public void sendEmailValidacaoNovoUsuario(Usuario usuario, String baseUrl) throws MessagingException, UnsupportedEncodingException {
//        try {
//            String urlValidacaoEmail = baseUrl + "/register/confirmEmail?id=" +
//                    usuario.getId() + "&token=" + usuario.getTokenValidacaoEmail();
//
//            MimeMessage mail = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper( mail );
//
//            helper.setTo(usuario.getUsername());
//            helper.setSubject("Bem Vindo ao MeuProduto - Valide seu E-Mail");
//            helper.setText("<h3>Bem vindo ao <span style=\"background-color: #02034f; color: #ffffff; padding: 0 3px;\">MeuProduto</span></h3>"+
//                            "<p><strong>Você solicitou o cadastro no MeuProduto utilizando esta conta de e-mail, para ativá-la acesse o link abaixo:</strong></p>"+
//                            "<p>"+urlValidacaoEmail+"</p>", true);
//            mailSender.send(mail);
//        } catch (Exception e) {
//            throw e;
//        }

        String urlValidacaoEmail = baseUrl + "/api/register/confirmEmail?id=" +
                    usuario.getId() + "&token=" + usuario.getTokenValidacaoEmail();
        String body = "<h3>Bem vindo ao <span style=\"background-color: #02034f; color: #ffffff; padding: 0 3px;\">MeuProduto</span></h3>"+
                "<p><strong>Você solicitou o cadastro no MeuProduto utilizando esta conta de e-mail, para ativá-la acesse o link abaixo:</strong></p>"+
                "<p>"+urlValidacaoEmail+"</p>";

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM,FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(usuario.getUsername()));
        msg.setSubject(SUBJECT);
        msg.setContent(body,"text/html");

        // Add a configuration set header. Comment or delete the
        // next line if you are not using a configuration set
//        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

        try (Transport transport = session.getTransport()) {
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
    }
}
