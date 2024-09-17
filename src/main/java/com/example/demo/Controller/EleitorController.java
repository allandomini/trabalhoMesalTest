package com.example.demo.Controller;

import com.example.demo.model.Voter;
import com.example.demo.service.EleitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/eleitores")
public class EleitorController {

    @Autowired
    private EleitorService eleitorService;

    private static final Logger logger = LoggerFactory.getLogger(EleitorController.class);

    @PostMapping("/save")
    public void save(@RequestBody Voter eleitor) {
        logger.info("Recebida requisição para salvar eleitor: {}", eleitor);
        eleitorService.save(eleitor);
    }

    @PutMapping("/update")
    public void update(@RequestBody Voter eleitor) {
        eleitorService.update(eleitor);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Voter eleitor) throws Exception {
        eleitorService.delete(eleitor);
    }

    @GetMapping("/findAll")
    public List<Voter> findAll() {
        logger.info("Recebida requisição para buscar todos os eleitores");
        return eleitorService.findAll();
    }
}
