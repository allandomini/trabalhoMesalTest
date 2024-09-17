package com.example.demo.Controller;

import com.example.demo.model.Candidate;
import com.example.demo.model.Voter;
import com.example.demo.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping("/votar")
    public void votar(@RequestBody VotoRequest votoRequest) {
        votoService.votar(votoRequest.getEleitor(), votoRequest.getPrefeito(), votoRequest.getVereador());
    }

    @GetMapping("/apuracao")
    public List<Candidate> realizarApuracao() {
        return votoService.realizarApuracao();
    }
}

