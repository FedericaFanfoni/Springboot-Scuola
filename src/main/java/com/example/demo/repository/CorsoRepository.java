package com.example.demo.repository;

import com.example.demo.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, Integer> {

    @Query(value = "SELECT * FROM corsi WHERE id_docente != :idDocente", nativeQuery = true)
    List<Corso> findForDocente(@Param("idDocente") Integer idDocente);

    @Query(value =
            "SELECT c.* FROM corsi c\n" +
            "WHERE NOT EXISTS (\n" +
            "\tSELECT 1 \n" +
            "\tFROM corsidiscenti cd\n" +
            "\tWHERE cd.id_corso = c.id\n" +
            "\tAND cd.id_discente = :idDiscente\n" +
            ")" , nativeQuery = true)
    List<Corso> findForDiscente(@Param("idDiscente") Integer idDiscente);

}
