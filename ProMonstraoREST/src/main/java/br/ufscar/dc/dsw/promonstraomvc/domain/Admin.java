package br.ufscar.dc.dsw.promonstraomvc.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Admin extends User {

    private static final String ROLE_NAME = "ADMIN";

    public Admin(String email, String password, String name) {
        super(ROLE_NAME);
        setEmail(email);
        setPassword(password);
        setName(name);      
    }

    public Admin() {
        super(ROLE_NAME);
    }

    @Override
    public String toString() {
        return "Theater{" +
                "email='" + this.getEmail() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", role='" + this.getRole() + '\'' +
                '}';
    }
}
