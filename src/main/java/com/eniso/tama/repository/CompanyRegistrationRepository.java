package com.eniso.tama.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.CompanyRegistration;
@Repository
public interface CompanyRegistrationRepository extends JpaRepository<CompanyRegistration, Long> {
	
	 List<CompanyRegistration> findAllByDeletedFalse() ; 
	 Optional<CompanyRegistration>  findByIdAndDeletedFalse(long id) ; 

	 @Query
		( nativeQuery=true, value="SELECT * from company_registration cr where cr.entreprise_id =?1 and cr.deleted= FALSE ;")
	List<CompanyRegistration> findByEntrepriseIdAndDeletedFalse(long entrepId);
	 
	 @Query
		( "SELECT cr FROM CompanyRegistration cr WHERE cr.programinstance.id =?1 and cr.deleted= TRUE  ")
	List<CompanyRegistration> findByProgramInstanceIdAndDeletedFalse(long programInstance);
}