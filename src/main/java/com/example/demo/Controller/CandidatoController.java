package com.example.demo.Controller;

import com.example.demo.model.Candidate;
import com.example.demo.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @PostMapping("/save")
    public Candidate save(@RequestBody Candidate candidato) {
        return candidatoService.save(candidato);
    }

    @PutMapping("/update")
    public Candidate update(@RequestBody Candidate candidato) {
        return candidatoService.save(candidato);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        candidatoService.delete(id);
    }

    @GetMapping("/active")
    public List<Candidate> findActiveCandidates() {
        return candidatoService.findActiveCandidates();
    }
}
