package com.mmplus.promo.service;

import com.mmplus.promo.exceptions.ContractNotFoundException;
import com.mmplus.promo.repository.CompanyRepository;
import com.mmplus.promo.domain.profiles.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CompanyRepositoryUserDetailsService implements UserDetailsService   {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyRepositoryUserDetailsService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Company company = companyRepository.findByUsername(username);
        if (company !=  null){
            return company;
        }
        else throw new  UsernameNotFoundException("Username '" +username +"' not found.");
    }

    public void saveOrUpdate(Company company){
        companyRepository.save(company);
    }

    public Iterable<Company> findAll(){
        return companyRepository.findAll();
    }

    public Company findCompanyByContractNumber(String contractNumber){
        return companyRepository.findCompanyByContractNumber(contractNumber);
    }
}
