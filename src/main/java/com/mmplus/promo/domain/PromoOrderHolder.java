package com.mmplus.promo.domain;

import com.mmplus.promo.domain.activity.PromoOrder;
import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.utils.Constants;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class PromoOrderHolder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt;

    @NotBlank(message = Constants.MESSAGE_NAME_REQUIRED)
    private String name;

    private Company company;

    //todo fix phone-number validation only with rejex
    @Digits(integer = 10, fraction = 0, message = Constants.MESSAGE_CORRECT_PHONE_NUMBER)
    private String contactPhoneNumber;

    @ManyToMany(targetEntity = PromoOrder.class)
    private List<PromoOrder> promoOrders = new ArrayList<>();

    public PromoOrderHolder() {
    }

    public PromoOrderHolder(Date placedAt, @NotBlank(message = Constants.MESSAGE_NAME_REQUIRED) String name,
                            Company company, @Digits(integer = 10, fraction = 0,
                            message = Constants.MESSAGE_CORRECT_PHONE_NUMBER) String contactPhoneNumber,
                            List<PromoOrder> promoOrders) {
        this.placedAt = placedAt;
        this.name = name;
        this.company = company;
        this.contactPhoneNumber = contactPhoneNumber;
        this.promoOrders = promoOrders;
    }

    public void addPromoOrder(PromoOrder promoOrder){
        this.promoOrders.add(promoOrder);
    }

    @PrePersist
    void createdAt(){
        this.placedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<PromoOrder> getPromoOrders() {
        return promoOrders;
    }

    public void setPromoOrders(List<PromoOrder> promoOrders) {
        this.promoOrders = promoOrders;
    }
}
