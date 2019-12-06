package com.mmplus.promo.domain.registerforms;

import com.mmplus.promo.domain.profiles.Company;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CompanyRegistrationForm {
    private String companyName;
    private String companyEmail;
    private String username;
    private String password;
    private String contractNumber;

    public Company toCompany(PasswordEncoder passwordEncoder){
        return new Company(username, passwordEncoder.encode(password), companyName, companyEmail, contractNumber);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
}
