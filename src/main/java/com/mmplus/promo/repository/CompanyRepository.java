package com.mmplus.promo.repository;

import com.mmplus.promo.domain.profiles.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    Company findByUsername(String username);

    Company findCompanyByContractNumber(String contractNumber);

    Optional<Company> findById(Long id);

}
