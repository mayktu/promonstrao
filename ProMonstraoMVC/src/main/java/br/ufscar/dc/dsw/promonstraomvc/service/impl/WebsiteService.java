package br.ufscar.dc.dsw.promonstraomvc.service.impl;

import br.ufscar.dc.dsw.promonstraomvc.dao.IWebsiteDAO;
import br.ufscar.dc.dsw.promonstraomvc.domain.Website;
import br.ufscar.dc.dsw.promonstraomvc.service.spec.IWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WebsiteService implements IWebsiteService {

    IWebsiteDAO websiteDAO;

    @Autowired
    public WebsiteService(IWebsiteDAO websiteDAO) {
        this.websiteDAO = websiteDAO;
    }

    @Override
    public Website save(Website website) {
        return websiteDAO.save(website);
    }

    @Transactional(readOnly = true)
    public List<Website> findAll() {
        return websiteDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Website> findById(Long id) {
        return websiteDAO.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        websiteDAO.deleteById(id);
    }
}
