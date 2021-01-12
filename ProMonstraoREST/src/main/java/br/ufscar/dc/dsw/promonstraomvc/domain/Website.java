package br.ufscar.dc.dsw.promonstraomvc.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Website extends User {

    private static final String ROLE_NAME = "WEBSITE";

    @NotBlank
    @Column(nullable = false, length = 256)
    private String url;

    @NotBlank
    @Column(name = "phone_number", nullable = false, length = 32)
    private String phoneNumber;

    @OneToMany(mappedBy = "website", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Sale> sales;

    public Website() {
        super(ROLE_NAME);
    }

    public Website(String email, String password, String name, String url, String phoneNumber) {
        super(ROLE_NAME);
        setEmail(email);
        setPassword(password);
        setName(name);
        this.url = url;
        this.phoneNumber = phoneNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Website{" +
                "email='" + this.getEmail() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", url='" + url + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role='" + this.getRole() + '\'' +
                '}';
    }
}
