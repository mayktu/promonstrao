package br.ufscar.dc.dsw.promonstraomvc.dao;

import br.ufscar.dc.dsw.promonstraomvc.domain.Website;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IWebsiteDAO extends CrudRepository<Website, Long> {

    Website save(Website s);

    List<Website> findAll();

    Optional<Website> findById(Long id);

    void deleteById(Long id);
}