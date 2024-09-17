package com.example.demo.Controller;

import com.example.demo.model.Candidate;
import com.example.demo.model.Voter;

public class VotoRequest {
    private Voter eleitor;
    private Candidate prefeito;
    private Candidate vereador;

    public Voter getEleitor() {
        return eleitor;
    }

    public void setEleitor(Voter eleitor) {
        this.eleitor = eleitor;
    }

    public Candidate getPrefeito() {
        return prefeito;
    }

    public void setPrefeito(Candidate prefeito) {
        this.prefeito = prefeito;
    }

    public Candidate getVereador() {
        return vereador;
    }

    public void setVereador(Candidate vereador) {
        this.vereador = vereador;
    }
}
