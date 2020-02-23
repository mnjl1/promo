package com.mmplus.promo.service;

import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private  final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company saveOrUpdateCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company findCompanyByUserName(String username) {
        return companyRepository.findByUsername(username);
    }

    @Override
    public Optional<Company> findCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public boolean companyContractExists(String contractNumber) {
        Company company = companyRepository.findCompanyByContractNumber(contractNumber);
        return company != null;
    }
}
