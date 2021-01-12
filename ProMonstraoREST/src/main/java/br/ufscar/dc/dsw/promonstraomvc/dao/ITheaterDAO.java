package br.ufscar.dc.dsw.promonstraomvc.dao;

import br.ufscar.dc.dsw.promonstraomvc.domain.Theater;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ITheaterDAO extends CrudRepository<Theater, Long> {

    Theater save(Theater s);

    List<Theater> findAll();

    Optional<Theater> findById(Long id);

    void deleteById(Long id);

    @Query("SELECT t FROM Theater t, City c WHERE c.name = :city AND c.id = t.city")
    Optional<List<Theater>> findAllByCity(String city);
}
