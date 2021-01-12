package br.ufscar.dc.dsw.promonstraomvc.service.spec;

import br.ufscar.dc.dsw.promonstraomvc.domain.Website;

import java.util.List;
import java.util.Optional;

public interface IWebsiteService {

    Website save(Website website);

    List<Website> findAll();

    Optional<Website> findById(Long id);

    void deleteById(Long id);
}
