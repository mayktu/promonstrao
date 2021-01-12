package br.ufscar.dc.dsw.promonstraomvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends AbstractEntity<Long> {

    @NotBlank
    @Column(nullable = false, length = 256, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 64)
    private String password;

    @NotBlank
    @Column(nullable = false, length = 256)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 10)
    private final String role;

    protected User() {
        this.role = "USER";
    };

    protected User(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

}
