package br.ufscar.dc.dsw.promonstraomvc.service.spec;

import br.ufscar.dc.dsw.promonstraomvc.domain.Sale;

import java.util.List;
import java.util.Optional;

public interface ISaleService {

    List<Sale> findAllByTheater(Long theaterId);

    List<Sale> findAllByWebsite(Long websiteId);

    Sale save(Sale sale);
    
    Optional<Sale> findById(Long id);
    
    void deleteById(Long id);
}