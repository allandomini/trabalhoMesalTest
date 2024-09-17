package com.example.demo.repositories;

import com.example.demo.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidate, Long> {

    @Query("SELECT c FROM Candidate c WHERE c.status = 'ativo'")
    List<Candidate> findActiveCandidates();

    // Removido método countVotesByCandidate
    // @Query("SELECT c.numeroCandidato, SUM(c.votosApurados) FROM Candidate c GROUP BY c.numeroCandidato")
    // List<Object[]> countVotesByCandidate();
}
