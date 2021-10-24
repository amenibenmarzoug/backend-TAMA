package com.eniso.tama.service;

import com.eniso.tama.dto.Notification;
import com.eniso.tama.entity.MailTemplate;

public interface MailTemplateService {

	public MailTemplate findMailTemplate(String templateId) ;
	public void sendMailUsingTemplate(Notification notification) ;
	//public void sendMailValidationUser(Notification notification) ;


}
