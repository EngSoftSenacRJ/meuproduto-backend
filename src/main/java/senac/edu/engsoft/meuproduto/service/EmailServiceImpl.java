package senac.edu.engsoft.meuproduto.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.model.Usuario;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    @Override
    public void sendEmailValidacaoNovoUsuario(Usuario usuario, String baseUrl) throws MessagingException {
        try {
            String urlValidacaoEmail = baseUrl + "/register/confirmEmail?id=" +
                    usuario.getId() + "&token=" + usuario.getTokenValidacaoEmail();

            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper( mail );

            helper.setTo(usuario.getUsername());
            helper.setSubject("Bem Vindo ao MeuProduto - Valide seu E-Mail");
            helper.setText("<h3>Bem vindo ao <span style=\"background-color: #02034f; color: #ffffff; padding: 0 3px;\">MeuProduto</span></h3>"+
                            "<p><strong>Você solicitou o cadastro no MeuProduto utilizando esta conta de e-mail, para ativá-la acesse o link abaixo:</strong></p>"+
                            "<p>"+urlValidacaoEmail+"</p>", true);
            mailSender.send(mail);
        } catch (Exception e) {
            throw e;
        }
    }
}
