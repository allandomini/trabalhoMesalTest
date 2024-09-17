package com.example.demo.test;

import com.example.demo.model.Candidate;
import com.example.demo.model.Status;
import com.example.demo.model.Vote;
import com.example.demo.model.Voter;
import com.example.demo.repositories.VotoRepository;
import com.example.demo.repositories.CandidatoRepository;
import com.example.demo.service.VotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VotoServiceTest {

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private CandidatoRepository candidatoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testVotar() {
        Voter eleitor = new Voter();
        eleitor.setStatus(Status.APT);

        Candidate prefeito = new Candidate();
        prefeito.setFuncao(1);
        prefeito.setStatus("ATIVO");

        Candidate vereador = new Candidate();
        vereador.setFuncao(2);
        vereador.setStatus("ATIVO");

        votoService.votar(eleitor, prefeito, vereador);

        verify(votoRepository, times(1)).save(any(Vote.class));
    }

    @Test
    public void testVotarInvalidCandidates() {
        Voter eleitor = new Voter();
        eleitor.setStatus(Status.APT);

        Candidate prefeito = new Candidate();
        prefeito.setFuncao(2); // Funcao inválida
        prefeito.setStatus("ATIVO");

        Candidate vereador = new Candidate();
        vereador.setFuncao(1); // Funcao inválida
        vereador.setStatus("ATIVO");

        assertThrows(RuntimeException.class, () -> {
            votoService.votar(eleitor, prefeito, vereador);
        });
    }

    @Test
    public void testVotarInactiveCandidates() {
        Voter eleitor = new Voter();
        eleitor.setStatus(Status.APT);

        Candidate prefeito = new Candidate();
        prefeito.setFuncao(1);
        prefeito.setStatus("INATIVO"); // Status inválido

        Candidate vereador = new Candidate();
        vereador.setFuncao(2);
        vereador.setStatus("INATIVO"); // Status inválido

        assertThrows(RuntimeException.class, () -> {
            votoService.votar(eleitor, prefeito, vereador);
        });
    }

    // Teste adicional para cobrir um cenário não testado
    @Test
    public void testVotarWithNullVoter() {
        Candidate prefeito = new Candidate();
        prefeito.setFuncao(1);
        prefeito.setStatus("ATIVO");

        Candidate vereador = new Candidate();
        vereador.setFuncao(2);
        vereador.setStatus("ATIVO");

        assertThrows(IllegalArgumentException.class, () -> {
            votoService.votar(null, prefeito, vereador);
        });
    }

    // Teste adicional para cobrir o método realizarApuracao
    @Test
    public void testRealizarApuracao() {
        Candidate candidato1 = new Candidate();
        candidato1.setVotosApurados(10);

        Candidate candidato2 = new Candidate();
        candidato2.setVotosApurados(20);

        when(candidatoRepository.findActiveCandidates()).thenReturn(Arrays.asList(candidato1, candidato2));

        List<Candidate> result = votoService.realizarApuracao();

        assertEquals(2, result.size());
        assertEquals(candidato2, result.get(0));
        assertEquals(candidato1, result.get(1));
    }


    @Test
    public void testRealizarApuracaoNoActiveCandidates() {
        when(candidatoRepository.findActiveCandidates()).thenReturn(Collections.emptyList());

        List<Candidate> result = votoService.realizarApuracao();

        assertTrue(result.isEmpty());
    }
}