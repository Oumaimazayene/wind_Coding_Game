package com.demo.demo.ServiceImpl;

import com.demo.demo.Repository.CandidateRepository;
import com.demo.demo.Repository.SoumetRepository;
import com.demo.demo.Repository.TestRepository;
import com.demo.demo.Service.SoumetService;
import com.demo.demo.dtos.SoumetDTo;
import com.demo.demo.entity.Candidate;
import com.demo.demo.entity.Soumet;
import com.demo.demo.entity.Test;
import com.demo.demo.mappers.SoumetMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SoumetServiceImpl implements SoumetService {
    private final SoumetRepository soumetRepository;
    private final SoumetMapper soumetMapper;
    private final CandidateRepository candidateRepository;
    private final TestRepository testRepository ;

    public SoumetServiceImpl(SoumetRepository soumetRepository, SoumetMapper soumetMapper, CandidateRepository candidateRepository, TestRepository testRepository) {
        this.soumetRepository = soumetRepository;
        this.soumetMapper = soumetMapper;
        this.candidateRepository = candidateRepository;
        this.testRepository = testRepository;
    }

    @Override
    public Soumet getsoumetTestById(Long id)  {
        return soumetRepository.findById(id).orElse(null);
    }

    @Override
    public List<SoumetDTo> getAll() {
        return soumetRepository.findAll().stream()
                .map(soumetMapper::ToSoumetDTo)
                .collect(Collectors.toList());    }


    @Override
    public SoumetDTo create(SoumetDTo soumetDTo) {
        Long candidate_id = soumetDTo.getCandidate_id();
        Long test_id = soumetDTo.getTest_id();
        Candidate candidate = candidateRepository.findById(candidate_id)
                .orElseThrow(() -> new RuntimeException("candidate non trouvé avec l'id " + candidate_id));
        Test test = testRepository.findById(test_id)
                .orElseThrow(() -> new RuntimeException("test non trouvé avec l'id " + test_id));
        Soumet soumet = soumetMapper.ToSoumet(soumetDTo);
        soumet.setCandidate(candidate);
        soumet.setTest(test);
        Soumet savedSoumet =soumetRepository.save(soumet);

        return soumetMapper.ToSoumetDTo(savedSoumet);
    }

    @Override
    public SoumetDTo update(Long id, SoumetDTo soumetDTo) {
        Optional<Soumet> existingSoumet =soumetRepository.findById(id);
        if (existingSoumet.isPresent()) {
            soumetMapper.updateFromDTO(soumetDTo, existingSoumet.get());
            return soumetMapper.ToSoumetDTo(soumetRepository.save(existingSoumet.get()));
        }
        return null;    }

    @Override
    public void delete(Long id) {
        try {
            soumetRepository.deleteById(id);
            System.out.println(" deleted successfully with ID: " + id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(" with ID " + id + " not found");
            throw e;
        } catch (Exception e) {
            System.err.println("Error deleting ");
            throw e;
        }

    }

    @Override
    public void deleteAll() {
        try {
            soumetRepository.deleteAll();
            System.out.println("All  deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting all ");
            throw e;
        }
    }
}
