package br.ufscar.dc.dsw.promonstraomvc.service.impl;

import br.ufscar.dc.dsw.promonstraomvc.dao.IUserDAO;
import br.ufscar.dc.dsw.promonstraomvc.domain.User;
import br.ufscar.dc.dsw.promonstraomvc.service.spec.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {

    private final IUserDAO userDAO;

    @Autowired
    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}
