package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.Usuario;

import javax.mail.MessagingException;

public interface EmailService {
    public void sendEmailValidacaoNovoUsuario(Usuario usuario, String baseUrl) throws MessagingException;
}
