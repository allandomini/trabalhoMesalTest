package com.example.demo.service;

import com.example.demo.model.Candidate;
import com.example.demo.repositories.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    public Candidate save(Candidate candidato) {
        candidato.setStatus("ATIVO");
        return candidatoRepository.save(candidato);
    }

    public void delete(Long id) {
        Candidate candidato = candidatoRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidato não encontrado"));
        candidato.setStatus("INATIVO");
        candidatoRepository.save(candidato);
    }

    public List<Candidate> findActiveCandidates() {
        return candidatoRepository.findActiveCandidates();
    }

    // Removido método apurarVotos
}
