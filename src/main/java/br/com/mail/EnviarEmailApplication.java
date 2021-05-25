package br.com.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import br.com.mail.service.EmailService;

@SpringBootApplication
public class EnviarEmailApplication {
	
	@Autowired
	private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(EnviarEmailApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() {
		emailService.sendSimpleEmail("jonatasluisprates@hotmail.com", 
				" Seja Bem Vindo", "EscolaTi agradece");
	}

}
