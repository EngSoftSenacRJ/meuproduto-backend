//package senac.edu.engsoft.meuproduto.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import senac.edu.engsoft.meuproduto.service.EmailService;
//
//@RestController
//@RequestMapping({"/emails"})
//public class EmailController {
//
//	private EmailService emailService;
//
//	public EmailController(EmailService emailService) {
//		this.emailService = emailService;
//	}
//
//	@ResponseStatus(value=HttpStatus.OK)
//	@PostMapping
//	public ResponseEntity<Object> sendEmail() {
//		emailService.sendEmail();
//		return ResponseEntity.status(HttpStatus.OK).build();
//	}
//
//}
