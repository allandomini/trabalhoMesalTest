package com.example.demo.model;

import java.time.LocalDateTime;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "votes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @ManyToOne
    private Candidate prefeito;

    @ManyToOne
    private Candidate vereador;

    private String hash;

    public Vote(Candidate prefeito, Candidate vereador) {
        if (prefeito == null || vereador == null) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios.");
        }
        this.dataHora = LocalDateTime.now();
        this.prefeito = prefeito;
        this.vereador = vereador;
        this.hash = generateHash();
    }

    private String generateHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = prefeito.toString() + vereador.toString() + dataHora.toString();
            byte[] hashBytes = digest.digest(input.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash", e);
        }
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getHash() {
        return hash;
    }
}
