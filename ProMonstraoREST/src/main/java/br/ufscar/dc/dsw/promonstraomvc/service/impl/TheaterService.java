package br.ufscar.dc.dsw.promonstraomvc.service.impl;

import br.ufscar.dc.dsw.promonstraomvc.dao.ITheaterDAO;
import br.ufscar.dc.dsw.promonstraomvc.domain.Theater;
import br.ufscar.dc.dsw.promonstraomvc.service.spec.ITheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService implements ITheaterService {

    private final ITheaterDAO theaterDAO;

    @Autowired
    public TheaterService(ITheaterDAO theaterDAO) {
        this.theaterDAO = theaterDAO;
    }

    @Override
    public Theater save(Theater theater) {
        return theaterDAO.save(theater);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Theater> findAll() {
        return theaterDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Theater> findById(Long id) {
        return theaterDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Theater>> findAllByCity(String city) {
        return theaterDAO.findAllByCity(city);
    }

    @Override
    public void deleteById(Long id) {
        theaterDAO.deleteById(id);
    }
}
