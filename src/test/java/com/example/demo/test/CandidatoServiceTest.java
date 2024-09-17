package com.example.demo.test;



import com.example.demo.model.Candidate;
import com.example.demo.repositories.CandidatoRepository;
import com.example.demo.service.CandidatoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CandidatoServiceTest {

    @Mock
    private CandidatoRepository candidatoRepository;

    @InjectMocks
    private CandidatoService candidatoService;

    public CandidatoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Candidate candidato = new Candidate();
        candidato.setName("João");
        candidato.setCpf("12345678900");
        candidato.setEmail("joao@example.com");
        candidato.setNumeroCandidato("123");
        candidato.setFuncao(1);

        when(candidatoRepository.save(any(Candidate.class))).thenReturn(candidato);

        Candidate savedCandidato = candidatoService.save(candidato);

        assertEquals("ATIVO", savedCandidato.getStatus());
        assertEquals("João", savedCandidato.getName());
    }

    @Test
    public void testDelete() {
        Candidate candidato = new Candidate();
        candidato.setStatus("ATIVO");

        when(candidatoRepository.findById(anyLong())).thenReturn(Optional.of(candidato));

        candidatoService.delete(1L);

        assertEquals("INATIVO", candidato.getStatus());
        verify(candidatoRepository, times(1)).save(candidato);
    }

    @Test
    public void testDeleteNotFound() {
        when(candidatoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            candidatoService.delete(1L);
        });
    }
}