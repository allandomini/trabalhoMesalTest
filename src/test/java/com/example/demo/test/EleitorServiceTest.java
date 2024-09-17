package com.example.demo.test;

import com.example.demo.model.Status;
import com.example.demo.model.Voter;
import com.example.demo.repositories.EleitorRepository;
import com.example.demo.service.EleitorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EleitorServiceTest {

    @InjectMocks
    private EleitorService eleitorService;

    @Mock
    private EleitorRepository eleitorRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Voter eleitor = new Voter();
        eleitor.setCpf("12345678909");
        eleitor.setEmail("test@example.com");

        eleitorService.save(eleitor);

        assertEquals(Status.APT, eleitor.getStatus());
        verify(eleitorRepository, times(1)).save(eleitor);
    }

    @Test
    public void testUpdate() {
        Voter eleitor = new Voter();
        eleitor.setCpf("12345678909");
        eleitor.setEmail("test@example.com");

        eleitorService.update(eleitor);

        assertEquals(Status.APT, eleitor.getStatus());
        verify(eleitorRepository, times(1)).save(eleitor);
    }

    @Test
    public void testDelete() throws Exception {
        Voter eleitor = new Voter();
        eleitor.setStatus(Status.PENDING);

        eleitorService.delete(eleitor);

        assertEquals(Status.INACTIVE, eleitor.getStatus());
        verify(eleitorRepository, times(1)).save(eleitor);
    }

    @Test
    public void testFindAll() {
        Voter eleitor1 = new Voter();
        Voter eleitor2 = new Voter();

        when(eleitorRepository.findAll()).thenReturn(Arrays.asList(eleitor1, eleitor2));

        List<Voter> result = eleitorService.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(eleitor1));
        assertTrue(result.contains(eleitor2));
    }

    @Test
    public void testIsValidEmail() {
        assertTrue(eleitorService.isValidEmail("test@example.com"));
        assertFalse(eleitorService.isValidEmail("invalid-email"));
    }

    @Test
    public void testIsValidCPF() {
        assertTrue(eleitorService.isValidCPF("12345678909"));
        assertFalse(eleitorService.isValidCPF("invalid-cpf"));
    }
}