package com.mmplus.promo.service;

import com.mmplus.promo.domain.profiles.Company;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public interface CompanyService  {

    Company saveOrUpdateCompany(Company company);

    Company findCompanyByUserName(String username);

    Optional<Company> findCompanyById(Long id);
}
