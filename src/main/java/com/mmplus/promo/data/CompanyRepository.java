package com.mmplus.promo.data;

import com.mmplus.promo.domain.profiles.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    Company findByUsername(String username);
}
