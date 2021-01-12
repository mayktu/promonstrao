package br.ufscar.dc.dsw.promonstraomvc.dao;

import br.ufscar.dc.dsw.promonstraomvc.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUserDAO extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE email = :email")
    User findByEmail(String email);
}
