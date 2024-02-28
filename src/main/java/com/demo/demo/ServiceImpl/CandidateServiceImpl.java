package com.demo.demo.ServiceImpl;

import com.demo.demo.Repository.CandidateRepository;
import com.demo.demo.Service.CandidateService;
import com.demo.demo.dtos.CandidateDTO;
import com.demo.demo.entity.Candidate;
import com.demo.demo.entity.Type;
import com.demo.demo.mappers.CandidateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
        this.candidateRepository = candidateRepository;
        this.candidateMapper = candidateMapper;
    }

    @Override
    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    @Override
    public List<CandidateDTO> getAllCandidates() {
        return candidateRepository.findAll().stream()
                .map(candidateMapper::ToCandidateDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateDTO createCandidate(CandidateDTO candidateDTO) {
        return candidateMapper.ToCandidateDTO(candidateRepository.save(candidateMapper.ToCandidate(candidateDTO)));
    }

    @Override
    public CandidateDTO updateCandidate(Long id, CandidateDTO candidateDTO) {
        Optional<Candidate> existingCandidate = candidateRepository.findById(id);
        if (existingCandidate.isPresent()) {
            candidateMapper.updateCandidateFromDTO(candidateDTO, existingCandidate.get());
            return candidateMapper.ToCandidateDTO(candidateRepository.save(existingCandidate.get()));
        }
        return null;
    }

    @Override
    public void deleteCandidate(Long id) {
        try {
            candidateRepository.deleteById(id);
            System.out.println("Candidate deleted successfully with ID: " + id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Candidate with ID " + id + " not found");
            throw e;
        } catch (Exception e) {
            System.err.println("Error deleting Candidate");
            throw e;
        }
    }

    @Override
    public void deleteAllCandidates() {
        try {
            candidateRepository.deleteAll();
            System.out.println("All Candidates deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting all Candidates");
            throw e;
        }
    }
}