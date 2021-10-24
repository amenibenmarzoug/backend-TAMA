package com.eniso.tama.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.dto.Notification;
import com.eniso.tama.service.MailTemplateService;
@RestController
@CrossOrigin(origins = "*")
@ComponentScan(basePackageClasses = MailTemplateService.class)
@RequestMapping("/api")
public class MailTemplateController {

	
	@Autowired
	private MailTemplateService mailTemplateService;
	

	//@Autowired
	public MailTemplateController(MailTemplateService theEmailService) {
		mailTemplateService = theEmailService;
	}
	
    @PostMapping("/email")
    public ResponseEntity<Notification> enviarEmail(@RequestBody Notification notif) {
    	System.out.println(notif) ;
        try {
        	mailTemplateService.sendMailUsingTemplate(notif);
           return new ResponseEntity<Notification>(notif,  HttpStatus.OK);
        } catch( MailException e){
            return new ResponseEntity<Notification>(notif, HttpStatus.INTERNAL_SERVER_ERROR);
    	//return email;
        }
    }
	
}
