package br.ufscar.dc.dsw.promonstraomvc.service.spec;

import br.ufscar.dc.dsw.promonstraomvc.domain.User;

public interface IUserService {

    User findByEmail(String email);
}
