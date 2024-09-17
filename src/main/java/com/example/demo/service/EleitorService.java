package com.example.demo.service;

import com.example.demo.model.Voter;
import com.example.demo.model.Status;
import com.example.demo.repositories.EleitorRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class EleitorService {

    @Autowired
    private EleitorRepository eleitorRepository;

    private static final Logger logger = LoggerFactory.getLogger(EleitorService.class);

    public void save(Voter eleitor) {
        try {
            logger.info("Salvando eleitor: {}", eleitor);
            if (isValidCPF(eleitor.getCpf()) && isValidEmail(eleitor.getEmail())) {
                eleitor.setStatus(Status.APT);
            } else {
                eleitor.setStatus(Status.PENDING);
            }
            eleitorRepository.save(eleitor); // Salvar no banco de dados
            logger.info("Eleitor salvo com sucesso: {}", eleitor);
        } catch (Exception e) {
            logger.error("Erro ao salvar eleitor: {}", eleitor, e);
            throw e;
        }
    }

    public void update(Voter eleitor) {
        if (isValidCPF(eleitor.getCpf()) && isValidEmail(eleitor.getEmail())) {
            eleitor.setStatus(Status.APT);
        } else {
            eleitor.setStatus(Status.PENDING);
        }
        eleitorRepository.save(eleitor); 
    }

    public void delete(Voter eleitor) throws Exception {
        if (eleitor.getStatus() == Status.APT) {
            throw new Exception("Usuário já votou. Não foi possível inativá-lo");
        } else {
            eleitor.setStatus(Status.INACTIVE);
            eleitorRepository.save(eleitor); 
        }
    }

    public boolean isValidCPF(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);
        return cpfValidator.isValid(cpf, null);
    }

    public boolean isValidEmail(String email) {
        EmailValidator emailValidator = new EmailValidator();
        emailValidator.initialize(null);
        return emailValidator.isValid(email, null);
    }

    public List<Voter> findAll() {
        logger.info("Buscando todos os eleitores");
        return eleitorRepository.findAll();
    }
}
