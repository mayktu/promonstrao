package br.ufscar.dc.dsw.promonstraomvc.dao;

import br.ufscar.dc.dsw.promonstraomvc.domain.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@SuppressWarnings("unchecked")
public interface ICityDAO extends CrudRepository<City, Long> {

    List<City> findAll();

    City save(City city);
}
