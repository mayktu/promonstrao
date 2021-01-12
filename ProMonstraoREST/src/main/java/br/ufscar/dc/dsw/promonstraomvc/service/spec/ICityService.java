package br.ufscar.dc.dsw.promonstraomvc.service.spec;

import br.ufscar.dc.dsw.promonstraomvc.domain.City;

import java.util.List;

public interface ICityService {

    List<City> buscarTodos();

    City save(City city);
}
