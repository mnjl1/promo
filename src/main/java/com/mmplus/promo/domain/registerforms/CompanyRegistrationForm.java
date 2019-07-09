package com.mmplus.promo.domain.registerforms;

import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.domain.profiles.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class CompanyRegistrationForm {
    private String companyName;
    private String companyEmail;
    private Integer zkpo;
    private String username;
    private String password;

    public Company toCompany(PasswordEncoder passwordEncoder){
        return new Company(username, passwordEncoder.encode(password), companyName, companyEmail, zkpo);
    }
}
