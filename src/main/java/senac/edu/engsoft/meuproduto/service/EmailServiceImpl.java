package senac.edu.engsoft.meuproduto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(){
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( "rderociml@gmail.com" );
            helper.setSubject( "Teste Envio de e-mail" );
            helper.setText("<p>Hello from Spring Boot Application</p>", true);
            mailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
