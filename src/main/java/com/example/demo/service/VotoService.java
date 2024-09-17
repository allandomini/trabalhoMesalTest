package com.example.demo.service;

import com.example.demo.model.Candidate;
import com.example.demo.model.Status;
import com.example.demo.model.Vote;
import com.example.demo.model.Voter;
import com.example.demo.repositories.CandidatoRepository;
import com.example.demo.repositories.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    public void votar(Voter eleitor, Candidate prefeito, Candidate vereador) {
        if (eleitor == null) {
            throw new IllegalArgumentException("Eleitor não pode ser nulo.");
        }
        if (prefeito.getFuncao() != 1 || vereador.getFuncao() != 2 || !prefeito.getStatus().equals("ATIVO") || !vereador.getStatus().equals("ATIVO")) {
            throw new RuntimeException("Candidatos inválidos.");
        }

        Vote voto = new Vote(prefeito, vereador);
        votoRepository.save(voto);
    }

    public List<Candidate> realizarApuracao() {
        List<Candidate> candidatosAtivos = candidatoRepository.findActiveCandidates();

        return candidatosAtivos.stream()
                .sorted((c1, c2) -> Integer.compare(c2.getVotosApurados(), c1.getVotosApurados()))
                .collect(Collectors.toList());
    }
}
