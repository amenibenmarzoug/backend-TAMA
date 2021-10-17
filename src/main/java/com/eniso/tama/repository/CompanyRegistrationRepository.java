package com.eniso.tama.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.eniso.tama.entity.CompanyRegistration;

@Repository
public interface CompanyRegistrationRepository extends JpaRepository<CompanyRegistration, Long> {

	 @Query
		( nativeQuery=true, value="SELECT * from company_registration cr where cr.entreprise_id =?1 ;")
	List<CompanyRegistration> findByEntrepriseId(long entrepId);
	 
	 @Query
		( "SELECT cr FROM CompanyRegistration cr WHERE cr.programinstance.id =?1")
	List<CompanyRegistration> findByProgramInstanceId(long programInstance);
}