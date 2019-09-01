package com.mmplus.promo.service;

import com.mmplus.promo.repository.CompanyRepository;
import com.mmplus.promo.domain.profiles.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CompanyRepositoryUserDetailsService implements UserDetailsService   {
    private CompanyRepository companyRepository;

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
}
