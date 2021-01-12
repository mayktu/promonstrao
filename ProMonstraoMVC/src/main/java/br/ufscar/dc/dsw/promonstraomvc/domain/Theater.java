package br.ufscar.dc.dsw.promonstraomvc.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Theater extends User {

    private static final String ROLE_NAME = "THEATER";

    @NotBlank
    @Column(nullable = false, unique = true)
    private String cnpj;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Sale> sales;

    public Theater(String email, String password, String name, String cnpj, City city) {
        super(ROLE_NAME);
        setEmail(email);
        setPassword(password);
        setName(name);
        this.cnpj = cnpj;
        this.city = city;
    }

    public Theater() {
        super(ROLE_NAME);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "email='" + this.getEmail() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", city='" + city.getName() + '\'' +
                ", role='" + this.getRole() + '\'' +
                '}';
    }
}
