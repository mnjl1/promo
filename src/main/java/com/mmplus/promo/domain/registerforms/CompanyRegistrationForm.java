package com.mmplus.promo.domain.registerforms;

import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.domain.profiles.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class CompanyRegistrationForm {
    private String companyName;
    private String companyEmail;
    private String username;
    private String password;
    private String contractNumber;

    public Company toCompany(PasswordEncoder passwordEncoder){
        return new Company(username, passwordEncoder.encode(password), companyName, companyEmail, contractNumber);
    }
}
