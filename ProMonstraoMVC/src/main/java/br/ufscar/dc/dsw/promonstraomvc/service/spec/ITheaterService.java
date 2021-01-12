package br.ufscar.dc.dsw.promonstraomvc.service.spec;

import br.ufscar.dc.dsw.promonstraomvc.domain.Theater;

import java.util.List;
import java.util.Optional;

public interface ITheaterService {

    Theater save(Theater theater);

    List<Theater> findAll();

    Optional<Theater> findById(Long id);

    void deleteById(Long id);

    Optional<List<Theater>> findAllByCity(String city);
}
