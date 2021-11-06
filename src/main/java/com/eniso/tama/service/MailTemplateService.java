package com.eniso.tama.service;

import com.eniso.tama.dto.Notification;
import com.eniso.tama.entity.MailTemplate;

public interface MailTemplateService {

	public MailTemplate findMailTemplate(String templateId) ;
	//public void sendUserValidation(Notification notification) ;
	public void sendUserValidation(String from,String to ) ;
	public void sendmailAlert(String from , String to , String programInstanceName, String ProgramInstanceLocation) ;

}
