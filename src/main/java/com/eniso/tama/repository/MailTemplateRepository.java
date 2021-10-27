package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.MailTemplate;
@Repository
public interface MailTemplateRepository extends JpaRepository<MailTemplate, String> {

}
