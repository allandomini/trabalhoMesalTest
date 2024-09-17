package com.example.demo.test;

import com.example.demo.Controller.VotoController;
import com.example.demo.Controller.VotoRequest;
import com.example.demo.model.Candidate;
import com.example.demo.model.Voter;
import com.example.demo.service.VotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VotoControllerTest {

    @InjectMocks
    private VotoController votoController;

    @Mock
    private VotoService votoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(votoController).build();
    }

    @Test
    public void testVotar() throws Exception {
        VotoRequest votoRequest = new VotoRequest();
        votoRequest.setEleitor(new Voter());
        votoRequest.setPrefeito(new Candidate());
        votoRequest.setVereador(new Candidate());

        mockMvc.perform(post("/votos/votar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"eleitor\":{}, \"prefeito\":{}, \"vereador\":{}}"))
                .andExpect(status().isOk());

        verify(votoService, times(1)).votar(any(Voter.class), any(Candidate.class), any(Candidate.class));
    }

    @Test
    public void testRealizarApuracao() throws Exception {
        when(votoService.realizarApuracao()).thenReturn(Arrays.asList(new Candidate(), new Candidate()));

        mockMvc.perform(get("/votos/apuracao"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(votoService, times(1)).realizarApuracao();
    }
}