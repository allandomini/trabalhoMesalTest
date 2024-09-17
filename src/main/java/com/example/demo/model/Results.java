package com.example.demo.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Results {
    private int totalVotes;
    private int totalBlankVotes;
    private List<String> listaCandidatosPrefeito;
    private List<String> listaCandidatosVereador;

    public Results(int totalVotes, int totalBlankVotes, List<String> listaCandidatosPrefeito, List<String> listaCandidatosVereador) {
        this.totalVotes = totalVotes;
        this.totalBlankVotes = totalBlankVotes;
        this.listaCandidatosPrefeito = listaCandidatosPrefeito;
        this.listaCandidatosVereador = listaCandidatosVereador;
    }

}
