package com.example.demo.test;

import com.example.demo.service.VotoService;
import org.junit.jupiter.api.Test;

import com.example.demo.model.Candidate;
import com.example.demo.model.Vote;
import com.example.demo.repositories.CandidatoRepository;
import com.example.demo.repositories.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VotoIntegrationTest {

    @Autowired
    private VotoService votoService;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private VotoRepository votoRepository;

    @Test
    public void testVoto() {
        Candidate candidato1 = new Candidate();
        candidato1.setName("Joao");
        candidato1.setCpf("4490583405934");
        candidato1.setEmail("joao@gmail.com");
        candidato1.setFuncao(1);
        candidato1.setNumeroCandidato("13");
        candidato1.setStatus("Ativo");

        Candidate candidato2 = new Candidate();
        candidato2.setName("Maria");
        candidato2.setCpf("3424324234");
        candidato2.setEmail("Maria@gmail.com");
        candidato2.setFuncao(1);
        candidato2.setNumeroCandidato("22");
        candidato2.setStatus("Ativo");

        candidatoRepository.save(candidato1);
        candidatoRepository.save(candidato2);

        Vote voto1 = new Vote();
        voto1.setPrefeito(candidato2);
        voto1.setVereador(candidato1);

        votoRepository.save(voto1);

        assertNotNull(voto1.getId());
    }
}