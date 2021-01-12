package br.ufscar.dc.dsw.promonstraomvc.dao;

import br.ufscar.dc.dsw.promonstraomvc.domain.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IAdminDAO extends CrudRepository<Admin, Long> {

    Admin save(Admin a);
    
}
