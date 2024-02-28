package com.demo.demo.ServiceImpl;

import com.demo.demo.Repository.DomaineRepository;
import com.demo.demo.Service.DomaineSevice;
import com.demo.demo.dtos.DomaineDTo;
import com.demo.demo.entity.Candidate;
import com.demo.demo.entity.Domaine;
import com.demo.demo.mappers.DomaineMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DomaineServiceImpl implements DomaineSevice {
private  final DomaineRepository domaineRepository;
private final DomaineMapper domaineMapper;

    public DomaineServiceImpl(DomaineRepository domaineRepository, DomaineMapper domaineMapper) {
        this.domaineRepository = domaineRepository;
        this.domaineMapper = domaineMapper;
    }

    @Override
    public Domaine getDomaineById(Long id)  {
        return domaineRepository.findById(id).orElse(null);
    }


    @Override
    public List<DomaineDTo> getAllDomaines() {
        return domaineRepository.findAll().stream()
                .map(domaineMapper::ToDomaineDTo)
                .collect(Collectors.toList());
    }

    @Override
    public DomaineDTo createDomaine(DomaineDTo domaineDTo) {
        return domaineMapper.ToDomaineDTo(domaineRepository.save(domaineMapper.ToDomaine(domaineDTo)));
    }

    @Override
    public DomaineDTo updateDomaine(Long id, DomaineDTo domaineDTo) {
        Optional<Domaine> existingDomaine = domaineRepository.findById(id);
        if (existingDomaine.isPresent()) {
            domaineMapper.updateDomaineFromDTO(domaineDTo, existingDomaine.get());
            return domaineMapper.ToDomaineDTo(domaineRepository.save(existingDomaine.get()));
        }
        return null;
    }

    @Override
    public void deleteDomaine(Long id) {
        try {
            domaineRepository.deleteById(id);
            System.out.println("Domaine deleted successfully with ID: " + id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Domaine with ID " + id + " not found");
            throw e;
        } catch (Exception e) {
            System.err.println("Error deleting Domaine");
            throw e;
        }
    }

    @Override
    public void deleteAllDomaine() {
        try {
            domaineRepository.deleteAll();
            System.out.println("All Domaines deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting all Domaines");
            throw e;
        }

    }
}
