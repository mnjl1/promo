package com.mmplus.promo.domain.profiles;

import com.mmplus.promo.domain.Item;
import com.mmplus.promo.utils.Constants;
import net.bytebuddy.build.ToStringPlugin;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;


@Entity
@Table(name = "company")
public class Company extends User {

    private static final long serialVersionUID = 1L;

    private String companyName;

    private String contractNumber;

    @Email
    private String companyEmail;

    //todo create @Digits rejex validation
    private int zkpo;

    private boolean isRegistered;

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST,
    CascadeType.MERGE})
    @JoinTable(name = "company_items",
    joinColumns = @JoinColumn(name = "company_id"),
    inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items = new HashSet<>();


    public Company(String username, String password,
                   String companyName, String contractNumber, @Email String companyEmail) {
        super(username, password);
        this.companyName = companyName;
        this.contractNumber = contractNumber;
        this.companyEmail = companyEmail;
    }

    public Company(Long id, String username, String password,
            String companyName, String contractNumber, @Email String companyEmail) {
        super(id, username, password);
        this.companyName = companyName;
        this.contractNumber = contractNumber;
        this.companyEmail = companyEmail;
    }

    public Company(Long id, String username, String password,
            String companyName, String contractNumber, @Email String companyEmail,
                   Set<Item> items, int zkpo) {
        super(id, username, password);
        this.companyName = companyName;
        this.contractNumber = contractNumber;
        this.companyEmail = companyEmail;
        this.items = items;
        this.zkpo = zkpo;
    }

    public Company(){

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_COMPANY"));
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public int getZkpo() {
        return zkpo;
    }

    public void setZkpo(int zkpo) {
        this.zkpo = zkpo;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public void addItem(Item item){
        items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(getCompanyName(), company.getCompanyName()) &&
                Objects.equals(getContractNumber(), company.getContractNumber()) &&
                Objects.equals(getCompanyEmail(), company.getCompanyEmail()) &&
                Objects.equals(getItems(), company.getItems()) &&
                Objects.equals(getZkpo(), company.getZkpo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompanyName(), getContractNumber(), getCompanyEmail(), getItems(), getZkpo());
    }
}
