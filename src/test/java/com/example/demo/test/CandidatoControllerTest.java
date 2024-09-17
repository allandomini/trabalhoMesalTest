    package com.example.demo.test;


import com.example.demo.Controller.CandidatoController;
import com.example.demo.model.Candidate;
import com.example.demo.service.CandidatoService;
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

public class CandidatoControllerTest {

    @InjectMocks
    private CandidatoController candidatoController;
    
    @Mock
    private CandidatoService candidatoService;

    private MockMvc mockMvc; 

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(candidatoController).build();
    }

    @Test
    public void testSave() throws Exception {
        Candidate candidato = new Candidate();
        candidato.setName("Test");
        when(candidatoService.save(any(Candidate.class))).thenReturn(candidato);

        mockMvc.perform(post("/candidatos/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));

        verify(candidatoService, times(1)).save(any(Candidate.class));
    }

    @Test
    public void testUpdate() throws Exception {
        Candidate candidato = new Candidate();
        candidato.setName("Test");
        when(candidatoService.save(any(Candidate.class))).thenReturn(candidato);

        mockMvc.perform(put("/candidatos/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));

        verify(candidatoService, times(1)).save(any(Candidate.class));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/candidatos/delete/1"))
                .andExpect(status().isOk());

        verify(candidatoService, times(1)).delete(1L);
    }

    @Test
    public void testFindActiveCandidates() throws Exception {
        when(candidatoService.findActiveCandidates()).thenReturn(Arrays.asList(new Candidate(), new Candidate()));

        mockMvc.perform(get("/candidatos/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(candidatoService, times(1)).findActiveCandidates();
    }
}
