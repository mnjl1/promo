package com.mmplus.promo.domain.profiles;

import com.mmplus.promo.domain.Item;
import com.mmplus.promo.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Data
//@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
@Entity
public class Company extends User {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = Constants.MESSAGE_COMPANY_NAME_REQUIRED)
    private String companyName;

    @NotBlank
    @Email
    private String companyEmail;


    //todo create @Digits rejex validation
    private Integer zkpo;

    public Company(){

    }

    @ManyToMany(targetEntity = Item.class)
    private Set<Item> items;

    public Company(String companyName, String companyEmail) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
    }



        public Company(String username, String password, String companyName, String companyEmail) {
        super(username, password);
        this.companyName = companyName;
        this.companyEmail = companyEmail;
    }

    public Company(@NotBlank(message = Constants.MESSAGE_COMPANY_NAME_REQUIRED) String companyName,
                   @NotBlank @Email String companyEmail,
                   @NotBlank Integer zkpo) {
        super();
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.zkpo = zkpo;
    }


    public Company(String username, String password, @NotBlank(message = Constants.MESSAGE_COMPANY_NAME_REQUIRED)
            String companyName, @NotBlank @Email String companyEmail, @NotBlank Integer zkpo) {
        super(username, password);
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.zkpo = zkpo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_COMPANY"));
    }
}
