package br.ufscar.dc.dsw.promonstraomvc.dao;

import br.ufscar.dc.dsw.promonstraomvc.domain.Sale;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ISaleDAO extends CrudRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE s.theater.id = :theaterId")
    List<Sale> findAllByTheater(Long theaterId);

    @Query("SELECT s FROM Sale s WHERE s.website.id = :websiteId")
    List<Sale> findAllByWebsite(Long websiteId);

    Sale save(Sale s);
    
    Optional<Sale> findById(Long id);
    
    void deleteById(Long id);
}
