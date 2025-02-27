package com.example.demo.repository;

import com.example.demo.entity.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscenteRepository extends JpaRepository<Discente, Integer> {

    @Query(value = "SELECT * FROM discenti WHERE matricola = :matricola", nativeQuery = true)
    Optional<Discente> findByMatricola(@Param("matricola") String matricola);

    @Query(value =
            "SELECT d.* FROM discenti d\n" +
            "WHERE NOT EXISTS (\n" +
            "SELECT 1 FROM corsidiscenti cd\n" +
            "WHERE cd.id_discente = d.id\n" +
            "AND cd.id_corso = :idCorso" +
            ")", nativeQuery = true)
    List<Discente> filterForCorso(@Param("idCorso") Integer idCorso);
}

