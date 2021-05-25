package br.com.mail.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendSimpleEmail(String toEmail, String body, String subject) {

		SimpleMailMessage mensagem = new SimpleMailMessage();
		mensagem.setFrom("email");
		mensagem.setTo(toEmail);
		mensagem.setText(body);
		mensagem.setSubject(subject);

		mailSender.send(mensagem);
		System.out.println("Email enviado com sucesso!");

	}

	public void sendEmailWithAttachment(String toEmail, String body, String subject, String attachment)
			throws MessagingException {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setFrom("email");
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);

		FileSystemResource fileSystem = new FileSystemResource(new File(attachment));

		mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);

		mailSender.send(mimeMessage);
		System.out.println("Email enviado com sucesso!");

	}
}
