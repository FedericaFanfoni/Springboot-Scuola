package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "corsi")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome_corso")
    private String nomeCorso;
    @Column(name = "data_inizio")
    private LocalDate dataInizio;
    private Integer durata;
    @ManyToOne
    @JoinColumn(name = "id_docente")
    private Docente docente;
    @ManyToMany
    @JoinTable(
            name = "corsidiscenti",
            joinColumns = @JoinColumn(name = "id_corso"),
            inverseJoinColumns = @JoinColumn(name = "id_discente"))
    private List<Discente> discenti;

}
