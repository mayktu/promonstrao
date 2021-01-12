package br.ufscar.dc.dsw.promonstraomvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class City extends AbstractEntity<Long> {

    @NotBlank
    @Column(nullable = false, length = 128, unique = true)
    private String name;

    @OneToMany(mappedBy = "city")
    private List<Theater> Theaters;

    public City(String name) {
        this.name = name;
    }

    public City() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                '}';
    }
}
