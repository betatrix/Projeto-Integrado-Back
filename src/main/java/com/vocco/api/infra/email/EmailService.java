package com.vocco.api.infra.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void enviarEmailRecuperacaoSenha(String para, String url) {
        log.info("Enviando email");
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(para);
            message.setSubject("Vocco - Recuperação de Senha");
            message.setText("Acesse o código e digite uma nova senha " + url);
            message.setFrom("voccosupp@outlook.com");
            emailSender.send(message);
            log.info("Email enviado");
        } catch (Exception e) {
            log.error("Erro ao enviar email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

