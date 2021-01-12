package br.ufscar.dc.dsw.promonstraomvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.ufscar.dc.dsw.promonstraomvc.dao.IUserDAO;
import br.ufscar.dc.dsw.promonstraomvc.domain.User;
 
public class UsuarioDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private IUserDAO dao;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = dao.findByEmail(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        
        System.out.println("Email: " + user.getEmail());
        System.out.println("Papel: " + user.getRole());
        System.out.println("Senha: " + user.getPassword() + "\n");
         
        return new UsuarioDetails(user);
    }
}