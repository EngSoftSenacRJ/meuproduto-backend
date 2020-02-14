package senac.edu.engsoft.meuproduto.service.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.mail.smtp.SMTPAddressSucceededException;
import com.sun.mail.smtp.SMTPTransport;

import senac.edu.engsoft.meuproduto.model.ParametroEnum;
import senac.edu.engsoft.meuproduto.service.ParametroServiceImpl;

public class EmailUtil {

	private static final Logger logger = LogManager.getLogger(EmailUtil.class);
	
	public static boolean sendEmail(String emailTo) {
		
		String emailFrom = ParametroServiceImpl.parametroMap.get(ParametroEnum.EMAIL_FROM).getValor();
		String password = ParametroServiceImpl.parametroMap.get(ParametroEnum.PASSWORD).getValor();
		String smtpServer = ParametroServiceImpl.parametroMap.get(ParametroEnum.SMTP_SERVER).getValor();
		String smtpServerPort = ParametroServiceImpl.parametroMap.get(ParametroEnum.SMTP_SERVER_PORT).getValor();
		String username = ParametroServiceImpl.parametroMap.get(ParametroEnum.USERNAME).getValor();
		String newPasswordUrl = ParametroServiceImpl.parametroMap.get(ParametroEnum.NEW_PASSWORD_URL).getValor();
		
		Properties prop = System.getProperties();
        prop.put("mail.smtp.host", smtpServer);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", smtpServerPort);
		
        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);
        
        try {
            msg.setFrom(new InternetAddress(emailFrom));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo, false));
            msg.setSubject("MeuProduto - Troca de Senha");
            msg.setText("Troque a senha nessa URL: "+newPasswordUrl);
            msg.setSentDate(new Date());

            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
            t.connect(smtpServer, username, password);
			
            t.sendMessage(msg, msg.getAllRecipients());
            t.setReportSuccess(true);
            System.out.println("Response: " + t.getLastServerResponse());
            t.close();
            
            return true;
        } catch (SMTPAddressSucceededException e) {
        	logger.error("Falha ao realizar o envio do email. [EMAIL_FROM]: "+emailFrom+", [SMTP_SERVER]: "+smtpServer+" e [SMTP_SERVER_PORT]: "+smtpServerPort+", para: "+emailTo);
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
        return false;
	}
}
